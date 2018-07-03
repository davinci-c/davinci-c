package edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDeEsquina extends Configuracion{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeEsquina.class.getName());
    
    /*************
     * ATRIBUTOS *
     *************/
    
    /**
     * 
     */
    private Integer avenida;
    
    /**
     * 
     */
    private Integer calle;

    /*****************
     * CONSTRUCTORES *
     *****************/
    
    /**
     * 
     */
    public ConfiguracionDeEsquina(){
        super();
        
        auditor.finest("Constructor1");
        
        avenida = 0;
        calle   = 0;
    }

    /**
     * 
     * @param a
     * @param c 
     */
    public ConfiguracionDeEsquina(Integer a, Integer c){
        super();
        
        auditor.log(Level.FINEST, "Constructor2 {0} {1}", new Object[]{a, c});
        
        avenida = a;
        calle   = c;
    }
    
    /***************
     * PROPIEDADES *
     ***************/
    
    /**
     * @return the avenida
     */
    public Integer getAvenida() {
        
        auditor.finest("getAvenida");
        
        return avenida;
    }

    /**
     * @param a the avenida to set
     */
    public void setAvenida(Integer a) {
        
        auditor.log(Level.FINEST, "setAvenida {0}", a);
        
        Integer olda = avenida;
        avenida = a;
        getCambios().firePropertyChange("avenida", olda, a);
    }

    /**
     * @return the calle
     */
    public Integer getCalle() {
        
        auditor.finest("getCalle");
        
        return calle;
    }

    /**
     * @param c the calle to set
     */
    public void setCalle(Integer c) {
        
        auditor.log(Level.FINEST, "setCalle {0}", c);
        
        Integer oldc = calle;
        calle = c;
        getCambios().firePropertyChange("calle", oldc, c);
    }
}
