/*******************************************************************************
 * Copyright (c) 2011, 2013  - Daniel, Aguil Mallea.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Daniel, Aguil Mallea - initial API and implementation
 ******************************************************************************/
package edu.davinci.ui.ciudad;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.ciudad.Objeto;
import edu.davinci.ciudad.Robot;
import edu.davinci.lenguaje.Dato;
import edu.davinci.lenguaje.DatoEntero;
import edu.davinci.lenguaje.DatoLogico;
import edu.davinci.lenguaje.DatoTexto;
import edu.davinci.lenguaje.ParserConstants;
import edu.davinci.utils.Resource;

public class CiudadSimpleGuiImpl implements Ciudad, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2844621245573748033L;

	/** The Constant AVENIDAS. */
	private int avenidas;

	/** The Constant CALLES. */
	private int calles;

	/** The robots. */
	private List<Robot> robots;

	private Objeto flores;
	private Objeto papeles;
	private Objeto obstaculos;

	/** cantidad inicial de flores en la bolsa de los robots **/
	private int floresEnBolsa;

	/** cantidad inicial de papeles en la bolsa de los robots **/
	private int papelesEnBolsa;

	/** los robots pueden estar superpuestos **/
	private boolean superpuestos;

	/** la representacion grafica de la ciudad **/
	private CiudadPanel panel;

	/**
	 * Constructor
	 */
	public CiudadSimpleGuiImpl() {
		this(Ciudad.AVENIDAS, Ciudad.CALLES);
	}

	/**
	 * Constructor
	 */
	public CiudadSimpleGuiImpl(int avenidas, int calles) {
		// seteamos las avenidas con el valor por defecto
		this.avenidas = avenidas;
		// seteamos las calles con el valor por defecto
		this.calles = calles;
		// inicializamos los robots en la ciudad
		robots = new ArrayList<Robot>();
		// inicializamos flores, papeles y obstaculos, y kla representacion
		// grafica de estos
		inicializarElementos(avenidas, calles);

	}

	/**
	 * Inicializa la ciudad en funcion de la cantidad de avenidas y calles que
	 * posee.
	 * 
	 * @param av
	 * @param ca
	 */

	private void inicializarElementos(int av, int ca) {
		//
		flores = new FlorSimpleGuiImpl(av, ca);
		flores.setImagen(Objeto.RECURSO_FLOR);

		//
		papeles = new PapelSimpleGuiImpl(av, ca);
		papeles.setImagen(Objeto.RECURSO_PAPEL);

		//
		obstaculos = new ObstaculoSimpleGuiImpl(av, ca);
		obstaculos.setImagen(Objeto.RECURSO_OBSTACULO);

		// inicializamos el dibujo
		panel = new CiudadPanel(robots, flores, papeles, obstaculos);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#agregarRobot(edu.davinci.ciudad.Robot)
	 */
	@Override
	public void agregarRobot(Robot robot) {
		// verificamos si el robots ya existe
		for (Robot r : robots) {
			if (r.getNombre().equals(robot.getNombre()))
				throw new RuntimeException(MessageFormat.format(
						Resource.getString("ciudad.existeElRobot"), robot.getNombre()));
		}

		// inicializamos el robot
		robot.setBolsaFlores(floresEnBolsa);
		robot.setBolsaPapeles(papelesEnBolsa);

		// insertamos el robot
		robots.add(robot);

		// hay q redibujar la ciudad
		panel.repaint();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#agregarRobot(java.lang.String)
	 */
	@Override
	public void agregarRobot(String nombre) {

		// insertamos el robot
		agregarRobot(new RobotSimpleGuiImpl(nombre, this));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#decrementarFlores(int, int)
	 */
	@Override
	public void decrementarFlores(int avenida, int calle) {
		//
		flores.eliminar(avenida, calle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#decrementarPapeles(int, int)
	 */
	public void decrementarPapeles(int avenida, int calle) {
		//
		papeles.eliminar(avenida, calle);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#getFlores(int, int)
	 */
	@Override
	public int getFlores(int avenida, int calle) {
		return flores.cantidad(avenida, calle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#getPapeles(int, int)
	 */
	@Override
	public int getPapeles(int avenida, int calle) {
		//
		return papeles.cantidad(avenida, calle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#getRobot(java.lang.String)
	 */
	@Override
	public Robot getRobot(String nombre) {

		for (Robot r : robots) {
			if (r.getNombre().equals(nombre))
				return r;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#HayFlorEnLaEsquina(int, int)
	 */
	@Override
	public boolean HayFlorEnLaEsquina(int avenida, int calle) {
		return flores.hay(avenida, calle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#HayObstaculo(int, int)
	 */
	@Override
	public boolean HayObstaculo(int avenida, int calle) {
		//
		return obstaculos.hay(avenida, calle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#HayPapelEnLaEsquina(int, int)
	 */
	@Override
	public boolean HayPapelEnLaEsquina(int avenida, int calle) {
		//
		return papeles.hay(avenida, calle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#incrementarFlores(int, int)
	 */
	@Override
	public void incrementarFlores(int avenida, int calle) {
		flores.agregar(avenida, calle);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#incrementarPapeles(int, int)
	 */
	@Override
	public void incrementarPapeles(int avenida, int calle) {
		//
		papeles.agregar(avenida, calle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#setFlores(int, int, int)
	 */
	@Override
	public void setFlores(int avenida, int calle, int cant) {
		for (int i = 0; i < cant; i++) {
			incrementarFlores(avenida, calle);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#setObstaculos(int, int, boolean)
	 */
	@Override
	public void setObstaculo(int avenida, int calle) {
		//
		obstaculos.agregar(avenida, calle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#setPapeles(int, int, int)
	 */
	@Override
	public void setPapeles(int avenida, int calle, int cant) {
		for (int i = 0; i < cant; i++) {
			incrementarPapeles(avenida, calle);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#getCantidadAvenidas()
	 */
	@Override
	public int getCantidadAvenidas() {
		return avenidas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#getCantidadCalles()
	 */
	@Override
	public int getCantidadCalles() {
		return calles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#setCantidadAvenidas(int)
	 */
	@Override
	public void setCantidadAvenidasYCalles(int avenidas, int calles) {
		this.avenidas = avenidas;
		this.calles = calles;
		inicializarElementos(avenidas, calles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#visualizarMensaje(java.lang.String)
	 */
	@Override
	public void visualizarMensaje(Object mensaje) {
		// JOptionPane.showMessageDialog(panel, mensaje, "Información...",
		// JOptionPane.OK_OPTION);
		// TODO poner boton de abortar ejecucion
		System.out.println(mensaje);
	}

	public List<Robot> getRobots() {
		return robots;
	}

	@Override
	public void leerVariable(String nombreIdentificador, Dato variable) {

		String valor = JOptionPane.showInputDialog(panel,
				"Ingrese el valor para guardar en la variable " + nombreIdentificador,
				"Lectura de variable...", JOptionPane.QUESTION_MESSAGE);

		// verificamos el tipo de variable
		if (variable instanceof DatoEntero) {
			if (null == valor)
				variable.setValor(0);
			else
				try {
					variable.setValor(Integer.parseInt(valor));
				} catch (NumberFormatException n) {
					variable.setValor(0);
				}

		} else if (variable instanceof DatoTexto) {
			if (null == valor)
				variable.setValor("");
			else
				variable.setValor(valor);

		} else if (variable instanceof DatoLogico) {
			if (null == valor)
				variable.setValor(false);
			else
				variable.setValor((valor
						.compareToIgnoreCase(ParserConstants.tokenImage[ParserConstants.TRUE]
								.split("\"")[1]) == 0) ? true : false);

		} else {
			throw new RuntimeException(MessageFormat.format(
					Resource.getString("ciudad.variableDesconocida"), variable.getClass()));
		}

	}

	@Override
	public void establecerBolsaPapeles(int cantidad) {
		papelesEnBolsa = cantidad;
	}

	@Override
	public void establecerBolsaFlores(int cantidad) {
		floresEnBolsa = cantidad;
	}

	@Override
	public void permitirRobotsSuperpuestos(boolean superpuestos) {
		this.superpuestos = superpuestos;
	}

	@Override
	public boolean cantidadRobotsPermitida(int avenida, int calle) {
		boolean permitido = false;

		// verificmos si esta permitido que se superpongan
		if (superpuestos) {
			permitido = true;
		} else {
			int cant = 0;
			// recorremos los robot verificando si alguno se encuentra en la
			// avenida y calle observada
			for (Robot robot : robots) {
				if (robot.getPosAv() == avenida && robot.getPosCa() == calle)
					cant++;
			}
			permitido = cant <= 1;
		}
		return permitido;
	}

	@Override
	public boolean tieneRepresentacionGrafica() {
		return true;
	}

	/**
	 * Retorna el panel con la representacion gráfica de la ciudad
	 * 
	 * @return JPanel
	 */
	@Override
	public JPanel getRepresentacionGrafica() {
		return panel;
	}

	@Override
	public int getCantidadRobots() {
		return robots.size();
	}

}
