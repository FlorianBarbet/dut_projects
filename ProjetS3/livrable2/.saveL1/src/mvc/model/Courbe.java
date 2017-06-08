package mvc.model;

import java.util.ArrayList;
/**
 * Courbe encapsule deux ArrayList<> considere comme coordonnee
 * servira a indiquer les coordonnees pour LineChart
 * @author florian barbet
 *
 * @param <typeX>
 * @param <typeY>
 */
public class Courbe<typeX,typeY> {
	
	/**
	 * Tableau des abscisses
	 */
	protected ArrayList<typeX> x = new ArrayList<typeX>();
	/**
	 * Tableau des ordonnees
	 */
	protected ArrayList<typeY> y = new ArrayList<typeY>();


	/**
	 * vérifie que la taille des deux tableaux correspondent
	 * @return 0 si ok ; valeur de la différences sinon
	 */
	private int check(){
		return x.size()-y.size();
	}

	/**
	 * Accesseur de la valeur i dans le tableau des abscisses
	 * @param i
	 * @return la valeur à l'emplacement i
	 */
	public typeX getX(int i){
		return x.get(i);
	}
	/**
	 * Accesseur de la valeur i dans le tableau des ordonnees
	 * @param i
	 * @return la valeur à l'emplacement i
	 */
	public typeY getY(int i){
		return y.get(i);
	}
	
	public void setY(int i,typeY nY){
		y.set(i,nY);
	}

	/**
	 * Ajout des valeurs X et Y pour la courbe ; afin de preserver une taille homogene pour x et y 
	 * verification qu'il ne manque aucune donnee
	 * @param dataX
	 * @param dataY
	 */
	public void addXY(typeX dataX,typeY dataY){
		x.add(dataX);
		y.add(dataY);
	}

	/**Supprime les valeurs X et Y
	 * @see Courbe#addXY(Object, Object)
	 * @param i
	 */
	public void removeXY(int i){
		x.remove(i);
		y.remove(i);
	}

	/**
	 * retourne l'index ou se situe dataX
	 * @param dataX
	 * @return indice de dataX dans x
	 */
	public int indexOfXYbyX(typeX dataX){
		return x.indexOf(dataX);
	}

	/**
	 * retourne l'index ou se situe dataY
	 * @param dataY
	 * @return indice de dataY dans y
	 */
	public int indexOfXYbyY(typeY dataY){
		return y.indexOf(dataY);
	}


	/**
	 * indication de la taille des donnees renvoie -1 si taille de x!= taille de y
	 * @return taille de x ou y ; sinon -1
	 */
	public int sizeOfData(){
		if(this.check()==0){
			return x.size();
		}

		return -1;
	}


}
