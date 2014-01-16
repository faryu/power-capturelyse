package sessionBeans;

import interfaces.EnergietypVerwaltungInterface;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import entity.Energietyp;

@Stateless
public class EnergietypVerwaltungBean implements EnergietypVerwaltungInterface{

	static Logger logger = Logger.getLogger(EnergietypVerwaltungBean.class);
    
    @PersistenceContext(name="powerCapturelyseBeans")
    private EntityManager em;
	
	@Override
	public Energietyp createEnergietyp(Energietyp energietyp) {
		em.persist(energietyp);
		return energietyp;
	}

	@Override
	public void updateEnergietyp(Energietyp energietyp) {
		em.merge(energietyp);
		
	}

	@Override
	public List<Energietyp> showEnergietypen() {
		Query query = em.createQuery("SELECT e.energietypen, e.einheiten FROM Energietypen");
		@SuppressWarnings("unchecked")
		List<Energietyp> resultList = query.getResultList();		
		return resultList;
	}

	@Override
	public void deleteEnergietyp(Energietyp energietyp) {
		em.remove(energietyp);
		
	}

}
