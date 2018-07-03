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

public class Identificador {
	private String nombre;
	private int tipo;

	/**
	 * Constructor
	 */
	public Identificador() {
		this(null, 0);
	}

	/**
	 * Public Constructor
	 * 
	 * @param nombre
	 *            Nombre del identifiacdor
	 * @param tipo
	 *            tipo de dato
	 */
	public Identificador(String name, int type) {
		nombre = name;
		tipo = type;

	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @return
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String name) {
		nombre = name;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(int type) {
		tipo = type;
	}
}
