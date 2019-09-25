package dk.jarry.fagligfredag.todo.control;

/**
 * https://www.youtube.com/watch?v=iEEfNB05ado
 *
 */
public class DemoPrincipal {

	private String name;

	public DemoPrincipal(String name) {
		this.name = name;
	}

	public String getName() {
		
		if("micbn".contentEquals(this.name)){
			/**
			 * Pretend we do a database lookup ;o)
			 */
			return "Michael Bornholdt Nielsen";
		}
		
		return name;
	}

}
