package com.pagoefectivo.web.app.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FilesUtil {
	public boolean GuardarArchivo(String nombreArchivo, String contenido) throws IOException {
		boolean fileCreated = false;
		File strFile = null;
		try {

			strFile = new File(nombreArchivo);
			// Si el Archivo Existe
			if (strFile.exists() && strFile.isFile()) {
				// Lo Borra
				strFile.delete();
			}
			// Crear Archivo
			fileCreated = strFile.createNewFile();

			// Escribir Archivo
			Writer objWriter = new BufferedWriter(new FileWriter(strFile, false));
			objWriter.write(contenido);
			objWriter.flush();
			objWriter.close();

		} catch (Exception e) {
			System.err.println(e);
			fileCreated = false;
		}
		return fileCreated;
	}

	
	public boolean SobreescribirArchivo(String nombreArchivo, String contenido) throws IOException {
		boolean fileCreated = false;
		File strFile = null;
		try {

			strFile = new File(nombreArchivo);
			// Si el Archivo Existe
			if (!(strFile.exists())) {
				fileCreated = strFile.createNewFile();
			}

			// Escribir Archivo
			Writer objWriter = new BufferedWriter(new FileWriter(strFile.getAbsoluteFile(), true));
			objWriter.write(contenido);
			objWriter.flush();
			objWriter.close();

		} catch (Exception e) {
			System.err.println(e);
			fileCreated = false;
		}
		return fileCreated;
	}
	
	public String LeerArchivo(String nombreArchivo) throws IOException {

		File strFile = null;
		String contenido = "";
		String text = "";

		try {

			strFile = new File(nombreArchivo);
			// Si el Archivo Existe
			if (strFile.exists() && strFile.isFile()) {
				// Leer Archivo
				BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
				while ((text = reader.readLine()) != null) {
					contenido = contenido + text;
				}
				reader.close();
			}
		} catch (Exception e) {
			System.err.println(e);
			contenido = "";
		}
		return contenido;
	}

}
