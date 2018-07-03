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

import edu.davinci.ciudad.Robot;

@SuppressWarnings("all")
public class ASTPri_Pos extends ASTPrimitiva {

	/**
	 * 
	 * @param id
	 */
	public ASTPri_Pos(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTPri_Pos(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {
		Debug.println("<<Pos>>");

		// Obtenemos el robot adecuado
		Robot robot = super.getRobot();

		// Interpretamos la expresion de la posicion de la Avenida
		jjtGetChild(0).interpret();

		// guardamos la avenida
		// int av = ((DatoEntero) pila[indicePila--]).getValor();
		int av = ((DatoEntero) pila.pop()).getValor();

		// Interpretamos la expresion de la posicion de la Calle
		jjtGetChild(1).interpret();

		// guardamos la calle
		// int ca = ((DatoEntero) pila[indicePila--]).getValor();
		int ca = ((DatoEntero) pila.pop()).getValor();

		// posicionamos el robot
		robot.posicionarse(av, ca);

	}
}
