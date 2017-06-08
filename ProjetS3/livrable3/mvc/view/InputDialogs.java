package mvc.view;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;
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
