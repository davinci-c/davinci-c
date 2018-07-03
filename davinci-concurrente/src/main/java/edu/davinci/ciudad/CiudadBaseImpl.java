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
package edu.davinci.ciudad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.sun.org.apache.xpath.internal.operations.Variable;

import edu.davinci.lenguaje.Dato;
import edu.davinci.lenguaje.DatoEntero;
import edu.davinci.lenguaje.DatoLogico;
import edu.davinci.lenguaje.DatoTexto;
import edu.davinci.lenguaje.ParserConstants;
import edu.davinci.utils.Resource;

/**
 * Implentacion en consola de la ciudad.
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class CiudadBaseImpl implements Ciudad, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3386193595291681833L;

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

	/**
	 * Constructor
	 */
	public CiudadBaseImpl() {
		// seteamos las avenidas con el valor por defecto
		avenidas = AVENIDAS;
		// seteamos las calles con el valor por defecto
		calles = CALLES;
		// inicializamos los robots en la ciudad
		robots = new ArrayList<Robot>();
		// inicializamos flores, papeles y obstaculos
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
		flores = new FlorSimpleImpl(av, ca);

		//
		papeles = new PapelSimpleImpl(av, ca);

		//
		obstaculos = new ObstaculoSimpleImpl(av, ca);

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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#agregarRobot(java.lang.String)
	 */
	@Override
	public void agregarRobot(String nombre) {

		// insertamos el robot
		agregarRobot(new RobotBaseImpl(nombre, this));
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
	 * @see edu.davinci.ciudad.Ciudad#setCantidadCalles(int)
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
		System.out.println(mensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.ciudad.Ciudad#getRobots()
	 */
	public List<Robot> getRobots() {
		return robots;
	}

	@Override
	public void leerVariable(String nombreIdentificador, Dato variable) {

		// String valor = JOptionPane.showInputDialog(null,
		// "Ingrese el valor para guardar en la variable " +
		// nombreIdentificador,
		// "Lectura de variable...", JOptionPane.QUESTION_MESSAGE);

		// Scanner scanner = new Scanner(System.in);
		// String valor = scanner.nextLine();
		// scanner.close();

		System.out.print(MessageFormat.format(Resource.getString("ciudad.variable.lectura"),
				nombreIdentificador));

		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String valor;
		try {
			valor = bufferRead.readLine();
		} catch (IOException e1) {
			valor = null;
		}

		// verificamos el tipo de variable
		if (variable instanceof DatoEntero) {
			try {
				variable.setValor(Integer.parseInt(valor));
			} catch (Exception n) {
				variable.setValor(0);
			}

		} else if (variable instanceof DatoTexto) {
			if (null == valor)
				variable.setValor("");
			else
				variable.setValor(valor);

		} else if (variable instanceof DatoLogico) {
			try {
				variable.setValor((valor
						.compareToIgnoreCase(ParserConstants.tokenImage[ParserConstants.TRUE]
								.split("\"")[1]) == 0) ? true : false);
			} catch (Exception e) {
				variable.setValor(false);
			}

		} else {
			throw new RuntimeException(MessageFormat.format(
					Resource.getString("ciudad.variableDesconocida"), Variable.class));
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
		return false;
	}

	@Override
	public JPanel getRepresentacionGrafica() {
		return null;
	}

	@Override
	public int getCantidadRobots() {
		return robots.size();
	}

}
