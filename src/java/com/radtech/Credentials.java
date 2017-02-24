package com.radtech;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Credentials extends ActionSupport implements SessionAware {

    private static SessionFactory factory;
    private String user1;
    private String pass1;
    private String user2;
    private String pass2;
    private String firstName;
    private String lastName;
    private SessionMap sessionmap;
    private Map applicationmap;
    private Configuration c;
    private Dataholder dh;
    public static List GENDER = null;
    public static List SEARCHTYPES = null;
    public static List BREEDS = null;

    public Credentials() {
        if(GENDER==null){
            GENDER = new ArrayList();
            GENDER.add("MALE");
            GENDER.add("FEMALE");
        }
        
        if(SEARCHTYPES == null){
            SEARCHTYPES = new ArrayList();
            SEARCHTYPES.add("CONTROL_NUMBER");
            SEARCHTYPES.add("OWNER_NAME");
            SEARCHTYPES.add("ADDRESS");
            SEARCHTYPES.add("CONTACT_NUMBER");
            SEARCHTYPES.add("PATIENT_NAME");
            SEARCHTYPES.add("BREED");
            SEARCHTYPES.add("SEX");
            SEARCHTYPES.add("AGE");
            SEARCHTYPES.add("COLOR");
            SEARCHTYPES.add("WEIGHT");
        }
        
        if(BREEDS == null){
            BREEDS = new ArrayList();
        }
    }

    @Override
    public String execute() {
        System.out.println("inside execute, running setsession...");
        setSession(sessionmap);
        return SUCCESS;
    }

    /**
     * @return the user1
     */
    public String getUser1() {
        return user1;
    }

    /**
     * @param user1 the user1 to set
     */
    public void setUser1(String user1) {
        this.user1 = user1;
    }

    /**
     * @return the pass1
     */
    public String getPass1() {
        return pass1;
    }

    /**
     * @param pass1 the pass1 to set
     */
    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    /**
     * @return the user2
     */
    public String getUser2() {
        return user2;
    }

    /**
     * @param user2 the user2 to set
     */
    public void setUser2(String user2) {
        this.user2 = user2;
    }

    /**
     * @return the pass2
     */
    public String getPass2() {
        return pass2;
    }

    /**
     * @param pass2 the pass2 to set
     */
    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setSession(Map map) {
        sessionmap = (SessionMap) map;
        applicationmap = (Map) ActionContext.getContext().get("application");
        //sessionmap = (SessionMap) ActionContext.getContext().getSession();
        System.out.println(sessionmap == null);
        sessionmap.put("login", "true");
        System.out.println("login is true, making session...");
//        factory = c.configure("hibernate.cfg.xml").buildSessionFactory();
        c = new Configuration().configure("hibernate.cfg.xml");
        factory =  c.buildSessionFactory();
        sessionmap.put("factory", factory);
        dh = new Dataholder();
        dh.setSession(sessionmap);
        dh.viewlist();
        
        //adding lists into application
        applicationmap.put("breeds", BREEDS);
        applicationmap.put("gender", GENDER);
        applicationmap.put("searchlist", SEARCHTYPES);
    }

    public String logout() {
        System.out.println("Logging out...");
        sessionmap.invalidate();
        return "success";
    }

   
    @Override
    public void validate() {
        if (!getUser1().equals("radtech".trim())) {
            addFieldError("user1", "Invalid username");
        }

        if (!getPass1().equals("@ics123".trim())) {
            addFieldError("pass1", "Invalid Password");
        }
        if (sessionmap != null) {
            System.out.println("Inside validate");
        }
    }
    
    
   

}
