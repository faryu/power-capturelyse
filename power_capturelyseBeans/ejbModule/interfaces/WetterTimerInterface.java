package interfaces;

import javax.ejb.Remote;

@Remote
public interface WetterTimerInterface {
    
	public void createTimer(long milliseconds, String name);
	public void stopTimer(String timerName);
	public String showTimer();
}
