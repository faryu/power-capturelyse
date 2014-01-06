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
	@FormParam("fname")
	String fname;
	@FormParam("name")
	String name;

	Map<String, String> errors;

	public void addError(String key, String msg) {
		if (errors == null)
			errors = new HashMap<String, String>();
		errors.put(key, msg);
	}

}
