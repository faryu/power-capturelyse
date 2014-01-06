package de.whs.mwa.pcl.rs;

import interfaces.AdressVerwaltungInterface;

import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.jboss.resteasy.annotations.Form;

import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.View;
import com.googlecode.htmleasy.ViewWith;

import entity.Adresse;

@Path("/address")
public class AddressResource {
	@EJB
	private AdressVerwaltungInterface adressverwaltung;
	
	@GET
	@Path("/new")
	public View create(@Context HttpServletRequest request)
	{
		HomeResource.currentUser(request);
		return new View("/address_register.jsp");
	}
	
	@POST
	@Path("/new")
	@ViewWith("/address_register.jsp")
	public Address create(@Form Address address, @Context HttpServletRequest request)
	{
		entity.User user = HomeResource.currentUser(request);
		
		if(address.getStrasse().length() < 4)
			address.addError("strasse", "Gültigen Strassennamen eingeben");
		if(address.getPlz() < 10000 || address.getPlz() > 99999)
			address.addError("plz", "Ungültige Postleitzahl");
		if(address.getOrt().length() < 3)
			address.addError("ort", "Ort muss mindestens 3 Zeichen haben");
		if(address.errors == null)
		{
			Adresse adresse = new Adresse(address.getStrasse(), address.getPlz(), address.getOrt(), user);
			adresse = adressverwaltung.addAdresse(adresse);
			throw new RedirectException("/address/" + Integer.toString(adresse.getId_adresse()));
		}
		return address;
	}
	
	@GET
	@Path("{id}")
	@ViewWith("/address.jsp")
	public Adresse show(@PathParam("id") int id, @Context HttpServletRequest request)
	{
		Adresse adresse = adressverwaltung.findAdresse(id);
		if(adresse == null)
			throw new RedirectException("");
		entity.User user = HomeResource.currentUser(request);
		if(!user.equals(adresse.getUser()))
			throw new RedirectException("/home");
		
		return adresse;
	}
}
