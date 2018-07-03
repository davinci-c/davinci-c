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
package edu.davinci.planificador;

import java.beans.PropertyChangeListener;
import java.util.List;

import edu.davinci.planificador.tarea.Tarea;

/**
 * Interface encargada de definir las operaciones que deber&aacute; implementar
 * un planificador.
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public interface Planificador {

	/**
	 * Agregar tarea.
	 * 
	 * @param t
	 */
	public void agregarTarea(Tarea t);

	/**
	 * Despertar tarea.
	 * 
	 * @param t
	 */
	public void despertarTarea(Tarea t);

	/**
	 * Ejecutar tarea.
	 * 
	 * @param tarea
	 */
	public void ejecutarTarea(Tarea tarea);

	/**
	 * Ejecutar tareas.
	 */
	public void ejecutarTareas();

	/**
	 * Hay tareas para ejecutar.
	 * 
	 * @return verdadero, si hay tareas para ejecutar
	 */
	public boolean hayTareasParaEjecutar();

	/**
	 * Obtener proxima tarea.
	 * 
	 * @return tarea
	 */
	public Tarea obtenerProximaTarea();

	/**
	 * Obtener tarea en ejecucion.
	 * 
	 * @return tarea
	 */
	public Tarea obtenerTareaEnEjecucion();

	/**
	 * Suspender tarea en ejecucion.
	 */
	public void suspenderTareaEnEjecucion();

	/**
	 * Elimina todas las tareas del planificador
	 */
	public void borrarTareas();

	/**
	 * Registra el oyente para la depuracion
	 * 
	 * @param oyente
	 */
	public void registrarOyenteDepurador(PropertyChangeListener oyente);

	/**
	 * Registra el oyente para la depuracion especificando si debe mostrar el
	 * cambio de linea
	 * 
	 * @param oyente
	 */
	public void registrarOyenteDepurador(PropertyChangeListener oyente, Boolean sinLinea);

	/**
	 * remueve el oyente de depuracion
	 * 
	 * @param oyente
	 */
	public void removerOyenteDepurador(PropertyChangeListener oyente);

	/**
	 * Retorna la secuencia que se utilizo para llevar a cabo toda la ejecucion
	 * 
	 * @return Lista<Integer>
	 */
	public List<Secuencia> obtenerSecuenciaLogica();

	/**
	 * Retorna el nombre de la clase de manera más descriptiva
	 * 
	 * @return
	 */
	public String nombre();

}
