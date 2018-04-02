package com.grupo500noches.vista;

/*
 * Jorge Macias Espinoza
 * Actualizacion: 05/03/2018
 * 
 * METODOS PARA EL MANEJO DE ARCHIVOS
 * 
 * OPERACIONES:
 * 	- ABRIR ARCHIVO
 *  - ELIMINAR ARCHIVO
 *  - GUARDAR ARCHIVO
 *  
 *  
 *  Se debe acompanar con la clase Archivo 
 */

import javax.swing.*;

import com.grupo500noches.modelo.modelo.Archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class FileOption extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFileChooser abrirArchivo;
	private Archivo archivo = new Archivo();

	// OBTENER EL CONTENIDO DE UN ARCHIVO
	public String getArchivo(String ruta) {
		FileReader fr = null;
		BufferedReader br = null;
		// Cadena de texto donde se guardara el contenido del archivo
		String contenido = "";
		try {
			// ruta puede ser de tipo String o tipo File
			fr = new FileReader(ruta);
			br = new BufferedReader(fr);

			String linea;
			// Obtenemos el contenido del archivo linea por linea
			while ((linea = br.readLine()) != null) {
				contenido += linea + "\n";
			}
		} catch (Exception e) {
		} finally {
			try {
				br.close();
			} catch (Exception ex) {
			}
		}
		return contenido;
	}

	// ABRIR ARCHIVO
	public Archivo abrirArchivo(boolean multiseleccion) {
		Archivo archivo = new Archivo();
		String path ="";
		
		if (abrirArchivo == null)
			abrirArchivo = new JFileChooser();
		if (multiseleccion)
			abrirArchivo.setMultiSelectionEnabled(true);
		
		abrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int seleccion = abrirArchivo.showOpenDialog(this);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File f = abrirArchivo.getSelectedFile();
			try {
				path = f.getAbsolutePath();
				
				archivo.setPath(path);
				archivo.setNombre(f.getName());
				archivo.setContenido( getArchivo(path) );
			} catch (Exception exp) {
				
			}
		}
		return archivo;
	}

	public void borrarArchivo() {
		if (abrirArchivo == null)
			abrirArchivo = new JFileChooser();
		// Con esto solamente podamos abrir archivos
		abrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int seleccion = abrirArchivo.showOpenDialog(this);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File f = abrirArchivo.getSelectedFile();
			try {
				String nombre = f.getName();
				String path = f.getAbsolutePath();
				if (f.delete()) {
					System.out.println("Nombre: " + nombre + "Path: " + path + "ELIMINADO!");
					// System.exit(0);
				} else {
					System.out.println("NO SE PUEDE BORRAR error");
					// System.exit(0);
				}
			} catch (Exception exp) {
			}
		}
	}

	public void guardarArchivo(String cont, String extension) {
		try {
			String name = "";
			JFileChooser file = new JFileChooser();
			file.showSaveDialog(this);
			File guarda = file.getSelectedFile();
			if (guarda != null) {
				name = file.getSelectedFile().getName();
				FileWriter save = new FileWriter(guarda + extension);
				save.write(cont);
				save.close();
				System.out.println("ARCHIVO GUARDADO!" + name);
			}
		} catch (Exception ex) {
			System.out.println("NO SE PUEDE GUARADAR error");
		}
	}

	public Archivo getArchivo() {
		return archivo;
	}

	// public static void main(String[] args) {
	// FileOption f = new FileOption ();
	// //f.borrarArchivo();
	// //f.guardarArchivo("Contenido",".java");
	// f.abrirArchivo();
	// }
}