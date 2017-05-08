package com.radtech;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;

public class MedicineAction extends GenericAction{
    Medicine medicine = new Medicine();
    public Medicine getModel(){
        return medicine;
    }
    
    public String tempMeds(){
        ArrayList<Medicine> tempMeds = (ArrayList)sessionmap.get("tempMeds");
        if(tempMeds == null) tempMeds = new ArrayList<Medicine>();
            if(!medicine.getMedicineName().equals("")){
            tempMeds.add(medicine);
            sessionmap.put("tempMeds", tempMeds);
            return SUCCESS;
        }
        else{
            addActionError("Empty medicine field");
            return INPUT;
        }
                    
    }
}

