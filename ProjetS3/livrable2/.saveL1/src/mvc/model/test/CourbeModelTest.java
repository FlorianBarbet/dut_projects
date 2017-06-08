package mvc.model.test;
/*
 * @author Rayan Haddad
 * @author Florian BARBET
 * */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import mvc.model.Courbe;
import mvc.model.CourbeModel;
// TEST POUR COURBE MODEL
public class CourbeModelTest {
	
	Courbe<Number,Number> courbeData = new Courbe<Number,Number>();
	CourbeModel<Number,Number> model = new CourbeModel<Number,Number>();
	CourbeModel<Number,Number> modelPaire = new CourbeModel<Number,Number>();
	CourbeModel<Number,Number> modelImpair = new CourbeModel<Number,Number>();
	
	
	@Before
	public void setUp() throws Exception {
		String chemin = "";
		String chaine = "";
		int i,j,indice=0;
		Double x,y;
		BufferedReader fichier_source;
		ArrayList<String[]> tabChaine = new ArrayList<String[]>();
		ArrayList<String[]> tabCh = new ArrayList<String[]>();
		try
		{
			chemin = "data/Test2.csv";
			fichier_source = new BufferedReader(new FileReader(chemin));
			while((chaine = fichier_source.readLine())!= null)
			{
				tabChaine.add(chaine.split(";"));
				indice++;
			}
			for( i = 0; i < indice ; i++)
				for( j = 0; j < tabChaine.get(i).length ; j++ )
				{
					tabCh.add(tabChaine.get(i)[j].split(","));
				}
			fichier_source.close();
			for(i = 0; i < indice ; i++)
			{
				x = Double.parseDouble(tabCh.get(i)[0]);
				y = Double.parseDouble(tabCh.get(i)[1]);
				courbeData.addXY(x,y);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier est introuvable !");
		}
		modelImpair.setCourbe(courbeData);
		modelPaire.setCourbe(courbeData);
		modelPaire.setOrdre(4);
		modelImpair.setOrdre(3);
		modelPaire.setLambda(4);
		modelImpair.setLambda(0);
	}
	
	@Test
	public void getDataXtest() {
		Courbe<Integer,Integer> c = new Courbe<Integer,Integer>();
		c.addXY(5,2);
		int x = c.getX(0);
		assertEquals(5,x);
	}
	
	@Test
	public void getDataYtest() {	
		CourbeModel<Integer,Integer> modc = new CourbeModel<Integer,Integer>();

		Courbe<Integer,Integer> c = new Courbe<Integer,Integer>();
		
		c.addXY(2,5);
		modc.setCourbe(c);
		int y = modc.getDataY(0);
		assertEquals(5,y);
	}
	
	@Test
	public void setCourbeTest(){
		CourbeModel<Integer,Integer> modc = new CourbeModel<Integer,Integer>();

		Courbe<Integer,Integer> c = new Courbe<Integer,Integer>();
		
		c.addXY(2,5);
		modc.setCourbe(c);
		int x = modc.getDataX(0);
		assertEquals(2,x);
	}
	
	@Test
	public void getLambdaAndSetLambdaInSetUTest(){
		assertEquals(4,modelPaire.getLambda(),0.1);
		assertEquals(0,modelImpair.getLambda(),0.1);
	}
	
	@Test
	public void getOrdreAndSetOrdreInSetUTest(){
		assertEquals(4,modelPaire.getOrdre());
		assertEquals(3,modelImpair.getOrdre());
	}
	
	@Test
	public void getCourbeAndSizeTest(){
		assertEquals(courbeData.sizeOfData(),modelPaire.sizeOfCourbe());
		for(int i=0;i < courbeData.sizeOfData();i++){
			assertEquals(courbeData.getX(i),modelPaire.getDataX(i));
			assertEquals(courbeData.getY(i),modelPaire.getDataY(i));
		}
		
	}
	
	
	@Test
	public void ErrorFinal4Ordre(){
		modelPaire.setOrdre(10);
		assertEquals(4,modelPaire.getOrdre());
	}
	
	@Test
	public void ErrorFinal4Lambda(){
		modelPaire.setLambda(10);
		assertEquals(4,modelPaire.getLambda(),0.1);
	}
	
	@Test
	public void moyenneMobilePaireTest(){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		modelPaire.moyenneMobile(cmm,0);
		assertEquals(courbeData.getX(2), cmm.getX(0));
		assertEquals(0.2425, (double) cmm.getY(0),0.001);
		assertTrue(courbeData.sizeOfData()!=cmm.sizeOfData());
	}
	
	@Test
	public void moyenneMobileImpairTest(){
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		modelImpair.moyenneMobile(cmm,0);
		assertEquals(courbeData.getX(2), cmm.getX(0));
		assertEquals(0.156, (double) cmm.getY(0),0.1);
		assertTrue(courbeData.sizeOfData()!=cmm.sizeOfData());
	}
	
	
	
	@Test
	public void logistiqueTest(){
		Courbe<Number,Number> clogis = new Courbe<Number,Number>();
		modelPaire.logistique(clogis,0);
		assertEquals(courbeData.getX(0), clogis.getX(0));
		assertEquals(-0.200, (double) clogis.getY(0),0.001);
		assertTrue(courbeData.sizeOfData()!=clogis.sizeOfData());
	}
	
	@Test
	public void logistiqueHorsBorneTest(){
		Courbe<Number,Number> clogis = new Courbe<Number,Number>();
		modelPaire.logistique(clogis,0);
		assertEquals(courbeData.getX(0), clogis.getX(0));
		assertEquals(12,courbeData.sizeOfData());
		assertEquals(10,clogis.sizeOfData());
		assertTrue(courbeData.sizeOfData()!=clogis.sizeOfData());
	}
	
	@Test
	public void transfoLogTest(){
		Courbe<Number,Number> clog = new Courbe<Number,Number>();
		modelPaire.transfoLog(clog,0);
		assertEquals(courbeData.getX(0), clog.getX(0));
		assertEquals(-0.798, (double) clog.getY(0),0.001);
		assertTrue(courbeData.sizeOfData()!=clog.sizeOfData());
	}
	
	@Test
	public void transfoLogYinf0Test(){
		Courbe<Number,Number> clog = new Courbe<Number,Number>();
		modelPaire.transfoLog(clog,0);
		assertEquals(courbeData.getX(0), clog.getX(0));
		assertEquals(12,courbeData.sizeOfData());
		assertEquals(11,clog.sizeOfData());// on rappelle que le tableau initiale à 12 valeurs si une donnée est < 0 elle n'est pas repertorié car log(0) tends vers -Infinity
		assertTrue(courbeData.sizeOfData()!=clog.sizeOfData());
	} 
	
	@Test
	public void residuTest(){
		Courbe<Number,Number> cr = new Courbe<Number,Number>();
		modelPaire.residu(cr,0);
		assertEquals(courbeData.getX(0), cr.getX(0));
		assertEquals(0.934, (double) cr.getY(0),0.001);
		assertTrue(courbeData.sizeOfData()==cr.sizeOfData());
	}
	
	@Test
	public void desaisonaliserTest(){
		Courbe<Number,Number> cd = new Courbe<Number,Number>();
		modelPaire.desaisonaliser(cd,0);
		assertEquals(courbeData.getX(0), cd.getX(0));
		assertEquals(0.393, (double) cd.getY(0),0.001);
		assertTrue(courbeData.sizeOfData()==cd.sizeOfData());
	}
	
	@Test
	public void saisonResiduTest(){
		Courbe<Number,Number> csr = new Courbe<Number,Number>();
		modelPaire.saisonResidu(csr,0);
		assertEquals(courbeData.getX(2), csr.getX(0));
		assertEquals(0.127, (double) csr.getY(0),0.001);
		assertTrue(courbeData.sizeOfData()!=csr.sizeOfData());
	}
	
	@Test
	public void transfoBoxCoxLambdaPositifTest(){
		Courbe<Number,Number> cbc = new Courbe<Number,Number>();
		modelPaire.transfoBoxCox(cbc,0);
		assertEquals(courbeData.getX(0), cbc.getX(0));
		assertEquals(-0.239, (double) cbc.getY(0),0.001);
		assertTrue(courbeData.sizeOfData()==cbc.sizeOfData());
	}
	
	@Test
	public void transfoBoxCoxLambdaNulleTest(){
		Courbe<Number,Number> cbc = new Courbe<Number,Number>();
		modelImpair.transfoBoxCox(cbc,0);
		assertEquals(courbeData.getX(0), cbc.getX(0));
		assertEquals(-0.798, (double) cbc.getY(0),0.001);
		assertTrue(courbeData.sizeOfData()!=cbc.sizeOfData());//parce qu'on utilise log
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void ErrorInitialize(){
		CourbeModel<Number,Number> modc = new CourbeModel<Number,Number>();
		Courbe<Number,Number> c = new Courbe<Number,Number>();
		Courbe<Number,Number> cp = new Courbe<Number,Number>();
		modc.setLambda(4);
		modc.setOrdre(5);
		modc.setCourbe(c);
		modc.desaisonaliser(cp, 0);//contient saison qui contient Xt-Mht qui contient Moyenne Mobile
		assertNull(cp.getX(0));
		modc.logistique(cp, 0);
		assertNull(cp.getX(0));
		modc.transfoLog(cp, 0);
		assertNull(cp.getX(0));
		modc.transfoBoxCox(cp, 0);
		assertNull(cp.getX(0));	
		
	}
}