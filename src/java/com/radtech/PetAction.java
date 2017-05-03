package com.radtech;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

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
			for(Object o:p.getConsultations()){
				hiberialize(((Consultation)o).getMedicines());
			}
			putMap("consultations", session.createCriteria(Consultation.class, "consultation").createAlias("consultation.pet", "pet").add(Restrictions.eq("pet.petId",p.getPetId())).add(Restrictions.isNotNull("diagnosis")).list());
			
			return SUCCESS;
		}
		else{
			addActionError("Pet not found!");
			return INPUT;
		}
	}
	    public String fetchPet(){
            try{
                    session = getSession();
//                    Pet p = (Pet)session.load(Pet.class, );
//                    System.out.println(p.toString());
//                    sessionmap.put("currentPet", p);
                    return SUCCESS;
            }
            catch(HibernateException e){
                    e.printStackTrace();
            }
            finally{
                    if(session != null) session.close();
            }
            return INPUT;
    }
	
	public String editPet(){
		session = getSession();
		tx = session.getTransaction();
		try{
			tx.begin();
			System.out.println("Input is " + pet.toString());
			Pet p = (Pet)session.load(Pet.class, Long.parseLong(pet.getInput3()));
			p.setName(pet.getName());
			p.setBreed(pet.getBreed());
			p.setColor(pet.getColor());
			p.setSex(pet.getSex());
			p.setDateOfBirth(pet.getDateOfBirth());
			System.out.println("Should be output " + p.toString());
			session.merge(p);
			tx.commit();
			refresh();
			putMap("currentPet", p);
			return SUCCESS;
            }
            catch(HibernateException e){
                    e.printStackTrace();
                    tx.rollback();
                    addActionError("Edit not complete");
                    return INPUT;
            }
            finally{
                    if(session!= null)session.close();
            }
    }
}



//pet
//	add pet
//	edit pet
//	delete pet?
