import java.util.ArrayList;

/**Tireur est une classe qui h�rit� de Robot
 * 
 * @see Tireur#Tireur(int, int, int, int)
 * @see Robot
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
public class Tireur extends Robot {

	/**Constructeur : permet de donner des coordonn�e d'un tireur � sa cr�ation
	 * 
	 * @param x
	 * @param y
	 * @param nx
	 * @param ny
	 */
	Tireur(int x, int y,int nx,int ny, int e){
		super(80,3,1,e, "Tireur");
		position=new Coordonnee(x,y);
	}


	/**
	 * @see Robot#action()
	 */
	@Override
	public void action() {
		// TODO Auto-generated method stub
		this.energie-=2;
	}
	/**
	 * @see Robot#getDamage(Element r)
	 */
	@Override
	public void getDamage(Element r) {
		r.energie-=3;
		this.action();
	}

	/**
	 * @see Robot#getDamage(Robot r)
	 */
	@Override
	public int getDamage(Robot r) {
		r.energie-=3;
		return r.getEnergie();
	}

	/**
	 * @see Robot#deplacement(char)
	 */
	@Override
	public void deplacement(int x, int y) {
		this.move(x, y);
		this.energie-=1;
	}
	
	public ArrayList<Tuple> casesPossible(int x, int y){
		int x1, y1;
		ArrayList<Tuple> cases = new ArrayList<Tuple>();
		for(x1=-super.portee; x1<=super.portee; x1++){
			cases.add(new Tuple(x1+x, y, Tuple.UNKNOWN));
			cases.add(new Tuple(x, x1+y, Tuple.UNKNOWN));
		}
		for(x1=-1; x1<=1; x1++){
			for(y1=-1; y1<=1; y1++){
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
			return "T";
		else return "t";
	}
	
	public int toInteger(){
		return 5+super.getEquipe();
	}


}
