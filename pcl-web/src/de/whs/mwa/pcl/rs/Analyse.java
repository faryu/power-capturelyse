package de.whs.mwa.pcl.rs;

import java.util.List;

import javax.ws.rs.FormParam;

import entity.Verbrauch;
import entity.Zaehler;

public class Analyse {
	@FormParam("from")
	private String from;
	
	@FormParam("to")
	private String to;
	
	private Zaehler zaehler;
	private List<Verbrauch> verbraeuche;
	private double avg;
	private double total;
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public List<Verbrauch> getVerbraeuche() {
		return verbraeuche;
	}
	public void setVerbraeuche(List<Verbrauch> verbraeuche) {
		this.verbraeuche = verbraeuche;
	}
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
