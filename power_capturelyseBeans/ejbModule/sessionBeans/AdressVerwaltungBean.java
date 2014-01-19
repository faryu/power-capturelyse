package sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import entity.Adresse;
import interfaces.AdressVerwaltungInterface;

@Stateless
public class AdressVerwaltungBean implements AdressVerwaltungInterface {

	static Logger logger = Logger.getLogger(AdressVerwaltungBean.class);
	@PersistenceContext(unitName = "powerCapturelyseBeans")
	private EntityManager em;

	@Override
	public Adresse addAdresse(Adresse adresse) {
		if (em.find(Adresse.class, adresse.getId_adresse()) != null) {
			System.out.println("Adresse schon vorhanden");
		}
		em.persist(adresse);
		em.flush();
		em.refresh(adresse);

		return adresse;
	}

	@Override
	public Adresse findAdresse(int id_adresse) {
		return em.find(Adresse.class, id_adresse);
	}

	@Override
	public Adresse findAdressByUser(int id_user) {
		return em.find(Adresse.class, id_user);
	}

	@Override
	public void updateAdresse(Adresse adresse) {
		em.merge(adresse);

	}

}
