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
		super(100,1,1,e, "Piegeur");

		for(int i = 0; i <21; i++) munition.add(new Mine(this.getEquipe()));
		this.position = new Coordonnee(x,y);


	}


	/**
	 * l'action d'un piegeur est un peu plus que les autres, il va vérifier ses munition, puis poser, tout en supprimant une munition de son sac!
	 * @see Mine
	 * @see Robot#action()
	 */
	@Override
	public void action() {
		this.energie-=2;
	}

	/**
	 * permet de prendre en compte la gestion de pose d'une mine
	 * @param c
	 */
	public int actionp(int x, int y){
		for(Mine mine : munition){
			if(!mine.isInit()){
				mine.poser(this.position.getCol()+y,this.position.getRow()+x);
				return 1;	
			}
		}
		return 0;
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
	public int getDamage(Robot r) {
		r.setEnergie(r.getEnergie()-2);
		return r.getEnergie();
	}
	
	public ArrayList<Mine> getMines(){
		return munition;
	}

	/**
	 * @see Robot#deplacement(char)
	 */
	@Override
	public void deplacement(int x, int y) {
		this.move(x, y);
		this.energie-=2;
	}
	
	public ArrayList<Tuple> casesPossible(int x, int y){
		ArrayList<Tuple> cases = new ArrayList<Tuple>();
		for(int x1=-1; x1<=1; x1++){
			for(int y1=-1; y1<=1; y1++){
				cases.add(new Tuple(x+x1, y+y1, Tuple.DEPLACEMENT));
			}
		}
		return cases;
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
	
	public int toInteger(){
		return 3+super.getEquipe();
	}




}
