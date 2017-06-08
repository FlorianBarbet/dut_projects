import java.util.*;
/** Robot est une classe abstraite qui permettra de definir deux equipes de types Robot et instanciable avec son heritage
 * @see Robot#energie
 * @see Robot#cpt
 * @see Robot#portï¿½e
 * @see Robot#casD
 * @see Robot#position
 * @see Robot#Robot(int, int, int)
 * @see Robot#getEnergie()
 * @see Robot#setEnergie(int)
 * @see Robot#getEquipe()
 * @see Robot#incEquipe()
 * @see Robot#moveHaut()
 * @see Robot#moveBas()
 * @see Robot#moveDroite()
 * @see Robot#moveGauche()
 * @see Robot#action()
 * @see Robot#getDamage(Element r)
 * @see Robot#getDamage(Robot r)
 * @see Robot#deplacement(char)
 * @see Robot#toString()
 * 
 * @author BARBET Florian
 * @author LEICHT Theo
 * @author MURAT Edouard
 * @author SKAWAND Dylan
 * @author RICHARD Alexandre
 * @author CORNET Thomas
 * @author BRUNI XAVIER
 * 
 * @version 2.1.3
 */

abstract class Robot {
	/**Energie est la vitalite d'un Robot
	 * 
	 */
	protected int energie;
	/**Compteur static du nombre de Robot afin que lorsque 5 robots ont etait choisi l'equipe d'incremente automatiquement
	 * 
	 */

	/**Equipe permet de modifier les representations graphique et d'identifier les robots
	 * 
	 */
	private int equipe=1;
	/**Portï¿½e permet de donner la portee de tir d'un robot
	 * 
	 */
	protected int portee;
	/**Cases Deplacement, identifie le nombre de case pour un deplacement
	 * 
	 */
	protected int casD;

	/**Position permet de donner les coordonnee de chacun des robots
	 * 
	 */
	protected Coordonnee position;

	/**
	 * permet d'identifier un robot
	 */
	protected String nom;
	
	/**
	 * titre et informations pour le tip
	 */
	private String tip;

	/**
	 * permet de rendre le nom d'un robot unique si il est dans cette liste alors le nom est invalide
	 */
	private static ArrayList<String> nomUtilise = new ArrayList<String>();

	/**Constructeur basicsur l'energie, la portee et les cases de deplacement
	 * 	
	 * @see Robot#energie
	 * @see Robot#portee
	 * @see Robot#casD
	 * @param n
	 * @param p
	 * @param c
	 */
	public Robot(int n, int p, int c, int i, String t){
		energie = n; portee = p; casD = c; tip = t;
		setEquipe(i);


	}

	/**
	 * permet de nommer un robot et d'enregistrer son nom dans un tableau
	 */

	public void nommer(String tmp){

		if(!nomDejaPris(tmp)){
			nom=String.valueOf(tmp);
			nomUtilise.add(nom);
		}
		else
		{
			if(equipe == 1)
				tmp +="I";
			else
				tmp += "i";

			nommer(tmp);
		}
	}

	/**Permettra d'identifier si le nom choisit est deja dans la liste
	 * @param String n le nom du robot
	 * @return true si le nom est deja pris
	 */
	public static boolean nomDejaPris(String n){
		return nomUtilise.indexOf(n)!=-1?true:false;
	}

	/**Sert d'accesseur
	 * @see Robot#nomUtilise
	 * @return tout les noms utilisés
	 */
	public static ArrayList<String> getNomUse(){
		return nomUtilise;
	}

	/**
	 * permet de recuperer le nom d'un robot
	 * @return nom
	 */
	public String getName(){return nom;}


	/**Retourne la vitalite d'un robot
	 * @return energie
	 */
	public int getEnergie() {
		return energie;
	}

	/**Modifie la vitalite d'un robot
	 * @param energie 
	 */
	public void setEnergie(int energie) {
		this.energie = energie;
	}


	/** Retourne l'equipe d'un robot
	 * @return equipe
	 */
	public int getEquipe() {
		return equipe;
	}

	/**
	 * configure l'equipe d'un robot
	 * @param e 0 ou 1 selon l'equipe
	 */
	public void setEquipe(int e){
		equipe=e;
	}

	/**
	 * Permet de bouger un robot
	 * @param x la nouvelle abscisse du robot
	 * @param y la nouvelle ordonnee du robot
	 */
	public void move(int x, int y){
		this.position.modifRow(x);
		this.position.modifCol(y);
	}

	/**
	 * Mï¿½thode abstraite : dï¿½finition d'une action(Tir, Mine)
	 */
	public abstract void action();

	/**
	 * Mï¿½thode abstraite : dï¿½finition des dommage envoyï¿½s sur un Element
	 */
	public abstract void getDamage(Element r);

	/**
	 * Mï¿½thode abstraite : dï¿½finition des dommage envoyï¿½s sur un Robot
	 */
	public abstract int getDamage(Robot r);

	/**
	 * Mï¿½thode abstraite : dï¿½finition des touches de deplacements
	 * @param key
	 */
	public abstract void deplacement(int x, int y);

	/**
	 * Mï¿½thode abstraite : reprï¿½sentation
	 */
	public abstract String toString();
	
	/**
	 * @return l'entier correspondant a la case (plus pratique pour l'affichage)
	 */
	public abstract int toInteger();
	
	/**
	 * cases de deplacements et d'attaque possibles pour un robot
	 * @param x l'abscisse du robot
	 * @param y l'ordonnee du robot
	 * @return une ArrayList de Tuple correspondant aux cases avec actions possible
	 */
	public abstract ArrayList<Tuple> casesPossible(int x, int y);
	
	public String[] getTip(){
		String values = tip+"@equipe : "+(equipe==0?"bleu":"rouge")+"@energie : "+energie+"@portee : "+portee;
		String[] str = {tip, values};
		return str;
	}





}
