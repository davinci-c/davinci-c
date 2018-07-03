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
public final class AutoCompletarRobotDepositarPapel extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Deposita un papel en la esquina en la que se \n" +
            "encuentra el robot. Si se intentase depositar un \n" +
            "papel en una esquina y el robot no tuviera papeles \n" +
            "en la bolsa se producirá un error que abortará la \n" +
            "ejecución del programa.</div>\n" +
            "        <div>Ejemplo: \n" +
            "            <ul>\n" +
            "                <li>depositarPapel</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "depositarPapel";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotDepositarPapel(DefaultCompletionProvider provider) {
         super(provider, "depositarPapel", PREFIJO + "Depositar Papel" + SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
