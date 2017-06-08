import java.util.*;
/**Monde permet la creation de la carte, et certaines actions des autres classes.
 * 
 * @see Monde#nbCol
 * @see Monde#nbRow
 * @see Monde#grille
 * @see Monde#equipe1
 * @see Monde#equipe2
 * @see Monde#Monde(int, int)
 * @see Monde#createTeam()
 * @see Monde#nbObstacle()
 * @see Monde#ajouterRobot(String)
 * @see Monde#askBot()
 * @see Monde#setVide()
 * @see Monde#randomBorne(int, int)
 * @see Monde#setObstacle(double)
 * @see Monde#movebot(char, int, int, char, int)
 * @see Monde#poseMine(Robot)
 * @see Monde#getTeam(int)
 * @see Monde#placeRandom(Element)
 * @see Monde#isFree(int, int)
 * @see Monde#placeElement(int, int, Element)
 * @see Monde#setEmpty(int, int)
 * @see Monde#getElement(int, int)
 * @see Monde#afficher()
 *
 * @author BARBET Florian
 * @author LEICHT Theo
 * @author MURAT Edouard
 * @author SKAWAND Dylan
 * @author RICHARD Alexandre
 * @author CORNET Thomas
 * @author BRUNI XAVIER
 *
 */
class Monde{
	/**
	 * Nombre de colonnes de la grille
	 */
	private int nbCol;
	/**
	 * Nombre de lignes de la grille
	 */
	private int nbRow;
	/**
	 * Grille d'element permet donc d'y placer divers obstacles, vide, Mine, autres
	 */

	private Element[][] grille;
	/**
	 * Liste de Robot 1 permet d'identifier l'equipe1
	 */
	private ArrayList<Robot> equipe1 = new ArrayList<Robot>();
	/**
	 * Liste de Robot 2 permet d'identifier l'equipe2
	 */
	private ArrayList<Robot> equipe2 = new ArrayList<Robot>();
	/**
	 * Permet une demande de quantite dans l'equipe >1 && <5
	 */
	private int nb_robot=3;

	/**Constructeur du Monde prennant en paramï¿½tres ses dimensions, et utilises 3 methodes de crï¿½ations !
	 * 
	 * @param row
	 * @param col
	 * @throws OutOfMapException 
	 * @throws CannotMoveException 
	 * 
	 * @see Monde#setVide()
	 * @see Monde#createTeam()
	 * @see Monde#setObstacle(double)
	 * 		@see Monde#nbObstacle()
	 */
	Monde(int row, int col) throws OutOfMapException, CannotMoveException{
		this.nbRow = row;
		this.nbCol = col;
		this.grille = new Element[col][row];
		System.out.println("Nombre de robot par equipe ?");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int test= sc.nextInt();
		if(test > 1 && test < 6 )nb_robot=test;
		setVide();
		createTeam();


		setObstacle(nbObstacle());

	}
	/**
	 *  Permet de crï¿½er les deux equipes, par choix utilisateur, obligation d'equipe de 5
	 * @throws OutOfMapException 
	 * @throws CannotMoveException 
	 *  @see Monde#askBot()
	 *  @see Monde#ajouterRobot(String)
	 */
	private void createTeam() throws OutOfMapException, CannotMoveException{
		System.out.println("Equipe1 >>");
		boolean tir1=true,tir2=true,pieg1=true,pieg2 = true;
		for(int i = 0 ; i < nb_robot;i++){
			String r=askBot();
			if((r.equals("T") || r.equals("Tireur") || r.equals("tireur") || r.equals("t") )&& tir1 )
			{equipe1.add(ajouterRobot("tireur",1));}
			if((r.equals("P") || r.equals("Piegeur") || r.equals("piegeur") || r.equals("p")) && pieg1 )
			{equipe1.add(ajouterRobot("piegeur",1));}
			if(r.equals("C") || r.equals("Char") || r.equals("char") || r.equals("c") )
				equipe1.add(ajouterRobot("char",1));
		}

		System.out.println("Equipe2 >>");
		for(int i = 0 ; i < nb_robot;i++){
			String r = askBot();
			if(( r.equals("T") || r.equals("Tireur") || r.equals("tireur") || r.equals("t")) && tir2)
			{equipe2.add(ajouterRobot("tireur",2));}
			if((r.equals("P") || r.equals("Piegeur") || r.equals("piegeur") || r.equals("p")) && pieg2)
			{equipe2.add(ajouterRobot("piegeur",2));}
			if(r.equals("C") || r.equals("Char") || r.equals("char") || r.equals("c") )
				equipe2.add(ajouterRobot("char",2));
		}



	} 
	/**Calcul permettant de donner un pourcentage du nombre d'obstacle par choix utilisateur entre 10 et 30%
	 * 
	 * @return a (choix utilisateur) si condition respectï¿½, Iteration de la methode sinon
	 */
	private double nbObstacle(){
		System.out.println("pourcentage d'obstacle (0 ; 0.3) >>");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		double a = sc.nextDouble();
		if(a==0.0)return -1.0;
		else if(a<=0.3)return a;
		else return nbObstacle();
	}
	/**Methode permettant d'ajouter un Robot dans une equipe, selon le parametre choix
	 * 
	 * @param choix
	 * @return c Char | p Piegeur | t Tireur [ ajouterRobot(choix)
	 * @throws CannotMoveException 
	 * @throws OutOfMapException 
	 */
	private Robot ajouterRobot(String choix, int eq) throws CannotMoveException{ 

		int col ;
		int row;
		try {
			col = randomBorne(0, nbCol-1);
			row = randomBorne(0, nbRow-1);
			if(isFree(col,row)){
				Piegeur p = new Piegeur(col, row, nbCol, nbRow,eq); 
				Char c = new Char(col, row, nbCol, nbRow,eq);
				Tireur t = new Tireur( col, row, nbCol, nbRow,eq) ;



				if(choix == "char"){c.nommer("c");return c;}
				if(choix == "piegeur"){p.nommer("p");return p;}
				if(choix == "tireur"){t.nommer("t");return t;}


			}else return ajouterRobot(choix, eq);
		} catch (OutOfMapException e ) {
			System.out.println(e);
		}
		return null;

	}


	/**Permet de demander ï¿½ l'utilisateur son choix au niveau des robots.
	 * 
	 * @return r String (Scanner uses)
	 */
	private String askBot(){
		System.out.println("\tChoix robots: T, P, C"); 
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in); 
		String r=sc.nextLine();
		return r;
	}
	/**
	 * Dï¿½finit les cases de type Vide.
	 */
	private void setVide() {
		for (int i = 0; i < nbCol; i++) {
			for (int j = 0; j < nbRow; j++) {
				grille[i][j] = new Vide();
			}
		}
	}
	/**Methode permettant un calcul bornee (pseudo-)aleatoire 
	 * 
	 * @param min
	 * @param max
	 * @return entier aleatoire entre min et max
	 */
	private int randomBorne(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}
	/**Defini le nombre d'obstacle selon une position aleatoire
	 * 
	 * @param nb (Double)
	 * @see Monde#placeRandom(Element)
	 */
	private void setObstacle(double nb){
		int nbObstacle = (int)( (this.nbCol)*(this.nbRow)*nb);
		if(nb == -1.0)nbObstacle = 0;
		for(int i=0; i<=nbObstacle; i++){
			this.placeRandom(new Obstacle());
		}
	}

	/**Permet de deplacer les robot selon la methode deplacement (verification de passage)
	 * 
	 * 
	 * @param c permet didentifier l'equipe
	 * @param x la modification sur la colonne
	 * @param y la modification sur la ligne
	 * @param a action mene
	 * @param i index du robot
	 * @param ia methode d'accès pour ia
	 * 
	 * @see Monde#isFree(int, int)
	 * @see Monde#getElement(int, int)
	 * @see Robot#deplacement(char)
	 */
	public void movebot(char c, int x, int y, char a, int i,boolean ia) throws CannotMoveException, OutOfMapException{
		ArrayList<Robot> e = new ArrayList<Robot>();

		if (c == '1')
			e = equipe1;
		else
			e = equipe2;
		
		if (isFree(e.get(i).position.getCol()+x, e.get(i).position.getRow()+y) && !getElement(e.get(i).position.getCol()+x,e.get(i).position.getRow()+y).equals("O")) {

			getElement(e.get(i).position.getCol() + x, e.get(i).position.getRow() + y).setFind();//pour decouvrir les mines
			switch (a) {
			case 'Z':case 'z':
				e.get(i).deplacement('Z');
				break;
			case 'S':case's':
				e.get(i).deplacement('S');
				break;
			case 'Q':
				e.get(i).deplacement('Q');
				break;
			case 'D':case 'd':
				e.get(i).deplacement('D');
				break;
			case 'A' :case'a':
				if((e.get(i).toString().equals("C") || e.get(i).toString().equals("c")) && ia )((Char)e.get(i)).deplacement4IA('A');
				else
					e.get(i).deplacement('A');

				break;
			case 'E' :case'e':
				if((e.get(i).toString().equals("C") || e.get(i).toString().equals("c")) && ia)((Char)e.get(i)).deplacement4IA('E');
				else
					e.get(i).deplacement('E');
				break;
			case 'W' :case'w':
				if((e.get(i).toString().equals("C") || e.get(i).toString().equals("c")) && ia)((Char)e.get(i)).deplacement4IA('W');
				else
					e.get(i).deplacement('W');
				break;
			case 'C' :case'c':
				if((e.get(i).toString().equals("C") || e.get(i).toString().equals("c")) && ia)((Char)e.get(i)).deplacement4IA('C');
				else
					e.get(i).deplacement('C');
				break;

			}
		} else {
			throw new CannotMoveException("Mouvement impossible");
		}
	}
	/**
	 * Donne l'ordre au Robot r de tirer vers un autre robot cible si conditions respectee
	 * @param r est le tireur ou le char qui tir
	 * @param cible
	 * @throws OutOfMapException 
	 * @throws CannotMoveException 
	 */
	public void tirer(Robot r,Robot cible) throws OutOfMapException, CannotMoveException{
		if(r.getEquipe() != cible.getEquipe()){
			int res = 0;//booleen si 0 alors on peut tirer
			if(r.position.getCol()==cible.position.getCol()){


				if(r.position.getRow()<cible.position.getRow()){
					for(int i = cible.position.getRow();i<r.position.getRow();i++){

						if(!isFree(cible.position.getCol()-1,cible.position.getRow()))
							res = -1;

					}


				}else{
					for(int i = cible.position.getRow();i>r.position.getRow();i--){

						if(!isFree(cible.position.getCol()-1,cible.position.getRow()))
							res = -1;

					}
				}

			}
			else if(r.position.getRow() == cible.position.getRow()){


				if(r.position.getCol()<cible.position.getCol()){
					for(int i = cible.position.getCol();i<r.position.getCol();i++){

						if(!isFree(cible.position.getCol(),cible.position.getRow()-1))
							res = -1;

					}


				}else{
					for(int i = cible.position.getCol();i>r.position.getCol();i--){

						if(!isFree(cible.position.getCol(),cible.position.getRow()-1))
							res = -1;

					}
				}

			}else{
				if(r.position.getCol()<cible.position.getCol()){
					for(int i = cible.position.getCol();i<r.position.getCol();i++){

						if(!isFree(cible.position.getCol(),cible.position.getRow()-1))
							res = -1;

					}


				}else{
					if(r.position.getCol()<cible.position.getCol()){
						for(int i = cible.position.getCol();i>r.position.getCol();i--){

							if(!isFree(cible.position.getCol(),cible.position.getRow()-1))
								res = -1;

						}
					}

					if(r.position.getRow()<cible.position.getRow()){
						for(int i = cible.position.getRow();i<r.position.getRow();i++){

							if(!isFree(cible.position.getCol()-1,cible.position.getRow()))
								res = -1;

						}


					}else{
						for(int i = cible.position.getRow();i>r.position.getRow();i--){

							if(!isFree(cible.position.getCol()-1,cible.position.getRow()))
								res = -1;

						}
					}
				}
			}

			if(res == 0){
				switch(r.toString()){
				case "c":case"C":
					if(cible.position.getCol() == r.position.getCol()-2 || cible.position.getCol() == r.position.getCol()+2)
						r.getDamage(cible);
					else if(cible.position.getCol() == r.position.getCol()-1 || cible.position.getCol() == r.position.getCol()+1)
						r.getDamage(cible);
					else if(cible.position.getRow() == r.position.getRow()-2 || cible.position.getRow() == r.position.getRow()+2)
						r.getDamage(cible);
					else if(cible.position.getRow() == r.position.getRow()-1 || cible.position.getRow() == r.position.getRow()+1)
						r.getDamage(cible);

					r.action();
					break;

				case "t" : case "T":  

					if(cible.position.getCol()-1 == r.position.getCol()&&cible.position.getRow()+1 == r.position.getRow())//cible bie haut
						r.getDamage(cible); 
					else if(cible.position.getCol() == r.position.getCol()&&cible.position.getRow()+1 == r.position.getRow())//cible haut
						r.getDamage(cible);
					else if(cible.position.getCol()+1 == r.position.getCol()&&cible.position.getRow()+1 == r.position.getRow())//cible dia haut
						r.getDamage(cible);

					else if(cible.position.getCol()-1 == r.position.getCol() && cible.position.getRow() == r.position.getRow())//cible gauche
						r.getDamage(cible);
					else if(cible.position.getCol()+1 == r.position.getCol()&&cible.position.getRow() == r.position.getRow())//cible droite
						r.getDamage(cible);

					else if(cible.position.getCol()-1 == r.position.getCol()&&cible.position.getRow()-1 == r.position.getRow())//bas gauche
						r.getDamage(cible);
					else if(cible.position.getCol()-1 == r.position.getCol() && cible.position.getRow() == r.position.getRow())//bas
						r.getDamage(cible);
					else if(cible.position.getCol()-1 == r.position.getCol() && cible.position.getRow()+1 == r.position.getRow())//bas droite
						r.getDamage(cible);

					r.action();
					break;
				default: System.out.println("Erreur vous n'avez pas choisis un char ni un tireur : recommencez >>>");

				break;
				}

			}else System.out.println("vous ne pouvez pas tirer sur ce robot car il est hors de portï¿½e");
		}else{
			System.out.println("Ce joueur est de votre equipe !");
		}
	}

	/**
	 * Permet de poser une mine verification de la classe instanciï¿½ par le toString()
	 * @param r
	 * @throws OutOfMapException 
	 * @throws CannotMoveException 
	 */
	public void poseMine(Robot r, char c) throws OutOfMapException, CannotMoveException{
		if(r.toString().equals("P") || r.toString().equals("p")) {

		
			switch(c){

			case '9':if(isFree(r.position.getRow()+1,r.position.getCol()+1))((Piegeur) r).actionp(c);
			break;

			case '8':if(isFree(r.position.getRow()+1,r.position.getCol()))((Piegeur) r).actionp(c);
			break;

			case '7':if(isFree(r.position.getRow()+1,r.position.getCol()-1))((Piegeur) r).actionp(c);
			break;

			case '6':if(isFree(r.position.getRow(),r.position.getCol()+1))((Piegeur) r).actionp(c);
			break;

			case '4': if(isFree(r.position.getRow(),r.position.getCol()-1))((Piegeur) r).actionp(c);
			break;

			case '3':if(isFree(r.position.getRow()-1,r.position.getCol()+1))((Piegeur) r).actionp(c);
			break;

			case '2':if(isFree(r.position.getRow()-1,r.position.getCol()))((Piegeur) r).actionp(c);
			break;

			case '1' : if(isFree(r.position.getRow()-1,r.position.getCol()-1))((Piegeur) r).actionp(c);
			break;
			default:poseMine(r,c);break;
			}



		}

	}
	/**Renvoie la liste de robot selon ne (aurait pu etre un booleen)
	 * 
	 * @param ne
	 * @return une equipe
	 */
	public ArrayList<Robot> getTeam(int ne){
		if(ne == 1)return equipe1;
		else return equipe2;}
	/**
	 * Permet de placer un element ï¿½ un endroit aleatoire sur la grille
	 * 
	 * @see Monde#randomBorne(int, int)
	 * @param e
	 */
	public void placeRandom(Element e){
		int col = randomBorne(0, nbCol - 1);
		int row = randomBorne(0, nbRow - 1);
		if (grille[col][row] instanceof Vide) {
			grille[col][row] = e;
		} else {
			placeRandom(e);
		}
	}

	/** Verification de la posibilite de passage (est ce que la case(x,y) est vide ?)
	 * 
	 * @param x
	 * @param y
	 * @return booleen verifiant le toString de la grille
	 */
	public boolean isFree(int x, int y) throws OutOfMapException, CannotMoveException{

		boolean result = true;
		if (x == nbCol || x < 0 || y == nbRow || y < 0)
			throw new OutOfMapException("Vous ne pouvez pas sortir de la carte.");

		else if (grille[x][y].toString().equals("O")) {
			result= false;
			throw new CannotMoveException("Vous ï¿½tes face ï¿½ un obstacle");
		}

		for(int i = 0; i < equipe1.size();i++){
			if (equipe1.get(i).position.getCol() == x && equipe1.get(i).position.getRow() == y){

				result=false;
				
			}
		}
		for(int i = 0; i< equipe2.size();i++){
			if (equipe2.get(i).position.getCol() == x && equipe2.get(i).position.getRow() == y){
				result = false;
				
			}
		}

		return result;
	}


	/**Booleen permettant de verifier si l'element est plaï¿½able, si il l'est on le place
	 * 
	 * @param x
	 * @param y
	 * @param elem
	 * @return false si la case n'est pas vide, sinon renvoie true
	 * @throws OutOfMapException 
	 * @throws CannotMoveException 
	 * @see Monde#isFree(int, int)
	 */
	public boolean placeElement(int x, int y, Element elem) throws OutOfMapException, CannotMoveException{
		if(this.isFree(x, y)) this.grille[x][y] = elem;
		else return false;
		return true;
	}
	/**
	 * Permet de dï¿½finir une case comme etant Vide
	 * @param x
	 * @param y
	 */
	public void setEmpty(int x, int y){
		this.grille[x][y] = new Vide();
	}
	/**
	 * Retourne l'element x, y
	 * @param x
	 * @param y
	 * @return retourne l'element ï¿½ la position x, y
	 */
	public Element getElement(int x, int y){
		if(x < nbCol && y < nbRow)
			return this.grille[x][y];
		else
			return null;
	}



	/**
	 * affichage de la carte, et de l'interface
	 */
	public void afficher(){

		String[][] grille = new String[nbCol][nbRow];



		for (int i = 0; i < nbCol; i++) {
			for (int j = 0; j < nbRow; j++) {
				if (this.grille[i][j].isFind())
					grille[i][j] = this.grille[i][j].toString();
				else
					grille[i][j] = " -";
			}
		}


		for(int a = 0 ; a < equipe1.size();a++){
			if(equipe1.get(a).energie>0)
				grille[equipe1.get(a).position.getCol()][equipe1.get(a).position.getRow()] = equipe1.get(a).toString();
			else equipe1.remove(a);


		}

		for(int b = 0 ; b < equipe2.size();b++){
			if(equipe2.get(b).energie>0)
				grille[equipe2.get(b).position.getCol()][equipe2.get(b).position.getRow()] = equipe2.get(b).toString();
			else equipe2.remove(b);
		}

		for (String[] em : grille) {
			for (String e : em) {
				System.out.print("|" + e);
			}
			System.out.print("|\n");
		}
	}

	/** Permet de recupèrer un robot à une position donné, repond a la question : est il present a cet endroit?
	 * 
	 * @param x la colonne
	 * @param y la ligne
	 * @param eq l'equipe dite du robot
	 * @return Robot
	 */
	public Robot whatIsRobot(int x, int y, int eq){
		Robot r = null;
		for(int i = 0; i < getTeam(eq).size(); i++){
			r=getTeam(eq).get(i);
			if(x == r.position.getRow()){
				if(y == r.position.getCol()){
					r = getTeam(eq).get(i);
				}
			}
		}
		return r;

	}






}