package  edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDeRobotsEspecifica extends ConfiguracionDeRobots{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeRobotsEspecifica.class.getName());
    
    /**************
     * CONSTANTES *
     **************/
    
    /**
     * 
     */
    private final static String REPRESENTACION = "%s <%d,%d>";
    
    /*************
     * ATRIBUTOS *
     *************/
    
    /**
     * 
     */
    private String nombre;
    
    /*****************
     * CONSTRUCTORES *
     *****************/
    
    /**
     * 
     */
    public ConfiguracionDeRobotsEspecifica(){
        super();
        
        auditor.finest("Constructor1");
        
        nombre = "";
    }

    /**
     * 
     * @param f
     * @param p
     * @param n 
     */
    public ConfiguracionDeRobotsEspecifica(Integer f, Integer p, String n){
        super(f, p);
        
        auditor.log(Level.FINEST, "Constructor2 {0} {1} {2}", new Object[]{f, p, n});
        
        nombre = n;
    }
    
    /***************
     * PROPIEDADES *
     ***************/

    /**
     * @return the nombre
     */
    public String getNombre() {
        
        auditor.finest("getNombre");
        
        return nombre;
    }

    /**
     * @param n the nombre to set
     */
    public void setNombre(String n) {
        
        auditor.log(Level.FINEST, "setNombre {0}", n);
        
        String oldn = nombre;
        nombre = n;
        getCambios().firePropertyChange("nombre", oldn, n);
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
        
        return String.format(REPRESENTACION, getNombre(), getFlores(), getPapeles());
    }   
}