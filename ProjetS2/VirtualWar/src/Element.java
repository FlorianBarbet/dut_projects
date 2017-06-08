/**Element classe permettant la cohesion des autres (servira pour generer une grille de différents heritage d'element)
 * aucun constructeur
 * @see Element#find
 * @see Element#setFind()
 * @see Element#isFind()
 * @see Element#takeDamage()
 * @author BARBET Florian
 * @author LEICHT Theo
 * @author MURAT Edouard
 * @author SKAWAND Dylan
 * @author RICHARD Alexandre
 * @author CORNET Thomas
 * @author BRUNI XAVIER
 *
 */

abstract class Element {
	
	protected int energie = 0;
	
	public abstract int toInteger();

}
