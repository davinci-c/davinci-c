package  edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDeEsquinaPorCantidad extends ConfiguracionDeEsquina{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeEsquinaPorCantidad.class.getName());
    
    /*************
     * ATRIBUTOS *
     *************/
    
    /**
     * 
     */
    private Integer cantidad;
    
    /*****************
     * CONSTRUCTORES *
     *****************/
    
    /**
     * 
     */
    public ConfiguracionDeEsquinaPorCantidad(){
        super();
        
        auditor.finest("Constructor1");
        
        cantidad = 0;
    }

    /**
     * 
     * @param a
     * @param c
     * @param n 
     */
    public ConfiguracionDeEsquinaPorCantidad(Integer a, Integer c, Integer n){
        super(a, c);
        
        auditor.log(Level.FINEST, "Constructor1 {0} {1} {2}", new Object[]{a, c, n});
        
        cantidad = n;
    }
    
    /***************
     * PROPIEDADES *
     ***************/

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        
        auditor.finest("getCantidad");
        
        return cantidad;
    }

    /**
     * @param c the cantidad to set
     */
    public void setCantidad(Integer c) {
        
        auditor.log(Level.FINEST, "setCantidad {0}", c);
        
        Integer oldc = cantidad;
        cantidad = c;
        getCambios().firePropertyChange("cantidad", oldc, c);
    }
}
