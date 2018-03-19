package demo.security;

import static act.controller.Controller.Util.redirect;
import static act.controller.Controller.Util.render;

import act.app.ActionContext;
import org.osgl.aaa.NoAuthentication;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

@NoAuthentication
public class LoginController {
    @GetAction("/login")
    public void loginForm() {
        render("login.html");
    }

    @PostAction("/login")
    public void login(String username, char[] password, TupleUser.Dao userDao, ActionContext context) {
        TupleUser user = userDao.authenticate(username, password);
        if (user != null) {
            context.login(username);
            redirect("/");
        }
        context.flash().error("username/password pair not authenticated");
        redirect("/login");
    }
}
