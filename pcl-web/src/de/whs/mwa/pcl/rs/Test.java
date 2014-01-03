package de.whs.mwa.pcl.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("Test")
public class Test {
	@GET()
	@Produces("text/plain")
	public String Testing()
	{
		return "This is the test string";
	}

	@GET()
	@Path("Name/{name}")
	@Produces("text/plain")
	public String TestName(@PathParam("name") final String name)
	{
		return "Hello " + name;
	}
}
