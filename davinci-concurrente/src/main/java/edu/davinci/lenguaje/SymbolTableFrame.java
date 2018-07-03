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
import java.util.Iterator;
import java.util.List;

/**
 * The Class SymbolTableFrame.
 */
public class SymbolTableFrame {

	/** nombre. */
	private String nombre;

	/** tipo. */
	private int tipo;

	/** parametros. */
	private List<Parameter> parametros;

	/** lista de identificador. */
	private List<Identificador> identificador;

	/**
	 * Instantiates a new symbol table frame.
	 */
	public SymbolTableFrame() {
		setNombre("");
		setTipo(0);
		setParametros(new ArrayList<Parameter>());
		setIdentificador(new ArrayList<Identificador>());
	}

	/**
	 * Agregar identificador.
	 * 
	 * @param nombre
	 * @param tipo
	 */
	public void agregarIdentificador(String nombre, int tipo) {
		identificador.add(new Identificador(nombre, tipo));

	}

	/**
	 * Agregar parametro.
	 * 
	 * @param id
	 * @param tipo
	 * @param esSalida
	 */
	public void agregarParametro(String id, int tipo, boolean esSalida) {
		parametros.add(new Parameter(id, tipo, esSalida));

	}

	/**
	 * retorna el identificador.
	 * 
	 * @return the identificador
	 */
	public List<Identificador> getIdentificador() {
		return identificador;
	}

	/**
	 * retorna el nombre.
	 * 
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * reorna los parametros.
	 * 
	 * @return the parametros
	 */
	public List<Parameter> getParametros() {
		return parametros;
	}

	/**
	 * retorna el tipo.
	 * 
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * retorna el tipo del identificador.
	 * 
	 * @param nombre
	 * 
	 * @return the tipo del identificador
	 */
	public int getTipoDeIdentificador(String nombre) {
		for (Iterator<Identificador> it = identificador.iterator(); it.hasNext();) {
			Identificador id = it.next();
			if (id.getNombre().equalsIgnoreCase(nombre))
				return id.getTipo();
		}
		return 0;
	}

	/**
	 * Setea el identificador.
	 * 
	 * @param id
	 * 
	 */
	public void setIdentificador(List<Identificador> id) {
		identificador = id;
	}

	/**
	 * Setea el nombre.
	 * 
	 * @param nombre
	 * 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Setea los parametros.
	 * 
	 * @param parametros
	 * 
	 */
	public void setParametros(List<Parameter> parametros) {
		this.parametros = parametros;
	}

	/**
	 * Setea el tipo.
	 * 
	 * @param tipo
	 * 
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Verificar identificador.
	 * 
	 * @param nombre
	 * 
	 * @return true, if successful
	 */
	public boolean verificarIdentificador(String nombre) {
		for (Iterator<Identificador> it = identificador.iterator(); it.hasNext();)
			if (it.next().getNombre().equalsIgnoreCase(nombre))
				return true;
		return false;

	}

	/**
	 * Verificar parametro.
	 * 
	 * @param order
	 * @param type
	 * @param esSalida
	 * 
	 * @return true, if successful
	 */
	public boolean verificarParametro(int order, int type, boolean esSalida) {
		if (parametros.size() > order) {
			Parameter p = parametros.get(order);
			if (p.getTipo() == type) {
				// Si el parametro formal es de salida verifico q el parametro
				// real tambien lo sea
				if (p.esSalida())
					return esSalida;
				else
					// como el parametro formal es de entrada no importa cual
					// sea el parametro real.
					return true;
			} else
				return false;
		} else
			return false;

	}

}
