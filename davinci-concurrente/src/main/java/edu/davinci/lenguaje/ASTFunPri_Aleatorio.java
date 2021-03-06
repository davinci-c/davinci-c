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

import java.util.Random;

/**
 * The Class ASTFunPri_Aleatorio.
 */
public class ASTFunPri_Aleatorio extends SimpleNode {

	/**
	 * Instantiates a new aST fun pri_ aleatorio.
	 * 
	 * @param id
	 */
	public ASTFunPri_Aleatorio(int id) {
		super(id);
	}

	/**
	 * Instantiates a new aST fun pri_ aleatorio.
	 * 
	 * @param p
	 * @param id
	 */
	public ASTFunPri_Aleatorio(Parser p, int id) {
		super(p, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.lenguaje.MyNode#interpret()
	 */
	@Override
	public void interpret() {
		Debug.println("<<Pri_Aleatorio>>");

		// Interpretamos la expresion de la funcion
		jjtGetChild(0).interpret();

		// guardamos la avenida
		// int value = ((DatoEntero) pila[indicePila--]).getValor();
		int value = ((DatoEntero) pila.pop()).getValor();

		Random r = new Random();
		// pila[++indicePila] = new DatoEntero(r.nextInt(value));
		pila.push(new DatoEntero(r.nextInt(value)));

	}

}
