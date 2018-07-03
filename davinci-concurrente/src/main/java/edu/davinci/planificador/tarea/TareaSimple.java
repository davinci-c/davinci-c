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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.davinci.lenguaje.Node;

/**
 * Clase que mantiene el conjunto de instruciones interpretables
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class TareaSimple {

	/** The nodo. */
	private List<Node> nodo;

	/** The indice. */
	private int indice;

	/**
	 * Instantiates a new tarea simple.
	 */
	public TareaSimple() {
		this(new Node[0]);
	}

	/**
	 * Instantiates a new tarea simple.
	 * 
	 * @param nodos
	 */
	public TareaSimple(Node[] nodos) {
		if (nodos == null)
			nodo = new ArrayList<Node>();
		else
			nodo = new ArrayList<Node>(Arrays.asList(nodos));
		indice = 0;
	}

	/**
	 * Gets the indice.
	 * 
	 * @return the indice
	 */
	public int getIndice() {
		return indice;
	}

	/**
	 * Gets the nodo.
	 * 
	 * @return the nodo
	 */
	public Node getNodo() {
		return nodo.get(getIndice());
	}

	/**
	 * Incrementar indice.
	 */
	public void incrementarIndice() {
		indice++;
	}

	/**
	 * Reestablecer indice.
	 */
	public void reestablecerIndice() {
		indice = 0;
	}

	/**
	 * Hay otro nodo.
	 * 
	 * @return true, if successful
	 */
	public boolean hayOtroNodo() {
		return indice < nodo.size();
	}

	/**
	 * Gets the nodos.
	 * 
	 * @return the nodos
	 */
	public List<Node> getNodos() {
		return nodo;
	}

}
