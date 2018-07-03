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
package edu.davinci.interprete;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.MessageFormat;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.lenguaje.ParserMyConstants;
import edu.davinci.lenguaje.SimpleNode;
import edu.davinci.planificador.Planificador;
import edu.davinci.planificador.tarea.Tarea;
import edu.davinci.utils.Resource;

/**
 * Clase que proporciona las operacion elementales para ejecutar/interpretar el
 * código del lenguaje Davinci Concurrente
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class Ejecutor implements Runnable {

	// Nombre del robot por defecto
	private String nombre = ParserMyConstants.ROBOT_POR_DEFECTO;

	// referencia al planificador utilizado para la ejecucion
	private Planificador planificador;

	// oyente de finalizacion
	private PropertyChangeSupport oyenteFinalizacion;

	// oyente de cambios de estado
	private PropertyChangeSupport oyenteCambiosEstado;

	// retardo usado en la ejecucion
	private int velocidadRetardo;

	private Thread thread;
	private Estado estado;

	private Tarea tarea;

	/**
	 * Constructor sin retardo
	 * 
	 * @param nodoRaiz
	 * @param planificador
	 * @param ciudad
	 */
	public Ejecutor(SimpleNode nodoRaiz, Planificador planificador, Ciudad ciudad) {
		this(nodoRaiz, planificador, ciudad, 0);
	}

	/**
	 * Constructor con retardo
	 * 
	 * @param nodoRaiz
	 * @param planificador
	 * @param ciudad
	 * @param retardoMillis
	 */
	public Ejecutor(SimpleNode nodoRaiz, Planificador planificador, Ciudad ciudad, int retardoMillis) {
		// almacenamos el planificador
		this.planificador = planificador;

		// creamos el oyente de finalizacion
		oyenteFinalizacion = new PropertyChangeSupport(this);

		// creamos el oyente de cambios de estado
		oyenteCambiosEstado = new PropertyChangeSupport(this);

		// this.nodoRaiz = nodoRaiz;

		// almacenamos la velocidad de retardo en la intepretacion
		velocidadRetardo = retardoMillis;

		// agregamos la primer tarea
		planificador.agregarTarea(recuperarPrimerTarea(nombre, nodoRaiz));

		// establecemos el estado
		estado = Estado.PARADO;

		// ponemos listo el hilo
		thread = (new Thread(this));

	}

	private void setEstado(Estado estado) {
		// verifico el estado anterior y el nuevo
		if ((!this.estado.equals(Estado.PARADO)) && (estado.equals(Estado.PARADO))) {

			// registro la secuencia de ejecucion
			oyenteFinalizacion.firePropertyChange("SECUENCIA_FINALIZACION", null,
					planificador.obtenerSecuenciaLogica());
		}
		// registro el cambio de estado
		// TODO arreglar para pasar el estado anterior
		// oyenteCambiosEstado.firePropertyChange("ESTADO", this.estado,
		// estado);
		oyenteCambiosEstado.firePropertyChange("ESTADO", null, estado);

		// guardo el cambio de estado
		this.estado = estado;

	}

	// Retorna la primer tarea a interpretar
	private Tarea recuperarPrimerTarea(String nombre, SimpleNode nodoRaiz) {
		// dejamos lista la primer tarea en el planificador
		Tarea tarea = new Tarea();
		tarea.setNombre(nombre);
		tarea.setNodos(nodoRaiz);
		tarea.setContexto(SimpleNode.inicializaTablaDeSimbolos());

		return tarea;

	}

	// ---------------------------- EXECUTION METHODS
	// ---------------------------- //

	public synchronized void run() {

		try {

			// iniciamos ejecucion
			while (!Thread.currentThread().isInterrupted() && planificador.hayTareasParaEjecutar()) {

				// obtenemos la tarea
				tarea = planificador.obtenerProximaTarea();

				// la ejecutamos
				planificador.ejecutarTarea(tarea);

				// demoramos si hay que hacerlo
				Thread.sleep(velocidadRetardo);

				// verificamos si fue una pausa
				synchronized (this) {
					while (estado.equals(Estado.PAUSADO))
						wait();
				}
			}
		} catch (InterruptedException ie) {
			thread.interrupt();
			System.out.println(Resource.getString("programa.ejecucion.parar"));

		} catch (RuntimeException re) {
			System.out.println(MessageFormat.format(Resource.getString("programa.ejecucion.error"),
					re.getMessage()));
			// re.printStackTrace();

		} finally {
			// lanzar evento de finalizazion
			// Establecemos el estado
			setEstado(Estado.PARADO);

			// Informamos la finalizacion de la ejecucion
			System.out.println(Resource.getString("programa.ejecucion.fin"));

		}
	}

	/**
	 * Retorna si existen tareas para seguir interpretando
	 * 
	 * @return boolean
	 */
	public boolean hasNext() {
		return planificador.hayTareasParaEjecutar();
	}

	/**
	 * Prepara el modo debug
	 */
	public void debug() {

		// Establecemos el estado
		setEstado(Estado.DEBUG);

		// Obtenemos la proxima tarea
		tarea = planificador.obtenerProximaTarea();
	}

	/**
	 * Ejecuta la proxima tarea
	 */

	public void next() {

		try {
			// Establecemos el estado
			setEstado(Estado.DEBUG);

			// ejecutmos la tarea cargada
			planificador.ejecutarTarea(tarea);

			// verificamos si termino
			if (planificador.hayTareasParaEjecutar()) {

				// cargamos la proxima tarea
				tarea = planificador.obtenerProximaTarea();

			} else {

				// Establecemos el estado
				setEstado(Estado.PARADO);

				// informamos la finalizacion de la ejecución
				System.out.println(Resource.getString("programa.ejecucion.fin"));

			}

		} catch (RuntimeException re) {
			System.out.println(MessageFormat.format(Resource.getString("programa.ejecucion.error"),
					re.getMessage()));
			// re.printStackTrace();
			setEstado(Estado.PARADO);

		}
	}

	/**
	 * Comienza con la ejecucion de las tareas
	 */
	public void start() throws RuntimeException {

		// verifico si vengo de un estado de debug
		if (estado.equals(Estado.DEBUG)) {
			// la ejecutamos
			planificador.ejecutarTarea(tarea);
		}

		// Establecemos el estado
		setEstado(Estado.CORRIENDO);

		thread.setPriority(Thread.NORM_PRIORITY + 1);

		// Lanzamos la ejecucion
		thread.start();

	}

	/**
	 * Continua con la ejecucion de las tareas
	 */
	public synchronized void resume() {

		// Mensaje a la consola
		System.out.println(Resource.getString("programa.ejecucion.resumen"));

		// Establecemos el estado
		setEstado(Estado.CORRIENDO);

		// Notificamos que puede continuar
		notify();

	}

	/**
	 * Pausa la ejecucion
	 */
	public void pause() {

		// Establecemos el estado como pausado
		setEstado(Estado.PAUSADO);

		// Mensaje a la consola
		Resource.getString("programa.ejecucion.pausa");

	}

	/**
	 * Interrumpe la ejecucion
	 */
	public void stop() {

		// Establecemos el estado
		setEstado(Estado.PARADO);

		// interrumpimos la ejecucion
		thread.interrupt();

		// dejamos listo para volver a ejecutar
		// prepararEjecucion();

	}

	/**
	 * Retornamos el estado (Estado.CORRIENDO, Estado.PAUSADO, Estado.PARADO )
	 * 
	 * @return Estado.kind
	 */
	public Estado getEstado() {
		return estado;
	}

	public void setVelocidadRetardo(int velocidadRetardo) {
		this.velocidadRetardo = velocidadRetardo;
	}

	/**
	 * Registra en el oyente para la depuracion
	 * 
	 * @param oyente
	 */
	public void registerDebugChangeListener(PropertyChangeListener oyente) {

		// seteamos el oyente
		planificador.registrarOyenteDepurador(oyente);

	}

	/**
	 * remueve el oyente
	 * 
	 * @param oyente
	 */
	public void removerDebugChangeListener(PropertyChangeListener oyente) {
		planificador.removerOyenteDepurador(oyente);
	}

	/**
	 * Registra el escuchador de finalizacion
	 * 
	 * @param l
	 */
	public void registerStopListener(PropertyChangeListener listener) {
		oyenteFinalizacion.addPropertyChangeListener(listener);
	}

	/**
	 * 
	 * @param listener
	 */
	public void registerOyenteCambiosEstado(PropertyChangeListener listener) {
		oyenteCambiosEstado.addPropertyChangeListener(listener);
	}

	public void registerDebugChangeListener(PropertyChangeListener oyente, Boolean sinLinea) {
		// seteamos el oyente
		planificador.registrarOyenteDepurador(oyente, sinLinea);

	}

}
