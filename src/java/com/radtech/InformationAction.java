package com.radtech;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Sphere
 */
public class InformationAction extends ActionSupport implements ModelDriven<Information>, SessionAware {

    private Information information = new Information();
    SessionMap sessionmap;

    @Override
    public Information getModel() {
        return information;
    }

    public void setSession(Map m) {
        sessionmap = (SessionMap) m;
    }

    public void setInformation(Information in) {
        information = in;
    }

    public String add() {
        if(checkUser()){
            addFieldError("username", "Session timeout");
            return "error";
        }
        Session session = null;
        Transaction tx = null;
        if (information != null) {
            try {
                session = ((SessionFactory) sessionmap.get("factory")).openSession();
                tx = session.getTransaction();
                tx.begin();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                information.setDateOfBirth(sdf.parse(information.getDateinput()));
                setInformation(information);
                session.save(information);
                session.flush();
                information = null;
                tx.commit();
                sessionmap.put("view", (List) session.createQuery("from Information").list());
            } catch (HibernateException | ParseException e) {
                e.printStackTrace();
                tx.rollback();

            } finally {
                if (session != null) {
                    session.close();
                }
            }
        } else {
            System.out.println("MISSING INFORMATION");
            addFieldError("ownerName", "Returned with error");
            return INPUT;
        }
        return SUCCESS;
    }

    public String get() {
        if(checkUser()){
            addFieldError("username", "Session timeout");
            return "error";
        }
        Session session = null;
        try {
            session = ((SessionFactory) sessionmap.get("factory")).openSession();

            information = (Information) session.load(Information.class, information.getId());
            information.getDiagnosis().size();

            if (information == null) {
                addActionError("Record is not found");
                return INPUT;
            } else {
                setSchedule(information);
                sessionmap.put("currentRecord", information);
                sessionmap.put("currentDiag", information.getDiagnosis());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return SUCCESS;
    }

    public String toArchive() {
        if(checkUser()){
            addFieldError("username", "Session timeout");
            return "error";
        }
        Session session = null;
        try {
            session = ((SessionFactory) sessionmap.get("factory")).openSession();
            Transaction tx = session.getTransaction();
            tx.begin();
            Information info = (Information) session.load(Information.class, information.getId());
            info.getDiagnosis().size();
            Archive arc = new Archive();
            arc.setInformation(info);
            session.save(arc);
            session.delete(info);
            session.flush();
            tx.commit();
            sessionmap.put("view", session.createQuery("from Information").list());
            sessionmap.put("archive", session.createQuery("from Archive").list());

        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return SUCCESS;
    }

    public String updateRecord() {
        if(checkUser()){
            addFieldError("username", "Session timeout");
            return "error";
        }
        Session session = null;
        Transaction tx = null;

        try {
            session = ((SessionFactory) sessionmap.get("factory")).openSession();
            tx = session.getTransaction();
            tx.begin();

            Information info = (Information) session.get(Information.class, ((Information) sessionmap.get("currentRecord")).getControlNumber());
            information.setControlNumber(info.getControlNumber());

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            information.setDateOfBirth(sdf.parse(information.getDateinput()));

            if (info.getDateOfBirth().compareTo(information.getDateOfBirth()) != 0) {
                System.out.println(info.getDateOfBirth().compareTo(sdf.parse(information.getDateinput())) + " compareto");
                information.setDateOfBirth(sdf.parse(information.getDateinput()));
            }

            if (information == null) {
                System.out.println("Says info is null");
                tx.rollback();
                information = null;
                session.close();
                return INPUT;
            } else {
                session.merge(information);
                information = null;
            }
            session.flush();
            tx.commit();
            sessionmap.put("currentRecord", info);
            sessionmap.put("view", session.createQuery("from Information").list());

        } catch (HibernateException | ParseException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return SUCCESS;
    }

    public String execute() {
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
    public boolean checkUser(){
        return sessionmap.get("currentUser")==null;
    }
}
