package de.whs.mwa.pcl.rs;

import interfaces.AdressVerwaltungInterface;
import interfaces.UserVerwaltungInterface;
import interfaces.VerbrauchVerwaltungInterface;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.jboss.resteasy.annotations.Form;
import org.jboss.security.auth.callback.UsernamePasswordHandler;

import com.googlecode.htmleasy.View;
import com.googlecode.htmleasy.ViewWith;

@Path("/user")
public class UserResource {
	@EJB
	private static UserVerwaltungInterface userverwaltung;

	@EJB
	private static VerbrauchVerwaltungInterface verbrauchsverwaltung;

	@EJB
	private static AdressVerwaltungInterface adresseverwaltung;

	@GET
	@Path("/register")
	public View registerUserForm() {
		return new View("/user_register.jsp");
	}
	
	@GET
	@Path("/register/{uname}")
	@ViewWith("/user_register.jsp")
	public User registerUserForm(@PathParam("uname") String uname)
	{
		User user = new User();
		user.uname = uname;
		if(userverwaltung.exists(uname))
			user.addError("uname", "Username bereits vergeben");
		return user;
	}

	@POST
	@Path("/register")
	@ViewWith("/user_register.jsp")
	public User createUser(@Form User user) {
		if(userverwaltung.exists(user.uname))
			user.addError("uname", "Username bereits vergeben");
		if(user.uname == null || user.uname.length() < 3)
			user.addError("uname", "username muss mindestens 4 Zeichen lang sein!");
		if(user.errors.size() == 0)
		{
			userverwaltung.createUser(new entity.User(user.fname, user.name, user.uname, user.password));
		}
		return user;
	}
}