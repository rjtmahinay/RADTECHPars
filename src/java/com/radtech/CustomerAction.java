package com.radtech;

import java.util.ArrayList;
import org.hibernate.HibernateException;



public class CustomerAction extends GenericAction{
    Customer customer = new Customer();
    
    @Override
    public Customer getModel(){
        return customer;
    }
    public String addCustomer(){
        try{
            session = getSession();
            tx = session.getTransaction();
            //input is customer info
            //save it into customer
            tx.begin();
            ArrayList<Pet> tempets = (ArrayList)sessionmap.get("tempets");
                if(tempets == null | tempets.size()<=0) return INPUT;           //no pet input
            //make appointment
            Appointment app = new Appointment();
            //set appointment-customer, customer-appointment
            app.setCustomer(customer);
            customer.setAppointments(new ArrayList());
            System.out.println("Customer appointments is null? " + customer.getAppointments()==null);
            customer.getAppointments().add(app);
            
            for(Pet p: tempets){
                p.setOwner(customer);
                //make consultation
                Consultation c = new Consultation();
                //setup pet-consult, consult-pet, consult-appoint
                c.setPet(p);
                hiberialize(p.getConsultations());
                System.out.println("Pet consultations is null? " + p.getConsultations()==null);
                p.getConsultations().add(c);
                c.setAppointment(app);
                session.saveOrUpdate(c);
                session.saveOrUpdate(p);
                session.flush();
            }
            app.setAppointmentDate(new java.util.Date());
            
            customer.setPets(tempets);
            session.saveOrUpdate(customer);
            session.saveOrUpdate(app);
            tx.commit();
            refresh(); 
            putMap("currentCustomer", customer);
            sessionmap.remove("tempets");
           return SUCCESS;
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
            return INPUT;
        }
        finally{
            if(session!= null) session.close();
        }
    }
    
    public String editCustomer(){
        return addCustomer();
    }
    
    public String fetchCustomer(){
        try{
            session = getSession();
            Customer custom = (Customer)session.load(Customer.class, Long.parseLong(customer.getNumberInput()));
            System.out.println(custom.toString());
            sessionmap.put("currentCustomer", custom);
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
}
//customer
//	add customer check
//	edit customer check

//	delete customer ?
