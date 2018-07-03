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

public class ObstaculoSimpleImpl implements Objeto {

	public Elemento[][] obstaculoIndice;
	public List<Elemento> obstaculo;

	private int max_avenidas;
	private int max_calles;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param avenidas
	 * @param calles
	 */
	public ObstaculoSimpleImpl(int avenidas, int calles) {

		// Guardo los limites
		max_avenidas = avenidas;
		max_calles = calles;

		obstaculo = new ArrayList<Elemento>();

		obstaculoIndice = new Elemento[avenidas][calles];

	}

	@Override
	public void agregar(int avenida, int calle) {
		// TODO solo debe haber un obstaculo por posicion
		Elemento e = new Elemento(avenida, calle);
		obstaculo.add(e);
		obstaculoIndice[avenida - 1][calle - 1] = e;
	}

	@Override
	public void eliminar(int avenida, int calle) {
		// TODO se puede eliminar solo un obstaculo que exista

		Elemento e = obstaculoIndice[avenida - 1][calle - 1];
		obstaculoIndice[avenida - 1][calle - 1] = null;
		obstaculo.remove(e);
	}

	@Override
	public int cantidad(int avenida, int calle) {
		return (obstaculoIndice[avenida - 1][calle - 1] != null) ? 1 : 0;
	}

	@Override
	public boolean hay(int avenida, int calle) {
		if ((avenida <= 0) || (avenida > max_avenidas) || (calle <= 0) || (calle > max_calles))
			return false;
		return obstaculoIndice[avenida - 1][calle - 1] != null;
	}

	@Override
	public List<Elemento> lista() {
		// TODO Auto-generated method stub
		return obstaculo;
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
