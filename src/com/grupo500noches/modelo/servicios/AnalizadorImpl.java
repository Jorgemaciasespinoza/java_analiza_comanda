package com.grupo500noches.modelo.servicios;

import java.util.ArrayList;
import java.util.List;

import com.grupo500noches.modelo.Analizador;
import com.grupo500noches.modelo.db.Repository;
import com.grupo500noches.modelo.modelo.Archivo;
import com.grupo500noches.modelo.modelo.Comanda;
import com.grupo500noches.modelo.modelo.Platillo;
import com.grupo500noches.modelo.modelo.Producto;

public class AnalizadorImpl implements Analizador {

	// variables globales
	int progreso = 0;

	// lista para almacenar las palabras reservadas
	ArrayList<String> platillos = new ArrayList<String>();

	Comanda comanda;
	List<Platillo> listaPlatillos;
	
	int inicioTimestamp = 0;
	int finalPlatillo = 0;
	int limiteInferiorPlatillo =0;

	// ******** configuracion del automata **************
	public boolean isString(int codigo) {
		boolean s = false;
		if (codigo >= 65 && codigo <= 90 || isAcento(codigo)) // MAYUSCULAS
			s = true;
		return s;
	}

	public boolean isNum(int codigo) {
		boolean s = false;
		if ((codigo >= 48 && codigo <= 57)) // 0- 9
			s = true;
		return s;
	}

	public boolean isChar(int codigo) {
		boolean s = false;
		if (codigo == 32 || codigo == 35 || codigo == 45 || codigo == 58 || codigo == 46) // espacio # - : .
			s = true;
		return s;
	}

	public boolean isCharFecha(int codigo) {
		boolean s = false;
		if (codigo == 32 || codigo == 45 || codigo == 58 || codigo == 46) // espacio - : .
			s = true;
		return s;
	}

	public boolean isSpace(int codigo) {
		boolean s = false;
		if (codigo == 32 || codigo == 46 || codigo == 47) // espacio .
			s = true;
		return s;
	}
	
	public boolean isAcento(int codigo) { // Vocal acentuada y u con dieresis
		boolean s = false;
		if (codigo == 209 || codigo == 193 || codigo == 201|| codigo == 205|| codigo == 211 || codigo == 218 || codigo == 220 )  //u dieresis, a e i o u 
			s = true;
		return s;
	}

	// ************ OBTENER ORDEN **********************
	@Override
	public void getOrden(String contenido) {

		String lexema = "";

		String texto;
		texto = contenido;
		char letra;
		int codigo;
		int estado = 0;

		System.out.println("LONGITUD DEL CONTENIDO: " + contenido.length());

		boolean r = false;

		// Se recorre el contenido
		for (int i = 0; i < contenido.length() - 1; i++) {
			progreso++;
			// Se revisa cada character
			letra = texto.charAt(i);

			// Se obtiene el numero del codigo ascii
			codigo = letra;

			// Condición de paro
			if (r == true) {
				break;
			}

			// Evalua que tipo de char es
			switch (estado) {
			case 0:
				if (codigo == 79) {
					estado = 1;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 1:
				if (codigo == 82) {
					estado = 2;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 2:
				if (codigo == 68) {
					estado = 3;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 3:
				if (codigo == 69) {
					estado = 4;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 4:
				if (codigo == 78) {
					estado = 5;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 5:
				if (isChar(codigo) == true || isNum(codigo)) {
					estado = 6;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 6:
				if (isChar(codigo) == true || isNum(codigo)) {
					estado = 6;
					lexema += letra;
				} else {
					lexema = lexema.replaceAll("ORDEN # ", "");
					lexema = lexema.replaceAll("#", "");
					comanda.setOrden(lexema);
					r = true;
				}
			}
		}
		// System.out.println("ODEN ENCONTRDA: "+ lexema);
	}

	// ************ OBTENER MESA **********************
	@Override
	public void getMesa(String contenido, int indice) {

		String lexema = "";
		String texto;
		texto = contenido;
		char letra;
		int codigo;
		int estado = 0;

		// System.out.println("Analisis a partir de: " + indice);

		boolean r = false;
		// Se recorre el contenido
		for (int i = indice; i < contenido.length() - 1; i++) {
			progreso++;
			// Se revisa cada character
			letra = texto.charAt(i);

			// Se obtiene el numero del codigo ascii
			codigo = letra;

			// Condición de paro
			if (r == true) {
				break;
			}

			// Evalua que tipo de char es
			switch (estado) {
			case 0:
				if (codigo == 77) {
					estado = 1;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 1:
				if (codigo == 69) {
					estado = 2;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 2:
				if (codigo == 83) {
					estado = 3;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 3:
				if (codigo == 65) {
					estado = 4;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 4:
				if (isChar(codigo) == true || isNum(codigo) || isString(codigo)) {
					estado = 5;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 5:
				if (isChar(codigo) == true || isNum(codigo) || isString(codigo)) {
					estado = 5;
					lexema += letra;
				} else {
					lexema = lexema.replaceAll("MESA  :", "");
					lexema = lexema.replaceAll("#", "");
					comanda.setMesa(lexema);
					r = true;
				}
			}
		}
		// System.out.println("MESA ENCONTRDA: "+ lexema);
	}

	// ************ OBTENER MESERO **********************
	@Override
	public void getMesero(String contenido, int indice) {

		String lexema = "";
		String texto;
		texto = contenido;
		char letra;
		int codigo;
		int estado = 0;

		// System.out.println("Analisis a partir de: " + indice);

		boolean r = false;

		// Se recorre el contenido
		for (int i = indice; i < contenido.length() - 1; i++) {
			progreso++;
			// Se revisa cada character
			letra = texto.charAt(i);

			// Se obtiene el numero del codigo ascii
			codigo = letra;

			// Condición de paro
			if (r == true) {
				break;
			}

			// Evalua que tipo de char es
			switch (estado) {
			case 0:
				if (codigo == 77) {
					estado = 1;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 1:
				if (codigo == 69) {
					estado = 2;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 2:
				if (codigo == 83) {
					estado = 3;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 3:
				if (codigo == 69) {
					estado = 4;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 4:
				if (codigo == 82) {
					estado = 5;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 5:
				if (codigo == 79) {
					estado = 6;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 6:
				if (isChar(codigo) == true || isString(codigo)) {
					estado = 7;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 7:
				if (isChar(codigo) == true || isString(codigo)) {
					estado = 7;
					lexema += letra;
				} else {
					lexema = lexema.replaceAll("MESERO :", "");
					comanda.setMesero(lexema);
					r = true;
				}
			}
		}
		// System.out.println("MESERO ENCONTRDA: "+ lexema);
	}

	// ************ OBTENER CLIENTE **********************
	@Override
	public void getCliente(String contenido, int indice) {

		String lexema = "";
		String texto;
		texto = contenido;
		char letra;
		int codigo;
		int estado = 0;

		// System.out.println("Analisis a partir de: " + indice);

		boolean r = false;

		// Se recorre el contenido
		for (int i = indice; i < contenido.length() - 1; i++) {
			progreso++;
			// Se revisa cada character
			letra = texto.charAt(i);

			// Se obtiene el numero del codigo ascii
			codigo = letra;

			// Condición de paro
			if (r == true) {
				break;
			}

			// Evalua que tipo de char es
			switch (estado) {
			case 0:
				if (codigo == 67) {
					estado = 1;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 1:
				if (codigo == 76) {
					estado = 2;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 2:
				if (codigo == 73) {
					estado = 3;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 3:
				if (codigo == 69) {
					estado = 4;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 4:
				if (codigo == 78) {
					estado = 5;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 5:
				if (codigo == 84) {
					estado = 6;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 6:
				if (codigo == 69) {
					estado = 7;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 7:
				if (isChar(codigo) == true || isString(codigo)) {
					estado = 8;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 8:
				if (isChar(codigo) == true || isString(codigo)) {
					estado = 8;
					lexema += letra;
				} else {
					lexema = lexema.replaceAll("CLIENTE :", "");
					comanda.setCliente(lexema);
					r = true;
				}
			}
		}
		// System.out.println("CLIENTE ENCONTRDA: "+ lexema);
	}

	// ************ OBTENER LA CANTIDAD DE PLATILLOS  ****
	@Override
	public Platillo getCantidadPlatillo(Platillo platillo, String contenido, int limiteInferior, int limiteSuperior) {

		String lexema = "";
		String texto;
		texto = contenido;
		char letra;
		int codigo;
		int estado = 0;

		// System.out.println("Analisis a partir de: " + indice);

		boolean r = false;

		// Se recorre el contenido
		for (int i = limiteInferior; i < limiteSuperior; i++) {
			//progreso++;
			// Se revisa cada character
			letra = texto.charAt(i);

			// Se obtiene el numero del codigo ascii
			codigo = letra;

			// Condición de paro
			if (r == true) {
				break;
			}

			// Evalua que tipo de char es
			switch (estado) {
			case 0:
				if (isNum(codigo) == true) {
					estado = 1;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 1:
				if (isNum(codigo) == true) {
					estado = 1;
					lexema += letra;
				} else {
					if (codigo == 120) { // 120 = x
						lexema += letra;
						lexema = lexema.trim().replaceAll("x", "");
						platillo.setCantidad(Integer.parseInt(lexema));
						System.out.println("Cantidad de platillos: " + lexema);
						r = true;
					} else {
						estado = 0;
						lexema = "";
					}
				}
			}
		}
		// System.out.println("FECHA ENCONTRADA: " +lexema);
		return platillo;
	}

	// ************ OBTENER PLATILLO **********************
	@Override
	public Platillo getPlatillo(String contenido, int limiteinferior, int limiteSuperior) {
		
		Platillo platillo = new Platillo();
		
		String lexema = "";
		String texto;
		texto = contenido;
		char letra;
		int codigo;
		int estado = 0;

		// System.out.println("Analisis a partir de: " + limiteinferior + " hasta: " +
		// limiteSuperior);

		boolean r = false;

		// Se recorre el contenido
		for (int i = limiteinferior; i < limiteSuperior; i++) {
			progreso++;
			// Se revisa cada character
			letra = texto.charAt(i);

			// Se obtiene el numero del codigo ascii
			codigo = letra;

			// Condición de paro
			if (r == true) {
				finalPlatillo = progreso;
				break;
			}

			// Evalua que tipo de char es
			switch (estado) {
			case 0:
				if (isSpace(codigo) == true || isString(codigo) == true || isNum(codigo) == true) {
					estado = 1;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 1:
				if (isSpace(codigo) == true || isString(codigo) == true || isNum(codigo) == true) {
					estado = 2;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 2:
				if (isSpace(codigo) == true || isString(codigo) == true || isNum(codigo) == true) {
					estado = 3;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 3:
				if (isSpace(codigo) == true || isString(codigo) == true || isNum(codigo) == true) {
					estado = 4;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 4:
				if (isSpace(codigo) == true || isString(codigo) == true || isNum(codigo) == true) {
					estado = 4;
					lexema += letra;
				} else {
					platillo.setNombre(lexema);
					platillo = getCantidadPlatillo(platillo, contenido, limiteInferiorPlatillo, progreso);
					if (platillo.getCantidad() == 0)
						platillo.setCantidad(1);
					limiteInferiorPlatillo = progreso;
					r = true;
				}
			}
		}
		// System.out.println("PLATILLO ENCONTRADO: "+lexema);
		return platillo;
	}

	// ************ OBTENER HORA Y FECHA **********************
	@Override
	public void getTimeStamp(String contenido, int indice) {

		String lexema = "";
		String texto;
		texto = contenido;
		char letra;
		int codigo;
		int estado = 0;

		// System.out.println("Analisis a partir de: " + indice);

		boolean r = false;

		// Se recorre el contenido
		for (int i = indice; i < contenido.length() - 1; i++) {
			progreso++;
			// Se revisa cada character
			letra = texto.charAt(i);

			// Se obtiene el numero del codigo ascii
			codigo = letra;

			// Condición de paro
			if (r == true) {
				inicioTimestamp = progreso - 19;
				break;
			}

			// Evalua que tipo de char es
			switch (estado) {
			case 0:
				if (isNum(codigo) == true) {
					estado = 1;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 1:
				if (isNum(codigo) == true) {
					estado = 2;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 2:
				if (codigo == 58) {
					estado = 3;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 3:
				if (isNum(codigo) == true) {
					estado = 4;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 4:
				if (isNum(codigo) == true) {
					estado = 5;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 5:
				if (isCharFecha(codigo) == true || isNum(codigo) == true) {
					estado = 6;
					lexema += letra;
				} else {
					estado = 0;
					lexema = "";
				}
				break;
			case 6:
				if (isCharFecha(codigo) == true || isNum(codigo) == true) {
					estado = 6;
					lexema += letra;
				} else {
					System.out.println("Fecha: "+ lexema );
					comanda.setFecha(lexema);
					r = true;
				}
				break;
			}
		}
		// System.out.println("FECHA ENCONTRADA: " +lexema);
	}

	@Override
	public void start(Archivo archivo) {
		

		comanda = new Comanda();
		listaPlatillos = new ArrayList<Platillo>();
		Platillo platilloDefault = new Platillo();
		
		
		getOrden(archivo.getContenido());
		getMesa(archivo.getContenido(), progreso);
		getMesero(archivo.getContenido(), progreso);
		getCliente(archivo.getContenido(), progreso);
		platilloDefault = getPlatillo(archivo.getContenido(), progreso, archivo.getContenido().length() - 1);
		getTimeStamp(archivo.getContenido(), progreso);
		progreso = finalPlatillo;

		if (validarPlatillo(platilloDefault))
			listaPlatillos.add(platilloDefault);
		
		// System.out.println("COMIENZA EL ANALISIS DE N PLATILLOS");
		do {
			Platillo platillo = new Platillo();
			platillo = getPlatillo(archivo.getContenido(), progreso, inicioTimestamp); 
			// System.out.println("================> INDICE INICIAL (FINAL DE PLATILLO): "
			// + progreso + "INDICE FINAL (INICIO DE FECHA) : " + inicioTimestamp);
			if(platillo.getNombre() != null ) {
				
				if( validarPlatillo(platillo) ) 
					listaPlatillos.add(platillo);
				
			}
				
			
		} while (progreso < inicioTimestamp);

		comanda.setPlatillos(listaPlatillos);
	}

	@Override
	public Comanda finish() {
		// Comanda com = new Comanda(comanda.getOrden(), comanda.getMesa(),
		// comanda.getMesero(), comanda.getCliente(), comanda.getPlatillos(),
		// comanda.getFecha());
		// return com;
		return comanda;
	}

	@Override
	public void reset() {
		progreso = 0;
	}
	
	public boolean validarPlatillo(Platillo platillo) {
		boolean bandera = false;
		Repository repository = new Repository();
		List<Producto> listaProductos;
		listaProductos = repository.getProducto(platillo.getNombre());
		repository.getProducto(platillo.getNombre());
		if (listaProductos != null ) {
			bandera = !listaProductos.isEmpty();
		}
		return bandera;
		//return true;
	}

}
