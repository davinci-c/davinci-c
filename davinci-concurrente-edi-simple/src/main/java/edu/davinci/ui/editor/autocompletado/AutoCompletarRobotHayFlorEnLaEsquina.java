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
public final class AutoCompletarRobotHayFlorEnLaEsquina extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Retorna verdadero en caso de existir al menos \n" +
            "una flor en la esquina en la que se encuentra el robot, \n" +
            "caso contrario retorna falso.</div>\n" +
            "        <div>Ejemplos: \n" +
            "            <ul>\n" +
            "                <li>si(hayFlorEnLaEsquina) ... </li>\n" +
            "                <li>mientras(hayFlorEnLaEsquina)...</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "hayFlorEnLaEsquina";
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotHayFlorEnLaEsquina(DefaultCompletionProvider provider) {
         super(provider, "hayFlorEnLaEsquina", PREFIJO + "HayFlorEnLaEsquina" + SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
