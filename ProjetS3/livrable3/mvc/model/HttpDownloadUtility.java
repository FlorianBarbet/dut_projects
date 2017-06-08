package mvc.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Une méthode qui télécharge un fichier à partir d'une url.
 * @author Florian Hirson
 *
 */
public class HttpDownloadUtility {
    private static final int BUFFER_SIZE = 4096;
    private static String saveFilePath;

    /**
     *Télécharge un fichier à partir d'une url
     * @param fileURL URL HTTP du fichier à telecharger
     * @param saveDir Chemin du dossier où le fichier sera téléchargé
     * @throws IOException
     */
    public static void downloadFile(String fileURL, String saveDir)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }

            System.out.println("Type de contenu = " + contentType);
            System.out.println("Disposition du contenu = " + disposition);
            System.out.println("Longueur du contenu = " + contentLength);
            System.out.println("Nom du fichier = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("Fichier telechargé");

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Fenetre d'information");
            alert.setHeaderText(null);
            alert.setContentText("Le fichier a bien été téléchargé !");

            alert.showAndWait();
        } else {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Fenetre d'erreur");
        	alert.setHeaderText(null);
        	alert.setContentText("Aucun fichier à telecharger. Le serveur a répondu avec le code HTTP: " + responseCode);

        	alert.showAndWait();

            System.out.println("Aucun fichier à telecharger. Le serveur a répondu avec le code HTTP: " + responseCode);
        }
        httpConn.disconnect();
    }

	/**
	 * @return the filePath
	 */
	public static String getFilePath() {
		return saveFilePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public static void setFilePath(String filePath) {
		HttpDownloadUtility.saveFilePath = filePath;
	}
}
