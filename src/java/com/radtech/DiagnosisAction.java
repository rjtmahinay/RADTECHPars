package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class DiagnosisAction extends ActionSupport implements ModelDriven<Diagnosis>, SessionAware{
    private Diagnosis model = new Diagnosis();
    private SessionMap sessionmap;
    @Override
    public Diagnosis getModel(){
        return model;
    }
    @Override
    public void setSession(Map m){
        sessionmap = (SessionMap)m;
    }
    @Override
    public String execute(){
        return SUCCESS;
    }
    @Override
    public void validate(){
        if(sessionmap.get("currentRecord")==null);
        if(model.getDiagnosis() == null | model.getDiagnosis().equals("")){
            addFieldError("diagnosis", "Diagnosis cannot be empty");
        }
    }
    public String addDiagnosis(){
        
        Session session = null;
        Transaction tx=null;
        try{
            session= ((SessionFactory)sessionmap.get("factory")).openSession();
            tx = session.getTransaction();
            tx.begin();
            Date date = new Date();
            model.setDateDiagnosed(date);
            model.setControlNumber(Long.parseLong(model.getId()));
            Information info = (Information)session.load(Information.class, model.getControlNumber());
            model.setInformation(info);
            session.save(model);  
            sessionmap.put("currentRecord", info);
            model=null;
            tx.commit();
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
        }
        finally{
            if(session!=null) session.close();
        }
        return SUCCESS;
    }
}
