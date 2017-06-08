package mvc.model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;



import mvc.model.Courbe;
import mvc.model.CourbeModel;

public class CourbeModelPrevisionTest {

	ArrayList<Courbe<Number,Number>> courbeData = new ArrayList<Courbe<Number,Number>>();
	Courbe<Number,Number> courbeTest = new Courbe<Number,Number>();
	CourbeModel<Number,Number> model = CourbeModel.getInstance();
	Courbe<Number,Number> c1 = new Courbe<Number,Number>();
	Courbe<Number,Number> c2 = new Courbe<Number,Number>();
	
	@Before
	public void setUp() throws Exception {
		
		courbeTest.addXY(1,3.0);
		courbeTest.addXY(2,4.0);
		courbeTest.addXY(3,4.5);
		courbeTest.addXY(4,4.0);
		
		
		
		
		model.setCourbes(courbeData);
		model.addCourbe(courbeTest);
		model.setIndex(0);
		model.setBeta(0.5);
		model.setOrdre(4);
		
		
		model.lissage_exp1et2(c1, c2);
		
	}

	@Test
	public void keepdataSimpleTest(){
		assertEquals(3.0,(double)c1.getY(0),0.1);
		assertEquals(4.0,(double)c1.getY(1),0.1);
		assertEquals(4.5,(double)c1.getY(2),0.1);	
		assertEquals(4.0,(double)c1.getY(3),0.1);	
	}
	
	@Test
	public void keepdataDoubleTest(){
		assertEquals(3.0,(double)c2.getY(0),0.1);
		assertEquals(4.0,(double)c2.getY(1),0.1);
		assertEquals(4.5,(double)c2.getY(2),0.1);	
		assertEquals(4.0,(double)c2.getY(3),0.1);	
	}
	
	@Test
	public void lissageExpSimpleTest() {
		assertEquals(3.8125,(double)c1.getY(6),0.0001);	
		assertEquals(3.8125,(double)c1.getY(5),0.0001);	
		assertEquals(3.8125,(double)c1.getY(4),0.0001);	
		
	}

	@Test
	public void lissageExpDoubleTest(){
		assertEquals(3.375,(double)c2.getY(6),0.001);
		assertEquals(3.375,(double)c2.getY(5),0.001);	
		assertEquals(3.375,(double)c2.getY(4),0.001);	
		
	}
	
}
