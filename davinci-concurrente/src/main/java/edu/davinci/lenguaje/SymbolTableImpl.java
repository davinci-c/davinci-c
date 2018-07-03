/**
 * *****************************************************************************
 * Copyright (c) 2011, 2013 - Daniel, Aguil Mallea. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the GNU Public License v3.0 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Daniel, Aguil Mallea - initial API and implementation
 *****************************************************************************
 */
package edu.davinci.lenguaje;

import java.util.ArrayList;

/**
 * The Class SymbolTableImpl.
 */
public class SymbolTableImpl implements SymbolTable {

    /**
     * The st.
     */
    private ArrayList<SymbolTableFrame> st;

    /**
     * Instantiates a new symbol table impl.
     */
    public SymbolTableImpl() {
        st = new ArrayList<SymbolTableFrame>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see lenguaje.SymbolTable#agregarParametro(java.lang.String,
     * java.lang.String, int, boolean)
     */
    public void agregarParametro(String padre, String identificador, int tipo,
            boolean esSalida) {
        // Hacerlo eficiente!!!
        for (int i = 0; i < st.size(); i++) {
            if ((st.get(i)).getNombre().equalsIgnoreCase(padre)) {
                (st.get(i)).agregarParametro(identificador, tipo, esSalida);
                return;
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see lenguaje.SymbolTable#agregarSimbolo(java.lang.String, int)
     */
    @Override
    public boolean agregarSimbolo(String nombre, int tipo) {
        if (!verificarDeclaracion(nombre)) {
            SymbolTableFrame simbolo = new SymbolTableFrame();
            simbolo.setNombre(nombre);
            simbolo.setTipo(tipo);
            st.add(simbolo);
            return true;
        } else {
            return false;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see lenguaje.SymbolTable#agregarSimbolo(java.lang.String,
     * java.lang.String, int)
     */
    @Override
    public boolean agregarSimbolo(String padre, String nombre, int tipo) {
		// Este medoto lo utilizan los procesos o hilos
        // se verifico antes la existencia del proceso/hilo (padre)
        for (int i = 0; i < st.size(); i++) {
            if ((st.get(i)).getNombre().equalsIgnoreCase(padre)) {
                (st.get(i)).agregarIdentificador(nombre, tipo);
                return true;
            }
        }

        return false;

    }

    /*
     * (non-Javadoc)
     * 
     * @see lenguaje.SymbolTable#getTipo(java.lang.String)
     */
    @Override
    public int getTipo(String nombre) {
        for (int i = 0; i < st.size(); i++) {
            if ((st.get(i)).getNombre().equalsIgnoreCase(nombre)) {
                return (st.get(i)).getTipo();
            }
        }
        return 0;
    }

    /**
     *
     * @param nombre
     * @param tipo
     */
    @Override
    public void setTipo(String nombre, int tipo) {
        for (int i = 0; i < st.size(); i++) {
            if ((st.get(i)).getNombre().equalsIgnoreCase(nombre)) {
                st.get(i).setTipo(tipo);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see lenguaje.SymbolTable#getTipo(java.lang.String, java.lang.String)
     */
    @Override
    public int getTipo(String padre, String name) {
        for (int i = 0; i < st.size(); i++) {
            if ((st.get(i)).getNombre().equalsIgnoreCase(padre)) {
                return (st.get(i)).getTipoDeIdentificador(name);
            }
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see lenguaje.SymbolTable#verificarCantidadDeParametros(java.lang.String,
     * int)
     */
    @Override
    public boolean verificarCantidadDeParametros(String nombre, int n) {
        for (int i = 0; i < st.size(); i++) // buscamos el padre del parametro
        {
            if ((st.get(i)).getNombre().equalsIgnoreCase(nombre)) {
                // retornamos verdadero si coinciden la cantidad de parametros
                return (st.get(i)).getParametros().size() == n;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see lenguaje.SymbolTable#verificarDeclaracion(java.lang.String)
     */
    @Override
    public boolean verificarDeclaracion(String nombre) {
        for (int i = 0; i < st.size(); i++) {
            if ((st.get(i)).getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see lenguaje.SymbolTable#verificarDeclaracion(java.lang.String,
     * java.lang.String)
     */
    @Override
    public boolean verificarDeclaracion(String padre, String nombre) {
        for (int i = 0; i < st.size(); i++) {
            if ((st.get(i)).getNombre().equalsIgnoreCase(padre)) {
                return (st.get(i)).verificarIdentificador(nombre);
            }
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see lenguaje.SymbolTable#verificarParametro(java.lang.String, int,
     * boolean, int)
     */
    @Override
    public boolean verificarParametro(String nombre, int tipo,
            boolean esSalida, int orden) {
        for (int i = 0; i < st.size(); i++) // buscamos el padre del parametro
        {
            if ((st.get(i)).getNombre().equalsIgnoreCase(nombre)) {
                return (st.get(i)).verificarParametro(orden, tipo, esSalida);

            }
        }
        return false;
    }

}
