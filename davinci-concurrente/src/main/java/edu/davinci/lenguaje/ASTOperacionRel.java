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
public class ASTOperacionRel extends SimpleNode {

	/**
	 * 
	 * @param id
	 */
	public ASTOperacionRel(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTOperacionRel(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {

		Debug.println("<<Rel>>");

		// interpretamos el lado izquierdo de la expresion
		jjtGetChild(0).interpret();
		Dato izquierdo = (Dato) pila.pop();

		// Intepretamos el operador de la expresion
		jjtGetChild(1).interpret();
		String operador = (String) pila.pop();

		// Intepretamos el lado derecho de la expresion
		jjtGetChild(2).interpret();
		Dato derecho = (Dato) pila.pop();

		// dependiendo del operador, contenido en la pila, de la expresion
		// realizamos la intepretacion
		// de la operacion de relacion
		Dato valor = new DatoLogico();

		// operador: igualdad
		// TODO: Cambiar la forma en que se comparan los operadores
		// String EQ = ParserConstants.tokenImage[ParserConstants.EQ];
		// EQ = EQ.substring(1, op.length() - 1);

		if (operador.equals("=")) {
			if (izquierdo instanceof DatoEntero)
				valor.setValor(((DatoEntero) izquierdo).getValor().equals(
						((DatoEntero) derecho).getValor()));
			else if (izquierdo instanceof DatoLogico)
				valor.setValor(((DatoLogico) izquierdo).getValor().equals(
						((DatoLogico) derecho).getValor()));
			else
				valor.setValor(((DatoTexto) izquierdo).getValor()
						.compareToIgnoreCase(((DatoTexto) derecho).getValor()) == 0);

			// operador: diferente
		} else if (operador.equals("<>")) {
			if (izquierdo instanceof DatoEntero)
				valor.setValor(!((DatoEntero) izquierdo).getValor().equals(
						((DatoEntero) derecho).getValor()));
			else if (izquierdo instanceof DatoLogico)
				valor.setValor(!((DatoLogico) izquierdo).getValor().equals(
						((DatoLogico) derecho).getValor()));
			else if (izquierdo instanceof DatoTexto)
				valor.setValor(((DatoTexto) izquierdo).getValor()
						.compareToIgnoreCase(((DatoTexto) derecho).getValor()) != 0);

			// operador: menor igual
		} else if (operador.equals("<=")) {
			if (izquierdo instanceof DatoEntero)
				valor.setValor(((DatoEntero) izquierdo).getValor() <= ((DatoEntero) derecho)
						.getValor());
			else if (izquierdo instanceof DatoTexto)
				valor.setValor(((DatoTexto) izquierdo).getValor()
						.compareToIgnoreCase(((DatoTexto) derecho).getValor()) <= 0);

			// operador: mayor igual
		} else if (operador.equals(">=")) {
			if (izquierdo instanceof DatoEntero)
				valor.setValor(((DatoEntero) izquierdo).getValor() >= ((DatoEntero) derecho)
						.getValor());
			else if (izquierdo instanceof DatoTexto)
				valor.setValor(((DatoTexto) izquierdo).getValor()
						.compareToIgnoreCase(((DatoTexto) derecho).getValor()) >= 0);

			// operador: menor
		} else if (operador.equals("<")) {
			if (izquierdo instanceof DatoEntero)
				valor.setValor(((DatoEntero) izquierdo).getValor() < ((DatoEntero) derecho)
						.getValor());
			else if (izquierdo instanceof DatoTexto)
				valor.setValor(((DatoTexto) izquierdo).getValor()
						.compareToIgnoreCase(((DatoTexto) derecho).getValor()) < 0);

			// operador: mayor
		} else if (operador.equals(">")) {
			if (izquierdo instanceof DatoEntero)
				valor.setValor(((DatoEntero) izquierdo).getValor() > ((DatoEntero) derecho)
						.getValor());
			else if (izquierdo instanceof DatoTexto)
				valor.setValor(((DatoTexto) izquierdo).getValor()
						.compareToIgnoreCase(((DatoTexto) derecho).getValor()) > 0);
		}

		// almacenamos en la pila el resultado de la expresion
		pila.push(valor);
	}

}
