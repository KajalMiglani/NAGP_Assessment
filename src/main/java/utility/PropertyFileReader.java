package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
	

	 public static Properties readProperty(String fileName) throws IOException {
	      FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(fileName);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException ex) {
	         ex.printStackTrace();
	      } catch(IOException e) {
	         e.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	   }


}
