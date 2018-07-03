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

import java.util.List;

/**
 * Clase encargada de planificar las tareas de acuerdo a la secuencia que se
 * obtuvo de otra planificacion.
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class PlanificadorRepetitivoImpl extends PlanificadorEsencialImpl {

	/** The secuencia. */
	List<Secuencia> secuencia;

	/**
	 * Instantiates a new planificador repetitivo impl.
	 * 
	 * @param list
	 */
	public PlanificadorRepetitivoImpl(List<Secuencia> list) {
		super();
		secuencia = list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.PlanificadorImpl#getProximaTarea()
	 */
	@Override
	protected int getProximaTarea() {

		// retornamos el indice de la proxima tarea
		return secuencia.remove(0).getIndice();
	}

	@Override
	public boolean hayTareasParaEjecutar() {
		// verificamos que en la ejecucion existan secuencias
		return (super.hayTareasParaEjecutar()) && (secuencia.size() > 0);
	}

	@Override
	public String nombre() {
		return "planificador repetitivo";
	}

}
