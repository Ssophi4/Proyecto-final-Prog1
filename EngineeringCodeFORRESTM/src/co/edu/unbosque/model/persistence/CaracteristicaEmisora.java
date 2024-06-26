package co.edu.unbosque.model.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CaracteristicaEmisora extends Persistence {

	private final String directorio = "./Data/";
	private final String gui_ES = "GUI_ES.properties";
	private final String gui_EN = "GUI_EN.properties";
	private final String archivoConfiguracion = "Configuracion.properties";
	private Properties prop;
	private Properties propGUI;

	public CaracteristicaEmisora() {
		// TODO Auto-generated constructor stub
		prop = new Properties();
		propGUI = new Properties();
	}

	@Override
	public boolean escribir(String dato) {
		// TODO Auto-generated method stub

		String[] listaDatos = dato.split("~");
		try {
			prop.setProperty("nombreEmisora", listaDatos[0]);
			prop.setProperty("modoTransmision", listaDatos[1]);
			prop.setProperty("tipoMusica", listaDatos[2]);
			prop.setProperty("idioma",listaDatos[3]);
			prop.store(new FileOutputStream(directorio+archivoConfiguracion), null);
		} catch (IOException ex) {
			return false;
		}
		return true;
	}


	@Override
	public String leer(String dato) {
		// TODO Auto-generated method stub
		String linea = "";
		String leng = "";

		try {
			prop.load(new FileInputStream(directorio+archivoConfiguracion));
			leng=prop.getProperty("idioma");
			
			if(leng.equals("EN")) {
				propGUI.load(new FileInputStream(directorio+gui_EN));
				linea = propGUI.getProperty(dato);
			}
			else if(leng.equals("ES")){
				propGUI.load(new FileInputStream(directorio+gui_ES));
				linea = propGUI.getProperty(dato);
			}
			
		} 
		catch (IOException e) {
			return null;
		}
		return linea;
	}

	public String leer(String dato, String ruta) {

		String linea = "";

		try {
			prop.load(new FileInputStream(directorio+ruta));
			linea=prop.getProperty(dato);
		} 
		catch (IOException e) {
			return null;
		}
		return linea;
	}
	
	

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public Properties getPropGUI() {
		return propGUI;
	}

	public void setPropGUI(Properties propGUI) {
		this.propGUI = propGUI;
	}

	public String getDirectortio() {
		return directorio;
	}

	public String getGui_ES() {
		return gui_ES;
	}

	public String getGui_EN() {
		return gui_EN;
	}

	public String getArchivoConfiguracion() {
		return archivoConfiguracion;
	}

}
