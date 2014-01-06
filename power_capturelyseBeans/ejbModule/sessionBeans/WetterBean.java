package sessionBeans;

import helper.WetterOpenWAPI;
import interfaces.WetterTimerInterface;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;


@Stateless
public class WetterBean implements WetterTimerInterface
{	
	@Resource
	private TimerService timerService;

	@SuppressWarnings("unused")
	@Override
	public void createTimer(long milliseconds, String name) 
	{
	 	//createTimer(long initialDuration, long intervalDuration, java.io.Serializable info)
        //Create an interval timer whose first expiration occurs after a specified duration, and whose subsequent expirations occur after a specified interval.
		Timer timer  = this.timerService.createTimer(10, milliseconds, name);
		//context.getTimerService().createTimer(milliseconds, "Hello World!");
	}
	
	@Timeout
	public void timeOutHandler(Timer timer)
	{
		System.out.println("timeoutHandler : " + timer.getInfo());
		WetterOpenWAPI.abfrageStarten();		
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
        	timers+=("Name: "+t.getInfo()+", Nächste Ausführung: "+t.getNextTimeout()+"\n");
	    }
		return timers;
	}
}
