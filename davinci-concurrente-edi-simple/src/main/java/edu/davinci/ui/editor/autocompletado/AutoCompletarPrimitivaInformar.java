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
public final class AutoCompletarPrimitivaInformar extends AutoCompletarPrimitiva {
    
    //
    private static final String AYUDA = 
            "<div>Informa por pantalla el valor de una expresión.</div>\n" +
            "        <div>Ejemplos: \n" +
            "            <ul>\n" +
            "                <li>informar(\"hola\")</li>\n" +
            "                <li>informar(variable)</li>\n" +
            "                <li>informar(\"hola \",nombre)</li>\n" +
            "            </ul>\n" +
            "        </div>";
    
    private static final String EXPR = "informar(\"${expr}\")";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarPrimitivaInformar(DefaultCompletionProvider provider) {
         super(provider, "informar", PREFIJO + "Informar"+ SUFIJO, EXPR, "", AYUDA);
         
    }
       
}
