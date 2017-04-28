package com.radtech;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Archives")
public class Archive extends GenericModel{
    
    private String reason;
    private long archiveId, contactNumber;
    private String name, address;
    
    @Column(name="REASON")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(long archiveId) {
        this.archiveId = archiveId;
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
        return "Archive{" + "reason=" + reason + '}';
    }

}
