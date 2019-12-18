package dk.jarry.fagligfredag.todo.boundary;

import java.util.HashMap;

import org.eclipse.microprofile.jwt.Claims;

/**
 * A simple utility class to generate and print a JWT token string to stdout.
 * Can be run with: mvn exec:java
 * -Dexec.mainClass=dk.jarry.fagligfredag.todo.boundary.GenerateToken
 * -Dexec.classpathScope=test
 */
public class GenerateToken {
	/**
	 *
	 * @param args - [0]: optional name of classpath resource for json document of
	 *             claims to add; defaults to "/JwtClaims.json" [1]: optional time
	 *             in seconds for expiration of generated token; defaults to 300
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String claimsJson = "/JwtClaims.json";
		if (args.length > 0) {
			claimsJson = args[0];
		}
		HashMap<String, Long> timeClaims = new HashMap<>();
		if (args.length > 1) {
			long duration = Long.parseLong(args[1]);
			long exp = TokenUtils.currentTimeInSecs() + duration;
			timeClaims.put(Claims.exp.name(), exp);
		}
		String token = TokenUtils.generateTokenString(claimsJson, timeClaims);
		
		String ip ="localhost";
		String port ="8080";
		
		ip ="192.168.39.24";
		port = "32744";
		
		System.out.println("");
		System.out.println("export TOKEN=\"" + token + "\"");

		System.out.println("");

		System.out.println("curl -v http://"+ip+":"+port+"/todos \\");
		System.out.println("	-H 'Accept: application/json' \\");
		System.out.println("	-H 'Authorization: Bearer '$TOKEN'' \\");
		System.out.println("	-H 'Content-Type: application/json' \\");
		System.out.println(
				"	-d '{\"subject\":\"Hello from Quarkus\",\"body\":\"Content\",\"priority\": 1,\"importens\": 10,\"owner\" : \"Duke\"}'");

	}
}