package com.radtech;


import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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

    public String statize() throws IOException{
        session = getSession();
        tx = session.getTransaction();
        try{
            Date from = toDate(app.getDateInput2());
            Date to = toDate(app.getDateInput3());
            if(from.compareTo(to)>=0){
                addActionError("Invalid date inputs");
                return INPUT;
            }
            else{
                String str = "";
                String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                if(app.getStatType().equals("Appointment")){
                    int[] number = new int[12];
                    List appointments = session.createCriteria(Appointment.class)
                            .add(Restrictions.ge("appointmentDate", from))
                            .add(Restrictions.le("appointmentDate", to))
                            .list();
                    Calendar cal = Calendar.getInstance();
                    for(Object o: appointments){
                        Appointment app = (Appointment)o;
                        cal.setTime(app.getAppointmentDate());
                        int x = cal.get(Calendar.MONTH);
                        if(app.getStatus()!= "cancelled") number[x]+=1;
                    }
                    for(int y=0;y<12;y++){
                    if(number[y]>0){
                        str+= months[y]+","+number[y]+";";
                    }
                    }
                    if(!str.equals("")){
                        str = str.substring(0, str.length()-1);
                    }
                    System.out.println(str);
                    makeJson(str);
                    return SUCCESS;
                }
                else if(app.getStatType().equals("Status")){
                    int[] number = new int[3];
                    //pending,cancelled,complete;
                    List appointments = session.createCriteria(Appointment.class)
                            .add(Restrictions.ge("appointmentDate", from))
                            .add(Restrictions.le("appointmentDate", to))
                            .list();
                    for(Object o: appointments){
                        Appointment appointment = (Appointment)o;
                        System.out.println("Appointment is " + appointment.toString());
                        switch(appointment.getStatus()){
                            case "pending" : number[0]+=1; break;
                            case "cancelled":number[1]+=1; break;
                            case "completed":number[2]+=1; break;
                            default: addActionError("Critical error on building statistics"); return INPUT;
                        }
                        System.out.println(str);
                        str = "pending," + number[0] + ";cancelled," + number[1] + ";completed," + number[2];
                        System.out.println(str);
                        makeJson(str);
                    }
                }
                else if(app.getStatType().equals("Walk-in")){
                    int[] number = new int[12];
                    List appointments = session.createCriteria(Appointment.class)
                            .add(Restrictions.ge("appointmentDate", from))
                            .add(Restrictions.le("appointmentDate", to))
                            .list();
                    Calendar cal = Calendar.getInstance();
                    for(Object o: appointments){
                        Appointment app = (Appointment)o;
                        cal.setTime(app.getAppointmentDate());
                        int x = cal.get(Calendar.MONTH);
                        if(app.getTransactionType().equals("walk-in")) number[x]+=1;
                    }
                    for(int y=0;y<12;y++){
                    if(number[y]>0){
                        str+= months[y]+","+number[y]+";";
                    }
                    }
                    if(!str.equals("")){
                        str = str.substring(0, str.length()-1);
                    }
                    System.out.println(str);
                    makeJson(str);
                    return SUCCESS;
                }
                else if(app.getStatType().equals("Breed")){
                    System.out.println("I am inside breed loop");
                    HashMap<String, Integer> breeds = new HashMap<String, Integer>();
                    List entries = session.createCriteria(Consultation.class)
                            .add(Restrictions.ge("consultationDate", from))
                            .add(Restrictions.le("consultationDate", to))
                            .list();
                    for(Object o: entries){
                        Consultation consult = (Consultation)o;
                        hiberialize(consult.getPet());
                        String petBreed = consult.getPet().getBreed();
                        if(breeds.get(petBreed)==null){
                            breeds.put(petBreed, 1);
                        }
                        else{
                            int x = breeds.get(petBreed);
                            breeds.put(petBreed, ++x);
                        }
                    }
                    // such as
                    for (Map.Entry<String, Integer> pair : breeds.entrySet()) {
                        System.out.println("For key " + pair.getKey());
                        str += pair.getKey() +","+ pair.getValue()+";";
                    }
                    if(str.length()>0){
                        str = str.substring(0, str.length()-1);
                        System.out.println("Breeds " + str);
                        makeJson(str);
                        return SUCCESS;
                    }
                    else{
                        addActionError("Stat table cannot be built");
                        return INPUT;
                    }
                }
                else if(app.getStatType().equals("Medicine")){
                    System.out.println("I am inside Medicine");
                    HashMap<String, Integer> meds = new HashMap<String, Integer>();
                    List entries = session.createCriteria(Consultation.class)
                            .add(Restrictions.ge("consultationDate", from))
                            .add(Restrictions.le("consultationDate", to))
                            .list();
                    for(Object o: entries){
                        Consultation consult = (Consultation)o;
                        hiberialize(consult.getMedicines());
                        for(Object oo: consult.getMedicines()){
                            Medicine med = (Medicine)oo;
                            String medName = med.getMedicineName();
                            if(meds.get(medName)==null){
                                meds.put(medName, 1);
                            }
                            else{
                                int x = meds.get(medName);
                                meds.put(medName, ++x);
                            }
                        }
                    }
                    // such as
                    for (Map.Entry<String, Integer> pair : meds.entrySet()) {
                        System.out.println("For key " + pair.getKey());
                        str += pair.getKey() +","+ pair.getValue()+";";
                    }
                    if(str.length()>0){
                        str = str.substring(0, str.length()-1);
                        System.out.println("Meds " + str);
                        makeJson(str);
                        return SUCCESS;
                    }
                    else{
                        addActionError("Stat table cannot be built");
                        return INPUT;
                    }
                }
                else{
                    addActionError("Statistics build fail");
                    return INPUT;
                }
                
                return SUCCESS;
                
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
            addActionError("Critical error on statize");
            return INPUT;
        }
        finally{
            if(session!=null) session.close();
        }
    }
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
                c.setStatus("cancelled");
                session.merge(c);
            }
            appoint.setStatus("cancelled");
            session.merge(appoint);
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
