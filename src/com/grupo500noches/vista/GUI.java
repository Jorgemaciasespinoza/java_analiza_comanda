package com.grupo500noches.vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.grupo500noches.control.Controller;
import com.grupo500noches.modelo.Analizador;
import com.grupo500noches.modelo.LecturaArchivos;
import com.grupo500noches.modelo.modelo.Comanda;
import com.grupo500noches.modelo.modelo.GUIModelo;
import com.grupo500noches.modelo.servicios.AnalizadorImpl;
import com.grupo500noches.modelo.servicios.LecturaArchivosImpl;


public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextPane area;
	private JTextArea area2;
	private JMenuBar mb;
	private JMenu menu1;
	private JMenuItem mi1, mi2, mi3;
	private JLabel estado;

	private static GUI gui;

	FileOption f = new FileOption();
	Comanda comanda;
	Analizador analizador = new AnalizadorImpl();
	LecturaArchivos lecturaArchivos = new LecturaArchivosImpl();
	Controller controller = new Controller();

	private GUI() {
		super("Analizador de comandas");
		setSize(600, 400);
		menu();
		panel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static GUI getGUIInstance() {
		if (gui == null) {
			gui = new GUI();
		}
		return gui;
	}

	// Configuracion del menu
	public void menu() {
		mb = new JMenuBar();
		setJMenuBar(mb);
		menu1 = new JMenu("Archivo");
		mb.add(menu1);
		mi1 = new JMenuItem("Abrir archivo");
		mi1.addActionListener(this);
		menu1.add(mi1);
		mi2 = new JMenuItem("Analizar contenido");
		mi2.addActionListener(this);
		menu1.add(mi2);

		mi3 = new JMenuItem("Analizar comandas del spool");
		mi3.addActionListener(this);
		menu1.add(mi3);
	}

	// Configuracion del panel
	public void panel() {

		area = new JTextPane();
		area.setPreferredSize(new Dimension(200, 200));
		// area.setDocument();
		area.setMargin(new Insets(5, 5, 5, 5));
		// area.setBackground(Color.black);
		JScrollPane scrollPane = new JScrollPane(area);

		area2 = new JTextArea(5, 30);
		area2.setEditable(false);
		JScrollPane scrollPaneForLog = new JScrollPane(area2);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, scrollPaneForLog);
		splitPane.setOneTouchExpandable(true);

		JPanel statusPane = new JPanel(new GridLayout(1, 1));
		estado = new JLabel("Status: ");
		statusPane.add(estado);

		BorderLayout borderLayout = new BorderLayout();
		JPanel panel = new JPanel();
		panel.setLayout(borderLayout);
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(statusPane, BorderLayout.SOUTH);
		add(panel);
	}

	// Eventos asociados con el menu
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mi1) {
			controller.abrirArchivo();
		}
		if (e.getSource() == mi2) {
			controller.analizarArchivo();
		}
		if (e.getSource() == mi3) {
			controller.analizarComandasSpool();
		}
	}

	// Metodo para actualizar el modelo de la GUI
	public void actulizarGUI(GUIModelo guiModelo) {
		if (guiModelo.getContenido() != null)
			if (guiModelo.getContenido().trim() != "")
				area.setText(guiModelo.getContenido());

		if (guiModelo.getMensajes() != null)
			if (guiModelo.getMensajes().trim() != "")
				area2.setText(guiModelo.getMensajes());

		if (guiModelo.getEstatus() != null)
			if (guiModelo.getEstatus().trim() != "")
				estado.setText(guiModelo.getEstatus());
	}
}