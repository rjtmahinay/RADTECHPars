
package com.radtech;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Sphere
 */
public class InformationAction extends ActionSupport implements ModelDriven<Information>, SessionAware{

    private Information information = new Information();
    SessionMap sessionmap;
    
    @Override
    public Information getModel(){
        return information;
    }
    
    public void setSession(Map m){
        sessionmap = (SessionMap)m;
    }
    
    public void setInformation(Information in){
        information = in;
    }
    
    public String add(){
        Session session = null;
        if(information!=null){
            try {
                session= ((SessionFactory)sessionmap.get("factory")).openSession();
                session.getTransaction().begin();
                session.save(information);
                sessionmap.put("view", (List)session.createQuery("from Information").list());
                information=null;
                session.getTransaction().commit();
            }catch (HibernateException e){
                e.printStackTrace();
            }
            finally{
                if(session!=null)session.close();
            }
        }
        return SUCCESS;
    }
    
    public String get(){
        Session session = null;
        try{
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            
            Information info = (Information)session.get(Information.class, information.getId());
            System.out.println("Owner name is " + info.getOwnerName());
            if(info == null){
                addActionError("Record is not found");
                return INPUT;
            }
            else{
                sessionmap.put("currentRecord", info);
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        finally {
            if(session!=null) session.close();
        }
        return SUCCESS;
    }
    
    public String toArchive(){
        
        Session session = null;
        try {
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            Transaction tx = session.getTransaction();
            tx.begin();
            Information info = (Information)session.load(Information.class, information.getId());
            Archive arc = new Archive();
            arc.setInformation(info);
            session.save(arc);
            session.delete(info);
            session.flush();
            System.out.println(arc.toString() + " arc");
            System.out.println(info.toString() + "info");
            sessionmap.put("view", session.createQuery("from Information").list());
            tx.commit();

        }catch (HibernateException e){
            session.getTransaction().rollback();
        }
        finally{
            if(session!=null)session.close();
        }
        
        return SUCCESS;
    }
    public String execute(){
        return SUCCESS;
    }
}
