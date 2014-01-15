package interfaces;

import entity.Zaehler;

public interface ZaehlerVerwaltungInterface {
    
    public Zaehler createZaehler(Zaehler zaehler);
    public Zaehler findZaehler(int id_zaehler);
    public Zaehler updateZaehler(Zaehler zaehler);
    public void deleteZaehler(Zaehler zaehler);

}
