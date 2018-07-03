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

@SuppressWarnings("all")
public class ASTAsignacion extends SimpleNode {// implements ComplexNode {

    /**
     *
     * @param id
     */
    public ASTAsignacion(int id) {
        super(id);
    }

    /**
     *
     * @param p
     * @param id
     */
    public ASTAsignacion(Parser p, int id) {
        super(p, id);
    }

    /**
     *
     */
    public void interpret() {
        //20180420 - TOMAR COMO EJEMPLO A ASIGNACION PARA HACER LO DE FUNCIONES
        Debug.println("<<Asignacion - Interpretamos>>");

        // interpretamos el lado derecho de la asignacion
        jjtGetChild(1).interpret();

        // obtenemos el nombre del identificador
        String identificador = ((ASTIdentificador) jjtGetChild(0)).getNombre();

        Debug.println(String.format("<<ASTAsignacion - le asignamos el valor a la variable: [%s] - pila: %s >>", identificador, pila));
        // guardamos el valor de la asignacion en la tabla de simbolos
        ((Dato) getTablaDeSimbolos().get(identificador)).setValor(((Dato) pila.pop()).getValor());

    }

}
