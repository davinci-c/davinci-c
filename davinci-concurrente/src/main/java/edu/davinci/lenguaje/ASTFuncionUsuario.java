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

import edu.davinci.planificador.tarea.TareaCompuestaFuncUsuario;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ASTFuncionUsuario extends SimpleNode implements ComplexNode {

    String nombre;

    private List<String> nombreParametrosReales = new ArrayList<String>();

    /**
     *
     * @param id
     */
    public ASTFuncionUsuario(int id) {
        super(id);
    }

    /**
     *
     * @param p
     * @param id
     */
    public ASTFuncionUsuario(Parser p, int id) {
        super(p, id);
    }

    /**
     *
     * @param s
     */
    public void addNombreParametrosReales(String s) {
        if (s != null) {
            nombreParametrosReales.add(s.toLowerCase());
        } else {
            nombreParametrosReales.add(null);
        }
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre.toLowerCase();
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        // TODO deberia tener un nombre interno y uno externo
        this.nombre = nombre.toLowerCase();
    }

    /**
     *
     * @return
     */
    public List<String> getNombresParametrosReales() {
        return nombreParametrosReales;
    }

    @Override
    public void interpret() {
        Debug.println(String.format("<<--------Interpretamos la funcion: %s >>", nombre));

        SimpleNode sn = (SimpleNode) this;
        TareaCompuestaFuncUsuario t = new TareaCompuestaFuncUsuario(sn, getTablaDeSimbolos());
        while (t.tieneNodosParaInterpretar()) {
            t.ejecutar();
        }
        Debug.println(String.format("<<--------Finaliza la interpretacion de la funcion: %s >>", nombre));
    }

}
