import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Game extends Application {
	private Scene mode;
	private Scene premier;
	private Scene deuxieme;
	private Scene jouer;
	private Button bouton8;
	private Button bouton9;
	private Button bouton11;
	private Button bouton82;
	private Button bouton92;
	private Button bouton112;
	private Button validation;
	private Button J2B;
	private AnchorPane premierJ;
	private AnchorPane deuxiemeJ;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayer2;
	private Stage stage2;
	private int check=0;
	private int check2=0;
	private int x;
	private int y;
	private int tose=0;
	private Stage stage;
	private int typerobot;
	private Scene regles;
	private Scene reglesSolo;
	private Scene reglesDuo;
	private Scene reglesAuto;
	private ArrayList<ArrayList<String>> equipes=new ArrayList<ArrayList<String>>();
	private ArrayList<String> equipe1=new ArrayList<String>();
	private ArrayList<String> equipe2=new ArrayList<String>();
	
	class ChoixRobots implements EventHandler<ActionEvent> {



		public void handle(ActionEvent event) {

			Image tireur= new Image("images\\tireur.png");
			Image tireurBar= new Image("images\\tireurbar.png");
			Image mineur = new Image("images\\mineur.png");
			Image mineurBar = new Image("images\\mineurbar.png");
			Image tank = new Image("images\\tank.png");
			Image tankBar = new Image("images\\tankbar.png");
			ImageView iv1 = new ImageView();



			if (event.getTarget() == bouton8 &&check==0) {
				typerobot=1;
				iv1.setImage(tireur);	
				ImageView tireurbar1=new ImageView(tireurBar);
				tireurbar1.setX(530);
				tireurbar1.setY(440);
				Rectangle tireurR = new Rectangle();
				tireurR.setX(536);
				tireurR.setY(400);
				tireurR.setWidth(300);
				tireurR.setHeight(120);
				tireurR.setFill(Color.web("#880015"));

				Label tireurL=new Label("Tireur");
				tireurL.setTranslateY(400);
				tireurL.setTranslateX(600);
				tireurL.setTextFill(Color.web("#d0d0d0"));
				tireurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 110.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==1){
							
						
							equipe1.add("tireur");
							final File file = new File("src\\sons\\tireur.mp3"); 
							final Media media = new Media(file.toURI().toString()); 
							final MediaPlayer mediaPlayer = new MediaPlayer(media); 
							mediaPlayer.play();
							Image tireur2= new Image("images\\tireur.png");
							ImageView iv2 = new ImageView();
							iv2.setImage(tireur2);	 	
							Ellipse ellipse = new Ellipse(); 
							DropShadow dS = new DropShadow();
							dS.setOffsetX(6.0);
							dS.setOffsetY(4.0);
							ellipse.setCenterX(410.0f);
							ellipse.setCenterY(275.0f);
							ellipse.setRadiusX(50.0f);
							ellipse.setRadiusY(25.0f);
							ellipse.setEffect(dS);
							ellipse.setFill(Color.web("00E200"));
							ellipse.setStroke(Color.web("#00CC00")); 
							ellipse.setOpacity(0.5);
							ellipse.setStrokeWidth(5); 
							premierJ.getChildren().addAll(iv2,ellipse);
							check=1;
						}
					}
				});
				premierJ.getChildren().addAll(iv1,tireurR,tireurL,validation,tireurbar1);
			} 
			else if(event.getTarget() == bouton9 && check==0){
				typerobot=2;
				iv1.setImage(mineur);
				ImageView mineurbar1=new ImageView(mineurBar);
				mineurbar1.setX(530);
				mineurbar1.setY(440);
				Rectangle mineurR = new Rectangle();
				mineurR.setX(536);
				mineurR.setY(400);
				mineurR.setWidth(300);
				mineurR.setHeight(120);
				mineurR.setFill(Color.web("#880015"));

				Label mineurL=new Label("Mineur");
				mineurL.setTranslateY(400);
				mineurL.setTranslateX(600);
				mineurL.setTextFill(Color.web("#d0d0d0"));
				mineurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 110.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==2){
						equipe1.add("mineur");
						final File file = new File("src\\sons\\mineur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image mineur= new Image("images\\mineur.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(mineur);	 	
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(410.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						premierJ.getChildren().addAll(iv2,ellipse);
						check=1;
						}
					}
				});

				premierJ.getChildren().addAll(iv1,mineurR,mineurL,validation,mineurbar1);

			}
			else if(event.getTarget() == bouton11 && check==0){
				typerobot=3;
				iv1.setImage(tank);
				ImageView tankbar1=new ImageView(tankBar);
				tankbar1.setX(530);
				tankbar1.setY(440);
				Rectangle tankR = new Rectangle();
				tankR.setX(536);
				tankR.setY(400);
				tankR.setWidth(300);
				tankR.setHeight(120);
				tankR.setFill(Color.web("#880015"));

				Label tankL=new Label("Tank");
				tankL.setTranslateY(400);
				tankL.setTranslateX(600);
				tankL.setTextFill(Color.web("#d0d0d0"));
				tankL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 110.0);

				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==3){
						equipe1.add("char");
						final File file = new File("src\\sons\\tank.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image tank= new Image("images\\tank.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(tank);	 	
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(410.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						premierJ.getChildren().addAll(iv2,ellipse);
						check=1;}
					}
				});

				premierJ.getChildren().addAll(iv1,tankR,tankL,validation,tankbar1);
			}

			if (event.getTarget() == bouton8 &&check==1) {
				typerobot=1;
				Image tireurM= new Image("images\\tireurM.png");

				ImageView tireurbar1=new ImageView(tireurBar);
				tireurbar1.setX(530);
				tireurbar1.setY(440);
				iv1.setImage(tireurM);	
				iv1.setX(279);
				Rectangle tireurR = new Rectangle();
				tireurR.setX(536);
				tireurR.setY(400);
				tireurR.setWidth(300);
				tireurR.setHeight(120);
				tireurR.setFill(Color.web("#880015"));

				Label tireurL=new Label("Tireur");
				tireurL.setTranslateY(400);
				tireurL.setTranslateX(600);
				tireurL.setTextFill(Color.web("#d0d0d0"));
				tireurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 370.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==1){
						equipe1.add("tireur");
						final File file = new File("src\\sons\\tireur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image tireur2= new Image("images\\tireurM.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(tireur2);	
						iv2.setX(279);
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(660.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						premierJ.getChildren().addAll(iv2,ellipse);
						check=2;}
					}
				});
				premierJ.getChildren().addAll(iv1,tireurR,tireurL,validation,tireurbar1);
			} 
			else if(event.getTarget() == bouton9 && check==1){
				
				typerobot=2;
				Image mineurM= new Image("images\\mineurM.png");
				ImageView mineurbar1=new ImageView(mineurBar);
				mineurbar1.setX(530);
				mineurbar1.setY(440);
				iv1.setImage(mineurM);
				iv1.setX(278);
				Rectangle mineurR = new Rectangle();
				mineurR.setX(536);
				mineurR.setY(400);
				mineurR.setWidth(300);
				mineurR.setHeight(120);
				mineurR.setFill(Color.web("#880015"));

				Label mineurL=new Label("Mineur");
				mineurL.setTranslateY(400);
				mineurL.setTranslateX(600);
				mineurL.setTextFill(Color.web("#d0d0d0"));
				mineurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 370.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==2){
						equipe1.add("mineur");
						final File file = new File("src\\sons\\mineur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image tireur2= new Image("images\\mineurM.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(tireur2);	 
						iv2.setX(278);
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(660.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						premierJ.getChildren().addAll(iv2,ellipse);
						check=2;}
					}
				});

				premierJ.getChildren().addAll(iv1,mineurR,mineurL,validation,mineurbar1);

			}
			else if(event.getTarget() == bouton11 && check==1){
				typerobot=3;
				Image tankM= new Image("images\\tankM.png");
				ImageView tankbar1=new ImageView(tankBar);
				tankbar1.setX(530);
				tankbar1.setY(440);
				iv1.setImage(tankM);
				iv1.setX(279);
				Rectangle tankR = new Rectangle();
				tankR.setX(536);
				tankR.setY(400);
				tankR.setWidth(300);
				tankR.setHeight(120);
				tankR.setFill(Color.web("#880015"));

				Label tankL=new Label("Tank");
				tankL.setTranslateY(400);
				tankL.setTranslateX(600);
				tankL.setTextFill(Color.web("#d0d0d0"));
				tankL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 370.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==3){
						equipe1.add("char");
						final File file = new File("src\\sons\\tank.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image tireur2= new Image("images\\tankM.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(tireur2);	 
						iv2.setX(279);
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(660.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						premierJ.getChildren().addAll(iv2,ellipse);
						check=2;}
					}
				});

				premierJ.getChildren().addAll(iv1,tankR,tankL,validation,tankbar1);
			}



			if (event.getTarget() == bouton8 &&check==2) {
				typerobot=1;
				Image tireurM2= new Image("images\\tireurM.png");
				ImageView tireurbar1=new ImageView(tireurBar);
				tireurbar1.setX(530);
				tireurbar1.setY(440);
				ImageView iv3 = new ImageView();
				iv3.setImage(tireurM2);	
				iv3.setX(526);
				Rectangle tireurR = new Rectangle();
				tireurR.setX(536);
				tireurR.setY(400);
				tireurR.setWidth(300);
				tireurR.setHeight(120);
				tireurR.setFill(Color.web("#880015"));

				Label tireurL=new Label("Tireur");
				tireurL.setTranslateY(400);
				tireurL.setTranslateX(600);
				tireurL.setTextFill(Color.web("#d0d0d0"));
				tireurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");


				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 620.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==1){
						equipe1.add("tireur");
						final File file = new File("src\\sons\\tireur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play(); 

						Image tireurM2= new Image("images\\tireurM.png");
						ImageView iv3 = new ImageView();
						iv3.setImage(tireurM2);	
						iv3.setX(526);


						check=3;
						premierJ.getChildren().addAll(iv3);
						Label J2 =new Label("Joueur 2 es-tu prêt ? ");

						J2.setLayoutX(280);
						J2.setLayoutY(80);
						J2.setFont(new Font("Arial", 28));
						J2.setTextFill(Color.web("#f4ff66"));
						J2B = new Button("Prêt !");


						J2B.setTextFill(Color.web("#f4ff66"));
						J2B.setLayoutX(350);
						J2B.setLayoutY(200);
						J2B.setOnAction(e->stage.setScene(deuxieme));
						premierJ.getChildren().addAll(J2,J2B);
						equipes.add(equipe1);
						}
					}
				});

				premierJ.getChildren().addAll(iv3,tireurR,tireurL,validation,tireurbar1);

			} 
			else if(event.getTarget() == bouton9 && check==2){
				typerobot=2;

				ImageView mineurbar1=new ImageView(mineurBar);
				mineurbar1.setX(530);
				mineurbar1.setY(440);
				Image mineurM2= new Image("images\\mineurM.png");
				ImageView iv3= new ImageView(mineurM2);
				iv3.setX(525);
				Rectangle mineurR = new Rectangle();
				mineurR.setX(536);
				mineurR.setY(400);
				mineurR.setWidth(300);
				mineurR.setHeight(120);
				mineurR.setFill(Color.web("#880015"));

				Label mineurL=new Label("Mineur");
				mineurL.setTranslateY(400);
				mineurL.setTranslateX(600);
				mineurL.setTextFill(Color.web("#d0d0d0"));
				mineurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 620.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==2){
							equipe1.add("mineur");
							equipes.add(equipe1);
						
						final File file = new File("src\\sons\\mineur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play(); 

						Image mineurM2= new Image("images\\mineurM.png");
						ImageView iv3= new ImageView(mineurM2);
						iv3.setX(525);
						check=3;
						premierJ.getChildren().addAll(iv3);
						Label J2 =new Label("Joueur 2 es-tu prêt ? ");

						J2.setLayoutX(280);
						J2.setLayoutY(80);
						J2.setFont(new Font("Arial", 28));
						J2.setTextFill(Color.web("#f4ff66"));
						J2B = new Button("Prêt !");


						J2B.setTextFill(Color.web("#f4ff66"));
						J2B.setLayoutX(350);
						J2B.setLayoutY(200);
						J2B.setOnAction(e->stage.setScene(deuxieme));
						premierJ.getChildren().addAll(J2,J2B);
					}}
				});

				premierJ.getChildren().addAll(iv3,mineurR,mineurL,validation,mineurbar1);

			}
			else if(event.getTarget() == bouton11 && check==2){
				typerobot=3;
				ImageView tankbar1=new ImageView(tankBar);
				tankbar1.setX(530);
				tankbar1.setY(440);
				Image tankM2= new Image("images\\tankM.png");
				ImageView iv3= new ImageView(tankM2);
				iv3.setX(526);
				Rectangle tankR = new Rectangle();
				tankR.setX(536);
				tankR.setY(400);
				tankR.setWidth(300);
				tankR.setHeight(120);
				tankR.setFill(Color.web("#880015"));

				Label tankL=new Label("Tank");
				tankL.setTranslateY(400);
				tankL.setTranslateX(600);
				tankL.setTextFill(Color.web("#d0d0d0"));
				tankL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 620.0);

				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==3){
					
						equipe1.add("char");
						final File file = new File("src\\sons\\tank.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play(); 

						Image tankM2= new Image("images\\tankM.png");
						ImageView iv3= new ImageView(tankM2);
						iv3.setX(526);

						check=3;
						premierJ.getChildren().addAll(iv3);
						Label J2 =new Label("Joueur 2 es-tu prêt ? ");

						J2.setLayoutX(280);
						J2.setLayoutY(80);
						J2.setFont(new Font("Arial", 28));
						J2.setTextFill(Color.web("#f4ff66"));
						J2B = new Button("Prêt !");


						J2B.setTextFill(Color.web("#f4ff66"));
						J2B.setLayoutX(350);
						J2B.setLayoutY(200);
						J2B.setOnAction(e->stage.setScene(deuxieme));
						premierJ.getChildren().addAll(J2,J2B);
						equipes.add(equipe1);}
					}
				});
				
				premierJ.getChildren().addAll(iv3,tankR,tankbar1,tankL,validation);
			}
			
			
		}
		
	}

	class ChoixRobots2 implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event){

			Image tireur= new Image("images\\tireur.png");
			Image tireurBar= new Image("images\\tireurbar.png");
			Image mineur = new Image("images\\mineur.png");
			Image mineurBar = new Image("images\\mineurbar.png");
			Image tank = new Image("images\\tank.png");
			Image tankBar = new Image("images\\tankbar.png");
			ImageView iv1 = new ImageView();



			if (event.getTarget() == bouton82 &&check2==0) {
				typerobot=1;
				iv1.setImage(tireur);	
				ImageView tireurbar1=new ImageView(tireurBar);
				tireurbar1.setX(530);
				tireurbar1.setY(440);
				Rectangle tireurR = new Rectangle();
				tireurR.setX(536);
				tireurR.setY(400);
				tireurR.setWidth(300);
				tireurR.setHeight(120);
				tireurR.setFill(Color.web("#880015"));

				Label tireurL=new Label("Tireur");
				tireurL.setTranslateY(400);
				tireurL.setTranslateX(600);
				tireurL.setTextFill(Color.web("#d0d0d0"));
				tireurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 110.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==1){
						equipe2.add("tireur");
						final File file = new File("src\\sons\\tireur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image tireur2= new Image("images\\tireur.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(tireur2);	 	
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(410.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						deuxiemeJ.getChildren().addAll(iv2,ellipse);
						check2=1;}
					}
				});
				deuxiemeJ.getChildren().addAll(iv1,tireurR,tireurL,validation,tireurbar1);
			} 
			else if(event.getTarget() == bouton92 && check2==0){
				typerobot=2;
				iv1.setImage(mineur);
				ImageView mineurbar1=new ImageView(mineurBar);
				mineurbar1.setX(530);
				mineurbar1.setY(440);
				Rectangle mineurR = new Rectangle();
				mineurR.setX(536);
				mineurR.setY(400);
				mineurR.setWidth(300);
				mineurR.setHeight(120);
				mineurR.setFill(Color.web("#880015"));

				Label mineurL=new Label("Mineur");
				mineurL.setTranslateY(400);
				mineurL.setTranslateX(600);
				mineurL.setTextFill(Color.web("#d0d0d0"));
				mineurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 110.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==2){
						equipe2.add("mineur");
						final File file = new File("src\\sons\\mineur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image mineur= new Image("images\\mineur.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(mineur);	 	
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(410.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						premierJ.getChildren().addAll(iv2,ellipse);
						check2=1;}
					}
				});

				deuxiemeJ.getChildren().addAll(iv1,mineurR,mineurL,validation,mineurbar1);

			}
			else if(event.getTarget() == bouton112 && check2==0){
				typerobot=3;
				iv1.setImage(tank);
				ImageView tankbar1=new ImageView(tankBar);
				tankbar1.setX(530);
				tankbar1.setY(440);
				Rectangle tankR = new Rectangle();
				tankR.setX(536);
				tankR.setY(400);
				tankR.setWidth(300);
				tankR.setHeight(120);
				tankR.setFill(Color.web("#880015"));

				Label tankL=new Label("Tank");
				tankL.setTranslateY(400);
				tankL.setTranslateX(600);
				tankL.setTextFill(Color.web("#d0d0d0"));
				tankL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 110.0);

				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==3){
						equipe2.add("char");
						final File file = new File("src\\sons\\tank.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image tank= new Image("images\\tank.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(tank);	 	
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(410.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						deuxiemeJ.getChildren().addAll(iv2,ellipse);
						check2=1;}
					}
				});

				deuxiemeJ.getChildren().addAll(iv1,tankR,tankL,validation,tankbar1);
			}

			if (event.getTarget() == bouton82 &&check2==1) {
				typerobot=1;
				Image tireurM= new Image("images\\tireurM.png");

				ImageView tireurbar1=new ImageView(tireurBar);
				tireurbar1.setX(530);
				tireurbar1.setY(440);
				iv1.setImage(tireurM);	
				iv1.setX(279);
				Rectangle tireurR = new Rectangle();
				tireurR.setX(536);
				tireurR.setY(400);
				tireurR.setWidth(300);
				tireurR.setHeight(120);
				tireurR.setFill(Color.web("#880015"));

				Label tireurL=new Label("Tireur");
				tireurL.setTranslateY(400);
				tireurL.setTranslateX(600);
				tireurL.setTextFill(Color.web("#d0d0d0"));
				tireurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 370.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==1){
						equipe2.add("tireur");
						final File file = new File("src\\sons\\tireur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image tireur2= new Image("images\\tireurM.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(tireur2);	
						iv2.setX(279);
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(660.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						deuxiemeJ.getChildren().addAll(iv2,ellipse);
						check2=2;}
					}
				});
				deuxiemeJ.getChildren().addAll(iv1,tireurR,tireurL,validation,tireurbar1);
			} 
			else if(event.getTarget() == bouton92 && check2==1){
				typerobot=2;
				Image mineurM= new Image("images\\mineurM.png");
				ImageView mineurbar1=new ImageView(mineurBar);
				mineurbar1.setX(530);
				mineurbar1.setY(440);
				iv1.setImage(mineurM);
				iv1.setX(278);
				Rectangle mineurR = new Rectangle();
				mineurR.setX(536);
				mineurR.setY(400);
				mineurR.setWidth(300);
				mineurR.setHeight(120);
				mineurR.setFill(Color.web("#880015"));

				Label mineurL=new Label("Mineur");
				mineurL.setTranslateY(400);
				mineurL.setTranslateX(600);
				mineurL.setTextFill(Color.web("#d0d0d0"));
				mineurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 370.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==2){
						equipe2.add("mineur");
						final File file = new File("src\\sons\\mineur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image tireur2= new Image("images\\mineurM.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(tireur2);	 
						iv2.setX(278);
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(660.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						deuxiemeJ.getChildren().addAll(iv2,ellipse);
						check2=2;}
					}
				});

				deuxiemeJ.getChildren().addAll(iv1,mineurR,mineurL,validation,mineurbar1);

			}
			else if(event.getTarget() == bouton112 && check2==1){
				typerobot=3;
				Image tankM= new Image("images\\tankM.png");
				ImageView tankbar1=new ImageView(tankBar);
				tankbar1.setX(530);
				tankbar1.setY(440);
				iv1.setImage(tankM);
				iv1.setX(279);
				Rectangle tankR = new Rectangle();
				tankR.setX(536);
				tankR.setY(400);
				tankR.setWidth(300);
				tankR.setHeight(120);
				tankR.setFill(Color.web("#880015"));

				Label tankL=new Label("Tank");
				tankL.setTranslateY(400);
				tankL.setTranslateX(600);
				tankL.setTextFill(Color.web("#d0d0d0"));
				tankL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 370.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==3){
						equipe2.add("char");
						final File file = new File("src\\sons\\tank.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play();
						Image tireur2= new Image("images\\tankM.png");
						ImageView iv2 = new ImageView();
						iv2.setImage(tireur2);	 
						iv2.setX(279);
						Ellipse ellipse = new Ellipse(); 
						DropShadow dS = new DropShadow();
						dS.setOffsetX(6.0);
						dS.setOffsetY(4.0);
						ellipse.setCenterX(660.0f);
						ellipse.setCenterY(275.0f);
						ellipse.setRadiusX(50.0f);
						ellipse.setRadiusY(25.0f);
						ellipse.setEffect(dS);
						ellipse.setFill(Color.web("00E200"));
						ellipse.setStroke(Color.web("#00CC00")); 
						ellipse.setOpacity(0.5);
						ellipse.setStrokeWidth(5); 
						deuxiemeJ.getChildren().addAll(iv2,ellipse);
						check2=2;}
					}
				});

				deuxiemeJ.getChildren().addAll(iv1,tankR,tankL,validation,tankbar1);
			}



			if (event.getTarget() == bouton82 &&check2==2) {
				typerobot=1;
				Image tireurM2= new Image("images\\tireurM.png");
				ImageView tireurbar1=new ImageView(tireurBar);
				tireurbar1.setX(530);
				tireurbar1.setY(440);
				ImageView iv3 = new ImageView();
				iv3.setImage(tireurM2);	
				iv3.setX(526);
				Rectangle tireurR = new Rectangle();
				tireurR.setX(536);
				tireurR.setY(400);
				tireurR.setWidth(300);
				tireurR.setHeight(120);
				tireurR.setFill(Color.web("#880015"));

				Label tireurL=new Label("Tireur");
				tireurL.setTranslateY(400);
				tireurL.setTranslateX(600);
				tireurL.setTextFill(Color.web("#d0d0d0"));
				tireurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");


				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 620.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==1){
						equipe2.add("tireur");
						equipes.add(equipe2);
						final File file = new File("src\\sons\\tireur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play(); 

						Image tireurM2= new Image("images\\tireurM.png");
						ImageView iv3 = new ImageView();
						iv3.setImage(tireurM2);	
						iv3.setX(526);
						
						
						
						
						Stage modal=new Stage();
						modal.initStyle(StageStyle.UNDECORATED);
						modal.initOwner(stage);
						modal.initModality(Modality.WINDOW_MODAL);
						VBox fen=new VBox();
						fen.setId("modal");
						Button ok=new Button("Valider");
						ok.setLayoutX(100);
						ok.setLayoutY(230);
						ok.setOnAction(events->modal.close());
						
						Label nbrQ = new Label("Taux d'obstacle:");
						nbrQ.setId("taux");
						nbrQ.setFont(new Font("Arial", 20));
						
						
						TextField nbr = new TextField(tose+"");
						nbr.setFont(new Font("Arial", 16));
						nbr.setId("taux");
						nbr.setAlignment(Pos.CENTER_RIGHT);
						TextField nbr1 = new TextField("%");
						nbr1.setId("taux");
						nbr1.setAlignment(Pos.CENTER_LEFT);
						
						Label tailleC = new Label("Taille de la carte");
						tailleC.setId("taux");
						tailleC.setFont(new Font("Arial", 20));
						Button huit=new Button("8*5");
				
						Button douze=new Button("12*9");
						
						Button seize=new Button("16*13");
						huit.setOnMouseClicked(e->{huit.setId("BoutonModifie");douze.setId("taille");seize.setId("taille");x=8;y=5;});
						douze.setOnMouseClicked(e->{douze.setId("BoutonModifie");huit.setId("taille");seize.setId("taille");x=12;y=9;});
						seize.setOnMouseClicked(e->{seize.setId("BoutonModifie");douze.setId("taille");huit.setId("taille");x=16;y=13;});
						huit.setId("taille");	
						douze.setId("taille");
						seize.setId("taille");
						Button Bplus=new Button("+");	
						Bplus.setId("Bplus");
						Bplus.setOnMouseClicked(e->{tose+=1;nbr.setText(tose+"");if(tose<=0){
							tose=0;
						}
						else if(tose>=30){
							tose=30;
						}});
						Button Bmoins=new Button("-");
						Bmoins.setOnMouseClicked(e->{tose-=1;nbr.setText(tose+"");if(tose<=0){
							tose=0;
						}
						else if(tose>=30){
							tose=30;
						}});
						Bmoins.setId("Bplus");
					
						
						HBox h1=new HBox();
						h1.getChildren().addAll(huit,douze,seize);
						HBox h2=new HBox();
						h2.getChildren().addAll(nbr,nbr1);
						HBox h3=new HBox();
						h3.getChildren().addAll(Bmoins,Bplus);
						h3.setSpacing(5);
						h1.setAlignment(Pos.CENTER);
						h1.setSpacing(5);
						h2.setAlignment(Pos.CENTER);
						h3.setAlignment(Pos.CENTER);
						h1.setPadding(new Insets(10, 10, 10, 10)); 
						h2.setPadding(new Insets(10, 10, 10, 10)); 
						h3.setPadding(new Insets(10, 10, 10, 10)); 
						
						fen.getChildren().addAll(tailleC,h1,nbrQ,h2,h3,ok);
						fen.setAlignment(Pos.CENTER);
					
						Scene scene=new Scene(fen,300,300);
						modal.setScene(scene);
						modal.show();
						
						check2=3;
						deuxiemeJ.getChildren().addAll(iv3);
						ok.addEventHandler(ActionEvent.ACTION, (e)->{
							mediaPlayer.stop();
							modal.close();

							stage.setScene(new Jouer2().start(equipes,x,y,tose));
							
						});}
					}
				});
				deuxiemeJ.getChildren().addAll(iv3,tireurR,tireurL,validation,tireurbar1);
			} 
			else if(event.getTarget() == bouton92 && check2==2){
				typerobot=2;

				ImageView mineurbar1=new ImageView(mineurBar);
				mineurbar1.setX(530);
				mineurbar1.setY(440);
				Image mineurM2= new Image("images\\mineurM.png");
				ImageView iv3= new ImageView(mineurM2);
				iv3.setX(525);
				Rectangle mineurR = new Rectangle();
				mineurR.setX(536);
				mineurR.setY(400);
				mineurR.setWidth(300);
				mineurR.setHeight(120);
				mineurR.setFill(Color.web("#880015"));

				Label mineurL=new Label("Mineur");
				mineurL.setTranslateY(400);
				mineurL.setTranslateX(600);
				mineurL.setTextFill(Color.web("#d0d0d0"));
				mineurL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 620.0);
				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==2){
						equipe2.add("mineur");
						equipes.add(equipe2);
						final File file = new File("src\\sons\\mineur.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play(); 

						Image mineurM2= new Image("images\\mineurM.png");
						ImageView iv3= new ImageView(mineurM2);
						iv3.setX(525);
						
						
						Stage modal=new Stage();
						modal.initStyle(StageStyle.UNDECORATED);
						modal.initOwner(stage);
						modal.initModality(Modality.WINDOW_MODAL);
						VBox fen=new VBox();
						fen.setId("modal");
						Button ok=new Button("Valider");
						ok.setLayoutX(100);
						ok.setLayoutY(230);
						ok.setOnAction(events->modal.close());
						
						Label nbrQ = new Label("Taux d'obstacle:");
						nbrQ.setId("taux");
						nbrQ.setFont(new Font("Arial", 20));
						
						
						TextField nbr = new TextField(tose+"");
						nbr.setFont(new Font("Arial", 16));
						nbr.setId("taux");
						nbr.setAlignment(Pos.CENTER_RIGHT);
						TextField nbr1 = new TextField("%");
						nbr1.setId("taux");
						nbr1.setAlignment(Pos.CENTER_LEFT);
						
						Label tailleC = new Label("Taille de la carte");
						tailleC.setId("taux");
						tailleC.setFont(new Font("Arial", 20));
						Button huit=new Button("8*5");
				
						Button douze=new Button("12*9");
						
						Button seize=new Button("16*13");
						huit.setOnMouseClicked(e->{huit.setId("BoutonModifie");douze.setId("taille");seize.setId("taille");x=8;y=5;});
						douze.setOnMouseClicked(e->{douze.setId("BoutonModifie");huit.setId("taille");seize.setId("taille");x=12;y=9;});
						seize.setOnMouseClicked(e->{seize.setId("BoutonModifie");douze.setId("taille");huit.setId("taille");x=16;y=13;});
						huit.setId("taille");	
						douze.setId("taille");
						seize.setId("taille");
						Button Bplus=new Button("+");	
						Bplus.setId("Bplus");
						Bplus.setOnMouseClicked(e->{tose+=1;nbr.setText(tose+"");if(tose<=0){
							tose=0;
						}
						else if(tose>=30){
							tose=30;
						}});
						Button Bmoins=new Button("-");
						Bmoins.setOnMouseClicked(e->{tose-=1;nbr.setText(tose+"");if(tose<=0){
							tose=0;
						}
						else if(tose>=30){
							tose=30;
						}});
						Bmoins.setId("Bplus");
					
						
						HBox h1=new HBox();
						h1.getChildren().addAll(huit,douze,seize);
						HBox h2=new HBox();
						h2.getChildren().addAll(nbr,nbr1);
						HBox h3=new HBox();
						h3.getChildren().addAll(Bmoins,Bplus);
						h3.setSpacing(5);
						h1.setAlignment(Pos.CENTER);
						h1.setSpacing(5);
						h2.setAlignment(Pos.CENTER);
						h3.setAlignment(Pos.CENTER);
						h1.setPadding(new Insets(10, 10, 10, 10)); 
						h2.setPadding(new Insets(10, 10, 10, 10)); 
						h3.setPadding(new Insets(10, 10, 10, 10)); 
						
						fen.getChildren().addAll(tailleC,h1,nbrQ,h2,h3,ok);
						fen.setAlignment(Pos.CENTER);
					
						Scene scene=new Scene(fen,300,300);
						modal.setScene(scene);
						modal.show();
						
						check2=3;
						deuxiemeJ.getChildren().addAll(iv3);
						ok.addEventHandler(ActionEvent.ACTION, (e)->{
							mediaPlayer.stop();
							modal.close();
							stage.setScene(new Jouer2().start(equipes,x,y,tose));
						});}
					}
				});

				deuxiemeJ.getChildren().addAll(iv3,mineurR,mineurL,validation,mineurbar1);

			}
			else if(event.getTarget() == bouton112 && check2==2){
				typerobot=3;
				ImageView tankbar1=new ImageView(tankBar);
				tankbar1.setX(530);
				tankbar1.setY(440);
				Image tankM2= new Image("images\\tankM.png");
				ImageView iv3= new ImageView(tankM2);
				iv3.setX(526);
				Rectangle tankR = new Rectangle();
				tankR.setX(536);
				tankR.setY(400);
				tankR.setWidth(300);
				tankR.setHeight(120);
				tankR.setFill(Color.web("#880015"));

				Label tankL=new Label("Tank");
				tankL.setTranslateY(400);
				tankL.setTranslateX(600);
				tankL.setTextFill(Color.web("#d0d0d0"));
				tankL.setFont(new Font("Arial", 28));

				validation=new Button("Recruter");
				validation.setId("validation");
				AnchorPane.setTopAnchor(validation, 330.0);
				AnchorPane.setLeftAnchor(validation, 620.0);

				validation.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(typerobot==3){
							
						equipe2.add("char");
						equipes.add(equipe2);
						final File file = new File("src\\sons\\tank.mp3"); 
						final Media media = new Media(file.toURI().toString()); 
						final MediaPlayer mediaPlayer = new MediaPlayer(media); 
						mediaPlayer.play(); 

						Image tankM2= new Image("images\\tankM.png");
						ImageView iv3= new ImageView(tankM2);
						iv3.setX(526);

						
						Stage modal=new Stage();
						modal.initStyle(StageStyle.UNDECORATED);
						modal.initOwner(stage);
						modal.initModality(Modality.WINDOW_MODAL);
						VBox fen=new VBox();
						fen.setId("modal");
						Button ok=new Button("Valider");
						ok.setLayoutX(100);
						ok.setLayoutY(230);
						ok.setOnAction(events->modal.close());
						
						Label nbrQ = new Label("Taux d'obstacle:");
						nbrQ.setId("taux");
						nbrQ.setFont(new Font("Arial", 20));
						
						
						TextField nbr = new TextField(tose+"");
						nbr.setFont(new Font("Arial", 16));
						nbr.setId("taux");
						nbr.setAlignment(Pos.CENTER_RIGHT);
						TextField nbr1 = new TextField("%");
						nbr1.setId("taux");
						nbr1.setAlignment(Pos.CENTER_LEFT);
						
						Label tailleC = new Label("Taille de la carte");
						tailleC.setId("taux");
						tailleC.setFont(new Font("Arial", 20));
						Button huit=new Button("8*5");
				
						Button douze=new Button("12*9");
						
						Button seize=new Button("16*13");
						huit.setOnMouseClicked(e->{huit.setId("BoutonModifie");douze.setId("taille");seize.setId("taille");x=8;y=5;});
						douze.setOnMouseClicked(e->{douze.setId("BoutonModifie");huit.setId("taille");seize.setId("taille");x=12;y=9;});
						seize.setOnMouseClicked(e->{seize.setId("BoutonModifie");douze.setId("taille");huit.setId("taille");x=16;y=13;});
						huit.setId("taille");	
						douze.setId("taille");
						seize.setId("taille");
						Button Bplus=new Button("+");	
						Bplus.setId("Bplus");
						Bplus.setOnMouseClicked(e->{tose+=1;nbr.setText(tose+"");if(tose<=0){
							tose=0;
						}
						else if(tose>=30){
							tose=30;
						}});
						Button Bmoins=new Button("-");
						Bmoins.setOnMouseClicked(e->{tose-=1;nbr.setText(tose+"");if(tose<=0){
							tose=0;
						}
						else if(tose>=30){
							tose=30;
						}});
						Bmoins.setId("Bplus");
					
						
						HBox h1=new HBox();
						h1.getChildren().addAll(huit,douze,seize);
						HBox h2=new HBox();
						h2.getChildren().addAll(nbr,nbr1);
						HBox h3=new HBox();
						h3.getChildren().addAll(Bmoins,Bplus);
						h3.setSpacing(5);
						h1.setAlignment(Pos.CENTER);
						h1.setSpacing(5);
						h2.setAlignment(Pos.CENTER);
						h3.setAlignment(Pos.CENTER);
						h1.setPadding(new Insets(10, 10, 10, 10)); 
						h2.setPadding(new Insets(10, 10, 10, 10)); 
						h3.setPadding(new Insets(10, 10, 10, 10)); 
						
						fen.getChildren().addAll(tailleC,h1,nbrQ,h2,h3,ok);
						fen.setAlignment(Pos.CENTER);
					
						Scene scene=new Scene(fen,300,300);
						modal.setScene(scene);
						modal.show();
						
						check2=3;
						deuxiemeJ.getChildren().addAll(iv3);
						ok.addEventHandler(ActionEvent.ACTION, (e)->{
							mediaPlayer.stop();
							modal.close();
							stage.setScene(new Jouer2().start(equipes,x,y,tose));

						});
						check2=3;}
					}
				});

				deuxiemeJ.getChildren().addAll(iv3,tankR,tankbar1,tankL,validation);
			}

		}
		
	}

	
	public void start(Stage stage1){
		final File file = new File("src\\sons\\war.mp3"); 
		final Media media = new Media(file.toURI().toString()); 
		mediaPlayer = new MediaPlayer(media); 
		mediaPlayer.play(); 
		stage = stage1;

		//***************Accueil*********************
		AnchorPane accueilP =new AnchorPane();
		accueilP.setId("accueilP");
		VBox vbox=new VBox();
		
		
		Scene accueil=new Scene(accueilP,800,538);

		Button bouton1=new Button("Jouer");
		bouton1.setOnAction(e->stage.setScene(mode));
		
		
		Button bouton2=new Button("Régles");
		bouton2.setOnAction(e->stage.setScene(regles));
		Button bouton3=new Button("Quitter");
		bouton3.setOnAction(e->stage.close());
		
		AnchorPane.setTopAnchor(vbox, 280.0);
		AnchorPane.setRightAnchor(vbox, 260.0);
		bouton1.setMaxWidth(Double.MAX_VALUE);
		bouton2.setMaxWidth(Double.MAX_VALUE);
		bouton3.setMaxWidth(Double.MAX_VALUE);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(0, 20, 10, 20)); 


		vbox.getChildren().addAll(bouton1,bouton2,bouton3);
		accueilP.getChildren().addAll(vbox);
		
//***************Regles*******************
		AnchorPane reglesDuJeu=new AnchorPane();

		regles =new Scene(reglesDuJeu,800,538);
		reglesDuJeu.setId("reglesDuJeu");

		Button bouton12=new Button("Retour");
		bouton12.setOnAction(e->stage.setScene(accueil));
		AnchorPane.setTopAnchor(bouton12, 465.0);
		AnchorPane.setLeftAnchor(bouton12, 30.0);

		Button bouton13=new Button("  Règles\n" + "       1\n" + "  Joueur\n");
		bouton13.setOnAction(e->stage.setScene(reglesSolo));
		bouton13.setId("solo");	
		AnchorPane.setTopAnchor(bouton13, 150.0);
		AnchorPane.setLeftAnchor(bouton13, /*26.0*/37.0);
		bouton13.setMinHeight(200.0);
		bouton13.setMinWidth(200.0);

		Button bouton14=new Button("  Règles\n" + "       2\n" + "  Joueurs\n");
		bouton14.setOnAction(e->stage.setScene(reglesDuo));
		bouton14.setId("duo");	
		AnchorPane.setTopAnchor(bouton14, 150.0);
		AnchorPane.setLeftAnchor(bouton14, /*286.0*/ 297.0);
		bouton14.setMinHeight(200.0);
		bouton14.setMinWidth(200.0);

		Button bouton15=new Button("  Règles\n" + "       0\n" + "  Joueurs\n");
		bouton15.setOnAction(e->stage.setScene(reglesAuto));
		bouton15.setId("auto");	
		AnchorPane.setTopAnchor(bouton15, 150.0);
		AnchorPane.setLeftAnchor(bouton15, /*546.0*/ 557.0);
		bouton15.setMinHeight(200.0);
		bouton15.setMinWidth(200.0);

		reglesDuJeu.getChildren().addAll(bouton12,bouton13,bouton14,bouton15);

		//***************ReglesSolo***************
		AnchorPane reglesModeSolo=new AnchorPane();

		reglesSolo=new Scene(reglesModeSolo,800,538);
		reglesModeSolo.setId("reglesModeSolo");

		Button bouton16=new Button("Retour");
		bouton16.setOnAction(e->stage.setScene(regles));
		AnchorPane.setTopAnchor(bouton16, 465.0);
		AnchorPane.setLeftAnchor(bouton16, 30.0);


		reglesModeSolo.getChildren().addAll(bouton16);

		//***************ReglesDuo****************
		AnchorPane reglesModeDuo=new AnchorPane();

		reglesDuo=new Scene(reglesModeDuo,800,538);
		reglesModeDuo.setId("reglesModeDuo");

		Button bouton17=new Button("Retour");
		bouton17.setOnAction(e->stage.setScene(regles));
		AnchorPane.setTopAnchor(bouton17, 465.0);
		AnchorPane.setLeftAnchor(bouton17, 30.0);

		Label labelDuo=new Label("Règles en mode auto:\n"+"Lorem ipsum dolor sit amet,"
				+ " consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n "
				+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n"
				+ " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n"
				+ " Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n");
		reglesModeDuo.getChildren().addAll(bouton17);

		//***************ReglesAuto***************
		AnchorPane reglesModeAuto=new AnchorPane();

		reglesAuto=new Scene(reglesModeAuto,800,538);
		reglesModeAuto.setId("reglesModeAuto");

		Button bouton18=new Button("Retour");
		bouton18.setOnAction(e->stage.setScene(regles));
		AnchorPane.setTopAnchor(bouton18, 465.0);
		AnchorPane.setLeftAnchor(bouton18, 30.0);

		Label labelAuto=new Label("Règles en mode duo:\n"+"Lorem ipsum dolor sit amet,"
				+ " consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n "
				+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n"
				+ " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n"
				+ " Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n");
		reglesModeAuto.getChildren().addAll(bouton18);


		//***************Mode*********************
		AnchorPane modeDeJeu=new AnchorPane();

		mode =new Scene(modeDeJeu,800,538);
		modeDeJeu.setId("modeDeJeu");

		Button bouton4=new Button("Retour");
		bouton4.setOnAction(e->stage.setScene(accueil));
		AnchorPane.setTopAnchor(bouton4, 465.0);
		AnchorPane.setLeftAnchor(bouton4, 30.0);


		Button bouton5=new Button("  2 Joueurs");
		bouton5.setOnAction(e->stage.setScene(premier));
		bouton5.setId("unVSun");	
		AnchorPane.setTopAnchor(bouton5, 27.0);
		AnchorPane.setLeftAnchor(bouton5, 26.0);
		bouton5.setMinHeight(425.0);
		bouton5.setMinWidth(230.0);

		Button bouton6=new Button("  1 Joueurs");
		bouton6.setId("unVSia");	
		AnchorPane.setTopAnchor(bouton6, 27.0);
		AnchorPane.setLeftAnchor(bouton6, 286.0);
		bouton6.setMinHeight(425.0);
		bouton6.setMinWidth(231.0);

		Button bouton7=new Button("0 Joueur");
		bouton7.setId("iaVSia");	
		AnchorPane.setTopAnchor(bouton7, 27.0);
		AnchorPane.setLeftAnchor(bouton7, 546.0);
		bouton7.setMinHeight(425.0);
		bouton7.setMinWidth(231.0);

		modeDeJeu.getChildren().addAll(bouton4,bouton5,bouton6,bouton7);


		//*******1vs1****************


		premierJ=new AnchorPane();
		premier =new Scene(premierJ,800,538);
		premierJ.setId("premierJ");

		Ellipse ellipse = new Ellipse(); 
		DropShadow dS = new DropShadow();
		dS.setOffsetX(6.0);
		dS.setOffsetY(4.0);
		ellipse.setCenterX(140.0f);
		ellipse.setCenterY(275.0f);
		ellipse.setRadiusX(50.0f);
		ellipse.setRadiusY(25.0f);
		ellipse.setEffect(dS);
		ellipse.setFill(Color.web("00E200"));
		ellipse.setStroke(Color.web("#00CC00")); 
		ellipse.setOpacity(0.5);
		ellipse.setStrokeWidth(5); 


		Label choix=new Label("Choisissez vos robots:\n           Joueur 1");
		choix.setTranslateX(20);
		choix.setTranslateY(420);
		choix.setFont(new Font("Arial", 20));
		choix.setTextFill(Color.web("#d0d0d0"));

		Button bouton10=new Button("Retour");
		bouton10.setOnAction(e->stage.setScene(mode));
		AnchorPane.setTopAnchor(bouton10, 470.0);
		AnchorPane.setLeftAnchor(bouton10, 30.0);

		bouton8=new Button();
		bouton8.setId("tireur");
		Rectangle tireurR = new Rectangle();
		tireurR.setX(236);
		tireurR.setY(416);
		tireurR.setWidth(78);
		tireurR.setHeight(78);
		tireurR.setArcWidth(10);
		tireurR.setArcHeight(10);
		AnchorPane.setTopAnchor(bouton8, 420.0);
		AnchorPane.setLeftAnchor(bouton8, 240.0);
		bouton8.setMinHeight(70.0);
		bouton8.setMinWidth(70.0);
		bouton8.addEventHandler(ActionEvent.ACTION, new ChoixRobots());

		bouton9=new Button();
		bouton9.setId("mineur");
		Rectangle mineurR = new Rectangle();
		mineurR.setX(326);
		mineurR.setY(416);
		mineurR.setWidth(78);
		mineurR.setHeight(78);
		mineurR.setArcWidth(10);
		mineurR.setArcHeight(10);
		AnchorPane.setTopAnchor(bouton9, 420.0);
		AnchorPane.setLeftAnchor(bouton9, 330.0);
		bouton9.setMinHeight(70.0);
		bouton9.setMinWidth(70.0);
		bouton9.addEventHandler(ActionEvent.ACTION, new ChoixRobots());

		bouton11=new Button();
		bouton11.setId("tank");	
		Rectangle tankR = new Rectangle();
		tankR.setX(416);
		tankR.setY(416);
		tankR.setWidth(78);
		tankR.setHeight(78);
		tankR.setArcWidth(10);
		tankR.setArcHeight(10);
		AnchorPane.setTopAnchor(bouton11, 420.0);
		AnchorPane.setLeftAnchor(bouton11, 420.0);
		bouton11.setMinHeight(70.0);
		bouton11.setMinWidth(70.0);
		bouton11.addEventHandler(ActionEvent.ACTION, new ChoixRobots());

		premierJ.getChildren().addAll(ellipse,choix,tireurR,mineurR,tankR,bouton8,bouton9,bouton10,bouton11);

		//********************** 1vs1 2 joueurs******************//
		deuxiemeJ=new AnchorPane();
		deuxieme =new Scene(deuxiemeJ,800,538);
		deuxiemeJ.setId("deuxiemeJ");

		Ellipse ellipse2 = new Ellipse(); 
		DropShadow dS2 = new DropShadow();
		dS2.setOffsetX(6.0);
		dS2.setOffsetY(4.0);
		ellipse2.setCenterX(140.0f);
		ellipse2.setCenterY(275.0f);
		ellipse2.setRadiusX(50.0f);
		ellipse2.setRadiusY(25.0f);
		ellipse2.setEffect(dS);
		ellipse2.setFill(Color.web("00E200"));
		ellipse2.setStroke(Color.web("#00CC00")); 
		ellipse2.setOpacity(0.5);
		ellipse2.setStrokeWidth(5); 


		Label choix2=new Label("Choisissez vos robots:\n           Joueur 2");
		choix2.setTranslateX(20);
		choix2.setTranslateY(420);
		choix2.setFont(new Font("Arial", 20));
		choix2.setTextFill(Color.web("#d0d0d0"));

		Button bouton102=new Button("Retour");
		bouton102.setOnAction(e->stage.setScene(mode));
		AnchorPane.setTopAnchor(bouton102, 470.0);
		AnchorPane.setLeftAnchor(bouton102, 30.0);

		bouton82=new Button();
		bouton82.setId("tireur");
		Rectangle tireurR2 = new Rectangle();
		tireurR2.setX(236);
		tireurR2.setY(416);
		tireurR2.setWidth(78);
		tireurR2.setHeight(78);
		tireurR2.setArcWidth(10);
		tireurR2.setArcHeight(10);
		AnchorPane.setTopAnchor(bouton82, 420.0);
		AnchorPane.setLeftAnchor(bouton82, 240.0);
		bouton82.setMinHeight(70.0);
		bouton82.setMinWidth(70.0);
		bouton82.addEventHandler(ActionEvent.ACTION, new ChoixRobots2());

		bouton92=new Button();
		bouton92.setId("mineur");
		Rectangle mineurR2 = new Rectangle();
		mineurR2.setX(326);
		mineurR2.setY(416);
		mineurR2.setWidth(78);
		mineurR2.setHeight(78);
		mineurR2.setArcWidth(10);
		mineurR2.setArcHeight(10);
		AnchorPane.setTopAnchor(bouton92, 420.0);
		AnchorPane.setLeftAnchor(bouton92, 330.0);
		bouton92.setMinHeight(70.0);
		bouton92.setMinWidth(70.0);
		bouton92.addEventHandler(ActionEvent.ACTION, new ChoixRobots2());

		bouton112=new Button();
		bouton112.setId("tank");	
		Rectangle tankR2 = new Rectangle();
		tankR2.setX(416);
		tankR2.setY(416);
		tankR2.setWidth(78);
		tankR2.setHeight(78);
		tankR2.setArcWidth(10);
		tankR2.setArcHeight(10);
		AnchorPane.setTopAnchor(bouton112, 420.0);
		AnchorPane.setLeftAnchor(bouton112, 420.0);
		bouton112.setMinHeight(70.0);
		bouton112.setMinWidth(70.0);
		bouton112.addEventHandler(ActionEvent.ACTION, new ChoixRobots2());

		deuxiemeJ.getChildren().addAll(ellipse2,choix2,tireurR2,mineurR2,tankR2,bouton82,bouton92,bouton102,bouton112);


		stage.setResizable(false);
		stage.setScene(accueil);

		Application.setUserAgentStylesheet(getClass().getResource("stylesheet\\Deco.css").toExternalForm());
		stage.setTitle("Virtual War");
		stage.show();


	}


	public static void main(String[] args) {
		Application.launch(args);
	}
}
