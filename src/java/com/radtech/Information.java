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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MEDICALDB")
public class Information implements Serializable {

    private long controlNumber;
    private String ownerName;
    private String address;
    private long contactNumber;
    private String patientName;
    private String breed;
    private Date dateOfBirth;
    private String sex;
    private String color;
    private double weight;
    private long id;
    private String dateinput;
    private String idinput;
    public List<Consultation> diagnosis;
    private List<Appointment> appointments;
    private Date nextAppointment;

    public Information() {
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

    @Id
    @Column(name = "CONTROL_NUMBER")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(long controlNumber) {
        this.controlNumber = controlNumber;
    }

    @Column(name = "WEIGHT")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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

    @Column(name = "PATIENT_NAME")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name = "COLOR")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "CONTACT_NUMBER")
    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Column(name = "DATEOFBIRTH")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Transient
    public long getId() {
        return id;
    }

    @Transient
    public String getDateinput() {
        return dateinput;
    }

    public void setDateinput(String dateinput) {
        this.dateinput = dateinput;
    }

    @OneToMany(targetEntity = Consultation.class, mappedBy = "information",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Consultation> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<Consultation> diagnosis) {
        this.diagnosis = diagnosis;
    }

    @OneToMany(targetEntity = Appointment.class, mappedBy = "information",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("date")
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Column(name = "NEXT_APPOINTMENT")
    public Date getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(Date nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    @Transient
    public String getIdinput() {
        return idinput;
    }

    public void setIdinput(String idinput) {
        this.idinput = idinput;
    }

    @Override
    public String toString() {
        return "Information{" + "controlNumber=" + controlNumber + ", ownerName=" + ownerName + ", address=" + address + ", contactNumber=" + contactNumber + ", patientName=" + patientName + ", breed=" + breed + ", dateOfBirth=" + dateOfBirth + ", sex=" + sex + ", color=" + color + ", weight=" + weight + ", id=" + id + ", dateinput=" + dateinput + '}';
    }

}
