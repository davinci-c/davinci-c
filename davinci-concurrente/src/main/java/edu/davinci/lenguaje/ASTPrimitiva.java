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
package edu.davinci.lenguaje;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.ciudad.Robot;
import edu.davinci.interprete.Interprete;
import edu.davinci.planificador.PlanificadorUtils;

/**
 * The Class ASTPrimitiva.
 */
public class ASTPrimitiva extends SimpleNode {

	/**
	 * Instantiates a new aST primitiva.
	 * 
	 * @param id
	 */
	public ASTPrimitiva(int id) {
		super(id);
	}

	/**
	 * Instantiates a new aST primitiva.
	 * 
	 * @param p
	 * @param id
	 */
	public ASTPrimitiva(Parser p, int id) {
		super(p, id);
	}

	/**
	 * Gets the nombre del robot.
	 * 
	 * @return nombre del robot
	 */
	public String getNombreDelRobot() {
		return PlanificadorUtils.getPlanificador().obtenerTareaEnEjecucion().getNombre();
	}

	public Robot getRobot() {
		// Obtenemos la ciudad
		Ciudad ciudad = Interprete.getCiudad();

		// Obtenemos el robot de la ciudad
		Robot r = ciudad.getRobot(getNombreDelRobot());

		if (null == r)
			throw new RuntimeException("no se inserto el robot " + getNombreDelRobot()
					+ " en la ciudad");

		return r;

	}

}
