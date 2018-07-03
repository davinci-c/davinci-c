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
 * 
 */
package edu.davinci.ui.ciudad;

import java.awt.Image;
import java.awt.Toolkit;

import edu.davinci.ciudad.Objeto;
import edu.davinci.ciudad.ObstaculoSimpleImpl;

/**
 * @author dany
 * 
 */
public class ObstaculoSimpleGuiImpl extends ObstaculoSimpleImpl implements Objeto {

	private Image imagen;

	public ObstaculoSimpleGuiImpl(int avenidas, int calles) {
		super(avenidas, calles);
	}

	@Override
	public void setImagen(String recurso) {
		imagen = Toolkit.getDefaultToolkit().getImage(
				ObstaculoSimpleGuiImpl.class.getResource(recurso));
	}

	@Override
	public Image getImagen() {
		return imagen;
	}
}
