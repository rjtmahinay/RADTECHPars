
package com.radtech;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;  
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  

public class Credentials extends ActionSupport{

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    SessionMap sessionmap;  
    
    @Override
    public String execute(){
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
        //sessionmap=(SessionMap)map;  
        sessionmap = (SessionMap) ActionContext.getContext().getSession();
        System.out.println(sessionmap == null);
        sessionmap.put("login","true");  
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
