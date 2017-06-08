package mvc.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

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
		fileChooser.setInitialDirectory(new File("livrable2/data/"));
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
		directoryChooser.setInitialDirectory(new File(""));
        File selectedDirectory = directoryChooser.showDialog(null);

		return selectedDirectory.getAbsolutePath();
	}

	public static void csvDownload(String urlStr, String file)  {
		//System.setProperty("http.proxyPort", "3128");
		//System.setProperty("http.proxyHost", "proxy.univ-lille1.fr");
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			error(e);
			// TODO Auto-generated catch block
			System.out.println(e);
		}
        ReadableByteChannel rbc = null;
		try {
			rbc = Channels.newChannel(url.openStream());
		} catch (IOException e) {
			error(e);
			// TODO Auto-generated catch block
			System.out.println(e);
		}
        FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			error(e);
			// TODO Auto-generated catch block
			System.out.println(e);
		}
        try {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e) {
			error(e);
			// TODO Auto-generated catch block
			System.out.println(e);
		}
        try {
			fos.close();
		} catch (IOException e) {
			error(e);
			// TODO Auto-generated catch block
			System.out.println(e);
		}
        try {
			rbc.close();
		} catch (IOException e) {
			error(e);
			// TODO Auto-generated catch block
			System.out.println(e);
		}
        System.out.println("Success !");

	}

	public static void error(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception Dialog");
		alert.setHeaderText("Look, an Exception Dialog");
		alert.setContentText(e.getLocalizedMessage());



		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

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