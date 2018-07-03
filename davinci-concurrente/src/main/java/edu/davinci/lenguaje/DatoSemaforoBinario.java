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

import java.util.ArrayList;
import java.util.List;

import edu.davinci.planificador.PlanificadorUtils;
import edu.davinci.planificador.tarea.Tarea;
import edu.davinci.utils.Resource;

public class DatoSemaforoBinario implements Dato, DatoSemaforo {

	protected Integer valor;

	protected List<Tarea> tareasSuspendidas = new ArrayList<Tarea>();

	/**
	 * 
	 */
	public DatoSemaforoBinario() {
		setValorPorDefecto();
	}

	/**
	 * 
	 * @param b
	 */
	public DatoSemaforoBinario(int n) {
		if (n < 0 || n > 1)
			throw new RuntimeException(
					Resource.getString("semaforo.binario.valorErroneo"));
		valor = new Integer(n);
	}

	@Override
	public boolean esperar() {
		boolean suspendido = false;
		if (valor == 1) {
			valor = 0;
		} else {
			// marcamos que el semaforo suspendera la tarea
			suspendido = true;

			// Obtenemos la tarea y la colocamos en la lista de suspendidos
			tareasSuspendidas.add(PlanificadorUtils.getPlanificador()
					.obtenerTareaEnEjecucion());

			// suspendemos la tarea
			PlanificadorUtils.getPlanificador().suspenderTareaEnEjecucion();
		}
		return suspendido;
	}

	@Override
	public Integer getValor() {
		return valor;
	}

	@Override
	public void senal() {
		// verificamos si la cola de tareas esta vacia
		if (tareasSuspendidas.isEmpty()) {
			valor = 1;

		} else {
			// TODO Verificar politica para ver a cual despertamos
			// agregamos la tarea al planificador y removemos la tarea de la
			// lista
			PlanificadorUtils.getPlanificador().despertarTarea(
					tareasSuspendidas.remove(0));

		}

	}

	/**
	 * 
	 */
	@Override
	public void setValor(Object o) {
		valor = (Integer) o;
		if (valor < 0 || valor > 1)
			throw new RuntimeException(
					Resource.getString("semaforo.binario.valorErroneo"));
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return valor.toString();
	}

	@Override
	public void setValorPorDefecto() {
		valor = new Integer(1);
	}
}
