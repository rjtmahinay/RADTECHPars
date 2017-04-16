package com.radtech;

import org.hibernate.HibernateException;

public class ConsultationAction extends GenericAction{
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
}
//consultation
//  add consultation
//  edit consultation
//  view consultation
