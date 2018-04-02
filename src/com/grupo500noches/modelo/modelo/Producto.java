package com.grupo500noches.modelo.modelo;

public class Producto {

	int id;
	int codigo;

	String descripcion;
	int codigoFamilia;
	int codigoDepartamento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCodigoFamilia() {
		return codigoFamilia;
	}

	public void setCodigoFamilia(int codigoFamilia) {
		this.codigoFamilia = codigoFamilia;
	}

	public int getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(int codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", codigoFamilia="
				+ codigoFamilia + ", codigoDepartamento=" + codigoDepartamento + "]";
	}
	
	

}
