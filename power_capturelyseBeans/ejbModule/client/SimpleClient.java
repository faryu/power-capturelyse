package client;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.naming.NamingException;

import entity.Adresse;
import entity.User;
import entity.Verbrauch;
import serviceLocator.ServiceLocator;
import sessionBeans.AdressVerwaltungBean;
import sessionBeans.UserVerwaltungBean;
import sessionBeans.VerbrauchVerwaltungBean;
import interfaces.AdressVerwaltungInterface;
import interfaces.UserVerwaltungInterface;
import interfaces.VerbrauchVerwaltungInterface;

public class SimpleClient {

    @EJB
    private static UserVerwaltungInterface userverwaltung;

    @EJB
    private static VerbrauchVerwaltungInterface verbrauchsverwaltung;

    @EJB
    private static AdressVerwaltungInterface adressverwaltung;

    public static void main(String[] args) throws NamingException,
	    ParseException {

	ServiceLocator locator = new ServiceLocator();
	String beanName = UserVerwaltungBean.class.getSimpleName();
	userverwaltung = (UserVerwaltungInterface) locator.getStateless("pcl-eap", "power_capturelyseBeans", beanName, UserVerwaltungInterface.class);

	ServiceLocator locator2 = new ServiceLocator();
	beanName = VerbrauchVerwaltungBean.class.getSimpleName();
	verbrauchsverwaltung = (VerbrauchVerwaltungInterface) locator2.getStateless("pcl-eap", "power_capturelyseBeans", beanName, VerbrauchVerwaltungInterface.class);

	ServiceLocator locator3 = new ServiceLocator();
	beanName = AdressVerwaltungBean.class.getSimpleName();
	adressverwaltung = (AdressVerwaltungInterface) locator3.getStateless("pcl-eap", "power_capturelyseBeans", beanName, AdressVerwaltungInterface.class);

	// User user = new User("Gouders", "Steffi", "steff", "h1411893");
	User user = new User();
	// user = userverwaltung.createUser(user);
	// Adresse adresse = new Adresse("Schwanenstr. 70a", 46399, "Bocholt",
	// user);
	// Verbrauch verbrauch = new Verbrauch();

	// adressverwaltung.addAdresse(adresse);
	OutputHelper.output(userverwaltung.findUserAdresse(2));

	String date1 = "2014-01-01 00:00:00";
	String date2 = "2014-01-10 00:00:00";
	SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
	Date datumVon = (Date) format1.parse(date1);
	Date datumBis = (Date) format1.parse(date2);
	// Timestamp datumVon = new Timestamp(datum1.getTime());
	// Timestamp datumBis = new Timestamp(datum2.getTime());
	System.out.println(datumVon);
	System.out.println(datumBis);

	// System.out.println(verbrauchsverwaltung.getVerbraeucheAuswahl(1, 1,
	// datumVon, datumBis));
	 
	OutputHelper.output(verbrauchsverwaltung.getVerbraeucheAuswahl(1,datumVon, datumBis));
	
	OutputHelper.output(verbrauchsverwaltung.getVerbraeuche(1));
	int id_user = user.getId_user();
	// System.out.println(userverwaltung.findUser(id_user));

	System.out.println(id_user);

    }

}
