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
public class ASTOperacionMul extends SimpleNode {

	/**
	 * 
	 * @param id
	 */
	public ASTOperacionMul(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTOperacionMul(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {

		Debug.println("<<Mult>>");

		// Interpretamos el lado izquierdo de la expresion
		jjtGetChild(0).interpret();
		Dato izquierdo = (Dato) pila.pop();

		// Intepretamos el operador de la expresion
		jjtGetChild(1).interpret();
		String operador = (String) pila.pop();

		// Intepretamos el lado derecho de la expresion
		jjtGetChild(2).interpret();
		Dato derecho = (Dato) pila.pop();

		// Dependiendo del operador (guardado en la pila) interpretamos la
		// operacion
		Dato valor = null;
		if (operador == "*") {
			valor = new DatoEntero(
					((DatoEntero) izquierdo).multiplica((DatoEntero) derecho));
		} else if (operador == "/") {
			valor = new DatoEntero(
					((DatoEntero) izquierdo).divide((DatoEntero) derecho));
		} else if (operador == "%") {
			valor = new DatoEntero(
					((DatoEntero) izquierdo).modulo((DatoEntero) derecho));
		} else if (operador == "&") {
			valor = new DatoLogico(
					((DatoLogico) izquierdo).multiplica((DatoLogico) derecho));
		}

		// Almacenamos el resultado de la expresion en la pila
		pila.push(valor);

	}

}
