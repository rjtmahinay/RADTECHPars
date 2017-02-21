
package com.radtech;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Iterator;
import java.util.List;
import java.util.Map;  
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Credentials extends ActionSupport implements SessionAware{

    private static SessionFactory factory;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    SessionMap sessionmap;  
    private Dataholder dh;
    
    public Credentials(){}
    
    @Override
    public String execute(){
        System.out.println("inside execute, running setsession...");
        setSession(sessionmap);
       return SUCCESS; 
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
    
    public void setSession(Map map) {  
        sessionmap=(SessionMap)map;  
        //sessionmap = (SessionMap) ActionContext.getContext().getSession();
        System.out.println(sessionmap == null);
        sessionmap.put("login","true");
        System.out.println("login is true, making session...");
        factory = new Configuration().configure().buildSessionFactory();
        sessionmap.put("factory", factory);
        dh = new Dataholder();
        dh.setSession(sessionmap);
        dh.viewlist();

    }  

    public String logout(){  
        System.out.println("Logging out...");
        sessionmap.invalidate();  
        return "success";  
    }  
    
    public void validate(){
        if(sessionmap != null){
            System.out.println("Inside validate");
        }
    }
    
    
}
