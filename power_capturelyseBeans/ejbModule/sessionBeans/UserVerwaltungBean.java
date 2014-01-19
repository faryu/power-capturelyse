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


		Query q = em.createQuery("select id_user from User where loginname = :uname");
		q.setParameter("uname", uname);
		return !q.getResultList().isEmpty();

	} 

    @Override
    public User findUser(int id) {
	return em.find(User.class, id);
	
    }
    
    public Adresse findAdresse(int id){
	return em.find(Adresse.class, id);
    }

        
//    @Override
    public void updateUser(User user) {
	em.merge(user);	
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public List<Adresse> findUserAdresse(int id_user) {
    Query query = em.createQuery("SELECT a from Adresse a where a.user.id_user = :id_user" );
    query.setParameter("id_user", id_user);
    List<Adresse> resultList = (List<Adresse>) query.getResultList();
       
    
	return resultList;
	
    }



	@SuppressWarnings("unchecked")
	@Override
	public User findUserLoginName(String uname, String password) {
		Query q = em.createQuery("select id_user from User where loginname = :uname AND password = :password");
		q.setParameter("uname", uname);
		q.setParameter("password", password);		
		List<Integer> result = q.getResultList();
		if(result.isEmpty())
		{
			return null;
		}
		else
		{
			return findUser(result.get(0));
		}
	} 

}
