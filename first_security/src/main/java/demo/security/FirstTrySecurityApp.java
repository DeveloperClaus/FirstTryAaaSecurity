package demo.security;


import static act.controller.Controller.Util.redirect;
import static act.controller.Controller.Util.render;

import act.Act;
import act.app.ActionContext;
import org.osgl.aaa.AAA;
import org.osgl.aaa.NoAuthentication;
import org.osgl.mvc.annotation.GetAction;

public class FirstTrySecurityApp {

    @GetAction
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
