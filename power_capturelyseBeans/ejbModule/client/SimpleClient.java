package client;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.naming.NamingException;

import org.jboss.netty.util.internal.LinkedTransferQueue;

import entity.Adresse;
import entity.Energietyp;
import entity.User;
import entity.Verbrauch;
import serviceLocator.ServiceLocator;
import sessionBeans.AdressVerwaltungBean;
import sessionBeans.AnalyseBean;
import sessionBeans.EnergietypVerwaltungBean;
import sessionBeans.UserVerwaltungBean;
import sessionBeans.VerbrauchVerwaltungBean;
import sessionBeans.WetterBean;
import interfaces.AdressVerwaltungInterface;
import interfaces.AnalyseInterface;
import interfaces.EnergietypVerwaltungInterface;
import interfaces.UserVerwaltungInterface;
import interfaces.VerbrauchVerwaltungInterface;
import interfaces.WetterTimerInterface;

public class SimpleClient {

    @EJB
    private static UserVerwaltungInterface userverwaltung;

    @EJB
    private static VerbrauchVerwaltungInterface verbrauchsverwaltung;

    @EJB
    private static AdressVerwaltungInterface adressverwaltung;
    
    @EJB
    private static EnergietypVerwaltungInterface energieverwaltung;
    
    @EJB
    private static AnalyseInterface analyse;
    
    @EJB
    private static WetterTimerInterface wetter;

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
	
	ServiceLocator locator4 = new ServiceLocator();
	beanName = EnergietypVerwaltungBean.class.getSimpleName();
	energieverwaltung = (EnergietypVerwaltungInterface) locator4.getStateless("pcl-eap", "power_capturelyseBeans", beanName, EnergietypVerwaltungInterface.class);

	ServiceLocator locator5 = new ServiceLocator();
	beanName = AnalyseBean.class.getSimpleName();
	analyse = (AnalyseInterface) locator5.getStateless("pcl-eap", "power_capturelyseBeans", beanName, AnalyseInterface.class);
	
	
	ServiceLocator locator6 = new ServiceLocator();
	beanName = WetterBean.class.getSimpleName();
	wetter = (WetterTimerInterface) locator6.getStateless("pcl-eap", "power_capturelyseBeans", beanName, WetterTimerInterface.class);
	
	// User user = new User("Gouders", "Steffi", "steff", "h1411893");
	User user = new User();
	// user = userverwaltung.createUser(user);
	// Adresse adresse = new Adresse("Schwanenstr. 70a", 46399, "Bocholt",
	// user);
	// Verbrauch verbrauch = new Verbrauch();

	// adressverwaltung.addAdresse(adresse);
//	OutputHelper.output(userverwaltung.findUserAdresse(2));

	String date1 = "2014-01-16 00:00:00";
	String date2 = "2014-01-16 00:00:00";
	SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
	Date datumVon = (Date) format1.parse(date1);
	Date datumBis = (Date) format1.parse(date2);
	
	System.out.println(datumVon);
	System.out.println(datumBis);
	
	System.out.println("Test");
	
	wetter.showWetterPlz(12345);
	wetter.showWetterAuswahl(12345, datumVon, datumBis);
	

	 
	
	//OutputHelper.output(verbrauchsverwaltung.getVerbraeucheAuswahl(1,datumVon, datumBis));
	//energieverwaltung.showEnergietypen();
	
	
	//BigDecimal innn = analyse.mittlererVerbrauch(verbrauchsverwaltung.getVerbraeucheAuswahl(1,datumVon, datumBis));
//	System.out.println(verbrauchsverwaltung.getVerbraeucheAuswahl(1,datumVon, datumBis).get(0).getZaehlerstand());

//	System.out.println(verbrauchsverwaltung.getVerbraeucheAuswahl(1,datumVon, datumBis).get(0).getZaehlerstand());
	//OutputHelper.output(verbrauchsverwaltung.getVerbraeuche(1));
//	int id_user = user.getId_user();
	// System.out.println(userverwaltung.findUser(id_user));

	
//	System.out.println(id_user);

    }

}
