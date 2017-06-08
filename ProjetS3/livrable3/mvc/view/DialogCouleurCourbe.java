package mvc.view;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Classe pour gérer afficher la fenetre de changement de couleurs des courbes du graphe
 * @author Florian Hirson
 *
 */
public class DialogCouleurCourbe extends Stage {

	private int numCourbe;


	public DialogCouleurCourbe(LineChart<Number, Number> lineChart) {
		super();
		numCourbe = 0;

		Stage stage = new Stage();
		VBox root = new VBox(10);
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);
		HBox h = new HBox(10);
		Button ok = new Button("Ok");
		Button annuler = new Button("Annuler");

		h.getChildren().addAll(ok, annuler);
		ColorPicker couleur = new ColorPicker(Color.RED);
		ListView<Series<Number, Number>> list = new ListView<>(lineChart.getData());
		root.getChildren().addAll(couleur,list,h);


		list.setOnMouseClicked(e -> {
			numCourbe = list.getSelectionModel().getSelectedIndex();
			System.out.println("NumCourbe : "+ numCourbe++);
		});

		couleur.setOnAction(e -> {
			String hex = "#" + Integer.toHexString(couleur.getValue().hashCode());
			setColor(numCourbe, hex, lineChart);
		});

		ok.setOnAction(e-> {

			stage.close();
		});

		annuler.setOnAction(e -> {
			lineChart.setStyle(null);
			stage.close();
		});
		stage.showAndWait();

	}

	/**
     *Change la couleur de la serie en parametre
     * @param nbCourbe Indice de la courbe
     * @param color Couleur a appliquer sur la courbe
     * @param lineChart Graphe de la courbe
     */
	public void setColor( int nbCourbe, String color, LineChart<Number, Number> lineChart){
		lineChart.setStyle("CHART_COLOR_"+ nbCourbe+": "+color+";");
	}



}
