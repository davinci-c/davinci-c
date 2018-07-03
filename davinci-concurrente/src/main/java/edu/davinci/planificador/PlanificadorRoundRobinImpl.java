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

public class PlanificadorRoundRobinImpl extends PlanificadorEsencialImpl {

	private static final int DEFAULT_QUANTUM = 4;
	private int quantum;
	private int quantumInicial;
	private int indice;

	// private HashMap<Tarea, Integer> tareasQuantum;

	public PlanificadorRoundRobinImpl() {
		// inicializamos el planificador
		super();
		// inicializamos el conjunto de tareas a seleccionar
		// tareasQuantum = new HashMap<Tarea, Integer>();

		// inicializamos el valor quantum por defecto
		quantumInicial = DEFAULT_QUANTUM;

		// inicializa el valor del quantum
		iniciarQuatum(quantumInicial);

		// apuntador de la tarea activo
		indice = 0;
	}

	private void iniciarQuatum(int q) {
		quantum = q;

	}

	@Override
	protected int getProximaTarea() {
		// si se agoto el quantum se selecciona la siguiente tarea seguiendo
		// el esquema FIFO

		// decremetamos el tiempo disponible dentro del planificador
		quantum--;

		// verificamos si hay que procesar otra tarea
		if (quantum == 0) {

			// establecemos el tiempo disponible de la nueva tarea
			iniciarQuatum(quantumInicial);

			//
			indice++;

		}

		//
		if (indice >= tareasActivas.size())
			indice = 0;

		// retornamos el indice
		return indice;
	}

	@Override
	public void suspenderTareaEnEjecucion() {
		super.suspenderTareaEnEjecucion();

		iniciarQuatum(quantumInicial);
	}

	/**
	 * Setea el quantum establecido para seleccionar tareas
	 * 
	 * @param quantum
	 */
	public void setQuantum(int quantum) {
		// guardamos el quantum deseado
		quantumInicial = quantum;

		// establecemos el quatum
		iniciarQuatum(quantum);
	}

	/**
	 * retorna el quantum establecido
	 * 
	 * @return
	 */
	public int getQuantumInicial() {
		return quantumInicial;
	}

	/**
	 * retorna el quatum restante para re organizar las tareas
	 * 
	 * @return
	 */
	public int getQuantum() {
		return quantum;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Planificador de cola circular";
	}

}
