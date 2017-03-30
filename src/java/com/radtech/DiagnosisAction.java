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

public class DiagnosisAction extends ActionSupport implements ModelDriven<Diagnosis>, SessionAware {

    private Diagnosis model = new Diagnosis();
    private SessionMap sessionmap;

    @Override
    public Diagnosis getModel() {
        return model;
    }

    @Override
    public void setSession(Map m) {
        sessionmap = (SessionMap) m;
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    @Override
    public void validate() {
        if (model.getDiagnosis() == null | model.getDiagnosis().equals("")) {
            addFieldError("diagnosis", "Diagnosis cannot be empty");
        }
    }

    public String addDiagnosis() {
        if(checkUser()){
            addFieldError("username", "Session Timeout");
            return "error";
        }
        Session session = null;
        Transaction tx = null;
        try {
            session = ((SessionFactory) sessionmap.get("factory")).openSession();
            tx = session.getTransaction();
            tx.begin();
            System.out.println(model.getId());
            Information info = (Information) session.load(Information.class, Long.parseLong(model.getId()));
            System.out.println(info.toString());
            if (info != null) {
                model.setInformation(info);
                model.setDateDiagnosed(new Date());
                session.save(model);
                info.getAppointments();
                session.merge(info);
                session.flush();
                model = null;
                tx.commit();
                sessionmap.put("currentRecord", info);
            }
            
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return SUCCESS;
    }
    public boolean checkUser(){
        return sessionmap.get("currentUser")==null;
    }
}
