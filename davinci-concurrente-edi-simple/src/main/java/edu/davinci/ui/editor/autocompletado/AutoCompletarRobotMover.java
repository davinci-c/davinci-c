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
public final class AutoCompletarRobotMover extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Avanza una cuadra al robot en la dirección que \n" +
            "este se encuentre.</div>\n" +
            "        <div>Ejemplo: \n" +
            "            <ul>\n" +
            "                <li>mover</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "mover";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotMover(DefaultCompletionProvider provider) {
         super(provider, "mover", PREFIJO + "Mover" + SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
