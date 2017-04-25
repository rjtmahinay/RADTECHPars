package com.radtech;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Archives")
public class Archive extends GenericModel{
    
    private long archiveId;
    private String name, address, reason;
    private List pets;

    @Id
    @Column(name="ARCHIVE_ID")
    public long getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(long archiveId) {
        this.archiveId = archiveId;
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

    @Column(name="REASON")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @OneToMany(targetEntity = Pet.class)
    @JoinColumn(name="PETS")
    public List getPets() {
        if(pets == null) return new ArrayList();
        return pets;
    }

    public void setPets(List pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Archive{" + "archiveId=" + archiveId + ", name=" + name + ", address=" + address + ", reason=" + reason + ", pets=" + pets + '}';
    }
}
