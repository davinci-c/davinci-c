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

public class ASTExpresion extends SimpleNode {// implements ComplexNode {

    public ASTExpresion(int id) {
        super(id);
    }

    public ASTExpresion(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void interpret() {
        // TODO para permitir interrumpir hay q trabajar con asignacion
        Debug.println("<<Expresion>>");

        // obtenemos todas las partes de la expresion
        Node[] expresion = getChildren();

        // recorremos las partes
        for (Node node : expresion) {

            // interpretamos
            node.interpret();
        }
    }

}
