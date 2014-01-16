package sessionBeans;

import helper.WetterOpenWAPI;
import interfaces.WetterTimerInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import entity.Verbrauch;
import entity.Wetter;


@Stateless
public class WetterBean implements WetterTimerInterface, Serializable
{	
    
    /**
     * 
     */
    private static final long serialVersionUID = 2083346131132684186L;

    static Logger logger = Logger.getLogger(WetterBean.class);
    
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
	void timeOutHandler(Timer timer) // ohne Modifier Zugriff f�r Klasse und Package
	{
		System.out.println("timeoutHandler : " + timer.getInfo());		
		List<Wetter> wetterdaten = WetterOpenWAPI.abfrageStarten(getPLZ()); // Abfrage der Wetterdaten f�r alle PLZ		
		saveWetter(wetterdaten); // Speichern der gelieferten Daten
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
        	//System.out.println("Name: "+t.getInfo()+", N�chste Ausf�hrung: "+t.getNextTimeout()+", Intervall: "+t.getSchedule());			
        	timers+=("Name: "+t.getInfo()+", N�chste Ausf�hrung: "+t.getNextTimeout()+", Zeit bis zur n�chsten Ausf�hrung "+t.getTimeRemaining()/1000+" Sekunden\n");
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
	
	private void saveWetter(List<Wetter> alleWetterdaten)
    {
    	for (Wetter wetter:alleWetterdaten)
    		em.persist(wetter);
    }
	
	
	
	public List<Wetter> showWetterPlz(int plz) {		
		Query query = em.createQuery("SELECT w from Wetter w where w.plz =:plz ");
		query.setParameter("plz",plz );
		@SuppressWarnings("unchecked")
		List<Wetter> resultList = (List<Wetter>)query.getResultList();
		for(Wetter wetter : resultList){
		    System.out.println(wetter.getPlz() +" " + wetter.getDatum()+ " " + wetter.getTemp());
		}
					
		
		// TODO Auto-generated method stub
		logger.info("Anzahl der gefundener Wetter für PLZ: "+ plz + resultList.size());
		return resultList;
	}
}
