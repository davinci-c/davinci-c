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
public class ASTOperacionSum extends SimpleNode {

	/**
	 * 
	 * @param id
	 */
	public ASTOperacionSum(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTOperacionSum(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {

		Debug.println("<<Add>>");

		// Interpretamos el lado izquierdo de la expresion
		jjtGetChild(0).interpret();
		Dato izquierdo = (Dato) pila.pop();

		// Intepretamos el operador de la expresion
		jjtGetChild(1).interpret();
		String operador = (String) pila.pop();

		// Intepretamos el lado derecho de la expresion
		jjtGetChild(2).interpret();
		Dato derecho = (Dato) pila.pop();

		// dependiendo del operador, contenido en la pila, de la expresion
		// realizamos la intepretacion
		// de la operacion aditiva
		Dato valor = null;

		// operador: suma
		if (operador.equals("+")) {
			if (izquierdo instanceof DatoTexto)
				valor = new DatoTexto(
						((DatoTexto) izquierdo).suma((DatoTexto) derecho));

			else
				valor = new DatoEntero(
						((DatoEntero) izquierdo).suma((DatoEntero) derecho));

			// operador: resta

		} else if (operador.equals("-")) {
			valor = new DatoEntero(
					((DatoEntero) izquierdo).resta((DatoEntero) derecho));

			// operador: or

		} else if (operador.equals("|")) {
			valor = new DatoLogico(
					((DatoLogico) izquierdo).suma((DatoLogico) derecho));

		}

		// almacenamos en la pila el resultado de la expresion

		pila.push(valor);
	}

}
