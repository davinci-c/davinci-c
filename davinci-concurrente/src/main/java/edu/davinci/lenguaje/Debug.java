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

public class Debug {
	private static boolean DEBUG = true;

	/**
	 * 
	 * @param o
	 */
	public static void print(Object o) {
		if (DEBUG)
			System.out.print(o);

	}

	/**
	 * 
	 * @param o
	 */
	public static void println(Object o) {
		if (DEBUG)
			System.out.println(o);
	}

	/**
	 * 
	 * @param b
	 */
	public static void setDebug(boolean b) {
		DEBUG = b;
	}
}
