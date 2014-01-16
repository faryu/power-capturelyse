package de.whs.mwa.pcl.rs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;

public class EnergyType {
	@FormParam("id")
	private int id;
	@FormParam("name")
	private String name;
	@FormParam("unit")
	private String unit;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	Map<String, String> errors;

	public void addError(String key, String msg) {
		if (errors == null)
			errors = new HashMap<String, String>();
		errors.put(key, msg);
	}
}
