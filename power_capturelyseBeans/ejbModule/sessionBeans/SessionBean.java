package sessionBeans;

import java.security.MessageDigest;

import interfaces.*;

import javax.ejb.*;


@Stateful
public class SessionBean implements SessionRemoteInterface {

	
	private String user;
	
	
	@EJB
	private UserVerwaltungInterface userobject;
	
	public boolean identityCheck(String loginname, String password) {
				
		// Hier wird eine Funktion benötigt die schaut ob es den Usernamen gibt
		if(userobject.exists(loginname)==true)
		{
	        String digest = null;
	        try 
	        {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] hash = md.digest(password.getBytes("UTF-8"));
	           
	            //converting byte array to Hexadecimal String
	           StringBuilder sb = new StringBuilder(2*hash.length);
	           for(byte b : hash)
	           {
	               sb.append(String.format("%02x", b&0xff));
	           }          
	           digest = sb.toString();
	          
	        } 
	        catch (Exception ex)
	        {
	        	ex.printStackTrace();
	        }
	        
			if(userobject.getPasswort(loginname).contains(digest))
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
