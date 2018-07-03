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

public class ASTPri_IniciarSemaforo extends SimpleNode {

	public ASTPri_IniciarSemaforo(int id) {
		super(id);
	}

	public ASTPri_IniciarSemaforo(Parser p, int id) {
		super(p, id);
	}

	@Override
	public void interpret() {
		Debug.println("<<Iniciar Semaforo>>");

		// obtenemos el nombre del identificador
		String identificador = ((ASTIdentificador) jjtGetChild(0)).getNombre();

		// Creamos el valor por defecto
		Integer valor = Integer.valueOf(1);

		// verificamos si se ha invocado con un valor de inicializacion
		if (children.length == 2) {
			// interpretamos el lado derecho de la inicializacion
			jjtGetChild(1).interpret();

			// obtenemos el valor de la expresion de inicializacion
			// valor = ((DatoEntero) pila[indicePila]).getValor();
			valor = ((DatoEntero) pila.pop()).getValor();

			// indicePila = -1;
		}
		// guardamos el valor de la inicializacion en la tabla de simbolos
		((Dato) getTablaDeSimbolos().get(identificador)).setValor(valor);

	}
}
