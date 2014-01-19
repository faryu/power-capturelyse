package de.whs.mwa.pcl.rs;

import interfaces.VerbrauchVerwaltungInterface;
import interfaces.ZaehlerVerwaltungInterface;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.jboss.resteasy.annotations.Form;

import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.ViewWith;

import entity.Verbrauch;
import entity.Zaehler;

@Path("/meter")
public class MeterResource {
	@EJB
	private ZaehlerVerwaltungInterface zaehlerVerwaltung;

	@EJB
	private VerbrauchVerwaltungInterface verbrauchVerwaltung;

	@GET
	@Path("{id}")
	@ViewWith("/meter.jsp")
	public Zaehler view(@PathParam("id") int id,
			@Context HttpServletRequest request) {
		entity.User user = HomeResource.currentUser(request);
		Zaehler zaehler = zaehlerVerwaltung.findZaehler(id);
		if (zaehler == null || !zaehler.getAdresse().getUser().equals(user))
			throw new RedirectException("/");
		return zaehler;
	}

	@POST
	@Path("{id}/add_reading")
	public void addReading(@PathParam("id") int id,
			@FormParam("reading") BigDecimal reading,
			@FormParam("date") String date, @Context HttpServletRequest request) {
		entity.User user = HomeResource.currentUser(request);
		Zaehler zaehler = zaehlerVerwaltung.findZaehler(id);
		if (zaehler == null || !zaehler.getAdresse().getUser().equals(user))
			throw new RedirectException("/");

		try {
			Date d = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(date);
			Verbrauch v = new Verbrauch();
			v.setZaehlerstand(reading);
			v.setDatum(d);
			v.setZaehler(zaehler);
			zaehler.getVerbrauch().add(v);

			verbrauchVerwaltung.createVerbrauch(v);
			throw new RedirectException("/meter/" + id);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			throw new RedirectException("/");
		}
	}

	@GET
	@Path("{id}/analyse")
	@ViewWith("/analyse.jsp")
	public Analyse analyse(@PathParam("id") int id,
			@Context HttpServletRequest request) {
		entity.User user = HomeResource.currentUser(request);
		Zaehler zaehler = zaehlerVerwaltung.findZaehler(id);
		if (zaehler == null || !zaehler.getAdresse().getUser().equals(user))
			throw new RedirectException("/");

		Analyse analyse = new Analyse();
		analyse.setFrom(dateFormat().format(new Date()));
		analyse.setTo(dateFormat().format(new Date()));
		analyse.setZaehler(zaehler);

		return analyse;
	}

	@POST
	@Path("{id}/analyse")
	@ViewWith("/analyse.jsp")
	public Analyse analyse(@PathParam("id") int id, @Form Analyse analyse,
			@Context HttpServletRequest request) {
		entity.User user = HomeResource.currentUser(request);
		Zaehler zaehler = zaehlerVerwaltung.findZaehler(id);
		if (zaehler == null || !zaehler.getAdresse().getUser().equals(user))
			throw new RedirectException("/");

		analyse.setZaehler(zaehler);

		try {
			Date from = dateFormat().parse(analyse.getFrom());
			Date to = dateFormat().parse(analyse.getTo());
			analyse.setVerbraeuche(verbrauchVerwaltung.getZaehlerstaendeAuswahl(id, from, to));
			analyse.setAvg(verbrauchVerwaltung.showTagesVerbrauchImIntervall(id, from, to));
			analyse.setTotal(verbrauchVerwaltung.showGesamtVerbrauchImIntervall(id, from, to));
			
		} catch (ParseException e) {

		}

		return analyse;
	}

	public static SimpleDateFormat dateFormat() {
		return new SimpleDateFormat("dd.MM.yyyy HH:mm");
	}
}
