import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Animation {
	
	GraphicsContext gc;
	ArrayList<Image> images;
	
	/**
	 * Permet d'initialiser une animation. Cette fonction ne fonctionne pas et n'est pas termine.
	 * @param gc Le GraphicsContext sur laquelle l'animation sera dessine
	 * @param images L'ArrayList d'image dans l'ordre chronologique correspondant a l'animation.
	 */
	public Animation(GraphicsContext gc, ArrayList<Image> images){
		this.gc = gc;
		this.images = images;
	}
	
	/**
	 * Fait l'anilation
	 * @param x l'abcsisse a laquelle l'animation est fait
	 * @param y l'ordonne a laquelle l'animation est fait
	 * @param ms le temps entre chaque frame de l'animation
	 */
	public void run(int x, int y, int ms){
		int i = 0;
		Instant first = Instant.now();
		gc.drawImage(images.get(i), x, y);
		while(images.size()-1 > i){
			
			Duration duration = Duration.between(first, Instant.now());
			if(duration.getNano() > i*ms*1000){
				i++;
				gc.clearRect(x, y, images.get(i).getWidth(), images.get(i).getHeight());
				System.out.println("x="+x+" y="+y);
				gc.drawImage(images.get(i), x, y);
				first = Instant.now();
			}
		}
		gc.clearRect(x, y, images.get(0).getWidth(), images.get(i).getHeight());
	}
	
	
}
