/**
 * *****************************************************************************
 * Copyright (c) 2011, 2013 - Daniel, Aguil Mallea. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the GNU Public License v3.0 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Daniel, Aguil Mallea - initial API and implementation
 * ****************************************************************************
 */
package edu.davinci.lenguaje;

import java.util.Hashtable;

@SuppressWarnings("all")
public class ASTIdentificador extends SimpleNode {

    private String nombre;

    /**
     *
     * @param id
     */
    public ASTIdentificador(int id) {
        super(id);
    }

    /**
     *
     * @param p
     * @param id
     */
    public ASTIdentificador(Parser p, int id) {
        super(p, id);
    }

    /**
     * Retorna el nombre del indentificador
     *
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        // TODO deberia tener un nombre interno y uno externo
        this.nombre = nombre.toLowerCase();
    }

    /**
     *
     */
    public void interpret() {
        Debug.println("<<Identificador - interpretamos la variable>>");
        Hashtable<String, Object> tabla = getTablaDeSimbolos();
        // Insertamos en la cabecera de la pila el identificador contenido en la
        // tabla de simbolos
        // pila[++indicePila] = getTablaDeSimbolos().get(nombre);
        pila.push(tabla.get(nombre));

        Debug.println(String.format("<<Identificador - pila: %s >>", pila));
    }
}
