package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_verbrauch")
public class Verbrauch implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 8910547784460812467L;
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_verbrauch;
    @Column(name="id_adresse", nullable=false, columnDefinition="INTEGER(11)")    
    @JoinColumn(name="id_adresse", referencedColumnName="id_adresse")		
    private Adresse adresse;
    @ManyToOne
    @JoinColumn(name="id_energietyp", referencedColumnName="id_energietyp", nullable=false, columnDefinition="INTEGER(11)")	
    private Energietyp engergietyp;
    @Column(nullable=false, precision=14, scale=2)
    private BigDecimal zaehlerstand;    
    @Column(nullable=false)
    private Timestamp datum;           
    
       
    public Verbrauch(){
    	
    }
    
    public Verbrauch(Adresse adresse, BigDecimal zaehlerstand, Timestamp datum, Energietyp energietyp){
	this.adresse = adresse;
	this.datum = datum;
	this.zaehlerstand = zaehlerstand;
	this.engergietyp = energietyp;
	
    }
    
    public Verbrauch(BigDecimal zaehlerstand, Timestamp datum){		
	this.datum = datum;
	this.zaehlerstand = zaehlerstand;	
    }
    
    public int getId_verbrauch() {
        return id_verbrauch;
    }

    public void setId_verbrauch(int id_verbrauch) {
        this.id_verbrauch = id_verbrauch;
    }
    
    public Adresse getAadresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    
    public Energietyp getEngergietyp() {
        return engergietyp;
    }

    public void setEngergietyp(Energietyp engergietyp) {
        this.engergietyp = engergietyp;
    }
    
    public BigDecimal getZaehlerstand() {
        return zaehlerstand;
    }

    public void setZaehlerstand(BigDecimal zaehlerstand) {
        this.zaehlerstand = zaehlerstand;
    }

    @Temporal(TemporalType.TIMESTAMP)   
    public Timestamp getDatum() {
        return datum;
    }
    
    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }
    
    
    
    



    

}
