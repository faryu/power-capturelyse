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
import javax.persistence.Table;

@Entity
@Table(name="tb_zaehler")
public class Zaehler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2767752979144686132L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_zaehler;
	
	@Column(name="zaehlername",nullable=false, length=45)
	private String zaehlername;
	
	@ManyToOne
        @JoinColumn(name="id_adresse", referencedColumnName="id_adresse", nullable=false, columnDefinition="INTEGER(11)")	
        private Adresse adresse;
	
	@ManyToOne
        @JoinColumn(name="id_energietyp", referencedColumnName="id_energietyp", nullable=false, columnDefinition="INTEGER(11)")	
        private Energietyp energietyp;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "zaehler")
	private Set<Verbrauch> verbrauch;
	
	public Zaehler(){
	    verbrauch = new HashSet<Verbrauch>();
	    
	}
	
	public Zaehler(Adresse adresse, Energietyp energietyp){
	    this.adresse = adresse;
	    this.energietyp = energietyp;
	}

	public int getId_zaehler() {
	    return id_zaehler;
	}

	public void setId_zaehler(int id_zaehler) {
	    this.id_zaehler = id_zaehler;
	}

	public String getZaehlername() {
	    return zaehlername;
	}

	public void setZaehlername(String zaehlername) {
	    this.zaehlername = zaehlername;
	}

	public Adresse getAdresse() {
	    return adresse;
	}

	public void setAdresse(Adresse adresse) {
	    this.adresse = adresse;
	}

	public Energietyp getEnergietyp() {
	    return energietyp;
	}

	public void setEnergietyp(Energietyp energietyp) {
	    this.energietyp = energietyp;
	}

	public Set<Verbrauch> getVerbrauch() {
	    return verbrauch;
	}

	public void setVerbrauch(Set<Verbrauch> verbrauch) {
	    this.verbrauch = verbrauch;

	}
	
	
}

