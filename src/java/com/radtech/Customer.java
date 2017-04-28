package com.radtech;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Table(name="Customers")
public class Customer extends GenericModel{
    private long customerId, contactNumber;
    private String name, address;
    private List appointments = new ArrayList();
    private List pets = new ArrayList();

    @Id
    @Column(name="CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Column(name="CONTACT_NUMBER")
    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer", targetEntity = Appointment.class,orphanRemoval = true)
    public List getAppointments() {
        return appointments;
    }

    public void setAppointments(List appointments) {
        this.appointments = appointments;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner", targetEntity = Pet.class,orphanRemoval = true)
    public List getPets() {
        return pets;
    }

    public void setPets(List pets) {
        this.pets = pets;
    }

    
    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", contactNumber=" + contactNumber + ", name=" + name + ", address=" + address + '}';
    }
    
    
}
