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

	protected Coordonnee position = new Coordonnee();

	/**Recupére l'equipe du piegeur par un setter afin de bien utiliser le toString()
	 * @see Mine#toString()
	 * @see Mine#setTeam(int)
	 */
	private int equipe;
	private boolean init = false;
	private boolean alive = false;
	
	public Mine(int equipe){
		this.equipe = equipe;
	}

	/**Permet de poser à un endroit c, r sur une grille
	 * 
	 * @param c
	 * @param r
	 */
	public void poser(int c, int r){
		this.position.setCoordonnee(c, r);
		init = true;
		alive = true;
	}

	public boolean isInit(){
		return init;
	}

	/**
	 * enleve les points d'energie au Robot r et detruit la mine
	 * @param Robot r
	 */

	public int getDamage(Robot r) {
		r.energie-=2;
		alive = false;
		return r.getEnergie();
	}
	
	/**
	 * Permet de savoir si une bombe est actuellement pose sur la carte ou non
	 * @return
	 */
	public boolean isAlive(){
		return alive;
	}
	
	/**
	 * @return @see {@link Robot#toInteger()}
	 */
	public int toInteger(){
		return 2;
	}

}
