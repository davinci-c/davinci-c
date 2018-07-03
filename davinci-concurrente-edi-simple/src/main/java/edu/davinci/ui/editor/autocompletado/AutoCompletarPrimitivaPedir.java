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
public final class AutoCompletarPrimitivaPedir extends AutoCompletarPrimitiva {
    
    //
    private static final String AYUDA = 
            "<div>Pide por pantalla el valor para almacenar en una variable.</div>\n" +
            "        <div>Ejemplo: \n" +
            "            <ul>\n" +
            "                <li>pedir(variable)</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "pedir(${variable})";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarPrimitivaPedir(DefaultCompletionProvider provider) {
         super(provider, "pedir", PREFIJO + "Pedir"+ SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
