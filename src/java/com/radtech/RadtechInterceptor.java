
package com.radtech;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.StrutsStatics;

/**
 *
 * @author Aspire
 */
public class RadtechInterceptor extends AbstractInterceptor{

    private ActionContext contxt = null;
    private HttpServletResponse res = null;
    
    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        
        contxt = ai.getInvocationContext();
        res = (HttpServletResponse) contxt.get(StrutsStatics.HTTP_RESPONSE);
        
            if(res != null){
                res.setHeader("Cache-control", "no-cache, no-store");
                res.setHeader("Pragma", "no-cache");
                res.setDateHeader("Expires", 0);
                res.setHeader("Vary", "*");
            }
        return ai.invoke();
    }
    
}
