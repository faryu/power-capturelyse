package client;

import interfaces.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;



import serviceLocator.ServiceLocator;
import sessionBeans.*;
import client.ClientUtility;
import interfaces.*;

public class SessionBeanClient {
    @EJB
    private static SessionRemoteInterface remote;
    
   @EJB
   private static VerbrauchVerwaltungInterface remote2;

    public static void main(String[] args) throws IOException, NamingException ,ParseException {
	
      
    ServiceLocator locator = new ServiceLocator();
    
    String beanName = VerbrauchVerwaltungBean.class.getSimpleName();
    System.out.println("test");
    	  
    	  
    	remote2 = (VerbrauchVerwaltungInterface) locator.getStateless(
    		"pcl-eap", "power_capturelyseBeans", beanName,
    		VerbrauchVerwaltungInterface.class);
    	
    	
    	String date1 = "2014-01-01 00:00:00";
    	String date2 = "2014-01-10 00:00:00";
    	SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    	Date datumVon = (Date) format1.parse(date1);
    	Date datumBis = (Date) format1.parse(date2);
    	// Timestamp datumVon = new Timestamp(datum1.getTime());
    	// Timestamp datumBis = new Timestamp(datum2.getTime());
    	System.out.println(datumVon);
    	System.out.println(datumBis);
    	
    
    	remote2.getVerbraeucheAuswahl(1, datumVon, datumBis);
    	
    	System.out.print("Fertig");
	/*	
	BufferedReader console = new BufferedReader(new InputStreamReader(
		System.in));

	System.out.println("Bitte geben Sie einen Usernamen ein.");
	String user = console.readLine();
	System.out.println("Bitte geben Sie das zugehoerige Passwort ein.");
	String password = console.readLine();

	ServiceLocator locator = new ServiceLocator();
	String beanName = SessionBean.class.getSimpleName();

	remote = (SessionRemoteInterface) locator.getStateful(
		"pcl-eap", "power_capturelyseBeans", beanName,
		SessionRemoteInterface.class);
		
		*/
	
//	SessionRemoteInterface remote = doLookup();

	/*if (remote.identityCheck(user, password) == false) {
	    System.out.println("Ihr Username oder Password ist fehlerhaft");
	} else {
	    System.out.println("Sie haben sich erfolgreich angemeldet, ");// +remote.whoIam());
	}
	


    }

    private static SessionRemoteInterface doLookup() {
	Context context = null;
	SessionRemoteInterface remote = null;
	try {
	    // 1. Obtaining Context
	    context = ClientUtility.getInitialContext();
	    // 2. Generate JNDI Lookup name
	    String lookupName = getLookupName();
	    // 3. Lookup and cast
	    remote = (SessionRemoteInterface) context.lookup(lookupName);

	} catch (NamingException e) {
	    e.printStackTrace();
	}
	return remote;
    }

    private static String getLookupName() {
	String appName = "pcl-eap";

	/*
	 * The module name is the JAR name of the deployed EJB without the .jar
	 * suffix.
	 */
/*	String moduleName = "power_capturelyseBeans";

	/*
	 * AS7 allows each deployment to have an (optional) distinct name. This
	 * can be an empty string if distinct name is not specified.
	 */
	/*String distinctName = "";

	// The EJB bean implementation class name
	String beanName = SessionBean.class.getSimpleName();

	// Fully qualified remote interface name
	final String interfaceName = SessionRemoteInterface.class.getName();

     // Create a look up string name Stateless
	String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
		+ "/" + beanName + "!" + interfaceName;
	System.out.println(name);
	
	// Create a look up string name Stateful
	String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
		+ "/" + beanName + "!" + interfaceName + "?stateful";
	System.out.println(name);

	return name;
		*/
    }
}
