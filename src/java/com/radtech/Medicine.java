package com.radtech;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="MEDICINES")
public class Medicine implements Serializable{  
    private long medicineId, consultationId;
    private String name;

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

    @Override
    public String toString() {
        return "Medicine{" + "medicineId=" + medicineId + ", consultationId=" + consultationId + ", name=" + name + '}';
    }
    
    
    
}
