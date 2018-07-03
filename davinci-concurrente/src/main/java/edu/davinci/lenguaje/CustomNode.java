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

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Hashtable;
import java.util.Stack;

/**
 * Specialized node.
 */
public class CustomNode {

	/** Tabla de simbolos. */

	private static Hashtable<String, Object> tablaDeSimbolos = TablaDeSimbolos();

	/** pila para los calculos. */
	// protected static Object[] pila = new Object[1024];

	protected static Stack<Object> pila = new Stack<Object>();

	/** indice de la pila. */
	// protected static int indicePila = -1;

	/** The out. */
	protected static Writer out = new PrintWriter(System.out);

	/** The in. */
	protected static Reader in = new InputStreamReader(System.in);

	/**
	 * retorna la tablaDeSimbolos.
	 * 
	 * @return the tablaDeSimbolos
	 */
	public static Hashtable<String, Object> getTablaDeSimbolos() {
		return tablaDeSimbolos;
	}

	/**
	 * Retorna la ultima operacion
	 * 
	 * @return the last operation
	 */
	public static Object getUltimaOperacion() {
		// return pila[indicePila];
		return pila.pop();
	}

	/**
	 * 
	 * 
	 * @param in
	 * 
	 */
	public static void setIn(Reader in) {
		CustomNode.in = in;
	}

	/**
	 * 
	 * 
	 * @param out
	 * 
	 */
	public static void setOut(Writer out) {
		CustomNode.out = out;
	}

	/**
	 * Setear tablaDeSimbolos.
	 * 
	 * @param tablaDeSimbolos
	 */
	public static void setTablaDeSimbolos(Hashtable<String, Object> symtab) {
		CustomNode.tablaDeSimbolos = symtab;
	}

	public static Hashtable<String, Object> inicializaTablaDeSimbolos() {
		return CustomNode.tablaDeSimbolos = TablaDeSimbolos();
	}

	/**
	 * 
	 * 
	 * @throws UnsupportedOperationException
	 *             si es llamado
	 */
	public void interpret() {
		throw new UnsupportedOperationException();
	}

	public static Hashtable<String, Object> TablaDeSimbolos() {
		return new Hashtable<String, Object>();
	}
}
