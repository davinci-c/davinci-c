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
public class ASTConstanteBoolFalse extends SimpleNode {

	/**
	 * 
	 * @param id
	 */
	public ASTConstanteBoolFalse(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTConstanteBoolFalse(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {
		Debug.println("<<Constante - booleanFalse>>");

		// Almacenamos en la pila la Constante Logica
		// pila[++indicePila] = new DatoLogico();
		pila.push(new DatoLogico());

	}

}
