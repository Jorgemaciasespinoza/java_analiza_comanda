package com.grupo500noches.modelo;

import com.grupo500noches.modelo.modelo.Archivo;
import com.grupo500noches.modelo.modelo.Comanda;
import com.grupo500noches.modelo.modelo.Platillo;

public interface Analizador {
	
	public void getOrden(String contenido);

	public void getMesa(String contenido, int indice);

	public void getMesero(String contenido, int indice);

	public void getCliente(String contenido, int indice);
	
	public Platillo getCantidadPlatillo(Platillo platillo, String contenido, int limiteInferior, int limiteSuperior);

	public Platillo getPlatillo(String contenido, int limiteinferior, int limiteSuperior);

	public void getTimeStamp(String contenido, int indice);

	public void start(Archivo archivo);

	public Comanda finish();

	public void reset();
}
