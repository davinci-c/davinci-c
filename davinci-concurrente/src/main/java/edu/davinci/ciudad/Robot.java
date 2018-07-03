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

public interface Robot {

	/**
	 * Toma el papel donde se encuentra posicionado
	 */
	public void tomarPapel();

	/**
	 * Toma la flor donde se encuentra posicionado
	 */
	public void tomarFlor();

	/**
	 * Setea las posiciones donde se encuentra el robot
	 * 
	 * @param av
	 * @param ca
	 */
	public void posicionarse(int av, int ca);

	/**
	 * Deposita una flor en la esquina que se encuentra posicionado
	 */
	public void depositarFlor();

	/**
	 * Deposita un papel en la esquina que se encuentra posicionado
	 */
	public void depositarPapel();

	/**
	 * rota a la derecha.
	 */
	public void derecha();

	/**
	 * retorna el nombre.
	 * 
	 * @return the nombre
	 */
	public String getNombre();

	/**
	 * retorna en que avenida se encuentra.
	 * 
	 * @return the pos av
	 */
	public int getPosAv();

	/**
	 * retorna en que calle se encuentra.
	 * 
	 * @return the pos ca
	 */
	public int getPosCa();

	/**
	 * Hay flor en la bolsa.
	 * 
	 * @return true, if successful
	 */
	public boolean hayFlorEnLaBolsa();

	/**
	 * Hay papel en la bolsa.
	 * 
	 * @return true, if successful
	 */
	public boolean hayPapelEnLaBolsa();

	/**
	 * Avanza al robot en la direccion que se encuentra.
	 */
	public void mover();

	/**
	 * Hay flor en la esquina.
	 * 
	 * @return true, if successful
	 */
	public boolean hayFlorEnLaEsquina();

	/**
	 * Hay papel en la esquina.
	 * 
	 * @return true, if successful
	 */
	public boolean hayPapelEnLaEsquina();

	/**
	 * Hay obstaculo en la esquina.
	 * 
	 * @return true, if successful
	 */
	public boolean hayObstaculo();

	/**
	 * inicializa la cantidad de flores en la bolsa
	 * 
	 * @param bolsaFlores
	 */
	public void setBolsaFlores(int bolsaFlores);

	/**
	 * inicializa la cantidad de papeles en la bolsa
	 * 
	 * @param bolsaPapeles
	 */
	public void setBolsaPapeles(int bolsaPapeles);

	/**
	 * retorna la cantidad de flores que el robot posee en su bolsa
	 * 
	 * @return
	 */
	public int getBolsaFlores();

	/**
	 * retorna la cantidad papeles que el robot posee en su bolsa
	 * 
	 * @return
	 */
	public int getBolsaPapeles();

	/**
	 * retorna la direccion del robot
	 * 
	 * @return
	 */
	public Direccion getDireccion();

	/**
	 * retorna hacia donde se encuentra orrientado
	 * 
	 * @return
	 */
	public String getOrientacion();

	/**
	 * retorna verdadero si hay un robot en la interseccion, caso contrario
	 * falso.
	 * 
	 * @param avenida
	 * @param calle
	 * @return
	 */
	public boolean hayRobot(int avenida, int calle);

	/**
	 * retorna verdadero si hay un robot en la proxima avenida o calle segun
	 * hacia donde se encuentre orientado el robot, caso contrario falso.
	 * 
	 * @param avenida
	 * @param calle
	 * @return
	 */
	public boolean hayRobot();
}
