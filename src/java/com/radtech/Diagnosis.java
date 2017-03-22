/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radtech;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name="DIAGNOSISDB")
public class Diagnosis implements Serializable{
    private long diagnosisNumber;
    private Date dateDiagnosed;
    private String diagnosis, id;
    public Information information;
    
    @Id
    @Column(name="DIAGNOSIS_NUMBER")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getDiagnosisNumber() {
        return diagnosisNumber;
    }

    public void setDiagnosisNumber(long diagnosisNumber) {
        this.diagnosisNumber = diagnosisNumber;
    }

    @ManyToOne
    @JoinColumn(name="INFO_DIAG")
    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
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
    
    @Transient
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Diagnosis{" + "diagnosisNumber=" + diagnosisNumber + ", dateDiagnosed=" + dateDiagnosed + ", diagnosis=" + diagnosis + ", id=" + id + '}';
    }
    
    
    
    
}
