package  edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.logging.Logger;

/**
 *
 * @author german.tejero
 */
public class ConfiguracionDeInterprete extends Configuracion{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeInterprete.class.getName());

    /**
     * 
     */
    public static final String RETARDO = "retardo";
    
    /**
     * 
     */
    public static final String QUANTUM = "quantum";
    
    /**
     * 
     */
    public static final String PLANIFICADOR = "planificador";

    
    
    /**
     * 
     */
    private Integer retardo;

    /**
     * 
     */
    private Integer quantum;
    
    /**
     * 
     */
    private String planificador;
    
    /**
     * 
     */
    public ConfiguracionDeInterprete(){
    
        //DEBUG
        auditor.finest("ConfiguracionDeInterprete");
        
        //
        retardo = 0;
        quantum = 0;
        planificador = null;
    }
    
    /**
     * 
     * @param r
     * @param p 
     */
    public ConfiguracionDeInterprete(Integer r, String p){

        //DEBUG
        auditor.finest("ConfiguracionDeInterprete");
        
        //
        retardo = r;
        quantum = 0;
        planificador = p;                        
    }

    /**
     * @return the retardo
     */
    public Integer getRetardo() {
        
        //DEBUG
        auditor.finest("getRetardo");
        
        //
        return retardo;
    }

    /**
     * @param r the retardo to set
     */
    public void setRetardo(Integer r) {
        
        //DEBUG
        auditor.finest("setRetardo");
        
        //
        Integer oldretardo = retardo;
        retardo = r;
        getCambios().firePropertyChange(RETARDO, oldretardo, retardo);
    }

    /**
     * @return the retardo
     */
    public Integer getQuantum() {
        
        //DEBUG
        auditor.finest("getQuantum");
        
        //
        return quantum;
    }

    /**
     * @param q
     */
    public void setQuantum(Integer q) {
        
        //DEBUG
        auditor.finest("setQuantum");
        
        //
        Integer oldquantum = quantum;
        quantum = q;
        getCambios().firePropertyChange(QUANTUM, oldquantum, quantum);
    }
    
    /**
     * @return the planificador
     */
    public String getPlanificador() {
        
        //DEBUG
        auditor.finest("getPlanificador");
        
        //
        return planificador;
    }

    /**
     * @param p the planificador to set
     */
    public void setPlanificador(String p) {
        
        //DEBUG
        auditor.finest("setPlanificador");
        
        //
        String oldplanificador = planificador;
        planificador = p;
        getCambios().firePropertyChange(PLANIFICADOR, oldplanificador, planificador);
    }
}