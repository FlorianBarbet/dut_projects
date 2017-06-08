package mvc.view;

import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mvc.control.CourbeController;
import mvc.model.Courbe;
import mvc.model.CourbeModel;

public class CourbeVueConcret<X, Y> extends CourbeVue<X,Y> {



	
	public CourbeVueConcret(CourbeModel<X, Y> mod, CourbeController<X, Y> cont, NumberAxis xAx, NumberAxis yAx, String t,TabPane tabPane, ArrayList<Tab> listT, Courbe<Number, Number> courbe) {
		super(mod, cont, xAx, yAx, t, tabPane, listT,courbe);

	}



	@Override
	public void update(Observable o, Object arg) {
		for(int i=0; i < model().getNbCourbe();i++){
			setDisplay(model().getCourbe(i));
		}
	}

}
