package com.radtech;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ARCHIVE")
public class Archive implements Serializable{
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

    public Archive(){}
    @Id
    @Column(name="CONTROL_NUMBER")
    public long getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(long controlNumber) {
        this.controlNumber = controlNumber;
    }
    
    @Column(name="OWNER_NAME")
    public String getOwnerName() {
        return ownerName;
    }

    
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Column(name="ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="CONTACT_NUMBER")
    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Column(name="PATIENT_NAME")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name="BREED")
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Column(name="SEX")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name="AGE")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name="COLOR")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name="WEIGHT")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public long getId(){
        return id;
    }
    
    public void setInformation(Information i){
        setAddress(i.getAddress());
        setControlNumber(i.getControlNumber());
        setOwnerName(i.getOwnerName());
        setContactNumber(i.getContactNumber());
        setBreed(i.getBreed());
        setSex(i.getSex());
        setAge(i.getAge());
        setColor(i.getColor());
        setWeight(i.getWeight());
    }

    @Override
    public String toString() {
        return "Archive{" + "controlNumber=" + controlNumber + ", ownerName=" + ownerName + ", address=" + address + ", contactNumber=" + contactNumber + ", patientName=" + patientName + ", breed=" + breed + ", sex=" + sex + ", age=" + age + ", color=" + color + ", weight=" + weight + ", id=" + id + '}';
    }
    
    
}
