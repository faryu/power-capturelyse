package serviceLocator;


import interfaces.UserVerwaltungInterface;
import interfaces.VerbrauchVerwaltungInterface;

public interface ServiceLocatorInterface {
    
    public UserVerwaltungInterface getUserVerwaltung();
    public VerbrauchVerwaltungInterface getVerbrauchVerwaltung();

}
