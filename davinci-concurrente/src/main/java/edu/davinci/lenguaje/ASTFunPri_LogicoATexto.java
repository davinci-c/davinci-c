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
 * The Class ASTFunPri_LogicoATexto.
 */
public class ASTFunPri_LogicoATexto extends SimpleNode {

	/**
	 * Instantiates a new aST fun pri_ logico a texto.
	 * 
	 * @param id
	 */
	public ASTFunPri_LogicoATexto(int id) {
		super(id);
	}

	/**
	 * Instantiates a new aST fun pri_ logico a texto.
	 * 
	 * @param p
	 * @param id
	 */
	public ASTFunPri_LogicoATexto(Parser p, int id) {
		super(p, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.lenguaje.MyNode#interpret()
	 */
	@Override
	public void interpret() {
		Debug.println("<<Pri_LogicoATexto>>");

		// Interpretamos la expresion de la funcion, el texto a sustraer
		jjtGetChild(0).interpret();

		// guardamos el texto
		// boolean value = ((DatoLogico) pila[indicePila--]).getValor();
		boolean value = ((DatoLogico) pila.pop()).getValor();

		// almacenamos el texto resultante
		// pila[++indicePila] = new DatoTexto(value ? "verdadero" : "falso");
		try {
			pila.push(new DatoTexto(value ? "verdadero" : "falso"));
		} catch (Exception e) {
			throw new RuntimeException(MessageFormat.format(
					Resource.getString("funcion.logicoatexto"), value));

		}

	}
}
