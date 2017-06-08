package traitements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Echantillon implements Traitement {

	private static final String MESSAGE_ERREUR_PARM_INCORRECT = "Un paramètre de type entier positif attendu";
	
	private String messageErreur = "";
	
	@Override
	public List<PointDonnees> calculer(List<PointDonnees> entree, String... params) {
		
		// Parser les paramètres
		if (params.length != 1) {
			
			messageErreur = MESSAGE_ERREUR_PARM_INCORRECT;
			return null;
		}
		
		int n;
		try {
			n = Integer.parseInt(params[0]);
		} catch (NumberFormatException e) {
			messageErreur = MESSAGE_ERREUR_PARM_INCORRECT;
			return null;
		}
		
		if (n <= 0) {
			messageErreur = MESSAGE_ERREUR_PARM_INCORRECT;
			return null;
		}
		
		
		List<PointDonnees> resultat = new ArrayList<>();
		for (int i = 0; i < entree.size(); i+= n) {
			resultat.add(entree.get(i));
		}
		
		messageErreur = "";
		return resultat;
	}

	@Override
	public String getNom() {
		return "Échantillonnage (1 valeur sur n)";
	}

	@Override
	public List<String> getNomsParams() {
		return Collections.singletonList("n (entier, pour prendre 1 valeur sur n)");
	}

	@Override
	public String getMessageErreur() {
		return messageErreur;
	}

}
