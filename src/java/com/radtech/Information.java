/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radtech;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="MEDICALDB" )
public class Information implements Serializable{
    private long controlNumber;
    private String ownerName;
    private String address;
    private int contactNumber;
    private String patientName;
    private String breed;
    private Date dateOfBirth;
    private String sex;
    private String color;
    private double weight;
    private long id;
    private String dateinput;
    public List<Diagnosis> diagnosis;
    public Information() {
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
    
    @Column(name="DATEOFBIRTH")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

 
    public void setId(long id){
        this.id = id;
    }
    @Transient
    public long getId(){
        return id;
    }
    
    @Transient
    public String getDateinput() {
        return dateinput;
    }

    public void setDateinput(String dateinput) {
        this.dateinput = dateinput;
    }

    @OneToMany(targetEntity = Diagnosis.class, mappedBy = "info", 
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    @Override
    public String toString() {
        return "Information{" + "controlNumber=" + controlNumber + ", ownerName=" + ownerName + ", address=" + address + ", contactNumber=" + contactNumber + ", patientName=" + patientName + ", breed=" + breed + ", dateOfBirth=" + dateOfBirth + ", sex=" + sex + ", color=" + color + ", weight=" + weight + ", id=" + id + ", dateinput=" + dateinput + '}';
    }
    
    
}
