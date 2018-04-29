package demo.security;

import javax.persistence.Entity;
import javax.persistence.Table;

import act.cli.Command;
import act.cli.Required;
import act.db.ebean.EbeanDao;
import act.validation.Password;

@Entity
@Table(name = "USER")
public class TupleUser {

	public String userId;
	@Password
	public char[] password;


	public static class Dao extends EbeanDao<String, TupleUser> {

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
