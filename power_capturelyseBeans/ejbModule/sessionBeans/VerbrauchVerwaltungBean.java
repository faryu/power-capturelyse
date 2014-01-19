package sessionBeans;

import interfaces.VerbrauchVerwaltungInterface;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import entity.Verbrauch;

@Stateless
public class VerbrauchVerwaltungBean implements VerbrauchVerwaltungInterface{
    
 
	static Logger logger = Logger.getLogger(VerbrauchVerwaltungBean.class);
    
    @PersistenceContext(name="powerCapturelyseBeans")
    private EntityManager em;
       
    @Override
    public Verbrauch createVerbrauch(Verbrauch verbrauch) {
	em.persist(verbrauch);
	return verbrauch;
    }

    @Override
    public void updateVerbrauch(Verbrauch verbrauch) {
	em.merge(verbrauch);
	
    }
    
    @Override
	public void deleteVerbrauch(Verbrauch verbrauch) {
		em.remove(verbrauch);		
	}
 
    
    /**
     * Gibt die Zaehlerstande fuer einen bestimmten Zaehler zurueck
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Verbrauch> getZaehlerstaende(int id_zaehler) {
    	Query query = em.createQuery("SELECT v from Verbrauch v where v.zaehler.id_zaehler = :id_zaehler ORDER BY v.datum ASC");
    	query.setParameter("id_zaehler", id_zaehler);
    	List<Verbrauch> resultList = (List<Verbrauch>)query.getResultList();
    	logger.info("******* Anzahl der gefundenen getZaehlerstaende(): " + resultList.size()+" *******");
    	return resultList;
    }
    
    /**
     * Gibt die Zaehlerstaende fuer einen bestimmten Zaehler
     * innerhalb eines bestimmten Zeitraums zurueck
     */
	@Override	
	public List<Verbrauch> getZaehlerstaendeAuswahl(int id_zaehler, Date datumVon, Date datumBis) {		
		Query query = em.createQuery("SELECT v from Verbrauch v where v.zaehler.id_zaehler = :id_zaehler " +  
					"and (v.datum between :datumVon and :datumBis ) ORDER BY v.datum ASC");
		query.setParameter("id_zaehler",id_zaehler );
		query.setParameter("datumVon",datumVon );
		query.setParameter("datumBis",datumBis );
		@SuppressWarnings("unchecked")
		List<Verbrauch> resultList = (List<Verbrauch>)query.getResultList();
		
					
		// TODO Auto-generated method stub
		logger.info("********* Anzahl der gefundenen getZaehlerstaendeAuswahl(): " + resultList.size()+ " *******");
		return resultList;
		}

	
	
	@SuppressWarnings("deprecation")
	public Double showGesamtVerbrauchImIntervall(int id_zaehler, Date datumVon,Date datumBis) throws ParseException
		{ 
		
		System.out.println("showGesamtVerbrauchImIntervall=======================");
		
//		Double intervalVerbrauchReturn;
//		
//		datumVon.setHours(00);
//		datumVon.setMinutes(00);
//		datumVon.setSeconds(00);
//		
//		datumBis.setHours(23);
//		datumBis.setMinutes(59);
//		datumBis.setSeconds(59);
			
		Query query = em.createQuery("SELECT v from Verbrauch v where v.zaehler.id_zaehler = :id_zaehler " +  
		"and (v.datum between :datumVon and :datumBis ) ORDER BY v.datum ASC");
		query.setParameter("id_zaehler",id_zaehler );
		query.setParameter("datumVon",datumVon );
		query.setParameter("datumBis",datumBis );
  
		@SuppressWarnings("unchecked")
		List<Verbrauch> resultList = (List<Verbrauch>)query.getResultList();
    	if(resultList == null || resultList.isEmpty())
    		return 0.0;
    	return resultList.get(resultList.size() - 1).getZaehlerstand().doubleValue() - resultList.get(0).getZaehlerstand().doubleValue();
		// 
		
		
		// String strvonDatum = resultList.get(0).getDatum().toString();
		// String strbisDatum =
		// resultList.get(resultList.size()-1).getDatum().toString();
		//
		// SimpleDateFormat format1 = new
		// SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		//
		// Date sqlvonDatum = (Date) format1.parse(strvonDatum);
		// Date sqlbisDatum = (Date) format1.parse(strbisDatum);
		//
		// sqlvonDatum.setHours(00);
		// sqlvonDatum.setMinutes(00);
		// sqlvonDatum.setSeconds(00);
		//
		// sqlbisDatum.setHours(23);
		// sqlbisDatum.setMinutes(59);
		// sqlbisDatum.setSeconds(59);
		//
		//
		//
		// if((sqlvonDatum.compareTo(datumVon)==0) &&
		// (sqlbisDatum.compareTo(datumBis)==0))
		// {
		// System.out.println("Intervall ist vorhanden");
		//
		// Long zeitvonDatum = resultList.get(0).getDatum().getTime();
		// Long zeitbisDatum = resultList.get(1).getDatum().getTime();
		//
		// Long intervallTagAbstand = (zeitbisDatum -
		// zeitvonDatum)/1000/60/60/24;
		// System.out.println("Tage im Intervall: "+intervallTagAbstand);
		//
		// BigDecimal zaehlerstandvonDatum =
		// resultList.get(0).getZaehlerstand();
		// BigDecimal zaehlerstandbisDatum =
		// resultList.get(1).getZaehlerstand();
		//
		// BigDecimal intervallVerbrauch =
		// zaehlerstandbisDatum.subtract(zaehlerstandvonDatum);
		//
		// intervalVerbrauchReturn =intervallVerbrauch.doubleValue();
		//
		// System.out.println("Verbrauch im Intervall: "+intervalVerbrauchReturn);
		//
		// // verbrauchProTag= intervallVerbrauch.doubleValue() /
		// intervallTagAbstand.doubleValue();
		//
		// // System.out.println( "Durchschnittsverbrauch pro Tag "+
		// verbrauchProTag);
		//
		// }
		// else
		// {
		// System.out.println("Angebene Intervallgrenzen sind nicht beide in den Verbrauchsdaten vorhanden.");
		// return null;
		// }
		//
		// return intervalVerbrauchReturn;
	}
	
	
	
	
	/**
	 * Gibt den durchschnittlichen Verbrauch eines bestimmmten Zaehlers
	 * innerhalb eines bestimmten Zeitraumes zurueck
	 * @param int id_zaehler, Date datumVon, Date datumBis
	 * @return BigDecimal
	 */
	//K�se ermittelt keinen Mittelwert pro Tag	
//	public BigDecimal showVerbraeucheAVG(int id_zaehler, Date datumVon, Date datumBis) {
//	    Query query = em.createQuery("SELECT abs((Max(v.zaehlerstand) - Min(v.zaehlerstand)) /( Datediff(:datumVon, :datumBis)))as avg from Verbrauch v where v.zaehler.id_zaehler = :id_zaehler " +  
//			"and (v.datum between :datumVon and :datumBis )");
//	    query.setParameter("id_zaehler",id_zaehler );
//	    query.setParameter("datumVon",datumVon );
//	    query.setParameter("datumBis",datumBis );
//		
//	    BigDecimal wert = (BigDecimal) query.getSingleResult();
//	  
//	    // TODO Auto-generated method stub
//	    logger.info("******* AVG Verbrauch für Zaehler: " + id_zaehler + " innerhalb " + datumVon + " und " + datumBis + "-> " + wert + " *******");
//	    return wert;
//		}
		
	/**
	 * Gibt den durchschnittlichen Verbrauch und den Gesamtverbrauch
	 * als eine Liste von Object[]zurueck
	 */
	
//	public List<Object[]>showVerbraeucheAVG(int id_zaehler, Date datumVon, Date datumBis) {
//	    Object[] arr;
//	    Query query = em.createQuery("SELECT abs((Max(v.zaehlerstand) - Min(v.zaehlerstand)) /( Datediff(:datumVon, :datumBis)))as avg, (Max(v.zaehlerstand)-Min(v.zaehlerstand)) as gesamt from Verbrauch v where v.zaehler.id_zaehler = :id_zaehler " +  
//			"and (v.datum between :datumVon and :datumBis )");
//	    query.setParameter("id_zaehler",id_zaehler );
//	    query.setParameter("datumVon",datumVon );
//	    query.setParameter("datumBis",datumBis );
//	    @SuppressWarnings("unchecked")
//	    List<Object[]> l = query.getResultList();	    
//	    for(Object object: l ){
//		arr = (Object[]) object;
//		System.out.println("AVG: " + arr[0]);
//		System.out.println("Verbrauch gesamt:" + arr[1]);		
//	    }
//	    // TODO Auto-generated method stub
//	    logger.info("******* AVG Verbrauch für Zaehler: " + id_zaehler + " innerhalb " + datumVon + " und " + datumBis + "->  *******");
//	    return l;
//		}

	
	@SuppressWarnings("deprecation")
	public Double showTagesVerbrauchImIntervall(int id_zaehler, Date datumVon,Date datumBis) throws ParseException
	{	
		System.out.println("showTagesVerbrauchImIntervall=======================");
		
		if(showGesamtVerbrauchImIntervall(id_zaehler, datumVon, datumBis) != null)
		{
		Double intervallVerbrauch = showGesamtVerbrauchImIntervall(id_zaehler, datumVon, datumBis);
		
		datumVon.setHours(00);
		datumVon.setMinutes(00);
		datumVon.setSeconds(00);
		
		datumBis.setHours(23);
		datumBis.setMinutes(59);
		datumBis.setSeconds(59);
		
		
		Long tagesAbstand = (datumBis.getTime() - datumVon.getTime()) /1000 /60 /60/24;
		
		System.out.println("Tagesabstand "+tagesAbstand);
		
		Double avgTagVerbrauch = intervallVerbrauch /tagesAbstand.doubleValue();
		
		System.out.println("AVG pro Tag "+avgTagVerbrauch);
		
	   return avgTagVerbrauch;
	   }
	System.out.println("Angebene Intervallgrenzen sind nicht beide in den Verbrauchsdaten vorhanden.");
	return null;
	}
	

	
}
