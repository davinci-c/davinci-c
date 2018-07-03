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
public class ASTOperacionNeg extends SimpleNode {

	/**
	 * 
	 * @param id
	 */
	public ASTOperacionNeg(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTOperacionNeg(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {
		Debug.println("<<NegacionFactor>>");

		// Intepretamos el lado deracho de la expresion
		jjtGetChild(0).interpret();

		// Almacenamos en la pila la negacion del valor contenido en la cabecera
		// de la pila
		Dato valor = new DatoLogico(((DatoLogico) pila.pop()).niega());

		pila.push(valor);

	}

}
