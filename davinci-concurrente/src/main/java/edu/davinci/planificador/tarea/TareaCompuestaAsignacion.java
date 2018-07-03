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
package edu.davinci.planificador.tarea;

import java.util.Hashtable;

import edu.davinci.lenguaje.SimpleNode;

/**
 * Clase encargada de representar la "seleccion" Una tarea de este tipo tiene la
 * responsabilidad de identificar el camino logico a seguir en funcion de la
 * condicion.
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class TareaCompuestaAsignacion extends TareaCompuesta {

	String identificador;

	/**
	 * Instantiates a new tarea compuesta seleccion.
	 * 
	 * @param nodo
	 * @param contexto
	 */
	public TareaCompuestaAsignacion(SimpleNode nodo, Hashtable<String, Object> contexto) {
		// inicializamos la tarea
		super(nodo, contexto);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.tarea.Tarea#ejecutar()
	 */
	@Override
	public void ejecutar() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.tarea.TareaCompuesta#getLinea()
	 */
	@Override
	public int getLinea() {
		return ((SimpleNode) getNodos().getNodo()).jjtGetFirstToken().beginLine;
	}

}
