import java.util.Scanner;
/** Classe heritant d'utilisateur, il permet d'avoir un choix sur les actions d'une equipe
 * @see Utilisateur
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
public class Humain extends Utilisateur {

	/**
	 * Constructeur definission de l'equipe
	 * @param e
	 */
	public Humain(int e){
		super(e);
	}


	/**Permet de savoir si l'action est possible ou non
	 * @see Jouer#action
	 * @param c
	 * @return boolean
	 */
	private boolean isPossible(char c) {
		for (int i = 0; i < getAction().size(); i++) {
			if (c ==getAction().get(i)  ) 
				return true;
		}
		return false;
	}

	/**
	 * Permet de lire une touche utilisee par l'utilisateur
	 * @return char
	 */
	private char readKey() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		return sc.next().toUpperCase().charAt(0);
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
		System.out.println("Faites votre action (i.e Menu tapez 1)>>>");
		char action = readKey();

		if (isPossible(action)) {
			try{
				switch (action) {

				case '1':
					System.out.println("Z : Haut, Q: Gauche, S : Bas, D : Droite\n E : D.haut, W : D.bas, A : B.haut, C : B.bas \nT : tirer ; M : poser mine; 0 : Pour quitter la partie");
					break;
				case 'Z':case 'z':
					m.movebot(robot, -1, 0, action,a,false);
					break;
				case 'S':case's':
					m.movebot(robot, 1, 0, action,a,false);
					break;
				case 'Q':case'q':
					m.movebot(robot, 0, -1, action,a,false);
					break;
				case 'D':case'd':
					m.movebot(robot, 0, 1, action,a,false);
					break;

				case 'A':case'a':
					m.movebot(robot, -1, -1, action,a,false);
					break;
				case'E':case'e':
					m.movebot(robot, -1, 1, action,a,false);
					break;

				case 'W':case'w':
					m.movebot(robot, 1, -1, action,a,false);
					break;
				case'C':case'c':
					m.movebot(robot, 1, 1,action,a,false);
					break;

				case 'M':case'm':
					System.out.println("Ou voulez vous poser votre mine : \n[utilisez le pave numerique votre position est la touche 5]\n");
					
					Scanner sc = new Scanner(System.in);
					char c = sc.next().charAt(0);
					m.poseMine(m.getTeam(eq).get(a),c);
					break;
				case 'T':case't':

					if(equipe==1){ m.tirer(m.getTeam(eq).get(a),m.getTeam(2).get(userChoice(2,m)));} 
					else m.tirer(m.getTeam(eq).get(a), m.getTeam(1).get(userChoice(1,m)));
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
		else {
			clearScreen();
			System.out.println("Action Impossible");
			userAction(p,m);
		}


	}


	/**
	 * Permet a l'utilisateur de choisir un robot par son nom donnee la creation
	 * @return int indice sur une ArrayList
	 */

	@Override
	public int userChoice(int eq, Monde m) {
		System.out.println("Veuillez choisir un robot : \n(indiquez son nom)>>");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String tmp = sc.nextLine();
		int a=-1;
		m.getTeam(eq).get(1);
		if( Robot.nomDejaPris(tmp)){ 


			for(int i=0;i< m.getTeam(eq).size(); i++){
				if(m.getTeam(eq).get(i).getName().equals(String.valueOf(tmp)) ){ a=Integer.valueOf(i); i=m.getTeam(eq).size();}
			}


			return a;
		}
		else{
			System.out.println("Le nom que vous avez choisi n'est pas present, recommencez !");
			return userChoice(eq,m);
		}
	}








}
