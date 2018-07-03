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
public class ASTConstanteBoolTrue extends SimpleNode {

	/**
	 * 
	 * @param id
	 */
	public ASTConstanteBoolTrue(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTConstanteBoolTrue(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {
		Debug.println("<<Constante - booleanTrue>>");

		// Almacenamos en la pila la constante logica
		// pila[++indicePila] = new DatoLogico(true);
		pila.push(new DatoLogico(true));

	}

}
