import java.util.*;
/**Char est une classe hérité de Robot, et donc un type de robot
 * 
 * @see Char#Char(int, int, int, int)
 * @see Robot
 * @author BARBET Florian
 * @author LEICHT Theo
 * @author MURAT Edouard
 * @author SKAWAND Dylan
 * @author RICHARD Alexandre
 * @author CORNET Thomas
 * @author BRUNI XAVIER
 */
public class Char extends Robot{

	/**Constructeur : permet de donner des coordonnée d'un char à sa création
	 * 
	 * @param x
	 * @param y
	 * @param nx
	 * @param ny
	 */
	public Char(int x, int y,int nx,int ny, int e){super(120,10,2,e);position=new Coordonnee(x,y,nx,ny); }



	/**Décremente la vitalité par action
	 * @see Robot#action()
	 */
	@Override
	public void action() {

		this.energie-=1;
	}
	/**
	 * @see Robot#getDamage(Element r)
	 */
	@Override
	public void getDamage(Element r) {
		r.energie-=6;
	}

	/**
	 * @see Robot#getDamage(Robot r)
	 */
	@Override
	public void getDamage(Robot r) {
		r.energie-=6;
	}

	/**
	 * @see Robot#deplacement(char)
	 */
	@Override
	public void deplacement(char key) {
		// TODO Auto-generated method stub
		if(key == 'Z' || key == 'z'){this.moveHaut();this.moveHaut();}
		else if(key == 'Q' || key == 'q'){this.moveGauche();this.moveGauche();}
		else if(key == 'S' || key == 's'){this.moveBas();this.moveBas();}
		else if(key == 'D' || key == 'd'){this.moveDroite();this.moveDroite();}
		else{
			System.out.println("Erreur ce robot ne peut se deplacer qu'horizontalement ou verticalement !");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in); 
			key = sc.next().charAt(0);
			deplacement(key);
		}
		this.energie-=5;
	}

	/**Permet à éviter les demande de correction à l'ia, ressemble à deplacement
	 * @see Char#deplacement(char)
	 */
	
	public void deplacement4IA(char key) {
		// TODO Auto-generated method stub
		if(key == 'Z' || key == 'z'){this.moveHaut();this.moveHaut();}
		else if(key == 'Q' || key == 'q'){this.moveGauche();this.moveGauche();}
		else if(key == 'S' || key == 's'){this.moveBas();this.moveBas();}
		else if(key == 'D' || key == 'd'){this.moveDroite();this.moveDroite();}
		else{
			Random rand = new Random();
			int choice = rand.nextInt(3);
			switch(choice){
			case 0: key='Z';
			break;
			case 1: key='Q';
			break;
			case 2: key='S';
			break;
			case 3: key='D';
			break;
			default: System.out.println("Erreur");
			break;
			
			}
			deplacement4IA(key);
		}
		this.energie-=5;
	}
	
	/**
	 * @see Robot#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(super.getEquipe() == 1)
			return "C";
		else return "c";
	}




}
