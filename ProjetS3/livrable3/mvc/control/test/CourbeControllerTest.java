package mvc.control.test;

import java.util.ArrayList;

import org.junit.Before;

import mvc.control.CourbeController;
import mvc.model.Courbe;
import mvc.model.CourbeModel;

public class CourbeControllerTest {

	CourbeModel<Number,Number> model = CourbeModel.getInstance();
	ArrayList<Courbe<Number,Number>> courbeData = new ArrayList<Courbe<Number,Number>>();
	Courbe<Number,Number> courbeTest = new Courbe<Number,Number>();
	CourbeController<Number,Number> control = new CourbeController<Number,Number>(model); 
	
	@Before
	public void setUp() throws Exception {

		courbeTest.addXY(1,3.0);
		courbeTest.addXY(2,4.0);
		courbeTest.addXY(3,4.5);
		courbeTest.addXY(4,4.0);
		courbeTest.setName("Base");
		control.fixeCourbes(courbeData);
		model.addCourbe(courbeTest);
		model.setIndex(0);
		model.setBeta(0.5);
		model.setOrdre(4);
		model.setLambda(4);
	}
	

	

}
