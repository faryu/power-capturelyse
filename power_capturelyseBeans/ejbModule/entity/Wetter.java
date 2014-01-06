package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="tb_wetter")
public class Wetter implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 268678410474092103L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_wetter;
    @Column(nullable=false) private Timestamp datum;
    @Column(nullable=false, length=10) private int plz;
    @Column(nullable=false, precision=5, scale=2) private BigDecimal temp;
    
    
    public Wetter(int plz, BigDecimal temp, Timestamp datum)
    {       	
	    this.datum = datum;
	    this.plz = plz;
	    this.temp = temp;	    	    
	}
    
    public int getId_wetter() {
        return id_wetter;
    }

    public void setId_wetter(int id_wetter) {
        this.id_wetter = id_wetter;
    }


    @Temporal(TemporalType.TIMESTAMP)
    public Timestamp getDatum() {
        return datum;
    }


    
    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }


    
    public int getPlz() {
        return plz;
    }



    public void setPlz(int plz) {
        this.plz = plz;
    }


    
    public BigDecimal getTemp() {
        return temp;
    }



    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }



    public Wetter(){
	
    }
    

}
