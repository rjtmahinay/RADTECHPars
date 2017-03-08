/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radtech;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="AppointmentDB")
public class Appointment implements Serializable{
    private long appointmentNumber, controlNumber;
    private Date date;
    private String comment, dateinput;

    @Id
    @Column(name="APPOINTMENT_NUMBER")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(long appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    @Column(name="CONTROL_NUMBER")
    public long getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(long controlNumber) {
        this.controlNumber = controlNumber;
    }
    @Column(name="DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Column(name="COMMENT")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Transient
    public String getDateinput() {
        return dateinput;
    }

    public void setDateinput(String dateinput) {
        this.dateinput = dateinput;
    }

    
    
    @Override
    public String toString() {
        return "Appointment{" + "appointmentNumber=" + appointmentNumber + ", controlNumber=" + controlNumber + ", date=" + date + ", comment=" + comment + '}';
    }
    
    
    
}
