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
import java.util.LinkedHashMap;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.ciudad.Robot;
import edu.davinci.planificador.PlanificadorUtils;
import edu.davinci.planificador.tarea.Tarea;

/**
 * Clase encargada de llevar la depuracion de la ejecucion. Se deben registrar
 * oyentes a los cuales se les informara de los cambios
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class Depurador {

	public static final String PROPIEDAD_LINEA = "linea";
	public static final String PROPIEDAD_HILO = "hilo";
	public static final String PROPIEDAD_VARIABLES = "variables";
	public static final Boolean SIN_LINEA = false;
	/** cambios. */
	private PropertyChangeSupport cambios = new PropertyChangeSupport(this);

	/** linea. */
	private int linea;

	/** nombre del hilo **/
	private String nombreHilo;

	/** variables. */
	private LinkedHashMap<String, Object> estado_anterior;

	/** muestra el cambio de linea */
	private Boolean mostrarLinea;

	/**
	 * Constructor.
	 */
	public Depurador() {
		// marcamos que debe mostrar la linea
		mostrarLinea = true;
	}

	/**
	 * Agrega el oyente de la propiedad
	 * 
	 * @param l
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		cambios.addPropertyChangeListener(l);
	}

	/**
	 * Agrega el oyente de la propiedad
	 * 
	 * @param l
	 */
	public void addPropertyChangeListener(PropertyChangeListener l, Boolean linea) {
		cambios.addPropertyChangeListener(l);
		mostrarLinea = linea;
	}

	/**
	 * Remueve el oyente de la propiedad
	 * 
	 * @param l
	 */
	public void removePropertyChangeListener(PropertyChangeListener l) {
		cambios.removePropertyChangeListener(l);
	}

	/**
	 * activa/desactiva el muestreo de lineas
	 * 
	 * @param mostrarLinea
	 */
	public void setMostrarLinea(Boolean mostrarLinea) {
		this.mostrarLinea = mostrarLinea;
	}

	/**
	 * Depurar.
	 * 
	 * @param t
	 */
	public void depurar(Tarea t) {

		// verificamos si hay que mostrar el cambio de linea
		if (mostrarLinea) {

			// Seteamos la linea que se va a interpretar
			setLinea(t.getLineaInterpretacion());
		}

		// Lo saco hasta mostrar de manera correcta las variables
		// setNombreHilo(t.getNombre());

		// variables = ;

		// Seteamos las variables

		setVariables(armarVariables(t));
	}

	private LinkedHashMap<String, Object> armarVariables(Tarea t) {
		LinkedHashMap<String, Object> variables = new LinkedHashMap<String, Object>();

		variables.put("Planificador:", PlanificadorUtils.getPlanificador().getClass()
				.getCanonicalName());

		// agrego el nombre del hilo - se deberia poner por separado
		variables.put("Hilo en ejecución:", t.getNombre());

		variables.putAll(t.getVariables());

		// agrego los datos del robot - se debería poner por separado
		// Obtenemos la ciudad
		Ciudad ciudad = Interprete.getCiudad();

		// Obtenemos el robot de la ciudad
		Robot r = ciudad.getRobot(t.getNombre());

		// verifico si el hilo contiene un robot
		if (r != null) {

			// inserta la avenida
			variables.put("Orientación:", r.getOrientacion());

			// inserta la avenida
			variables.put("PosAv:", r.getPosAv());

			// inserta la calle
			variables.put("PosCa:", r.getPosCa());

			// inserta la cantidad de flores en la bolsa
			variables.put("Bolsa de flores:", r.getBolsaFlores());

			// inserta la cantidad de papeles en la bolsa
			variables.put("Bolsa de papeles:", r.getBolsaPapeles());

		}

		return variables;
	}

	private void setNombreHilo(String nombre) {
		String oldNombrehilo = nombreHilo;

		nombreHilo = nombre;

		cambios.firePropertyChange(PROPIEDAD_HILO, oldNombrehilo, nombreHilo);

	}

	/**
	 * Setea las variables.
	 * 
	 * @param variables2
	 */
	private void setVariables(LinkedHashMap<String, Object> estado) {

		estado_anterior = estado;
		/*
		 * Cambios introducidos 20110817 La variable global variables no se
		 * encuentra en uso. TODO: falta identificar porque tengo que pasar
		 * oldVariables y no null.
		 * 
		 * - variables = contexto;- cambios.firePropertyChange("variables",
		 * oldVariables, variables);
		 */
		cambios.firePropertyChange(PROPIEDAD_VARIABLES, null, estado);
	}

	/**
	 * 
	 */
	public void depurar_fin(Tarea t) {

		setVariables(armarVariables(t));

		// cambios.firePropertyChange(PROPIEDAD_VARIABLES, null,
		// estado_anterior);
	}

	/**
	 * Setea la linea.
	 * 
	 * @param l
	 *            la nueva linea
	 */
	private void setLinea(int l) {
		int oldLinea = linea;
		linea = l;
		cambios.firePropertyChange(PROPIEDAD_LINEA, oldLinea, linea);
	}

}
