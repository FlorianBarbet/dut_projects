/**Obstacle hérite d'Element, permettra de bloquer les joueurs.
 * 
 * @see Obstacle#Obstacle()
 * @see Obstacle#takeDamage()
 * @see Obstacle#toString()
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
public class Obstacle extends Element{

	/**Constructeur d'obstacle : dévoilé
	 * @see Element#find
	 */
	public Obstacle(){
		find=true;
	}

	/**Methode hérité : Message d'erreur
	 * @see Element#takeDamage()
	 */
	public Element takeDamage() {
		System.out.println("Vous avez tiré sur un obstacle");
		return this;
	}

	/**Représentation d'un obstacle : O
	 * @return "O"
	 */
	public String toString(){
		return "O";
	}

}