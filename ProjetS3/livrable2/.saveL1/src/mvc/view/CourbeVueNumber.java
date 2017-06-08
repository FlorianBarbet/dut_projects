package mvc.view;

import java.util.Observable;

import javafx.scene.chart.NumberAxis;
import mvc.control.CourbeController;
import mvc.model.CourbeModel;

public class CourbeVueNumber extends CourbeVue<Number,Number> {

	

	public CourbeVueNumber(CourbeModel<Number, Number> mod, CourbeController<Number, Number> cont, String t) {
		super(mod, cont, new NumberAxis(), new NumberAxis(), t);
		// TODO Auto-generated constructor stub
		lineChart.getData().add(series);
		model.addObserver(this);
	}

	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		setDisplay(model().getCourbe());
	}

}
