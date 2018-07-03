package edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDePapeles extends ConfiguracionDeEsquinaPorCantidad {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDePapeles.class.getName());
    
    /*****************
     * CONSTRUCTORES *
     *****************/
    
    /**
     * 
     */
    public ConfiguracionDePapeles(){
        super();
        
        auditor.finest("Constructor1");
    }

    /**
     * 
     * @param a
     * @param c
     * @param n 
     */
    public ConfiguracionDePapeles(Integer a, Integer c, Integer n){
        super(a, c, n);
        
        auditor.log(Level.FINEST, "Constructor2 {0} {1} {2}", new Object[]{a, c, n});
    }
}
