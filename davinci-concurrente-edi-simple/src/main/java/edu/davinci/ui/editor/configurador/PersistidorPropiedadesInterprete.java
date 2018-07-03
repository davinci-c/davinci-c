package edu.davinci.ui.editor.configurador;

import edu.davinci.ui.OpcionesInterprete;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.Properties;


/**
 *
 * @author german.tejero
 */
public class PersistidorPropiedadesInterprete implements Persistidor{

    /**
     * 
     */
    private final static String PLANIFICADOR = "planificador";

    
    /**
     * 
     */
    private final static String QUANTUM = "planificador.quantum";
    
    /**
     * 
     */
    private final static String RETARDO = "ejecutor.retardo";
    
    /**
     * 
     */
    private File archivo;
    
    /**
     * 
     * @param a 
     */
    public PersistidorPropiedadesInterprete(File a){
        archivo = a;
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    @Override
    public Configuracion cargarConfiguracion() throws Exception {
        
        FileInputStream fis = new FileInputStream(archivo);
        Properties propiedades = new Properties();
        ConfiguracionDeInterprete cdi = new ConfiguracionDeInterprete();
        
        propiedades.load(fis);
        fis.close();
        
        String planificador = propiedades.getProperty(PLANIFICADOR);
        Integer quantum     = Integer.valueOf(propiedades.getProperty(QUANTUM,"0"));
        Integer retardo     = Integer.valueOf(propiedades.getProperty(RETARDO, "0"));
       
        cdi.setPlanificador(planificador);
        cdi.setQuantum(quantum);
        cdi.setRetardo(retardo);
        
        return cdi;
    }

    /**
     * 
     * @param configuracion
     * @throws Exception 
     */
    @Override
    public void guardarConfiguracion(Configuracion configuracion) throws Exception {
        
        FileInputStream fis = new FileInputStream(archivo);
        Properties propiedades = new Properties();
        
        propiedades.load(fis);
        fis.close();

        ConfiguracionDeInterprete cdi = (ConfiguracionDeInterprete)configuracion;
        propiedades.setProperty(PLANIFICADOR, cdi.getPlanificador());
        if(cdi.getQuantum() != null){
            propiedades.setProperty(QUANTUM, cdi.getQuantum().toString());
        }else{
            propiedades.setProperty(QUANTUM, "0");
        }
        if(cdi.getRetardo() != null){
            propiedades.setProperty(RETARDO, cdi.getRetardo().toString());
        }else{
            propiedades.setProperty(RETARDO, "0");
        }
        
        FileOutputStream fos = new FileOutputStream(archivo);
        propiedades.store(fos, "");
        fos.close();
    }
}
