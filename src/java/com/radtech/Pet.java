package com.radtech;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name="Pets")
public class Pet extends GenericModel{
    private long petId, customerId;
    private String name, breed,color, sex;
    private Date dateOfBirth;
    private Customer owner;
    private List consultations = new ArrayList<Consultation>();

    @Id
    @Column(name="PET_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    @Column(name="CUSTOMER_ID")
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="BREED")
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Column(name="COLOR")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name="SEX")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name="DATE_OF_BIRTH")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @ManyToOne(fetch = FetchType.EAGER,  targetEntity = Customer.class)
    @JoinColumn(name="OWNER_ID")
    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Consultation.class, mappedBy = "pet", orphanRemoval = true)
    public List getConsultations() {
        return consultations;
    }

    public void setConsultations(List consultations) {
        this.consultations = consultations;
    }


    @Override
    public String toString() {
        return "Pet{" + "petId=" + petId + ", customerId=" + customerId + ", name=" + name + ", breed=" + breed + ", color=" + color + ", sex=" + sex + ", dateOfBirth=" + dateOfBirth + '}';
    }
    
    
    
}
