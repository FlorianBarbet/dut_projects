package traitements;

import java.util.List;

public interface Traitement {
	
	public class PointDonnees {
		public final String date;
		public final Number val;
		public PointDonnees(String date, Number val) {
			this.date = date;
			this.val = val;
		}
	}

	// Je vous fais une version à peu près complète.
	// Dans une version minimale vous pourrez prendre juste un traitement sans paramètres supplémentaires, et ne garder que 
	//   les méthodes calculer(List<PointDonnees>) et getNom()
	
	/** Effectue le calcul associé au traitement.
	 * 
	 * @param entree La série en entrée
	 * @param params La liste des paramètres nécessaires pour le calcul. Pour des raisons de simplicité, on prend des String que la méthode se chargera de décoder vers les bonnes valeurs.
	 * @return Une série en sortie, ou null si le calcul n'a pas été possible. Dans ce second cas, {@link #getMessageErreur} retournera une explication de l'erreur. 
	 */
	public List<PointDonnees> calculer (List<PointDonnees> entree, String ... params);
	
	/** Le nom du traitement, à afficher e.g. dans un menu.  
	 * 
	 * @return
	 */
	public String getNom ();
	
	
	// Méthohes optionnelles
	/** Les noms des paramètres, dans le bon ordre.
	 * À utiliser lorsqu'il faudra choisir des valeurs pour les paramètres de {@link #calculer(List, String...)}
	 * 
	 * @return
	 */
	public List<String> getNomsParams ();
	
	/** Le message d'erreur à afficher si le dernier appel à {@link #calculer(List, String...)} a retourné null.
	 * Pour aider l'utilisateur.
	 * 
	 * @return
	 */
	public String getMessageErreur ();
}
