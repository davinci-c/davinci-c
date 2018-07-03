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

import javax.swing.text.JTextComponent;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

/**
 *
 * @author dany
 */
public final class AutoCompletar {
    
    /**
     * Habilita el autocompletado según las reglas gramaticales
     * @param visor 
     */
    public static void habilitar(JTextComponent visor){
        
        CompletionProvider provider = createCompletionProvider();

        AutoCompletion ac = new AutoCompletion(provider);

        //instala el autocompletado en el visor
        ac.install(visor);

        //activa el autocompletado
        ac.setAutoCompleteEnabled(true);

        //activa la auto activacion
        ac.setAutoActivationEnabled(true);

        //muestra la descripción
        ac.setShowDescWindow(true);

        //se inserta automaticamente cuando es la única opcion posible
        ac.setAutoCompleteSingleChoices(false);

        //
        ac.setParameterAssistanceEnabled(true);

        //
        ac.setShowDescWindow(true);
        
    }
    
    /**
     * Crea y retorna el proveedor de autocompletado
     * @return 
     */
    private static CompletionProvider createCompletionProvider() {

        //
        DefaultCompletionProvider provider = new DefaultCompletionProvider();

        //
        provider.addCompletion(new AutoCompletarEjemploEstructura(provider));

        //ROBOT
        
        //
        provider.addCompletion(new AutoCompletarRobotIniciar(provider));

        //
        provider.addCompletion(new AutoCompletarRobotDerecha(provider));

        //
        provider.addCompletion(new AutoCompletarRobotMover(provider));

        //
        provider.addCompletion(new AutoCompletarRobotPos(provider));

        //
        provider.addCompletion(new AutoCompletarRobotTomarFlor(provider));

        //
        provider.addCompletion(new AutoCompletarRobotTomarPapel(provider));

        //
        provider.addCompletion(new AutoCompletarRobotDepositarFlor(provider));

        //
        provider.addCompletion(new AutoCompletarRobotDepositarPapel(provider));

        //
        provider.addCompletion(new AutoCompletarRobotPosAv(provider));

        //
        provider.addCompletion(new AutoCompletarRobotPosCa(provider));

        //
        provider.addCompletion(new AutoCompletarRobotHayFlorEnLaBolsa(provider));

        //
        provider.addCompletion(new AutoCompletarRobotHayFlorEnLaEsquina(provider));

        //
        provider.addCompletion(new AutoCompletarRobotHayPapelEnLaBolsa(provider));

        //
        provider.addCompletion(new AutoCompletarRobotHayPapelEnLaEsquina(provider));

        //
        provider.addCompletion(new AutoCompletarRobotHayObstaculo(provider));

        //PRIMITIVAS
        
        //
        provider.addCompletion(new AutoCompletarPrimitivaInformar(provider));

        //
        provider.addCompletion(new AutoCompletarPrimitivaPedir(provider));

        
        return provider;
    }

}
