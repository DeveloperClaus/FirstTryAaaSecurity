package demo.security;

import java.util.Set;

import org.osgl.util.C;

import act.aaa.ActAAAService;

public class FirstTryAaaSecurity extends ActAAAService.Base<TupleUser> {

    private static final Set<String> DEFAULT_PERMS = C.set("testpermission");

    @Override
    protected Set<String> permissionsOf(TupleUser user) {
        return DEFAULT_PERMS;
    }
    
    @Override
    protected boolean verifyPassword(TupleUser user, char[] password) {
    	return true;
    }
}
