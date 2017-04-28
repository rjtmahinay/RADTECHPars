package com.radtech;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="Medicines")
public class Medicine extends GenericModel{  
    private long medicineId;
    private String medicineName;
    private Consultation consultation;

    @Id
    @Column(name="MEDICINE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    @Column(name="MEDICINE_NAME")
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Consultation.class)
    @JoinColumn(name="CONSULTATION_ID")
    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    
    @Override
    public String toString() {
        return  medicineName +", ";
    }   
}
