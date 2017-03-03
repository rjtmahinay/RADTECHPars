
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class UserAction extends ActionSupport implements ModelDriven<User>, SessionAware{
    private User user = new User();
    private SessionMap sessionmap;
    

    public void setUser(User u){
        user = u;
    }
    
    
    @Override
    public User getModel() {
        return user;
    }
    
    public void setSession(Map m){
        sessionmap = (SessionMap)m;
        if(sessionmap.get("factory")==null){
            AnnotationConfiguration configuration = new AnnotationConfiguration();
            configuration.configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Information.class);
            configuration.addAnnotatedClass(Diagnosis.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sessionmap.put("factory", sessionFactory);
        }
    }
    
    public String execute(){
        return SUCCESS;
    }
    
    public void validate(){
        if(!(getModel() instanceof User)){}
        if(sessionmap == null){}
    }
    
    public String login(){
        //Insert login logic
        
        return SUCCESS;
    }
    
    public String signup(){
        //Insert register logic
        Session session =null;
        user.setPassword("" + user.getPassword().hashCode());
        try {
            session= ((SessionFactory)sessionmap.get("factory")).openSession();
            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
        }
        finally{
            if(session!=null)session.close();
        }
        return SUCCESS;
    }
}
