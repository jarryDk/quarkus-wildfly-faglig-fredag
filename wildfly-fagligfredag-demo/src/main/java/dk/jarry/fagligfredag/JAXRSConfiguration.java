package dk.jarry.fagligfredag;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 */
@ApplicationPath("/resources")
public class JAXRSConfiguration extends Application {

	@SuppressWarnings("rawtypes")
	public JAXRSConfiguration(@Context ServletConfig servletConfig) {
		super();
		OpenAPI oas = new OpenAPI();
		Info info = new Info() //
				.title("ToDo demo app") //
				.description("This is a sample ToDO app.") //
				// .termsOfService("http://swagger.io/terms/")
				.contact(new Contact() //
						.email("MichaelBornholdtNielsen@gmail.com")) //
				.license(new License() //
						.name("Apache 2.0") //
						.url("http://www.apache.org/licenses/LICENSE-2.0.html"));

		oas.info(info);
		SwaggerConfiguration oasConfig = new SwaggerConfiguration() //
				.openAPI(oas) //
				.prettyPrint(true) //
				.resourcePackages(Stream.of("dk.jarry.fagligfredag.todo").collect(Collectors.toSet()));

		try {
			new JaxrsOpenApiContextBuilder() //
					.servletConfig(servletConfig) //
					.application(this) //
					.openApiConfiguration(oasConfig) //
					.buildContext(true);
		} catch (OpenApiConfigurationException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}