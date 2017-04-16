package com.radtech;

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
        //insert crypto
        //check for temporary password
        try {
            //if admin, just get in for now
            if(user.getUsername().trim().equals("admin")){
                user.setUserType(user.getPassword());
                sessionmap.put("currentUser", user);
                System.out.println("User is " + user.toString());
                
                return SUCCESS;
            }
            else{
                //check if user exists
                
                session = getSession();
                User db = (User) session.get(User.class, user.getUsername());
                if (db == null) {
                    addActionError("Username or password does not match");
                    return INPUT;
                } else {
                    if (db.getUsername().equalsIgnoreCase(user.getUsername())) {
                        if (db.getPassword().equals(user.getPassword())) {
                            refreshUser(db);
                            refresh();
                            return SUCCESS;
                        } else {
                            addActionError("Username/Password doesn't match");
                            return INPUT;
                        }
                    } else {
                        addActionError("Username/Password doesn't match");
                        return INPUT;
                    }
                }
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ERROR;
    }

    public String signup() {
        //Insert register logic
        session = getSession();
        tx = session.getTransaction();
        try{
            //check if user exists
            User tempUser = (User)session.get(User.class, user.getUsername().trim());
            if(tempUser == null){
                //no username found, can use it
                if(user.getPassword().trim().equals(user.getConfirmPassword().trim())){
                    //check if passwords match
                    user.setPassword("" + user.getConfirmPassword().trim().hashCode());
                    tx.begin();
                    session.save(user);
                    tx.commit();
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
            User currentUser = (User)session.load(User.class, user.getUsername());
            if(currentUser != null){
                if(currentUser.getPassword().equals("" + user.getPassword().trim().hashCode())){
                    if(user.getNewPassword().equals(user.getConfirmPassword())){
                        currentUser.setPassword("" + user.getNewPassword().trim().hashCode());
                        tx.begin();
                        session.merge(currentUser);
                        tx.commit();
                        refreshUser(currentUser);
                        user = null;
                        return SUCCESS;
                    }
                    else{
                        //passwords does not match
                        addFieldError("password2", "Passwords does not match.");
                        return INPUT;
                    }
                }
                else{
                    //password is incorrect
                    addFieldError("password", "Password is incorrect.");
                    return INPUT;
                }
            }
            //if username is not found on DB
            else{
                addFieldError("password", "Critical Error: Code 5");
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

//    public String forgotPassword() {
//        if (!(user.getUsername().trim().equals("")) & user != null) {
//            System.out.println(user.getUsername());
//            Session session = null;
//            Transaction tx = null;
//            try {
//                session = ((SessionFactory) sessionmap.get("factory")).openSession();
//                System.out.println(user.getUsername());
//                user = (User) session.get(User.class, user.getUsername());
//                System.out.println("Users' " + (user == null));
//                if (user == null) {
//                    addFieldError("username", "Username not found");
//                    return INPUT;
//                } else {
//                    user = (User) session.load(User.class, user.getUsername());
//                    List questions = new ArrayList();
//                    for (SecurityQuestion sq : user.getSQuestions()) {
//                        System.out.println(sq.getQuestion());
//                        questions.add(sq.getQuestion());
//                    }
//                    sessionmap.put("tempUser", user);
//                    return SUCCESS;
//                }
//            } catch (HibernateException e) {
//                tx.rollback();
//            } finally {
//                if (session != null) {
//                    session.close();
//                }
//            }
//        }
//        addFieldError("username", "Something happened midway...");
//        return INPUT;
//    }

    public String logout() {

        sessionmap.invalidate();

        //renew servlet session
        sessionmap.put("renewServletSession", null);
        sessionmap.remove("renewServletSession");

        //populate the struts session
        sessionmap.entrySet();
        return SUCCESS;
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