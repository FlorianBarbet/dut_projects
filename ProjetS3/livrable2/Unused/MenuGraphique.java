<<<<<<< HEAD:Livrable1/mvc/Menu.java
<<<<<<< HEAD
import java.io.IOException;
import java.util.Scanner;

public class Menu {
	
	Scanner sc = new Scanner(System.in);
	
	void erreurChoix() {
		System.out.println();
		System.out.println("##########   ERREUR   ##########");
		System.out.println("#                              #");
		System.out.println("#           Ce choix           #");
		System.out.println("#         n'existe pas         #");
		System.out.println("#                              #");
		System.out.println("################################");
	}
	
	void menuLancement()throws IOException{
		
		int choix;
		
		System.out.println("##########  PROJET   ###########");
		System.out.println("#                              #");
		System.out.println("#     Voulez-vous importer     #");
		System.out.println("#     une série de données ?   #");
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
			//méthode charger fichier CSV
		case 9 :
			menuQuitter();
		default:
			erreurChoix();
			System.out.println();
			menuLancement();
		}
	}
	
	void menuChoixActions()throws IOException{
		
		int choix;
		
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
//		System.out.println("#  3 : une prévision           #");
//		System.out.println("#                              #");	
		System.out.println("#  0 : Retour menu précédent   #");
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
			//menu choix prévision
		case 0 :
			menuLancement();
		case 9 :
			menuQuitter();
		default:
			erreurChoix();
			System.out.println();
		}
	}
	
	void menuChoixTransformation()throws IOException{
		
		int choix;
		
		System.out.println();
		System.out.println("##############   PROJET   ############");
		System.out.println("#                                    #");
		System.out.println("#  quelles transformations voulez    #");
		System.out.println("#          vous effectuer ?          #");
		System.out.println("#                                    #");
		System.out.println("######################################");
		System.out.println("#                                    #");
		System.out.println("#  1 : Logarithme                    #");
//		System.out.println("#                                    #");	
//		System.out.println("#  2 : Box-Cox                       #");
//		System.out.println("#                                    #");	
//		System.out.println("#  3 : Logistique                    #");
		System.out.println("#                                    #");	
		System.out.println("#  4 : Moyenne mobile                #");
//		System.out.println("#                                    #");
//		System.out.println("#  5 : Moyenne mobile pondérée       #");
//		System.out.println("#                                    #");
//		System.out.println("#  6 : Saisonnalité                  #");
//		System.out.println("#                                    #");	
//		System.out.println("#  7 : Tendance linaire              #");
//		System.out.println("#                                    #");
//		System.out.println("#  8 : Oprateur de différenciation   #");
		System.out.println("#                                    #");
		System.out.println("#  0 : Retour menu précédent         #");
		System.out.println("#                                    #");
		System.out.println("#  9 : Quitter PROJET                #");		
		System.out.println("#                                    #");
		System.out.println("######################################");
		
		choix = Integer.parseInt(sc.next());
		
		switch(choix){
		case 1 :
			//méthode logarithme
		case 2 :
			//méthode box-cox
		case 3 :
			//méthode logistique
		case 4 :
			//méthode moyenne mobile
		case 5 :
			//méthode moyenne pondre
		case 6 :
			//méthode saisonnalit
		case 7 :
			//méthode tendance linaire
		case 8 :
			//méthode oprateur de diffrenciation
		case 0 :
			menuChoixActions();
		case 9 :
			menuQuitter();
	    default :
	    	erreurChoix();
			System.out.println();
		}
	}
	
	void menuChoixPrevisions()throws IOException{
		
		int choix;
		
		System.out.println();
		System.out.println("##############   PROJET   ############");
		System.out.println("#                                    #");
		System.out.println("#     quelles prévisions voulez      #");
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
		System.out.println("#  0 : Retour menu précédent         #");
		System.out.println("#                                    #");
		System.out.println("#  9 : Quitter PROJET                #");		
		System.out.println("#                                    #");
		System.out.println("######################################");
		
		choix = Integer.parseInt(sc.next());
		
		switch(choix){
		case 1:
			//méthode lissage exponentiel simple
		case 2 :
			//méthode lissage exponentiel double
		case 3 :
			//méthode holt-winters
		case 0 :
			menuChoixActions();
		case 9 :
			menuQuitter();
		default :
			erreurChoix();
			System.out.println();
		}
	}
	
	void menuChoixAnalyse()throws IOException{
		
		int choix;
		
		System.out.println();
		System.out.println("##############   PROJET   ############");
		System.out.println("#                                    #");
		System.out.println("#     quelles analyses voulez        #");
		System.out.println("#        vous effectuer ?            #");
		System.out.println("#                                    #");
		System.out.println("######################################");
		System.out.println("#                                    #");
//		System.out.println("#  1 : Lissage exponentiel simple    #");
//		System.out.println("#                                    #");	
//		System.out.println("#  2 : Lissage exponentiel double    #");
//		System.out.println("#                                    #");	
		System.out.println("#  3 : Autocorrélations des résidus  #");
		System.out.println("#                                    #");
		System.out.println("#  0 : Retour menu précédent         #");
		System.out.println("#                                    #");
		System.out.println("#  9 : Quitter PROJET                #");		
		System.out.println("#                                    #");
		System.out.println("######################################");
		
		choix = Integer.parseInt(sc.next());
		
		switch(choix){
		case 1:
			//méthode lissage exponentiel simple
		case 2 :
			//méthode lissage exponentiel double
		case 3 :
			//méthode autocorrlation
		case 0 :
			menuChoixActions();
		case 9 :
			menuQuitter();
		default :
			erreurChoix();
			System.out.println();
		}
	}
	
	void menuQuitter()throws IOException{
		
		int choix;
		
		System.out.println();
		System.out.println("#############  ASSOLOC  ###########");
		System.out.println("#                                 #");
		System.out.println("#      Etes vous sur de vouloir   #");
		System.out.println("#              quitter ?          #");
		System.out.println("#                                 #");
		System.out.println("###################################");
		System.out.println("#                                 #");
		System.out.println("#  1 : Oui                        #");
		System.out.println("#                                 #");
		System.out.println("#  2 : Non                        #");
		System.out.println("#                                 #");
		System.out.println("#  3 : annuler                    #");
		System.out.println("#                                 #");
		System.out.println("###################################");

		choix = Integer.parseInt(sc.next());

		switch (choix) {
		case 1 :
			System.out.println("merci d'avoir utilisé ce programme ! au revoir !");
			System.exit(0);
		case 2 :
			menuLancement();
		case 3 :
			menuLancement();	
		default:
			erreurChoix();
			System.out.println("\nRetour au menu principal\n\n");
		}
	}
}
=======
package mvc;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Menu extends Stage{
	
	private Text actionStatus;
	private static final String titleTxt = "Selection d'un fichier CSV";
	private Label label;
	private HBox labelHb;
	private Button btn1;
	private HBox buttonHb1;
	private VBox vbox;
	private Scene scene;
	public Stage primaryStage;
	private String chemin;
	
	/**
	 * Constructeur de la fenetre de selection de fichier CSV
	 * @author Florian Hirson
	 *
	 * 
	 */
	Menu() {
		super();
		primaryStage = new Stage();
		primaryStage.setTitle(titleTxt);	

		// Window label
		label = new Label("Select File Choosers");
		label.setTextFill(Color.DARKBLUE);
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		labelHb = new HBox();
		labelHb.setAlignment(Pos.CENTER);
		labelHb.getChildren().add(label);

		// Buttons
		btn1 = new Button("Choisissez un fichier...");
		btn1.setOnAction(e-> showSingleFileChooser());
		buttonHb1 = new HBox(10);
		buttonHb1.setAlignment(Pos.CENTER);
		buttonHb1.getChildren().addAll(btn1);


		// Status message text
		actionStatus = new Text();
		actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
		actionStatus.setFill(Color.FIREBRICK);

		// Vbox
		vbox = new VBox(30);
		vbox.setPadding(new Insets(25, 25, 25, 25));
		vbox.getChildren().addAll(labelHb, buttonHb1, actionStatus);

		// Scene
		scene = new Scene(vbox, 500, 300); // w x h
		primaryStage.setScene(scene);
		primaryStage.show();


	}



	private void showSingleFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Fichiers CSV", "*.csv"));
		File selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile != null) {
			actionStatus.setText("Fichier choisis: " + selectedFile.getName());
			setChemin(selectedFile.getAbsolutePath());
			
		}
		else {
			actionStatus.setText("Selection de fichier annulee.");
		}
		
	}



	/**
	 * @return the chemin
	 */
	public String getChemin() {
		return chemin;
	}
	
	/**
	 * @param chemin the chemin to set
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

}
>>>>>>> a66bc6bca4ffa672ba6d1d1f1e70d81af4b0d924
=======
package mvc;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Menu extends Stage{
	
	private Text actionStatus;
	private static final String titleTxt = "Selection d'un fichier CSV";
	private Label label;
	private HBox labelHb;
	private Button btn1;
	private HBox buttonHb1;
	private VBox vbox;
	private Scene scene;
	public Stage primaryStage;
	private String chemin;
	
	/**
	 * Constructeur de la fenetre de selection de fichier CSV
	 * @author Florian Hirson
	 *
	 * 
	 */
	Menu() {
		super();
		primaryStage = new Stage();
		primaryStage.setTitle(titleTxt);	

		// Window label
		label = new Label("Select File Choosers");
		label.setTextFill(Color.DARKBLUE);
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		labelHb = new HBox();
		labelHb.setAlignment(Pos.CENTER);
		labelHb.getChildren().add(label);

		// Buttons
		btn1 = new Button("Choisissez un fichier...");
		btn1.setOnAction(e-> showSingleFileChooser());
		buttonHb1 = new HBox(10);
		buttonHb1.setAlignment(Pos.CENTER);
		buttonHb1.getChildren().addAll(btn1);


		// Status message text
		actionStatus = new Text();
		actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
		actionStatus.setFill(Color.FIREBRICK);

		// Vbox
		vbox = new VBox(30);
		vbox.setPadding(new Insets(25, 25, 25, 25));
		vbox.getChildren().addAll(labelHb, buttonHb1, actionStatus);

		// Scene
		scene = new Scene(vbox, 500, 300); // w x h
		primaryStage.setScene(scene);
		primaryStage.show();


	}



	private void showSingleFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Fichiers CSV", "*.csv"));
		File selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile != null) {
			actionStatus.setText("Fichier choisis: " + selectedFile.getName());
			setChemin(selectedFile.getAbsolutePath());
			
		}
		else {
			actionStatus.setText("Selection de fichier annulee.");
		}
		
	}



	/**
	 * @return the chemin
	 */
	public String getChemin() {
		return chemin;
	}
	
	/**
	 * @param chemin the chemin to set
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

}
>>>>>>> dad7d3b06aab1ed1624d97682be8414490a43b6b:Livrable1/mvc/MenuGraphique.java
