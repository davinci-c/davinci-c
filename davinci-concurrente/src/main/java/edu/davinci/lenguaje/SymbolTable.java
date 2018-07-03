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
/*
 * 
 */
package edu.davinci.lenguaje;

/**
 * 
 * 
 * @author DanGer
 */
public interface SymbolTable {

	/**
	 * Agregar parametro.
	 * 
	 * @param identificador
	 * @param tipo
	 * @param esSalida
	 * @param nombre
	 */
	public void agregarParametro(String nombre, String identificador, int tipo, boolean esSalida);

	/**
	 * Agregar simbolo.
	 * 
	 * @param nombre
	 * @param tipo
	 * 
	 * @return true, if successful
	 */
	public boolean agregarSimbolo(String nombre, int tipo);

	/**
	 * Agregar simbolo.
	 * 
	 * @param padre
	 * @param nombre
	 * @param tipo
	 * 
	 * @return true, if successful
	 */
	public boolean agregarSimbolo(String padre, String nombre, int tipo);

	/**
	 * Retorna el tipo.
	 * 
	 * @param nombre
	 * 
	 * @return the tipo
	 */
	public int getTipo(String nombre);

	/**
         * 
         * @param nombre
         * @param tipo 
         */
	public void setTipo(String nombre, int tipo);        
        
	/**
	 * Retorna el tipo segun el padre.
	 * 
	 * @param padre
	 * @param nombreId
	 * 
	 * @return the tipo
	 */
	public int getTipo(String padre, String nombreId);

	/**
	 * Verificar cantidad de parametros.
	 * 
	 * @param n
	 * @param nombre
	 * 
	 * @return true, if successful
	 */
	public boolean verificarCantidadDeParametros(String nombre, int n);

	/**
	 * Verificar declaracion.
	 * 
	 * @param nombre
	 * 
	 * @return true, if successful
	 */
	public boolean verificarDeclaracion(String nombre);

	/**
	 * Verificar declaracion.
	 * 
	 * @param padre
	 * @param nombre
	 * 
	 * @return true, if successful
	 */
	public boolean verificarDeclaracion(String padre, String nombre);

	/**
	 * Verificar parametro.
	 * 
	 * @param nombre
	 * @param tipo
	 * @param esSalida
	 * @param orden
	 * 
	 * @return true, if successful
	 */
	public boolean verificarParametro(String nombre, int tipo, boolean esSalida, int orden);

}
