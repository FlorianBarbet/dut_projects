import java.util.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuG extends Application{
	
	public void start (Stage stage){
		
		Button analyse = new Button("ANALYSE");
		analyse.setMaxSize(150, 150);
		analyse.setPadding(new Insets(10,10,10,10));
		Button prevision = new Button("PREVISION");
		prevision.setMaxSize(150, 150);
		prevision.setPadding(new Insets(10,10,10,10));
		Button transfo = new Button("TRANSFORMATION");
		transfo.setMaxSize(150, 150);
		transfo.setPadding(new Insets(10,10,10,10));
		Button quitter = new Button("Quitter");
		
		
		
		VBox vb = new VBox();
		vb.getChildren().addAll(analyse,prevision,transfo,quitter);
		vb.setPadding(new Insets(20,20,20,20));
		vb.setSpacing(40);
		vb.setAlignment(Pos.CENTER);
		BorderPane bp = new BorderPane();
		bp.setCenter(vb);
		
		transfo.setOnAction((event) -> {
			
			Label question = new Label("Quelles transformation voulez-vous effectuez ?");
			BorderPane bptransfo = new BorderPane();
			bptransfo.setTop(question);
			BorderPane.setAlignment(question, Pos.CENTER);
			question.setPadding(new Insets(100,0,0,0));
			
			Button logarithme = new Button("Logarithme");	
			logarithme.setMaxSize(190, 50);
			Button boxcox = new Button("Box-Cox");
			boxcox.setMaxSize(190, 50);
			Button logistique = new Button("Logistique");
			logistique.setMaxSize(190, 50);
			Button mobile = new Button("Moyenne Mobile");
			mobile.setMaxSize(190, 50);
			Button pondere = new Button("Moyenne Mobile Pondéré");
			pondere.setMaxSize(190, 50);
			Button saison = new Button("Saisonnalité");
			saison.setMaxSize(190, 50);
			Button lineaire = new Button("Tendance Lineaire");
			lineaire.setMaxSize(190, 50);
			Button diff = new Button("Opérateur de différenciation");
			diff.setMaxSize(190, 50);
			
			logarithme.setOnAction((eventlog) ->{
				
			});
			
			VBox v = new VBox();
			v.getChildren().addAll(logarithme,boxcox,logistique,mobile,pondere,saison,lineaire,diff);
			v.setSpacing(10);
			v.setPadding(new Insets(0,0,90,0));
			v.setAlignment(Pos.CENTER);
			bptransfo.setCenter(v);
			Scene scenetransfo = new Scene(bptransfo);
			stage.setTitle("Menu");
			stage.setScene(scenetransfo);
			stage.setHeight(600);
			stage.setWidth(600);
			stage.show();
		});
		
		quitter.setOnAction((event) ->{
			stage.close();
		});
		
		Scene scene = new Scene(bp);
		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setHeight(600);
		stage.setWidth(600);
		stage.show();
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}
	
}
