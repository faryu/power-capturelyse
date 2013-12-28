package sessionBeans;

import interfaces.VerbrauchVerwaltungInterface;


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
    public List<Verbrauch> getVerbraeuche(int id_adresse, int id_energietyp) {
	List<Verbrauch> resultList = em.createQuery("SELECT v from Verbrauch where v.adresse.id_adresse = " + id_adresse + "and v.engergietyp.id_engergietyp = " + id_energietyp).getResultList();
	
	logger.debug("Anzahl der gefundenen Verbräuche: " + resultList.size());
	return resultList;
    }

}
