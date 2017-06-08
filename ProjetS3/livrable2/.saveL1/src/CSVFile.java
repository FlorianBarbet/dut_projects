
import java.io.BufferedReader;
import java.io.FileReader;
 
public class CSVFile
 {
 
  public static void main(String[] args) throws Exception
  	 {
    BufferedReader br = new BufferedReader(new FileReader("test.csv"));
    String ligne = null;
    while ((ligne = br.readLine()) != null)
     {
      // Retourner la ligne dans un tableau
      String[] data = ligne.split(",");
 
      // Afficher le contenu du tableau
      for (String val : data)
      {
        System.out.println(val);
      }
    }
    br.close();
  }
}