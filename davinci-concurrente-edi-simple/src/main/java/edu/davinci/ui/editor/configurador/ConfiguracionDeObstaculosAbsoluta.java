package edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDeObstaculosAbsoluta extends ConfiguracionDeObstaculos{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeObstaculosAbsoluta.class.getName());
    
    /**************
     * CONSTANTES *
     **************/
    
    /**
     * 
     */
    private final static String REPRESENTACION = "Absoluta <%d,%d>";
    
    /*****************
     * CONSTRUCTORES *
     *****************/
    
    /**
     * 
     */
    public ConfiguracionDeObstaculosAbsoluta(){
        super();
        
        auditor.finest("Constructor1");
    }

    /**
     * 
     * @param a
     * @param c
     * @param n 
     */
    public ConfiguracionDeObstaculosAbsoluta(Integer a, Integer c){
        super(a, c);
        
        auditor.log(Level.FINEST, "Constructor2 {0} {1}", new Object[]{a, c});
    }
    
    /***************
     * OPERACIONES *
     ***************/
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
        
        auditor.finest("toString");
        
        return String.format(REPRESENTACION, getAvenida(), getCalle());
    }
}
