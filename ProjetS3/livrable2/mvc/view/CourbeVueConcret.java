package mvc.view;

import java.util.Observable;

import javafx.scene.chart.Axis;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mvc.control.CourbeController;
import mvc.model.CourbeModel;

public class CourbeVueConcret<X, Y> extends CourbeVue<X,Y> {



	@SuppressWarnings("unchecked")
	public CourbeVueConcret(CourbeModel<X, Y> mod, CourbeController<X, Y> cont, Axis<X> xAx, Axis<Y> yAx, String t,TabPane tabPane) {
		super(mod, cont, xAx, yAx, t, tabPane);
		lineChart.getData().add(series);

	}



	@Override
	public void update(Observable o, Object arg) {
		for(int i=0; i < model().getNbCourbe();i++){
			setDisplay(model().getCourbe(i));
		}
	}

}
