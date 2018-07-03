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

import java.text.MessageFormat;

import edu.davinci.utils.Resource;

/**
 * The Class ASTFunPri_Sustraer.
 */
public class ASTFunPri_Sustraer extends SimpleNode {

	/**
	 * Instantiates a new aST fun pri_ sustraer.
	 * 
	 * @param id
	 */
	public ASTFunPri_Sustraer(int id) {
		super(id);
	}

	/**
	 * Instantiates a new aST fun pri_ sustraer.
	 * 
	 * @param p
	 * @param id
	 */
	public ASTFunPri_Sustraer(Parser p, int id) {
		super(p, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.lenguaje.MyNode#interpret()
	 */
	@Override
	public void interpret() {
		Debug.println("<<Pri_Sustraer>>");

		// Interpretamos la expresion de la funcion, el texto a sustraer
		jjtGetChild(0).interpret();

		// guardamos el texto
		// String value = ((DatoTexto) pila[indicePila--]).getValor();
		String value = ((DatoTexto) pila.pop()).getValor();

		// Interpretamos la expresion de la funcion, desde donde sustraer
		jjtGetChild(1).interpret();

		// guardamos desde
		// int desde = ((DatoEntero) pila[indicePila--]).getValor();
		int desde = ((DatoEntero) pila.pop()).getValor();

		// Interpretamos la expresion de la funcion, hasta donde sustraer
		jjtGetChild(2).interpret();

		// guardamos desde
		// int hasta = ((DatoEntero) pila[indicePila--]).getValor();
		int hasta = ((DatoEntero) pila.pop()).getValor();

		// sustraemos el texto
		try {
			value = value.substring(desde - 1, hasta);
		} catch (StringIndexOutOfBoundsException s) {
			throw new RuntimeException(
					MessageFormat.format(
							Resource.getString("funcion.sustraer"), value,
							desde, hasta));

		}

		// almacenamos el texto resultante
		// pila[++indicePila] = new DatoTexto(value);
		pila.push(new DatoTexto(value));

	}
}
