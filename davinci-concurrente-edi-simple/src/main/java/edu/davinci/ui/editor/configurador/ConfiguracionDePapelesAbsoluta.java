package edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDePapelesAbsoluta extends ConfiguracionDePapeles{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDePapelesAbsoluta.class.getName());
    
    /**************
     * CONSTANTES *
     **************/
    
    /**
     * 
     */
    private final static String REPRESENTACION = "Absoluta <%s,%s> = %d";
    
    /*****************
     * CONSTRUCTORES *
     *****************/
    
    /**
     * 
     */
    public ConfiguracionDePapelesAbsoluta(){
        super();
        
        auditor.finest("Constructor1");
    }

    /**
     * 
     * @param a
     * @param c
     * @param n 
     */
    public ConfiguracionDePapelesAbsoluta(Integer a, Integer c, Integer n){
        super(a, c, n);
        
        auditor.log(Level.FINEST, "Constructor2 {0} {1} {2}", new Object[]{a, c, n});
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
        
        //verificamos el símbolo que aparece si es una avenida igual a cero 
        String avenida = (getAvenida()==0?SIMBOLO_COMODIN:getAvenida().toString());
        
        //verificamos el símbolo que aparece si es una calle igual a cero
        String calle = (getCalle()==0?SIMBOLO_COMODIN:getCalle().toString());
        
        //
        return String.format(REPRESENTACION, avenida, calle, getCantidad());
    }   
}
