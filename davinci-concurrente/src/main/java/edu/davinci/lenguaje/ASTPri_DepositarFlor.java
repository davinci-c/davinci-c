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
public class ASTPri_DepositarFlor extends ASTPrimitiva {

	/**
	 * 
	 * @param id
	 */
	public ASTPri_DepositarFlor(int id) {
		super(id);
	}

	/**
	 * 
	 * @param p
	 * @param id
	 */
	public ASTPri_DepositarFlor(Parser p, int id) {
		super(p, id);
	}

	/**
	 * 
	 */
	public void interpret() {
		Debug.println("<<depositarFlor>>");

		// Obtenemos el robot adecuado
		Robot robot = super.getRobot();

		// depositamos la flor en la esquina que se encuentra posicionado
		robot.depositarFlor();

	}

}
