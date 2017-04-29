package com.radtech;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.hibernate.HibernateException;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.PrintWriter;
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
                //appo for appointment string
                arc.setName(c.getName());
                arc.setContactNumber(c.getContactNumber());
                arc.setAddress(c.getAddress());
                System.out.println(customer.getReasonInput());
                arc.setReason(customer.getReasonInput());
                hiberialize(c.getPets());
                hiberialize(c.getAppointments());
                String custom = "";
                String appo = "[\n";
                for(Object o: c.getAppointments()){
                    Appointment ap = (Appointment)o;
                    appo += "{\n\tappointmentId:"+ap.getAppointmentId()+",\n\tstatus:"+ap.getStatus()+",\n\tappointmentDate:"+ap.getAppointmentDate()+"},\n\t";
                }
                if(appo.length()>0) appo = appo.substring(0, appo.length()-1) + "]";
                else appo = "";
                String pets = "[";
                for(Object o: c.getPets()){
                    Pet p = (Pet)o;
                    //loop through consultations
                    String cons = "[\n\t\t";
                    hiberialize(p.getConsultations());
                    for(Object oo: p.getConsultations()){
                        Consultation cc = (Consultation)oo;
                        hiberialize(cc.getMedicines());
                        String meds = "[\n\t\t\t";
                        //loop through medicines
                        for(Object ooo: cc.getMedicines()){
                            Medicine m = (Medicine)ooo;
                            meds+="{\n\t\t\tmedicineName:"+m.getMedicineName()+",\n\t\t\tmedicineId:"+m.getMedicineId()+"\n\t\t},\n\t\t\t";
                        }
                        if(meds.length()>0) meds = meds.substring(0, meds.length()-1) + "]";
                        else meds = "";
                        cons += "{\n\t\tconsultationId:"+cc.getConsultationId()+",\n\t\tconsultationDate:"+cc.getConsultationDate()+","
                                + "\n\t\teyes:" + cc.getEyes()+",\n\t\tears:"+cc.getEars()+",\n\t\tnose:"+cc.getNose()+",\n\t\tthroat:"+
                                cc.getThroat()+",\n\t\tderma:"+cc.getDerma()+",\n\t\tgums:"+cc.getGums()+",\n\t\tlymphNodes:"+cc.getLymphNodes()
                                + ",\n\t\tweight:" +cc.getWeight() +",\n\t\ttemperature:"+ cc.getTemperature()+",\n\t\tdiagnosis:"+cc.getDiagnosis()
                                +",\n\t\tstatus:" + cc.getStatus()+",\n\t\tmedicines:"+meds+"\n\t},";
                        
                        //add fields after this, use [meds] string for medicine
                    }
                    cons = cons.substring(0, cons.length()-1) + "],";
                    pets += "{\n\tpetId:" + p.getPetId() +",\n\tname:"+p.getName()+",\n\tsex:"+p.getSex()+",\n\tbreed:"+p.getBreed()+
                            ",\n\tdateOfBirth:" + p.getDateOfBirth()+",\n\tconsultations:"+cons +"\n},";
                    //add Fields after this use [cons] string for consultation
                }
                pets = pets.substring(0, pets.length()-1) + "]\n";
                custom += "{customerId:"+c.getCustomerId()+",\nname:"+c.getName()+",\naddress:"+c.getAddress()+",\ncontactNumber:" + c.getContactNumber()
                        +",\nreason:" + customer.getReasonInput() +",\nappointments:"+appo+",\npets:"+pets+"}";
                System.out.println(custom);
                try(  PrintWriter out = new PrintWriter(getPath()+"/"+c.getCustomerId()+".json") ){
                out.println( custom );
                }
                session.save(arc);
                session.delete(c);
                tx.commit();
                refresh();
		putMap("archive", session.createCriteria(Archive.class).list());
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
