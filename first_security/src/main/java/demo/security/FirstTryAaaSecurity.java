package demo.security;

import act.aaa.ActAAAService;
import act.validation.Password;
import org.osgl.util.C;

import java.util.Set;

public class FirstTryAaaSecurity extends ActAAAService.Base<TupleUser> {

    private static final Set<String> DEFAULT_PERMS = C.set("testpermission");

    @Override
    protected Set<String> permissionsOf(TupleUser user) {
        return DEFAULT_PERMS;
    }
    
    @Override
    protected boolean verifyPassword(TupleUser user, char[] password) {
    	return Password.Verifier.verifyPassword(password, user);
    }
}
