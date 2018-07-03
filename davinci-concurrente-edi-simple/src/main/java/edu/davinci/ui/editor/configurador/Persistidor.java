package edu.davinci.ui.editor.configurador;

/**
 * Clase que permite leer y guardar elementos de configuracion.
 *
 * @author german.tejero
 */
public interface Persistidor {
    
    /**
     * Lee un elmento de configuracion.
     * 
     * @return El elemento de configuracion leido.
     * @throws Exception Lanza una excepcion en caso de no poder leer la configuracion.
     */
    public Configuracion cargarConfiguracion() throws Exception;

    /**
     * Guarda un elemento de configuracion.
     * 
     * @param configuracion Elemento de configuracion a guardar.
     * @throws Exception Lanza una excepcion en caso de no poder guardar la configuracion.
     */
    public void guardarConfiguracion(Configuracion configuracion) throws Exception;
}