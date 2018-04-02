package com.grupo500noches.modelo.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

	private static ConexionDB conexionDB;
	public Connection conexion;

	public static ConexionDB getConexionDBInstance() {
		if (conexionDB == null) {
			conexionDB = new ConexionDB("localhost", "3306", "grupo500noches", "root", "s3l3c7");
		}
		return conexionDB;
	}

	private ConexionDB(String host, String puerto, String db, String usuario, String password) {
		conexion = conexion(host, puerto, db, usuario, password);
	}

	public Connection getConexionDB() {
		return conexion;
	}

	private Connection conexion(String host, String puerto, String bd, String usuario, String pass) {
		Connection con = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			System.out.println("cocetando...");

			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + puerto + "/" + bd + "?" + "user="+ usuario+ "&password="+pass );
			System.out.println("Se ha conectado!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al establecer conexion con el servidor de base de datos");
		}
		return con;
	}
}
