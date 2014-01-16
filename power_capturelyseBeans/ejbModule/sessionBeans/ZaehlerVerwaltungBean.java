package sessionBeans;

import interfaces.ZaehlerVerwaltungInterface;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import entity.Zaehler;

/**
 * Session Bean implementation class ZaehlerVerwaltungBean
 */
@Stateless
public class ZaehlerVerwaltungBean implements ZaehlerVerwaltungInterface {
    
    
    static Logger logger = Logger.getLogger(VerbrauchVerwaltungBean.class);
    
    @PersistenceContext(name="powerCapturelyseBeans")
    private EntityManager em;

    /**
     * Default constructor. 
     */
    public ZaehlerVerwaltungBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Zaehler createZaehler(Zaehler zaehler) {
	em.persist(zaehler);	
	return zaehler;
    }

    @Override
    public Zaehler findZaehler(int id_zaehler) {
	return em.find(Zaehler.class, id_zaehler);	
    }

    @Override
    public Zaehler updateZaehler(Zaehler zaehler) {
	em.merge(zaehler);
	return zaehler;
    }

    @Override
    public void deleteZaehler(Zaehler zaehler) {
	em.remove(zaehler);
	
    }

}
