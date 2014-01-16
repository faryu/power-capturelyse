package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.Energietyp;

@Remote
public interface EnergietypVerwaltungInterface {
	
	public Energietyp createEnergietyp(Energietyp energietyp);
	public void updateEnergietyp(Energietyp energietyp);
	public List<Energietyp> showEnergietypen();
	public void deleteEnergietyp(Energietyp energietyp);

}
