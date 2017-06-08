package mvc.view;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.control.CourbeController;
import mvc.model.Courbe;
import mvc.model.CourbeModel;

public abstract class CourbeVue<X,Y> extends Stage implements Observer {
	protected CourbeModel<X,Y> model ;
	protected CourbeController<X,Y> controller ;
	protected final NumberAxis xAxis;
	protected final NumberAxis yAxis;
	protected final LineChart<Number,Number> lineChart;
	@SuppressWarnings("rawtypes")
	protected XYChart.Series series = new XYChart.Series();
	public DialogCouleurCourbe couleurCourbe;
	@SuppressWarnings({"unchecked", "rawtypes" })
	public CourbeVue(CourbeModel<X,Y> mod, CourbeController<X,Y> cont,NumberAxis xAx,NumberAxis yAx,String t,TabPane tabPane, ArrayList<Tab> listT,Courbe<Number, Number> courbe){
		super();
		super.setTitle("Projet Modelisation");
		ArrayList<XYChart.Series> l = new ArrayList();
		Tab tab = new Tab();
		model = mod;
		controller = cont;
		xAxis=xAx;
		yAxis=yAx;

		xAxis.setAutoRanging(true);
		xAxis.setForceZeroInRange(false);
		yAxis.setAutoRanging(true);
		yAxis.setForceZeroInRange(false);

		xAxis.setLabel("Abcisse");
		yAxis.setLabel("Ordonnee");
		lineChart = new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle(t+"");

		//definition de la serie
		series.setName(t);

		// remplir la serie de donnees de la transformation
		for(int i = 0; i < model.sizeOfCourbe()  ; i++)
		{
			series.getData().add(new XYChart.Data(model.getDataX(i), model.getDataY(i)));
		}
		l.add(series);

		final XYChart.Series seriesO = new XYChart.Series();
		seriesO.setName(courbe.getName());

		// remplir la serie de donnees de la transformation
		for(int i = 0; i < courbe.sizeOfData()  ; i++)
		{
			seriesO.getData().add(new XYChart.Data(courbe.getX(i), courbe.getY(i)));
		}
		l.add(seriesO);

		Button couleur = new Button("Couleurs");
		final VBox root = new VBox(10);
		final StackPane pane = new StackPane();
		root.getChildren().addAll(couleur,pane);


		model.addObserver(this);


		pane.getChildren().add(lineChart);
		@SuppressWarnings("unused")
		ZoomManager zoom = new ZoomManager(pane, lineChart, l);

		tab.setText(t);
		tab.setContent(root);
        tabPane.getTabs().add(tab);
        listT.add(tab);

		couleur.setOnAction(e -> {
			couleurCourbe = new DialogCouleurCourbe(lineChart);
		});






	}











	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addSeries( CourbeModel<X,Y> c, String title){
		XYChart.Series nSeries = new XYChart.Series();
		nSeries.setName(title);
		for(int i = 0; i < c.sizeOfCourbe();i++){
			nSeries.getData().add(new XYChart.Data(c.getDataX(i), c.getDataY(i)));
		}


	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addSeries( Courbe<X,Y> c, String title){
		XYChart.Series nSeries = new XYChart.Series();

		nSeries.setName(title);

		for(int i = 0; i < c.sizeOfData();i++){
			nSeries.getData().add(new XYChart.Data(c.getX(i), c.getY(i)));
		}
		lineChart.getData().add(nSeries);

	}




	public void setColorSeries( Courbe<X,Y> c,int nbCourbe, String color){
		this.show();
		String backgroundStyle = "-fx-background-color: "+color+",white";
		String strokeStyle = "-fx-stroke:"+ color;
		final ObservableList<Series<Number,Number>> chart = lineChart.getData();
		final Series<Number, Number> series1;
		final Set<Node> nodes;
		if(chart.size()!=0 ){
			 series1 = chart.get(nbCourbe);

		}else{
			series1 = null;
		}

		for (final Data<Number, Number> data : series1.getData()) {
			data.getNode().setStyle(backgroundStyle);
		}

		series1.getNode().setStyle(strokeStyle);
		nodes = lineChart.lookupAll(".chart-legend-item-symbol.default-color" + nbCourbe);
		for (final Node n : nodes) {
			n.setStyle(backgroundStyle);
		}

		this.close();
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  void  setDisplay(Courbe<X,Y> c) {
		for(int i=0;i<c.sizeOfData();i++){
			series.getData().add(new XYChart.Data(c.getX(i),c.getY(i)));
		}
	}

	protected CourbeModel<X,Y> model (){
		return model ;
	}

}
