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


public class SecurityQuestionAction extends ActionSupport implements ModelDriven<SecurityQuestion>, SessionAware{
    private SecurityQuestion security = new SecurityQuestion();
    private SessionMap sessionmap;
    
    public SecurityQuestion getModel(){
        return security;
    }
    public void setSession(Map m){
        sessionmap = (SessionMap)m;
    }

    public SecurityQuestion getSecurity() {
        return security;
    }

    public void setSecurity(SecurityQuestion security) {
        this.security = security;
    }

    public SessionMap getSessionmap() {
        return sessionmap;
    }

    public void setSessionmap(SessionMap sessionmap) {
        this.sessionmap = sessionmap;
    }
    
    public String addQuestion(){
        System.out.println("Current item is " + security.toString());
        System.out.println(security.getUsername());
        Session session = null;
        Transaction tx = null;
        try{
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            tx=session.getTransaction();
            tx.begin();
            User user = (User)session.load(User.class, security.getUsername());
            if(!user.getPassword().equals(security.getPassword().toLowerCase().trim().hashCode()+"")){
                addFieldError("password", "Password is Incorrect");
                return INPUT;
            }
            security.setUser(user);
            security.setAnswer(security.getAnswer().toLowerCase().trim().hashCode()+"");
            session.save(security);
            security=null;
            tx.commit();
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
        }
        finally{
            
        }
        return INPUT;
    }
    public String execute(){
        return INPUT;
    }
}
