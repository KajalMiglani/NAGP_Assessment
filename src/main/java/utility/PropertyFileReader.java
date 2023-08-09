package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyFileReader {

	
	private static final Logger logger = LoggerFactory.getLogger(PropertyFileReader.class);

	public static Properties readProperty(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException ex) {
			logger.error(Constant.ERROR_OCCURED, ex);
		} catch (IOException e) {
			logger.error(Constant.ERROR_OCCURED, e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (FileNotFoundException er) {
					logger.error(Constant.ERROR_OCCURED, er);
				}
			}
		}
		return prop;
	}

}
