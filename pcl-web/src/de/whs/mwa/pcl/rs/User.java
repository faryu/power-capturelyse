package de.whs.mwa.pcl.rs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;

import com.googlecode.htmleasy.ViewWith;

@ViewWith("/user_register.jsp")
public class User {
	@FormParam("uname")
	String uname;
	@FormParam("password")
	String password;
	@FormParam("vname")
	String vname;
	@FormParam("name")
	String name;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public String getUname() { return uname; }

	Map<String, String> errors;

	public void addError(String key, String msg) {
		if (errors == null)
			errors = new HashMap<String, String>();
		errors.put(key, msg);
	}

}
