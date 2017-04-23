package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class GenericAction extends ActionSupport implements SessionAware, ModelDriven{
    SessionMap sessionmap;
    Session session;
    Transaction tx;
    SessionFactory factory;
    Object model;
    Deencrypt de = new Deencrypt();
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
        sessionmap.put("customers", session.createQuery("from Customer").list());
        sessionmap.put("search", session.createQuery("from Customer").list());
        //if(((User)sessionmap.get("currentUser")).getUserType().equals("assistant")){
            refreshAppointments();
        //}
            
        //else{
        //    refreshUnfinishedConsultations();
        //}
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
        List<Appointment> apps = session.createCriteria(Appointment.class)
                .addOrder(Order.asc("appointmentDate"))
                .list();
        System.out.println("The size of appointments is " + apps.size());
        for(Appointment app: apps){
            System.out.println("Making appointment for " + app.toString());
            //app.setCustomer(app.getCustomer());
            for(Object o: app.getConsultations()){
                Consultation c = (Consultation)o;
                c.setPet(c.getPet());
                c.setAppointment(app);
                System.out.println("Consultation made for " + c.getAppointment().toString());
            }
        }
        sessionmap.put("appointments", apps);
    }
    
    public boolean checkAppointment(Consultation consult){
        Appointment app = consult.getAppointment();
        hiberialize(app.getConsultations());
        for(Object o: app.getConsultations()){
            Consultation c = (Consultation)o;
            if(c.getConsultationDate()!= null) return false;
        }
        app.setAppointmentDate(new java.util.Date());
        return true;
    }
    
    public void refreshAppointmentConsultations(Appointment app){
        Hibernate.initialize(app.getConsultations());
        ArrayList<Consultation> consultations = new ArrayList<Consultation>();
        for(Object o : app.getConsultations()){
            Consultation c = (Consultation)o;
            if(c.getWeight()<=0) consultations.add(c);
            System.out.println("---->" + c.toString());
        }
        sessionmap.put("currentConsultations", consultations);
    }
    
    public void refreshUnfinishedConsultations(){
        //include customer and pet updates
        List<Consultation>  consultations = session.createCriteria(Consultation.class)
                                .add(Restrictions.isNull("consultationDate"))
                                .list();
        HashSet hashed = new HashSet();
        for(Object o: consultations){
            Consultation c = (Consultation)o;
            hiberialize(c.getAppointment());
            System.out.println("Consultation is " + c.toString());
            Appointment app = c.getAppointment();
            hiberialize(app.getConsultations());
            app.setConsultations(app.getConsultations());
            System.out.println("App is " + app.toString());
            //check if weight <0, dont include
            if(c.getWeight()<=0){
                app.getConsultations().remove(c);
            }
            else{
                hiberialize(c.getPet());
                c.setPet(c.getPet());
                hiberialize(app.getCustomer());
                app.setCustomer(app.getCustomer());
                hashed.add(c);
            }
        }
        ArrayList result = new ArrayList();
        result.addAll(hashed);
        System.out.println("Size of hashed is " + hashed.size() + " and result is " + result.size());
        sessionmap.put("appointments", result);
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
    
    public String putMap(String s, Object o){
        sessionmap.put(s, o);
        
        return s + " is placed on the map";   
    }
    public Deencrypt getD(){
        return de;
    }
    
    public void hiberialize(Object o){
        Hibernate.initialize(o);
    }
}

//GenericModel
//	get customers
//	get pets
//	get appointments
//	get consultation
//	get medicines