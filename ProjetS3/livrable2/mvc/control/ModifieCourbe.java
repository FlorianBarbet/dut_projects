/**
 * mise en place des prototypes de transformations, analyse et prevision de CourbeModel !
 * @author florian barbet
 */
package mvc.control;

import mvc.model.Courbe;
import mvc.model.CourbeModel;

public interface ModifieCourbe {


	public Courbe<Number,Number> doMM(Courbe<Number,Number> c,CourbeModel<Number,Number> model);
	public Courbe<Number,Number> doSaisonResidu(Courbe<Number,Number> c,CourbeModel<Number,Number> model);
	public Courbe<Number,Number> doSaison(Courbe<Number,Number> c,CourbeModel<Number,Number> model);
	public Courbe<Number,Number> doDesaisonaliser(Courbe<Number,Number> c,CourbeModel<Number,Number> model);
	public Courbe<Number,Number> doLogistique(Courbe<Number,Number> c,CourbeModel<Number,Number> model);
	public Courbe<Number,Number> doLog(Courbe<Number,Number> c,CourbeModel<Number,Number> model);
	public Courbe<Number,Number> doBoxCox(Courbe<Number,Number> c,CourbeModel<Number,Number> model);
	public Courbe<Number,Number> doRegLin(Courbe<Number,Number> c,CourbeModel<Number,Number> model);
	public Courbe<Number,Number> doLissageExp1(Courbe<Number,Number> s1,CourbeModel<Number,Number> model);
	public Courbe<Number,Number> doLissageExp2(Courbe<Number,Number> s2,CourbeModel<Number,Number> model);


}
