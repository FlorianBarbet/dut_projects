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
	public Char(int x, int y,int nx,int ny, int e){
		super(120,10,2,e,"Char");
		position=new Coordonnee(x,y);
	}



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
	public int getDamage(Robot r) {
		r.energie-=6;
		this.action();
		return r.getEnergie();
	}

	/**
	 * @see Robot#deplacement(char)
	 */
	@Override
	public void deplacement(int x, int y) {
		this.move(x, y);
		this.energie-=5;
	}

	/**Permet à éviter les demande de correction à l'ia, ressemble à deplacement
	 * @see Char#deplacement(char)
	 */
	
	public void deplacement4IA(char key) {
		int randX = new Random().nextInt(2)==0?2:-2;
		int randY = new Random().nextInt(2)==0?2:-2;
		this.move(randX, randY);
		this.energie-=5;
	}
	
	public ArrayList<Tuple> casesPossible(int x, int y){
		ArrayList<Tuple> cases = new ArrayList<Tuple>();
		for(int i=-super.portee; i<=super.portee; i++){
			cases.add(new Tuple(x+i, y, Tuple.UNKNOWN));
			cases.add(new Tuple(x, y+i, Tuple.UNKNOWN));
			if(i==-2 || i==2){
				cases.add(new Tuple(x+i, y, Tuple.DEPLACEMENT));
				cases.add(new Tuple(x, y+i, Tuple.DEPLACEMENT));
			}
		}
		return cases;
	}
	
	/**
	 * @see Robot#toString()
	 */
	@Override
	public String toString() {
		if(super.getEquipe() == 1)
			return "C";
		else return "c";
	}
	
	/**
	 * @see Robot#toInteger()
	 */
	public int toInteger(){
		return 7+super.getEquipe();
	}




}
