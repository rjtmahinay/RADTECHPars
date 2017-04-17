package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
    
    public void initialize(){
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
        System.out.println(sessionmap == null);
        session = getSession();
        sessionmap.put("archive", session.createQuery("from Archive").list());
        sessionmap.put("appointments", session.createQuery("from Appointment order by appointmentDate").list());
        sessionmap.put("customers", session.createQuery("from Customer").list());
        sessionmap.put("search", session.createQuery("from Customer").list());
        System.out.println("Refresh done");
    }
    
    public void refreshUser(User user){
        sessionmap.put("currentUser",user);
    }
    
    public void refreshCustomers(){
        sessionmap.put("customers", (List) session.createQuery("from Customers").list());
    }
    
    public void refreshPets(){
        sessionmap.put("pets", (List) session.createQuery("from Pets").list());
    }
    
    public void refreshAppointments(){
        sessionmap.put("appointments", (List) session.createQuery("from Appointments order by appointmentDate").list());
    }
    
//    public void refreshAppointmentsPending(){
//        sessionmap.put("appointments", session.createQuery("from Appointment where adate is null order by date").list());
//    }
    
    public void refreshConsultation(){
        sessionmap.put("consultations", (List) session.createQuery("from Consultations").list());
    }
    
    public void refreshMedicines(){
        sessionmap.put("medicines", (List) session.createQuery("from Medicines").list());
    }
    
    public Date toDate(String input){
        Date result;
        try{
            result = new SimpleDateFormat("MM/dd/yyyy").parse(input);
            return result;
        }
        catch(ParseException e){
            return null;
        }
    }
    
    public Pet getCurrentPet(){
        return (Pet)sessionmap.get("currentPet");
    }
    
    public Customer getCurrentCustomer(){
        return (Customer)sessionmap.get("currentCustomer");
    }
    
    
}

//GenericModel
//	get customers
//	get pets
//	get appointments
//	get consultation
//	get medicines