package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class SearchEngine extends ActionSupport implements SessionAware {

    private SessionMap sessionmap;
    private String searchInput;
    private String searchType;

    public SearchEngine() {
        setSession(sessionmap);
    }

    @Override
    public void setSession(Map m) {
        sessionmap = (SessionMap) m;
    }

    public void setSearchInput(String s) {
        searchInput = s.trim();
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchType(String s) {
        searchType = s;

        //change search type into proper DB search type
    }

    public String getSearchType() {
        switch (searchType) {
            case "Control Number":
                return "controlNumber";
            case "Owner Name":
                return "ownerName";
            case "Address":
                return "address";
            case "Contact Number":
                return "contactNumber";
            case "Patient Name":
                return "patientName";
            case "Breed":
                return "breed";
            case "Sex":
                return "sex";
            case "Date of Birth":
                return "dateOfBirth";
            case "Color":
                return "color";
            case "Weight":
                return "weight";
            default:
                break;
        }

        return "FAIL";
    }

    public void validate() {
        if (getSearchType().equals("FAIL")) {
            sessionmap.put("search", (List) sessionmap.get("view"));
            addFieldError("searchInput", "Field is left blank");
        }
        if (getSearchInput().trim().equals("")) {
            System.out.println("isnull " + sessionmap.get("view") == null);
            sessionmap.put("search", sessionmap.get("view"));
        }
    }

    @Action("searchDatabase")
    public String searchDatabase() {
        Session session = null;
        if (getSearchType().equals("FAIL")) {
            addFieldError("searchInput", "Something went wrong...");
            return INPUT;
        }
        session = ((SessionFactory) sessionmap.get("factory")).openSession();
        List records = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Criteria criteria = session.createCriteria(Information.class);
            ProjectionList pjl = Projections.projectionList()
                    .add(Projections.property("controlNumber"))
                    .add(Projections.property("address"))
                    .add(Projections.property("breed"))
                    .add(Projections.property("color"))
                    .add(Projections.property("contactNumber"))
                    .add(Projections.property("dateOfBirth"))
                    .add(Projections.property("ownerName"))
                    .add(Projections.property("patientName"))
                    .add(Projections.property("sex"))
                    .add(Projections.property("weight"));
            criteria.setProjection(pjl);

            //executing the search
            try {
                //for exact fields
                System.out.println(getSearchType() + "Search type and " + getSearchInput() + " search input");
                if (getSearchType().equals("contactNumber") | getSearchType().equals("controlNumber")
                        | getSearchType().equals("sex") | getSearchType().equals("dateOfBirth")) {

                    //@for getting dob
                    if (getSearchType().equals("dateOfBirth")) {
                        java.util.Date date = sdf.parse(getSearchInput());
                        System.out.println("Inside dateOfBirth " + date);
                        criteria.add(Restrictions.eq(getSearchType(), date));
                    } //@if either weight
                    else if (getSearchType().equals("weight")) {
                        criteria.add(Restrictions.eq(getSearchType(), Double.parseDouble(getSearchInput())));
                    } //@if controlNumber or contactNumber
                    else {
                        criteria.add(Restrictions.eq(getSearchType(), Long.parseLong(getSearchInput())));
                    }
                } //for non exact fields
                else {

                    criteria.add(Restrictions.ilike(getSearchType(), "%" + getSearchInput().trim() + "%"));
                }
                records = criteria.list();
                System.out.println("List is fired " + records.size());
                ArrayList<Information> arl = new ArrayList();

                for (Iterator it = records.iterator(); it.hasNext();) {
                    Object[] myResult = (Object[]) it.next();
                    Information inf = new Information();
                    inf.setControlNumber((Long) myResult[0]);
                    inf.setAddress((String) myResult[1]);
                    inf.setBreed((String) myResult[2]);
                    inf.setColor((String) myResult[3]);
                    inf.setContactNumber((Long) myResult[4]);
                    inf.setDateOfBirth((java.util.Date) myResult[5]);
                    inf.setOwnerName((String) myResult[6]);
                    inf.setPatientName((String) myResult[7]);
                    inf.setSex((String) myResult[8]);
                    inf.setWeight((double) myResult[9]);
                    arl.add(inf);
                }
                sessionmap.put("search", arl);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid input on field");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return SUCCESS;
    }
}
