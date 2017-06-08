package mvc.view;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import mvc.model.Courbe;

public class InputDialogs {
	static Optional<ObservableList<Courbe<Number, Number>>> result=null;
	static ObservableList<Courbe<Number,Number>> retour = null;

	public static double saisieLambda() {
		double lambda = 0;
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setHeaderText(null);
		dialog.setTitle("Saisie de lambda");
		dialog.setContentText("Veuillez entrer lambda : ");
		Optional<String> res = dialog.showAndWait();

		try {
			lambda = Double.parseDouble(res.get());
			return lambda;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int saisieOrdre() {
		int ordre = 0;
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setHeaderText(null);

		dialog.setTitle("Saisie de l'ordre pour la moyenne mobile");
		dialog.setContentText("Veuillez entrer l'ordre : ");
		Optional<String> res = dialog.showAndWait();

		try {
			ordre = Integer.parseInt(res.get());
			return ordre;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public static double saisieBeta() {
		double beta = 0;
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setHeaderText(null);

		dialog.setTitle("Saisie de Beta pour Lissage");
		dialog.setContentText("Veuillez entrer beta entre 0 et 1 : ");
		Optional<String> res = dialog.showAndWait();

		try {
			beta = Double.parseDouble(res.get());
			return beta;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}


}
