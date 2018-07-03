package  edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDeRobots extends Configuracion{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeRobots.class.getName());
    
    /*************
     * ATRIBUTOS *
     *************/
    
    /**
     * 
     */
    private Integer flores;
    
    /**
     * 
     */
    private Integer papeles;
    
    /*****************
     * CONSTRUCTORES *
     *****************/
    
    /**
     * 
     */
    public ConfiguracionDeRobots(){
        super();
        
        auditor.finest("Constructor1");
        
        flores  = 0;
        papeles = 0;
    }

    /**
     * 
     * @param f
     * @param p 
     */
    public ConfiguracionDeRobots(Integer f, Integer p){
        super();
        
        auditor.log(Level.FINEST, "Constructor2 {0} {1}", new Object[]{f, p});
        
        flores  = f;
        papeles = p;
    }
    
    /***************
     * PROPIEDADES *
     ***************/
    
    /**
     * @return the flores
     */
    public Integer getFlores() {
        
        auditor.finest("getFlores");
        
        return flores;
    }

    /**
     * @param f the flores to set
     */
    public void setFlores(Integer f) {
        
        auditor.log(Level.FINEST, "setFlores {0}", f);
        
        Integer oldf = flores;
        flores = f;
        getCambios().firePropertyChange("flores", oldf, f);
    }

    /**
     * @return the papeles
     */
    public Integer getPapeles() {
        
        auditor.finest("getPapeles");
        
        return papeles;
    }

    /**
     * @param p the papeles to set
     */
    public void setPapeles(Integer p) {
        
        auditor.log(Level.FINEST, "setPapeles {0}", p);
        
        Integer oldp = papeles;
        papeles = p;
        getCambios().firePropertyChange("papeles", oldp, p);
    }    
}
