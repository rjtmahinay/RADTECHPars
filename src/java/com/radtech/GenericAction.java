package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.service.ServiceRegistry;

public class GenericAction extends ActionSupport implements SessionAware, ModelDriven{
    SessionMap sessionmap;
    Session session;
    Transaction tx;
    SessionFactory factory;
    Object model;
    
    @Override
    public void setSession(Map map) {
        sessionmap = (SessionMap)map;
    }
    
    public void init(){
        if (sessionmap.get("factory") == null) {
            AnnotationConfiguration configuration = new AnnotationConfiguration();
            configuration.configure();
            //insert annot class here
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Pet.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Medicine.class);
            configuration.addAnnotatedClass(Consultation.class);
            configuration.addAnnotatedClass(Appointment.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sessionmap.put("factory", sessionFactory);
        }
    }
    
    public Session getSession(){
        factory = ((SessionFactory)sessionmap.get("factory"));
        if(factory!= null){
            session = factory.openSession();
            factory = null;
            return session;
        }
        else{
            factory=null;
            return null;
        }
    }
    
    public Object getModel(){
        model = new Object();
        return model;
    }
    
    public void refresh(){
        //change table names
        sessionmap.put("view", (List) session.createQuery("from Information").list());
        sessionmap.put("archive", (List) session.createQuery("from Archive").list());
        sessionmap.put("appointments", session.createQuery("from Appointment where adate is null order by date").list());
    }
    
    public void refreshUser(User user){
        sessionmap.put("currentUser",user);
    }
}

//GenericModel
//	get customers
//	get pets
//	get appointments
//	get consultation
//	get medicines
//user 
//	add user
//	login
//	logout
//	change pass
//	add sec question
//	change sec pass
//customer
//	add customer
//	edit customer
//	delete customer
//pet
//	add pet
//	edit pet
//	delete pet?
//appointment
//	add appointment
//	edit appointment
//	delete appointment
//	complete appointment
//consultation
//medicine