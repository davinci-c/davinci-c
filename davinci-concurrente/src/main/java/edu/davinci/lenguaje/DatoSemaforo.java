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

public interface DatoSemaforo {

	/**
	 * Signal(semaforo)
	 * 
	 * Verifica si hay tareas suspendias, de haberlas despierta una y la pone en estado de listo.
	 */
	public void senal();

	/**
	 * Wait(semaforo)
	 * 
	 * Se suspende la tarea actual si el valor del semaforo es falso
	 * 
	 * @return boolean retorna si la tarea fue suspendida
	 */
	public boolean esperar();

}
