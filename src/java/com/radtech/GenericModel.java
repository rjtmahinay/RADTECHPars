package com.radtech;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


public class GenericModel implements Serializable{
    private String numberInput, dateInput, input3;

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

    public String getInput3() {
        return input3;
    }

    public void setInput3(String input3) {
        this.input3 = input3;
    }
    
}
