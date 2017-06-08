package mvc.model.test;

/*
 * @author Rayan Haddad
 * */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mvc.model.Courbe;




public class CourbeTest {
	
	Courbe<Number,Number> courbeData = new Courbe<Number,Number>();
	int taille=0;

	@Before
	public void setUp(){
		for(int i = 1; i < 11;i++){
			courbeData.addXY(i,i*2);
			taille++;
		}
	}
	
	
	@Test
	public void sizeOfDataTest(){
		assertEquals(taille,courbeData.sizeOfData());
	}
	
	@Test
	public void getXtest() {
		Courbe<Integer,Integer> c = new Courbe<Integer,Integer>();
		c.addXY(5,2);
		int x = c.getX(0);
		assertEquals(5,x);
	}

	@Test
	public void getYtest() {	
		Courbe<Integer,Integer> c = new Courbe<Integer,Integer>();
		c.addXY(2,5);
		int y = c.getY(0);
		assertEquals(5,y);
	}
	
	@Test
	public void removeXYTest(){
		int sizeDebut = Integer.valueOf(courbeData.sizeOfData());
		courbeData.removeXY(0);
		assertEquals(sizeDebut,courbeData.sizeOfData()+1); 
	}

	@Test
	public void addXYTest(){
		int sizeDebut = Integer.valueOf(courbeData.sizeOfData());
		courbeData.addXY(40,20);
		assertTrue(sizeDebut==courbeData.sizeOfData()-1);
	}
	
	@Test
	public void setYTest(){ 
			
		assertEquals(2,courbeData.getY(0));
		courbeData.setY(0,20);
		assertEquals(20,courbeData.getY(0));
		
	}

	@Test
	public void indexOfXYTest(){
		int indexY = courbeData.indexOfXYbyY(4);
		int indexX = courbeData.indexOfXYbyX(2);
		
		assertEquals(1,indexY);
		assertEquals(1,indexX);
	}





}