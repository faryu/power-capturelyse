package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_energietyp")
public class Energietyp implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 8566587793818247515L;
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_energietyp;
    @Column(nullable=false, length=45) private String energietyp;
    @Column(nullable=false, length=45) private String einheit;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id_engergietyp")
    private Set<Verbrauch> verbrauch;
    
    

    
	public int getId_energietyp() {
        return id_energietyp;
    }


    public void setId_energietyp(int id_energietyp) {
        this.id_energietyp = id_energietyp;
    }

    	
	public String getEnergietyp() {
		return energietyp;
	}
	
	
	public void setEnergietyp(String energietyp) {
		this.energietyp = energietyp;
	}

	
	public String getEinheit() {
		return einheit;
	}

	public void setEinheit(String einheit) {
		this.einheit = einheit;
	}
	
	
	public Set<Verbrauch> getVerbrauch() {
	        return verbrauch;
	}

	public void setVerbrauch(Set<Verbrauch> verbrauch) {
	        this.verbrauch = verbrauch;
	}

	public Energietyp(){
	
	}
	
	

}
