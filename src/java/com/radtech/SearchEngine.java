package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class SearchEngine extends ActionSupport implements SessionAware{
    private SessionMap sessionmap;
    private String searchInput;
    private String searchType;
    
    
    public SearchEngine(){
        setSession(sessionmap);
    }
    
    @Override
    public void setSession(Map m){
        sessionmap = (SessionMap)m;
    }
    
    public void setSearchInput(String s){
        searchInput = s;
    }
    
    public String getSearchInput(){
        return searchInput;
    }
    
    public void setSearchType(String s){
        searchType = s;
        
        //change search type into proper DB search type
    }
    
    public String getSearchType(){
        switch(searchType){
            case "CONTROL_NUMBER" : return "controlNumber";
            case "OWNER_NAME" : return "ownerName";
            case "ADDRESS" : return "address";
            case "CONTACT_NUMBER" : return "contactNumber";
            case "PATIENT_NAME" : return "patientName";
            case "BREED" : return "breed";
            case "SEX" : return "sex";
            case "AGE" : return "age";
            case "COLOR" : return "color";
            case "WEIGHT" : return "weight";
            default: break;
        }
        
        return "FAIL";
    }
    
    public void validate(){
        if(sessionmap == null){
            addFieldError("searchInput", "Field is left blank");
        }
    }
    @Action("searchDatabase")
    public String searchDatabase(){
        
        System.out.println("I am inside search");
        
        if(getSearchType().equals("FAIL")) {
            addFieldError("searchInput", "Something went wrong...");
            return "input";
        }
        Session session = ((SessionFactory)sessionmap.get("factory")).openSession();
        System.out.println(sessionmap == null);
        Transaction tx = null;
        List records;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Information where " + getSearchType() + " = :input");
            //query.setParameter("field", getSearchType());
            query.setParameter("input", getSearchInput());
            System.out.println(getSearchInput() + " " + getSearchType());
            records = query.list();
            for(Object s: records){
            System.out.println(s.toString());
        }
        
        tx.commit();
        sessionmap.put("search", records);
            System.out.println(sessionmap.get("search")==null + " results");
        }
        catch (HibernateException e) {
        if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }
        finally {
            session.close(); 
        }
    
        return SUCCESS;
    }
}
