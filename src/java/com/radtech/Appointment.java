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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="AppointmentDB")
public class Appointment implements Serializable{
    private long appointmentNumber;
    private Date date, adate;
    private String comment, dateinput;
    private Information information;
    private long id;
    private String appinput;
    
    @Id
    @Column(name="APPOINTMENT_NUMBER")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(long appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
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
    @Column(name="ACCOMPLISHED_DATE")
    public Date getAdate() {
        return adate;
    }

    public void setAdate(Date adate) {
        this.adate = adate;
    }
    
    @ManyToOne
    @JoinColumn(name="APPOINT_PK")
    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    @Transient
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Transient
    public String getAppinput() {
        return appinput;
    }

    public void setAppinput(String appinput) {
        this.appinput = appinput;
    }
    
    
}
