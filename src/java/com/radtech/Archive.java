package com.radtech;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ARCHIVE")
public class Archive implements Serializable {

    private long controlNumber;
    private String ownerName;
    private String address;
    private long contactNumber;
    private String patientName;
    private String breed;
    private String sex;
    private Date dateOfBirth;
    private String color;
    private double weight;
    private long id;
    private String controlInput;

    public Archive() {
    }

    @Id
    @Column(name = "CONTROL_NUMBER")
    public long getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(long controlNumber) {
        this.controlNumber = controlNumber;
    }

    @Column(name = "OWNER_NAME")
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "CONTACT_NUMBER")
    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Column(name = "PATIENT_NAME")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name = "BREED")
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Column(name = "SEX")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "DATEOFBIRTH")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "COLOR")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "WEIGHT")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Transient
    public long getId() {
        return id;
    }

    @Transient
    public String getControlInput() {
        return controlInput;
    }

    public void setControlInput(String controlInput) {
        this.controlInput = controlInput;
    }

    public void setInformation(Information i) {
        setAddress(i.getAddress());
        setControlNumber(i.getControlNumber());
        setOwnerName(i.getOwnerName());
        setPatientName(i.getPatientName());
        setContactNumber(i.getContactNumber());
        setBreed(i.getBreed());
        setSex(i.getSex());
        System.out.println(i.getDateOfBirth());
        setDateOfBirth(i.getDateOfBirth());
        setColor(i.getColor());
        setWeight(i.getWeight());
    }
}
