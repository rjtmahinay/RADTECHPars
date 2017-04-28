package com.radtech;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.hibernate.HibernateException;
import com.google.gson.Gson;
import java.io.FileWriter;
import static org.apache.struts2.ServletActionContext.getServletContext;




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
                session.save(customer);
                session.flush();
                ArrayList<Pet> tempets = (ArrayList)sessionmap.get("tempets");
                        if(tempets == null | tempets.size()<=0) return INPUT;           //no pet input
                //make appointment
                Appointment app = new Appointment();
                //set appointment-customer, customer-appointment
                session.save(app);
                session.flush();
                app.setCustomer(customer);
                customer.getAppointments().add(app);
                session.merge(customer);
                session.merge(app);
                session.flush();
                for(Pet p: tempets){
                    Consultation c = new Consultation();
                    p.setOwner(customer);
                    hiberialize(customer.getPets());
                    customer.getPets().add(p);
                    session.save(c);
                    c.setPet(p);
                    hiberialize(p.getConsultations());
                    p.getConsultations().add(c);
                    c.setAppointment(app);
                    c.setStatus("pending");
                    p.setOwner(customer);
                    session.save(p);
                    session.merge(c);
                    session.flush();
                }
                app.setAppointmentDate(new java.util.Date());
                app.setTransactionType("walk-in");
                app.setStatus("pending");
                session.merge(app);
                session.merge(customer);
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
            session = getSession();
            tx = session.getTransaction();
            try{
                    tx.begin();
                    System.out.println("Input is " + customer.toString());
                    Customer c = (Customer)session.load(Customer.class, Long.parseLong(customer.getInput3()));
                    c.setName(customer.getName());
                    c.setContactNumber(customer.getContactNumber());
                    c.setAddress(customer.getAddress());
                    System.out.println("Should be output " + c.toString());
                    session.merge(c);
                    tx.commit();
                    refresh();
                    putMap("currentCustomer", c);

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

    public String toArchive() throws IOException{
        session = getSession();
        tx=session.getTransaction();
        try{
            long customerId = Long.parseLong(customer.getInput3());
            Customer c = (Customer)session.get(Customer.class, customerId);
            if(c==null){
                addActionError("Customer not found!");
                return INPUT;
            }
            else{
                tx.begin();
                c = (Customer)session.load(Customer.class, customerId);
                Archive arc = new Archive();
                session.persist(arc);
                arc.setName(c.getName());
                arc.setContactNumber(c.getContactNumber());
                arc.setAddress(c.getAddress());
                System.out.println(customer.getReasonInput());
                arc.setReason(customer.getReasonInput());
                hiberialize(c.getPets());
                hiberialize(c.getAppointments());
                session.save(arc);
                session.delete(c);
                tx.commit();
                refresh();
                return SUCCESS;
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
            return INPUT;
        }
        finally{
            if(session!= null)session.close();
        }
    }
}
