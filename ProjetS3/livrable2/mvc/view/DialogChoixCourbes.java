package mvc.view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mvc.model.Courbe;

public class DialogChoixCourbes extends Stage{
	private ArrayList<Courbe<Number,Number>> courbesChoisies;

	public ObservableList<Courbe<Number,Number>> candidates;
    public ListView<Courbe<Number,Number>> candidatesListView;

    public ObservableList<Courbe<Number,Number>> selected;
    public ListView<Courbe<Number,Number>> heroListView;

	public DialogChoixCourbes(ArrayList<Courbe<Number,Number>> listCourbe) {
		super();
		courbesChoisies = new ArrayList<Courbe<Number,Number>>();

		Stage stage = new Stage();
		BorderPane root = new BorderPane();
	    Scene scene = new Scene(root, 400, 400, Color.WHITE);
	    stage.setResizable(false);

	    GridPane gridpane = new GridPane();

	    gridpane.setPadding(new Insets(5));
	    gridpane.setHgap(10);
	    gridpane.setVgap(10);
	    ColumnConstraints column1 = new ColumnConstraints(150, 150,
	        Double.MAX_VALUE);
	    ColumnConstraints column2 = new ColumnConstraints(50);
	    ColumnConstraints column3 = new ColumnConstraints(150, 150,
	        Double.MAX_VALUE);
	    column1.setHgrow(Priority.ALWAYS);
	    column3.setHgrow(Priority.ALWAYS);
	    gridpane.getColumnConstraints().addAll(column1, column2, column3);

	    Label candidatesLbl = new Label("Candidates");
	    GridPane.setHalignment(candidatesLbl, HPos.CENTER);
	    gridpane.add(candidatesLbl, 0, 0);

	    Label selectedLbl = new Label("selected");
	    gridpane.add(selectedLbl, 2, 0);
	    GridPane.setHalignment(selectedLbl, HPos.CENTER);

	    // Candidates
	    candidates = FXCollections.observableArrayList(listCourbe);
	    candidatesListView = new ListView<>(candidates);
	    gridpane.add(candidatesListView, 0, 1);

	    selected = FXCollections.observableArrayList();
	    heroListView = new ListView<>(selected);

	    gridpane.add(heroListView, 2, 1);

	    Button ok = new Button("OK");
	    ok.setOnAction(e -> {
	    	ArrayList<Courbe<Number,Number>> choix = new ArrayList<Courbe<Number,Number>>();
	    	for(Courbe<Number, Number> c : selected) {
	    		choix.add(c);
	    	}
	    	setCourbesChoisies(choix);
	    	stage.close();

	    });
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e -> {
			stage.close();
		});

		HBox b = new HBox();
		b.getChildren().addAll(ok,cancel);

	    gridpane.add(b, 2, 3);

	    Button sendRightButton = new Button(" > ");
	    sendRightButton.setOnAction((ActionEvent event) -> {
	    	Courbe<Number,Number> c = candidatesListView.getSelectionModel()
	          .getSelectedItem();
	      if (c != null) {
	        candidatesListView.getSelectionModel().clearSelection();
	        candidates.remove(c);
	        selected.add(c);
	      }
	    });

	    Button sendLeftButton = new Button(" < ");
	    sendLeftButton.setOnAction((ActionEvent event) -> {
	    	Courbe<Number,Number> c = heroListView.getSelectionModel().getSelectedItem();
	      if (c != null) {
	        heroListView.getSelectionModel().clearSelection();
	        selected.remove(c);
	        candidates.add(c);
	      }
	    });
	    VBox vbox = new VBox(5);
	    vbox.getChildren().addAll(sendRightButton, sendLeftButton);

	    gridpane.add(vbox, 1, 1);
	    root.setCenter(gridpane);

	    GridPane.setVgrow(root, Priority.ALWAYS);
	    stage.setScene(scene);
	    stage.showAndWait();
	}
	/**
	 * @return the courbesChoisies
	 */
	public ArrayList<Courbe<Number,Number>> getCourbesChoisies() {
		return courbesChoisies;
	}
	/**
	 * @param courbesChoisies the courbesChoisies to set
	 */
	public void setCourbesChoisies(ArrayList<Courbe<Number,Number>> courbesChoisies) {
		this.courbesChoisies = courbesChoisies;
	}

}
