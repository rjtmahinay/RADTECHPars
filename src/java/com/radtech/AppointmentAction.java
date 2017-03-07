
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class AppointmentAction extends ActionSupport implements ModelDriven<Appointment>, SessionAware{
    Appointment app = new Appointment();
    SessionMap sessionmap;
    
    @Override
    public Appointment getModel(){
        return app;
    }
    @Override
    public String execute(){
        return SUCCESS;
    }
    @Override
    public void setSession(Map m){
        sessionmap = (SessionMap)m;
    }
    
    public String addAppointment(){
        Session session = null;
        Transaction tx = null;
        try{
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy HH:mm:ss");
            Date date = dateFormat.parse(app.getDateinput());
            app.setDate(date);
            app.setControlNumber(((Information)sessionmap.get("currentRecord")).getControlNumber());
            System.out.println(app.toString());
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            tx = session.getTransaction();
            tx.begin();
            session.save(app);
            sessionmap.put("appointments", session.createQuery("from Appointment").list());
            tx.commit();
        }
        catch(HibernateException | ParseException e){
            if(tx != null)tx.rollback();
        }
        finally{
            if(session!= null) session.close();
        }
        return SUCCESS;
    }
}
