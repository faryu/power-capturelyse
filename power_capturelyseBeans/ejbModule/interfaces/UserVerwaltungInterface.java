package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.Adresse;
import entity.User;

@Remote
public interface UserVerwaltungInterface {
    
    public void createUser(User user);
    boolean exists(String uname);
    public User findUser(int id);
    public void updateUser(User user);
//    public List<String> findUserAdresse(int id_user);
    public List<Object []> findUserAdresse(int id_user);
    public Adresse findAdresse(int id);
    

}
