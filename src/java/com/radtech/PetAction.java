package com.radtech;

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
            pet.setDateOfBirth(toDate(pet.getDateInput()));
            pet.setOwner(getCurrentCustomer());
            //if customer not found and adding pet, something wrong
            if(pet.getOwner()== null) return ERROR;
            tx.begin();
            session.saveOrUpdate(pet);
            tx.commit();
            refreshPets();
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
}
//pet
//	add pet
//	edit pet
//	delete pet?
