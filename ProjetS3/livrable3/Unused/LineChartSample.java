import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class LineChartSample extends Application {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start(Stage stage) throws IOException {
		stage.setTitle("Test d'exportation du fichier csv");
		//defining the axes
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Abcisse");
		yAxis.setLabel("Ordonnee");
		//creating the chart
		final LineChart<Number,Number> lineChart = 
				new LineChart<Number,Number>(xAxis,yAxis);

		lineChart.setTitle("Fonction f(x) 2");

		//defining a series
		XYChart.Series series = new XYChart.Series();
		series.setName(" f(x) = x^2");

		



		try
		{
			String chemin = "Test.csv";
			BufferedReader fichier_source = new BufferedReader(new FileReader(chemin));
			String chaine;

			ArrayList<String[]> tabChaine = new ArrayList<String[]>();
			ArrayList<String[]> tabCh = new ArrayList<String[]>();

			int indice = 0;
			while((chaine = fichier_source.readLine())!= null)
			{


				tabChaine.add(chaine.split(";"));

				indice++;

			}
			for(int i = 0; i < indice ; i++)
				for(int j = 0; j < tabChaine.get(i).length ; j++ )
				{
					tabCh.add(tabChaine.get(i)[j].split(","));
				}
			fichier_source.close();

			for(int i = 0; i < indice ; i++)
			{
				int x = Integer.parseInt(tabCh.get(i)[0]);
				int y = Integer.parseInt(tabCh.get(i)[1]);
				series.getData().add(new XYChart.Data(x, y));

			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier est introuvable !");
		}



		Scene scene  = new Scene(lineChart,800,600);
		lineChart.getData().add(series);

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}