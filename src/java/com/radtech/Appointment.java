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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Appointments")
public class Appointment extends GenericModel{
    private long appointmentId;
    private String status, transactionType, appointmentComment;
    private Date appointmentDate;
    private Customer customer;
    private List consultations = new ArrayList<Consultation>();

    @Id
    @Column(name="APPOINTMENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Column(name="STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="APPOINTMENT_DATE")
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appointment", targetEntity = Consultation.class, orphanRemoval = true)
    public List getConsultations() {
        return consultations;
    }

    public void setConsultations(List consultations) {
        this.consultations = consultations;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name="TRANSACTION_TYPE")
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    
    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + ", status=" + status + ", appointmentDate=" + appointmentDate + ", customer=" + customer + ", consultations=" + consultations + '}';
    }

	@Column(name="APPOINTMENT_COMMENT")
	public String getAppointmentComment() {
		return appointmentComment;
	}

	public void setAppointmentComment(String appointmentComment) {
		this.appointmentComment = appointmentComment;
	}
	
}
