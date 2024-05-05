package co.edu.unbosque.model.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Archivo extends Persistence {

	private final String RUTA_PISTAS = "./Data/Pistas.txt";
	private final String RUTA_PARRILLA = "./Data/Parrilla.txt";

	@Override
	public boolean escribir(String dato) {
		// TODO Auto-generated method stub
		String linea = dato + "\n";
		File f = new File(this.RUTA_PISTAS);
		try {
			FileWriter fw = new FileWriter(f, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(linea);
			fw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean escribir(String[] datos) {

		String linea = datos[0] + "~" + datos[1] + "~" + datos[2] + "~" + datos[3] + "\n";
		File f = new File(this.RUTA_PARRILLA);

		try {
			FileWriter fw = new FileWriter(f, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(linea);
			fw.close();
		} catch (IOException e) {
			return false;
		}

		return true;
	}
	@Override
	public String leer(String ruta) {
		// TODO Auto-generated method stub
		String linea = "";
		String data = "";
		File f = new File(ruta);

		try {
			if (f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				linea = br.readLine();

				while (linea != null) {
					data += linea + "\n";
					linea = br.readLine();
				}
				br.close();
			} else {
				f.createNewFile();
			}

		} catch (IOException e) {
			return null;
		}
		return data;
	}

	public boolean copiarCancion(String ruta, String nombreCancion) {

		boolean salida = true;
		try {
			// Se quitan los espacios en blanco o /n para evitar confictos con la ruta
			File archivoEntrada = new File(ruta.trim());
			// Se verifica que se pueda leer
			if (archivoEntrada.canRead()) {
				// Se crea el nuevo archivo
				File archivoDestino = new File("./Canciones/" + nombreCancion.trim() + ".mp3");
				// Se copia la información del archivo de la ruta a uno nuevo y se reemplaza si
				// existe un archivo igual
				Files.copy(archivoEntrada.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
				salida = true;
			}
		} catch (Exception e) {
			salida = false;
		}
		return salida;
	}

	public void borrarArchivoParrilla() {
		File file = new File(this.RUTA_PARRILLA);
		if (file.exists()) {
			file.delete();
		}
	}

	public String getRUTA_PISTAS() {
		return RUTA_PISTAS;
	}

	public String getRUTA_PARRILLA() {
		return RUTA_PARRILLA;
	}

}
