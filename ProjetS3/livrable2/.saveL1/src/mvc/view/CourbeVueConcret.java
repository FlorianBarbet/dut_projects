package mvc.view;

import java.util.Observable;

import javafx.scene.chart.Axis;
import mvc.control.CourbeController;
import mvc.model.CourbeModel;

public class CourbeVueConcret<X, Y> extends CourbeVue<X,Y> {

	

	

	public CourbeVueConcret(CourbeModel<X, Y> mod, CourbeController<X, Y> cont, Axis<X> xAx, Axis<Y> yAx, String t) {
		super(mod, cont, xAx, yAx, t);

		
	}
	

	

	@Override
	public void update(Observable o, Object arg) {
		
		setDisplay(model().getCourbe());
	}

}
