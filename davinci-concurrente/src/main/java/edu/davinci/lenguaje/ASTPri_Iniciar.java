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

import edu.davinci.ciudad.Ciudad;
import edu.davinci.interprete.Interprete;

/**
 * The Class ASTPri_Iniciar.
 */
@SuppressWarnings("all")
public class ASTPri_Iniciar extends ASTPrimitiva {

	/**
	 * 
	 * 
	 * @param id
	 */
	public ASTPri_Iniciar(int id) {
		super(id);
	}

	/**
	 * 
	 * 
	 * @param p
	 * @param id
	 */
	public ASTPri_Iniciar(Parser p, int id) {
		super(p, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lenguaje.MyNode#interpret()
	 */
	public void interpret() {
		Debug.println("<<Pri_Iniciar>>");

		// Obtenemos la ciudad
		Ciudad ciudad = Interprete.getCiudad();

		// agregamos el robot a la ciudad
		ciudad.agregarRobot(super.getNombreDelRobot());

		// Almacenamos la ciudad en la tabla de simbolos
		getTablaDeSimbolos().put(ParserMyConstants.CIUDAD, ciudad);
	}

}
