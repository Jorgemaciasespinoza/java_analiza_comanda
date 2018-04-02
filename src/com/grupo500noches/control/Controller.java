package com.grupo500noches.control;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.grupo500noches.modelo.Analizador;
import com.grupo500noches.modelo.LecturaArchivos;
import com.grupo500noches.modelo.db.Repository;
import com.grupo500noches.modelo.modelo.Archivo;
import com.grupo500noches.modelo.modelo.Comanda;
import com.grupo500noches.modelo.modelo.GUIModelo;
import com.grupo500noches.modelo.modelo.Platillo;
import com.grupo500noches.modelo.servicios.AnalizadorImpl;
import com.grupo500noches.modelo.servicios.LecturaArchivosImpl;
import com.grupo500noches.vista.FileOption;
import com.grupo500noches.vista.GUI;

public class Controller {

	FileOption fileOption = new FileOption();
	GUIModelo guiModelo = new GUIModelo();

	Analizador analizador;
	Archivo archivo;
	LecturaArchivos lecturaArchivos = new LecturaArchivosImpl();
	List<Comanda> listaComandas = new LinkedList<>();

	public void abrirArchivo() {

		archivo = fileOption.abrirArchivo(false);

		guiModelo.setContenido(archivo.getContenido());
		guiModelo.setMensajes(archivo.getPath());
		guiModelo.setEstatus("Se cargo comanda");
		GUI.getGUIInstance().actulizarGUI(guiModelo);

	}

	public void analizarArchivo() {
		analizador = new AnalizadorImpl();

		Comanda comanda = new Comanda();
		
		String comandaResultado ="";

		// 1.- ANALISIS DE LA COMANDA
		analizador.start(archivo);

		// 2.- OBTENER RESULTADOS DEL ANALISIS
		comanda = analizador.finish();

		
		comandaResultado += "COMANDA [ \n";
		for (Platillo platillo: comanda.getPlatillos()) {
			comandaResultado += "Orden: "+ comanda.getOrden() + ", Mesa: "
					+ comanda.getMesa() + ", Mesero: "+ comanda.getMesero()
					+ ", Cliente: "+ comanda.getCliente() + ", Platillo: "
					+ platillo.getNombre() + ", Cantidad: " + platillo.getCantidad() + ", fecha: " + comanda.getFecha() + "\n";
		}
		comandaResultado += "] ";
		
		List<Comanda> listaComandas = new ArrayList<Comanda>();
		
		listaComandas.add(comanda);
		
		Repository respository = new Repository();
		respository.insertarComanda(listaComandas);
		analizador.reset();
		// 3.- MOSTRAR RESULTADOS
		guiModelo.setMensajes(comandaResultado);
		guiModelo.setEstatus("Archivo analizado");
		GUI.getGUIInstance().actulizarGUI(guiModelo);
	}

	public void analizarComandasSpool() {
		listaComandas.clear();
		List<Archivo> listaArchivos;
		listaArchivos = lecturaArchivos.getListaArchivos("C:\\Windows\\System32\\spool\\PRINTERS");
		
		for (Archivo archivo: listaArchivos) {
			analizador = new AnalizadorImpl();
			Comanda comanda;
			// 1.- ANALISIS DE LA COMANDA
			analizador.start(archivo);
			
			// 2.- OBTENER RESULTADOS DEL ANALISIS
			comanda = analizador.finish();
			
			if (comanda.getOrden() != null)
				listaComandas.add(comanda);
			analizador.finish().toString();
			analizador.reset();
		}
		
		String comandaResultado = "";
		System.out.println(listaComandas.size());
		for (Comanda comanda: listaComandas) {
			
			comandaResultado += "COMANDA [ \n";
			for (Platillo platillo: comanda.getPlatillos()) {
				comandaResultado += "Orden: "+ comanda.getOrden() + ", Mesa: "
						+ comanda.getMesa() + ", Mesero: "+ comanda.getMesero()
						+ ", Cliente: "+ comanda.getCliente() + ", Platillo: "
						+ platillo.getNombre() + ", Cantidad: " + platillo.getCantidad() + ", fecha: " + comanda.getFecha() + "\n";
			}
			comandaResultado += "] \n";
			
			//comandas += comanda.toString();
			System.out.println(comandaResultado);
		}
		
		Repository respository = new Repository();
		respository.insertarComanda(listaComandas);
		
		guiModelo.setMensajes(comandaResultado);
		guiModelo.setEstatus("Se completo el analisis del spool");
		GUI.getGUIInstance().actulizarGUI(guiModelo);
	}
}
