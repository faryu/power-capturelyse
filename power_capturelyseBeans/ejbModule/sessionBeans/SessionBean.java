package sessionBeans;

import helper.Helper;
import interfaces.SessionRemoteInterface;
import interfaces.UserVerwaltungInterface;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import entity.User;


@Stateful
public class SessionBean implements SessionRemoteInterface {

	
	private User user;
	
	
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
	
	      this.user = userobject.findUserLoginName(loginname, hash);
	      return true;
		}
		 return false;
	}
	
	public String whoIam()
		{
			return this.user.getUsername();
		}
		
	
	


	
/*		
	@PostConstruct
	public void postconstruct()
	{
		System.out.println("PostConstruct");
	}

*/
	
	
	
}
