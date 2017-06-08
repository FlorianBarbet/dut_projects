import java.util.ArrayList;
import java.util.Scanner;
/**Corps principal des classes, permet de lancer une partie !
 * 
 * @author BARBET Florian
 * @author LEICHT Theo
 * @author MURAT Edouard
 * @author SKAWAND Dylan
 * @author RICHARD Alexandre
 * @author CORNET Thomas
 * @author BRUNI XAVIER
 * @see Jouer#map
 * @see Jouer#equipe
 * @see Jouer#tour
 * @see Jouer#action
 * @see Jouer#maxSize
 * @see Jouer#minSize
 * @see Jouer#toursmax
 * @see Jouer#clearScreen()
 * @see Jouer#readKey()
 * @see Jouer#borne(int, int)
 * @see Jouer#isPossible(char)
 * @see Jouer#userAction(int)
 * @see Jouer#printgame(char)
 * @see Jouer#printend()
 * @see Jouer#main(String[])
 */
public class Jouer {
	/**
	 * Crï¿½ï¿½ une carte Monde, utilisable dans toute la classe jouer
	 */
	private static Monde map;

	/**
	 * Liste d'utilisateur, il y en aura en general 2membres de type ia ou humain
	 */
	private static ArrayList<Utilisateur> player = new ArrayList<Utilisateur>();		
	
	
	/**
	 * Permet de donner une taille maximale ï¿½ la carte
	 */
	private static int maxSize = 15;
	/**
	 * Permet de donner une taille minimale ï¿½ la carte
	 */
	private static int minSize = 10;


	

	/**Permet d'ï¿½valuer un nombre de tour choisit par utilisateur entre deux bornes
	 * 
	 * @param bmin
	 * @param bmax
	 * @return int
	 */
	public static int borne(int bmin, int bmax) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print(">>>  ");
		int r = sc.nextInt();
		if (bmin > r || bmax < r ) {
			return borne(bmin, bmax);
		}
		return r;
	}

	

	/**
	 * Impression de la fin du jeu, (qui a gagnï¿½?)
	 */
	public static void printend() {

		if(map.getTeam(1).size()==0)
			System.out.println("Fin equipe 2 a gagné");
		else if(map.getTeam(2).size() == 0)
			System.out.println("Fin equipe 1 a gagné");
		else System.out.println("Match Nul");

	}
	
	
	@SuppressWarnings("resource")
	public static void userType(int i){
		System.out.println("Choix : Humain, IA ?");
		Scanner sc = new Scanner(System.in);
		String tmp = sc.nextLine();
		
		if(tmp.equals("Humain") || tmp.equals("H")||tmp.equals("h")||tmp.equals("humain") || tmp.equals("human")||tmp.equals("Human")){
			player.add(new Humain(i));
		}else{
			player.add(new IA(i));
		}
		
		
	}
	

	/**Main, corps principale mettant en oeuvre toute les parties diverses du code afin de crï¿½er une Partie
	 * 
	 * @param args
	 * @throws OutOfMapException 
	 * 
	 * @see Monde
	 * @see Jouer
	 * @see Robot
	 * @see Element
	 * @see Coordonnee
	 */
	public static void main(String[] args) throws OutOfMapException, CannotMoveException {
		
		userType(1);
		userType(2);
		
		Utilisateur joue;
		System.out.println("Entrez la taille de la carte au clavier ( entre 10 et 15 )");
		map = new Monde(borne(minSize,maxSize), borne(minSize,maxSize));
		
		joue = player.get(0);


	
		
		
		
		for (int tour = 1; (map.getTeam(1).size()!=0 && map.getTeam(2).size() != 0); tour++) {

			System.out.println("\nEquipe 1 --#-- Equipe 2 | Type :\nT  --#-- t | tireur\nP  --#-- p | piegeur\nC  --#-- c | char\n------------------------\nO | Obstacle\n\nVotre Equipe :\n");
			
			for(int i=0;i<map.getTeam(joue.getEquipe()).size();i++){
				if(map.getTeam(joue.getEquipe()).get(i).getEnergie()>0)
					System.out.print("< energie de "+map.getTeam(joue.getEquipe()).get(i).getName()+" : "+map.getTeam(joue.getEquipe()).get(i).getEnergie()+" >\n");
				else System.out.print("x--< energie de "+map.getTeam(joue.getEquipe()).get(i).getName()+" : Mort >--x\n");
			}
			System.out.println("\nEquipe Adverse:\n");

			if(joue == player.get(0)){
				for(int i=0;i<map.getTeam(0).size();i++){

					
						System.out.print("< energie de "+map.getTeam(0).get(i).getName()+" : "+map.getTeam(0).get(i).getEnergie()+" >\n");
					
				}
			}
			else {

				for(int i=0;i<map.getTeam(1).size();i++){
					
						System.out.print("< energie de "+map.getTeam(1).get(i).getName()+" : "+map.getTeam(1).get(i).getEnergie()+" >\n");
					
				}
			}

			if(joue == player.get(0)){
			joue.userAction(1,map);
			}
			else{
				joue.userAction(2,map);
			}
			
			if (joue == player.get(0))
				joue = player.get(1);
			else
				joue = player.get(0);
			System.out.println(tour);
			joue.clearScreen();
		}
		printend();

	}/**/
}

