package mvc.control;

import mvc.model.Courbe;
import mvc.model.CourbeModel;
import mvc.view.CourbeVue;

public class CourbeController<X,Y> {


	private CourbeModel<X,Y> model;
	private CourbeVue<X,Y> view = null;
	private ModifieCourbe<X,Y> modcourbe = new ModifieCourbeLog<X,Y> ();

	public CourbeController( CourbeModel<X,Y> m) {
		model = m;
	}
	
	
	public void fixeCourbe(Courbe<X,Y> c){
		model.setCourbe(c);
	}

	public void addView ( CourbeVue<X,Y> view ) {
		this.view = view ;
	}
}
