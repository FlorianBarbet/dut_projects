package mvc.control;
/**
 * Controller et ajout des courbes par methode
 * @author florian barbet
 */
import java.util.ArrayList;

import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TabPane;
import mvc.model.Courbe;
import mvc.model.CourbeModel;
import mvc.view.CourbeVue;
import mvc.view.CourbeVueConcret;
import mvc.view.InputDialogs;

public class CourbeController<X,Y> {


	private CourbeModel<Number,Number> model;
	private CourbeVue<Number,Number> view = null;
	private ModifieCourbe modcourbe = new ModifieCourbeForm ();

	public CourbeController( CourbeModel<Number,Number> m) {
		model = m;
	}
	

	public void fixeCourbes(ArrayList<Courbe<Number,Number>> listcourbe){
		
		model.setCourbes(listcourbe);
	
	}

	public void addView ( CourbeVue<Number,Number> view ) {
		this.view=view ;
	}
	
	@SuppressWarnings("unchecked")
	public void doMM(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane){
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();

		while(model.getOrdre()<=0){
			model.setOrdre((int)InputDialogs.saisieOrdre());
		}
		model.setIndex(model.getIndexbyName(courbe.getName()));
		model.moyenneMobile(courbeN, 0);
		listCourbe.add(courbeN);
		listTitle.add("MM");
		if(model.getIndexbyName("Moyenne Mobile de "+courbe.getName())<0)
			courbeN.setName("Moyenne Mobile de "+courbe.getName());
		else
			;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>)this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.addSeries(courbeN, "MM");
		vueF.setTitle("MM");
		model.setIndex(0);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void doSaisonResidu(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane){
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();

		while(model.getOrdre()<=0){
			model.setOrdre((int)InputDialogs.saisieOrdre());
		}
		model.setIndex(model.getIndexbyName(courbe.getName()));
		model.saisonResidu(courbeN, 0);
		listCourbe.add(courbeN);
		listTitle.add("SR");
		if(model.getIndexbyName("Saison Residuelle de "+courbe.getName())<0)
			courbeN.setName("Saison Residuelle de "+courbe.getName());
		else
			;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>)this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.addSeries(courbeN, "Xt-Mht");
		vueF.setTitle("Xt-Mht");
		model.setIndex(0);

	}
	
	
	
	@SuppressWarnings("unchecked")
	public void doSaison(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane){
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();

		while(model.getOrdre()<=0){
			model.setOrdre((int)InputDialogs.saisieOrdre());
		}
		model.setIndex(model.getIndexbyName(courbe.getName()));
		model.saison(courbeN, 0);
		listCourbe.add(courbeN);
		listTitle.add("MM");
		if(model.getIndexbyName("Saisonnalité de "+courbe.getName())<0)
			courbeN.setName("Saisonnalité de "+courbe.getName());
		else
			;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>)this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.addSeries(courbeN, "St");
		vueF.setTitle("St");
		model.setIndex(0);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void doDesaisonaliser(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane){
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();

		while(model.getOrdre()<=0){
			model.setOrdre((int)InputDialogs.saisieOrdre());
		}
		model.setIndex(model.getIndexbyName(courbe.getName()));
		model.desaisonaliser(courbeN, 0);
		listCourbe.add(courbeN);
		listTitle.add("Xt-St");
		if(model.getIndexbyName("Desaisonnalisation de "+courbe.getName())<0)
			courbeN.setName("Desaisonnalisation de "+courbe.getName());
		else
			;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>)this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.addSeries(courbeN, "Xt-St");
		vueF.setTitle("Xt-St");
		model.setIndex(0);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void doLogistique(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane){
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();
		model.setIndex(model.getIndexbyName(courbe.getName()));
		model.logistique(courbeN,1);
		listCourbe.add(courbeN);
		listTitle.add("Logistique");
		if(model.getIndexbyName("Logistique de "+courbe.getName())<0)
				courbeN.setName("Logistique de "+courbe.getName());
		else
		;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>) this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.setTitle("Logistique");
		model.setIndex(0);
		
		
	}
	
	@SuppressWarnings("unchecked")
	public void doLog(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane ){
		
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();
		model.setIndex(model.getIndexbyName(courbe.getName()));
		model.transfoLog(courbeN,1);
		listCourbe.add(courbeN);
		listTitle.add("Logarithme");
		if(model.getIndexbyName("Logarithme de "+courbe.getName())<0)
				courbeN.setName("Logarithme de "+courbe.getName());
		else
		;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>) this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.setTitle("Logarithme");
		model.setIndex(0);
		
	
		
	}
	
	@SuppressWarnings("unchecked")
	public void doBoxCox(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane ){
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();

		while(model.getLambda()<0){
			model.setLambda((int)InputDialogs.saisieLambda());
		}
		model.setIndex(model.getIndexbyName(courbe.getName()));
		model.transfoBoxCox(courbeN, 0);
		listCourbe.add(courbeN);
		listTitle.add("BoxCox");
		if(model.getIndexbyName("Box Cox de "+courbe.getName())<0)
			courbeN.setName("Box Cox de "+courbe.getName());
		else
			;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>)this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.addSeries(courbeN, "BC");
		vueF.setTitle("BoxCox");
		model.setIndex(0);
	}
	
	@SuppressWarnings("unchecked")
	public void doRegLin(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane){
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();
		model.setIndex(model.getIndexbyName(courbe.getName()));
		model.transfoRegLineaire(courbeN,1);
		listCourbe.add(courbeN);
		listTitle.add("Logarithme");
		if(model.getIndexbyName("Regression Lineaire de "+courbe.getName())<0)
				courbeN.setName("Regression Lineaire de "+courbe.getName());
		else
		;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>) this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.setTitle("Regression Lineaire");
		model.setIndex(0);
	}
	
	@SuppressWarnings("unchecked")
	public void doLissageExp1(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane){
		
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();
		model.setIndex(model.getIndexbyName(courbe.getName()));
		while(model.getBeta()<=0 || model.getBeta()>1){
			model.setBeta((double)InputDialogs.saisieBeta());
		};
		courbeN=modcourbe.doLissageExp1(courbeN, model);
		listCourbe.add(courbeN);
		listTitle.add("Lissage exp simple");
		if(model.getIndexbyName("Lissage exp simple de "+courbe.getName())<0)
				courbeN.setName("Lissage exp simple de "+courbe.getName());
		else
		;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>) this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.setTitle("Lissage simple");
		model.setIndex(0);
		
		Courbe<Number,Number> c = modcourbe.doLog(new Courbe<Number,Number>(),model);
		model.addCourbe(c);
		view.addSeries(c,"LS");
		
	}
	
	@SuppressWarnings("unchecked")
	public void doLissageExp2(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane){
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();
		model.setIndex(model.getIndexbyName(courbe.getName()));
		
		while(model.getBeta()<=0 || model.getBeta()>1){
			model.setBeta((double)InputDialogs.saisieBeta());
		};
		
		courbeN=modcourbe.doLissageExp2(courbeN, model);
		listCourbe.add(courbeN);
		listTitle.add("Lissage exp double");
		if(model.getIndexbyName("Lissage exp double de "+courbe.getName())<0)
				courbeN.setName("Lissage exp double de "+courbe.getName());
		else
		;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>) this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.setTitle("Lissage double");
		model.setIndex(0);
		
		Courbe<Number,Number> c = modcourbe.doLog(new Courbe<Number,Number>(),model);
		model.addCourbe(c);
		view.addSeries(c,"LD");
	}
	
	@SuppressWarnings("unchecked")
	public void doResidu(Courbe<Number,Number> courbe,CourbeVue<Number,Number> vueF,ArrayList<Courbe<Number,Number>> listCourbe, ArrayList<String> listTitle, TabPane tabPane){
		Courbe<Number,Number> courbeN=new Courbe<Number,Number>();
		model.setIndex(model.getIndexbyName(courbe.getName()));
		while(model.getOrdre()<=0){
			model.setOrdre((int)InputDialogs.saisieOrdre());
		}
		model.residu(courbeN,1);
		listCourbe.add(courbeN);
		listTitle.add("residu");
		if(model.getIndexbyName("Residu "+courbe.getName())<0)
				courbeN.setName("Residu "+courbe.getName());
		else
		;
		model.addCourbe(courbeN);
		model.setIndex(model.getIndexbyName(courbeN.getName()));
		vueF = new CourbeVueConcret<Number,Number>(model,(CourbeController<Number, Number>) this,new NumberAxis(),new NumberAxis(),courbeN.getName(), tabPane);
		this.addView(vueF);
		vueF.setTitle("Residu");
		model.setIndex(0);
	}

}

