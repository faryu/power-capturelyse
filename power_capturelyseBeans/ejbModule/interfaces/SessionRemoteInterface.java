package interfaces;

import javax.ejb.Remote;

@Remote
public interface SessionRemoteInterface {

	
	public boolean identityCheck(String loginname, String password);
	
	public String whoIam();
	
	
}