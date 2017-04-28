package com.radtech;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


public class GenericModel implements Serializable{
    private String numberInput, dateInput, input1, input2, input3, dateInput2, dateInput3, statType, reasonInput;

    public String getNumberInput() {
        return numberInput;
    }

    public void setNumberInput(String numberInput) {
        this.numberInput = numberInput;
    }

    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
    }

    public String getInput1() {
        return input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getInput2() {
        return input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    
    public String getInput3() {
        return input3;
    }

    public void setInput3(String input3) {
        this.input3 = input3;
    }

    public String getDateInput2() {
        return dateInput2;
    }

    public void setDateInput2(String dateInput2) {
        this.dateInput2 = dateInput2;
    }

    public String getDateInput3() {
        return dateInput3;
    }

    public void setDateInput3(String dateInput3) {
        this.dateInput3 = dateInput3;
    }

    public String getStatType() {
        return statType;
    }

    public void setStatType(String statType) {
        this.statType = statType;
    }

    public String getReasonInput() {
        return reasonInput;
    }

    public void setReasonInput(String reasonInput) {
        this.reasonInput = reasonInput;
    }
    
}
