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
package edu.davinci.ciudad;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class PapelSimpleImpl implements Objeto {

	public List<Elemento>[][] papelesIndice;
	public List<Elemento> papeles;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param avenidas
	 * @param calles
	 */
	@SuppressWarnings("unchecked")
	public PapelSimpleImpl(int avenidas, int calles) {

		papeles = new ArrayList<Elemento>();

		papelesIndice = new ArrayList[avenidas][calles];
		for (int i = 0; i < avenidas; i++) {
			for (int j = 0; j < calles; j++) {
				papelesIndice[i][j] = new ArrayList<Elemento>();
			}
		}
	}

	@Override
	public void agregar(int avenida, int calle) {
		Elemento e = new Elemento(avenida, calle);
		papeles.add(e);
		papelesIndice[avenida - 1][calle - 1].add(e);
	}

	@Override
	public void eliminar(int avenida, int calle) {
		// TODO si no hay flor

		Elemento e = papelesIndice[avenida - 1][calle - 1].remove(0);
		papeles.remove(e);
	}

	@Override
	public int cantidad(int avenida, int calle) {
		return papelesIndice[avenida - 1][calle - 1].size();
	}

	@Override
	public boolean hay(int avenida, int calle) {
		return papelesIndice[avenida - 1][calle - 1].size() > 0;
	}

	@Override
	public List<Elemento> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setImagen(String recurso) {
		throw new RuntimeException("La implementación no tiene representación gráfica");
	}

	@Override
	public Image getImagen() {
		throw new RuntimeException("La implementación no tiene representación gráfica");
	}
}
