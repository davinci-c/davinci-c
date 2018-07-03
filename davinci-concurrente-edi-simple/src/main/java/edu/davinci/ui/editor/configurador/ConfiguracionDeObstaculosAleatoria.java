package edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDeObstaculosAleatoria extends ConfiguracionDeObstaculos{
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeObstaculosAleatoria.class.getName());
    
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
    public ConfiguracionDeObstaculosAleatoria(){
        super();
        
        auditor.finest("Constructor1");
        
        ancho    = 0;
        alto     = 0;
        cantidad = 0;
    }


    /**
     * 
     * @param a
     * @param c
     * @param n
     * @param h
     * @param l 
     */
    public ConfiguracionDeObstaculosAleatoria(Integer a, Integer c, Integer n, Integer h, Integer l){
        super(a, c);
        
        auditor.log(Level.FINEST, "Constructor2 {0} {1} {2} {3} {4}", new Object[]{a, c, n, h, l});
        
        cantidad = n;
        ancho    = h;
        alto     = l;
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
