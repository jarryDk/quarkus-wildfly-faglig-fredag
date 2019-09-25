package dk.jarry.fagligfredag.ping.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("pings")
public class PingResource {
	
	@Inject
	@ConfigProperty(name = "pingMessage", defaultValue = "pingMessage need config..")
	String message;
	
	@GET
	public String ping() {
		return "ping - message : " + message;
	}
	

}
