
package com.radtech;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;  
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  
import org.hibernate.SessionFactory;
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
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
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
    
    @Override
    public void validate(){
        String validUsername = new Configuration().getProperty("hibernate.connection.username");
        String validPassword = new Configuration().getProperty("hibernate.connection.password");
        
        if(sessionmap != null){
            System.out.println("Inside validate");
        }
        
        if(!getUsername().equals(validUsername)){
            addFieldError("username","Invalid username");
        }
//        
//         if(!password.equals(validPassword)){
//            addFieldError("password","Invalid Password");
//        }
    }
    
    
}
