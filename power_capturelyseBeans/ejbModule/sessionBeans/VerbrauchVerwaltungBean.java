package sessionBeans;

import interfaces.VerbrauchVerwaltungInterface;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Verbrauch> getVerbraeuche(int id_adresse) {
	List<Verbrauch> resultList = em.createQuery("SELECT v.zaehlerstand, v.datum, v.energietyp.energietyp from Verbrauch v where v.adresse.id_adresse = " + id_adresse).getResultList();
   
	logger.debug("Anzahl der gefundenen Verbräuche: " + resultList.size());
	return resultList;
    }

	@Override
	public List<Verbrauch> getVerbraeucheAuswahl(int id_adresse,
			int id_energietyp, Date datumVon, Date datumBis) {
		@SuppressWarnings("unchecked")
		List<Verbrauch> resultList = em.createQuery("SELECT v from Verbrauch v where v.adresse.id_adresse = " + id_adresse + 
													"and v.energietyp.id_energietyp= " + id_energietyp + 
													"and (v.datum between " + datumVon + "and " + datumBis + ")").getResultList();
		// TODO Auto-generated method stub
		logger.debug("Anzahl der gefundenen Verbräuche: " + resultList.size());
		return resultList;
	}

}
