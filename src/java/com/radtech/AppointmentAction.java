
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;


public class AppointmentAction extends ActionSupport implements ModelDriven<Appointment>, SessionAware{
    Appointment app = new Appointment();
    SessionMap sessionmap;
    JSONObject json = new JSONObject();
    int[] scores = new int[12];
    
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
        if(app!=null){
            try{
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                String str = app.getDateinput();
                Date date = null;
                date = formatter.parse(str.trim());
                app.setDate(date);
                session = ((SessionFactory)sessionmap.get("factory")).openSession();
                tx = session.getTransaction();
                tx.begin();
                
                Information info = (Information)session.load(Information.class, ((Information)sessionmap.get("currentRecord")).getControlNumber());
                app.setInformation(info);
                session.save(app);
                setSchedule(info);
                session.merge(info);
                sessionmap.put("currentRecord", info);
                info=null;
                app=null;
                sessionmap.put("appointments", session.createQuery("from Appointment where adate is null order by date").list());
                tx.commit();
            }
            catch(HibernateException e){
                if(tx != null)tx.rollback();
            }
            catch(ParseException e){
                System.out.println("Cannot parse");
            }
            finally{
                if(session!= null) session.close();
            }
        }
        return SUCCESS;
    }
    
    public String accomplishAppointment(){
        Session session = null;
        Transaction tx = null;
        if(app!= null){
            try{
                session = ((SessionFactory)sessionmap.get("factory")).openSession();
                tx = session.getTransaction();
                tx.begin();
                app = (Appointment)session.load(Appointment.class, app.getAppointmentNumber());
                app.setAdate(new java.util.Date());
                session.merge(app);
                sessionmap.put("appointments", (List)session.createQuery("from Appointment where adate is null order by date").list());
                app=null;
                tx.commit();
            }
            catch(HibernateException e){
                tx.rollback();
            }
            finally{
                if(session!=null)session.close();
            }        
        }
        return SUCCESS;
    }
    
    public void setSchedule(Information source){
        if(source.getAppointments()!= null){
            source.setNextAppointment(null);
            forwhile:
            for(Appointment a: source.getAppointments()){
                if(a.getAdate()== null){
                    source.setNextAppointment(a.getDate());
                    break forwhile;
                }
            }
        }
    }
    
    public String statistics(){
        tallyMonths();
        
        return SUCCESS;
    }
    public void tallyMonths(){
        //where MONTH(so.date) = MONTH(:date);
        System.out.println("Inside tally month");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Session session = null;
        try{
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            System.out.println("Session opened!");
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            int year = now.getYear();
            for(int i=0; i<12; i++){
                //scores[i];
//                Criteria criteria = session.createCriteria(Appointment.class);
//                criteria.add(Restrictions.ge("date", sdf.parse(i+1+""))); 
//                if(i==11){
//                    criteria.add(Restrictions.lt("date", sdf.parse(i+1+"")));
//                }
//                else criteria.add(Restrictions.lt("date", sdf.parse(i+2+"")));
//                List list = criteria.list();
                
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTime(sdf.parse(i+1+"/01/" + year));
                Date inTime = cal.getTime();
                System.out.println(inTime);
                cal.add(Calendar.MONTH, 1);
                cal.add(Calendar.DATE, -1);
                Date outTime = cal.getTime();
                System.out.println(outTime);
                Criteria crit = session.createCriteria(Appointment.class);
                crit.add(Restrictions.between("date", inTime, outTime));
                List list = crit.list();
                if(list == null) scores[i] = 0;
                else scores[i] = list.size();
                
                System.out.println(i + " scores is " + scores[i]);
            }
            sessionmap.put("scores", scores);
        }
        catch(HibernateException | ParseException e){
            System.out.println("Something happened midway...");
            e.printStackTrace();
        }
        finally{
            if(session!=null)session.close();
        }
        
    }
}
