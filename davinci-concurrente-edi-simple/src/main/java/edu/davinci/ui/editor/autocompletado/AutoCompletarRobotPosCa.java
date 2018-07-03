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
public final class AutoCompletarRobotPosCa extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Retorna la calle en la que se encuentra el robot.</div>\n" +
            "        <div>Ejemplos: \n" +
            "            <ul>\n" +
            "                <li>informar(posCa)</li>\n" +
            "                <li>calle := posCa + 1</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "posCa";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotPosCa(DefaultCompletionProvider provider) {
         super(provider, "posCa", PREFIJO + "PosCa" + SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
