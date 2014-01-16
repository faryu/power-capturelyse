package interfaces;

import javax.ejb.Remote;

import entity.Adresse;


@Remote
public interface AdressVerwaltungInterface {
    
    public Adresse addAdresse(Adresse adresse);
    public Adresse findAdresse(int id_adresse);
    public Adresse findAdressByUser(int id_user);    
    public void updateAdresse(Adresse adresse);
    

}
