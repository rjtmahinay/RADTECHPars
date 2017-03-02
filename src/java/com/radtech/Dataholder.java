package com.radtech;

import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
        List records;
        try{
            tx = session.beginTransaction();
            records = session.createQuery("FROM Information").list();
            for(Object s: records){
            System.out.println(s.toString());
        }
        
        tx.commit();
        sessionmap.put("view", records);
            System.out.println(sessionmap.get("view")==null + " z       ");
        }
        catch (HibernateException e) {
        if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }
        finally {
            session.close(); 
        }
    }
    
    public boolean getRecord(String str){
        //make query
        Session session = factory.openSession();
        Transaction tx = null;
        Information in = null;
        try{
            tx = session.beginTransaction();
            Query qry = session.createQuery("FROM Information where controlNumber= :control");
            long controlNumber;
            try {
                controlNumber = Long.parseLong(str);
            }
            catch(NumberFormatException e){
                throw new NumberFormatException("Input is not a valid control number");
            }
            qry.setParameter("control", controlNumber);
            in = (Information)qry.uniqueResult();
            tx.commit();
            sessionmap.put("currentRecord", in);  
        }
        catch (HibernateException e) {
        if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }
        finally {
            session.close(); 
        }
        
        return true;
    }
    public void setSession(Map m){
        sessionmap = (SessionMap) m;
        factory = (SessionFactory)sessionmap.get("factory");
    }
}
