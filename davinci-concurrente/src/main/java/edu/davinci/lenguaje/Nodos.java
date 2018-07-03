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

import java.util.HashMap;
import java.util.Map;

public class Nodos {
	private static Map<String, Node[]> nodos;

	public static Node[] get(String name) {
		return nodos.get(name);
	}

	public static void put(String name, Node[] childrens) {
		if (nodos == null)
			nodos = new HashMap<String, Node[]>();
		nodos.put(name, childrens);
	}
}
