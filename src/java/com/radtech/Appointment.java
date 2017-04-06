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
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Appointments")
public class Appointment implements Serializable {
    private long appointmentId;
    private String userType, comment, name;
    private Date appointmentDate;
    //single User?

    @Id
    @Column(name="APPOINTMENT_ID")
    public long getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Column(name="USER_TYPE")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Column(name="COMMENT")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="APPOINTMENT_DATE")
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + ", userType=" + userType + ", comment=" + comment + ", name=" + name + ", appointmentDate=" + appointmentDate + '}';
    }
    
    
}
