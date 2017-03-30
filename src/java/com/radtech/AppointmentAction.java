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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class AppointmentAction extends ActionSupport implements ModelDriven<Appointment>, SessionAware {

    Appointment app = new Appointment();
    SessionMap sessionmap;
    int[] scores = new int[12];

    @Override
    public Appointment getModel() {
        return app;
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    @Override
    public void setSession(Map m) {
        sessionmap = (SessionMap) m;
    }

    public String addAppointment() {
        if(checkUser()){
            addFieldError("username", "Session timeout");
            return "error";
        }
        Session session = null;
        Transaction tx = null;
        if (app != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                String str = app.getDateinput();
                Date date = null;
                date = formatter.parse(str.trim());
                app.setDate(date);
                session = ((SessionFactory) sessionmap.get("factory")).openSession();
                tx = session.getTransaction();
                tx.begin();
                Information info = (Information) session.load(Information.class, ((Information) sessionmap.get("currentRecord")).getControlNumber());
                app.setInformation(info);
                if (info.getNextAppointment() == null) {
                    info.setNextAppointment(date);
                } else {
                    if (date.compareTo(info.getNextAppointment()) < 0) {
                        info.setNextAppointment(date);
                    }
                }
                session.save(app);
                //setSchedule(info);
                session.merge(info);
                sessionmap.put("currentRecord", info);
                info = null;
                app = null;
                tx.commit();
                sessionmap.put("appointments", session.createQuery("from Appointment where adate is null order by date").list());
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
            } catch (ParseException e) {
                System.out.println("Cannot parse");
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return SUCCESS;
    }

    public String accomplishAppointment() {
        if(checkUser()){
            addFieldError("username", "Session timeout");
            return "error";
        }
        Session session = null;
        Transaction tx = null;
        if (app != null) {
            try {
                session = ((SessionFactory) sessionmap.get("factory")).openSession();
                tx = session.getTransaction();
                tx.begin();
                app = (Appointment) session.load(Appointment.class, Long.parseLong(app.getAppinput()));
                if (app != null) {
                    app.setAdate(new java.util.Date());
                    session.merge(app);
                    tx.commit();
                    app = null;
                    sessionmap.put("appointments", (List) session.createQuery("from Appointment where adate is null order by date").list());
                }
                else{
                    tx.rollback();
                }
                
            } catch (HibernateException e) {
                e.printStackTrace();
                tx.rollback();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return SUCCESS;
    }

    public void setSchedule(Information source) {
        if (source.getAppointments() != null) {
            source.setNextAppointment(null);
            forwhile:
            for (Appointment a : source.getAppointments()) {
                if (a.getAdate() == null) {
                    source.setNextAppointment(a.getDate());
                    break forwhile;
                }
            }
        }
    }

    public String statistics() {
        if(checkUser()){
            addFieldError("username", "Session timeout");
            return "error";
        }
        tallyMonths();

        return SUCCESS;
    }

    public void tallyMonths() {
        //where MONTH(so.date) = MONTH(:date);
        System.out.println("Inside tally month");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Session session = null;
        try {
            session = ((SessionFactory) sessionmap.get("factory")).openSession();
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            int year = now.getYear();
            for (int i = 0; i < 12; i++) {
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTime(sdf.parse(i + 1 + "/01/" + year));
                Date inTime = cal.getTime();
                System.out.println(inTime);
                cal.add(Calendar.MONTH, 1);
                cal.add(Calendar.DATE, -1);
                Date outTime = cal.getTime();
                System.out.println(outTime);
                Criteria crit = session.createCriteria(Appointment.class);
                crit.setProjection(Projections.property("date"));
                crit.add(Restrictions.between("date", inTime, outTime));
                List list = crit.list();
                if (list == null) {
                    scores[i] = 0;
                } else {
                    scores[i] = list.size();
                    System.out.println("Size of " + (i+1) + " is " + list.size());
                }
            }
            sessionmap.put("scores", scores);
        } catch (HibernateException | ParseException e) {
            System.out.println("Something happened midway...");
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public String cancelAppointment(){
        if(checkUser()){
            addFieldError("username", "Session timeout");
            return "error";
        }
        Session session = null;
        Transaction tx = null;

        try{
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            tx = session.getTransaction();
            tx.begin();
            Appointment appointment = (Appointment)session.load(Appointment.class, Long.parseLong(app.getAppinput()));
            if(appointment!=null){
                Information info = appointment.getInformation();
                info.getAppointments().remove(appointment);
                session.merge(info);
                session.delete(appointment);
                session.flush();
                tx.commit();
                sessionmap.put("appointments", session.createQuery("from Appointment where adate is null order by date").list());
            }
            else{
                tx.rollback();
                return INPUT;
            }

        }
        catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            if(session!=null)session.close();
        }

        return INPUT;
    }
    public boolean checkUser(){
        return sessionmap.get("currentUser")==null;
    }
}
