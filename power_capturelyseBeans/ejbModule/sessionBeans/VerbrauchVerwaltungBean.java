package sessionBeans;

import interfaces.VerbrauchVerwaltungInterface;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Verbrauch> getVerbraeuche(int id_zaehler) {
	Query query = em.createQuery("SELECT v.zaehlerstand, v.datum from Verbrauch v where v.zaehler.id_zaehler = :id_zaehler");
	query.setParameter("id_zaehler", id_zaehler);
    List<Verbrauch> resultList = query.getResultList();
	logger.info("Anzahl der gefundenen Verbr�uche: " + resultList.size());
	return resultList;
    }

	@Override
	public List<Verbrauch> getVerbraeucheAuswahl(int id_zaehler, Date datumVon, Date datumBis) {		
//		Query query = em.createQuery("SELECT v from Verbrauch v where v.adresse.id_adresse = :id_adresse " + 
//				"and v.energietyp.id_energietyp= :id_energietyp " + 
//				"and (v.datum between :datumVon and :datumBis )");
             Query query = em.createNativeQuery("SELECT * from tb_verbrauch v where v.id_adresse = :id_adresse " + 
		"and v.id_energietyp= :id_energietyp " + 
		"and v.datum between :datumVon and :datumBis ");
		query.setParameter("id_adresse",id_zaehler );
				query.setParameter("datumVon",datumVon );
		query.setParameter("datumBis",datumBis );
		@SuppressWarnings("unchecked")
		List<Verbrauch> resultList = query.getResultList();
		// TODO Auto-generated method stub
		logger.info("Anzahl der gefundenen Verbr�uche: " + resultList.size());
		return resultList;
		

	}

	

}
