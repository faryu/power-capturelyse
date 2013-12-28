package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
//import javax.persistence.SecondaryTable;
//import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="tb_user")
//@SecondaryTable(name="tb_adresse")

public class User implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3617527092249858891L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_user", nullable=false)
    private int id_user;
    @Column(name="username",nullable=false, length=45)
    private String username;
    @Column(name="uservname",nullable=false, length=45)
    private String uservname;
    @Column(name="password",nullable=false, length=45)
    private String password;
    
    //Adresse
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Adresse> adresse;
           
    public User(){
    	
    }
    
    public User(int id_user, String username, String uservname, String password){
	this.id_user = id_user;
	this.username = username;
	this.uservname = uservname;
	this.password = password;
	
    }
    
    public User(String username, String uservname, String password){	
	this.username = username;
	this.uservname = uservname;
	this.password = password;
	
    }
    
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getUservname() {
        return uservname;
    }

    public void setUservname(String uservname) {
        this.uservname = uservname;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getPassword()
    {
    	return password;
    }
    
       
    public List<Adresse> getAdresse(){
	return adresse;
    }
    
    public void setAdresse(List<Adresse> adresse){
	this.adresse = adresse;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id_user;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	User other = (User) obj;
	if (id_user != other.id_user)
	    return false;
	return true;
    }
    
    
    
    
    
    
    
    
    
	

}
