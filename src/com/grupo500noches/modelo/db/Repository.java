package com.grupo500noches.modelo.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.grupo500noches.modelo.modelo.Comanda;
import com.grupo500noches.modelo.modelo.Platillo;
import com.grupo500noches.modelo.modelo.Producto;
import com.grupo500noches.modelo.db.ConexionDB;

public class Repository {

	public boolean insertarComanda(List<Comanda> listaComandas) {
		Connection connection = ConexionDB.getConexionDBInstance().getConexionDB();
		try {
			Statement statement = connection.createStatement();
			for (Comanda comanda : listaComandas) {
				for ( Platillo platillo: comanda.getPlatillos() ) {
					for ( int i=0; i<platillo.getCantidad(); i++ ) {
						String query = "insert into tr_comandas (orden, mesa, mesero,cliente, platillo, fecha_hora) values ("
								+ "'" + comanda.getOrden() + "'," 
								+ "'" + comanda.getMesa() + "'," 
								+ "'" + comanda.getMesero() + "'," 
								+ "'" + comanda.getCliente() + "'," 
								+ "'" + platillo.getNombre() + "'," 
								+ "'" + comanda.getFecha() + "'" 
		 						+ ")";
		
						statement.addBatch(query);
					}
				}
				statement.executeBatch();
//				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return true;
	}

	public  List<Producto> getProducto(String descripcion) {
		List<Producto> listaProductos = null;
		try {
			String query = "select * from ct_productos where upper(descripcion) like '%" + descripcion.trim() + "%' ";

			// create the java statement
			Statement statement = ConexionDB.getConexionDBInstance().getConexionDB().createStatement();

			// execute the query, and get a java resultset
			ResultSet resultSet = statement.executeQuery(query);

			listaProductos = new ArrayList<Producto>();
			
			// iterate through the java resultset
			while (resultSet.next()) {
				Producto producto = new Producto();
				producto.setId(resultSet.getInt("pk_producto"));
				producto.setCodigo(resultSet.getInt("codigo"));
				producto.setDescripcion(resultSet.getString("descripcion"));
				producto.setCodigoFamilia(resultSet.getInt("codigo_familia"));
				producto.setCodigoDepartamento(resultSet.getInt("codigo_departamento"));
			

				System.out.println(producto.toString());
				
				listaProductos.add(producto);
			}
			statement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return listaProductos;
	}

}
