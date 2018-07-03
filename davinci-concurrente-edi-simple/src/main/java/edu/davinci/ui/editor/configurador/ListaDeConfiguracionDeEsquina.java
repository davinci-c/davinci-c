package edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
//JAVA SWING
import javax.swing.AbstractListModel;

/**
 *
 * @author german.tejero
 */
public class ListaDeConfiguracionDeEsquina extends AbstractListModel<ConfiguracionDeEsquina> implements Iterable<ConfiguracionDeEsquina> {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ListaDeConfiguracionDeEsquina.class.getName());
    
    /**
     * 
     */
    private List<ConfiguracionDeEsquina> configuraciones;
    
    /**
     * 
     */
    public ListaDeConfiguracionDeEsquina(){
    
        auditor.finest("Constructor1");
        
        configuraciones = new LinkedList<ConfiguracionDeEsquina>();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int getSize() {
        
        auditor.finest("getSize");

        return configuraciones.size();
    }

    /**
     * 
     * @param indice
     * @return 
     */
    @Override
    public ConfiguracionDeEsquina getElementAt(int indice) {
        
        auditor.log(Level.FINEST, "getElementAt {0}", indice);
        
        return configuraciones.get(indice);
    }
    
    /**
     * 
     * @param configuracion 
     */
    public void agregarConfiguracion(ConfiguracionDeEsquina configuracion){
    
        auditor.log(Level.FINEST, "agregarConfiguracion {0}", configuracion);
        
        if(configuraciones.add(configuracion)){
        
            Integer indice = configuraciones.indexOf(configuracion);
            
            fireIntervalAdded(this, indice, indice);
        }        
    }
    
    /**
     * 
     * @param configuracion 
     */
    public void eliminarConfiguracion(Integer indice){
    
        auditor.log(Level.FINEST, "eliminarConfiguracion {0}", indice);
        
        configuraciones.remove(indice.intValue());
        
        fireIntervalRemoved(this, indice, indice);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public Iterator<ConfiguracionDeEsquina> iterator() {
        
        auditor.finest("iterator");
        
        return configuraciones.listIterator();
    }
}
