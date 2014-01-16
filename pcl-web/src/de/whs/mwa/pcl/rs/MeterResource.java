package de.whs.mwa.pcl.rs;

import interfaces.ZaehlerVerwaltungInterface;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.ViewWith;

import entity.Zaehler;

@Path("/meter")
public class MeterResource {
	@EJB
	private ZaehlerVerwaltungInterface zaehlerVerwaltung;
	
	@GET
	@Path("{id}")
	@ViewWith("/meter.jsp")
	public Zaehler view(@PathParam("id") int id, @Context HttpServletRequest request)
	{
		entity.User user = HomeResource.currentUser(request);
		Zaehler zaehler = zaehlerVerwaltung.findZaehler(id);
		if(zaehler == null || !zaehler.getAdresse().getUser().equals(user))
			throw new RedirectException("/");
		return zaehler;
	}
}
