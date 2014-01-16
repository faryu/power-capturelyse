package de.whs.mwa.pcl.rs;

import interfaces.EnergietypVerwaltungInterface;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.jboss.resteasy.annotations.Form;

import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.View;
import com.googlecode.htmleasy.ViewWith;

import entity.Energietyp;

@Path("/type")
public class EnergyTypeResource {
	@EJB
	private EnergietypVerwaltungInterface energieVerwaltung;

	@GET
	public View home() {
		return new View("/type.jsp", energieVerwaltung);
	}

	@GET
	@Path("/new")
	public View create() {
		return new View("/type_register.jsp");
	}

	@POST
	@Path("/new")
	@ViewWith("/type_register.jsp")
	public EnergyType create(@Form EnergyType type) {
		if (type.getName().length() < 2)
			type.addError("name", "Muss mindestens 2 Zeichen lang sein");
		if (type.errors == null) {
			Energietyp t = new Energietyp();
			t.setEnergietyp(type.getName());
			t.setEinheit(type.getUnit());
			t = energieVerwaltung.createEnergietyp(t);
			// Zähler hinzufügen
			throw new RedirectException("/type/");
		}
		return type;
	}
}
