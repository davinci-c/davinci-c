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

@SuppressWarnings("all")
public class ASTInformar extends SimpleNode {

	/**
	 * 
	 * @param id
	 */
	public ASTInformar(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTInformar(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {
		Debug.println("<<Informar>>");

		Ciudad ciudad = Interprete.getCiudad();

		String mensaje = "";
		// Interpretamos las expresiones
		for (int i = 0; i <= jjtGetNumChildren() - 1; i++) {
			jjtGetChild(i).interpret();

			// concatenamos los mensajes
			// mensaje = mensaje + pila[indicePila];
			mensaje = mensaje + pila.pop();
		}
		// Visualizamos los resultados de las expresiones
		ciudad.visualizarMensaje(mensaje);
		// inicializamos la pila por medio del indice
		// indicePila = -1;

	}

}
