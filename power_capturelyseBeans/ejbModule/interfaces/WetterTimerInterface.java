package interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.Wetter;

@Remote
public interface WetterTimerInterface {
    
	public void createTimer(long milliseconds, String name);
	public void stopTimer(String timerName);
	public String showTimer();
	public List<Wetter> showWetterPlz(int plz);
	public List<Wetter> showWetterAuswahl(int plz, Date datumVon, Date datumBis);
	public double showWetterAVG(int plz, Date datumVon, Date datumBis);
	
}
