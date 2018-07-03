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
 * The Class ASTFunPri_TextoANumero.
 */
public class ASTFunPri_TextoANumero extends SimpleNode {

	/**
	 * Instantiates a new aST fun pri_ texto a numero.
	 * 
	 * @param id
	 */
	public ASTFunPri_TextoANumero(int id) {
		super(id);
	}

	/**
	 * Instantiates a new aST fun pri_ texto a numero.
	 * 
	 * @param p
	 * @param id
	 */
	public ASTFunPri_TextoANumero(Parser p, int id) {
		super(p, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.lenguaje.MyNode#interpret()
	 */
	@Override
	public void interpret() {
		Debug.println("<<Pri_TextoANumero>>");

		// Interpretamos la expresion de la funcion, el texto a sustraer
		jjtGetChild(0).interpret();

		// guardamos el texto
		// String value = ((DatoTexto) pila[indicePila--]).getValor();
		String value = ((DatoTexto) pila.pop()).getValor();

		// almacenamos el numero resultante
		// pila[++indicePila] = new DatoEntero(Integer.parseInt(value));
		try {
			pila.push(new DatoEntero(Integer.parseInt(value)));
		} catch (NumberFormatException n) {
			throw new RuntimeException(MessageFormat.format(
					Resource.getString("funcion.textoanumero"), value));

		}

	}
}
