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

@SuppressWarnings("all")
public class ASTDefinicionHilo extends SimpleNode {
	String nombre;

	/**
	 * 
	 * @param id
	 */
	public ASTDefinicionHilo(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTDefinicionHilo(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {
		Debug.println("<<Definicion hilo:" + nombre + ">>");

		// Almacenamos los nodos del hilo para su posterior interpretacion
		// Nodos.put(nombre, children);
		DefinicionProcedimientos.put(nombre, this);
	}

}
