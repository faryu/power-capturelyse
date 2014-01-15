package interfaces;


import java.math.BigDecimal;
import java.sql.Date;

import javax.ejb.Remote;

import java.util.Map;

@Remote
public interface VerbrauchBerechungInterface {

	public Map<Date,BigDecimal> getIntervallZaehlerstaende(int zaehlerid, Date anfang, Date ende);
	
}
