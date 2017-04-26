package com.radtech;


import org.hibernate.HibernateException;

public class AppointmentAction extends GenericAction{

    Appointment app = new Appointment();
    int[] scores = new int[12];

    @Override
    public Appointment getModel() {
        return app;
    }   

    public String addAppointment() {
        System.out.println(app.getInput3());
        String[] pets = app.getInput3().split(",");
        try{
            session = getSession();
            tx = session.getTransaction();
            tx.begin();
            app.setAppointmentDate(toDate(app.getDateInput()));
            session.persist(app);
            session.flush();
            for(String s: pets){
                if(s.length()>0){
                    Pet pet = (Pet)session.load(Pet.class, Long.parseLong(s.trim()));
                    Consultation consultation = new Consultation();
                    session.persist(consultation);
                    session.flush();
                    consultation.setPet(pet);
                    pet.getConsultations().add(consultation);
                    
                    consultation.setAppointment(app);
                    app.getConsultations().add(consultation);
                    session.flush();
                    consultation.setWeight(-1);
                    consultation.setStatus("pending");
                    if(app.getCustomer()==null)app.setCustomer(pet.getOwner());
                }
                else {
                    addActionError("Id not found");
                    return INPUT;
                }
            }
            app.setTransactionType("scheduled");
            app.setStatus("pending");
            tx.commit();
            refresh();
            return SUCCESS;
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
            return INPUT;
        }
        finally{
            if(session!= null) session.close();
        }
    }

//    public void tallyMonths() {
//        //where MONTH(so.date) = MONTH(:date);
//        System.out.println("Inside tally month");
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//        Session session = null;
//        try {
//            session = ((SessionFactory) sessionmap.get("factory")).openSession();
//            java.time.LocalDateTime now = java.time.LocalDateTime.now();
//            int year = now.getYear();
//            for (int i = 0; i < 12; i++) {
//                java.util.Calendar cal = java.util.Calendar.getInstance();
//                cal.setTime(sdf.parse(i + 1 + "/01/" + year));
//                Date inTime = cal.getTime();
//                System.out.println(inTime);
//                cal.add(Calendar.MONTH, 1);
//                cal.add(Calendar.DATE, -1);
//                Date outTime = cal.getTime();
//                System.out.println(outTime);
//                Criteria crit = session.createCriteria(Appointment.class);
//                crit.setProjection(Projections.property("date"));
//                crit.add(Restrictions.between("date", inTime, outTime));
//                List list = crit.list();
//                if (list == null) {
//                    scores[i] = 0;
//                } else {
//                    scores[i] = list.size();
//                    System.out.println("Size of " + (i+1) + " is " + list.size());
//                }
//            }
//            sessionmap.put("scores", scores);
//        } catch (HibernateException | ParseException e) {
//            System.out.println("Something happened midway...");
//            e.printStackTrace();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
    
    public String completeAppointment(){
        session=getSession();
        tx=session.getTransaction();
        try{
            Appointment appoint = ((Appointment)session.get(Appointment.class, Long.parseLong(app.getInput3().trim())));
            if(appoint == null){
                addActionError("Appointment not found!");
                return INPUT;
            }
            appoint = ((Appointment)session.load(Appointment.class, Long.parseLong(app.getInput3().trim())));
            tx.begin();
            hiberialize(appoint.getConsultations());
            for(Object o: appoint.getConsultations()){
                Consultation c = (Consultation)o;
                System.out.println("->consultation is " + c.toString());
                if(c.getConsultationDate()==null){
                    c.setEyes("N/A");
                    c.setEars("N/A");
                    c.setNose("N/A");
                    c.setThroat("N/A");
                    c.setDerma("N/A");
                    c.setGums("N/A");
                    c.setLymphNodes("N/A");
                    c.setDiagnosis("N/A");
                    c.setWeight(0);
                    c.setTemperature(0);
                    c.setStatus("cancelled");
                    session.persist(c);
                    session.flush();
                }
            }
            appoint.setStatus("completed");
            session.persist(appoint);
            tx.commit();
            refresh();
            return SUCCESS;
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
            return INPUT;
        }
        finally{
            if(session!= null)session.close();
        }
    }
    public String cancelAppointment(){
        session=getSession();
        tx=session.getTransaction();
        try{
            tx.begin();
			Appointment appoint = ((Appointment)session.get(Appointment.class, Long.parseLong(app.getInput3().trim())));
            if(appoint == null){
                addActionError("Appointment not found!");
                return INPUT;
            }
            appoint = ((Appointment)session.load(Appointment.class, Long.parseLong(app.getInput3().trim())));
            System.out.println("->Cancelling appointment " + appoint.toString());
            
            hiberialize(appoint.getConsultations());
		for(Object o: appoint.getConsultations()){
			Consultation c = (Consultation)o;
			Pet p = c.getPet();
			hiberialize(p.getConsultations());
			p.getConsultations().remove(c);
			session.merge(p);
			session.delete(c);
		}
			hiberialize(appoint.getCustomer().getAppointments());
		appoint.getCustomer().getAppointments().remove(appoint);
		session.merge(appoint.getCustomer());
		session.delete(appoint);
            tx.commit();
            refresh();
            return SUCCESS;
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
            return INPUT;
        }
        finally{
            if(session!= null)session.close();
        }
    }
}

//appointment
//	add appointment check

//	delete appointment