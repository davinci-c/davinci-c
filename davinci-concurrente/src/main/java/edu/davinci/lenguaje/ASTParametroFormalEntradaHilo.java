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
public class ASTParametroFormalEntradaHilo extends SimpleNode {
	String nombre;
	int tipo;

	/**
	 * 
	 * @param id
	 */
	public ASTParametroFormalEntradaHilo(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTParametroFormalEntradaHilo(Parser p, int id) {
		super(p, id);
	}

	/**
	 * Retorna el nombre del parametro formal de entrada del hilo
	 * 
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre.toLowerCase();
	}

}
