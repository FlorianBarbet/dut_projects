import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Jouer2{
	
	Stage stage;
	Monde monde;
	Canvas canvas, canvas2;
	GraphicsContext gc, gc2;
	int tourEquipe = 0;
	int nbTour = 0;
	Robot selected;
	boolean IA1 = false;
	boolean IA2 = false;
	Click click;
	Animation finalBoom;
	Animation simpleBoom;
	
	/**
	 * Initialise toutes les fonctions graphiques
	 * @param equipes choix effectuer par les joueurs en terme de robot (piegeur, char ou tireur)
	 * @param x le nombre de case par ligne
	 * @param y le nombre de case par colonne
	 * @param percentObstacle le pourcentage d'obstacle
	 */
	
	// TODO recevoir les equipes dans une arraylist de string. soit "char", "tireur" ou "piegeur". 3 robot precisement
	public Scene start(ArrayList<ArrayList<String>> equipes, int x, int y, int percentObstacle){
		
		Pane root = new Pane();
		Scene scene = new Scene(root, 1000, 700, Color.WHITE);
		
		canvas = new Canvas(1000,500);
		canvas2 = new Canvas(1000,700);
		gc = canvas.getGraphicsContext2D();
		gc2 = canvas2.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,1000,500);
		gc2.setFill(Color.BLUE);
		gc2.fillRect(0, 500, 1000, 200);
		root.getChildren().addAll(canvas, canvas2);
		this.loadImages();
		monde = new Monde(gc, x, y, equipes, percentObstacle);
		monde.draw(tourEquipe);
		
		eventListener();
		
		return scene;
	
	}
	
	/**
	 * passe au tour suivant et fait les verifications necessaire (est-ce que la partie est termine ?)
	 */
	public void nextTour(){
		nbTour++;
		tourEquipe = tourEquipe == 0?1:0;
		gc2.setFill(tourEquipe==0?Color.BLUE:Color.RED);
		gc2.fillRect(0, 500, 1000, 200);
		int equipe = monde.getWinner();
		if(equipe != -1){
			System.out.println("L'equipe "+equipe+" a gagne");
		}
		monde.draw(tourEquipe);
	}
	
	public void eventListener(){
		canvas2.addEventHandler(MouseEvent.MOUSE_CLICKED, new Click());
		canvas2.addEventHandler(MouseEvent.MOUSE_MOVED, new infos());
	}
	

	/**
	 * 
	 * Class gerant les mouvement de la souris (toolTip)
	 *
	 */
	class infos implements EventHandler<MouseEvent>{
		public void handle(MouseEvent event){
			gc2.clearRect(0, 0, 1000, 500);
			Robot robot = monde.getRobotWithCoord((int)event.getX(), (int)event.getY());
			if(robot != null) drawToolTip((int)event.getX(), (int)event.getY(), robot.getTip()[0], robot.getTip()[1]);
		}
	}
	
	/**
	 * 
	 * Class gerant les cliques de la souris (clique gauche et clique droit)
	 *
	 */
	class Click implements EventHandler<MouseEvent>{
		public void handle(MouseEvent event){
			int x = (int)event.getX(); int y=(int)event.getY();
			gc2.clearRect(0, 0, 1000, 500);
			Element elem = monde.getElementWithCoord(x,y);
			Robot robot = monde.getRobotWithCoord(x,y);
			if(event.getButton() == MouseButton.PRIMARY){
				if(robot != null && robot.getEquipe() == tourEquipe){
					selected = robot;
					monde.draw(tourEquipe);
					monde.drawCases(robot, gc);
				}else{
					monde.draw(tourEquipe);
					selected = null;
				}
			}else if(event.getButton() == MouseButton.SECONDARY){
				if(selected == null) return;
				Tuple tuple = monde.getTuple(selected, monde.getXYWithCoord(x, y)[0], monde.getXYWithCoord(x, y)[1]);
				if(tuple == null) return;
				if(tuple.couleur == Tuple.ACTION){
					if(selected.getDamage(robot) > 0){
						simpleBoom.run((int)(robot.position.getRow()*1000.0/monde.getRow()), (int)(robot.position.getCol()*500.0/monde.getCol()), 250);
					}else{
						finalBoom.run((int)(robot.position.getRow()*1000.0/monde.getRow()), (int)(robot.position.getCol()*500.0/monde.getCol()), 250);
					}
					
					nextTour();
				}else if(tuple.couleur == Tuple.DEPLACEMENT){
					if(selected instanceof Piegeur){
						drawChoixPiegeur(tuple);
						canvas2.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
					}
					else{
						selected.deplacement(tuple.x-selected.position.getRow(), tuple.y-selected.position.getCol());
						monde.checkMine(selected);
						nextTour();
					}
				}
				
			}
		}
	}
	
	/**
	 * Dessin du toolTip
	 * @param x abscisse
	 * @param y ordonnee
	 * @param titre 
	 * @param texte Le texte a affiche
	 */
	private void drawToolTip(int x, int y, String titre, String texte){
		final int MAXLENGTH = 15;
		final int MAXSIZE = MAXLENGTH*8;
		final int HAUTEUR = 15;
		int i=0;
		
		String[] str = texte.split("@");
		ArrayList<String> toRelease = new ArrayList<String>();
		String totalString = "";
		for(i=0; i<str.length; i++){
			if((totalString.length() + str[i].length()) < MAXLENGTH){
				totalString+=str[i];
			}else{
				toRelease.add(totalString);
				totalString=str[i];
			}
		}
		toRelease.add(totalString);
		
		
		if(x>500) x-=MAXSIZE+20;
		if(y>250) y-=HAUTEUR*toRelease.size()+20;
		
		gc2.setFill(Color.YELLOW);
		gc2.fillRect(x, y, MAXSIZE+20, toRelease.size()*HAUTEUR + 20);
		gc2.setFill(Color.BLACK);
		for(i=0; i<toRelease.size(); i++){
			gc2.fillText(toRelease.get(i) ,x+10, y+20+i*HAUTEUR);
		}
	}
	
	/**
	 * Propose les differents choix au piegeur (deplacement ou minage)
	 * @param tuple
	 */
	public void drawChoixPiegeur(Tuple tuple){
		int i = 0;
		int x = (int)(tuple.x*1000.0/monde.getRow()+(1000.0/monde.getRow()/2));
		int y = (int)(tuple.y*500.0/monde.getCol()+(500.0/monde.getCol()/2));
		Image mine =  new Image(OSChemin.convert("images\\mine.png"), 50, 50, false, false);
		Image fleche =  new Image(OSChemin.convert("images\\fleche.png"), 50, 50, false, false);
		final int[][] positions = {{x-60, x+10, y, y},{x+10, x+10, y-60, y+10}, {x-60, x-60, y-60, y+10}};
		if(x<100) i=1;
		if(x>900) i=2;
		gc.drawImage(mine, positions[i][0], positions[i][2]);
		gc.drawImage(fleche, positions[i][1], positions[i][3]);
		canvas2.setUserData(i);
		canvas2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.PRIMARY){
					int x = (int)(event.getX()); int y = (int)(event.getY());
					int i = (int) canvas2.getUserData();
					if(x>positions[i][0] && x<positions[i][0]+50 && y>positions[i][2] && y<positions[i][2]+50){
						((Piegeur) selected).actionp(tuple.x-selected.position.getRow(), tuple.y-selected.position.getCol());
						canvas2.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
						canvas2.addEventHandler(MouseEvent.MOUSE_CLICKED, new Click());
						nextTour();
					}else if(x>positions[i][1] && x<positions[i][1]+50 && y>positions[i][3] && y<positions[i][3]+50){
						selected.deplacement(tuple.x-selected.position.getRow(), tuple.y-selected.position.getCol());
						monde.checkMine(selected);
						canvas2.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
						canvas2.addEventHandler(MouseEvent.MOUSE_CLICKED, new Click());
						nextTour();
					}else{
						System.out.println("hors champs");
						canvas2.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
						canvas2.addEventHandler(MouseEvent.MOUSE_CLICKED, new Click());
					}
				}
			}
		});
	}
	
	/**
	 * Charge les images pour les animations
	 */
	public void loadImages(){
		int i;
		ArrayList<Image> finalBoom = new ArrayList<Image>();
		for(i=1; i<=18; i++){
			finalBoom.add(new Image(OSChemin.convert("images\\finalBoom\\explo"+i+".png"), 20, 20, false, false));
		}
		this.finalBoom = new Animation(gc2, finalBoom);
		ArrayList<Image> simpleBoom = new ArrayList<Image>();
		for(i=1; i<=3; i++){
			simpleBoom.add(new Image(OSChemin.convert("images\\simpleBoom\\boom"+i+".png")));
		}
		this.simpleBoom = new Animation(gc2, simpleBoom);
	}
	
}