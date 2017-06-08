/**Vide est un Element, permettra d'evaluer toute les cases libres dans Monde
 * 
 * @see Monde
 * @see Vide#toString()
 * @see Vide#takeDamage()
 * @see Vide#Vide()
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

public class Vide extends Element{

	/**Constructeur de vide, le vide est dévoilé !
	 * @see Element#find
	 */
	public Vide(){
		find=true;
	}

	/**Methode hérité, permet d'envoyer un message d'erreur.
	 * @see Element#takeDamage()
	 */
	public Element takeDamage(){System.out.println("Vous avez tiré dans le vide"); return this;}

	/**Représentation du vide
	 * @return " "
	 */
	public String toString(){
		return " ";

	}
}
