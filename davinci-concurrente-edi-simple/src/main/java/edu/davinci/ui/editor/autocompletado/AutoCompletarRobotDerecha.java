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
public final class AutoCompletarRobotDerecha extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Gira 90∘ al robot de acuerdo su orientación. El \n" +
            "giro se produce en el mismo sentido que las del reloj, las posibles " +
            "orientaciones son norte, sur, \n" +
            "este y oeste.</div>\n" +
            "        <div>Ejemplo: \n" +
            "            <ul>\n" +
            "                <li>derecha</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "derecha";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotDerecha(DefaultCompletionProvider provider) {
         super(provider, "derecha", PREFIJO + "Derecha" + SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
