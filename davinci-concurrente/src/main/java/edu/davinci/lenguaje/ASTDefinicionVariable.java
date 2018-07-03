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
public class ASTDefinicionVariable extends SimpleNode {
	String nombre;
	int tipo;

	/**
	 * 
	 * @param id
	 */
	public ASTDefinicionVariable(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTDefinicionVariable(Parser p, int id) {
		super(p, id);
	}

	public String getNombre() {
		return nombre.toLowerCase();
	}

	/**
	 * 
	 */
	public void interpret() {
		Debug.println("<<DefinicionVariable>>");

		// Almacenanos, dependiendo el tipo, la variable en la tabla de simbolos
		if (tipo == ParserConstants.BOOLEAN) {
			getTablaDeSimbolos().put(getNombre(), new DatoLogico(false));
		} else if (tipo == ParserConstants.INTEGER) {
			getTablaDeSimbolos().put(getNombre(), new DatoEntero(0));
		} else if (tipo == ParserConstants.STRING) {
			getTablaDeSimbolos().put(getNombre(), new DatoTexto(""));
		} else if (tipo == ParserConstants.SEMAPHORE_BINARY) {
			getTablaDeSimbolos().put(getNombre(), new DatoSemaforoBinario(1));
		} else if (tipo == ParserConstants.SEMAPHORE_GENERAL) {
			getTablaDeSimbolos().put(getNombre(), new DatoSemaforoGeneral(1));
		}
	}

	public void setNombre(String image) {
		nombre = image.toLowerCase();

	}

}
