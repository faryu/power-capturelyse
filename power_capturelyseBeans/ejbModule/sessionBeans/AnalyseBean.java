package sessionBeans;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import entity.Verbrauch;
import entity.Wetter;
import interfaces.*;

@Stateless
public class AnalyseBean implements AnalyseInterface {

	@Override
	public BigDecimal mittlererVerbrauch(List<Verbrauch> verbrauchsdaten) {

		System.out.println(verbrauchsdaten.get(0).getZaehlerstand());
		BigDecimal neeee = (BigDecimal)verbrauchsdaten.get(0).getZaehlerstand();
		return neeee;
	}

	@Override
	public BigDecimal mittlereTemperatur(List<Wetter> wetterdaten) {
		// TODO Auto-generated method stub
		return null;
	}

}
