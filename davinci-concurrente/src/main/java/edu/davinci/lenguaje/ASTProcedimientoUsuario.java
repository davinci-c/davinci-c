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

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ASTProcedimientoUsuario extends SimpleNode implements ComplexNode {
	String nombre;

	private List<String> nombreParametrosReales = new ArrayList<String>();

	/**
	 * 
	 * @param id
	 */
	public ASTProcedimientoUsuario(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTProcedimientoUsuario(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 * @param s
	 */
	public void addNombreParametrosReales(String s) {
		if (s != null)
			nombreParametrosReales.add(s.toLowerCase());
		else
			nombreParametrosReales.add(null);
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre.toLowerCase();
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getNombresParametrosReales() {
		return nombreParametrosReales;
	}

	// La interpretacion del nodo la realizamos dentro del planificador
	// public void interpret(){}

}
