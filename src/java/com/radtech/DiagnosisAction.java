package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


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
    }
    public String addDiagnosis(){
        long controlNumber = ((Information)sessionmap.get("currentRecord")).getControlNumber();
        model.setControlNumber(controlNumber);
        System.out.println(controlNumber + " is the controlNumber");
        Session session = null;
        try{
            session= ((SessionFactory)sessionmap.get("factory")).openSession();
            session.getTransaction().begin();
            //setting date
            DateFormat dateFormat = new SimpleDateFormat("MMMM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            model.setDateDiagnosed(date);
            
            System.out.println(model.getControlNumber());
            System.out.println(model.getDateDiagnosed());
            System.out.println(model.getDiagnosis());
            System.out.println(model.getDiagnosisNumber());
            session.save(model);
            model=null;
            session.getTransaction().commit();
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        finally{
            if(session!=null) session.close();
        }
        return SUCCESS;
    }
}
