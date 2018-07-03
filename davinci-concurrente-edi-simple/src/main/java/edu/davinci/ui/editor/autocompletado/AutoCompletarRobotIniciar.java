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
public final class AutoCompletarRobotIniciar extends AutoCompletarRobot {
    
    //
    private static final String AYUDA = 
            "<div>Introduce un robot en la ciudad en la avenida \n" +
            "uno y calle uno (1,1) orientándolo hacia el norte. \n" +
            "Esta primitiva solo puede ser invocada una única \n" +
            "vez en cada hilo de ejecución incluido el programa \n" +
            "principal..</div>\n" +
            "        <div>Ejemplo: \n" +
            "            <ul>\n" +
            "                <li>iniciar</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "iniciar";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarRobotIniciar(DefaultCompletionProvider provider) {
         super(provider, "iniciar", PREFIJO + "Iniciar"+ SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
