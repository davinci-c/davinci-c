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

import java.text.MessageFormat;
import java.util.Iterator;

import edu.davinci.lenguaje.ParserMyConstants;
import edu.davinci.utils.Resource;

/**
 * Clase que materializa al robot y sus operaciones dentro de una Ciudad
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class RobotBaseImpl extends Elemento implements Robot {

	/** The nombre. */
	private String nombre;

	/** The direccion. */
	protected Direccion direccion;

	/** The avenida. */
	// protected int avenida;

	/** The calle. */
	// protected int calle;

	/** The bolsa flores. */
	private int bolsaFlores;

	/** The bolsa papeles. */
	private int bolsaPapeles;

	// mantenemos una referencia de la ciudad para verificar limites y
	// obstaculos
	/** The ciudad. */
	protected Ciudad ciudad;

	/**
	 * Instantiates a new robot.
         * @param ciudad
	 */
	public RobotBaseImpl(Ciudad ciudad) {
		this(ParserMyConstants.ROBOT_POR_DEFECTO, ciudad);
	}

	/**
	 * Instantiates a new robot.
	 * 
	 * @param nombre
         * @param ciudad
	 */
	public RobotBaseImpl(String nombre, Ciudad ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		direccion = new Direccion();
		avenida = 1;
		calle = 1;
		bolsaFlores = 0;
		bolsaPapeles = 0;

	}

	/**
	 * Agregar flor en la bolsa.
	 */
	private void agregarFlorEnLaBolsa() {
		bolsaFlores++;
	}

	/**
	 * Agregar papel en la bolsa.
	 */
	private void agregarPapelEnLaBolsa() {
		bolsaPapeles++;
	}

	/**
	 * Depositar flor.
	 * 
	 * @param avenida
	 * @param calle
	 */
	private void depositarFlor(int avenida, int calle) {
		sacarFlorDeLaBolsa();
		ciudad.incrementarFlores(avenida, calle);

	}

	/**
	 * Depositar papel.
	 * 
	 * @param avenida
	 * @param calle
	 */
	private void depositarPapel(int avenida, int calle) {
		sacarPapelDeLaBolsa();
		ciudad.incrementarPapeles(avenida, calle);

	}

	/**
	 * Sacar flor de la bolsa.
	 */
	private void sacarFlorDeLaBolsa() {
		bolsaFlores--;
		if (bolsaFlores < 0)
			throw new RuntimeException(MessageFormat.format(
					Resource.getString("robot.bolsaSinFlor"), getNombre()));
	}

	/**
	 * Sacar papel de la bolsa.
	 */
	private void sacarPapelDeLaBolsa() {
		bolsaPapeles--;
		if (bolsaPapeles < 0)
			throw new RuntimeException(MessageFormat.format(
					Resource.getString("robot.bolsaSinPapel"), getNombre()));
	}

	/**
	 * Tomar flor.
	 * 
	 * @param avenida
	 * @param calle
	 */
	private void tomarFlor(int avenida, int calle) {
		if (ciudad.getFlores(avenida, calle) >= 1) {
			// decrementa la cantidad de flores de la esquina
			ciudad.decrementarFlores(avenida, calle);
			// incrementamos las flores de la bolsa
			agregarFlorEnLaBolsa();
		} else
			throw new RuntimeException(MessageFormat.format(Resource.getString("robot.noHayFlor"),
					getNombre(), avenida, calle));

	}

	/**
	 * Tomar el papel de un punto de la ciudad.
	 * 
	 * @param avenida
	 * @param calle
	 */
	private void tomarPapel(int avenida, int calle) {
		if (ciudad.getPapeles(avenida, calle) >= 1) {
			// decrementamos la cantidad de papeles en la esquina
			ciudad.decrementarPapeles(avenida, calle);
			// incrementamos los papeles de la bolsa
			agregarPapelEnLaBolsa();
		} else
			throw new RuntimeException(MessageFormat.format(Resource.getString("robot.noHayPapel"),
					getNombre(), avenida, calle));

	}

	/**
	 * Toma el papel donde se encuentra posicionado
	 */
        @Override
	public void tomarPapel() {
		tomarPapel(avenida, calle);
	}

	/**
	 * Toma la flor donde se encuentra posicionado
	 */
        @Override
	public void tomarFlor() {
		tomarFlor(avenida, calle);
	}

	/**
	 * Setea las posiciones donde se encuentra el robot
	 * 
	 * @param av
	 * @param ca
	 */
        @Override
	public void posicionarse(int av, int ca) {
		avenida = av;
		calle = ca;

		verificarEstadoDelRobot();
	}

	/**
	 * Deposita una flor en la esquina que se encuentra posicionado
	 */
        @Override
	public void depositarFlor() {
		depositarFlor(avenida, calle);

	}

	/**
	 * Deposita un papel en la esquina que se encuentra posicionado
	 */
        @Override
	public void depositarPapel() {
		depositarPapel(avenida, calle);

	}

	/**
	 * rota a la derecha.
	 */
        @Override
	public void derecha() {
		direccion.derecha();
	}

	/**
	 * retorna el nombre.
	 * 
	 * @return the nombre
	 */
        @Override
	public String getNombre() {
		return nombre;
	}

	/**
	 * retorna en que avenida se encuentra.
	 * 
	 * @return the pos av
	 */
        @Override
	public int getPosAv() {
		return avenida;
	}

	/**
	 * retorna en que calle se encuentra.
	 * 
	 * @return the pos ca
	 */
        @Override
	public int getPosCa() {
		return calle;
	}

	/**
	 * Hay flor en la bolsa.
	 * 
	 * @return true, if successful
	 */
        @Override
	public boolean hayFlorEnLaBolsa() {
		return bolsaFlores > 0;
	}

	/**
	 * Hay papel en la bolsa.
	 * 
	 * @return true, if successful
	 */
        @Override
	public boolean hayPapelEnLaBolsa() {
		return bolsaPapeles > 0;
	}

	/**
	 * Avanza al robot en la direccion que se encuentra.
	 */
        @Override
	public void mover() {

		//
		String desde = String.format("[%s,%s]-->", avenida, calle);

		if (direccion.getSentido() == Direccion.NORTE)
			calle++;
		else if (direccion.getSentido() == Direccion.SUR)
			calle--;
		else if (direccion.getSentido() == Direccion.ESTE)
			avenida++;
		else if (direccion.getSentido() == Direccion.OESTE)
			avenida--;

		System.out.println(String.format("//%s::%s[%s,%s]", nombre, desde, avenida, calle));
  
		verificarEstadoDelRobot();

	}

	protected void verificarEstadoDelRobot() {
		// verificamos la superposicion con otro robots
		if (!ciudad.cantidadRobotsPermitida(avenida, calle)) {
			// verificamos que no sea la esquina (1,1)
			if (!(avenida == 1 & calle == 1))
				throw new RuntimeException(Resource.getString("ciudad.superpuestos"));
		}// verificamos que este dentro de los limites
		else if (calle > ciudad.getCantidadCalles() || avenida > ciudad.getCantidadAvenidas()
				|| calle <= 0 || avenida <= 0) {
			throw new RuntimeException(MessageFormat.format(Resource.getString("robot.excedio"),
					getNombre()));
		}// verificamos que no este arriba de un obstaculo
		else if (ciudad.HayObstaculo(avenida, calle)) {
			throw new RuntimeException(MessageFormat.format(Resource.getString("robot.obstaculo"),
					getNombre(), avenida, calle));
		}

	}

	/**
	 * Hay flor en la esquina.
	 * 
	 * @return true, if successful
	 */
        @Override
	public boolean hayFlorEnLaEsquina() {
		return ciudad.HayFlorEnLaEsquina(avenida, calle);
	}

	/**
	 * Hay papel en la esquina.
	 * 
	 * @return true, if successful
	 */
        @Override
	public boolean hayPapelEnLaEsquina() {
		return ciudad.HayPapelEnLaEsquina(avenida, calle);
	}

	/**
	 * Hay obstaculo en la esquina.
	 * 
	 * @return true, if successful
	 */
        @Override
	public boolean hayObstaculo() {
		// TODO esto funciona??????
		// no debería mirar la siguiente calle o avenida segun dirección?????
		//return ciudad.HayObstaculo(avenida, calle);
            
                // establezco la posicion del robot
		int av = avenida;
		int ca = calle;

		// verifico la orientacion para averiguar si hay obstaculo en la proxima
		// avenida o calle
		if (direccion.getSentido() == Direccion.NORTE)
			ca++;
		else if (direccion.getSentido() == Direccion.SUR)
			ca--;
		else if (direccion.getSentido() == Direccion.ESTE)
			av++;
		else if (direccion.getSentido() == Direccion.OESTE)
			av--;

		return ciudad.HayObstaculo(av, ca);
	}

	/**
	 * inicializa la cantidad de flores en la bolsa
	 * 
	 * @param bolsaFlores
	 */
        @Override
	public void setBolsaFlores(int bolsaFlores) {
		this.bolsaFlores = bolsaFlores;
	}

	/**
	 * inicializa la cantidad de papeles en la bolsa
	 * 
	 * @param bolsaPapeles
	 */
        @Override
	public void setBolsaPapeles(int bolsaPapeles) {
		this.bolsaPapeles = bolsaPapeles;
	}

	@Override
	public int getBolsaFlores() {
		return bolsaFlores;
	}

	@Override
	public int getBolsaPapeles() {
		return bolsaPapeles;
	}

	@Override
	public Direccion getDireccion() {
		return direccion;
	}

	@Override
	public String getOrientacion() {
		return direccion.toString();
	}

	@Override
	public boolean hayRobot(int avenida, int calle) {

		// marca para retornar si hay un robot en la poscion que me pasaron
		boolean hayRobot = false;

		try {
			// verificamos si hay un robot en la avenida y calle que me pasaron

			Iterator<Robot> robots = ciudad.getRobots().iterator();

			// Robot para iterar;
			Robot robot;

			while (robots.hasNext() && !hayRobot) {

				// obtenego el robot
				robot = robots.next();

				// verifico si se encuentra en la posicion
				hayRobot = (robot.getPosAv() == avenida) && (robot.getPosCa() == calle);
			}

		} catch (Exception e) {
			System.out.println("error al averiguar si hay robot: " + e.getMessage());
		}

		return hayRobot;
	}

	@Override
	public boolean hayRobot() {
		// establezco la posicion del robot
		int av = avenida;
		int ca = calle;

		// verifico la orientacion para averiguar si hay robot en la proxima
		// avenida o calle
		if (direccion.getSentido() == Direccion.NORTE)
			ca++;
		else if (direccion.getSentido() == Direccion.SUR)
			ca--;
		else if (direccion.getSentido() == Direccion.ESTE)
			av++;
		else if (direccion.getSentido() == Direccion.OESTE)
			av--;

		return hayRobot(av, ca);
	}
}
