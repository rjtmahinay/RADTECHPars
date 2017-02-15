/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radtech;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Aspire
 */
public class Information extends ActionSupport{

    public Information(){
        breed = new ArrayList<>();
         sex = new ArrayList<>();
         
         sex.add("Male");
         sex.add("Female");
         
    }
     @Override
    public String execute(){
         
        return SUCCESS;
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
     * @return the breed
     */
    public List<String> getBreed() {
        return breed;
    }

    /**
     * @param breed the breed to set
     */
    public void setBreed(List<String> breed) {
        this.breed = breed;
    }

    /**
     * @return the sex
     */
    public List<String> getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(List<String> sex) {
        this.sex = sex;
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
    
    private long controlNumber;
    private String ownerName;
    private String address;
    private String patientName;
    private List<String> breed;
    private List<String> sex;
    private String color;
    private int contactNumber;
    private int age;
    private double weight;
}
