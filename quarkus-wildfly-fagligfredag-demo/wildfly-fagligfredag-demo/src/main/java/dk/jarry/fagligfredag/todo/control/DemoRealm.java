package dk.jarry.fagligfredag.todo.control;

import java.security.Principal;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@Stateless
public class DemoRealm {
	
	@Inject
	Principal principal;
	
	@Produces
	public DemoPrincipal fetch() {
		String name = principal.getName();		
		DemoPrincipal demoPrincipal = new DemoPrincipal(name);				
		return demoPrincipal;
	}
	
}
