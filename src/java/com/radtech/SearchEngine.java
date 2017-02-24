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
    
    
    public SearchEngine(){}
    
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
            case "PATIENT_NAME" : return "patientName";
            default: break;
        }
        
        return "FAIL";
    }
    
    public void validate(){
        if(searchInput == null){
            addFieldError("pass1", "Field is left blank");
        }
    }
    @Action("searchDatabase")
    public String searchDatabase(){
        
        System.out.println("I am inside search");
        
        addFieldError("searchType", getSearchType());
        addFieldError("searchInput", getSearchInput());
        sessionmap.put("trial", getSearchType());
//        Session session = ((SessionFactory)sessionmap.get("factory")).openSession();
//        System.out.println(sessionmap == null);
//        Transaction tx = null;
//        List records;
//        try{
//            tx = session.beginTransaction();
//            Query query = session.createQuery("FROM Information where :field = :input");
//            query.setParameter("field", "patientName");
//            query.setParameter("input", getSearchInput());
//            records = query.list();
//            for(Object s: records){
//            System.out.println(s.toString());
//        }
//        
//        tx.commit();
//        sessionmap.put("search", records);
//            System.out.println(sessionmap.get("search")==null + " results");
//        }
//        catch (HibernateException e) {
//        if (tx!=null) tx.rollback();
//            e.printStackTrace(); 
//        }
//        finally {
//            session.close(); 
//        }
    
        return SUCCESS;
    }
}
