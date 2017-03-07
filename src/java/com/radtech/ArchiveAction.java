
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class ArchiveAction extends ActionSupport implements ModelDriven<Archive>, SessionAware{
    private Archive model = new Archive();
    private Object obj;
    private SessionMap sessionmap;
    private long id;
    
    public Archive getModel(){
        return model;
    }
    
    public void setSession(Map m){
        sessionmap = (SessionMap)m;
    }
    
    public String execute(){
        return SUCCESS;
    }
    
    public void validate(){
        if(sessionmap.get("currentRecord")==null);
    }
}
