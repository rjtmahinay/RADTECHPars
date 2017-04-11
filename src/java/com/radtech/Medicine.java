package com.radtech;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="Medicines")
public class Medicine extends GenericModel{  
    private long medicineId, consultationId;
    private String name;
    private Consultation consultation;

    @Id
    @Column(name="MEDICINE_ID")
    public long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    @Column(name="CONSULTATION_ID")
    public long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(long consultationId) {
        this.consultationId = consultationId;
    }

    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Consultation.class)
    @JoinColumn(name="CONSULTATION_ID")
    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    
    @Override
    public String toString() {
        return "Medicine{" + "medicineId=" + medicineId + ", consultationId=" + consultationId + ", name=" + name + '}';
    }   
}
