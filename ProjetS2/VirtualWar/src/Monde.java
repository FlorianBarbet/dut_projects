import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
	 * Liste de Robot dans chaque equipe
	 */
	
	private ArrayList<ArrayList<Robot>> equipes;
	
	GraphicsContext gc;
	
	Map<Integer, Image> images;
	public static final int VIDE = -1;
	public static final int OBSTACLE0 = 0;
	public static final int OBSTACLE1 = 1;
	public static final int MINE = 2;
	public static final int PIEGEUR0 = 3;
	public static final int PIEGEUR1 = 4;
	public static final int TIREUR0 = 5;
	public static final int TIREUR1 = 6;
	public static final int CHAR0 = 7;
	public static final int CHAR1 = 8;

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
	Monde(GraphicsContext gc, int row, int col, ArrayList<ArrayList<String>> equipes, int obstacle){
		this.nbRow = row;
		this.nbCol = col;
		this.images = new HashMap<Integer, Image>();
		this.grille = new Element[col][row];
		this.gc = gc;
		this.equipes = new ArrayList<ArrayList<Robot>>();
		loadImages(1000/row, 500/col);
		setVide();
		setObstacle((double)(obstacle)/100);
		createTeam(equipes);
	}
	
	/**
	 * Charge toutes les images
	 * @param x la taille des images en largeur
	 * @param y la taille des images en hauteur
	 */
	private void loadImages(int x, int y){
		images.put(VIDE, new Image("images\\sol1.png", x, y, false, false));
		images.put(OBSTACLE0, new Image("images\\obstacle0.png", x, y, false, false));
		images.put(OBSTACLE1, new Image("images\\obstacle1.png", x, y, false, false));
		images.put(MINE, new Image("images\\mine.png", x, y, false, false));
		images.put(PIEGEUR0, new Image("images\\piegeur0.png", x, y, false, false));
		images.put(PIEGEUR1, new Image("images\\piegeur1.png", x, y, false, false));
		images.put(TIREUR0, new Image("images\\tireur0.png", x, y, false, false));
		images.put(TIREUR1, new Image("images\\tireur1.png", x, y, false, false));
		images.put(CHAR0, new Image("images\\char0.png", x, y, false, false));
		images.put(CHAR1, new Image("images\\char1.png", x, y, false, false));
		images.put(Tuple.SELECTION, new Image("images\\selection0.png", x, y, false, false));
		images.put(Tuple.DEPLACEMENT, new Image("images\\selection1.png", x, y, false, false));
		images.put(Tuple.ACTION, new Image("images\\selection2.png", x, y, false, false));
		images.put(Tuple.UNKNOWN, new Image("images\\selection3.png", x, y, false, false));
	}
	
	/**
	 *  Permet de crï¿½er les deux equipes, par choix utilisateur, obligation d'equipe de 5
	 * @throws OutOfMapException 
	 * @throws CannotMoveException 
	 *  @see Monde#askBot()
	 *  @see Monde#ajouterRobot(String)
	 */
	private void createTeam(ArrayList<ArrayList<String>> equipes){
		int index=0;
		for(ArrayList<String> equipe : equipes){
			this.equipes.add(new ArrayList<Robot>());
			for(String robot : equipe) this.equipes.get(index).add(ajouterRobot(robot, index));
			index++;
		}
	} 
	
	/**Methode permettant d'ajouter un Robot dans une equipe, selon le parametre choix
	 * 
	 * @param choix
	 * @return c Char | p Piegeur | t Tireur [ ajouterRobot(choix)
	 * @throws CannotMoveException 
	 * @throws OutOfMapException 
	 */
	private Robot ajouterRobot(String choix, int eq){ 

		int col ;
		int row;
		try {
			col = randomBorne(0, nbCol-1);
			row = randomBorne(0, nbRow-1);
			if(isFree(col,row)){
				switch(choix){
					case "char":
						return new Char(col, row, nbCol, nbRow, eq);
					case "piegeur":
						return new Piegeur(col, row, nbCol, nbRow, eq);
					case "tireur":
						return new Tireur(col, row, nbCol, nbRow, eq);
					default:
						return new Tireur(col, row, nbCol, nbRow, eq);
				}
			}else return ajouterRobot(choix, eq);
		} catch (OutOfMapException | CannotMoveException e ) {
			return ajouterRobot(choix, eq);
		}

	}
	
	/**
	 * Definit les cases de type Vide.
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
	
	/**
	 * Dessine la carte (case + robots + mine selon l'equipe)
	 * @param tourEquipe L'equipe qui joue acutellement (0 ou 1)
	 */
	public void draw(int tourEquipe){
		int width = 1000/nbRow;
		int height = 500/nbCol;
		for(int i=0; i<nbCol; i++){
			for(int j=0; j<nbRow; j++){
				if(!images.containsKey(grille[i][j].toInteger())) System.out.println("Case inconnu"+grille[i][j].toInteger());
				else gc.drawImage(images.get(grille[i][j].toInteger()), j*width, i*height);
			}
		}
		for(ArrayList<Robot> equipe : equipes){
			for(Robot robot : equipe){
				if(!images.containsKey(robot.toInteger())) System.out.println("Case inconnu"+robot.toInteger());
				else{
					if(robot.getEnergie() > 0) gc.drawImage(images.get(robot.toInteger()), robot.position.getRow()*width, robot.position.getCol()*height);
					if(robot instanceof Piegeur && robot.getEquipe() == tourEquipe){
						for(Mine mine : ((Piegeur) robot).getMines()){
							if(mine.isAlive()) gc.drawImage(images.get(mine.toInteger()), mine.position.getRow()*width, mine.position.getCol()*height);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Dessine les emplacement sur lequel peut se déplacer un robot
	 * @param robot Le robot pour lequel on veut voir les actions possibles
	 * @param gc Le GraphicsContext sur lequel on veut dessiner
	 */
	public void drawCases(Robot robot, GraphicsContext gc){
		int width = 1000/nbRow;
		int height = 500/nbCol;
		ArrayList<Tuple> cases = getCases(robot);
		for(Tuple case0 : cases){
			if(case0.couleur != Tuple.UNKNOWN){
				gc.drawImage(images.get(case0.couleur), case0.x*width, case0.y*height);
			}
		}
		gc.drawImage(images.get(Tuple.SELECTION), robot.position.getRow()*width, robot.position.getCol()*height);
	}
	
	/**
	 * @param robot Le robot pour lequel on veut recuperer les actions
	 * @param x L'abscisse de la case dont on veut l'action possible
	 * @param y L'ordonne de la case dont on veut l'action possible
	 * @return l'action possible sur cette case ou null si aucun action n'est possible
	 */
	public Tuple getTuple(Robot robot, int x, int y){
		ArrayList<Tuple> tuples = this.getCases(robot);
		for(Tuple tuple : tuples){
			if(tuple.x == x && tuple.y==y && tuple.couleur != Tuple.UNKNOWN) return tuple;
		}
		return null;
	}
	
	/**
	 * Retourne les cases sur lesquels le robot peut effectuer une action (attaque, deplacement ou minage)
	 * @param robot Le robot pour lequel on veut recuperer les actions
	 * @return une ArrayList de Tuple des cases avec actions possibles
	 */
	public ArrayList<Tuple> getCases(Robot robot){
		int x = robot.position.getRow();
		int y = robot.position.getCol();
		ArrayList<Tuple> cases = robot.casesPossible(robot.position.getRow(), robot.position.getCol());
		for(int i=0; i<cases.size(); i++){
			Tuple tuple = cases.get(i);
			if(tuple.x>=0 && tuple.y>=0 && tuple.x<nbRow && tuple.y<nbCol){
				for(ArrayList<Robot> equipe : equipes){
					for(Robot rob : equipe){
						if(rob.energie>0 && rob.position.getRow()==tuple.x && rob.position.getCol()==tuple.y && rob!=robot && robot.getEquipe() != rob.getEquipe() && (x==tuple.x || y==tuple.y)){
							if(robot instanceof Piegeur) tuple.couleur = Tuple.UNKNOWN;
							else{
								if(isPathEmpty(x, y, tuple.x+(x<tuple.x?-1:x>tuple.x?1:0), tuple.y+(y<tuple.y?-1:y>tuple.y?1:0)))
									tuple.couleur = Tuple.ACTION;
								else tuple.couleur = Tuple.UNKNOWN;
							}
						}
					}
				}
				if(!isPathEmpty(x, y, tuple.x, tuple.y) && tuple.couleur != Tuple.ACTION) tuple.couleur = Tuple.UNKNOWN;
			}else tuple.couleur = Tuple.UNKNOWN;
		}
		return cases;
	}
	
	/**
	 * Verifie si le robot est sur une mine et l'a fait exploser si oui
	 * @param robot le robot pour lequel on veut verifier
	 * @return 1 si une mine a explose, 0 sinon
	 */
	public int checkMine(Robot robot){
		for(ArrayList<Robot> equipe : equipes){
			for(Robot rob : equipe){
				if(rob instanceof Piegeur){
					for(Mine mine : ((Piegeur) rob).getMines()){
						if(mine.isAlive() && mine.position.getRow() == robot.position.getRow() && mine.position.getCol() == robot.position.getCol()){
							mine.getDamage(robot);
							return 1;
						}
					}
				}
			}
		}
		return 0;
	}
	
	/**
	 * Verifie si il y a un gagant
	 * @return 0 ou 1 celon l'equipe gagnante. -1 si il n'y a pas de gagnant.
	 */
	public int getWinner(){
		int i=0, j=0;
		for(ArrayList<Robot> equipe : equipes){
			for(Robot rob : equipe){
				if(rob.getEnergie() > 0 && rob.getEquipe() == 0) i++;
				else if(rob.getEnergie() > 0 && rob.getEquipe() == 1) j++;
			}
		}
		return i>0?(j>0?-1:1):0;
	}
	
	/**
	 * Verifie si il n'y a ni obstacle, ni robot entre (x1,y1) et (x2,y2)
	 * @return false si il y a quelque chose, true sinon
	 */
	public boolean isPathEmpty(int x1, int y1, int x2, int y2){
		int i=0;
		int incrx = (x1<x2)?1:(x1>x2)?-1:0;
		int incry = (y1<y2)?1:(y1>y2)?-1:0;
		int nbincrx = Math.abs(x1-x2);
		int nbincry = Math.abs(y1-y2);
		if(x1!=x2 && y1!=y2) return isActionPossible(x1+incrx, y1+incry);
		else if(nbincrx>0) for(i=1; i<=nbincrx; i++){if(!isActionPossible(x1+i*incrx, y1)) return false;}
		else if(nbincry>0) for(i=1; i<=nbincry; i++){if(!isActionPossible(x1, y1+i*incry)) return false;}

		return true;

	}
	
	
	/**
	 * Verifie si il y a un obstacle ou un robot sur une case
	 * @param x L'abscisse de la case
	 * @param y L'ordonne de la case
	 * @return false si pas d'action possible, true sinon
	 */
	public boolean isActionPossible(int x, int y){
		if(grille[y][x] instanceof Obstacle) return false;
		for(ArrayList<Robot> equipe : equipes){
			for(Robot rob : equipe){
				if(rob.position.getCol() == y && rob.position.getRow() == x) return false;
			}
		}
		return true;
	}
	
	/**Renvoie la liste de robot selon ne (aurait pu etre un booleen)
	 * 
	 * @param ne
	 * @return une equipe
	 */
	
	// if(ne == 1)return equipe1; !!!!!!!!!!!
	public ArrayList<Robot> getTeam(int ne){
		return equipes.get(ne);
	}
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

		if (x == nbCol || x < 0 || y == nbRow || y < 0)
			throw new OutOfMapException("Vous ne pouvez pas sortir de la carte.");

		else if (grille[x][y] instanceof Obstacle) {
			throw new CannotMoveException("Vous etes face a un obstacle");
		}

		for(ArrayList<Robot> equipe : equipes){
			for(Robot robot : equipe){
				if (robot.position.getCol() == x && robot.position.getRow() == y){
					return false;
				}
			}
		}

		return true;
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
	 * Permet de deinir une case comme etant Vide
	 * @param x
	 * @param y
	 */
	public void setEmpty(int x, int y){
		this.grille[x][y] = new Vide();
	}
	
	/**
	 * @return le nombre de case par colonne
	 */
	public int getCol(){
		return nbCol;
	}
	
	/**
	 *
	 * @return le nombre de case par ligne
	 */
	public int getRow(){
		return nbRow;
	}
	
	/**
	 * Retourne 
	 * @param x La case en abscisse
	 * @param y La case en ordonnee
	 * @return L'element en x,y
	 */
	public Element getElement(int x, int y){
		return x<nbCol && y<nbRow && x>=0 && y>=0 ? grille[y][x] : null;
	}
	
	/**
	 * 
	 * @param x la position en abscisse du clique de la souris
	 * @param y la positition en ordonne du clique de la souris
	 * @return l'element a cette position
	 */
	public Element getElementWithCoord(int x, int y){
		x= x/(1000/nbRow);
		y = y/(500/nbCol);
		return getElement(x, y);
	}
	
	/**
	 * 
	 * @param x la position en abscisse du clique de la souris
	 * @param y la position en ordonnee du clique de la souris
	 * @return x,y convertit pour le tableau de case/robot
	 */
	public int[] getXYWithCoord(int x, int y){
		return new int[] {x/(1000/nbRow), y/(500/nbCol)};
	}
	
	
	/**
	 * 
	 * @param x la position en abscisse du clique de la souris
	 * @param y la position en ordonnee du clique de la souris
	 * @return le robot a cette emplacement ou null
	 */
	public Robot getRobotWithCoord(int x, int y){
		x = x/(1000/nbRow);
		y = y/(500/nbCol);
		for(ArrayList<Robot> equipe : equipes){
			for(Robot robot : equipe){
				if(robot.position.getCol() == y && robot.position.getRow()==x && robot.getEnergie()>0) return robot;
			}
		}
		return null;
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