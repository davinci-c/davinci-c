package  edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDeRobotsUnica extends ConfiguracionDeRobots{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeRobotsUnica.class.getName());
    
    /**************
     * CONSTANTES *
     **************/
    
    /**
     * 
     */
    private final static String REPRESENTACION = "Todos <%d,%d>";
    
    /*****************
     * CONSTRUCTORES *
     *****************/
    
    /**
     * 
     */
    public ConfiguracionDeRobotsUnica(){
        super();
        
        auditor.finest("Constructor1");
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
        
        return String.format(REPRESENTACION, getFlores(), getPapeles());
    }
}
