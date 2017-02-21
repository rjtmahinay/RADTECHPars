package com.radtech;

import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Dataholder implements SessionAware{
    private static SessionFactory factory;
    private SessionMap sessionmap;
    
    public Dataholder(){
        //factory = new Configuration().configure().buildSessionFactory();

    }
    public void viewlist(){
        System.out.println("Inside viewlist, opening session...");
        Session session = factory.openSession();
        System.out.println(sessionmap == null);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List records = session.createQuery("FROM MEDICALDB").list();
            for(Object s: records){
            System.out.println(s.toString());
        }
        
        tx.commit();
        sessionmap.put(records,"view");
        }
        catch (HibernateException e) {
        if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }
        finally {
            session.close(); 
        }
    }
    
    public void setSession(Map m){
        sessionmap = (SessionMap) m;
        factory = (SessionFactory)sessionmap.get("factory");
    }
}
