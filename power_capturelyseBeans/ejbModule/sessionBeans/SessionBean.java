package sessionBeans;

import java.security.MessageDigest;

import interfaces.*;
import helper.*;
import javax.ejb.*;


@Stateful
public class SessionBean implements SessionRemoteInterface {

	
	private String user;
	
	
	@EJB
	private UserVerwaltungInterface userobject;
	
	public boolean identityCheck(String loginname, String password) {
				
		// Hier wird eine Funktion benötigt die schaut ob es den Usernamen gibt
		
		System.out.println("(User ist vorhanden)"+ userobject.exists(loginname));
		
		String hash = "";
		
		if(userobject.exists(loginname)==true)
		{
	        String digest = null;
	        try 
	        {  System.out.println("Das Passwort lautet: " + password);
	        	
	        	hash = Helper.md5Java(password);
	        	       
	          
	           System.out.println("Das MD5 Hash lautet: " + hash);
	        	
	        } 
	        catch (Exception ex)
	        {
	        	ex.printStackTrace();
	        }
	        
			if(userobject.getPasswort(loginname).contains(hash))
			{
				System.out.println("Das eigebene Passwort ist korrekt!");
				this.user = loginname;
				return true;				
			}	
			else
			{
				System.out.println("Das eingebene Passwort ist nicht korrekt");
				return false;
			}
		
		}
		else
		{
			System.out.println("Der eigebene Loginname existiert nicht!");
			return false;
		}			
	}
	
	public String whoIam()
		{
			return this.user;
		}
		
	
	


	
/*		
	@PostConstruct
	public void postconstruct()
	{
		System.out.println("PostConstruct");
	}

*/
	
	
	
}
