package demo.security;

public class TupleUser {

	private String m_userId;
	private String m_password;
	
	public TupleUser(String pUserId, String pPassword) {

		setUserId(pUserId);
	}
	
	public String getUserId() {
		return m_userId;
	}
	
	public void setUserId(String pUserId) {
		m_userId = pUserId;
	}
	
	public String getPassword() {
		return m_password;
	}
	
	public void setPassword(String pPassword) {
		m_password = pPassword;
	}
	
}
