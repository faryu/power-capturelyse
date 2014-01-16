package interfaces;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import entity.Verbrauch;
import entity.Wetter;

@Remote
public interface AnalyseInterface {
	
	public BigDecimal mittlererVerbrauch(List<Verbrauch> verbrauchsdaten);
	public BigDecimal mittlereTemperatur(List<Wetter> wetterdaten);

}
