package mvc.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
/**
 * Model du MVC sur la Courbe
 * @author Florian Barbet
 * @author Thomas Mastalerz
 * @author Rayan Haddad
 *  @author Florian Hirson
 * @param <X>
 * @param <Y>
 */
public class CourbeModel<X,Y> extends Observable {

	/**
	 * Courbe<X,Y> courbe sur laquelle toute transformation passera
	 */
	private ArrayList<Courbe<X,Y>> listcourbeData = new ArrayList<Courbe<X,Y>>();
	private Scanner sc = new Scanner(System.in);
	private int ordre=0;
	private int lambda=-1;
	private Courbe<X,Y> courbeData;
	/* Creation du singleton un seul model pour plusieurs vue !*/

	/* à modifier plus tard */

	private static CourbeModel<Number, Number> singleton = null;

	private CourbeModel(){}


	public static CourbeModel<Number, Number> getInstance(){
		if(singleton == null){
			singleton = new CourbeModel<Number,Number>();
		}
		return singleton;
	}
	/* Creation du singleton un seul model pour plusieurs vue !*/

	public int getNbCourbe(){
		return listcourbeData.size();
	}

	/**
	 * Renvoie la courbe accession
	 * @return courbeData
	 */
	public Courbe<X,Y> getCourbe(int i){

		if(!isSetIndex()){
			setIndex(i);
		}
		return courbeData;
	}

	/**
	 * give the first occurrence of courbe named by n
	 * @param n
	 * @return courbe
	 */
	public Courbe<X,Y> getCourbebyName(String n){
		Courbe<X,Y> tmp = null;
		for(Courbe<X, Y> courbe: listcourbeData){
			if(courbe.getName().equals(n)){
				if(tmp==null)
					tmp=courbe;
				else
					System.out.println("already set");
			}
		}
		return tmp;
		
	}
	
	/**
	 * give the first occurrence of courbe named by n return -1 if not found
	 * @param n
	 * @return index
	 */
	public int getIndexbyName(String n){
		
		int tmp = -1;
		for(int i=0; i < listcourbeData.size(); i++){
			Courbe<X,Y> courbe=listcourbeData.get(i);
			if(courbe.getName().equals(n)){
				if(tmp==-1)
					tmp=i;
				else
					System.out.println("already set");
			}
		}
		return tmp;
		
	}
	
	
	/**
	 * setIndex permet de choisir la courbe à transfo
	 * si le choix est deficient c'est à dire inferieur à 0 ou superieur à la taille maximale on met respectivement l'index à 0 ou l'index à la taille max
	 * ainsi on evite les Exception Out of Bounds
	 *
	 * @return rien
	 */
	public void setIndex(int index){
		if(index>=0 && index<=listcourbeData.size()-1)
			courbeData=listcourbeData.get(index);
		else if(index<0)
			courbeData=listcourbeData.get(0);
		else if(index>listcourbeData.size()-1)
			courbeData=listcourbeData.get(listcourbeData.size()-1);
	}

	public int getIndexUse(){
		return listcourbeData.indexOf(courbeData);
	}

	/**
	 * Test pour set l'index si il n'a pas été saisi
	 * @return true si l'index est deja saisie, false sinon;
	 */
	public boolean isSetIndex(){
		return !(courbeData==null);
	}


	private void checkSettingIndexOrDo(int i){

			setIndex(i);

	}
	/**
	 * Permet de modifier la courbe et initialiser
	 * @param c
	 */
	public void setCourbes(ArrayList<Courbe<X,Y>> c){
		listcourbeData = c;
		setChanged();
		notifyObservers();
	}

	public void addCourbe(Courbe<X,Y> c){
		listcourbeData.add(c);
	}

	public void setOrdre(int o){

		ordre = o;
	}

	public int getOrdre(){
		return Integer.valueOf(ordre);
	}

	public double getLambda(){
		return Double.valueOf(lambda);
	}

	public void setLambda(int l){

		lambda = l;
	}


	public int sizeOfCourbe(){
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		return courbeData.sizeOfData();
	}

	public X getDataX(int i){
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		return courbeData.getX(i);
	}

	public Y getDataY(int i){
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		return courbeData.getY(i);
	}




	/**
	 * @author Rayan
	 * @param c
	 */
	public void moyenneMobile(Courbe<Number,Number> c,int a){
		if(!isSetIndex()){checkSettingIndexOrDo(sc.nextInt());}
		double tabX[]=new double[courbeData.sizeOfData()];
		double moyenne = 0;

		if(a==1)System.out.println("Moyenne Mobile : Mht");
		

		if(this.ordre<=0);
		else{
			for(int i=2; i<(courbeData.sizeOfData()-2);i++){

				tabX[i]=(double)courbeData.getX(i);
				if(ordre%2==0){
					moyenne=((0.5*(double)courbeData.getY(i-2))+(double)courbeData.getY(i-1)+(double)courbeData.getY(i)+(double)courbeData.getY(i+1)+((double)courbeData.getY(i+2)*0.5));
					moyenne = moyenne/ordre;

				}
				else{
					moyenne=((double)courbeData.getY(i-1)+(double)courbeData.getY(i)+(double)courbeData.getY(i+1));
					moyenne = moyenne/ordre;
				}
				if(a==1)System.out.println("Mht: "+Double.valueOf(moyenne));
				c.addXY(tabX[i],moyenne);

			}
		}

	}

	/**
	 * Xt-Mht soit St+Residu
	 * @author florian barbet
	 * @param c
	 */
	public void saisonResidu(Courbe<Number,Number> c, int a){
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		double moyennet = 0;
		double xt = 0;
		int i;
		this.moyenneMobile(cmm,0);

		if(a==1)System.out.println("Saison + Residu : Xt-Mht");

		for(i = 2; i < courbeData.sizeOfData()-2;i++){

			moyennet = (double) cmm.getY(i-2);
			xt = (double)courbeData.getY(i);
			c.addXY((double)courbeData.getX(i),xt-moyennet);
			if(a==1)System.out.println("Xt-MhT : "+Double.valueOf(xt-moyennet));
		}
	}

	/**
	 * St calcul de la saisonalitee
	 * @author florian barbet
	 * @param c
	 */
	public void saison(Courbe<Number,Number> c, int a){
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		if(a==1)System.out.println("Saison : St");
		Courbe<Number, Number> cmd = new Courbe<Number,Number>();
		this.saisonResidu(cmd,0);
		double s1 = 0;
		double s2=0;
		double s3=0;
		double s4=0;
		int tourS1 =0;
		int tourS2 =0;
		int tourS3 =0;
		int tourS4 =0;
		double surplus=0.0;
		int i;
		for( i = 0;i<cmd.sizeOfData();i++){
			if((double)cmd.getX(i)%4==0){
				s4+=(double)cmd.getY(i);
				tourS4++;
			}else if((double)cmd.getX(i)%2==0){
				s2+=(double)cmd.getY(i);
				tourS2++;
			}else if((double)cmd.getX(i+1)%4==0){
				s3+=(double)cmd.getY(i);
				tourS3++;
			}else{
				s1+=(double)cmd.getY(i);
				tourS1++;

			}

		}
		s1=s1/tourS1;
		s2=s2/tourS2;
		s3=s3/tourS3;
		s4=s4/tourS4;


		if(s1+s2+s3+s4!=0){
			surplus = s1+s2+s3+s4;
			s1-=surplus/4;
			s2-=surplus/4;
			s3-=surplus/4;
			s4-=surplus/4;
		}
		if(a==1)System.out.println("St : s1: "+s1+" s2: "+s2+" s3: "+s3+" s4: "+s4+"\n Surplus :"+surplus);
		double temporary=0;
		double temporaryNext=0;
		for( i=0;i<courbeData.sizeOfData();i++){
			if((i+1)==courbeData.sizeOfData()){
				temporaryNext=-1;
			}else{
				if(courbeData.getX(i+1) instanceof Double )
					temporaryNext = ((Double)courbeData.getX(i+1)*10)/10;
				else{
					temporaryNext = ((Integer)courbeData.getX(i+1)*10)/10;
				}
			}
			if(courbeData.getX(i) instanceof Double )
				temporary = ((Double)courbeData.getX(i)*10)/10;
			else{
				temporary = ((Integer)courbeData.getX(i)*10)/10;
			}
			


			if(temporary%4==0){
				c.addXY(temporary, s4);
			}else if(temporaryNext%4==0){
				c.addXY(temporary, s3);
				
			}else if(temporary%2==0){
				c.addXY(temporary, s2);
			}else{
				c.addXY(temporary, s1);
			}


		}


	}

	/**
	 * Moyenne desaisonnalise
	 * @author florian barbet
	 * @param c
	 */
	public void desaisonaliser(Courbe<Number,Number> c,int a){
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		if(a==1)System.out.println("Desaisonnalisation : Xt-St");
		Courbe<Number,Number> st = new Courbe<Number,Number>();
		this.saison(st,0);
		double des = 0;
		for(int i = 0; i < courbeData.sizeOfData();i++){

			des =(double)courbeData.getY(i);
			des-=(double)st.getY(i);
			if(a==1)System.out.println("Xt-St : "+des);
			if(courbeData.getX(i) instanceof Double)
				c.addXY((double)courbeData.getX(i), des);
			else
				c.addXY((Integer)courbeData.getX(i), des);
		}
	}


	/**
	 * Basé sur l'exemple vue en cours sur R de M.IOVLEFF
	 * @author florian barbet
	 * @param c
	 * @param a
	 */
	public void residu(Courbe<Number,Number> c, int a){
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		Courbe<Number,Number> y = new Courbe<Number,Number>();
		double tbar=0.0;
		double ybar=0.0;
		double vart=0.0;
		double covyt=0.0;
		double ahat=0.0;
		double bhat=0.0;
		double yhat=0.0;
		double residu=0.0;
		desaisonaliser(y,0);

		for(int i = 0; i < courbeData.sizeOfData();i++){//sert à faire les sommes
			tbar+= i+1;
			ybar+= (double)y.getY(i);
			vart+=(i+1)*(i+1);
			covyt+=((double)y.getY(i)*(i+1));
		}
		vart/=12;
		covyt/=12;
		tbar/=12;
		ybar/=12;
		vart-=tbar*tbar;
		covyt-=ybar*tbar;
		ahat = covyt/vart;
		bhat = ybar - ahat*tbar;
		//	System.out.println("residu d:"+tbar+" ; "+ybar+" ; "+vart + " ; "+covyt + " b"+bhat+" a"+ahat);
		for(int i = 0; i < courbeData.sizeOfData();i++){
			yhat=ahat*(i+1)+bhat;
			//System.out.println("yhat :"+yhat);

			residu = (double)y.getY(i)-yhat;
			System.out.println("residu"+i+" :"+residu);
			c.addXY((double)courbeData.getX(i),residu );
		}


	}

	/**
	 * logistique se trouvant avec Xt -> I = ]0 , 1[
	 * @author florian barbet
	 * @param c
	 */
	public void logistique(Courbe<X,Number> c, int a){
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		int taille = courbeData.sizeOfData();
		X dataX;
		double dataY;
		double tmpY = 0;
		X tmpX;
		double tmpForm = 0;

		if(a==1)System.out.println("Logistique : Yt2");
		for(int i=0; i<taille; i++){

			tmpY = (double)courbeData.getY(i);
			tmpX = courbeData.getX(i);
			tmpForm = tmpY/(1-tmpY);
			if((tmpY > 0&&  tmpY<1)){
				dataX = tmpX;
				dataY = Math.log(tmpForm);
				c.addXY(dataX,dataY);
				if(a==1)System.out.println("Yt2 : "+dataY);
			}



		}
	}



	/**
	 * TransfoLog transformation sur la courbe avec la fonction log
	 * @author Thomas
	 * @param c
	 */
	public void transfoLog(Courbe<X,Number> c, int a){
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		if(a==1)System.out.println("Logarithme : Yt1");
		int taille = courbeData.sizeOfData();

		X dataX;
		double dataY;

		for(int i=0; i<taille; i++){


			if((double)courbeData.getY(i) < 0 ||(double)courbeData.getX(i)==0){

			}
			else{
				dataX = courbeData.getX(i);
				dataY = Math.log((double)courbeData.getY(i));
				c.addXY(dataX,dataY);
				if(a==1)System.out.println("Yt1 : "+dataY);

			}


		}

	}

	/**
	 * TransfoBoxCox transformation sur la courbe avec la fonction box cox
	 * @author Florian Hirson
	 * @param c
	 * @param lambda
	 */

	public void transfoBoxCox(Courbe<X,Number> c, int a) {
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		int i;
		int taille = courbeData.sizeOfData();
		X dataX;
		double dataY;
		if(lambda<0){
			System.out.print("Lambda : ");
			setLambda(sc.nextInt());
		}
		if(a==1)System.out.println("Fonction Box Cox : BC ");

		if (lambda == 0) {
			transfoLog(c,1);
		}else if( lambda > 0) {

			for(i=0; i<taille; i++){

				dataX = courbeData.getX(i);

				dataY = Math.pow((double)courbeData.getY(i), lambda);
				dataY-=1;
				dataY/=lambda;

				if(a==1)System.out.println("BC : "+ dataY);
				c.addXY(dataX,dataY);
			}

		}else{

			System.out.println("Lambda doit être positif ou nul ! >>");
			transfoBoxCox(c,a);
		}
	}

	/**
	 * méthode moyenne utilisée pour transformation régression linéaire
	 * @author Thomas
	 * @param c
	 * @param a
	 */

	public double Moyenne(Courbe<X,Number> c) {
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		double moyenne=0;
		int taille = courbeData.sizeOfData();

		for(int i = 0;i < taille ;i++)
		{
			moyenne += (double)c.getY(i);
		}
		moyenne /= taille;


		return moyenne;
	}

	/**
	 * transformation régression linéaire sur la courbe avec méthode transfoRegLineaire
	 * @author Thomas
	 * @param c
	 * @param a
	 */
	public void transfoRegLineaire(Courbe<X,Number> c, int a) {
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		int taille = courbeData.sizeOfData();
		Courbe<X, Number> cpcd = new Courbe<X,Number>();

		double somme1 = 0;
		int carre = 0;

		for(int i=0; i<taille; i++){
			cpcd.addXY(courbeData.getX(i), (double)courbeData.getY(i));
		}

		for(int i = 0; i < taille; i++)
		{
			somme1 += (double)courbeData.getY(i) * (i + 1);
		}
		double cov = somme1 / taille;

		for(int x = 0;x < taille; x++)
		{
			carre += Math.pow(x + 1,2);
		}
		carre /= taille;

		double valA = (cov - ((taille + 1) / 2) * Moyenne(cpcd)) / (carre - ((taille + 1) / 2));


		double valB = Moyenne(cpcd) - valA * ((taille + 1) / 2);

		for (int i = 0;i < taille; i++)
		{
			c.addXY(courbeData.getX(i), valA * (i + 1) + valB);
		}
	}


	/*prevision simple et double ci dessous*/

	private double beta=-1;
	private double aT=-1;
	private double bT=-1;

	public double getBchapT(){
		return this.bT;
	}
	public double getAchapT(){
		return this.aT;
	}

	public double getBeta(){
		return Double.valueOf(beta);
	}

	/**
	 *
	 * @param l valeur de beta
	 * @return -1 si beta est deja entre, 0 sinon.
	 */
	public int setBeta(double l){
		if(l>0 && l<1 && ( beta<0 || beta>1 || beta==-1 )){
			beta = l;
			return 0;
		}
		return -1;

	}

	/**
	 * Une methode regroupant deux fonctionnalites qui sont lies le lissage double s1 et simple c2
	 *
	 *
	 * @param s1 lissage exponentiel simple
	 * @param c2 lissage exponentiel double
	 * @return 0 si tout se passe bien, recursivite sinon.
	 */
	public int lissage_exp1et2(Courbe<Number,Number> c1,Courbe<Number,Number> c2){
		if(!isSetIndex())checkSettingIndexOrDo(sc.nextInt());
		Courbe<Number,Number> s1 = new Courbe<Number,Number>();
		Courbe<Number,Number> s2=new Courbe<Number,Number>();
		double xT = 0;
		int i=0;
		
		double cdY_0 = 0;
		double cdX_0 = 0;
		if(courbeData.getY(0) instanceof Double){
			cdY_0=(double)courbeData.getY(0);
		}
		else
			cdY_0=(int)courbeData.getY(0);

		if(courbeData.getX(0) instanceof Double){
			cdY_0=(double)courbeData.getX(0);
		}
		else
			cdY_0=(int)courbeData.getX(0);
		
		
		if(getBeta()<0 || getBeta()>1){ setBeta(sc.nextDouble()); return lissage_exp1et2(s1,c2);}else{
			
			double cdY = 0;
			double cdX = 0;
			if(courbeData.getY(i) instanceof Double){
				cdY=(double)courbeData.getY(i);
			}
			else
				cdY=(int)courbeData.getY(i);

			if(courbeData.getX(i) instanceof Double){
				cdY=(double)courbeData.getX(i);
			}
			else
				cdY=(int)courbeData.getX(i);
			
			
			c1.addXY(cdX_0, cdY_0);
			c2.addXY(cdX_0, cdY_0);
			s1.addXY(cdX_0,(cdY_0)*(1-beta) );
			s2.addXY(s1.getX(0),((double)s1.getY(0))*(1-beta));
			for( i=1; i < courbeData.sizeOfData();i++){
			
				s1.addXY(cdX,beta*(double)courbeData.getY(i)+(1-beta)*(double)s1.getY(i-1));
				if(s1.getX(i) instanceof Double)
					s2.addXY((double)s1.getX(i),beta*(double)s1.getY(i)+(1-beta)*(double)s2.getY(i-1));
				else
					s2.addXY((int)s1.getX(i),beta*(double)s1.getY(i)+(1-beta)*(double)s2.getY(i-1));
				c1.addXY((cdX), (cdY));
				c2.addXY((cdX), (cdY));
			
			
			}
			//pour demontrer le lissage nous allons ajouter 3 points

			for(int j=0;j<3;j++)
				c1.addXY(i+j, s1.getY(i-1));

			this.aT=(1-beta)/beta;
			this.aT*=(((double)s1.getY(s1.sizeOfData()-1))-((double)s2.getY(s2.sizeOfData()-1)));

			this.bT=2*(((double)s1.getY(s1.sizeOfData()-1))-((double)s2.getY(s2.sizeOfData()-1)));
			//xT est la prevision pour le lissage exponentiel double
			xT=aT*getOrdre()+bT;

			//pour demontrer le lissage nous allons ajouter 3 points

			for(i=courbeData.sizeOfData();i<courbeData.sizeOfData()+3;i++){
				System.out.print("/"+i+"/"+"---\n");
				c2.addXY(i, xT);
			}

		}
		return 0;

	}

	/**
	 * en gros on aura deux intervales un X et un Y
	 * X(debut,fin)
	 * Y(debut,fin)
	 * 
	 * @param xd la coordonnée X origine du zoom 
	 
	 * @param xf la coordonnée X de fin du zoom
	 
	 * @param courbe la courbe sur laquel le zoom est effectif
	 */
	public void zoomIn(int xd,int xf,Courbe<Number,Number> courbe){
		
		double coordY;
		int coordX;
		
		for(int i=0;i<courbeData.sizeOfData();i++){
			if(courbeData.getX(i) instanceof Double)
				coordX=(int)((double)courbeData.getX(i)*10/10);
			else
				coordX=(int)courbeData.getX(i);
			
			if(courbeData.getY(i) instanceof Double)	
				coordY=(double)courbeData.getY(i);
			else
				coordY=(double)((int)courbeData.getY(i)*1.0);
			
			
			
			if( coordX>=xd && coordX<=xf){
				courbe.addXY(coordX, coordY);
				System.out.println("zoom sur : ("+coordX+","+coordY+")");
			}
			
		}
		
		
	}

}


