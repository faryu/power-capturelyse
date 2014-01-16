package de.whs.mwa.pcl.rs;

import javax.ws.rs.FormParam;

import entity.Zaehler;

public class Analyse {
	@FormParam("from")
	private String from;
	
	@FormParam("to")
	private String to;
	
	private Zaehler zaehler;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Zaehler getZaehler() {
		return zaehler;
	}
	public void setZaehler(Zaehler zaehler) {
		this.zaehler = zaehler;
	}
}
