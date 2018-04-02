package com.grupo500noches.modelo.servicios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.grupo500noches.modelo.LecturaArchivos;
import com.grupo500noches.modelo.modelo.Archivo;
import com.grupo500noches.vista.FileOption;


public class LecturaArchivosImpl implements LecturaArchivos {

	@Override
	public List<Archivo> getListaArchivos(String directorio) {
		
		// LISTA DE ARCHIVOS A RETORNAR
		List<Archivo> listaArchivos = new ArrayList<Archivo>();
		
		// DIRECTORIO
		File archivo = new File(directorio);

		FileOption fileOption = new FileOption();
		
		// Obtener los archivos del directorio
		File[] ficheros = archivo.listFiles();

		if (ficheros == null)
			System.out.println("No hay ficheros en el directorio especificado");
		else

			for (int i = 0; i < ficheros.length; i++) {
				Archivo file = new Archivo();

				String path = ficheros[i].getAbsolutePath();
				String nombre =  ficheros[i].getName();
				String contenido = fileOption.getArchivo(path);
				
				file.setPath(path);
				file.setNombre(nombre);
				file.setContenido(contenido);
				
				listaArchivos.add(file);
			}
		return listaArchivos;
	}

}
