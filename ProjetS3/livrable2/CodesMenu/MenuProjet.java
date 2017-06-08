package CodesMenu;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.control.CourbeController;
import mvc.model.Courbe;
import mvc.model.CourbeModel;
import mvc.view.CourbeVue;
import mvc.view.CourbeVueConcret;
import mvc.view.DialogChoixCourbes;
import mvc.view.InputDialogs;
import mvc.view.SelectFileChooser;

public class MenuProjet extends Application{
	static String choixT = "";
	static String choixA = "";
	static String choixP = "";


	static double lambda = 0;
	static int ordre = 0;

	ArrayList<Courbe<Number, Number>> choix = new ArrayList<Courbe<Number, Number>>(); //Liste de courbes choisies par l'utilisateur
	CourbeVue<Number,Number> vueF = null;	                // en preparation pour Livrable 2
	@SuppressWarnings("rawtypes")
	static private TableView valCsv = new TableView();
	@SuppressWarnings("rawtypes")
	static private TableView valModif = new TableView();

	// load the stylesheets
	/*String styleMetroD = getClass().getResource("/styles/JMetroDarkTheme.css").toExternalForm();
	String styleMetroL = getClass().getResource("/styles/JMetroLightTheme.css").toExternalForm();
	String styleBrume = getClass().getResource("/styles/brume.css").toExternalForm();*/

	CourbeModel<Number,Number> model; 				//	Modele MVC
	CourbeVue<Number,Number> vue;	                // en preparation pour Livrable 2
	CourbeController<Number,Number> control;        // structure OK

	DialogChoixCourbes d;

	public static void main(String[] args){
		//System.setProperty("http.proxyPort", "3128");
		//System.setProperty("http.proxyHost", "proxy.univ-lille1.fr");
		launch(args);
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Override

	public void start(Stage primaryStage) throws Exception {

		String data = "";
		ArrayList<Courbe<Number,Number>> listc = new ArrayList<Courbe<Number,Number>>();

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

		/**
		 * Tableaux d'aperçu
		 */
		Label CsvLab = new Label("Work In Progress");

		Label ModifLab = new Label("Work In Progress");

		VBox valCsvLabel = new VBox();
		VBox.setMargin(valCsvLabel, new Insets(200,0,0,0));
		valCsvLabel.getChildren().addAll(CsvLab,valCsv);

		VBox valModifLabel = new VBox();
		VBox.setMargin(valModifLabel, new Insets(200,0,0,0));
		valModifLabel.getChildren().addAll(ModifLab,valModif);

		HBox table = new HBox();
		HBox.setMargin(table, new Insets(200,0,0,0));
		table.getChildren().addAll(valCsvLabel,valModifLabel);
		table.setSpacing(200.0);

		TableColumn ColX = new TableColumn("X");
		TableColumn ColY = new TableColumn("Y");
		ColX.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
		ColY.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
		valCsv.getColumns().addAll(ColX, ColY);
		valCsv.setMaxSize(200.0, 200.0);

		TableColumn ColXmodif = new TableColumn("X");
		TableColumn ColYmodif = new TableColumn("Y");
		ColXmodif.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
		ColYmodif.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
		valModif.getColumns().addAll(ColXmodif, ColYmodif);
		valModif.setMaxSize(200.0, 200.0);

		AnchorPane ap = new AnchorPane();
		ap.getChildren().add(table);
		AnchorPane.setBottomAnchor(table, 20.0);
		AnchorPane.setLeftAnchor(table, 150.0);

		//Vbox contenant les choicebox d'ajout de transformation/analyse/prevision
		VBox ajout = new VBox();
		ajout.setSpacing(10);
		ajout.setPadding(new Insets(10, 10, 10, 10));

		TabPane tabPane = new TabPane(); //Tabpane contenant les onglets de linecharts

		root.setTop(menuBar);
		root.setLeft(ajout);
		root.setCenter(tabPane);
		root.setBottom(ap);

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

		ajoutT.getChildren().addAll(cAjoutT,bAjoutT);
		ajoutA.getChildren().addAll(cAjoutA,bAjoutA);
		ajoutP.getChildren().addAll(cAjoutP,bAjoutP);

		ajout.getChildren().addAll(lAjouT,ajoutT,lAjouA,ajoutA,lAjouP,ajoutP);

		Menu menuF = new Menu("File");
		Menu menuH = new Menu("Aide");
		Menu menuS = new Menu("Styles");

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

		menuBar.getMenus().addAll(menuF,menuS,menuH);
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

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
						control.doLog(courbe, vueF, listCourbe, listTitle, tabPane);
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
						control.doBoxCox(courbe, vueF, listCourbe, listTitle, tabPane);
					}
					System.out.println(choixT);
				}
				System.out.println(choixT);
				lambda = model.getLambda();
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
						Courbe<Number,Number> courbeN=new Courbe<Number,Number>();
						model.logistique(courbeN, 0);
						listCourbe.add(courbeN);
						listTitle.add("Logistique");
						courbeN.setName("Logistique");
						vueF = new CourbeVueConcret<Number,Number>(model,control,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
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
						control.doMM(courbe, vueF, listCourbe, listTitle, tabPane);
					}
					System.out.println(choixT);
				}

				System.out.println(choixT);
				ordre = model.getOrdre();
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
						control.doSaisonResidu(courbe, vueF, listCourbe, listTitle, tabPane);
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
						control.doSaison(courbe, vueF, listCourbe, listTitle, tabPane);
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
						control.doDesaisonaliser(courbe, vueF, listCourbe, listTitle, tabPane);
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
						control.doResidu(courbe, vueF, listCourbe, listTitle, tabPane);
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
						control.doLissageExp1(courbe, vueF, listCourbe, listTitle, tabPane);
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
						control.doLissageExp2(courbe, vueF, listCourbe, listTitle, tabPane);
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

		//css
		vanilla.setOnAction(e -> {
			scene.getStylesheets().clear();
			System.out.println("vanilla !");
		});
		//css
		metroD.setOnAction(e -> {
			// apply stylesheet to the scene graph
			scene.getStylesheets().clear();
			//scene.getStylesheets().add(styleMetroD);
			System.out.println("metroDark !");
		});
		//css
		metroL.setOnAction(e -> {
			// apply stylesheet to the scene graph
			scene.getStylesheets().clear();
			//scene.getStylesheets().add(styleMetroL);
			System.out.println("metrolight !");
		});
		//css
		brume.setOnAction(e -> {
			// apply stylesheet to the scene graph
			scene.getStylesheets().clear();
			//scene.getStylesheets().add(styleBrume);
			System.out.println("brume !");
		});

		//aide en ligne
		aide.setOnAction(e -> {
				try {

					getHostServices().showDocument("http://www.hirsonf.fr"); //il suffit de changer l'url
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

		});

		//Evenement du chargement de csv en local
		chargerCSV.setOnAction(e -> {
			String chemin = "";
			String chaine = "";
			BufferedReader fichier_source = null;
			ArrayList<String[]> tabChaine =null;
			ArrayList<String[]> tabCh = new ArrayList<String[]>();
			Courbe<Number,Number> c = new Courbe<Number,Number>();

			int indice = 0;
			int i,j = 0;
			Double x,y;

			try {
				chemin = SelectFileChooser.showSingleFileChooser();
				//System.out.println(chemin+"");
			}
			catch(Exception ex) {
				System.out.println(ex);
				System.out.println("Test exception 1");
			}



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
			}else{
				for(i=0; i < 50; i++){
					if(model.getIndexbyName("Base"+i)==-1){
						listTitle.add("Base"+i);
						c.setName("Base"+i);
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
			vue = new CourbeVueConcret<Number,Number>(model,control,new NumberAxis(),new NumberAxis(),c.getName(),tabPane);
			control.addView(vue);

			listCourbe.add(c);
		});

		//Evenement du chargement de csv par internet
		chargerCSVInternet.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog("Download");
			dialog.setTitle("Téléchargement d'un CSV par internet");
			dialog.setContentText("Veuillez entrer l'url : ");
			Optional<String> result = dialog.showAndWait();
			try {
				result.ifPresent(url -> {
					String fileName = url.substring(url.lastIndexOf('/') + 1);
					SelectFileChooser.csvDownload(url, "livrable2/data/"+fileName);
					System.out.println("Success !");
				});
			}
			catch (Exception e1) {
				System.out.println(e1);
			}


		});

		saveCourbes.setOnAction( e -> {
			String chemin = "";
			chemin = SelectFileChooser.showDirChooser();
			System.out.println(chemin);
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

		Tab tab = new Tab();
		tab.setText("Tab 1");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Month");
		final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);

		tab.setContent(lineChart);
		tabPane.getTabs().add(tab);

		primaryStage.setScene(scene);
		primaryStage.setHeight(700);
		primaryStage.setWidth(1000);
		primaryStage.show();

	}

}
