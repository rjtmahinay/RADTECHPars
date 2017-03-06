package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


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
            case "Control Number" : return "controlNumber";
            case "Owner Name" : return "ownerName";
            case "Address" : return "address";
            case "Contact Number" : return "contactNumber";
            case "Patient Name" : return "patientName";
            case "Breed" : return "breed";
            case "Sex" : return "sex";
            case "Age" : return "age";
            case "Color" : return "color";
            case "Weight" : return "weight";
            default: break;
        }
        
        return "FAIL";
    }
    
    public void validate(){
        if(getSearchType().equals("FAIL")){
            sessionmap.put("search", (List)sessionmap.get("view"));
            addFieldError("searchInput", "Field is left blank");
        }
        if(getSearchInput().trim().equals("")){
            System.out.println("isnull " + sessionmap.get("view") == null);
            sessionmap.put("search", sessionmap.get("view"));
        }
    }
    @Action("searchDatabase")
    public String searchDatabase(){
        if(getSearchType().equals("FAIL")) {
            addFieldError("searchInput", "Something went wrong...");
            return "input";
        }
        Session session = ((SessionFactory)sessionmap.get("factory")).openSession();
        System.out.println(sessionmap == null);
        Transaction tx = null;
        List records = null;
        try{
            tx = session.beginTransaction();
            Query query = null;
            try{
                switch(getSearchType()){
                    case "contactNumber" : query = session.createQuery("from Information where contactNumber = " + Integer.parseInt(getSearchInput())); break;
                    case "age": query = session.createQuery("from Information where age = " + Integer.parseInt(getSearchInput())); break;
                    case "controlNumber": query = session.createQuery("from Information where controlNumber = " + Long.parseLong(getSearchInput())); break;
                    case "weight": query = session.createQuery("from Information where weight = " + Double.parseDouble(getSearchInput())); break;
                    case "sex": query = session.createQuery("from Information where sex = " + getSearchInput()); break;
                    default: Criteria crit = session.createCriteria(Information.class);
                            crit.add(Restrictions.ilike(getSearchType(), '%' + getSearchInput() +'%'));
                            records = crit.list();
                }
            }
            catch(NumberFormatException e) {
                throw new NumberFormatException("Invalid input on field");
            }
            if(records ==null) records = query.list();
            tx.commit();
            sessionmap.put("search", records);
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
