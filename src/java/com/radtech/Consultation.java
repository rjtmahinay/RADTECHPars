package com.radtech;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
@Table(name="Consultations")
public class Consultation extends GenericModel{
    private long consultationId;
    private Date consultationDate;
    private double weight, temperature;
    private String eyes, ears, nose, throat, derma, gums, lymphNodes, diagnosis;
    private Appointment appointment;
    private Pet pet;
    private List medicines = new ArrayList<Medicine>();

    @Id
    @Column(name="CONSULTATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(long consultationId) {
        this.consultationId = consultationId;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Pet.class)
    @JoinColumn(name="PET_ID")
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Column(name="CONSULTATION_DATE")
    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    @Column(name="WEIGHT")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Column(name="TEMPERATURE")
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Column(name="EYES")
    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    @Column(name="EARS")
    public String getEars() {
        return ears;
    }

    public void setEars(String ears) {
        this.ears = ears;
    }

    @Column(name="NOSE")
    public String getNose() {
        return nose;
    }

    public void setNose(String nose) {
        this.nose = nose;
    }

    @Column(name="THROAT")
    public String getThroat() {
        return throat;
    }

    public void setThroat(String throat) {
        this.throat = throat;
    }

    @Column(name="DERMA")
    public String getDerma() {
        return derma;
    }

    public void setDerma(String derma) {
        this.derma = derma;
    }

    @Column(name="GUMS")
    public String getGums() {
        return gums;
    }

    public void setGums(String gums) {
        this.gums = gums;
    }

    @Column(name="LYMPH_NODES")
    public String getLymphNodes() {
        return lymphNodes;
    }

    public void setLymphNodes(String lymphNodes) {
        this.lymphNodes = lymphNodes;
    }
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Appointment.class)
    @JoinColumn(name="APPOINTMENT_ID")
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "consultation", targetEntity = Medicine.class)
    public List getMedicines() {
        return medicines;
    }

    public void setMedicines(List medicines) {
        this.medicines = medicines;
    }

    @Column(name="DIAGNOSIS")
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    
    @Override
    public String toString() {
        return "Consultation{" + "consultationId=" + consultationId  + ", consultationDate=" + consultationDate + ", weight=" + weight + ", temperature=" + temperature + ", eyes=" + eyes + ", ears=" + ears + ", nose=" + nose + ", throat=" + throat + ", derma=" + derma + ", gums=" + gums + ", lymphNodes=" + lymphNodes +  '}';
    }
}
