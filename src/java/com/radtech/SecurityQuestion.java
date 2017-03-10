
package com.radtech;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="SECURITY_QUESTION")
public class SecurityQuestion implements Serializable{
    private long sec_number;
    private String  answer, username, password;
    private User user;
    public String question;

    @Id
    @Column(name="SECURITY_NUMBER")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getSec_number() {
        return sec_number;
    }

    public void setSec_number(long sec_number) {
        this.sec_number = sec_number;
    }
    
    @Column(name="QUESTION")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Column(name="ANSWER")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    
    @ManyToOne
    @JoinColumn(name="SEC_USER_KEY")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Transient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SecurityQuestion{" + "sec_number=" + sec_number + ", question=" + question + ", answer=" + answer + ", username=" + username + ", password=" + password + ", user=" + user + '}';
    }
    
    
}
