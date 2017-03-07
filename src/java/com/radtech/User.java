/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radtech;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "USERDB" )
public class User implements Serializable{

    private String name, surname, username, password;
    public String password2, password3;
    
    @Column(name="NAME", nullable=false)
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    @Column(name="SURNAME", nullable=false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Id
    @Column(name="USERNAME",nullable=false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name="PASSWORD", nullable=false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Transient
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    @Transient
    public String getPassword3() {
        return password3;
    }
    public void setPassword3(String password3) {
        this.password3 = password3;
    }
    
    
    
    public String toString(){
        return "Name: " + getName() + "\nSurname: " + getSurname() + "\nUsername: " + getUsername() + "Password: " + getPassword();
    }
    
}
