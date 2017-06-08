/**Mine est un element, permettra de poser cet element à l'aide d'un piegeur. C'est ici qu'est géré la destruction. 
 * et l'endroit où sera poser la mine
 * 
 * @see Mine#Poser(int, int)
 * @see Mine#getEnergie()
 * @see Mine#takeDamage()
 * @see Element
 * @author BARBET Florian
 * @author LEICHT Theo
 * @author MURAT Edouard
 * @author SKAWAND Dylan
 * @author RICHARD Alexandre
 * @author CORNET Thomas
 * @author BRUNI XAVIER
 *
 */
public class Mine extends Element{



	/**PositionM permettra au moment où l'on pose la mine, de la poser à la même position qu'un Piegeur.
	 * 
	 */

	private Coordonnee positionM= new Coordonnee();

	/**Recupére l'equipe du piegeur par un setter afin de bien utiliser le toString()
	 * @see Mine#toString()
	 * @see Mine#setTeam(int)
	 */
	private int equipe;

	/** Permet de définir l'equipe à laquelle appartient une Mine
	 * 
	 * @param a
	 */
	public void setTeam(int a) { equipe=a;}

	/**Permet de poser à un endroit c, r sur une grille
	 * 
	 * @param c
	 * @param r
	 */
	public void Poser(int c, int r){
		this.positionM.setCoordonnee(c, r);
		energie = 1;
		//find = true;
	}



	/**retourne l'energie de la mine il s'agit juste d'un simple accesseur
	 * 
	 * @return energie
	 */
	public int getEnergie() {return energie;}


	/**Methode hérité, abstraite d'element
	 * permet de detruire la Mine en lui infligeant des dégats
	 * @see Element#takeDamage()
	 */
	@Override
	public Element takeDamage(){energie--; return this;}

	/**
	 * enleve les points d'energie au Robot r
	 * @param Robot r
	 */

	public void getDamage(Robot r) {
		r.energie-=2;
	}
	/**Selon l'equipe la représentation sera différente
	 * @see Mine#equipe
	 * @see Piegeur#Piegeur(int, int, int, int)
	 * @return "x" ou "X"
	 */
	public String toString() {
		if(equipe == 1)
			return "X";
		else return "x";
	}


}
