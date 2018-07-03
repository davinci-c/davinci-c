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
public final class AutoCompletarRobotHayObstaculo extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Retorna verdadero en caso de que en la \n" +
            "siguiente cuadra, hacia donde se encuentra \n" +
            "orientado el robot, exista un obstaculo. En \n" +
            "cualquier otro caso retorna falso.</div>\n" +
            "        <div>Ejemplos: \n" +
            "            <ul>\n" +
            "                <li>informar(hayObstaculo) ... </li>\n" +
            "                <li>mientras( !hayObstaculo )...</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "hayObstaculo";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotHayObstaculo(DefaultCompletionProvider provider) {
         super(provider, "hayObstaculo", PREFIJO + "HayObstaculo" + SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
