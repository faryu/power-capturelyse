package de.whs.mwa.pcl.rs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;

public class Address {
	@FormParam("id")
	private int id_adresse;
	@FormParam("strasse")
	private String strasse;
	@FormParam("plz")
	private int plz;
	@FormParam("ort")
	private String ort;
	
	public int getId_adresse() {
		return id_adresse;
	}

	public String getStrasse() {
		return strasse;
	}

	public int getPlz() {
		return plz;
	}

	public String getOrt() {
		return ort;
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
