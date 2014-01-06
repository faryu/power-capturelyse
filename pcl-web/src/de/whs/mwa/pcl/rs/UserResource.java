package de.whs.mwa.pcl.rs;

import helper.Helper;
import interfaces.AdressVerwaltungInterface;
import interfaces.SessionRemoteInterface;
import interfaces.UserVerwaltungInterface;
import interfaces.VerbrauchVerwaltungInterface;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.jboss.resteasy.annotations.Form;

import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.View;
import com.googlecode.htmleasy.ViewWith;

@Path("/user")
public class UserResource {
	@EJB
	private UserVerwaltungInterface userverwaltung;

	@EJB
	private VerbrauchVerwaltungInterface verbrauchsverwaltung;

	@EJB
	private AdressVerwaltungInterface adresseverwaltung;
	
	@EJB
	private SessionRemoteInterface sessionBean;

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
			user.addError("uname", "username muss mindestens 3 Zeichen lang sein!");
		if(user.password == null || user.password.length() < 4)
			user.addError("password", "Passwort muss mindestens 4 Zeichen lang sein!");
		if(user.errors == null)
		{
			userverwaltung.createUser(new entity.User(user.fname, user.name, user.uname, Helper.md5Java(user.password)));
			throw new RedirectException("/user/login/" + user.uname);
		}
		return user;
	}
	
	@GET
	@Path("/login")
	public View login()
	{
		return new View("/user_login.jsp");
	}
	
	@GET
	@Path("/login/{uname}")
	@ViewWith("/user_login.jsp")
	public User login(@PathParam("uname") final String uname)
	{
		User user = new User();
		user.uname = uname;
		return user;
	}
	
	@POST
	@Path("/login")
	@ViewWith("/user_login.jsp")
	public User login(@Form User user, @Context HttpServletRequest request)
	{
		if(!userverwaltung.exists(user.uname))
			user.addError("uname", "User existiert nicht");
		if(user.errors == null)
		{
			// User login hinzufügen
			
			if(sessionBean.identityCheck(user.uname, user.password))
			{				
				HttpSession session = request.getSession();
				session.setAttribute("pcl-session", sessionBean);
				throw new RedirectException("/home");
			}
			else
			{
				user.addError("password", "Passwort falsch!");
			}
			
		}
		return user;
	}
}
