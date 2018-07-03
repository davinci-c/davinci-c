package  edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.List;
import java.util.ArrayList;
import java.util.ServiceLoader;
import java.util.logging.Logger;
//JAVAX SWING
import javax.swing.ComboBoxModel;
import javax.swing.AbstractListModel;
//DAVINCI
import edu.davinci.planificador.Planificador;

/**
 *
 * @author german.tejero
 */
public class ListaDePlanificadores extends AbstractListModel<Planificador> implements ComboBoxModel<Planificador> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ListaDePlanificadores.class.getName());

    /**
     * 
     */
    private static ServiceLoader<Planificador> cargadorDePlanificadores = ServiceLoader.load(Planificador.class);
    
    /**
     * 
     */
    private List<Planificador> planificadores;
    
    /**
     * 
     */
    private Integer seleccionado;
    
    /**
     * 
     */
    public ListaDePlanificadores(){
    
        //DEBUG
        auditor.finest("Constructor");
        
        //
        planificadores = new ArrayList<Planificador>();
        
        //
        for (Planificador planificador : cargadorDePlanificadores) {
            
            //DEBUG
            auditor.finest(String.format("planificador encontrado: %s", planificador.toString()));
            
            //
            planificadores.add(planificador);
        }
        
        //
        if(planificadores.size() > 0){
            seleccionado = 0;
        }else{
            seleccionado = -1;
        }
    }
   
    /**
     * 
     * @param planificador
     * @return 
     */
    public Boolean seleccionarPlanificador(String planificador){
        
        //DEBUG
        auditor.finest("seleccionarPlanificador");
        
        //
        for(Planificador p : planificadores){
            
            //
            if(p.getClass().getCanonicalName().equals(planificador)){
                
                //
                setSelectedItem(p);
                return true;
            }
        }
        
        //
        return false;
    }
    
    /**
     * 
     * @param planificador 
     */
    @Override
    public void setSelectedItem(Object planificador) {
        
        //DEBUG
        auditor.finest("setSelectedItem");
        
        //
        seleccionado = planificadores.indexOf(planificador);
    }

    /**
     * 
     * @return 
     */
    @Override
    public Object getSelectedItem() {
        
        //DEBUG
        auditor.finest("getSelectedItem");
        
        //
        return getElementAt(seleccionado);
    }

    /**
     * 
     * @return 
     */
    @Override
    public int getSize() {
        
        //DEBUG
        auditor.finest("getSize");
        
        //
        return planificadores.size();
    }

    /**
     * 
     * @param posicion
     * @return 
     */
    @Override
    public Planificador getElementAt(int posicion) {

        //DEBUG
        auditor.finest("getElementAt");
        
        //
        if(posicion >= 0 && posicion < planificadores.size()){
            return planificadores.get(posicion);
        }else{
            return null;
        }
    }
}