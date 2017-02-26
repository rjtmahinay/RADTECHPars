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

    /**
     * @return the GENDER
     */
    public static List<String> getGENDER() {
        return GENDER;
    }

    /**
     * @param aGENDER the GENDER to set
     */
    public static void setGENDER(List<String> aGENDER) {
        GENDER = aGENDER;
    }

    /**
     * @return the BREEDS
     */
    public static List<String> getBREEDS() {
        return BREEDS;
    }

    /**
     * @param aBREEDS the BREEDS to set
     */
    public static void setBREEDS(List<String> aBREEDS) {
        BREEDS = aBREEDS;
    }

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
    private static List<String> GENDER;
    public static List SEARCHTYPES;
    private static List<String> BREEDS;

    public Credentials() {

        if (GENDER == null) {
            GENDER = new ArrayList<>();
            GENDER.add("MALE");
            GENDER.add("FEMALE");
        }

        if (SEARCHTYPES == null) {
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

        if (BREEDS == null) {
            BREEDS = new ArrayList<>();
        }
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
        c = new Configuration();
        factory = c.configure("hibernate.cfg.xml").buildSessionFactory();
        sessionmap.put("factory", factory);
        dh = new Dataholder();
        dh.setSession(sessionmap);
        dh.viewlist();

        //adding lists into application
        applicationmap.put("breeds", getBREEDS());
        applicationmap.put("gender", getGENDER());
        applicationmap.put("searchlist", SEARCHTYPES);
    }

    public String display(){
        return NONE;
    }
    @Override
    public String execute() {
        System.out.println("inside execute, running setsession...");
        setSession(sessionmap);
        return SUCCESS;
    }

    public String logout() {
        System.out.println("Logging out...");
        sessionmap.invalidate();
        return "success";
    }

    @Override
    public void validate() {

        String validUsername = c.getProperty("hibernate.connection.username");
        String validPassword = c.getProperty("hibernate.connection.password");

        if (sessionmap != null) {
            System.out.println("Inside validate");
        }

        loginValidation(user1, pass1, validUsername, validPassword);
        
//        signupValidation(user2, pass2, firstName, lastName);
      
    }

    private void loginValidation(String user, String pass, String validUsername, String validPassword) {
        if (user.trim().length() < 1 || pass.trim().length() < 1) {
            addActionError("Fields can't be blank");
        } else {
            if (!user.equals(validUsername.trim())) {
                addFieldError("user1", "Invalid username");
            }

            if (!pass.equals(validPassword.trim())) {
                addFieldError("pass1", "Invalid password");
            }

        }

    }
    
    private void signupValidation(String user, String pass, String fname, String lname){
        
         if (fname.trim().length() < 0 || lname.trim().length() < 0
            || user.trim().length() < 0 || pass.trim().length() < 0) {
            
             addActionError("Fields can't be blank");
        }

    }
}
