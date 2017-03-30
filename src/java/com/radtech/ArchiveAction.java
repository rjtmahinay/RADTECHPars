
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class ArchiveAction extends ActionSupport implements ModelDriven<Archive>, SessionAware{
    private Archive model = new Archive();
    private Object obj;
    private SessionMap sessionmap;
    private long id;
    
    public Archive getModel(){
        return model;
    }
    
    public void setSession(Map m){
        sessionmap = (SessionMap)m;
    }
    
    public String execute(){
        return SUCCESS;
    }
    
    public void validate(){
        if(checkUser()){
            addFieldError("username", "Session timeout");
        }
    }
    
    public String deleteArchive(){
        System.out.println(model.getControlInput());
        Session session = null;
        Transaction tx = null;
        try{
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            tx = session.getTransaction();
            tx.begin();
            Archive arc = (Archive)session.load(Archive.class, Long.parseLong(model.getControlInput()));
            System.out.println(arc.getControlNumber());
            
            if(arc != null) {
                session.delete(arc);
                session.flush();
            }
            
            model = null;
            sessionmap.put("archive", session.createQuery("from Archive order by controlNumber").list());
            tx.commit();   
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
        }
        finally{
            if(session!=null)
                session.close();
        }
        return SUCCESS;
    }
    public boolean checkUser(){
        return sessionmap.get("currentUser")==null;
    }
}
