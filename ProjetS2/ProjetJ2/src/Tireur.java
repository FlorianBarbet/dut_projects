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
	Tireur(int x, int y,int nx,int ny, int e){super(80,3,1,e);System.out.println("---2");position=new Coordonnee(x,y,nx,ny);}


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
	}

	/**
	 * @see Robot#getDamage(Robot r)
	 */
	@Override
	public void getDamage(Robot r) {
		r.energie-=3;
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

		this.energie-=1;
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


}
