package com.radtech;


import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

public class ConsultationAction extends GenericAction{
    Consultation consultation = new Consultation();
    
    @Override
    public Consultation getModel(){
        return consultation;
    }
    public String addConsultation(){
        try{
            session=getSession();
            tx=session.getTransaction();
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
    
    public String setVitals(){
        try{
            session = getSession();
            tx = session.getTransaction();
            if(session.get(Consultation.class, Long.parseLong(consultation.getInput3())) == null){
                addActionError("Consultation is not found");
                return INPUT;
            }
            else{
                tx.begin();
                Consultation consult = (Consultation)session.load(Consultation.class, Long.parseLong(consultation.getInput3()));
                consult.setEyes(consultation.getEyes());
                consult.setEars(consultation.getEars());
                consult.setThroat(consultation.getThroat());
                consult.setNose(consultation.getNose());
                consult.setDerma(consultation.getDerma());
                consult.setLymphNodes(consultation.getLymphNodes());
                consult.setGums(consultation.getGums());
                consult.setTemperature(Double.parseDouble(consultation.getInput1()));
                consult.setWeight(Double.parseDouble(consultation.getInput2()));
                hiberialize(consult.getAppointment().getConsultations());
                tx.commit();
                refresh();
                return SUCCESS;
            }
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
    public String getVitals(){
        try{
            session = getSession();
            Consultation consul = (Consultation)session.get(Consultation.class, consultation.getConsultationId());
            if(consul == null){
                addActionError("Consultation not found");
                return INPUT;
            }
            else{
                consul = (Consultation)session.load(Consultation.class, consultation.getConsultationId());
                hiberialize(consul.getPet());
                hiberialize(consul.getAppointment().getCustomer());
                System.out.println("pet name is " + consul.getPet().getName());
                refreshAppointmentConsultations(consul.getAppointment());
                putMap("currentConsultation", consul);
                return SUCCESS;
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
            return INPUT;
        }
        finally{
            if(session!= null)session.close();
        }
    }
    
    public String addDiagnosis(){
        session = getSession();
        tx = session.getTransaction();
        System.out.println("Current input is " + consultation.toString());
        System.out.println("and input3 is " + consultation.getInput3());
        try{
            Consultation c = (Consultation)session.get(Consultation.class, Long.parseLong(consultation.getInput3()));
            if(c==null){
                addActionError("Consultation not found!");
                return INPUT;
            }
            else{
                tx.begin();
                c = (Consultation)session.load(Consultation.class, Long.parseLong(consultation.getInput3()));
                c.setDiagnosis(consultation.getDiagnosis());
                c.setConsultationDate(new java.util.Date());
                List<Medicine> meds = (List)sessionmap.get("tempMeds");
                System.out.println("Meds size is " + meds.size());
                hiberialize(c.getMedicines());
                if(meds!= null | meds.size()==0)for(Medicine med: meds){
                    session.persist(med);
                    med.setConsultation(c);
                    c.getMedicines().add(med);
                    session.saveOrUpdate(med);
                    session.merge(c);
                    session.flush();
                }
                c.setStatus("completed");
                session.flush();
                checkAppointment(c);
                sessionmap.remove("tempMeds");
                tx.commit();
                refresh();
                return SUCCESS;
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
            return INPUT;
        }
        finally{
            if(session!=null)session.close();
        }
    }
    
}
//consultation
//  add consultation
//  edit consultation
//  view consultation
