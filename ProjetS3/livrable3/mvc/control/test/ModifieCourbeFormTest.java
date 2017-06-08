package mvc.control.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import mvc.control.ModifieCourbe;
import mvc.control.ModifieCourbeForm;
import mvc.model.Courbe;
import mvc.model.CourbeModel;

public class ModifieCourbeFormTest {

	ArrayList<Courbe<Number,Number>> courbeData = new ArrayList<Courbe<Number,Number>>();
	Courbe<Number,Number> courbeTest = new Courbe<Number,Number>();
	CourbeModel<Number,Number> model = CourbeModel.getInstance();
	ModifieCourbe modcourbe = new ModifieCourbeForm();


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
		model.setLambda(4);
	}

	@Test
	public void doMMtest(){
		Courbe<Number,Number> courbeT = new Courbe<Number,Number>();
		Courbe<Number,Number> tmp = modcourbe.doMM(new Courbe<Number,Number>(), model);
		model.moyenneMobile(courbeT, 0);
		for(int i=0; i < courbeT.sizeOfData();i++){
			assertEquals(courbeT.getX(i),tmp.getX(i));
			assertEquals(courbeT.getY(i),tmp.getY(i));
		}
	}

	@Test
	public void doSaisonResidutest(){
		Courbe<Number,Number> courbeT = new Courbe<Number,Number>();
		Courbe<Number,Number> tmp = modcourbe.doSaisonResidu(new Courbe<Number,Number>(), model);
		model.saisonResidu(courbeT, 0);
		for(int i=0; i < courbeT.sizeOfData();i++){
			assertEquals(courbeT.getX(i),tmp.getX(i));
			assertEquals(courbeT.getY(i),tmp.getY(i));
		}
	}

	@Test
	public void doSaisontest(){
		Courbe<Number,Number> courbeT = new Courbe<Number,Number>();
		Courbe<Number,Number> tmp = modcourbe.doSaison(new Courbe<Number,Number>(), model);
		model.saison(courbeT, 0);
		for(int i=0; i < courbeT.sizeOfData();i++){
			assertEquals(courbeT.getX(i),tmp.getX(i));
			assertEquals(courbeT.getY(i),tmp.getY(i));
		}
	}

	@Test
	public void doDesaisonalisertest(){
		Courbe<Number,Number> courbeT = new Courbe<Number,Number>();
		Courbe<Number,Number> tmp = modcourbe.doDesaisonaliser(new Courbe<Number,Number>(), model);
		model.desaisonaliser(courbeT, 0);
		for(int i=0; i < courbeT.sizeOfData();i++){
			assertEquals(courbeT.getX(i),tmp.getX(i));
			assertEquals(courbeT.getY(i),tmp.getY(i));
		}
	}

	@Test
	public void doLogistiquetest(){
		Courbe<Number,Number> courbeT = new Courbe<Number,Number>();
		Courbe<Number,Number> tmp = modcourbe.doLogistique(new Courbe<Number,Number>(), model);
		model.logistique(courbeT, 0);
		for(int i=0; i < courbeT.sizeOfData();i++){
			assertEquals(courbeT.getX(i),tmp.getX(i));
			assertEquals(courbeT.getY(i),tmp.getY(i));	
		}
	}

	@Test
	public void doLogtest(){
		Courbe<Number,Number> courbeT = new Courbe<Number,Number>();
		Courbe<Number,Number> tmp = modcourbe.doLog(new Courbe<Number,Number>(), model);
		model.transfoLog(courbeT, 0);
		for(int i=0; i < courbeT.sizeOfData();i++){
			assertEquals(courbeT.getX(i),tmp.getX(i));
			assertEquals(courbeT.getY(i),tmp.getY(i));
		}
	}

	@Test
	public void doBoxCoxtest(){
		Courbe<Number,Number> courbeT = new Courbe<Number,Number>();
		Courbe<Number,Number> tmp = modcourbe.doBoxCox(new Courbe<Number,Number>(), model);
		model.transfoBoxCox(courbeT, 0);
		for(int i=0; i < courbeT.sizeOfData();i++){
			assertEquals(courbeT.getX(i),tmp.getX(i));
			assertEquals(courbeT.getY(i),tmp.getY(i));
		}
	}

	@Test
	public void doRegLintest(){
		Courbe<Number,Number> courbeT = new Courbe<Number,Number>();
		Courbe<Number,Number> tmp = modcourbe.doRegLin(new Courbe<Number,Number>(), model);
		model.transfoRegLineaire(courbeT, 0);
		for(int i=0; i < courbeT.sizeOfData();i++){
			assertEquals(courbeT.getX(i),tmp.getX(i));
			assertEquals(courbeT.getY(i),tmp.getY(i));
		}
	}

	@Test
	public void doLissageExp1test(){
		Courbe<Number,Number> courbeT = new Courbe<Number,Number>();
		Courbe<Number,Number> tmp = modcourbe.doLissageExp1(new Courbe<Number,Number>(), model);
		model.lissage_exp1et2(courbeT, new Courbe<Number,Number>());
		for(int i=0; i < courbeT.sizeOfData();i++){
			assertEquals(courbeT.getX(i),tmp.getX(i));
			assertEquals(courbeT.getY(i),tmp.getY(i));
		}
	}

	@Test
	public void doLissageExp2test(){
		Courbe<Number,Number> courbeT = new Courbe<Number,Number>();
		Courbe<Number,Number> tmp = modcourbe.doLissageExp2(new Courbe<Number,Number>(), model);
		model.lissage_exp1et2(new Courbe<Number,Number>(),courbeT);
		for(int i=0; i < courbeT.sizeOfData();i++){
			assertEquals(courbeT.getX(i),tmp.getX(i));
			assertEquals(courbeT.getY(i),tmp.getY(i));
		}
	}




}
