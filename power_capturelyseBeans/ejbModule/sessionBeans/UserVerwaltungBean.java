package sessionBeans;

//import java.util.ArrayList;
import java.util.List;

import interfaces.UserVerwaltungInterface;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public void createUser(User user) {
		em.persist(user);
	}

	@Override
	public boolean exists(String uname) {
		// TODO Auto-generated method stub
		return false;
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

    
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findUserAdresse(int id_user) {
    Query query = em.createQuery("SELECT a.ort, a.strasse, a.plz from Adresse a where a.user.id_user = :id_user" );
    query.setParameter("id_user", id_user);
	return query.getResultList();
	
    }

	@Override
	public String getPasswort(String uname) {
		// TODO Auto-generated method stub
		return null;
	} 

}
