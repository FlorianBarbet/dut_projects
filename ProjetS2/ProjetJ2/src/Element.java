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
	/**valeur booléene permettant de dévoiler ou non l'element
	 * 
	 */

	protected boolean find;
	/**Energie permet de voir la vitalié d'un element, ou d'un robot. Ici initialisé à 999.
	 * 
	 */

	public Element(){
		find=false;
	}

	protected int energie = 9999;
	/**Ne renvoie rien, rend l'attribut find à true pour dévoiler l'element
	 * @return void
	 */
	public void setFind() {
		find = true;
	}


	/**Renvoie la valeur de find; true = dévoiler ; false = non dévoilé
	 * 
	 * @return find (est ce que l'element est dévoiler?)
	 */
	public boolean isFind() {
		return find;
	}

	/** methode abstraite permet de voir combien de degat prendra un element.
	 * 
	 */
	public abstract Element takeDamage();

}
