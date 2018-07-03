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
public final class AutoCompletarRobotHayPapelEnLaEsquina extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Retorna verdadero en caso de existir al menos \n" +
            "un papel en la esquina en la que se encuentra el robot, \n" +
            "caso contrario retorna falso.</div>\n" +
            "        <div>Ejemplos: \n" +
            "            <ul>\n" +
            "                <li>si(hayPapelEnLaEsquina) ... </li>\n" +
            "                <li>mientras(hayPapelEnLaEsquina)...</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "hayPapelEnLaEsquina";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotHayPapelEnLaEsquina(DefaultCompletionProvider provider) {
         super(provider, "hayPapelEnLaEsquina", PREFIJO + "HayPapelEnLaEsquina" + SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
