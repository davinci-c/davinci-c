/*
 *  Copyright (c) 2011, 2014  - Daniel, Aguil Mallea.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the GNU Public License v3.0
 *  which accompanies this distribution, and is available at
 *  http://www.gnu.org/licenses/gpl.html
 *
 *  Contributors:
 *      Daniel, Aguil Mallea - initial API and implementation
 */
package edu.davinci.ui.editor.autocompletado;

import org.fife.ui.autocomplete.DefaultCompletionProvider;

/**
 *
 * @author dany
 */
public final class AutoCompletarRobotPosAv extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Retorna la avenida en la que se encuentra el robot.</div>\n" +
            "        <div>Ejemplos: \n" +
            "            <ul>\n" +
            "                <li>informar(posAv)</li>\n" +
            "                <li>avenidaImparSiguiente := posAv + 2</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "posAv";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotPosAv(DefaultCompletionProvider provider) {
         super(provider, "posAv", PREFIJO + "PosAv" + SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
