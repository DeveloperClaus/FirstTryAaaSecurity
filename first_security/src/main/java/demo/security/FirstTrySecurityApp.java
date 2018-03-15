package demo.security;


import static act.controller.Controller.Util.render;

import org.osgl.aaa.AAA;
import org.osgl.aaa.NoAuthentication;
import org.osgl.mvc.annotation.GetAction;

import act.Act;
import act.app.ActionContext;

public class FirstTrySecurityApp {

    @GetAction
    //@NonBlock
    //@NoImplicitTemplateVariable
    //@SessionFree
    @NoAuthentication
    public void home() {
    	
        render("home.html");
    }
    
    @GetAction("/add")
    @NoAuthentication
    public void add(ActionContext context) {
    	context.loginAndRedirect("testuser", "/");
    }

    @GetAction("/restrict")
    public void restrict() {
    	
    	AAA.requirePermission("testpermission");
    	
    	render("restrict.html");
    }


    public static void main(String[] args) throws Exception {
        Act.start();
    }
}
