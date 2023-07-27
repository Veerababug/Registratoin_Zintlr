package utility;

import java.io.FileInputStream;
import java.util.Properties;


public class Application_Keyword extends Generic_Keyword{

	public Application_Keyword() {
		String path = System.getProperty("user.dir")+"\\Properties\\env.properties";
		envprop = new Properties();
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(path);
			envprop.load(file);
			String environment = envprop.getProperty("env")+".properties";
			path = System.getProperty("user.dir")+"\\Properties\\"+environment;
			file = new FileInputStream(path);
			prop.load(file);

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void quit() {
		// TODO Auto-generated method stub
		
	}
	
}
