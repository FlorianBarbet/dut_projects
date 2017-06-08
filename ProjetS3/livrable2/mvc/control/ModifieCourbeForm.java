/**
 * mise en place des methodes de transformations, analyse et prevision de CourbeModel !
 * @author florian barbet
 */
package mvc.control;

import mvc.model.Courbe;
import mvc.model.CourbeModel;

public class ModifieCourbeForm implements ModifieCourbe{

	@Override
	public Courbe<Number, Number> doMM(Courbe<Number, Number> c, CourbeModel<Number, Number> model) {
		model.moyenneMobile(c, 0);
		c.setName("Moyenne Mobile");
		return c;
	}

	@Override
	public Courbe<Number, Number> doSaisonResidu(Courbe<Number, Number> c, CourbeModel<Number, Number> model) {
		model.saisonResidu(c, 0);
		c.setName("saison residu");
		return c;
	}

	@Override
	public Courbe<Number, Number> doSaison(Courbe<Number, Number> c, CourbeModel<Number, Number> model) {
		model.saison(c, 0);
		c.setName("Saison");
		return c;
	}

	@Override
	public Courbe<Number, Number> doDesaisonaliser(Courbe<Number, Number> c, CourbeModel<Number, Number> model) {
		model.desaisonaliser(c, 0);
		c.setName("Desaisonalise");
		return c;
	}

	@Override
	public Courbe<Number, Number> doLogistique(Courbe<Number, Number> c, CourbeModel<Number, Number> model) {
		model.logistique(c, 0);
		c.setName("Logistique");
		return c;
	}

	@Override
	public Courbe<Number, Number> doLog(Courbe<Number, Number> c, CourbeModel<Number, Number> model) {
		model.transfoLog(c, 0);
		c.setName("Logarithme");
		return c;
	}

	@Override
	public Courbe<Number, Number> doBoxCox(Courbe<Number, Number> c, CourbeModel<Number, Number> model) {
		model.transfoBoxCox(c, 0);
		c.setName("Box Cox");
		return c;
	}

	@Override
	public Courbe<Number, Number> doRegLin(Courbe<Number, Number> c, CourbeModel<Number, Number> model) {
		model.transfoRegLineaire(c, 0);
		c.setName("Regression Lineaire");
		return c;
	}

	@Override
	public Courbe<Number, Number> doLissageExp1(Courbe<Number, Number> s1, CourbeModel<Number, Number> model) {
		model.lissage_exp1et2(s1, new Courbe<Number,Number>());
		s1.setName("Lissage exponentiel simple");
		return s1;
	}

	@Override
	public Courbe<Number, Number> doLissageExp2(Courbe<Number, Number> s2, CourbeModel<Number, Number> model) {
		model.lissage_exp1et2(new Courbe<Number,Number>(), s2);
		s2.setName("Lissage exponentiel double");
		return s2;
	}



}
