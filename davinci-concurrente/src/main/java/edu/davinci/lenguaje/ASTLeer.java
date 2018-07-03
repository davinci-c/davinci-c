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

import edu.davinci.ciudad.Ciudad;
import edu.davinci.interprete.Interprete;

public class ASTLeer extends SimpleNode {
	String nombre;

	public ASTLeer(int id) {
		super(id);
	}

	public ASTLeer(Parser p, int id) {
		super(p, id);
	}

	/**
   * 
   */
	@Override
	public void interpret() {
		Debug.println("<<Leer>>");

		// Obtenemos la instancia de la ciudad
		Ciudad ciudad = Interprete.getCiudad();

		// obtenemos el nombre del identificador
		String identificador = ((ASTIdentificador) jjtGetChild(0)).getNombre();

		// Obtenemos a traves del identificador la variable
		Dato variable = (Dato) getTablaDeSimbolos().get(identificador);

		// Leemos el valor de la variable
		ciudad.leerVariable(identificador, variable);

	}
}
