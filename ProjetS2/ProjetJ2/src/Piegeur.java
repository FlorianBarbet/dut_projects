import java.util.*;
/** Piegeur est une classe hérité de Robot donc un type de robot cependant il utilise des mines contrairement aux deux autres types de robot
 * @see Piegeur#munition
 * @see Piegeur#Piegeur(int, int, int, int)
 * @see Piegeur#action()
 * @see Mine
 * @see Robot
 * @author BARBET Florian
 * @author LEICHT Theo
 * @author MURAT Edouard
 * @author SKAWAND Dylan
 * @author RICHARD Alexandre
 * @author CORNET Thomas
 * @author BRUNI XAVIER
 *
 */
public class Piegeur extends Robot{
	/**Liste de Mine, permet d'évaluer le nombre de mine posable
	 * @see Mine
	 */
	private ArrayList<Mine> munition = new ArrayList<Mine>();
	/**Constructeur : permet de donner des coordonnée d'un piegeur à sa création
	 * un stock de munition est ajouté avec cela.
	 * 
	 * @param x
	 * @param y
	 * @param nx
	 * @param ny
	 * @see Mine
	 */
	public Piegeur(int x, int y,int nx,int ny, int e) {
		super(100,1,1,e);

		for(int i = 0; i <21; i++) {munition.add(new Mine());  munition.get(i).setTeam(this.getEquipe());}
		this.position=new Coordonnee(x,y,nx,ny);


	}


	/**
	 * l'action d'un piegeur est un peu plus que les autres, il va vérifier ses munition, puis poser, tout en supprimant une munition de son sac!
	 * @see Mine
	 * @see Robot#action()
	 */
	@Override
	public void action() {
		// TODO Auto-generated method stub

		this.energie-=2;

	}

	/**
	 * permet de prendre en compte la gestion de pose d'une mine
	 * @param c
	 */
	public void actionp(char c){

		if(munition.size()>0){
			switch(c){
			case '9':munition.get(1).Poser(this.position.getCol()+1,this.position.getRow()+1);
			munition.remove(1);
			action();
			break;

			case '8':munition.get(1).Poser(this.position.getCol(),this.position.getRow()+1);
			munition.remove(1);
			action();
			break;

			case '7':munition.get(1).Poser(this.position.getCol()-1,this.position.getRow()+1);
			munition.remove(1);
			action();
			break;

			case '6':munition.get(1).Poser(this.position.getCol()+1,this.position.getRow());
			munition.remove(1);
			action();
			break;

			case '4': munition.get(1).Poser(this.position.getCol()-1,this.position.getRow());
			munition.remove(1);
			this.energie-=2;
			action();
			break;

			case '3':munition.get(1).Poser(this.position.getCol()+1,this.position.getRow()-1);
			munition.remove(1);
			action();
			break;

			case '2':munition.get(1).Poser(this.position.getCol()-1,this.position.getRow());
			munition.remove(1);
			action();
			break;

			case '1' : munition.get(1).Poser(this.position.getCol()-1,this.position.getRow()-1);
			munition.remove(1);
			action();
			break;

			default:System.out.println("Mauvaise Touche");Scanner sc = new Scanner(System.in); char nextc = sc.next().charAt(0); actionp(nextc);break;
			}
		}



	}


	/**
	 * @see Robot#getDamage(Element r)
	 */
	@Override
	public void getDamage(Element r) {
		r.energie=r.energie-2;
	}

	/**
	 * @see Robot#getDamage(Robot r)
	 */
	@Override
	public void getDamage(Robot r) {
		r.setEnergie(r.getEnergie()-2);
	}

	/**
	 * @see Robot#deplacement(char)
	 */
	@Override
	public void deplacement(char key) {
		// TODO Auto-generated method stub
		if(key == 'Z' || key == 'z')this.moveHaut();
		else if(key == 'Q' || key == 'q')this.moveGauche();
		else if(key == 'S' || key == 's')this.moveBas();
		else if(key == 'D' || key == 'd')this.moveDroite();
		else if(key == 'E' || key == 'e'){this.moveHaut(); this.moveDroite();}
		else if(key == 'A' || key == 'a'){this.moveHaut(); this.moveGauche();}
		else if(key == 'C' || key == 'c'){this.moveBas(); this.moveDroite();}
		else if(key == 'W' || key == 'w'){this.moveBas(); this.moveGauche();}

		this.energie-=2;
	}
	/**
	 * @see Robot#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(super.getEquipe() == 1)
			return "P";
		else return "p";
	}




}
