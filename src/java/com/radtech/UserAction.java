package com.radtech;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.hibernate.HibernateException;
import org.hibernate.Session;


public class UserAction extends GenericAction{

    private User user = new User();

    public void setUser(User u) {
        user = u;
    }

    public User getUser() {
        return user;
    }

    @Override
    public User getModel() {
        return user;
    }

    public String login() {
        //Insert login logic
        initialize();
        if(user.getUsername().trim().equals("admin")){
            user.setUserType(user.getPassword());
            sessionmap.put("currentUser", user);
            System.out.println("User is " + user.toString());
            refresh();
            return SUCCESS;
        }
        else{
            //check if user exists
            session = getSession();
            User db = (User) session.get(User.class, user.getUsername());
            if (db == null) {
                addActionError("Username not found");
                return INPUT;
            } 
            else {
                db = (User) session.load(User.class, user.getUsername());
                System.out.println("Input password is " + de.sha256(user.getPassword()));
                System.out.println("DB password is " + db.getPassword());
                if (de.sha256(user.getPassword()).equals(db.getPassword())) {
                    refreshUser(db);
                    refresh();
                    session.close();
                    return SUCCESS;
                } 
                else {
                    addActionError("Username/Password doesn't match");
                    return INPUT;
                }
            }
        }
            
    }

    public String signup() {
        //Insert register logic
        session = getSession();
        tx = session.getTransaction();
        try{
            //check if user exists
            tx.begin();
            User tempUser = (User)session.get(User.class, user.getUsername().trim());
            if(tempUser == null){
                //no username found, can use it
                if(user.getPassword().trim().equals(user.getConfirmPassword().trim())){
                    
                    //check if passwords match
                    user.setPassword(de.sha256(user.getPassword()));
                    User use = (User)sessionmap.get("currentUser");
                    switch(use.getUserType()){
                        case "admin": user.setUserType("doctor"); break;
                        case "doctor": user.setUserType("assistant"); break;
                        default: addActionError("Invalid user access"); return INPUT;
                    }
                    session.saveOrUpdate(user);
                    tx.commit();
                    refresh();
                    return SUCCESS;
                }
                else{
                    //Passwords does not match
                    addFieldError("password", "Password does not match");
                    return INPUT;
                }
            }
            else{
                //username already used
                addFieldError("username", "Username is already used");
                return INPUT;
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
            if(tx!= null) tx.rollback();
        }
        finally{
            if(session!=null)session.close();
        }
        return INPUT;
    }

    public String changePassword() {
        session = getSession();
        tx = session.getTransaction();
        try {
            //fetch user from db using uname
            User currentUser = (User)session.get(User.class, user.getUsername());
            if(currentUser != null){
                currentUser = (User)session.load(User.class, user.getUsername());
                tx.begin();
                if(currentUser.getPassword().equals(de.sha256(user.getPassword()))){
                    if(de.sha256(user.getNewPassword()).equals(de.sha256(user.getConfirmPassword()))){
                        currentUser.setPassword(de.sha256(user.getNewPassword()));
                        tx.commit();
                        refresh();
                        return SUCCESS;
                    }
                    else{
                        addActionError("Passwords does not match");
                        return INPUT;
                    }
                }
                else{
                    addActionError("Incorrect Password");
                    return INPUT;
                }
            }
            //if username is not found on DB
            else{
                addActionError("User not found");
                return INPUT;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return SUCCESS;
    }

    public String checkUser(){
        initialize();
        session = getSession();
        System.out.println("["+user.getUsername()+"]");
        User db = (User)session.get(User.class, user.getUsername().trim());
        if(db != null){
            db = (User) session.load(User.class, user.getUsername().trim());
            putMap("tempUser", db);
            return SUCCESS;
        }
        else{
            addActionError("Username not found");
            return INPUT;
        }
 }

    public String logout() {

        sessionmap.invalidate();

        //renew servlet session
        sessionmap.put("renewServletSession", null);
        sessionmap.remove("renewServletSession");

        //populate the struts session
        sessionmap.entrySet();
        return SUCCESS;
    }
    
    public String forgotPassword(){
        session = getSession();
        tx = session.getTransaction();
        System.out.println("Input is " + user.toString());
        User db = (User)session.get(User.class, user.getUsername());
        if(db != null){
            try{
                db = (User)session.load(User.class, user.getUsername());
                if(user.getSecurityAnswer().trim().equalsIgnoreCase(db.getSecurityAnswer())){
                    tx.begin();
                    String pass = de.generateRandom(10);
                    db.setPassword(de.sha256(pass));
                    System.out.println("new Password is " + pass);
                    HttpServletRequest request = ServletActionContext.getRequest();
                    sessionmap.remove("tempUser");
                    request.setAttribute("tempPass", pass);
                    tx.commit();
                    return SUCCESS;
                }
                else{
                    addActionError("Incorrect answer");
                    return INPUT;
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
        else{
            addActionError("Username is not found!");
            return INPUT;
        }
    }
}
//user 
//	add user checkk
//	login   check   
//	logout  check
//	change pass check

//      forgot password
//	add sec question
//	change sec pass