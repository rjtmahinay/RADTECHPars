/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radtech;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name="DIAGNOSISDB")
public class Diagnosis implements Serializable{
    private long diagnosisNumber;
    private long controlNumber;
    private Date dateDiagnosed;
    private String diagnosis;
    
    @Id
    @Column(name="DIAGNOSIS_NUMBER")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getDiagnosisNumber() {
        return diagnosisNumber;
    }

    public void setDiagnosisNumber(long diagnosisNumber) {
        this.diagnosisNumber = diagnosisNumber;
    }
    
    @Column(name = "CONTROL_NUMBER")
    public long getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(long controlNumber) {
        this.controlNumber = controlNumber;
    }
    @Column(name="DATE")
    public Date getDateDiagnosed() {
        return dateDiagnosed;
    }

    public void setDateDiagnosed(Date dateDiagnosed) {
        this.dateDiagnosed = dateDiagnosed;
    }
    @Column(name="DIAGNOSIS")
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    
}
