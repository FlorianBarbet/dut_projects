package mvc.view;

import java.io.File;

import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class DirChooser extends Stage{
	public Scene scene=null;
	public Stage primaryStage=null;
	private String chemin=null;
	
	public DirChooser() {
		super();
		primaryStage = new Stage();
		primaryStage.setTitle("Selection d'un dossier pour l'enregistrement des données");

		
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File("data/"));
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
         
        if(selectedDirectory == null){
            setChemin("\0");
            System.out.println("Selection annulée");
        }else{
            setChemin(selectedDirectory.getAbsolutePath());
            System.out.println(getChemin());
        }

	}

	/**
	 * @return the chemin
	 */
	public String getChemin() {
		return chemin;
	}

	/**
	 * @param chemin the chemin to set
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	
	
}

