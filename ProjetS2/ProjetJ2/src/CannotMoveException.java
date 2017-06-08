/**Permet de gérer le fat que l'utilisateur ne peut franchir un obstacle ou un autre robot
 * @see Monde
 * @see Jouer
 * @author florian
 *
 */
public class CannotMoveException extends Exception {

	/**
	 * numero de serie de l'exception
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * message d'erreur renvoyé par l'exception
	 */
	private String err;

	/**
	 * Constructeur vide de l'exception
	 */
	public CannotMoveException() {
		err = null;
	}

	/**
	 * Constructeur de l'exception avec un message à envoyer
	 * @param s
	 */
	public CannotMoveException(String s) {
		err = s;
	}

	/**
	 * rendu de l'exception
	 */
	@Override
	public String toString() {
		return err;
	}
}