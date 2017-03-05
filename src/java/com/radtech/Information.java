/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;

/**
 *
 * @author Aspire
 */
@Entity
@Table(name="MEDICALDB" )
public class Information extends ActionSupport implements Serializable{
    private static SessionMap sessionmap;
    private static String ERROR_BANNER = "ERROR", SUCCESS_BANNER = "SUCCESS";
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
    private long id;
    public Information() {
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
    @Column(name="BREED")
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
    @Column(name="SEX")
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    @Id
    @Column(name="CONTROL_NUMBER")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(long controlNumber) {
        this.controlNumber = controlNumber;
    }

    /**
     * @return the weight
     */
    @Column(name="WEIGHT")
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
    @Column(name="OWNER_NAME")
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
    @Column(name="ADDRESS")
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
    @Column(name="PATIENT_NAME")
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
    @Column(name="COLOR")
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
    @Column(name="CONTACT_NUMBER")
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
    @Column(name="AGE")
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setId(long id){
        this.id = id;
    }
    @Transient
    public long getId(){
        return id;
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
