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
package edu.davinci.planificador;

/**
 * Clase encargada de mantener al planificador utilizado en la ejecución.
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class PlanificadorUtils {

	/** planificador. */
	private static Planificador planificador;

	/**
	 * Retorna el planificador.
	 * 
	 * @return Planificador
	 */
	public static Planificador getPlanificador() {
		return planificador;
	}

	/**
	 * Setea el planificador.
	 * 
	 * @param pl
	 *            el nuevo planificador a utilizar
	 */
	public static void setPlanificador(Planificador pl) {
		planificador = pl;
	}

}
