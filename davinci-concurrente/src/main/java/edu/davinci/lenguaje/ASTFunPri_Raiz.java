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

/**
 * The Class ASTFunPri_Raiz.
 */
public class ASTFunPri_Raiz extends SimpleNode {

    /**
     * Instantiates a new aST fun pri_ aleatorio.
     *
     * @param id
     */
    public ASTFunPri_Raiz(int id) {
        super(id);
    }

    /**
     * Instantiates a new aST fun pri_ aleatorio.
     *
     * @param p
     * @param id
     */
    public ASTFunPri_Raiz(Parser p, int id) {
        super(p, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.davinci.lenguaje.MyNode#interpret()
     */
    @Override
    public void interpret() {
        Debug.println("<<Pri_Raiz>>");

        // Interpretamos la expresion de la funcion
        jjtGetChild(0).interpret();

        // guardamos la avenida
        // int value = ((DatoEntero) pila[indicePila--]).getValor();
        int value = ((DatoEntero) pila.pop()).getValor();

        Double resultado = Math.sqrt(value);
        // pila[++indicePila] = new DatoEntero(r.nextInt(value));
        pila.push(new DatoEntero(resultado.intValue()));

    }

}
