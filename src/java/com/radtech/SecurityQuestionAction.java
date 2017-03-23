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
            security.setAnswer(security.getAnswer().trim().toLowerCase().hashCode()+"");
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
    
    public String resetPassword(){
        Session session = null;
        Transaction tx = null;
        System.out.println(security.getAnswer());
        try{
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            tx=session.getTransaction();
            tx.begin();
            SecurityQuestion checker = (SecurityQuestion)session.get(SecurityQuestion.class, security.getSec_number());
            
            if(checker.getAnswer().equals(security.getAnswer().trim().toLowerCase().hashCode()+"")){
                User user = (User)session.load(User.class, security.getUsername());
                //generating random password
                String password = ((Double)(Math.random()*10)).hashCode()+"";
                user.setPassword(password.hashCode()+"");
                session.merge(user);
                sessionmap.put("tempPassword", password);
                tx.commit();
                return SUCCESS;
            }
            else{
                addFieldError("answer", "Answer does not match");
                return INPUT;
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
        }
        finally{
            if(session!=null)
                session.close();
        }
        return INPUT;
    }
    public String execute(){
        return INPUT;
    }
}
