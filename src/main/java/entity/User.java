package entity;

public class User {
	private String login_id;
	private String pass;
	
	public User() {
	}
	public User(String login_id, String pass) {
		this.login_id = login_id;
		this.pass = pass;
	}
	
	public void setLoginId(String login_id) {
		this.login_id = login_id;
	}
	public String getLoginId() {
		return login_id;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPass() {
		return pass;
	}
}
