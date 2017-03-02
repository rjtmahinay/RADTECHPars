
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Lucas
 */
public class ModelManager extends ActionSupport implements ModelDriven, SessionAware{
    Model current;
    SessionMap sessionmap;
    public Model getCurrent() {
        return current;
    }

    public void setCurrent(Model current) {
        this.current = current;
    }
    
    public Object getModel(){
        return new Model();
    }
    
    public String execute(){
        return SUCCESS;
    }
    
    public String login(){
        Login login = (Login)getModel();
        if(login.getUsername()=="radtech" & login.getPassword()=="@ics123") return SUCCESS;
        //verify input
        else{
            addActionError("Username or Password does not match");
            return INPUT;
        }
    }
    public String searchDatabase(){
        //search page
        return SUCCESS;
    }
    
    public String getRecord(){
        //return unique record
        return SUCCESS;
    }
    
    
    public void setSession(SessionMap map){
        sessionmap = map;
    }

    @Override
    public void setSession(Map map) {
        sessionmap = (SessionMap)map;
    }
    
    
}
