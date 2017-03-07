
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


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
    
    public String toArchive(){
        
        Session session = null;
        try{
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            session.getTransaction().begin();
            obj = (Information)session.load(Information.class, model.getId());
            System.out.println(((Information)obj).getOwnerName());
            model = new Archive((Information)obj);
            System.out.println(model.getOwnerName());
            Query qry = session.createQuery("delete from Information where controlNumber = :control");
            qry.setParameter("control", model.getControlNumber());
            qry.executeUpdate();
            session.save(model);
            session.getTransaction().commit();
        }
        catch(HibernateException e){
            if(session!=null)session.getTransaction().rollback();
        }
        finally{
            if(session!=null) session.close();
        }
        return SUCCESS;
    }
    
    public void validate(){
        if(sessionmap.get("currentRecord")==null);
    }
}
