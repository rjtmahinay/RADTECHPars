package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.service.ServiceRegistry;

public class UserAction extends ActionSupport implements ModelDriven<User>, SessionAware {

    private User user = new User();
    private SessionMap sessionmap;

    public void setUser(User u) {
        user = u;
    }

    public User getUser() {
        return user;
    }

    @Override
    public User getModel() {
        return user;
    }

    public void setSession(Map m) {
        sessionmap = (SessionMap) m;
        if (sessionmap.get("factory") == null) {
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

    @Override
    public String execute() {
        return SUCCESS;
    }

    @Override
    public void validate() {
    }

    public String login() {
        //Insert login logic
        Session session = null;
        user.setPassword("" + user.getPassword().hashCode());
        try {
            System.out.println("Inside login try");
            session = ((SessionFactory) sessionmap.get("factory")).openSession();
            User db = (User) session.get(User.class, user.getUsername());
            if (db == null) {
                addFieldError("password", "Username/Password doesn't match");
                return INPUT;
            } else {
                if (db.getUsername().equalsIgnoreCase(user.getUsername())) {
                    if (db.getPassword().equals(user.getPassword())) {
                        sessionmap.put("currentUser", db);
                        sessionmap.put("view", (List) session.createQuery("from Information").list());
                        sessionmap.put("archive", (List) session.createQuery("from Archive").list());
                        sessionmap.put("appointments", session.createQuery("from Appointment where adate is null order by date").list());
                        if (sessionmap.get("tempPassword") != null) {
                            sessionmap.remove("tempPassword");
                            return "temp";
                        } else {
                            return SUCCESS;
                        }
                    } else {
                        addFieldError("password", "Username/Password doesn't match");
                        return INPUT;
                    }
                } else {
                    addFieldError("username", "Username/Password doesn't match");
                    return INPUT;
                }
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

    public String signup() {
        //Insert register logic
        Session session = null;
        Transaction tx = null;
        try{
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            tx = session.getTransaction();
            User tempUser = (User)session.get(User.class, user.getUsername().trim());
            if(tempUser == null){
                //no username found, can use it
                if(user.getPassword().trim().equals(user.getPassword2().trim())){
                    //check if passwords match
                    user.setPassword("" + user.getPassword2().trim().hashCode());
                    tx.begin();
                    session.save(user);
                    tx.commit();
                }
                else{
                    //Passwords does not match
                    addFieldError("password", "Password does not match");
                    return INPUT;
                }
            }
            else{
                //username already used
                addFieldError("username", "Username is already used");
                return INPUT;
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
            if(tx!= null) tx.rollback();
        }
        finally{
            if(session!=null)session.close();
        }
        return INPUT;
    }

    public String changePassword() {
        Session session = null;
        Transaction tx = null;
        try {
            session = ((SessionFactory) sessionmap.get("factory")).openSession();
            tx = session.getTransaction();
            User currentUser = (User)session.load(User.class, user.getUsername());
            if(currentUser != null){
                if(currentUser.getPassword().equals("" + user.getPassword().trim().hashCode())){
                    if(user.getPassword2().equals(user.getPassword3())){
                        currentUser.setPassword("" + user.getPassword2().trim().hashCode());
                        tx.begin();
                        session.merge(currentUser);
                        tx.commit();
                        sessionmap.put("currentUser", currentUser);
                        user = null;
                        return SUCCESS;
                    }
                    else{
                        //passwords does not match
                        addFieldError("password2", "Passwords does not match.");
                        return INPUT;
                    }
                }
                else{
                    //password is incorrect
                    addFieldError("password", "Password is incorrect.");
                    return INPUT;
                }
            }
            else{
                addFieldError("password", "Critical Error: Code 5");
                return INPUT;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return SUCCESS;
    }

    public String forgotPassword() {
        if (!(user.getUsername().trim().equals("")) & user != null) {
            System.out.println(user.getUsername());
            Session session = null;
            Transaction tx = null;
            try {
                session = ((SessionFactory) sessionmap.get("factory")).openSession();
                System.out.println(user.getUsername());
                user = (User) session.get(User.class, user.getUsername());
                System.out.println("Users' " + (user == null));
                if (user == null) {
                    addFieldError("username", "Username not found");
                    return INPUT;
                } else {
                    user = (User) session.load(User.class, user.getUsername());
                    List questions = new ArrayList();
                    for (SecurityQuestion sq : user.getSQuestions()) {
                        System.out.println(sq.getQuestion());
                        questions.add(sq.getQuestion());
                    }
                    sessionmap.put("tempUser", user);
                    return SUCCESS;
                }
            } catch (HibernateException e) {
                tx.rollback();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        addFieldError("username", "Something happened midway...");
        return INPUT;
    }

    public String logout() {

        sessionmap.invalidate();

        //renew servlet session
        sessionmap.put("renewServletSession", null);
        sessionmap.remove("renewServletSession");

        //populate the struts session
        sessionmap.entrySet();
        return SUCCESS;
    }

    public String fetchuser() {
        Session session = null;
        try {
            session = ((SessionFactory) sessionmap.get("factory")).openSession();
            User userx = (User) sessionmap.get("currentUser");
            user = (User) session.load(User.class, ((User) sessionmap.get("currentUser")).getUsername());
            if (user == null) {
                addFieldError("username", "Fatal error : Code 1");
                return INPUT;
            } else {
                for (Object o : user.getSQuestions()) {
                    System.out.println(((SecurityQuestion) o).toString());
                }
                return SUCCESS;
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return INPUT;
    }

}
