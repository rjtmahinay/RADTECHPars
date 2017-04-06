package com.radtech;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name="Customers")
public class Customer implements Serializable{
    private long cutomerId, contactNumber;
    private String name, address;
    //insert single user

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

    @Override
    public String toString() {
        return "Customer{" + "cutomerId=" + cutomerId + ", contactNumber=" + contactNumber + ", name=" + name + ", address=" + address + '}';
    }
    
    
}
