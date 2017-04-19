/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radtech;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Appointments")
public class Appointment extends GenericModel{
    private long appointmentId;
    private String comment;
    private Date appointmentDate;
    private Customer customer;
    private List consultations = new ArrayList<Consultation>();

    @Id
    @Column(name="APPOINTMENT_ID")
    public long getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Column(name="COMMENT")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name="APPOINTMENT_DATE")
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name="CUSTOMER_ID")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appointment", targetEntity = Consultation.class)
    public List getConsultations() {
        return consultations;
    }

    public void setConsultations(List consultations) {
        this.consultations = consultations;
    }
    
    
    
    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId +  ", comment=" + comment  + ", appointmentDate=" + appointmentDate + '}';
    }
}
