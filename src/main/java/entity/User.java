package entity;

public class User {
	private String login_id;
	private String pass;
	private String name;
	
	public User() {
	}
	public User(String login_id, String pass, String name) {
		this.login_id = login_id;
		this.pass = pass;
		this.name = name;
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
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
