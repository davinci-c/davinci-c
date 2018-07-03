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

import java.util.List;

import javax.swing.JPanel;

import edu.davinci.lenguaje.Dato;

/**
 * Interface que establece las operacion que se deberan implementar.
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public interface Ciudad {

	// Cantidad maxima de avenidas por defecto
	public static final int AVENIDAS = 100;

	// Cantidad maxima de calles por defecto
	public static final int CALLES = 100;

	/**
	 * Agrega un robot a la ciudad.
	 * 
	 * @param robot
	 */
	public void agregarRobot(Robot robot);

	/**
	 * Agrega un robot a la ciudad.
	 * 
	 * @param nombre
	 *            String
	 */
	public void agregarRobot(String nombre);

	/**
	 * Decrementar flores.
	 * 
	 * @param avenida
	 * @param calle
	 */
	public void decrementarFlores(int avenida, int calle);

	/**
	 * Decrementar papeles.
	 * 
	 * @param avenida
	 * @param calle
	 */
	public void decrementarPapeles(int avenida, int calle);

	/**
	 * Retorna las flores que hay en un determinado punto.
	 * 
	 * @param avenida
	 *            int
	 * @param calle
	 *            int
	 * 
	 * @return int numero de flores
	 */
	public int getFlores(int avenida, int calle);

	/**
	 * Retorna los papeles que hay en un determinado punto.
	 * 
	 * @param avenida
	 *            int
	 * @param calle
	 *            int
	 * 
	 * @return int numero de papeles
	 */
	public int getPapeles(int avenida, int calle);

	/**
	 * Retorna el robots segun el nombre.
	 * 
	 * @param nombre
	 *            String nombre del robots
	 * 
	 * @return Robot retorna el robots especificado
	 */

	public Robot getRobot(String nombre);

	/**
	 * Retorna una lista con todos los robots
	 * 
	 * @return
	 */
	public List<Robot> getRobots();

	/**
	 * Retorna la cantidad de robots insertados
	 * 
	 * @return
	 */
	public int getCantidadRobots();

	/**
	 * Retorna si hay almenos una flor en la esquina.
	 * 
	 * @param avenida
	 *            int
	 * @param calle
	 *            int
	 * 
	 * @return boolean
	 */
	public boolean HayFlorEnLaEsquina(int avenida, int calle);

	/**
	 * Retorna si hay un obstaculo en un determinado punto.
	 * 
	 * @param avenida
	 * @param calle
	 * 
	 * @return boolean verdadero si hay obstaculo en el punto
	 */
	public boolean HayObstaculo(int avenida, int calle);

	/**
	 * Retorna si hay almenos un papel en la esquina.
	 * 
	 * @param avenida
	 *            int
	 * @param calle
	 *            int
	 * 
	 * @return boolean
	 */
	public boolean HayPapelEnLaEsquina(int avenida, int calle);

	/**
	 * incrementar flores.
	 * 
	 * @param avenida
	 * @param calle
	 */
	public void incrementarFlores(int avenida, int calle);

	/**
	 * incrementar papeles.
	 * 
	 * @param avenida
	 * @param calle
	 */
	public void incrementarPapeles(int avenida, int calle);

	/**
	 * Setea las flores q hay en un determinado punto.
	 * 
	 * @param avenida
	 *            int
	 * @param calle
	 *            int
	 * @param cant
	 *            int
	 */
	public void setFlores(int avenida, int calle, int cant);

	/**
	 * Setea un obstaculo en un determinado punto de la ciudad.
	 * 
	 * @param avenida
	 *            int
	 * @param calle
	 *            int
	 * @param obstaculo
	 */
	public void setObstaculo(int avenida, int calle);

	/**
	 * Setea los papeles que hay en un determinado punto.
	 * 
	 * @param avenida
	 *            int
	 * @param calle
	 *            int
	 * @param cant
	 *            int
	 */
	public void setPapeles(int avenida, int calle, int cant);

	/**
	 * Setea la cantidad maxima de avenidas y calles
	 * 
	 * @param avenidas
	 */
	public void setCantidadAvenidasYCalles(int avenidas, int calles);

	/**
	 * Retorna la cantidad maxima de avenidas NOTA: Establece a cero las flores,
	 * papeles y obstaculos en la ciudad
	 * 
	 * @return avenidas int correspondiente a la cantidad de avenidas
	 */
	public int getCantidadAvenidas();

	/**
	 * Retorna la cantidad maxima de calles NOTA: Establece a cero las flores,
	 * papeles y obstaculos en la ciudad
	 * 
	 * @return calles int correspondiente a la cantidad de calles
	 */
	public int getCantidadCalles();

	/**
	 * Visualiza mensajes en la ciudad
	 * 
	 * @param pila
	 */
	public void visualizarMensaje(Object pila);

	/**
	 * Guarda el estado de la ciudad (avenidas, calles, flores, papeles y
	 * obstaculos)
	 */
	// public void guardarEstado();

	/**
	 * Re establece el estado guardado de la ciudad (avenidas, calles, flores,
	 * papeles y obstaculos)
	 */
	// public void reEstablecerEstado();

	/**
	 * Pide el valor para asignarle a la variable
	 * 
	 * @param variable
	 */
	public void leerVariable(String nombre, Dato variable);

	/**
	 * Establece la cantidad de papeles que tendrán los robots de manera inicial
	 * 
	 * @param cantidad
	 */
	public void establecerBolsaPapeles(int cantidad);

	/**
	 * Establece la cantidad de flores que tendrán los robots de manera inicial
	 * 
	 * @param cantidad
	 */
	public void establecerBolsaFlores(int cantidad);

	/**
	 * Establece si los robots en la ciudad pueden superponerse
	 * 
	 * @param b
	 */
	public void permitirRobotsSuperpuestos(boolean superpuestos);

	/**
	 * En caso de permitirse los robots superpuestos retorna verdadero. En caso
	 * de NO permitirse los robots superpuestos retornará verdadero si solo hay
	 * uno
	 * 
	 * @param avenida
	 * @param calle
	 * @return
	 */
	public boolean cantidadRobotsPermitida(int avenida, int calle);

	/**
	 * Retorna verdadero si la ciudad tiene una representacion grafica para ser
	 * inscrustada
	 * 
	 * @return
	 */
	public boolean tieneRepresentacionGrafica();

	/**
	 * Retorna la represetacion de la ciudad en un panel
	 * 
	 * @return
	 */
	public JPanel getRepresentacionGrafica();

}
