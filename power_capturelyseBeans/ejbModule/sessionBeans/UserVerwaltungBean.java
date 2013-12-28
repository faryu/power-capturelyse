package sessionBeans;

//import java.util.ArrayList;
import java.util.List;

import interfaces.UserVerwaltungInterface;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import entity.Adresse;
import entity.User;


@Stateless
public class UserVerwaltungBean implements UserVerwaltungInterface{
    
    static Logger logger = Logger.getLogger(UserVerwaltungBean.class); 
    @PersistenceContext(unitName="powerCapturelyseBeans") 
    private EntityManager em;

    public User createUser(User user, Adresse adresse) {			
	em.persist(user);
	em.persist(adresse);
	return user;
    }

    @Override
    public User findUser(int id) {
	return em.find(User.class, id);
	
    }
    
    public Adresse findAdresse(int id){
	return em.find(Adresse.class, id);
    }

    
    //ToDo anbinden an JSP wegen usernamen usw.
//    @Override
    public void updateUser(User user) {
	em.merge(user);	
    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public List<String> findUserAdresse(int id_user) {
//	List<Adresse> adresse = em.createQuery("SELECT a from Adresse a where a.user.id_user = " + id_user).getResultList();
//	List<String> result = new ArrayList<String>();
//	for(Adresse a: adresse ){
//	    result.add(a.getOrt());	    
//	    result.add(a.getStrasse());
//	}
//	return result;
//    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findUserAdresse(int id_user) {
	return em.createQuery("SELECT a.ort, a.strasse, a.plz from Adresse a where a.user.id_user = " + id_user).getResultList();
	
    }
    
    
    
    

    
    

}
