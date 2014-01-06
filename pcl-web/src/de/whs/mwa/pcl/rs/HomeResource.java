package de.whs.mwa.pcl.rs;

import interfaces.AdressVerwaltungInterface;
import interfaces.SessionRemoteInterface;
import interfaces.VerbrauchVerwaltungInterface;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.ViewWith;

@Path("/home")
public class HomeResource {
	@EJB
	private VerbrauchVerwaltungInterface verbrauchsverwaltung;

	@EJB
	private AdressVerwaltungInterface adresseverwaltung;
	
	@GET
	@ViewWith("/home.jsp")
	public Home home(@Context HttpServletRequest request)
	{
		System.out.println("called");
		HttpSession session = request.getSession();
		SessionRemoteInterface sessionBean = (SessionRemoteInterface) session.getAttribute("pcl-session");
		if(sessionBean == null)
			throw new RedirectException("/user/login");
		Home home = new Home();
		home.uname = sessionBean.getUser().getLoginname();
		home.vname = sessionBean.getUser().getUservname();
		home.name = sessionBean.getUser().getUsername();
		return home;
	}
}
