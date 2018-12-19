package com.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.sun.media.jfxmedia.events.NewFrameEvent;


public class ParametersReader {
	
	private static final Logger logger = Logger.getLogger(ParametersReader.class.getName());
	public static final String PATH_PROPERTIES = "classpath";
	
	
	public ParametersReader() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**Prende i parametri che trova nel file Parametri.properties<br>
	 * settando i corrispondenti nel domain properties.java<br>
	 * ps. Per aggiungerne uno nuovo baste che il nome del parametro sia<br>
	 * presente sia nel file properties che in quello java, questo metodo lo setterà<br>
	 * NB: La path delle proprietà è Parameters <br>
	 * Quindi andrà a cercare i file dentro tale cartella.<br>
	 * E cerca solo file .properties
	 * @param nomeFileSenzaEstensione
	 */
	public static void readFileParameter (String nomeFileSenzaEstensione){
		// //////////Reading properties////////////////////////////////
		Properties prop = new Properties();
		String pathFile= PATH_PROPERTIES + "\\" + nomeFileSenzaEstensione + ".properties";
		
		try (InputStream in = FileStream.getInputStreamFromPath(pathFile)) {
			if (in == null) {
				//return null;
			}
			prop.load(in);
			logger.warning("####Parametri in " + pathFile + " Parametri file numero di parametri=" + prop.stringPropertyNames().size() + " ####");
			int contatore=1;
			for (String property : prop.stringPropertyNames()) {
				String value = prop.getProperty(property);
				logger.warning("---VAOLRE " + contatore + " --- PROPRIETA': " + property + "=" + value);
				contatore++;
			}
		} catch (IOException e) {
			logger.warning("%%%%%%%%%%%%Errore in loadProperties " + nomeFileSenzaEstensione + ".properties" + e.getMessage() + "%%%%%%%%%%%%%%%%%");
		}
	}
	
	public static String readNameDb (String nomeFileSenzaEstensione){
		Properties prop = new Properties();
		String absoluteP = new File("df").getAbsolutePath();
		String pathFile= absoluteP + "\\" + PATH_PROPERTIES + "\\" + nomeFileSenzaEstensione + ".properties";
		
		try (InputStream in = FileStream.getInputStreamFromPath(pathFile)) {
			if (in == null) {
				return null;
			}
			prop.load(in);		
			for (String property : prop.stringPropertyNames())
				if (property.equals("dbMySqlNomeDb"))
					return  prop.getProperty(property);

		} catch (IOException e) {
			logger.warning("%%%%%%%%%%%%Errore in loadProperties " + nomeFileSenzaEstensione + ".properties" + e.getMessage() + "%%%%%%%%%%%%%%%%%");
		}
		
		return null;
	}

}
