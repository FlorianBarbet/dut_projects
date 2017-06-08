package mvc.model;

public enum Mois {
	/**
	 * Enumeration par int.
	 */
	JAN(1), FEV(2), MAR(3), AVR(4),MAI(5),JUIN(6),JUIL(7),AOUT(8),SEPT(9),OCT(10),NOV(11),DEC(12);
	private int data;
	
	/**
	 * Constructeur pour initialisation de l'enumeration
	 * @param d
	 */
	private Mois(int d){data=d;}
	
	/**
	 * toString de l'enumeration
	 * return selon la valeur de data correspondant au mois
	 */
	public String toString(){
		switch(data){
		case 1:return "Janvier";
		case 2:return "Fevrier";
		case 3:return "Mars";
		case 4:return "Avril";
		case 5:return "Mai";
		case 6:return "Juin";
		case 7:return "Juillet";
		case 8:return "Aout";
		case 9:return "Septembre";
		case 10:return "Octobre";
		case 11:return "Novembre";
		case 12:return "Decembre";
		default: return "ERROR";
		}
	}
	
	/**
	 * Sert à incrementer la date de i
	 * @param i
	 * @return mois+i
	 */
	public int up(int i){
		return this.data+i;
	}
	
	/**
	 * Sert à decrementer la date de i
	 * @param i
	 * @return mois-i
	 */
	public int down(int i){
		return this.data-i;
	}
	
	/**
	 * retourne la valeur de l'enumeration en final
	 * @return data
	 */
	public final int getData(){
		return this.data;
	}
	
}
