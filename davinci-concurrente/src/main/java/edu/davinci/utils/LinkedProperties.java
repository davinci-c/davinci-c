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
package edu.davinci.utils;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;

public class LinkedProperties extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

	@Override
	public Enumeration<Object> keys() {
		return Collections.<Object> enumeration(keys);
	}

	@Override
	public Object put(Object key, Object value) {
		keys.add(key);
		return super.put(key, value);
	}
}
