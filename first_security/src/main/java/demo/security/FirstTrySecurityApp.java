package demo.security;


import static act.controller.Controller.Util.render;
import static act.controller.Controller.Util.redirect;

import org.osgl.aaa.AAA;
import org.osgl.aaa.NoAuthentication;
import org.osgl.aaa.RequireAuthentication;
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

    @GetAction("/back")
    @NoAuthentication
    public void back(ActionContext context) {
    	context.logout();
    	
    	redirect("/");
    }

    @GetAction("/restrict")
    @NoAuthentication
    public void restrict(ActionContext context) {

//    	AAA.requirePermission("testpermission");
    	boolean hasPermission = AAA.hasPermission("testpermission");
    	if(!hasPermission) {
    		context.param("restrictmessage", "sorry, no permission");
    		redirect("/");
    	}
    	
    	render("restrict.html");
    }


    public static void main(String[] args) throws Exception {
        Act.start();
    }
}
