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

import java.util.Random;

/**
 * Clase encargada de planificar las tareas de forma aleatoria
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class PlanificadorAleatorioImpl extends PlanificadorEsencialImpl {

	/** aleatorio. */
	private Random aleatorio;

	/**
	 * Constructor
	 */
	public PlanificadorAleatorioImpl() {
		super();
		aleatorio = new Random();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.PlanificadorImpl#getProximaTarea()
	 */
	@Override
	protected int getProximaTarea() {
		return aleatorio.nextInt(tareasActivas.size());
	}

	@Override
	public String nombre() {
		return "planificador aleatorio";
	}
        
        @Override
	public String toString() {
		return "Planificador aleatorio";
	}

}
