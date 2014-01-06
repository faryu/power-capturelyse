package sessionBeans;

import interfaces.VerbrauchVerwaltungInterface;

import java.sql.Date;
import java.sql.Timestamp;
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
    public List<Verbrauch> getVerbraeuche(int id_adresse) {
	Query query = em.createQuery("SELECT v.zaehlerstand, v.datum, v.energietyp.energietyp from Verbrauch v where v.adresse.id_adresse = :id_adresse");
	query.setParameter("id_adresse", id_adresse);
    List<Verbrauch> resultList = query.getResultList();
	logger.info("Anzahl der gefundenen Verbraeuche: " + resultList.size());
	return resultList;
    }

	@Override
	public List<Verbrauch> getVerbraeucheAuswahl(int id_adresse,
			int id_energietyp, Timestamp datumVon, Timestamp datumBis) {		
//		Query query = em.createQuery("SELECT v from Verbrauch v where v.adresse.id_adresse = :id_adresse " + 
//				"and v.energietyp.id_energietyp= :id_energietyp " + 
//				"and v.datum between :datumVon and :datumBis ");
	    Query query = em.createNativeQuery("SELECT * from tb_verbrauch v where v.id_adresse = :id_adresse " + 
			"and v.id_energietyp= :id_energietyp " + 
			"and v.datum between :datumVon and :datumBis ");
		query.setParameter("id_adresse",id_adresse );
		query.setParameter("id_energietyp",id_energietyp );
		query.setParameter("datumVon",datumVon );
		query.setParameter("datumBis",datumBis );
		@SuppressWarnings("unchecked")
		List<Verbrauch> resultList = query.getResultList();
		// TODO Auto-generated method stub
		System.out.println("test");
		logger.info("Anzahl der gefundenen Verbraeuche: " + resultList.size());
		return resultList;
	}

	

}
