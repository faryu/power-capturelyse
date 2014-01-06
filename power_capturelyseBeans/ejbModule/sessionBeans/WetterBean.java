package sessionBeans;

import helper.WetterOpenWAPI;
import interfaces.WetterTimerInterface;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Wetter;


@Stateless
public class WetterBean implements WetterTimerInterface
{	
	@Resource
	private TimerService timerService;
	
	@PersistenceContext(name="powerCapturelyseBeans")
    private static EntityManager em;
	
	@SuppressWarnings("unused")
	@Override
	public void createTimer(long milliseconds, String name) 
	{
	 	//createTimer(long initialDuration, long intervalDuration, java.io.Serializable info)
        //Create an interval timer whose first expiration occurs after a specified duration, and whose subsequent expirations occur after a specified interval.
		Timer timer  = this.timerService.createTimer(10, milliseconds, name);
	}
	
	@Timeout
	public void timeOutHandler(Timer timer)
	{
		System.out.println("timeoutHandler : " + timer.getInfo());		
		List<Wetter> wetterdaten = WetterOpenWAPI.abfrageStarten(getPLZ()); // Abfrage der Wetterdaten für alle PLZ		
		addWetter(wetterdaten); // Speichern der gelieferten Daten
	}

	@Override
	public void stopTimer(String timerName) 
	{
		for(Object obj : timerService.getTimers()) 
		{
	        Timer t = (Timer)obj;
	        if (t.getInfo().equals(timerName)) 
	        {
	        	System.out.println("Timer '"+timerName+"' gefunden und beendet.");
	        	t.cancel();
	        }
	    }
	}
	
	@Override
	public String showTimer() 
	{
		String timers="";
		for(Object obj : timerService.getTimers()) 
		{
			Timer t = (Timer)obj;
        	//System.out.println("Name: "+t.getInfo()+", Nächste Ausführung: "+t.getNextTimeout()+", Intervall: "+t.getSchedule());			
        	timers+=("Name: "+t.getInfo()+", Nächste Ausführung: "+t.getNextTimeout()+", Zeit bis zur nächsten Ausführung "+t.getTimeRemaining()/1000+" Sekunden\n");
	    }
		return timers;
	}
	
	@SuppressWarnings("unchecked")
	private static List<String> getPLZ()
	{
		List<String> allePlz = new ArrayList<String>();
				
		Query q = em.createNativeQuery ("select distinct plz from tb_adresse");
				List<Integer> result = q.getResultList();
				for (Integer r:result)				
					allePlz.add(r.toString());
				
		return allePlz;
	}
	
	private void addWetter(List<Wetter> alleWetterdaten)
    {
    	for (Wetter wetter:alleWetterdaten)
    		em.persist(wetter);
    }
}
