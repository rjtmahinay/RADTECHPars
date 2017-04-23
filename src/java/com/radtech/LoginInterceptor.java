package com.radtech;

import java.util.Map;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor  extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        
        String login;
        if(invocation.getInvocationContext().getName().equals("login")){
            return invocation.invoke();
        }
        if(session.get("currentUser")== null && session.get("tempUser")== null){
            return Action.LOGIN;
        }
        else 
        {
            return invocation.invoke();
        }
    }
}