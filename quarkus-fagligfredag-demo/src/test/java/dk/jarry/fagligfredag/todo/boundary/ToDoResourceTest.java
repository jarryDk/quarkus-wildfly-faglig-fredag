package dk.jarry.fagligfredag.todo.boundary;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.net.HttpURLConnection;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

/**
 * Tests of the ToDoResourceTest REST endpoints
 */
@QuarkusTest
public class ToDoResourceTest {

    /**
     * The test generated JWT token string
     */
    private String token;

    @BeforeEach
    public void generateToken() throws Exception {
        HashMap<String, Long> timeClaims = new HashMap<>();
        token = TokenUtils.generateTokenString("/JwtClaims.json", timeClaims);
    }

    @Test
    public void testHelloEndpoint() {
        Response response = given()
          .when()
          .get("/todos/permit-all")
          .andReturn();

        response.then()
          .statusCode(200)
          .body(containsString("hello + anonymous, isSecure: false, authScheme: null, hasJWT: false"));
    }

    @Test
    public void testHelloRolesAllowed() {
        Response response = given().auth()
                .oauth2(token)
                .when()
                .get("/todos/roles-allowed").andReturn();

        response.then()
          .statusCode(200)
          .body(containsString("hello + micbn@redpill-linpro.com, isSecure: false, authScheme: Quarkus, hasJWT: true"));
    }

    @Test
    public void testHelloDenyAll() {
        Response response = given().auth()
                .oauth2(token)
                .when()
                .get("/todos/deny-all").andReturn();

        Assertions.assertEquals(HttpURLConnection.HTTP_FORBIDDEN, response.getStatusCode());
    }

}