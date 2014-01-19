package interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.Verbrauch;

@Remote
public interface VerbrauchVerwaltungInterface {
    
    public Verbrauch createVerbrauch(Verbrauch verbrauch);
    public void updateVerbrauch(Verbrauch verbrauch);
    public void deleteVerbrauch(Verbrauch verbrauch);

    public List<Verbrauch> getZaehlerstaende(int id_zaehler);
    public List<Verbrauch> getZaehlerstaendeAuswahl(int id_zaehler, Date datumVon, Date datumBis);
    public Double showGesamtVerbrauchImIntervall(int id_zaehler, Date datumVon,Date datumBis) throws ParseException;
    public Double showTagesVerbrauchImIntervall(int id_zaehler, Date datumVon,Date datumBis) throws ParseException;
//    public List<Object[]> showVerbraeucheAVG(int id_zaehler, Date datumVon,	Date datumBis);

    

}
