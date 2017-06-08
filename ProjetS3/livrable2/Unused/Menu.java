package mvc;

import java.util.Scanner;

public class Menu {

	Scanner sc = new Scanner(System.in);

	private int choix;

	void erreurChoix() {

		System.out.println();
		System.out.println("##########   ERREUR   ##########");
		System.out.println("#                              #");
		System.out.println("#           Ce choix           #");
		System.out.println("#         n'existe pas         #");
		System.out.println("#                              #");
		System.out.println("################################");
	}


	void menuLancement(){

		System.out.println();
		System.out.println("##########  PROJET   ###########");
		System.out.println("#                              #");
		System.out.println("#     Voulez-vous importer     #");
		System.out.println("#     une s�rie de donn�es ?   #");
		System.out.println("#                              #");
		System.out.println("################################");
		System.out.println("#                              #");
		System.out.println("#  1 : Charger fichier CSV     #");
		System.out.println("#                              #");					
		System.out.println("#  9 : Quitter PROJET          #");		
		System.out.println("#                              #");
		System.out.println("################################");

		choix = Integer.parseInt(sc.next());

		switch(choix){
		case 1 : 
			//m�thode charger fichier CSV
		case 9 :
			//menu quitter et sauvegarde
		default:
			erreurChoix();
			System.out.println();
			menuLancement();
		}
	}

	void menuChoixActions(){

		System.out.println();
		System.out.println("##########  PROJET   ###########");
		System.out.println("#                              #");
		System.out.println("#     quelles actions voulez   #");
		System.out.println("#        vous effectuer        #");
		System.out.println("#                              #");
		System.out.println("################################");
		System.out.println("#                              #");
		System.out.println("#  1 : Une transformation      #");
		System.out.println("#                              #");	
		System.out.println("#  2 : Une analyse             #");
		System.out.println("#                              #");	
		System.out.println("#  3 : une pr�vision           #");
		System.out.println("#                              #");	
		System.out.println("#  0 : Retour menu pr�c�dent   #");
		System.out.println("#                              #");	
		System.out.println("#  9 : Quitter PROJET          #");		
		System.out.println("#                              #");
		System.out.println("################################");

		choix = Integer.parseInt(sc.next());

		switch(choix){
		case 1 :
			//menu choix transformations
		case 2 :
			//menu choix analyse
		case 3 :
			//menu choix pr�vision
		case 0 :
			menuLancement();
		case 9 :
			//menu quitter et sauvegarde
		default:
			erreurChoix();
			System.out.println();
		}
	}
	
	void menuChoixTransformation(){
		
		System.out.println();
		System.out.println("##############   PROJET   ############");
		System.out.println("#                                    #");
		System.out.println("#  quelles transformations voulez    #");
		System.out.println("#          vous effectuer ?          #");
		System.out.println("#                                    #");
		System.out.println("######################################");
		System.out.println("#                                    #");
		System.out.println("#  1 : Logarithme                    #");
		System.out.println("#                                    #");	
		System.out.println("#  2 : Box-Cox                       #");
		System.out.println("#                                    #");	
		System.out.println("#  3 : Logistique                    #");
		System.out.println("#                                    #");	
		System.out.println("#  4 : Moyenne mobile                #");
		System.out.println("#                                    #");
		System.out.println("#  5 : Moyenne mobile pond�r�e       #");
		System.out.println("#                                    #");
		System.out.println("#  6 : Saisonnalit�                  #");
		System.out.println("#                                    #");	
		System.out.println("#  7 : Tendance lin�aire             #");
		System.out.println("#                                    #");
		System.out.println("#  8 : Op�rateur de diff�renciation  #");
		System.out.println("#                                    #");
		System.out.println("#  0 : Retour menu pr�c�dent         #");
		System.out.println("#                                    #");
		System.out.println("#  9 : Quitter PROJET                #");		
		System.out.println("#                                    #");
		System.out.println("######################################");
		
		choix = Integer.parseInt(sc.next());
		
		switch(choix){
		case 1 :
			//m�thode logarithme
		case 2 :
			//m�thode box-cox
		case 3 :
			//m�thode logistique
		case 4 :
			//m�thode moyenne mobile
		case 5 :
			//m�thode moyenne pond�r�e
		case 6 :
			//m�thode saisonnalit�
		case 7 :
			//m�thode tendance lin�aire
		case 8 :
			//m�thode op�rateur de diff�renciation
		case 0 :
			menuChoixActions();
		case 9 :
			//menu quitter et sauvegarde
	    default :
	    	erreurChoix();
			System.out.println();
		}
	}
	
	void menuChoixPrevisions(){
		
		System.out.println();
		System.out.println("##############   PROJET   ############");
		System.out.println("#                                    #");
		System.out.println("#     quelles pr�visions voulez      #");
		System.out.println("#        vous effectuer ?            #");
		System.out.println("#                                    #");
		System.out.println("######################################");
		System.out.println("#                                    #");
		System.out.println("#  1 : Lissage exponentiel simple    #");
		System.out.println("#                                    #");	
		System.out.println("#  2 : Lissage exponentiel double    #");
		System.out.println("#                                    #");	
		System.out.println("#  3 : Holt-Winters                  #");
		System.out.println("#                                    #");
		System.out.println("#  0 : Retour menu pr�c�dent         #");
		System.out.println("#                                    #");
		System.out.println("#  9 : Quitter PROJET                #");		
		System.out.println("#                                    #");
		System.out.println("######################################");
		
		choix = Integer.parseInt(sc.next());
		
		switch(choix){
		case 1:
			//m�thode lissage exponentiel simple
		case 2 :
			//m�thode lissage exponentiel double
		case 3 :
			//m�thode holt-winters
		case 0 :
			menuChoixActions();
		case 9 :
			//menu quitter et sauvegarde
		default :
			erreurChoix();
			System.out.println();
		}
	}
	
	void menuChoixAnalyse(){
		
		System.out.println();
		System.out.println("##############   PROJET   ############");
		System.out.println("#                                    #");
		System.out.println("#     quelles analyses voulez        #");
		System.out.println("#        vous effectuer ?            #");
		System.out.println("#                                    #");
		System.out.println("######################################");
		System.out.println("#                                    #");
		System.out.println("#  1 : Lissage exponentiel simple    #");
		System.out.println("#                                    #");	
		System.out.println("#  2 : Lissage exponentiel double    #");
		System.out.println("#                                    #");	
		System.out.println("#  3 : Autocorr�lations des r�sidus  #");
		System.out.println("#                                    #");
		System.out.println("#  0 : Retour menu pr�c�dent         #");
		System.out.println("#                                    #");
		System.out.println("#  9 : Quitter PROJET                #");		
		System.out.println("#                                    #");
		System.out.println("######################################");
		
		choix = Integer.parseInt(sc.next());
		
		switch(choix){
		case 1:
			//m�thode lissage exponentiel simple
		case 2 :
			//m�thode lissage exponentiel double
		case 3 :
			//m�thode autocorr�lation
		case 0 :
			menuChoixActions();
		case 9 :
			//menu quitter et sauvegarde
		default :
			erreurChoix();
			System.out.println();
		}
	}
	
	











}
