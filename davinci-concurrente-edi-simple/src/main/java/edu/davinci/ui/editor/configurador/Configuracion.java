package  edu.davinci.ui.editor.configurador;

//JAVA IO
import java.io.Serializable;
//JAVA UTIL
import java.util.logging.Logger;
//JAVA BEANS
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Clase base qeu representa un elemento de configuracion.
 *
 * @author german.tejero
 */
public class Configuracion extends Object implements Serializable{

    /**
    * Símbolo para identificar el comodin en una avenida o calle
    */
    public static final String SIMBOLO_COMODIN = "?";
    
    /**
     * Identificador de serializacion
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor.
     */
    private static final Logger auditor = Logger.getLogger(Configuracion.class.getName());
    
    /**
     * Soporte para notificacion de cambios.
     */
    private PropertyChangeSupport cambios;
    
    /**
     * Constructor
     */
    public Configuracion(){
        //
        super();
        
        //
        auditor.finest("Constructor");
        
        //
        cambios = new PropertyChangeSupport(this);
    }
    
    /**
     * Permite obtener el soporte de notificacion de cambios para clases derivadas.
     * 
     * @return 
     */
    protected PropertyChangeSupport getCambios(){
        
        //DEBUG
        auditor.finest("getCambios");
        
        //
        return cambios;
    }
    
    /**
     * Agrega un nuevo oyente de cambios.
     * 
     * @param oyente Oyente de cambios.
     */
    public void addPropertyChangeListener(PropertyChangeListener oyente) {
        
        //DEBUG
        auditor.finest("addPropertyChangeListener");
        
        //
        cambios.addPropertyChangeListener(oyente);
    }
    
    /**
     * Remueve un nuevo oyente de cambios.
     * 
     * @param oyente Oyente a remover.
     */
    public void removePropertyChangeListener(PropertyChangeListener oyente) {
        
        //DEBUG
        auditor.finest("removePropertyChangeListener");
        
        //
        cambios.removePropertyChangeListener(oyente);
    }
}
