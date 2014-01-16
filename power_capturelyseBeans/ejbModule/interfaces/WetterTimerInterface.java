package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.Wetter;

@Remote
public interface WetterTimerInterface {
    
	public void createTimer(long milliseconds, String name);
	public void stopTimer(String timerName);
	public String showTimer();
	public List<Wetter> showWetterPlz(int plz);
}
