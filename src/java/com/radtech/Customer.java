package com.radtech;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Table(name="Customers")
public class Customer extends GenericModel{
    private long cutomerId, contactNumber;
    private String name, address;
    private List appointments, pets;

    @Id
    @Column(name="CUSTOMER_ID")
    public long getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(long cutomerId) {
        this.cutomerId = cutomerId;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer", targetEntity = Appointment.class)
    public List getAppointments() {
        return appointments;
    }

    public void setAppointments(List appointments) {
        this.appointments = appointments;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner", targetEntity = Pet.class)
    public List getPets() {
        return pets;
    }

    public void setPets(List pets) {
        this.pets = pets;
    }

    
    @Override
    public String toString() {
        return "Customer{" + "cutomerId=" + cutomerId + ", contactNumber=" + contactNumber + ", name=" + name + ", address=" + address + '}';
    }
    
    
}
