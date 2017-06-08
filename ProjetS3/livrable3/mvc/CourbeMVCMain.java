package mvc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import mvc.control.CourbeController;
import mvc.model.Courbe;
import mvc.model.CourbeModel;
import mvc.view.CourbeVue;
import mvc.view.CourbeVueConcret;

public class CourbeMVCMain extends Application{
	ArrayList<Courbe<Number,Number>> listc = new ArrayList<Courbe<Number,Number>>();
	Courbe<Number,Number> c = new Courbe<Number,Number>();
	Scanner sc = new Scanner(System.in);


	@Override
	public void start(Stage stage) throws Exception {



		String data = "";
		String chemin = "";
		String chaine = "";
		String save = "";
		int condition = 0;
		int scan = 0;
		int choixaction = -1;
		int indice = 0;
		int choix = 0;
		int i,j,k;
		Double x,y;

		BufferedReader fichier_source;
		BufferedWriter fichier_result;

		Courbe<Number,Number> donnee = new Courbe<Number,Number>();
		Courbe<Number,Number> cmm = new Courbe<Number,Number>();
		Courbe<Number,Number> csr = new Courbe<Number,Number>();
		Courbe<Number,Number> cs = new Courbe<Number,Number>();
		Courbe<Number,Number> cmd = new Courbe<Number,Number>();
		Courbe<Number,Number> logis = new Courbe<Number,Number>();
		Courbe<Number,Number> log = new Courbe<Number,Number>();
		Courbe<Number,Number> bc = new Courbe<Number,Number>();

		Courbe<Number,Number> res = new Courbe<Number,Number>();

		ArrayList<String> listTitle = new ArrayList<String>();
		ArrayList<Courbe<Number,Number>> listCourbe = new ArrayList<Courbe<Number,Number>>(); // permet d'indexer les courbes et donc de modifier la couleur d'une courbe vis�e
		ArrayList<Integer> choice = new ArrayList<Integer>();
		ArrayList<String[]> tabChaine = new ArrayList<String[]>();
		ArrayList<String[]> tabCh = new ArrayList<String[]>();


		CourbeModel<Number,Number> model; 				//	Modele MVC
		CourbeVue<Number,Number> vue;	                // en preparation pour Livrable 2
		CourbeController<Number,Number> control;        // structure OK


		CourbeVue<Number,Number> vueF = null;	                // en preparation pour Livrable 2




		ArrayList<CourbeVue<Number,Number>> listView = new ArrayList<CourbeVue<Number,Number>>();



		
		String nameRep = "";

		File repertoire = new File("data/");
		String [] listefichiers;  
		listefichiers=repertoire.list(); 
		for(i=0;i<listefichiers.length;i++){ 
			if(listefichiers[i].endsWith(".csv")==true){ 

				nameRep+=listefichiers[i].substring(0,listefichiers[i].length()-4)+ "  ";
				if(i%5==0){
					nameRep+="\n";
				}
			}
		}
		
		System.out.println("Inserez le fichier de donnee a utiliser parmis ceux la: ");
		System.out.println(nameRep);
		data = sc.nextLine();
		try
		{
			chemin = "data/"+data+".csv";
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
				c.addXY(x,y);

			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier est introuvable !");
			
		}


		model = CourbeModel.getInstance();
		model.setCourbes(listc);
		model.addCourbe(c);
		model.setIndex(0);
		control = new CourbeController<Number,Number>(model);
		vue = new CourbeVueConcret<Number,Number>(model,control,new NumberAxis(),new NumberAxis(),data);
		control.addView(vue);



		listCourbe.add(c);
		listTitle.add("Base");
		System.out.println("Action :  Transformation = 1 Analyse : 2 ?"); 
		System.out.println(":>>");
		choixaction = sc.nextInt();


		if(choixaction == 1 || choixaction == 2){

			while(condition==0){

				if(choixaction == 1){
					System.out.println("\nVoir resultat pour (number only): ");
					System.out.println("-> 1 : Logarithme Yt1 ");
					System.out.println("-> 2 : BoxCox BC ");
					System.out.println("-> 3 : Logistique Yt2 ");
					System.out.println("-> 4 : Moyenne Mobile (Mt) ");
					System.out.println("-> 5 : Xt-Mt ");
					System.out.println("-> 6 : St : saison");
					System.out.println("-> 7 : Xt-St desaisonnalisation ");
					System.out.println("-> 0 : Fin");
				}else{
					System.out.println("\nVoir resultat pour (un seul choix): ");
					System.out.println("-> 1 Residu");
					System.out.println("-> 0 : Fin");
				}
				System.out.println("0 = END :>>");
				scan = sc.nextInt();
				for( i = 0; i < choice.size();i++){
					if(choice.get(i)==scan){
						scan=-1;	
					}
				}
				if(scan == 0 )condition++;
				else if(scan == -1)
					System.out.println("Option d�j� saisie");
				else{
					choice.add(scan);
					switch(scan){
					case 1 :
						if(choixaction == 1){
							model.transfoLog(log,1);
							listCourbe.add(log);
							listTitle.add("Logarithme");
						}else if(choixaction==2){
							model.residu(res, 1);
							listCourbe.add(res);
							listTitle.add("AnalyseResidu");

						}

						break;
					case 2 : 
						if(choixaction == 1){
							model.transfoBoxCox(bc,1);
							listCourbe.add(bc);
							listTitle.add("BoxCox");

						}else{System.out.println("Option inexistante: "+scan);}
						break;
					case 3 :
						if(choixaction == 1){
							model.logistique(logis,1);
							listCourbe.add(logis);
							listTitle.add("Logistique");

						}else{System.out.println("Option inexistante: "+scan);}
						break;
					case 4 :

						if(choixaction == 1){
							model.moyenneMobile(cmm,1);
							listCourbe.add(cmm);
							listTitle.add("MoyenneMobile");
						}else{System.out.println("Option inexistante: "+scan);}
						break;

					case 5 : 
						if(choixaction == 1){
							model.saisonResidu(csr,1);
							listCourbe.add(csr);
							listTitle.add("residu");
						}else{System.out.println("Option inexistante: "+scan);}


						break;
					case 6 : 
						if(choixaction == 1){
							model.saison(cs,1);
							listCourbe.add(cs);
							listTitle.add("saison");
						}else{System.out.println("Option inexistante: "+scan);}
						break;
					case 7 : 
						if(choixaction == 1){
							model.desaisonaliser(cmd,1);
							listCourbe.add(cmd);
							listTitle.add("desonnalisation");
						}else{System.out.println("Option inexistante: "+scan);}
						break; 

					default : System.out.println("Option inexistante: "+scan);break;
					}
				}
			}
		}else{
			System.out.println("Ni analyse, ni transformation Affichage de la courbe de base !");
			vue.show();
			//System.exit(0);
		}





		System.out.println("Afficher  ? Yes = 1 No = 0");
		choix = sc.nextInt();
		if(Integer.valueOf(choix)==1)
		{

			if(choixaction==1) {
				System.out.println("Affichage  ? Unitaire = 1  Multiple = 0");
				choix+=sc.nextInt();
			}
			if(Integer.valueOf(choix) == 2){ 

				vueF = new CourbeVueConcret<Number,Number>(model,control,new NumberAxis(),new NumberAxis(),data, null);
			}
			for(i = 0; i < choice.size();i++){


				if(Integer.valueOf(choix)==1){
					listView.add(new CourbeVueConcret<Number,Number>(model,control,new NumberAxis(),new NumberAxis(),data));
					vueF = listView.get(i);
				}




				control.addView(vueF);

				switch(choice.get(i)){
				case 1 :
					if(choixaction == 1){
						vueF.addSeries(log, "Yt1");
						vueF.setTitle("Logarithme");

					}else if(choixaction == 2){
						vueF.addSeries(res, "Rt");
						vueF.setTitle("Residus");
					}
					break;
				case 2 : 
					vueF.addSeries(bc, "BC");
					vueF.setTitle("BoxCox");
					break;
				case 3 :
					vueF.addSeries(logis, "Yt2");
					vueF.setTitle("Logistique");
					break;
				case 4 :
					vueF.addSeries(cmm, "Mt");	
					vueF.setTitle("Moyenne Mobile");
					break;
				case 5 :
					vueF.addSeries(csr, "Xt-Mt");
					vueF.setTitle("Moyenne pond�r�e");
					break;
				case 6 :
					vueF.addSeries(cs, "St");
					vueF.setTitle("Saisonnalite");
					break;
				case 7 : 
					vueF.addSeries(cmd, "Xt-St");
					vueF.setTitle("Desaisonnalisation");
					break;
				}

				vueF.setColorSeries(c,listCourbe.indexOf(c), "blue");

				if(Integer.valueOf(choix)==1){

					for(k = 1 ; k < listCourbe.size();k++){
						vueF.setColorSeries(listCourbe.get(k), listCourbe.indexOf(listCourbe.get(k)) , "red");
					}

					vueF.show();
				}
			}



		}
		if(Integer.valueOf(choix) == 2)
			vueF.show();

		String crw = "";
		//for(int a = 0; a < vueF.getLC().getData().size();a++)System.out.println(" lc : "+vueF.getLC().getData().get(a));
		for(i = 0 ; i < listCourbe.size();i++){

			try{
				String title = listTitle.get(i);
				FileWriter fileWriter = new FileWriter("data/save/"+title+".csv");

				fileWriter.append(title);
				save = title+", Ordre : , "+model.getOrdre()+", Lambda : "+model.getLambda()+"\n X , Y \n";
				fileWriter.close();
				chemin = "data/save/"+title+".csv";
				fichier_result = new BufferedWriter(new FileWriter(chemin));


				donnee = listCourbe.get(i);
				for(j=0;j<donnee.sizeOfData();j++)
					save += donnee.getX(j)+","+donnee.getY(j)+"\n";


				fichier_result.write(save);
				fichier_result.close();
				String ch2 = "data/save/Save.csv";
				BufferedWriter fcr = new BufferedWriter(new FileWriter(ch2));
				crw+=save;
				fcr.write(crw);
				fcr.close();
			}catch(FileNotFoundException e){
				System.out.println("Erreur : "+e.getMessage());
			}



		}
		
		int fin=-1;
		while(fin!=0){
			System.out.println("fin de processus(0) : ");
			fin=sc.nextInt();
		}

		System.out.println("Fin de processus\nAu revoir !");
		System.exit(0);



	}



	public  static  void  main(String  args []) {
		launch(args);
	}


}	
