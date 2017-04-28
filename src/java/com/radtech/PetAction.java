package com.radtech;

import java.util.ArrayList;
import org.hibernate.HibernateException;

public class PetAction extends GenericAction{
    Pet pet = new Pet();
    
    @Override
    public Pet getModel(){
        return pet;
    }
    
    public String addPet(){
        try{
            session = getSession();
            tx =session.getTransaction();
		tx.begin();
		session.saveOrUpdate(pet);
		session.flush();
		long l = ((Customer)getCurrentCustomer()).getCustomerId();
		Customer c = (Customer)session.load(Customer.class, l);
            pet.setDateOfBirth(toDate(pet.getDateInput()));
            pet.setOwner(c);
			hiberialize(c.getPets());
			c.getPets().add(pet);
			
            //if customer not found and adding pet, something wrong
            if(pet.getOwner()== null) return ERROR;
            session.merge(c);
            session.merge(pet);
            tx.commit();
			
			refresh();
			putMap("currentCustomer", c);
            return SUCCESS;
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
            return INPUT;
        }
        finally{
            if(session!=null) session.close();
        }
    }
    public String editPet(){
        return addPet();
    }
    
    //function processes
    public String tempet(){
        //building pet list for new customer
        ArrayList<Pet> tempets = (ArrayList)sessionmap.get("tempets");
        if(tempets == null) tempets = new ArrayList<Pet>();
        pet.setDateOfBirth(toDate(pet.getDateInput()));
        tempets.add(pet);
        sessionmap.put("tempets", tempets);
        return SUCCESS;
    }
	public String historize(){
		session = getSession();
		long l = Long.parseLong(pet.getInput1());
		if(session.get(Pet.class, l)!= null){
			Pet p = (Pet)session.load(Pet.class, l);
			hiberialize(p.getConsultations());
			putMap("consultations", p.getConsultations());
			return SUCCESS;
		}
		else{
			addActionError("Pet not found!");
			return INPUT;
		}
	}
}
//pet
//	add pet
//	edit pet
//	delete pet?
