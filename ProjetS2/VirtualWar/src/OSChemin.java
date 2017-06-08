

/**
 * Convertit un chemin Windows en chemin Linux si besoin
 * @author Xavier
 *
 */

public class OSChemin {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	public static String convert(String s){
		if(OS.equals("linux")){
			return s.replace('\\', '/');
		}
		return s;
	}
	
}
