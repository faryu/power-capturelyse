package interfaces;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;

import entity.Verbrauch;

@Remote
public interface VerbrauchVerwaltungInterface {
    
    public Verbrauch createVerbrauch(Verbrauch verbrauch);
    public void updateVerbrauch(Verbrauch verbrauch);
    public void deleteVerbrauch(Verbrauch verbrauch);
    public List<Verbrauch> getVerbraeuche(int id_adresse);
    public List<Verbrauch> getVerbraeucheAuswahl(int id_adresse, int id_energietyp, Timestamp datumVon, Timestamp datumBis);
    

}
