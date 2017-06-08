package CodesMenu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mvc.control.CourbeController;
import mvc.model.Courbe;
import mvc.model.CourbeModel;
import mvc.view.CourbeVue;
import mvc.view.CourbeVueConcret;
import mvc.view.DialogChoixCourbes;
import mvc.view.DialogTelechargement;
import mvc.view.InputDialogs;
import mvc.view.SelectFileChooser;

public class MenuProjet extends Application{
	static String choixT = "";
	static String choixA = "";
	static String choixP = "";

	Tab tab = new Tab();
	static double lambda = 0;
	static int ordre = 0;
	ArrayList<Tab> listT = new ArrayList<Tab>();
	ArrayList<Courbe<Number, Number>> choix = new ArrayList<Courbe<Number, Number>>(); //Liste de courbes choisies par l'utilisateur
	CourbeVue<Number,Number> vueF = null;	                // en preparation pour Livrable 2

    private Button screenShot = new Button("Screenshot");
	LineChart<Number,Number> lineChart;


	// load the stylesheets
	//String styleMetroD = getClass().getResource("styles/JMetroDarkTheme.css").toExternalForm();
	//String styleMetroL = getClass().getResource("styles/JMetroLightTheme.css").toExternalForm();
	//String styleBrume = getClass().getResource("styles/brume.css").toExternalForm();

	CourbeModel<Number,Number> model; 				//	Modele MVC
	CourbeVue<Number,Number> vue;	                // en preparation pour Livrable 2
	CourbeController<Number,Number> control;        // structure OK

	DialogChoixCourbes d;

	public static void main(String[] args){
		//System.setProperty("http.proxyPort", "3128");
		//System.setProperty("http.proxyHost", "proxy.univ-lille1.fr");
		launch(args);
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused", "static-access" })
	@Override

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Projet de modélisation");
		String save = "";
		String data = "";
		ArrayList<Courbe<Number,Number>> listc = new ArrayList<Courbe<Number,Number>>();
		BufferedWriter fichier_result = null;

		Courbe<Number,Number> donnee = new Courbe<Number,Number>();
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		Courbe<Number,Number> csr = new Courbe<Number,Number>();
		Courbe<Number,Number> cs = new Courbe<Number,Number>();
		Courbe<Number,Number> cmd = new Courbe<Number,Number>();
		Courbe<Number,Number> logis = new Courbe<Number,Number>();
		Courbe<Number,Number> log = new Courbe<Number,Number>();
		Courbe<Number,Number> bc = new Courbe<Number,Number>();

		Courbe<Number,Number> res = new Courbe<Number,Number>();

		ArrayList<String> listTitle = new ArrayList<String>();
		ArrayList<Courbe<Number,Number>> listCourbe = new ArrayList<Courbe<Number,Number>>(); // permet d'indexer les courbes et donc de modifier la couleur d'une courbe vis�e
		ArrayList<Integer> choice = new ArrayList<Integer>();

		BorderPane root = new BorderPane(); //borderpane de la scene
		Scene scene = new Scene(root);
		MenuBar menuBar = new MenuBar(); //barre de menus


		Label lLambda = new Label("Lambda : "+lambda);
		Label lOrdre = new Label("Ordre : "+ordre);

		//Vbox contenant les choicebox d'ajout de transformation/analyse/prevision
		VBox ajout = new VBox();
		ajout.setSpacing(10);
		ajout.setPadding(new Insets(10, 10, 10, 10));


		TabPane tabPane = new TabPane(); //Tabpane contenant les onglets de linecharts

		root.setTop(menuBar);
		root.setLeft(ajout);
		root.setCenter(tabPane);

		Label lAjouT = new Label("Ajouter une transformation : ");
		Label lAjouA = new Label("Ajouter une analyse : ");
		Label lAjouP = new Label("Ajouter une prévision : ");

		HBox ajoutT = new HBox(); //hbox pour centrer la choicebox et le bouton des transformations
		ajoutT.setSpacing(10);

		HBox ajoutA = new HBox(); //hbox pour centrer la choicebox et le bouton des analyses
		ajoutA.setSpacing(10);

		HBox ajoutP = new HBox();  //hbox pour centrer la choicebox et le bouton des previsions
		ajoutP.setSpacing(10);

		//choicebox des transformations
		ChoiceBox<String> cAjoutT = new ChoiceBox<String>(FXCollections.observableArrayList("Logarithme Yt1", "BoxCox BC", "Logistique Yt2", "Moyenne Mobile (Mt)", "Xt-Mt", "St : saison", "Xt-St desaisonnalisation"));
		cAjoutT.setPrefWidth(150);

		//choicebox des analyses
		ChoiceBox<String> cAjoutA = new ChoiceBox<String>(FXCollections.observableArrayList("Graphe des résidus", "Variance résiduelle", "Autocorrélation des résidus"));
		cAjoutA.setPrefWidth(150);

		//choicebox des previsions
		ChoiceBox<String> cAjoutP = new ChoiceBox<String>(FXCollections.observableArrayList("Lissage exponentiel simple", "Lissage exponentiel double", "Holt-Winters"));
		cAjoutP.setPrefWidth(150);

		Button bAjoutT = new Button("Ajouter"); //bouton pour ajouter une transformation
		Button bAjoutA = new Button("Ajouter");	//bouton pour ajouter une analyse
		Button bAjoutP = new Button("Ajouter");	//bouton pour ajouter une prevision
        Button zoom = new Button("Zoom"); //bouton pour le zoom

		ajoutT.getChildren().addAll(cAjoutT,bAjoutT);
		ajoutA.getChildren().addAll(cAjoutA,bAjoutA);
		ajoutP.getChildren().addAll(cAjoutP,bAjoutP);

		ajout.getChildren().addAll(lAjouT,ajoutT,lAjouA,ajoutA,lAjouP,ajoutP,lLambda,lOrdre,screenShot,zoom);



		Menu menuF = new Menu("File");
		Menu menuO = new Menu("Options");
		Menu menuH = new Menu("Aide");
		Menu menuS = new Menu("Styles");

		MenuItem reafficher = new MenuItem("Réafficher les onglets");
		MenuItem reset = new MenuItem("Supprimer tous les onglets et toutes les courbes");

		MenuItem chargerCSV = new MenuItem("Charger un fichier CSV");
		MenuItem chargerCSVInternet = new MenuItem("Charger un fichier CSV par internet");
		MenuItem saveCourbes = new MenuItem("Sauvegarder les courbes");

		MenuItem exit = new MenuItem("Exit");
		MenuItem aide = new MenuItem("Aide en ligne");
		MenuItem apropos = new MenuItem("A propos");

		MenuItem vanilla = new MenuItem("Vanilla");
		MenuItem metroD = new MenuItem("Metro foncé");
		MenuItem metroL = new MenuItem("Metro clair");
		MenuItem brume = new MenuItem("Brume");

		menuBar.getMenus().addAll(menuF,menuO,menuS,menuH);
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		menuO.getItems().addAll(reafficher,reset);
		menuF.getItems().addAll(chargerCSV,chargerCSVInternet,saveCourbes);
		menuF.getItems().add(new SeparatorMenuItem());
		menuF.getItems().add(exit);
		menuH.getItems().addAll(aide,apropos);
		menuS.getItems().addAll(vanilla,metroD,metroL,brume);

		cAjoutT.getSelectionModel() //pour récup la transformation que l'utilisateur a choisi
		.selectedItemProperty()
		.addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> choixT = newValue );

		cAjoutA.getSelectionModel() //pour récup l'analyse que l'utilisateur a choisi
		.selectedItemProperty()
		.addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> choixA = newValue );

		cAjoutP.getSelectionModel() //pour récup la prevision que l'utilisateur a choisi
		.selectedItemProperty()
		.addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> choixP = newValue );

        //Evenement de ScreenShot
		screenShot.setOnAction(e -> {

			/* Attention pour linux: new File(file.toString()+"/transformation.png"); */
			/* Attention pour windows: new File(file.toString()+"\\transformatin.png"); */
			HBox buttons = new HBox(10);

			Button ok = new Button("OK");
			Button cancel = new Button("Annuler");

			buttons.getChildren().addAll(ok,cancel);
			buttons.setAlignment(Pos.CENTER_RIGHT);

			Label choix = new Label("Quel nom voulez-vous ?");
			TextField textChoix = new TextField();
			textChoix.setMaxWidth(150);

			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			vbox.setSpacing(15);
			vbox.getChildren().addAll(choix,textChoix,buttons);
			Stage choixNom = new Stage();
			Scene sceneNom = new Scene(vbox);
			choixNom.setScene(sceneNom);
			choixNom.setHeight(200);
			choixNom.setWidth(500);
			choixNom.setTitle("Choix du nom Fichier");
			choixNom.initModality(Modality.APPLICATION_MODAL);
		    choixNom.show();

			ok.setOnAction(el->{
				DirectoryChooser dialog = new DirectoryChooser();
				File file= dialog.showDialog(screenShot.getScene().getWindow());
				File file2= new File(file.toString()+"/"+textChoix.getText()+".png");
				System.out.println("file 2"+file2);
				String chemin= file2+"";
				System.out.println("print: "+file);
				WritableImage writableImage = new WritableImage((int)screenShot.getScene().getWidth(), (int)screenShot.getScene().getHeight());
				screenShot.getScene().snapshot(writableImage);
				if(chemin != null) {
					try{
						ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null),"png",file2);
						System.out.println(""+file2.getAbsolutePath());
					}catch(IOException ex){Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

				choixNom.close();
			});

			cancel.setOnAction(e1 -> {
				choixNom.close();
			});
		});

		//Evenement de zoom
		zoom.setOnAction(e ->{
			Button ok = new Button("OK");
			Label choixlab = new Label("Choisissez les valeurs de X :");
			TextField Xmin = new TextField();
			TextField Xmax = new TextField();
			Xmin.setMaxWidth(50);
			Xmax.setMaxWidth(50);
			BorderPane bp = new BorderPane();
			HBox hbox = new HBox();
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(10);
			hbox.getChildren().addAll(Xmin,Xmax);
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			vbox.setSpacing(10);
			vbox.getChildren().addAll(choixlab,hbox,ok);
			bp.setCenter(vbox);
			Stage choixVal = new Stage();
			Scene sceneVal = new Scene(bp);
			choixVal.setScene(sceneVal);
			choixVal.setHeight(200);
			choixVal.setWidth(500);
			choixVal.setTitle("Choix du nom Fichier");
			choixVal.initModality(Modality.APPLICATION_MODAL);
		    choixVal.show();

		    ok.setOnAction(el->{
		    	d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				System.out.println(choix);

				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						control.doZoom(courbe, vueF, listCourbe, listTitle, tabPane, listT,Integer.parseInt(Xmin.getText()),Integer.parseInt(Xmax.getText()) );
					}
					System.out.println(choixT);
				}
		    	
				choixVal.close();
			});

		});

		//Evenement d'ajout de transformations
		bAjoutT.setOnAction(e -> {
			switch(choixT) {
			case "Logarithme Yt1":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				System.out.println(choix);

				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						control.doLog(courbe, vueF, listCourbe, listTitle, tabPane, listT);
					}
					System.out.println(choixT);
				}

				break;
			case "BoxCox BC":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						control.doBoxCox(courbe, vueF, listCourbe, listTitle, tabPane, listT);
					}
					System.out.println(choixT);
				}
				System.out.println(choixT);
				lambda = model.getLambda();
				lLambda.setText("Lambda : "+lambda);
				System.out.println("Lambda :"+lambda);

				break;
			case "Logistique Yt2":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						Courbe<Number,Number> courbeN=courbe;
						model.logistique(courbeN, 0);
						listCourbe.add(courbeN);
						listTitle.add("Logistique");
						courbeN.setName("Logistique");
						vueF = new CourbeVueConcret<Number,Number>(model,control,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane, listT,null);
						control.addView(vueF);
						vueF.addSeries(courbeN, "Yt2");
						vueF.setTitle("Logistique");
						//vueF.show();

					}
					System.out.println(choixT);
				}
				System.out.println(choixT);
				break;
			case "Moyenne Mobile (Mt)":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						control.doMM(courbe, vueF, listCourbe, listTitle, tabPane, listT);
					}
					System.out.println(choixT);
				}

				System.out.println(choixT);
				ordre = model.getOrdre();
				lOrdre.setText("Ordre : "+ordre);
				System.out.println("Ordre :"+ordre);
				break;
			case "Xt-Mt":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						control.doSaisonResidu(courbe, vueF, listCourbe, listTitle, tabPane, listT);
					}
					System.out.println(choixT);
				}
				System.out.println(choixT);
				break;
			case "St : saison":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						control.doSaison(courbe, vueF, listCourbe, listTitle, tabPane, listT);
					}
					System.out.println(choixT);
				}
				System.out.println(choixT);
				break;
			case "Xt-St desaisonnalisation":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						control.doDesaisonaliser(courbe, vueF, listCourbe, listTitle, tabPane, listT);
					}
					System.out.println(choixT);
				}
				System.out.println(choixT);
				break;
			}
		});

		//Evenement d'ajout d'analyses
		bAjoutA.setOnAction(e -> {
			switch(choixA) {
			case "Graphe des résidus":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						control.doResidu(courbe, vueF, listCourbe, listTitle, tabPane, listT);
					}
					System.out.println(choixT);
				}
				System.out.println(choixA);
				break;
			case "Variance résiduelle":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix == null)
					break;
				System.out.println(choixA);
				lambda = InputDialogs.saisieLambda();
				lLambda.setText("Lambda : "+lambda);
				System.out.println("Lambda :"+lambda);
				break;
			case "Autocorrélation des résidus":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix == null)
					break;
				System.out.println(choixA);
				break;
			}
		});

		//Evenement d'ajout de previsions
		bAjoutP.setOnAction(e -> {
			switch(choixP) {
			case "Lissage exponentiel simple":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						control.doLissageExp1(courbe, vueF, listCourbe, listTitle, tabPane, listT);
					}
					System.out.println(choixT);
				}
				System.out.println(choixP);
				break;
			case "Lissage exponentiel double":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix.isEmpty()) {
					System.out.println("choix est vide");
				}
				else {
					System.out.println("choix n'est pas vide");
					for(Courbe<Number,Number> courbe : choix) {
						control.doLissageExp2(courbe, vueF, listCourbe, listTitle, tabPane, listT);
					}
					System.out.println(choixT);
				}

				break;
			case "Holt-Winters":
				d = new DialogChoixCourbes(listCourbe);
				choix = d.getCourbesChoisies();
				if(choix == null)
					break;
				System.out.println(choixP);
				break;
			}
		});

		//reaffiche les onglets supprimés WIP
		reafficher.setOnAction(e -> {
			tabPane.getTabs().clear();
			for(Tab t : listT) {
				tabPane.getTabs().add(t);
			}
		});

		//supprime tous les onglets et les courbes
		reset.setOnAction(e -> {
			tabPane.getTabs().clear();
			listCourbe.clear();
			lineChart.getData().clear();
			tabPane.getTabs().add(tab);
		});


		//css
		vanilla.setOnAction(e -> {
			scene.getStylesheets().clear();
			System.out.println("vanilla !");
		});
		//css
		metroD.setOnAction(e -> {
			// apply stylesheet to the scene graph
			scene.getStylesheets().clear();
			scene.getStylesheets().add("styles/JMetroDarkTheme.css");
			System.out.println("metroDark !");
		});
		//css
		metroL.setOnAction(e -> {
			// apply stylesheet to the scene graph
			scene.getStylesheets().clear();
		//	scene.getStylesheets().add(styleMetroL);
			System.out.println("metrolight !");
		});
		//css
		brume.setOnAction(e -> {
			// apply stylesheet to the scene graph
			scene.getStylesheets().clear();
		//	scene.getStylesheets().add(styleBrume);
			System.out.println("brume !");
		});

		//aide en ligne
		aide.setOnAction(e -> {
				try {

					getHostServices().showDocument("https://sites.google.com/view/projetffrt/"); //il suffit de changer l'url
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

		});

		//Evenement du chargement de csv en local
		chargerCSV.setOnAction(e -> {
			String chemin = "";

			try {
				chemin = SelectFileChooser.showSingleFileChooser();
				//System.out.println(chemin+"");
			}
			catch(Exception ex) {
				System.out.println(ex);
				System.out.println("Test exception 1");
			}
			System.out.println("Chemin du fichier : "+chemin);
			if(chemin != null) {
				lireFichier(chemin, listTitle, tabPane, listCourbe, listc);
			}

		});

		//Evenement du chargement de csv par internet
		chargerCSVInternet.setOnAction(e -> {
			DialogTelechargement d = new DialogTelechargement();
			try {
				lireFichier(d.getSaveFilePath(), listTitle, tabPane, listCourbe, listc);
			} catch (Exception ex) {
				System.out.println(ex);
				System.out.println("Erreur de chemin ou annulation");
			}


		});

		saveCourbes.setOnAction( e -> {
			String chemin = "";
			try {
				chemin = SelectFileChooser.showDirChooser();
				System.out.println(chemin);
				sauvegarderCourbes(listCourbe, listTitle, save, chemin, fichier_result, donnee);
			} catch(Exception ex) {
				System.out.println(ex);
				System.out.println("Erreur de chemin ou annulation");
			}


		});

		exit.setOnAction(e ->{
			primaryStage.close();
		});

		apropos.setOnAction(e ->{
			BorderPane bp = new BorderPane();

			Label lInfo = new Label("Projet du DUT Informatique\n	Membre du groupe :\n        - Barbet Florian\n        - Mastalerz Thomas\n        - Haddad Rayan\n        - Hirson Florian");
			bp.setCenter(lInfo);

			Scene sInfo = new Scene(bp);
			Stage info = new Stage();
			info.setScene(sInfo);
			info.setHeight(500.0);
			info.setWidth(500.0);
			info.setTitle("A propos");
			info.show();
		});


		tab.setText("Origin");
		listT.add(tab);
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Abcisse");
		yAxis.setLabel("Ordonnee");

		lineChart = new LineChart(xAxis,yAxis);

		tab.setContent(lineChart);
		tabPane.getTabs().add(tab);

		primaryStage.setScene(scene);
		primaryStage.setHeight(700);
		primaryStage.setWidth(1000);
		primaryStage.show();

	}

	public void lireFichier(String chemin, ArrayList<String> listTitle, TabPane tabPane, ArrayList<Courbe<Number, Number>> listCourbe, ArrayList<Courbe<Number, Number>> listc) {
		String name = "";
		String chaine = "";
		BufferedReader fichier_source = null;
		ArrayList<String[]> tabChaine =null;
		ArrayList<String[]> tabCh = new ArrayList<String[]>();
		Courbe<Number,Number> c = new Courbe<Number,Number>();
		int indice = 0;
		int i,j = 0;
		Double x,y;
		try {
			System.out.println(chemin);
			fichier_source = new BufferedReader(new FileReader(chemin));
			//System.out.println(fichier_source);
		} catch (FileNotFoundException e1) {
			SelectFileChooser.error(e1);
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			 tabChaine = new ArrayList<String[]>();
			while((chaine = fichier_source.readLine())!= null)
			{
				System.out.println(chaine+"");

				tabChaine.add(chaine.split(";"));
				indice++;
			}
		} catch (IOException e1) {
			SelectFileChooser.error(e1);
			e1.printStackTrace();
		}finally{
			try {
				fichier_source.close();
			} catch (IOException e1) {
				SelectFileChooser.error(e1);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


		for( i = 0; i < indice ; i++)
			for( j = 0; j < tabChaine.get(i).length ; j++ )
			{
				System.out.println(tabChaine.get(i)[j]+"---------");
				tabCh.add(tabChaine.get(i)[j].split(","));
			}

		try {
			for(i = 0; i < indice ; i++)
			{
				x = Double.parseDouble(tabCh.get(i)[0]);
				y = Double.parseDouble(tabCh.get(i)[1]);
				c.addXY(x,y);
				System.out.println("("+x+";"+y+")");
			}
		}
		catch (Exception e2) {
			System.out.println(e2);
		}

		model=CourbeModel.getInstance();
		if(model.getIndexbyName("Base")==-1){
			listTitle.add("Base");
			c.setName("Base");
			name = "Base";
		}else{
			for(i=0; i < 50; i++){
				if(model.getIndexbyName("Base"+i)==-1){
					listTitle.add("Base"+i);
					c.setName("Base"+i);
					name = "Base" + i;
					i=50;
				}
			}
		}
		if(!model.isSetIndex()){
			model.setCourbes(listc);
		}
		model.addCourbe(c);
		model.setIndex(model.getIndexbyName(c.getName()));
		control = new CourbeController<Number,Number>(model);
		System.out.println("============"+model.getCourbe(model.getIndexUse()).getName()+"=================>"+c.getName()+" : "+model.getIndexbyName(c.getName()));

		addSerie(name,c);


		listCourbe.add(c);

	}

	public void sauvegarderCourbes(ArrayList<Courbe<Number, Number>> listCourbe, ArrayList<String> listTitle, String save, String chemin, BufferedWriter fichier_result, Courbe<Number, Number> donnee) {
		String crw = "";

		//for(int a = 0; a < vueF.getLC().getData().size();a++)System.out.println(" lc : "+vueF.getLC().getData().get(a));
		for(int i = 0 ; i < listCourbe.size();i++){

			try{
				String title = listTitle.get(i);
				FileWriter fileWriter = new FileWriter(chemin+"/"+title+".csv");

				fileWriter.append(title);
				save = title+", Ordre : , "+model.getOrdre()+", Lambda : "+model.getLambda()+"\n X , Y \n";
				fileWriter.close();
				fichier_result = new BufferedWriter(new FileWriter(chemin+"/"+title+".csv"));


				donnee = listCourbe.get(i);
				for(int j=0;j<donnee.sizeOfData();j++)
					save += donnee.getX(j)+","+donnee.getY(j)+"\n";


				fichier_result.write(save);
				fichier_result.close();
				BufferedWriter fcr = new BufferedWriter(new FileWriter(chemin+"/Save.csv"));
				crw+=save;
				fcr.write(crw);
				fcr.close();
			}catch(Exception e){
				System.out.println("Erreur : "+e.getMessage());
			}



		}
	}

	@SuppressWarnings({"unchecked", "rawtypes" })
	public void addSerie(String t, Courbe<Number,Number> c) {
		XYChart.Series series1 = new XYChart.Series();
        series1.setName(t);

		// remplir la serie de donnees
		for(int i = 0; i < c.sizeOfData()  ; i++)
		{
			series1.getData().add(new XYChart.Data(c.getX(i), c.getY(i)));
			System.out.println("x : "+c.getX(i)+", y : "+c.getY(i));
		}
		lineChart.getData().add(series1);

	}

}
