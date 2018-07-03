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

/**
 * The Class ASTBloque.
 */
@SuppressWarnings("all")
public class ASTBloque extends SimpleNode {

	/**
	 * 
	 * 
	 * @param id
	 */
	public ASTBloque(int id) {
		super(id);
	}

	/**
	 * 
	 * 
	 * @param p
	 * @param id
	 */
	public ASTBloque(Parser p, int id) {
		super(p, id);
	}

	// La interpretacion del nodo la realizamos dentro del planificador/tarea
	// public void interpret(){}

}
