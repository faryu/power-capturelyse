package interfaces;

import javax.ejb.Remote;

import entity.User;

@Remote
public interface ClientSitzungRemoteInterface {
	public User getUser();
	
	public boolean identityCheck(String loginname, String password);
	
	public String whoIam();
	public void refresh();
	
}