package de.whs.mwa.pcl.rs;

import interfaces.EnergietypVerwaltungInterface;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;

public class Meter {
	@FormParam("id")
	private int id;
	@FormParam("name")
	private String name;
	@FormParam("type")
	private int type;	
	
	private int aid;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	private EnergietypVerwaltungInterface energieTypVerwaltung;
	
	public EnergietypVerwaltungInterface getEnergieTypVerwaltung() {
		return energieTypVerwaltung;
	}

	public void setEnergieTypVerwaltung(
			EnergietypVerwaltungInterface energieTypVerwaltung) {
		this.energieTypVerwaltung = energieTypVerwaltung;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	Map<String, String> errors;

	public void addError(String key, String msg) {
		if (errors == null)
			errors = new HashMap<String, String>();
		errors.put(key, msg);
	}
}
