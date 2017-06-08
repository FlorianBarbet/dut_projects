/**Permet de definir des coordonnées envers Robot et Mine
 * @see Coordonnee#_col
 * @see Coordonnee#_row
 * @see Coordonnee#_maxCol
 * @see Coordonnee#_maxRow
 * @see Coordonnee#Coordonnee(int, int, int, int)
 * @see Coordonnee#setCoordonnee(int, int)
 * @see Coordonnee#modifCol(int)
 * @see Coordonnee#modifRow(int)
 * @see Coordonnee#getCol()
 * @see Coordonnee#getRow()
 * 
 * @see Robot
 * @see Mine
 * @author Florian BARBET
 *
 */
public class Coordonnee {

	/**_col
	 * Permet d'identifier la colonne
	 */
	private int _col;

	/**_row
	 * Permet d'identifier la ligne
	 */
	private int _row;

	/**Constructeur, prends parametres d'entiers, afin de définir les attributs
	 * 
	 *  @see Coordonnee#_col
	 *  @see Coordonnee#_row
	 *  @see Coordonnee#_maxCol
	 *  @see Coordonnee#_maxRow
	 * 
	 * @param c
	 * @param r
	 * @param mC
	 * @param mR
	 */
	public Coordonnee(int c, int r) {
		_col = c;
		_row = r;
	}
	public Coordonnee(){}

	/**Permet de modifier une coordonnee, par la ligne et la colonne
	 * 
	 * @see Coordonnee#_col
	 * @see Coordonnee#_row
	 * @param c
	 * @param r
	 */
	public void setCoordonnee(int c, int r) {
		_col = c;
		_row = r;
	}

	/**Permet de modifier juste la colonne en augmentant de 1 unité (verifie si ne depasse pas les maximums)
	 * @see Coordonnee#_maxCol
	 * @see Coordonnee#_col
	 * @param i
	 */
	public void modifCol(int i) {
		_col += i;
	}
	/**Permet de modifier juste la ligne en augmentant de 1 unité (verifie si ne depasse pas les maximums)
	 * @see Coordonnee#_maxRow
	 * @see Coordonnee#_row
	 * @param i
	 */
	public void modifRow(int i) {
		_row += i;
	}

	/**Renvoie la valeur de la colonne
	 * 
	 * @return _col
	 * @see Coordonnee#_col
	 */
	public int getCol() {
		return _col;
	}
	/**Renvoie la valeur de la ligne
	 * 
	 * @return _row
	 * @see Coordonnee#_row
	 */
	public int getRow() {
		return _row;
	}
}