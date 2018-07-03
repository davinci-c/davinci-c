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
public final class AutoCompletarRobotPos extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Posiciona al robot en la intersección definida por la avenida y la calle declarada.</div>\n" +
            "        <div>Ejemplos: \n" +
            "            <ul>\n" +
            "                <li>pos(5,8)</li>\n" +
            "                <li>pos(numAvenida,3)</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "pos(${avenida},${calle})";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotPos(DefaultCompletionProvider provider) {
         super(provider, "pos", PREFIJO + "Pos"+ SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
