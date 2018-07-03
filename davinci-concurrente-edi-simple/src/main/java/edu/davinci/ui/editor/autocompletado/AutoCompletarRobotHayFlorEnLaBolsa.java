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
public final class AutoCompletarRobotHayFlorEnLaBolsa extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Retorna verdadero en caso de existir en la bolsa \n" +
            "del robot al menos una flor.</div>\n" +
            "        <div>Ejemplos: \n" +
            "            <ul>\n" +
            "                <li>si(hayFlorEnLaBolsa) ... </li>\n" +
            "                <li>mientras(hayFlorEnLaBolsa)...</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "hayFlorEnLaBolsa";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotHayFlorEnLaBolsa(DefaultCompletionProvider provider) {
         super(provider, "hayFlorEnLaBolsa", PREFIJO + "HayFlorEnLaBolsa" + SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
