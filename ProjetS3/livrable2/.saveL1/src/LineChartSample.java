import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;


public class LineChartSample extends Application {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start(Stage stage) throws IOException {
		stage.setTitle("Test d'exportation du fichier csv");
		//defining the axes
		int indice = 0;
		final Axis<Number> xAxis = new NumberAxis();
		final Axis<Number> yAxis = new NumberAxis();

		xAxis.setLabel("Abcisse");
		yAxis.setLabel("Ordonnee");
		//creating the chart
		final LineChart<Number,Number> lineChart = 
				new LineChart<Number,Number>(xAxis,yAxis);



		//defining a series
		XYChart.Series series = new XYChart.Series();
		series.setName(" f(x) ");


		//populating the series with data



		try
		{
			String chemin = "Test.csv";
			BufferedReader fichier_source = new BufferedReader(new FileReader(chemin));
			String chaine;

			ArrayList<String[]> tabChaine = new ArrayList<String[]>();
			ArrayList<String[]> tabCh = new ArrayList<String[]>();

			indice = 0;
			while((chaine = fichier_source.readLine())!= null)
			{


				tabChaine.add(chaine.split(";"));

				indice++;

			}
			for(int i = 0 ; i < indice ; i++)
				for(int j = 0; j < tabChaine.get(i).length ; j++ )
				{
					tabCh.add(tabChaine.get(i)[j].split(","));
				}
			fichier_source.close();

			for(int i = 0; i < indice-2 ; i++)
			{
				int x = Integer.parseInt(tabCh.get(i)[0]); // changer en parseDouble
				int y = Integer.parseInt(tabCh.get(i)[1]);
				series.getData().add(new XYChart.Data(x, y));


			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier est introuvable ! : ");
		}



		Scene scene  = new Scene(lineChart,800,600);
		lineChart.getData().add(series);
		String backgroundColorStyle = "-fx-background-color: blue,white";

		/*StringBuilder styleString = new StringBuilder();    
		styleString.append("-fx-stroke: blue; -fx-stroke-width: 2; ");
        styleString.append(
                ".chart-symbol {-fx-background-color: blue; -fx-background-radius: 3px;}");

        lineChart.setCreateSymbols(true);*/



			


		 





		System.out.println(" "+series.getNode()+" \n "+series.getChart()+" \n  "+series.nodeProperty()+" \n  "+series.chartProperty());
		stage.setScene(scene);
		stage.show();
		for( int i = 0;i<indice-2;i++){

			
			final Series<Number, Number> series1 = lineChart.getData().get(0);
			for (final Data<Number, Number> data : series1.getData()) {
				data.getNode().setStyle(backgroundColorStyle);
			}


			series.getNode().setStyle("-fx-stroke: blue;");
			final Set<Node> nodes2 = lineChart.lookupAll(".chart-legend-item-symbol.default-color" + i);
			for (final Node n : nodes2) {
				n.setStyle(backgroundColorStyle);
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}