package client;

import javax.ejb.EJB;
import javax.naming.NamingException;

import entity.Adresse;
import entity.User;
import serviceLocator.ServiceLocator;
import interfaces.AdressVerwaltungInterface;
import interfaces.UserVerwaltungInterface;
import interfaces.VerbrauchVerwaltungInterface;


public class SimpleClient {

	@EJB
    private static UserVerwaltungInterface userverwaltung;
    
    @EJB
    private static VerbrauchVerwaltungInterface verbrauchsverwaltung;
    
    @EJB 
    private static AdressVerwaltungInterface adresseverwaltung;
	
	public static void main(String[] args) throws NamingException {


    	ServiceLocator locator = new ServiceLocator();
    	userverwaltung = (UserVerwaltungInterface)locator.getStateless("", "power_capturelyseBeans", "UserVerwaltungBean", UserVerwaltungInterface.class);
    	ServiceLocator locator2 = new ServiceLocator();
    	verbrauchsverwaltung = (VerbrauchVerwaltungInterface)locator2.getStateless("", "power_capturelyseBeans", "VerbrauchVerwaltungBean", VerbrauchVerwaltungInterface.class);
    	User user = new User("Omar", "Hany", "h1411893");
    	//Adresse adresse = new Adresse();
    	Adresse adresse = new Adresse("Vardingholter Str. 15", 46414, "Rhede", user);
    	//Verbrauch verbrauch = new Verbrauch();
    	
    	user = userverwaltung.createUser(user,adresse);
//    	adresse = userverwaltung.findAdresse(1);
    	OutputHelper.output(userverwaltung.findUserAdresse(1));   	
//    	OutputHelper.output(verbrauchsverwaltung.getVerbraeuche(1));
//    	int id_user = user.getId_user();
//    	System.out.println(userverwaltung.findUser(id_user));
    		    	
//    	System.out.println(id_user);
//    	System.out.println(adresse);

	}
	

}
