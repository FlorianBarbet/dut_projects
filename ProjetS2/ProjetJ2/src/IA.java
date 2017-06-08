import java.util.ArrayList;
import java.util.Random;
/** Permet de definir une intelligence artificielle, basé sur l'aleatoire, et la prevision.
 * herite de la classe utilisateur
 * @see Utilisateur
 * 
 * @author Florian BARBET
 * @author Theo LEICHT
 * @author Xavier BRUNI
 * @author Edouard MURAT
 * @author Thomas CORNET
 * @author Dylan SKAWAND
 * @author Alexandre ROUKMOUT
 *
 */
public class IA extends Utilisateur{

	/**
	 * Permet de garder en memoire un index de robot (en general le tireur)
	 */
	private static int index_memory;
	/**
	 * Permet de garder un robot cible en memoire
	 */
	private static Robot cible;
	/**
	 * permet de definir les touches utilisatrices pour poser une mine
	 */
	private ArrayList<Character> action4mine = new ArrayList<Character>();

	/**
	 * Permet de definir le champ d'action d'action4mine ainsi que l'equipe de l'ia
	 * @param e equipe de l'ia
	 */
	public IA(int e) {
		super(e);
		action4mine.add('1');
		action4mine.add('2');
		action4mine.add('3');
		action4mine.add('4');

		action4mine.add('6');
		action4mine.add('7');
		action4mine.add('8');
		action4mine.add('9');

	}

	/**
	 * Methode interne, coeur du programme permettant au robot de choisir aleatoirement ce qu'il fera
	 * @param m
	 * @return
	 */
	private char randomChoice(Monde m){


		Random r = new Random();
		int index = r.nextInt(10);

		if(action.get(index).equals(action.get(action.indexOf('T'))) && canShoot(m,equipe)){
			return action.get(index);
		}


		return action.get(index);

	}

	/**
	 * permet de choisir aleatoirement l'endroit ou poser la mine
	 * @return
	 */
	private char poseMineauto(){
		Random r = new Random();
		int index = r.nextInt(action4mine.size());
		return action4mine.get(index);
	}

	/**
	 * Verifie si le robot peut tirer, sur un robot proche de lui  selon son type
	 * @param r Tireur | Char
	 * @param eq equipe du robot r
	 * @param m le monde
	 * @return boolean vrai si il y a quelqu'un
	 */
	public boolean scanAround(Robot r, int eq, Monde m) {
		boolean hadShot = false;
		int eqo = 0;
		if(eq == 1){eqo =  2;}else{eqo = 1;}

		switch (r.toString()) {
		case "c":case "C":
			for(int i =r.position.getCol()-r.portee; i < r.portee*2 && !hadShot; i++){
				if(m.whatIsRobot(i,r.position.getRow(),eqo)!=null){
					hadShot = true;
					cible=m.whatIsRobot(i, r.position.getCol(), eqo);
				}
			}
			if(!hadShot){
				for(int j = r.position.getRow()-r.portee; j< r.portee*2 && !hadShot; j++){
					if(m.whatIsRobot(r.position.getCol(),j, eqo)!=null){
						hadShot = true;
						cible=m.whatIsRobot(r.position.getRow(), j, eqo);
					}
				}
			}
			break;
		case "t":case "T":
			for(int i =r.position.getCol(); i < r.portee&& !hadShot; i++){
				for(int j = r.position.getRow(); j< r.portee && !hadShot; j++){
					if(m.whatIsRobot(i, j, eqo)!=null){
						hadShot = true;
						cible = m.whatIsRobot(i, j, eqo);					}
				}
			}

			break;

		default:
			break;
		}	
		return hadShot;
	}

	/**
	 * Methode interne permettant de stocker l'index d'un tireur
	 * @param r le tireur
	 * @param m
	 * @param eq l'equipe
	 */
	private static void stock(Robot r, Monde m, int eq){
		for(int i =0; i < m.getTeam(eq).size();i++)
			if(m.getTeam(eq).get(i)==r)index_memory=i;

	}

	/**Permet de savoir si le tireur peut ou non tirer selon les conditions qui se trouvent autour de lui
	 * 
	 * @param m Monde
	 * @param eq l'equipe
	 * @return vrai si il peut tirer, faux sinon
	 */
	public boolean canShoot(Monde m, int eq){
		boolean tmp = false;

		@SuppressWarnings("unused")
		Robot temp=null;
		for(int i = 0; i < m.getTeam(eq).size() && !tmp; i++){
			stock(m.getTeam(eq).get(i),m,eq);
			tmp = scanAround(m.getTeam(eq).get(i), eq, m);

		}
		return tmp;
	}

	/**
	 * @see Utilisateur#userAction(int, Monde)
	 */
	@Override
	public void userAction(int p, Monde m) {
		char robot;
		if (p == 1)
			robot = '1';
		else
			robot = '2';
		printgame(robot,m);

		int eq;
		if(equipe == 1)eq=1;
		else eq=2;
		int a = userChoice(equipe,m);
		if(a != 99){
			System.out.println("Faites votre action (i.e Menu tapez 1)>>>");



			char action = randomChoice(m);


			try{
				switch (action) {

				case '1':
					System.out.println("Z : Haut, Q: Gauche, S : Bas, D : Droite\n E : D.haut, W : D.bas, A : B.haut, C : B.bas \nT : tirer ; M : poser mine;");
					break;
				case 'Z':case 'z':
					m.movebot(robot, -1, 0, action,a,true);
					break;
				case 'S':case's':
					m.movebot(robot, 1, 0, action,a,true);
					break;
				case 'Q':case'q':
					m.movebot(robot, 0, -1, action,a,true);
					break;
				case 'D':case'd':
					m.movebot(robot, 0, 1, action,a,true);
					break;

				case 'A':case'a':
					m.movebot(robot, -1, -1, action,a,true);
					break;
				case'E':case'e':
					m.movebot(robot, -1, 1, action,a,true);
					break;

				case 'W':case'w':
					m.movebot(robot, 1, -1, action,a,true);
					break;
				case'C':case'c':
					m.movebot(robot, 1, 1,action,a,true);
					break;

				case 'M':case'm':


					m.poseMine(m.getTeam(eq).get(a),poseMineauto());
					break;
				case 'T':case't':
					if(canShoot(m,equipe)){
						if(equipe==1){ m.tirer(m.getTeam(eq).get(a),cible);} 
						else m.tirer(m.getTeam(eq).get(a), cible);
					}
					break;

				}
			} catch (CannotMoveException e) {
				clearScreen();
				System.out.println(e.toString());
				userAction(p,m);
			}catch (OutOfMapException e) {
				clearScreen();
				System.out.println(e.toString());
				userAction(p,m);
			}
		}
	}





	
/**
 * @see Utilisateur#userChoice(int, Monde)
 */
	public int userChoice(int eq, Monde m) {
		int index = 99;
		if( canShoot(m, equipe)){
			index = index_memory;
		}
		else
		{
			Random r = new Random();
			if(m.getTeam(eq).size()>=1)
				index = r.nextInt(m.getTeam(eq).size());
			else
				return userChoice(eq,m);
		}


		return index;


	}



}
