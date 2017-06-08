
import java.util.Observable;
/**
 * Model du MVC sur la Courbe
 * @author Rayan
 *
 * @param <X>
 * @param <Y>
 */
public class CourbeModel<X,Y> extends Observable {

	/**
	 * Courbe<X,Y> courbe sur laquelle toute transformation passera
	 */
	private Courbe<X,Y> courbeData = new Courbe<X,Y>();

	public CourbeModel(Courbe<X,Y> c){
		c = courbeData;
		
	}

	/**
	 * Renvoie la courbe accession
	 * @return courbeData
	 */
	public Courbe<X,Y> getCourbe(){
		return courbeData;
	}


	/**
	 * Permet de modifier la courbe et initialiser
	 * @param c
	 */
	public void setCourbe(Courbe<X,Y> c){
		courbeData = c;
		setChanged();
		notifyObservers();
	}


	/**
	 * 
	 * TODO Methode de transformation de la courbe
	 * 
	 **/

	public double[] MoyenneMobile(Courbe<Double,Double> c,int ordre){

		double tabX[]=new double[c.sizeOfData()];
		double moyenne = 0;
		double result[] = new double[c.sizeOfData()];
		for(int i=0; i<c.sizeOfData();++i){
			while(i>=3){
				
				tabX[i]=(double)c.getX(i);
				if(ordre%2==0){
					moyenne=(1/ordre)*(((tabX[i-2])/2)+tabX[i-1]+tabX[i]+tabX[i+1]+(tabX[i+2]/2));
				}
				else{
					moyenne=(1/ordre)*(tabX[i-1]+tabX[i]+tabX[i+1]);
				}
				result[i]=moyenne;
			}

		}

		return result;
	}

	public double residu(Courbe<Double,Double> c,int ordre){
		CourbeModel<Double, Double> cm = new CourbeModel<Double,Double>(c);
		double res = 0;
		double tabX[]=new double[c.sizeOfData()];
		double[] m = cm.MoyenneMobile(c,ordre);
		
		for(int i=0;i<c.sizeOfData();++i){
			tabX[i]=(double)c.getX(i);
			
			res=tabX[i]-m[i];
		}	
		return res;
	}

}
