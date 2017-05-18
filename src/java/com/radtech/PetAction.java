package com.radtech;

import com.google.gson.Gson;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
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
    
    public String petReport() throws IOException{
        session = getSession();
        Gson gson = new Gson();
        if(pet != null){
            List<Report> report = new ArrayList<Report>();
            System.out.println("Pet id is " + pet.getPetId());
            List<Consultation> cons = session.createCriteria(Consultation.class, "cons")
                    .createAlias("pet", "cons.pet")
                    .add(Restrictions.eq("pet.petId", pet.getPetId()))
                    .addOrder(Order.asc("consultationDate"))
                    .add(Restrictions.isNotNull("consultationDate"))
                    .add(Restrictions.eq("status", "completed"))
                    .list();
            System.out.println(cons.size());
		if(cons.size()==0)
		{
			return INPUT;
		}
            String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            HashMap<String, Integer> monthVisit = new HashMap<String, Integer>();
            HashMap<String, Integer> meds = new HashMap<String, Integer>();
            String dates = "";
            String temp = "";
            String weight = "";
            String medString="", visitString="";
            
            for(Consultation c: cons){
                System.out.println(c.toString());
                String month = months[c.getConsultationDate().getMonth()];
                temp+= c.getTemperature() +" |";
                weight += c.getWeight() + " |";
                Calendar cal = Calendar.getInstance();
                cal.setTime(c.getConsultationDate());
                dates+= String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "-" +month +
                        "-"+ String.valueOf(cal.get(Calendar.YEAR)) + " |";
                //check if month is on list
                if(monthVisit.get(month)==null){
                    monthVisit.put(month, 1);
                }
                //if its on list ,add 1
                else{
                    int x = monthVisit.get(month)+ 1;
                    monthVisit.put(month, x);
                }
                //m,edicines
                hiberialize(c.getMedicines());
                StringBuilder sb = new StringBuilder();
                for(Object oo: c.getMedicines()){
                    Medicine m = (Medicine)oo;
                    //check if medicine in list
                    if(meds.get(m.getMedicineName())== null){
                        meds.put(m.getMedicineName(), 1);
                    }
                    //else add1 on medicine
                    else{
                        int x = meds.get(m.getMedicineName()) + 1;
                        meds.put(m.getMedicineName(), x);
                    }
                    sb.append(m.getMedicineName()+",");
                }   
                if(sb.length()>0)sb.deleteCharAt(sb.length()-1);
                Report rep = new Report();
                rep.setS1("" + c.getWeight());
                rep.setS2("" + c.getTemperature());
                rep.setS3(sb.toString());
                rep.setD1(c.getConsultationDate());
                rep.setId1("" + c.getConsultationId());
                report.add(rep);
            }
            if(temp.length()>0){
                temp = temp.substring(0, temp.length()-1);
            }
            if(weight.length()>0){
                weight = weight.substring(0, weight.length()-1);
            }
            if(dates.length()>0){
                dates = dates.substring(0, dates.length()-1);
            }

            /*
                *Creation of Medicine Pet Json
                *
                *
                */
                JsonChart medChart = new JsonChart("Pet's Medicines", "Medicine Name", "Number prescribed", "");

                for (Map.Entry<String, Integer> pair : meds.entrySet()) {
                    System.out.println("For key " + pair.getKey());
                    medString += pair.getKey() +","+ pair.getValue()+";";
                }
                if(medString.length()>0) medString = medString.substring(0, medString.length()-1);
                JsonObject medObject = new JsonObject(medChart, medString);
                
                /*
                *Constructing Monthly visit stat
                *
                */
                for (Map.Entry<String, Integer> pair : monthVisit.entrySet()) {
                    System.out.println("For key " + pair.getKey());
                    visitString += pair.getKey() +","+ pair.getValue()+";";
                }
                if(visitString.length()>0)visitString= visitString.substring(0, visitString.length()-1);
                JsonChart visitChart = new JsonChart("Visit per Month", "Month", "Number of Visits", "");
                JsonObject visitObject = new JsonObject(visitChart, visitString);
                
                /*
                *Construct chart for weight and temp
                */
                JsonLineObject tempweight = new JsonLineObject(new JsonLineChart(),dates, "Temperature,"+ temp+";Weight,"+weight);
                
                //make json file for meds and visits
                File petTempWeight = new File(getServletContext().getRealPath("/") + "/tempweight.json");
                File medfile = new File(getServletContext().getRealPath("/") + "/petMeds.json");
                File visfile = new File(getServletContext().getRealPath("/") + "/petVisits.json");
                Writer writer1 = new FileWriter(medfile);
                gson.toJson(medObject, writer1);
                writer1.close();
                
                Writer writer2 = new FileWriter(visfile);
                gson.toJson(visitObject, writer2);
                writer2.close();
                
                Writer writer3 = new FileWriter(petTempWeight);
                gson.toJson(tempweight, writer3);
                writer3.close();
                
                //gson.toJson(tempweight, new FileWriter(new File(getPath()+"/tempweight.json")));
            System.out.println("\nSize of Month visit is " + monthVisit.size() +"\n"
                    + "Size of Meds is " + meds.size()+"\n"
                            + dates + "\n" + temp + "\n" + weight);
            putMap("display", "pet");
            putMap("report", report);
            return SUCCESS;
        }
        return INPUT;
    }
}

/*
*Pet stats using temp, weight, medicine, visits per month
*temp weight use line2d
*
*
*/