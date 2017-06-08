package mvc.view;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mvc.model.HttpDownloadUtility;

/**
 * Classe pour afficher la fenetre de telechargement d'un fichier
 * @author Florian Hirson
 *
 */
public class DialogTelechargement extends Stage{
	private String sChemin = "";
	private String sUrl = "";
	private static String saveFilePath;

	public DialogTelechargement() {
		super();
		Stage stage = new Stage();
		BorderPane root = new BorderPane();
	    Scene scene = new Scene(root, 450, 150, Color.WHITE);
	    stage.setTitle("Telechargement de fichier CSV");
	    stage.setResizable(false);
	    stage.setScene(scene);

	    Label location = new Label("Location du fichier\nsur l'ordinateur : ");
	    Label url = new Label("Lien du fichier : ");
	    TextField tLocation = new TextField("C:");
	    TextField tUrl = new TextField("http://google.com");

	    Button ouvrir = new Button("Ouvrir");
	    Button ok = new Button("OK");
	    Button annuler = new Button("Annuler");

	    HBox internet = new HBox(10);
	    HBox local = new HBox(10);
	    HBox boutons = new HBox(10);

	    internet.setPadding(new Insets(10, 10, 10, 10));
	    local.setPadding(new Insets(10, 10, 10, 10));
	    boutons.setPadding(new Insets(10, 10, 10, 10));

	    internet.getChildren().addAll(url,tUrl);
	    local.getChildren().addAll(location,tLocation,ouvrir);
	    boutons.getChildren().addAll(ok,annuler);

	    root.setCenter(local);
	    root.setTop(internet);
	    root.setBottom(boutons);


	    tUrl.textProperty().addListener((observable, oldValue, newValue) -> {
	        System.out.println("tUrl Changed (newValue: " + newValue + ")");
	        sUrl = newValue;
	    });

	    tLocation.textProperty().addListener((observable, oldValue, newValue) -> {
	        System.out.println("tLocation Changed (newValue: " + newValue + ")");
	        sChemin = newValue;
	    });

	    ouvrir.setOnAction(e -> {
	    	sChemin = SelectFileChooser.showDirChooser();
	    	tLocation.setText(sChemin);
	    });
	    annuler.setOnAction(e -> {
			stage.close();
		});

	    ok.setOnAction(e -> {
	    	try {
				HttpDownloadUtility.downloadFile(sUrl, sChemin);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	saveFilePath=HttpDownloadUtility.getFilePath();
	    	stage.close();
	    });

	    stage.showAndWait();


	}

	/**
	 * @return the saveFilePath
	 */
	public static String getSaveFilePath() {
		return saveFilePath;
	}

	/**
	 * @param saveFilePath the saveFilePath to set
	 */
	public static void setSaveFilePath(String saveFilePath) {
		DialogTelechargement.saveFilePath = saveFilePath;
	}


}
