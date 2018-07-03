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

import edu.davinci.ciudad.Robot;

@SuppressWarnings("all")
public class ASTPri_Variable extends ASTPrimitiva {

	String nombre;

	/**
	 * 
	 * @param id
	 */
	public ASTPri_Variable(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTPri_Variable(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {
		Debug.println("<<VariablePrimitiva");

		// Obtenemos el robot adecuado
		Robot robot = super.getRobot();

		// Obtenemos la variable pedida
		Object valor = null;
		if (nombre.equalsIgnoreCase(ParserMyConstants.POSAV)) {
			valor = new DatoEntero(robot.getPosAv());
		} else if (nombre.equalsIgnoreCase(ParserMyConstants.POSCA)) {
			valor = new DatoEntero(robot.getPosCa());
		} else if (nombre.equalsIgnoreCase(ParserMyConstants.HAYFLORENLABOLSA)) {
			valor = new DatoLogico(robot.hayFlorEnLaBolsa());
		} else if (nombre.equalsIgnoreCase(ParserMyConstants.HAYPAPELENLABOLSA)) {
			valor = new DatoLogico(robot.hayPapelEnLaBolsa());
		} else if (nombre.equalsIgnoreCase(ParserMyConstants.HAYFLORENLAESQUINA)) {
			valor = new DatoLogico(robot.hayFlorEnLaEsquina());
		} else if (nombre.equalsIgnoreCase(ParserMyConstants.HAYPAPELENLAESQUINA)) {
			valor = new DatoLogico(robot.hayPapelEnLaEsquina());
		} else if (nombre.equalsIgnoreCase(ParserMyConstants.HAYOBSTACULO)) {
			valor = new DatoLogico(robot.hayObstaculo());
		}

		// almacenamos el valor de la variable en la pila
		// pila[++indicePila] = valor;
		pila.push(valor);
	}
}
