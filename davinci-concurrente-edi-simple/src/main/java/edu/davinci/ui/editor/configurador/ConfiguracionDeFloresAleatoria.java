package edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDeFloresAleatoria extends ConfiguracionDeFlores{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeFloresAleatoria.class.getName());
    
    /**************
     * CONSTANTES *
     **************/
    
    /**
     * 
     */
    private final static String REPRESENTACION = "Aleatoria <%d,%d> <%d,%d> = %d";
    
    /*************
     * ATRIBUTOS *
     *************/
    
    /**
     * 
     */
    private Integer ancho;
    
    /**
     * 
     */
    private Integer alto;
    
    /*****************
     * CONSTRUCTORES *
     *****************/
    
    /**
     * 
     */
    public ConfiguracionDeFloresAleatoria(){
        super();
        
        auditor.finest("Constructor1");
        
        ancho = 0;
        alto  = 0;
    }

    /**
     * 
     */
    public ConfiguracionDeFloresAleatoria(Integer a, Integer c, Integer n, Integer h, Integer l){
        super(a, c, n);
        
        auditor.log(Level.FINEST, "Constructor2 {0} {1} {2} {3} {4}", new Object[]{a, c, n, h, l});
        
        ancho = h;
        alto  = l;
    }
    
    /***************
     * PROPIEDADES *
     ***************/

    /**
     * @return the ancho
     */
    public Integer getAncho() {
        
        auditor.finest("getAncho");
        
        return ancho;
    }

    /**
     * @param a the ancho to set
     */
    public void setAncho(Integer a) {
        
        auditor.log(Level.FINEST, "setAncho {0}", a);
        
        Integer olda = ancho;
        ancho = a;
        getCambios().firePropertyChange("ancho", olda, a);
    }

    /**
     * @return the alto
     */
    public Integer getAlto() {
        
        auditor.finest("getAlto");
        
        return alto;
    }

    /**
     * @param a the alto to set
     */
    public void setAlto(Integer a) {
        
        auditor.log(Level.FINEST, "setAlto {0}", a);
        
        Integer olda = alto;
        alto = a;
        getCambios().firePropertyChange("alto", olda, a);
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
        
        return String.format(REPRESENTACION, getAvenida(), getCalle(), getAncho(), getAlto(), getCantidad());
    }
    
}
