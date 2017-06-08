package testplugins;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import traitements.Traitement;
import traitements.Traitement.PointDonnees;

public class Main {
	
	
	public static void main(String[] args) {
		
		// Créer la liste en entrée pour le test
		String formatDate = "%d/05/2017";
		
		List<Traitement.PointDonnees> entree = new ArrayList<>();
		for (int i = 1; i <= 30; i++) {
			String date = String.format(formatDate, i);
			entree.add(new PointDonnees(date, i));
		}
		
		// Charger la classe du plugin
		File file = new File("/tmp/classes/");
		// Les classes doivent être dans ce répertoire sous une arborescence de dossiers qui correspond à leurs packages
		
		
		Class cls = null;
		try {
		    // Convert File to a URL
		    URL url = file.toURL();          
		    URL[] urls = new URL[]{url};

		    // Create a new class loader with the directory
		    ClassLoader cl = new URLClassLoader(urls);

		    // Echantillon.class doit être dans /tmp/classes/traitements
		    cls = cl.loadClass("traitements.Echantillon");
			System.out.println(cls);
			
		} catch (MalformedURLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			Scanner scan = new Scanner(System.in);
			Traitement traitement = (Traitement) cls.newInstance();
			
			List<String> params = new ArrayList<>();
			for (String s : traitement.getNomsParams()) {
				System.out.println("Donner valeur pour " + s + " :");
				String paramString = scan.nextLine();
				params.add(paramString);
			}
			
			List<PointDonnees> donneesTraitees = traitement.calculer(entree, params.toArray(new String[0]));
			if (donneesTraitees == null)
				System.out.println(traitement.getMessageErreur());
			else {
				for (PointDonnees pd : donneesTraitees) {
					System.out.println(pd.date + "\t" + pd.val);
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
