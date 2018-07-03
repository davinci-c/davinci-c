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

public class DefinicionProcedimientos {

	private static Map<String, SimpleNode> procedimiento;

	public static SimpleNode get(String name) {
		return procedimiento.get(name.toLowerCase());
	}

	public static void put(String name, SimpleNode node) {
		if (procedimiento == null)
			procedimiento = new HashMap<String, SimpleNode>();
		procedimiento.put(name.toLowerCase(), node);
	}
}
