package com.radtech;

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
}
//consultation
//  add consultation
//  edit consultation
//  view consultation
