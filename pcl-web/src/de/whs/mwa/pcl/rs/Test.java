package de.whs.mwa.pcl.rs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("Test")
public class Test {

	@GET()
	@Produces("text/plain")
	@Path("{test}")
	public String Testing(@PathParam("test") final String test) {
		return "This is the test string: " + test;
	}

	@GET()
	@Path("Name/{name}")
	@Produces("text/plain")
	public String TestName(@PathParam("name") final String name, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		String last = (String) session.getAttribute("pcl-data");
		
		String ret = "Last entered name: ";
		if (last != null)
			ret += last;
		ret += "\n";
		session.setAttribute("pcl-data", name);
		ret += "Current name: " + name;
		return ret;
	}
}
