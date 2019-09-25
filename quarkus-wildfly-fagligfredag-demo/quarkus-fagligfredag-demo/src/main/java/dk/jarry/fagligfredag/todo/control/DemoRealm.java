package dk.jarry.fagligfredag.todo.control;

import java.util.Base64;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class DemoRealm {
	
	/**
	 * https://github.com/quarkusio/quarkus/issues/2528<br>
	 * Looking forward to use java.security.Principal
	 */

	@Inject
	HttpServletRequest request;

	/**
	 * authorization : Basic bWljYm46TXlQYXNzd29yZA==
	 */
	@Produces
	@RequestScoped
	public DemoPrincipal fetch() {

		String name = "anonymous";

		if (request != null) {

			String authorization = request.getHeader("Authorization");
			if (authorization != null && !authorization.isEmpty()) {
				try {
					String base64 = authorization.split(" ")[1];
					String userPassword = new String(Base64.getDecoder().decode(base64));
					name = userPassword.split(":")[0];	
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
			}
		}

		DemoPrincipal demoPrincipal = new DemoPrincipal(name);
		return demoPrincipal;
	}

}
