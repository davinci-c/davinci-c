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
public class ASTSignoTermino extends SimpleNode {
	String signo;

	/**
	 * 
	 * @param id
	 */
	public ASTSignoTermino(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTSignoTermino(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {

		Debug.println("<<Signo>>");

		// intepretamos la expresion
		jjtGetChild(0).interpret();

		// almacenamos en la pila el valor de la expersion con el signo que lo
		// antecede (+1 o -1)
		// pila[indicePila] = new DatoEntero(((DatoEntero)
		// pila[indicePila]).getValor() * Integer.parseInt(signo + "1"));

		int multiplicador = (signo.equals("+")) ? 1 : -1;

		pila.push(new DatoEntero(((DatoEntero) pila.pop()).getValor() * multiplicador));

	}
}
