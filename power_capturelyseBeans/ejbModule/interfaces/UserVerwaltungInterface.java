package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.Adresse;
import entity.User;

@Remote
public interface UserVerwaltungInterface {
    
    public void createUser(User user);
    //Bitte drin lassen fuer mich zum testen - steffi 
    public User createUser(User user, Adresse adresse);
    boolean exists(String uname);
    public User findUser(int id);
    public void updateUser(User user);
//    public List<String> findUserAdresse(int id_user);
    public List<Adresse> findUserAdresse(int id_user);
    public Adresse findAdresse(int id);   
    public User findUserLoginName(String uname, String password);
}
