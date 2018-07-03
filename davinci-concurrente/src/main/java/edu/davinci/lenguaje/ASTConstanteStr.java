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
public class ASTConstanteStr extends SimpleNode {
	String valor;

	/**
	 * 
	 * @param id
	 */
	public ASTConstanteStr(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTConstanteStr(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {
		Debug.println("<<Constante - str>>");

		// Formateamos la constante Cadena eliminando las dobles comillas
		if (valor.contains("\"") && valor.charAt(0) == '"'
				&& valor.charAt(valor.length() - 1) == '"')
			valor = valor.substring(1, valor.length() - 1);

		// Almacenamos en la pila el valor de la constante Cadena
		// pila[++indicePila] = new DatoTexto(valor);
		pila.push(new DatoTexto(valor));
	}
}
