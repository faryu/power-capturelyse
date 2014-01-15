package sessionBeans;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Verbrauch;
import interfaces.*;

@Stateless
public class VerbrauchBerechnungBean implements VerbrauchBerechungInterface {
	
    @PersistenceContext(name="powerCapturelyseBeans")
    private EntityManager em;
	
	public Map<Date,BigDecimal> getIntervallZaehlerstaende(int zaehlerid, Date anfang, Date ende)
	{
		
		
		Query query = em.createQuery("SELECT v.datum, v.id_zaehler from Verbrauch v where v.datum BETWEEN :anfang and :ende");
		query.setParameter("id_adresse", id_adresse);
	    List<Verbrauch> resultList = query.getResultList();
		logger.info("Anzahl der gefundenen Verbräuche: " + resultList.size());
		return resultList;
		
		
		//( (datetime between 2004-12-0100:00 and 2004-12-09 00:00) ) order by datetime DESC; 
			return null;
	}

}
