package view;

public class IdPass {

	private String id;
	private String pass;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public IdPass(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}
	@Override
	public String toString() {
		return id+"&&"+pass;
	}
	
}
