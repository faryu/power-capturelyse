package client;

import interfaces.WetterTimerInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.ejb.EJB;

import serviceLocator.ServiceLocator;
import sessionBeans.WetterBean;


public class WetterTimerClient 
{
	@EJB
    private static WetterTimerInterface wetterRemote;
	
	public static void main (String args[]) throws Exception
	{	
		ServiceLocator locator = new ServiceLocator();
		String beanName = WetterBean.class.getSimpleName();
		wetterRemote = (WetterTimerInterface)locator.getStateless("pcl-eap", "power_capturelyseBeans", beanName, WetterTimerInterface.class);
		//WetterTimerInterface remote = doLookup();
		
		do
		{
			System.out.println("Bitte wählen:");
			System.out.println("1 - Timer anzeigen");
			System.out.println("2 - Timer beenden");
			System.out.println("3 - Timer erstellen");
			System.out.println();
			
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			String wahl = console.readLine();
			switch (Integer.parseInt(wahl))
			{
				case 1:
				{
					System.out.println("Ihre Timer:");
					String timers =  wetterRemote.showTimer();
					System.out.println(timers);
					System.out.println();
					break;
				}
				case 2:
				{	
					System.out.println("Bitte geben Sie einen Namen für den Timer ein: ");
					String name = console.readLine();
					wetterRemote.stopTimer(name);
					break;
				}
				case 3:
				{
					System.out.println("Bitte geben Sie einen Namen für den Timer ein: ");					
					String name = console.readLine();
					System.out.println("Bitte geben Sie einen Intervall für den Timer ein (ms): ");					
					String intervall = console.readLine();					
					wetterRemote.createTimer(Long.parseLong(intervall), name);
					break;
				}
			}
		} 
		while (true);
	}
	
//	private static WetterTimerInterface doLookup()
//	{
//		WetterTimerInterface remote = null;
//		Context context = null;
//		
//		try
//		{
//			context = ClientUtility.getInitialContext(); // Holt Kontext
//			String lookupName = getLookupName(); // Lookup Name generieren			
//			remote = (WetterTimerInterface)context.lookup(lookupName); // Lookup ausführen und Casten			
//		}
//		catch (NamingException ex)
//		{
//			ex.printStackTrace();
//		}
//		
//		return remote;
//	}
//	
//	private static String getLookupName()
//	{
//		String appName ="";
//		String moduleName="WetterBeanTimer";
//		String distincName ="";
//		String beanName= WetterBean.class.getSimpleName(); // "QuoterBean";
//		final String interfaceName = WetterTimerInterface.class.getName();
//		String name = "ejb:"+appName+"/"+moduleName+"/"+distincName+"/"+beanName+"!"+interfaceName;
//		System.out.println(name);
//		return name;
//	}

}
