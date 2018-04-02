package com.grupo500noches.modelo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Comanda {

	private String orden;
	private String mesa;
	private String mesero;
	private String cliente;
	private List<Platillo> platillos;
	private String fecha;

	public Comanda() {
		super();
	}

	public Comanda(String orden, String mesa, String mesero, String cliente, ArrayList<Platillo> platillos,
			String fecha) {
		super();
		this.orden = orden;
		this.mesa = mesa;
		this.mesero = mesero;
		this.cliente = cliente;
		this.platillos = platillos;
		this.fecha = fecha;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getMesa() {
		return mesa;
	}

	public void setMesa(String mesa) {
		this.mesa = mesa;
	}

	public String getMesero() {
		return mesero;
	}

	public void setMesero(String mesero) {
		this.mesero = mesero;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<Platillo> getPlatillos() {
		return platillos;
	}

	public void setPlatillos(List<Platillo> platillos) {
		this.platillos = platillos;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		String listaElementos = "";
//		listaElementos += "COMANDA \n[\norden=" + orden + ", \nmesa=" + mesa + ", \nmesero=" + mesero + ", \ncliente=" + cliente
//				+ ", \nfecha=" + fecha + ", \nplatillo= ";
//		for (String platillo : platillos) {
//			listaElementos += platillo + " \n";
//			// System.out.println(listaElementos);
//		}
//		listaElementos += " ]\n\n";
//		//System.out.println(listaElementos);
		return listaElementos;
	}

}
