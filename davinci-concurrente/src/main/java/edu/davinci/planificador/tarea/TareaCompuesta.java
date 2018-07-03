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
 * Clase abstracta encargada de reprentar tareas que se deben descomponer
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public abstract class TareaCompuesta extends Tarea {

	/** finalizo la tarea. */
	protected boolean finalizo;

	/**
	 * constructor *.
	 * 
	 * @param nodo
	 * @param contexto
	 */
	public TareaCompuesta(SimpleNode nodo, Hashtable<String, Object> contexto) {
		super.setNodos(nodo);
		super.setContexto(contexto);
		finalizo = false;
	}

	/**
	 * Finalizo.
	 * 
	 * @return true, if successful
	 */
	public boolean finalizo() {
		return finalizo;
	}

	/**
	 * Finaliza la tarea.
	 */
	public void finalizar() {
		finalizo = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.tarea.Tarea#getLinea()
	 */
	@Override
	public abstract int getLinea();

	/*
	 * @Override public void ejecutar() {
	 * 
	 * // verificamos si se encuentra en fase de definicion while
	 * (enFaseDeDefinicion(this)) { super.ejecutar(); } // super.ejecutar(); }
	 * 
	 * private boolean enFaseDeDefinicion(Tarea t) { try { Node nodo =
	 * t.getNodos().getNodo(); return (nodo instanceof ASTDefinicionHilo) ||
	 * (nodo instanceof ASTDefinicionProcedimiento) || (nodo instanceof
	 * ASTDefinicionVariable); } catch (Exception e) { return false; }
	 * 
	 * }
	 */

}
