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
 * The Class ASTFunPri_NumeroATexto.
 */
public class ASTFunPri_NumeroATexto extends SimpleNode {

	/**
	 * Instantiates a new aST fun pri_ numero a texto.
	 * 
	 * @param id
	 */
	public ASTFunPri_NumeroATexto(int id) {
		super(id);
	}

	/**
	 * Instantiates a new aST fun pri_ numero a texto.
	 * 
	 * @param p
	 * @param id
	 */
	public ASTFunPri_NumeroATexto(Parser p, int id) {
		super(p, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.lenguaje.MyNode#interpret()
	 */
	@Override
	public void interpret() {
		Debug.println("<<Pri_NumeroATexto>>");

		// Interpretamos la expresion de la funcion, el texto a sustraer
		jjtGetChild(0).interpret();

		// guardamos el numero
		// int value = ((DatoEntero) pila[indicePila--]).getValor();
		int value = ((DatoEntero) pila.pop()).getValor();

		// almacenamos el texto resultante
		// pila[++indicePila] = new DatoTexto(String.valueOf(value));
		try {
			pila.push(new DatoTexto(String.valueOf(value)));
		} catch (Exception e) {
			throw new RuntimeException(MessageFormat.format(
					Resource.getString("funcion.numeroatexto"), value));

		}
	}
}
