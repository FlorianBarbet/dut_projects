package mvc.view;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class SelectFileChooser {

	public static String showSingleFileChooser() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("livrable3/data/"));
		fileChooser.setTitle("Charger un fichier CSV");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if(selectedFile == null) {
			return null;
		}
		return selectedFile.getAbsolutePath();
	}

	public static String showDirChooser() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

		return selectedDirectory.getAbsolutePath();
	}



	public static void error(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fenetre erreur");
		alert.setHeaderText("Erreur de chargement du fichier CSV");
		alert.setContentText(e.getLocalizedMessage());



		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("La trace de la pile d'exception était :");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();

	}


}