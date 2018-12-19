package com.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;



public class FileStream {
	public static final String A_CAPO = "\r\n";
	private static final Logger logger = Logger.getLogger(FileStream.class.getName());
	
	public static enum CodificaFile {
		UTF8 ("UTF-8"),
		ANSI ("Cp1252"),
		Cp1252 ("Cp1252");
		
		 private final String name;   
		 
		 private CodificaFile(String s) {
		        name = s;
		    }
	
		    public boolean equalsName(String otherName) {
		        // (otherName == null) check is not needed because name.equals(null) returns false 
		        return name.equals(otherName);
		    }
	
		    public String toString() {
		       return this.name;
		    }
	}
	
	
	public static void copyFile(String source, String destination) throws IOException{
		File src = new File(source);
		File dest = new File(destination);		
			FileUtils.copyFile(src,dest,false);
	}
	
	
	public static void deletFile(String source) {
		File src = new File(source);	
		src.delete();
	}
	
	
	
	public static FileOutputStream getFileOutputStrean (String patchFile,boolean append){
		FileOutputStream fileOutputStrean=null;
				
		try {
			fileOutputStrean =  new FileOutputStream(patchFile,append);
		} catch (FileNotFoundException e) {
			logger.warning("Err- generazione del file pdf per allegato " + patchFile);
			e.printStackTrace();
		}
		
		return fileOutputStrean;
		
	}
	
	public static boolean existingFile (String patch){
		if(new File(patch).exists()){
			return true;
		}else
		{
			return false;
		}
	}
	
	public static void createFileFromString(String stringa,String pathFileDestination){
		if (StringUtils.isNotBlank(pathFileDestination)){
			File fileDiDestinazione = new File(pathFileDestination);
			InputStream inputStream = createInputStreamUTF_8FromString(stringa);
			try {
				FileUtils.copyInputStreamToFile(inputStream, fileDiDestinazione);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static InputStream getInputStreamFromPath(String path){
		InputStream is = null;
		try {
			is = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			logger.warning("Errore nel tentativo di creare InputStream dal file " + path);
			e.printStackTrace();
		} finally {
			return is;
		}
	}
	
	
	public static InputStream createInputStreamUTF_8FromString(String stringa){
		Charset charset = StandardCharsets.UTF_8;
		InputStream stream;
		stream = new ByteArrayInputStream(stringa.getBytes(charset));
		return stream;
	}

	
	public static void closeFile(FileOutputStream fileOutputStrean, String patchFile) {
		try {
			fileOutputStrean.close();
		} catch (IOException e) {
			logger.warning("errore chiusura file " + patchFile);
			e.printStackTrace();
		}
		
	}

	
	
	public static String writeFileProvvisorioRitornaPatchFile(InputStream isnputStringBinario, boolean FileRTFinMDB) {
	
		FileOutputStream fileOutputStrean = null;
		String patchFile="fileRTFProvvisorio.rtf";
		
		
		try {
			fileOutputStrean = new FileOutputStream(patchFile);
		} catch (FileNotFoundException e) {
			logger.warning("errore scrittura file " + patchFile);
			e.printStackTrace();
		}
		
		
		writeFile(isnputStringBinario,fileOutputStrean,patchFile,FileRTFinMDB);
		
		
		try {
			fileOutputStrean.close();
		} catch (IOException e) {
			logger.warning("Errore chiusura file stream "+ fileOutputStrean.toString() );
			e.printStackTrace();
		}	
		
		
		return patchFile;
		
	}
	
	
	
	public static void writeFileBinario(InputStream isnputStringBinario, String patchFile){
		
		FileOutputStream fos = getFileOutputStrean(patchFile, false);
		
		byte[] data  = null;
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    int nRead;
	    byte[] data2 = new byte[1024];
	    try {
			while ((nRead = isnputStringBinario.read(data2, 0, data2.length)) != -1) {
			    buffer.write(data2, 0, nRead);
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	 
	    try {
			buffer.flush();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    byte[] byteArray = buffer.toByteArray();
	    
		try {
			//data  = IOUtils.toByteArray(isnputStringBinario);
			fos.write(byteArray, 0, byteArray.length);
			fos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public static void writeFile(InputStream isnputStringBinario, FileOutputStream fileOutputStrean, String patchFile, boolean FileRTFinMDB) {
		int c;
		//sembra che i primi tre li debba cmq prendere poi parte la regola del uno si uno no
		
		if (!FileRTFinMDB){
			try {
				while((c = isnputStringBinario.read()) != -1){ 												
					fileOutputStrean.write(c);
					fileOutputStrean.flush();						
				}
			} catch (IOException e1) {
				logger.warning("errore scrittura file " + patchFile);
				e1.printStackTrace();
			}
		}else
		{
			//non metto contatore perchè altrimenti rallenta, faccio un giro separato per prime tre
//			int conta=1;

//			
			//allora, per rtf, deve sempre prendere i primi finchè non sono vuoti, poi va uno si e uno no
			try {
			while((c = isnputStringBinario.read()) != -1){ 			
				if (c>0){
				fileOutputStrean.write(c);
				fileOutputStrean.flush();					
				}
				else{
					break;
				}
			}
			} catch (IOException e1) {
				logger.warning("errore scrittura file " + patchFile);
				e1.printStackTrace();
			}		
			
			//FileRTFinMD//rimane true perchè automaticamente fa il next e va a quello dopo che non sarà più 0
			
			try {//negli allegati rtf, invece, il primo e il secondo e il terzo sono pieno, 4=0 6=0 ecc...
				while((c = isnputStringBinario.read()) != -1){ //nei referti il primo è pieno e così via x tutti i dispari
					if (FileRTFinMDB){
						fileOutputStrean.write(c);
						FileRTFinMDB=false;
					}else
					{
						FileRTFinMDB=true;
					}
				}
				
				
			} catch (IOException e1) {
				logger.warning("errore scrittura file " + patchFile);
				e1.printStackTrace();
			}
		}
	}
	
	

	public static void inserisciInFileStringaDaCercare(FileOutputStream fileOutputStrean, String stringaDaCercare) {
		char[] arrayString = stringaDaCercare.toCharArray();//trasforma stringa da ricercae in array di char interi
		for (char carattere: arrayString){
			try {
				fileOutputStrean.write(carattere);
			} catch (IOException e) {
				logger.warning("Err in inserisciInFileStringaDaCercare fileOutputStrean=" + fileOutputStrean + " stringaDaCercare=" + stringaDaCercare);
				e.printStackTrace();
			}					
		}	
		
	}
	
	public static FileReader getFileReaderFrompatch(String patchFile){
		
		FileReader filereader = null;
		try {
			filereader = new FileReader(new File(patchFile));
		} catch (FileNotFoundException e) {
			logger.warning("errore creazione FileReader patch "+patchFile);
			e.printStackTrace();
		}
		return filereader;
		
	}
	
	
	
	public static FileReader readFile(String patchFile){
		FileReader filereader =getFileReaderFrompatch(patchFile);
		return filereader;
	}
	
	
	
	//utile per specificare la CODIFICA del file
	public static BufferedReader getBufferedReader(String pathFile, CodificaFile codificaDelFile ){
		String testoDecodifica = codificaDelFile.name;
		BufferedReader bufferReader=null;
		try {
			FileInputStream fileInputStream = null;
			try{
				fileInputStream = new FileInputStream(pathFile);
			} catch (FileNotFoundException e) {
				logger.warning("File non trovato ? pathFile=" + pathFile);
				logger.warning(e.toString());
				return null;
			}
			
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, testoDecodifica);
			bufferReader= new BufferedReader(inputStreamReader);
		} catch (UnsupportedEncodingException e) {
			logger.warning("Buffreader in errore per pathFile=" + pathFile);
			e.printStackTrace();
		}		
		return bufferReader;		
	}
	
	public static String cercaElementoInFileERestituisciQuellaRiga (BufferedReader br, String elementoDaCercare){
		String strLine;
		try {
			if (br.ready())
			{
				try {
					while ((strLine = br.readLine()) != null) 
						if (strLine.contains(elementoDaCercare))
							return strLine;
				} catch (IOException e) {
					logger.warning("Errore lettura file "+ br.toString() + " durante ricerca di " + elementoDaCercare);
					return null;
				}finally {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					br=null;
				}
			}
		} catch (IOException e) {
			logger.warning("Errore lettura file buffer vuoto "+ br.toString());
			return null;
		}
		return null;		
	}
	
	
	
	
	public static String cercaElementoInFileERestituisciQuellaRiga (String patchFile, String elementoDaCercare, CodificaFile codificaDelFile){
		BufferedReader br = getBufferedReader (patchFile,codificaDelFile);
		try {
			if (br.ready())
			{
				String strLine;
				try {
					while ((strLine = br.readLine()) != null) 
						if (strLine.contains(elementoDaCercare))
							return strLine;
				} catch (IOException e) {
					logger.warning("Errore lettura file "+ patchFile + " durante ricerca di " + elementoDaCercare);
					e.printStackTrace();
				}finally {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					br=null;
				}
			}
		} catch (IOException e) {
			logger.warning("buffer vuoto");
			//e.printStackTrace();
		}
		return null;		
	}
	
	

	
	public static String readFileTXTUTF8 (String patchFile){
		File file = new File(patchFile);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] data = new byte[(int) file.length()];
		try {
			fis.read(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = null;
		try {
			str = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return str;
	}

	
}
