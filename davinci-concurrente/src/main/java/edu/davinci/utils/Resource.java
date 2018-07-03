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
/**
 *  Clase wrapper de ResourceBundle utilizada para internalizacion.
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
package edu.davinci.utils;

import java.util.ResourceBundle;

public class Resource {

	/** recursos */
	private static ResourceBundle resource = ResourceBundle.getBundle("messages");

	/**
	 * retorna el recurso segun la clave.
	 * 
	 * @param key
	 * @return string
	 */
	public static String getString(String key) {
		return resource.getString(key);
	}
}
