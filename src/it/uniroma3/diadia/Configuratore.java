package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Properties;

public final class Configuratore {

	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static final String PESO_MAX = "pesoMax";
	private static final String CFU = "cfu";
	private static final String Labirinto = "labirinto";
	private static final String Messaggio = "Messaggi_Di_Benvetuto";
	private static Properties prop = null;
	
	public static int getCFU() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU));
	}
	public static String getMessaggioDiBenvenuto(){
		if(prop==null)
			carica();
		LineNumberReader reader;
		StringBuilder output=new StringBuilder();
		String line=null;
		try {
			reader = new LineNumberReader(new FileReader(prop.getProperty(Messaggio)));
			while((line=reader.readLine())!=null) {
				output.append(line);
				output.append("\n");
		} }catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return output.toString();
	}
	public static int getPesoMax() {
		if(prop == null)
			carica();
		
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}
	
	public static String getLabirinto() {
		if(prop == null)
			carica();
		return prop.getProperty(Labirinto);
	}
	private static void carica() {
		prop = new Properties();
		try {
			FileInputStream input = new FileInputStream(DIADIA_PROPERTIES);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}