import java.util.ArrayList;
/**Permet d'indiquer la différence etre une IA et un Homme ; tel que l'une des deux classe est basé sur le Random tandis que l'autre sur le Scanner
 * 
 * @author Florian BARBET
 * @author Theo LEICHT
 * @author Xavier BRUNI
 * @author Edouard MURAT
 * @author Thomas CORNET
 * @author Dylan SKAWAND
 * @author Alexandre RICHARD
 *
 */
abstract class Utilisateur {

	/**
	 * Entier equipe permettra de clarifier quelques parties de code liï¿½ aux equipes
	 */
	protected int equipe;

	/**
	 * Permet d'identifier une liste de touche que l'utilisateur pourra utiliser en plein jeu.
	 */

	protected ArrayList<Character> action= new ArrayList<Character>();

	/**
	 * Constructeur d'un utilisateur definition des actions possibles.
	 * @param e
	 */
	public Utilisateur(int e){
		this.equipe=e;
		action.add('A'); // Bie haut
		action.add('E'); // Diagonal haut
		action.add('W'); // Diagonal Bas
		action.add('C'); // Bie bas
		action.add('Z'); // aller en Haut
		action.add('S'); // aller en Bas
		action.add('Q'); // aller a Gauche
		action.add('D'); // aller a Droite
		action.add('M'); // pour Poser une mine
		action.add('T'); // Pour Tirer 
		action.add('1');
	
	}

	/**
	 * accesseur au niveau de l'equipe
	 * @return equipe
	 */
	public int getEquipe() {
		return equipe;
	}

	/**
	 * accesseur à la liste d'action possible
	 * @return liste d'action
	 */
	public ArrayList<Character> getAction() {
		return action;
	}

	/**
	 * Permet de rendre l'affichae plus esthetique avec un rafraichissement
	 */
	public void clearScreen() {
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}


	/**
	 * Impression de la carte apres chaque tour
	 * @param r
	 */
	public void printgame(char r, Monde m) {

		m.afficher();

	}

	/**Permet a l'utilisateur de commander les robot il s'agit de la methode la plus importante car elle permet de deplacer tirer etc..
	 * 
	 * @see Humain#readKey()
	 * @see Utilisateur#printgame(char)
	 * @see Humain#isPossible(char)
	 * @see Utilisateur#clearScreen()
	 * @see Utilisateur#action
	 * @see IA#randomChoice
	 * 
	 * 
	 * @see Monde#movebot(char, int, int, char, int)
	 * @see Monde#getTeam(int)
	 * @see Monde#poseMine(Robot)
	 * 
	 * @param p
	 */
	abstract public void userAction(int n,Monde m);
	
	/**
	 * Permet a l'utilisateur de choisir un robot par son nom donnee la creation
	 * @param eq indice d'equipe
	 * @param m il s'ait de la carte @see Jouer#map
	 * 
	 * @return int indice sur une ArrayList
	 */
	abstract public int userChoice(int eq, Monde m);


}
