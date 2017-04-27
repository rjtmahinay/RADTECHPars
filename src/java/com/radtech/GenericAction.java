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
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import static org.apache.struts2.ServletActionContext.getServletContext;

public class GenericAction extends ActionSupport implements SessionAware, ModelDriven{
    SessionMap sessionmap;
    Session session;
    Transaction tx;
    SessionFactory factory;
    Object model;
    Deencrypt de = new Deencrypt();
    public static GsonBuilder gb = new GsonBuilder().setPrettyPrinting();
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
            configuration.addAnnotatedClass(Archive.class);
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
        sessionmap.put("search", session.createQuery("from Customer").list());
        refreshAppointments();
        System.out.println("Refresh done");
    }
    
    public void refreshUser(User user){
        sessionmap.put("currentUser",user);
    }
    
    public void refreshCustomers(){
        sessionmap.put("customers", (List) session.createQuery("from Customers").list());
    }
    
    public void refreshPets(){
        putMap("pets", session.createCriteria(Pet.class).list());
    }
    
    public void refreshAppointments(){
        if(session==null)session = getSession();
        List<Appointment> apps = session.createCriteria(Appointment.class, "appointment")
                .addOrder(Order.asc("appointmentDate"))
                .createAlias("appointment.consultations", "consultation")
                .add(Restrictions.eq("status", "pending"))
                .add(Restrictions.eq("consultation.status", "pending"))
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list();
        sessionmap.put("appointments", apps);
    }
    
    public boolean checkAppointment(Consultation consult){
        Appointment app = consult.getAppointment();
        hiberialize(app.getConsultations());
        for(Object o: app.getConsultations()){
            Consultation c = (Consultation)o;
            if(c.getStatus().equals("pending")){
                return false;
            }
        }
        app.setAppointmentDate(new java.util.Date());
        return true;
    }
    public boolean checkAppointment(Appointment appoint){
        hiberialize(appoint.getConsultations());
        System.out.println("->Checking for " + appoint.toString());
        for(Object o: appoint.getConsultations()){
            Consultation c = (Consultation)o;
            if(c.getStatus().equals("pending")) {
                System.out.println("--->This appointment is pending, returning false");
                return false;
            }
        }
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
    public String makeJson(String input) throws IOException{
        JsonChart chart = new JsonChart("Appointments", "Animal Station", "Month", "Number of Visitors", "pax", "fint");
        JsonObject object = new JsonObject(chart, input);
        Gson gson = new Gson();
        try{
            File file = new File(getServletContext().getRealPath("/") + "/stat.json");
            System.err.println("Does file exist? " + file.exists());
            System.out.println("is file edittable? " + file.canWrite());
            Writer writer = new FileWriter(file);
            gson.toJson(object, writer);
            writer.close();
        } catch (IOException e) {
            //do something
        }
        return gson.toJson(object);
    }
    public String getPath(){
        return getServletContext().getRealPath("/");
    }
}

//GenericModel
//	get customers
//	get pets
//	get appointments
//	get consultation
//	get medicines
