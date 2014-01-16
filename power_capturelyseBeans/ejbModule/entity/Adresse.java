package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


		
	

@Entity
@Table(name="tb_adresse")
public class Adresse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8411139256289721253L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_adresse")
	private int id_adresse;
	@Column(name="strasse", nullable=false, length=45)
	private String strasse;
	@Column(name="plz", nullable=false)
	private int plz;
	@Column(name="ort", nullable=false, length=45)
	private String ort;
	
	@ManyToOne
	@JoinColumn(name="id_user", referencedColumnName="id_user", nullable=false, columnDefinition="INTEGER(11)")			
	private User user;

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "adresse")
	private Set<Zaehler> zaehler;
	
	

	public Adresse(){
		zaehler = new HashSet<Zaehler>();
		
	}
	
	
	public Adresse(String strasse, int plz, String ort, User user){	    
	    this.strasse = strasse;
	    this.plz = plz;
	    this.ort = ort;
	    this.user = user;
	    user.getAdresse().add(this);
	    	    
	}
	
	public int getId_adresse() {
		return id_adresse;
	}


	public void setId_adresse(int id_adresse) {
		this.id_adresse = id_adresse;
	}


	
	public String getStrasse() {
		return strasse;
	}


	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}


	
	public int getPlz() {
		return plz;
	}


	public void setPlz(int plz) {
		this.plz = plz;
	}


	
	public String getOrt() {
		return ort;
	}


	public void setOrt(String ort) {
		this.ort = ort;
	}

	@Column(name="id_user", nullable=false, columnDefinition="INTEGER(11)")
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
		
	public Set<Zaehler> getZaehler() {
		return zaehler;
	}

	public void setZaehler(Set<Zaehler> zaehler) {
		this.zaehler = zaehler;
	}

}
