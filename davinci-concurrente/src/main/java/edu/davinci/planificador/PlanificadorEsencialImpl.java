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
import java.util.ArrayList;
import java.util.List;

import edu.davinci.interprete.Depurador;
import edu.davinci.lenguaje.ASTDefinicionHilo;
import edu.davinci.lenguaje.ASTDefinicionProcedimiento;
import edu.davinci.lenguaje.ASTDefinicionVariable;
import edu.davinci.lenguaje.Node;
import edu.davinci.planificador.tarea.Tarea;
import edu.davinci.utils.Resource;

/**
 * Implementaci&oacute;n del planificador elemental
 * 
 * Implementa un planificador del tipo FIFO, el cual es util cuando solo se
 * utiliza un hilo de ejecuci&oacute;n.
 * 
 * NOA: Es probable que el resto de los planificadores hereden el comportamiento
 * de este.
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class PlanificadorEsencialImpl implements Planificador {

	/** ID. */
	private static int ID = 0;

	/** Tareas activas. */
	protected ArrayList<Tarea> tareasActivas;

	/** Tareas suspendidas. */
	protected ArrayList<Tarea> tareasSuspendidas;

	/** indice de la tarea. */
	protected int indiceTarea;

	/** Depurador de tareas *. */
	protected Depurador depurador;

	// private List<Integer> secuencia;
	private List<Secuencia> secuenciaEjecucion;

	/**
	 * Constructor.
	 */
	public PlanificadorEsencialImpl() {

		inicializaTareas();

		// iniciamos el depurador
		depurador = new Depurador();

		// asignamos la referencia del planificador
		PlanificadorUtils.setPlanificador(this);

		// iniciamos la secuencia
		secuenciaEjecucion = new ArrayList<Secuencia>();
	}

	/**
	 * Inicializa tareas.
	 */
	private void inicializaTareas() {
		// inicializamos la lista de tareas activas
		tareasActivas = new ArrayList<Tarea>();

		// inicializamos la lista de tareas suspendidas
		tareasSuspendidas = new ArrayList<Tarea>();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.davinci.planificador.Planificador#agregarTarea(edu.davinci.planificador
	 * .Tarea)
	 */
	@Override
	public void agregarTarea(Tarea t) {
		// incrementamos el Identificador de las tareas
		ID++;

		// identificamos la tarea
		t.setId(ID);

		// marcamos la tarea como activa (no suspendida)
		t.setSuspendida(false);

		// agregamos la tarea a la lista de activas
		tareasActivas.add(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.davinci.planificador.Planificador#despertarTarea(edu.davinci.planificador
	 * .Tarea)
	 */
	@Override
	public void despertarTarea(Tarea t) {

		// marcamos la tarea como posible de ejecucion
		t.setSuspendida(false);

		// incorporamos la tarea a la lista de activos
		tareasActivas.add(t);

		// removemos la tarea de la lista de suspendidos
		tareasSuspendidas.remove(t);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.davinci.planificador.Planificador#ejecutarTarea(edu.davinci.planificador
	 * .Tarea)
	 */
	@Override
	public void ejecutarTarea(Tarea t) throws RuntimeException {

		if (t.tieneNodosParaInterpretar()) {
			// Ejecutamos la tarea
			t.ejecutar();
		}

		// removemos la tarea si ha finalizado
		removerTarea(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.Planificador#ejecutarTareas()
	 */
	@Override
	public void ejecutarTareas() {
		while (hayTareasParaEjecutar()) {
			ejecutarTarea(obtenerProximaTarea());
		}

	}

	/**
	 * Retorna el indice de la proxima tarea.
	 * 
	 * @return int - representa el indice de la proxima tarea a ejecutar
	 */
	protected int getProximaTarea() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.Planificador#hayTareasParaEjecutar()
	 */
	@Override
	public boolean hayTareasParaEjecutar() {
		// retornamos si hay tareas para ejecutar (por mas que esten
		// suspendidas)
		return (!tareasActivas.isEmpty() || !tareasSuspendidas.isEmpty());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.Planificador#obtenerProximaTarea()
	 */
	@Override
	public Tarea obtenerProximaTarea() {
		// verificamos si hay tareas para ejecutar en estado activo
		if (!tareasActivas.isEmpty()) {

			// obtenemos la proxima tarea
			indiceTarea = getProximaTarea();

			// Obtenemos la tarea
			Tarea t = tareasActivas.get(indiceTarea);

			if (t.tieneNodosParaInterpretar()) {
				// interpretamos los nodos hasta terminar de procesar los que
				// definen hilos, procedimientos y variables
				while (enFaseDeDefinicion(t)) {
					// interpretamos la tarea
					t.ejecutar();
				}

				// depuramos la tarea
				depurador.depurar(t);

				// obtengo el nombre del hilo
				String hilo = t.getNombre();

				int linea = t.getLineaInterpretacion();

				// almacenamos el indice de tarea en la secuencia
				secuenciaEjecucion.add(new Secuencia(indiceTarea, linea, hilo));
			}

			// retornamos la tarea
			return t;
		} else {
			if (!tareasSuspendidas.isEmpty())
				throw new RuntimeException(Resource.getString("planificador.tareasSuspendidas"));
			else
				throw new RuntimeException(Resource.getString("planificador.error"));
		}
	}

	/**
	 * En fase de definicion.
	 * 
	 * @param t
	 * @return true, if successful
	 */
	private boolean enFaseDeDefinicion(Tarea t) {
		try {
			Node nodo = t.getNodos().getNodo();
			return (nodo instanceof ASTDefinicionHilo)
					|| (nodo instanceof ASTDefinicionProcedimiento)
					|| (nodo instanceof ASTDefinicionVariable);
		} catch (Exception e) {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.Planificador#obtenerTareaEnEjecucion()
	 */
	@Override
	public Tarea obtenerTareaEnEjecucion() {
		// retornamos la tarea en ejecucion
		return tareasActivas.get(indiceTarea);
	}

	/**
	 * Remueve la tarea si no se encuentra suspendia y no tiene mas nodos para
	 * ejecutar.
	 * 
	 * @param t
	 *            - Tarea a remover
	 */
	private void removerTarea(Tarea t) {
		// verificamos que la tarea este activa y no tenga nodos para ejecutar
		if (!t.isSuspendida() && !t.tieneNodosParaInterpretar()) {
			tareasActivas.remove(t);

			// depuramos el ultimo estado
			depurador.depurar_fin(t);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.Planificador#suspenderTareaEnEjecucion()
	 */
	@Override
	public void suspenderTareaEnEjecucion() {
		// removemos la tarea de la lista de activos
		Tarea t = tareasActivas.remove(indiceTarea);

		// marcamos la tarea como suspendida
		t.setSuspendida(true);

		// agregamos la tarea a la lista de suspendidos
		tareasSuspendidas.add(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.Planificador#borrarTareas()
	 */
	@Override
	public void borrarTareas() {
		inicializaTareas();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.davinci.planificador.Planificador#registrarOyenteDebug(java.beans
	 * .PropertyChangeListener)
	 */
	@Override
	public void registrarOyenteDepurador(PropertyChangeListener oyente) {

		// agregamos el oyente
		depurador.addPropertyChangeListener(oyente);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.davinci.planificador.Planificador#registrarOyenteDebug(java.beans
	 * .PropertyChangeListener, Boolean)
	 */
	@Override
	public void registrarOyenteDepurador(PropertyChangeListener oyente, Boolean sinLinea) {
		// agregamos el oyente
		depurador.addPropertyChangeListener(oyente, sinLinea);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.davinci.planificador.Planificador#registrarOyenteDebug(java.beans
	 * .PropertyChangeListener)
	 */
	@Override
	public void removerOyenteDepurador(PropertyChangeListener oyente) {

		// agregamos el oyente
		depurador.removePropertyChangeListener(oyente);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.Planificador#obtenerSecuenciaLogica()
	 */
	@Override
	public List<Secuencia> obtenerSecuenciaLogica() {

		// mostramos la secuencia logica
		// imprimirSecuencia(secuenciaEjecucion);

		return secuenciaEjecucion;
	}

	private void imprimirSecuencia(List<Secuencia> secuencia2) {
		for (Secuencia valor : secuencia2) {
			for (int i = 0; i < valor.getIndice(); i++)
				System.out.print("----- ");
			System.out.println(valor.getLinea() + ":" + valor.getIndice());

		}
		System.out.println("");
	}

	@Override
	public String nombre() {
		return "planificador esencial";
	}
        
        @Override
	public String toString() {
		return "Planificador secuencial";
	}

}
