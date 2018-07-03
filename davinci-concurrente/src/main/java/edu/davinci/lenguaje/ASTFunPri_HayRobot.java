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

public class ASTFunPri_HayRobot extends ASTPrimitiva {
	public ASTFunPri_HayRobot(int id) {
		super(id);
	}

	public ASTFunPri_HayRobot(Parser p, int id) {
		super(p, id);
	}

	@Override
	public void interpret() {

		Debug.println("<<Pri_HayRobot>>");

		// Obtenemos el robot adecuado
		Robot robot = super.getRobot();

		int av, ca;

		boolean hayRobot = false;

		// verificamos si tiene avenida y calle como parametro
		if (jjtGetNumChildren() != 0) {
			// Interpretamos la expresion de la posicion de la Avenida
			jjtGetChild(0).interpret();

			// guardamos la avenida
			av = ((DatoEntero) pila.pop()).getValor();

			// Interpretamos la expresion de la posicion de la Calle
			jjtGetChild(1).interpret();

			// guardamos la calle
			ca = ((DatoEntero) pila.pop()).getValor();

			hayRobot = robot.hayRobot(av, ca);
		} else {
			// averiguamos donde esta y a donde debe mirar
			hayRobot = robot.hayRobot();
		}

		// almaceno el valor en la pila
		pila.push(new DatoLogico(hayRobot));
	}

}
