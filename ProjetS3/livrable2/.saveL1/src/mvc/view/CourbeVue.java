package mvc.view;

import java.util.Observer;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
//import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import mvc.control.CourbeController;
import mvc.model.Courbe;
import mvc.model.CourbeModel;

public abstract class CourbeVue<X,Y> extends Stage implements Observer {
	protected CourbeModel<X,Y> model ;
	protected CourbeController<X,Y> controller ;
	protected final Axis<X> xAxis;
	protected final Axis<Y> yAxis;
	protected final LineChart<X,Y> lineChart;
	@SuppressWarnings("rawtypes")
	protected XYChart.Series series = new XYChart.Series();
//	protected final ScatterChart<X,Y> sc;

	@SuppressWarnings({"unchecked", "rawtypes" })
	public CourbeVue(CourbeModel<X,Y> mod, CourbeController<X,Y> cont,Axis<X> xAx,Axis<Y> yAx,String t){
		super();
		super.setTitle("Projet Modelisation");
		model = mod;
		controller = cont;
		xAxis=xAx;
		yAxis=yAx;
		xAxis.setLabel("Abcisse");
		yAxis.setLabel("Ordonnee");
		lineChart = new LineChart<X,Y>(xAxis,yAxis);
		lineChart.setTitle(getTitle());
		//definition de la serie
	//	sc = new ScatterChart<X,Y>(xAxis,yAxis);
		//sc.setTitle(getTitle());

		series.setName(t);

		// remplir la serie de donnees
		for(int i = 0; i < model.sizeOfCourbe()  ; i++)
		{
			series.getData().add(new XYChart.Data(model.getDataX(i), model.getDataY(i)));
		}
		lineChart.getData().add(series);
		

		Scene scene  = new Scene(lineChart,800,600);

		model.addObserver(this);

		this.setScene(scene);
	}




	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addSeries( CourbeModel<X,Y> c, String title){
		XYChart.Series nSeries = new XYChart.Series();
		nSeries.setName(title);
		for(int i = 0; i < c.sizeOfCourbe();i++){
			nSeries.getData().add(new XYChart.Data(c.getDataX(i), c.getDataY(i)));
		}
		lineChart.getData().add(nSeries);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addSeries( Courbe<X,Y> c, String title){
		XYChart.Series nSeries = new XYChart.Series();

		nSeries.setName(title);
		;
		for(int i = 0; i < c.sizeOfData();i++){
			nSeries.getData().add(new XYChart.Data(c.getX(i), c.getY(i)));
		}
		lineChart.getData().add(nSeries);
	}




	public void setColorSeries( Courbe<X,Y> c,int nbCourbe, String color){
		this.show();
		String backgroundStyle = "-fx-background-color: "+color+",white";
		String strokeStyle = "-fx-stroke:"+ color;
		final ObservableList<Series<X,Y>> chart = lineChart.getData();
		final Series<X, Y> series1;
		final Set<Node> nodes;

		if(lineChart.getData().size()!=0 && chart.size()>nbCourbe ){
			 series1 = chart.get(nbCourbe);
				for (final Data<X, Y> data : series1.getData()) {
					data.getNode().setStyle(backgroundStyle);
				}

				series1.getNode().setStyle(strokeStyle);
				nodes = lineChart.lookupAll(".chart-legend-item-symbol.default-color" + nbCourbe);
				for (final Node n : nodes) {
					n.setStyle(backgroundStyle);
				}

		}else{
			series1 = null;
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
