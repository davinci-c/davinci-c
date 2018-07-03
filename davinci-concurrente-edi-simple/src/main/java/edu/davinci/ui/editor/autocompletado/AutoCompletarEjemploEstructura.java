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
import org.fife.ui.autocomplete.TemplateCompletion;

/**
 *
 * @author dany
 */
public final class AutoCompletarEjemploEstructura extends TemplateCompletion {
    
    //
    private static final String AYUDA = 
            "<div>Muestra un ejemplo de la estructura que puede tener un programa en DaVinci Concurrente.</div>";
    
    private static final String EXPR = "programa ${nombre} \ncomenzar\n\tiniciar\n\tmover\nfin";
    
    /**
     * 
     * @param provider 
     */
    public AutoCompletarEjemploEstructura(DefaultCompletionProvider provider) {
         super(provider, "ejemplo estructura", ":: Ejemplo estructura", EXPR, "", AYUDA);
         
    }
       
}
