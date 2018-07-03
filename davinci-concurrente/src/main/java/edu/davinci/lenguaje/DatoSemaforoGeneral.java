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

import edu.davinci.planificador.PlanificadorUtils;
import edu.davinci.utils.Resource;

public class DatoSemaforoGeneral extends DatoSemaforoBinario {

	public DatoSemaforoGeneral() {
		super();
	}

	public DatoSemaforoGeneral(int n) {
		setValor(n);
	}

	@Override
	public boolean esperar() {
		boolean suspendido = false;
		valor--;
		if (valor < 0) {
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
	public void senal() {
		valor++;
		// verificamos si hay tareas suspendidas
		if (valor <= 0) {
			// TODO Verificar politica para ver a cual despertamos
			// agregamos la tarea al planificador y removemos la tarea de la
			// lista
			PlanificadorUtils.getPlanificador().despertarTarea(
					tareasSuspendidas.remove(0));

		}

	}

	@Override
	public void setValor(Object o) {
		valor = (Integer) o;
		if (valor < 0)
			throw new RuntimeException(
					Resource.getString("semaforo.general.valorErroneo"));
	}
}
