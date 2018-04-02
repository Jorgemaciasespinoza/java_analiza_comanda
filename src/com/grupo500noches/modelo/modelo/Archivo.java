package com.grupo500noches.modelo.modelo;

public class Archivo {

	private String path;
	private String nombre;
	private String contenido;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	@Override
	public String toString() {
		String valores =
		"Archivo [path=" + path + ", nombre=" + nombre + ", contenido="  + "]";
		System.out.println(valores);
		return valores;
	}
	
	
}
