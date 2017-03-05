
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        try {
            session= ((SessionFactory)sessionmap.get("factory")).openSession();
            session.getTransaction().begin();
            session.save(information);
            sessionmap.put("view", (List)session.createQuery("from Information").list());
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        finally{
            if(session!=null)session.close();
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
    public String execute(){
        return SUCCESS;
    }
}
