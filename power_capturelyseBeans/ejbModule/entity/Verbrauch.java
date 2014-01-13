package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

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
    
    @ManyToOne    
    @JoinColumn(name="id_zaehler", referencedColumnName="id_zaehler", nullable=false, columnDefinition="INTEGER(11)")		
    private Zaehler zaehler;
    
    @Column(nullable=false, precision=14, scale=2)
    private BigDecimal zaehlerstand;    
    @Column(nullable=false)
    private Date datum;           
    
       
    public Verbrauch(){
    	
    }
    
    public Verbrauch(Zaehler zaehler, BigDecimal zaehlerstand, Date datum){
	this.zaehler = zaehler;
	this.datum = datum;
	this.zaehlerstand = zaehlerstand;
	
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
    
    public Zaehler getZaehler() {
        return zaehler;
    }

    public void setZaehler(Zaehler zaehler) {
        this.zaehler = zaehler;
    }
    
    public BigDecimal getZaehlerstand() {
        return zaehlerstand;
    }

    public void setZaehlerstand(BigDecimal zaehlerstand) {
        this.zaehlerstand = zaehlerstand;
    }

    @Temporal(TemporalType.DATE)   
    public Date getDatum() {
        return datum;
    }
    
    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }
    
    
    
    



    

}
