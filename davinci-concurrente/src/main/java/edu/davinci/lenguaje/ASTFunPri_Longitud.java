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

/**
 * The Class ASTFunPri_Tamaño.
 */
public class ASTFunPri_Longitud extends SimpleNode {

	/**
	 * Instantiates a new aST fun pri_ tamaño.
	 * 
	 * @param id
	 */
	public ASTFunPri_Longitud(int id) {
		super(id);
	}

	/**
	 * Instantiates a new aST fun pri_ tamaño.
	 * 
	 * @param p
	 * @param id
	 */
	public ASTFunPri_Longitud(Parser p, int id) {
		super(p, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.lenguaje.MyNode#interpret()
	 */
	@Override
	public void interpret() {
		Debug.println("<<Pri_Tamaño>>");

		// Interpretamos la expresion de la funcion
		jjtGetChild(0).interpret();

		// guardamos el texto
		// String value = ((DatoTexto) pila[indicePila--]).getValor();
		String value = ((DatoTexto) pila.pop()).getValor();

		// almacenamos la longitud
		// pila[++indicePila] = new DatoEntero(value.length());
		pila.push(new DatoEntero(value.length()));

	}

}
