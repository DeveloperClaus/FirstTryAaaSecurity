package demo.security;

import act.cli.Command;
import act.cli.Required;
import act.db.morphia.MorphiaAdaptiveRecord;
import act.db.morphia.MorphiaDao;
import act.validation.Password;

public class TupleUser extends MorphiaAdaptiveRecord<TupleUser> {

	public String userId;
	@Password
	public char[] password;


	public static class Dao extends MorphiaDao<TupleUser> {

        @Command(name = "user.create", help = "Create new user")
        public void createUser(
                @Required("specify username") String username,
                @Required("specify password") char[] password
        ) {
            TupleUser user = new TupleUser();
            user.userId = username;
            user.password = password;
            save(user);
        }

        public TupleUser findByUsername(String username) {
            return findOneBy("userId", username);
        }

        public TupleUser authenticate(String username, char[] password) {
            TupleUser user = findByUsername(username);
            return null != user ? Password.Verifier.verifyPassword(password, user) ? user : null : null;
        }

    }
}
