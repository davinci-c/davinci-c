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
import java.util.List;

/**
 * Interface encargada de manipular los objetos insertables en la ciudad
 * 
 * @author dany
 * 
 */
public interface Objeto {

	public static final String RECURSO_FLOR = "/edu/davinci/ui/images/flor.png";
	public static final String RECURSO_PAPEL = "/edu/davinci/ui/images/papel.png";
	public static final String RECURSO_OBSTACULO = "/edu/davinci/ui/images/caja.png";

	public void agregar(int avenida, int calle);

	public void eliminar(int avenida, int calle);

	public int cantidad(int avenida, int calle);

	public boolean hay(int avenida, int calle);

	public List<Elemento> lista();

	public void setImagen(String recurso);

	public Image getImagen();
}
