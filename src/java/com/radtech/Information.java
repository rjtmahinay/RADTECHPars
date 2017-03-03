/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Aspire
 */
public class Information extends ActionSupport{
    private final String SUCCESS_BANNER = "Add Sucessful";
    private final String ERROR_BANNER = "Error";
    private static SessionMap sessionmap;
    private Session s;
    private Transaction t;
    private long controlNumber;
    private String ownerName;
    private String address;
    private int contactNumber;
    private String patientName;
    private String breed;
    private String sex;
    private int age;
    private String color;
    private double weight;
    private List<String> breed_list;
    private List<String> sex_list;
    
    public Information() {

        breed_list = new ArrayList<>();
        breed_list.add("Doggy");
        breed_list.add("German");

        sex_list = new ArrayList<>();
        sex_list.add("Male");
        sex_list.add("Female");

    }


    @Override
    public String execute() {
        if (addInfo(this) <= 0) {
            addActionError(ERROR_BANNER);
            return ERROR;
        } else {
            addActionMessage(SUCCESS_BANNER);
            return SUCCESS;
        }
    }

    public long addInfo(Information information) {
        long i = 0;
        try {
            s = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();

            t = s.beginTransaction();
            i = (long) s.save(information);
            t.commit();

        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return i;
    }

    /**
     * @return the breed_list
     */
    public List<String> getBreed_list() {
        return breed_list;
    }

    /**
     * @param breed_list the breed_list to set
     */
    public void setBreed_list(List<String> breed_list) {
        this.breed_list = breed_list;
    }

    /**
     * @return the sex_list
     */
    public List<String> getSex_list() {
        return sex_list;
    }

    /**
     * @param sex_list the sex_list to set
     */
    public void setSex_list(List<String> sex_list) {
        this.sex_list = sex_list;
    }

    /**
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * @param breed the breed to set
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(long controlNumber) {
        this.controlNumber = controlNumber;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return the ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName the ownerName to set
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @param patientName the patientName to set
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the contactNumber
     */
    public int getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    public String single(){
        
        HttpServletRequest req = ServletActionContext.getRequest();
        System.out.println(req.getParameter("id") + " is the selected ID");
        return SUCCESS;
    }
    public void validate(){
        if(ServletActionContext.getRequest().getParameter("id") == ""){}
        
    }
    public void setSession(SessionMap m){
        sessionmap = m;
    }
}
