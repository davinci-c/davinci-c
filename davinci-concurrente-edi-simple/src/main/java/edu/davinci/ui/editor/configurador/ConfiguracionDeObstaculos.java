package edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDeObstaculos extends ConfiguracionDeEsquina {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeObstaculos.class.getName());
    
    /*****************
     * CONSTRUCTORES *
     *****************/
    
    /**
     * 
     */
    public ConfiguracionDeObstaculos(){
        super();
        
        auditor.finest("Constructor1");
    }

    /**
     * 
     * @param a
     * @param c
     * @param n 
     */
    public ConfiguracionDeObstaculos(Integer a, Integer c){
        super(a, c);
        
        auditor.log(Level.FINEST, "Constructor2 {0} {1}", new Object[]{a, c});
    }
}
