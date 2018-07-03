package edu.davinci.ui.editor.configurador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 *
 * @author german.tejero
 */
public class PersistidorPropiedadesCiudad implements Persistidor {

    /**
     *
     */
    private static final String OBSTACULOS = "obstaculos";

    /**
     *
     */
    private static final String PAPELES = "papeles";
    private static final String PAPELES_ALEATORIO = "papeles.aleatorio";

    /**
     *
     */
    private static final String FLORES = "flores";
    private static final String FLORES_ALEATORIO = "flores.aleatorio";

    /**
     *
     */
    private static final String ROBOTS_SUPERPUESTOS = "robot.superpuesto";
    private static final String ROBOTS_FLORES = "flores.bolsa";
    private static final String ROBOTS_PAPELES = "papeles.bolsa";
    
    private static final String HABILITAR_ROBOTFISICO = "habilitar.robotfisico";
    private static final String IP_ROBOTFISICO = "ip.robotfisico";
    private static final String PUERTO_ROBOTFISICO = "puerto.robotfisico";

    /**
     *
     */
    private File archivo;

    /**
     *
     * @param a
     */
    public PersistidorPropiedadesCiudad(File a) {
        archivo = a;
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public Configuracion cargarConfiguracion() throws Exception {
        
        FileInputStream fis = new FileInputStream(archivo);
        Properties propiedades = new Properties();
        ConfiguracionDeCiudad cdc = new ConfiguracionDeCiudad();
        
        propiedades.load(fis);
        fis.close();

        //Extraemos los valores de cada propiedad
        Boolean robost_superpuestos = Boolean.valueOf(propiedades.getProperty(ROBOTS_SUPERPUESTOS));
        Integer robots_flores = Integer.valueOf(propiedades.getProperty(ROBOTS_FLORES));
        Integer robost_papeles = Integer.valueOf(propiedades.getProperty(ROBOTS_PAPELES));
        String flores = propiedades.getProperty(FLORES);
        Integer flores_aleatorios = Integer.valueOf(propiedades.getProperty(FLORES_ALEATORIO));
        String papeles = propiedades.getProperty(PAPELES);
        Integer papeles_aleatorios = Integer.valueOf(propiedades.getProperty(PAPELES_ALEATORIO));
        String obstaculos = propiedades.getProperty(OBSTACULOS);
        
        Boolean habilitar_robotfisico = Boolean.valueOf(propiedades.getProperty(HABILITAR_ROBOTFISICO));
        String ip_robotfisico = propiedades.getProperty(IP_ROBOTFISICO);
        Integer puerto_robot_fisico = Integer.valueOf(propiedades.getProperty(PUERTO_ROBOTFISICO));
        
        cdc.setHabilitarRobotFisico(habilitar_robotfisico);
        cdc.setIpRobotFisico(ip_robotfisico);
        cdc.setPuertoRobotFisico(puerto_robot_fisico);

        //Superposicion
        cdc.setPermitirSuperposicion(robost_superpuestos);

        //Bolsa de robots
        ConfiguracionDeRobotsUnica cdr = new ConfiguracionDeRobotsUnica();
        cdr.setFlores(robots_flores);
        cdr.setPapeles(robost_papeles);

        //Guardamos la configuracion
        cdc.getConfiguracionDeRobots().add(cdr);

        //Flores, primero contamos
        Map<String, Integer> lasflores = new TreeMap<String, Integer>();
        if (!flores.isEmpty()) {
            for (String flor : flores.split("-")) {
                flor = flor.substring(1, flor.length() - 1);
                
                Integer cantidad = 1;
                if (lasflores.containsKey(flor)) {
                    cantidad = lasflores.get(flor) + 1;
                }
                lasflores.put(flor, cantidad);                
            }
            //Flores, segundo guardamos
            for (String flor : lasflores.keySet()) {
                ConfiguracionDeFloresAbsoluta cdfa = new ConfiguracionDeFloresAbsoluta();
                cdfa.setAvenida(Integer.valueOf(flor.split(",")[0]));
                cdfa.setCalle(Integer.valueOf(flor.split(",")[1]));
                cdfa.setCantidad(lasflores.get(flor));
                
                cdc.getConfiguracionDeFlores().add(cdfa);
            }
        }

        //Flores aleatorias
        ConfiguracionDeFloresAleatoria cdfa = new ConfiguracionDeFloresAleatoria();
        cdfa.setCantidad(flores_aleatorios);
        
        cdc.getConfiguracionDeFlores().add(cdfa);

        //Papeles, primero contamos
        Map<String, Integer> lospapeles = new TreeMap<String, Integer>();
        
        if (!papeles.isEmpty()) {
            for (String papel : papeles.split("-")) {
                papel = papel.substring(1, papel.length() - 1);
                
                Integer cantidad = 1;
                if (lospapeles.containsKey(papel)) {
                    cantidad = lospapeles.get(papel) + 1;
                }
                lospapeles.put(papel, cantidad);                
            }
            //Papeles, segundo guardamos
            for (String papel : lospapeles.keySet()) {
                ConfiguracionDePapelesAbsoluta cdpa = new ConfiguracionDePapelesAbsoluta();
                cdpa.setAvenida(Integer.valueOf(papel.split(",")[0]));
                cdpa.setCalle(Integer.valueOf(papel.split(",")[1]));
                cdpa.setCantidad(lospapeles.get(papel));
                
                cdc.getConfiguracionDePapeles().add(cdpa);
            }
        }
        //Papeles aleatorios
        ConfiguracionDePapelesAleatoria cdpa = new ConfiguracionDePapelesAleatoria();
        cdpa.setCantidad(papeles_aleatorios);
        cdc.getConfiguracionDePapeles().add(cdpa);

        //Obstaculos
        if (!obstaculos.isEmpty()) {
            for (String obstaculo : obstaculos.split("-")) {
                obstaculo = obstaculo.substring(1, obstaculo.length() - 1);
                
                ConfiguracionDeObstaculosAbsoluta cdoa = new ConfiguracionDeObstaculosAbsoluta();
                cdoa.setAvenida(Integer.valueOf(obstaculo.split(",")[0]));
                cdoa.setCalle(Integer.valueOf(obstaculo.split(",")[1]));
                
                cdc.getConfiguracionDeObstaculos().add(cdoa);
            }
        }
        return cdc;
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
        
        ConfiguracionDeCiudad cdc = (ConfiguracionDeCiudad) configuracion;
        ConfiguracionDeRobotsUnica cdr = (ConfiguracionDeRobotsUnica) cdc.getConfiguracionDeRobots().get(0);

        //Robots
        propiedades.setProperty(HABILITAR_ROBOTFISICO, Boolean.toString(cdc.isHabilitarRobotFisico()));
        propiedades.setProperty(IP_ROBOTFISICO, cdc.getIpRobotFisico());
        propiedades.setProperty(PUERTO_ROBOTFISICO, String.valueOf(cdc.getPuertoRobotFisico()));
        
        propiedades.setProperty(ROBOTS_SUPERPUESTOS, Boolean.toString(cdc.isPermitirSuperposicion()));
        
        if (cdr.getFlores() != null) {
            propiedades.setProperty(ROBOTS_FLORES, cdr.getFlores().toString());
        } else {
            propiedades.setProperty(ROBOTS_FLORES, "0");
        }
        if (cdr.getPapeles() != null) {
            propiedades.setProperty(ROBOTS_PAPELES, cdr.getPapeles().toString());
        } else {
            propiedades.setProperty(ROBOTS_PAPELES, "0");
        }

        //Flores
        String flores = "";
        String flores_aleatorias = "0";
        for (ConfiguracionDeFlores cdf : cdc.getConfiguracionDeFlores()) {
            if (cdf instanceof ConfiguracionDeFloresAbsoluta) {
                ConfiguracionDeFloresAbsoluta cdfa = (ConfiguracionDeFloresAbsoluta) cdf;
                for (Integer i = 0; i < cdfa.getCantidad(); i++) {
                    flores = flores + (flores.length() == 0 ? "" : "-") + "(" + cdfa.getAvenida() + "," + cdfa.getCalle() + ")";
                }
            } else {
                flores_aleatorias = ((ConfiguracionDeFloresAleatoria) cdf).getCantidad().toString();
            }            
        }
        propiedades.setProperty(FLORES, flores);
        propiedades.setProperty(FLORES_ALEATORIO, flores_aleatorias);

        //Papeles
        String papeles = "";
        String papeles_aleatorios = "0";
        for (ConfiguracionDePapeles cdp : cdc.getConfiguracionDePapeles()) {
            if (cdp instanceof ConfiguracionDePapelesAbsoluta) {
                ConfiguracionDePapelesAbsoluta cdpa = (ConfiguracionDePapelesAbsoluta) cdp;
                for (Integer i = 0; i < cdpa.getCantidad(); i++) {
                    papeles = papeles + (papeles.length() == 0 ? "" : "-") + "(" + cdpa.getAvenida() + "," + cdpa.getCalle() + ")";
                }
            } else {
                papeles_aleatorios = ((ConfiguracionDePapelesAleatoria) cdp).getCantidad().toString();
            }            
        }
        propiedades.setProperty(PAPELES, papeles);
        propiedades.setProperty(PAPELES_ALEATORIO, papeles_aleatorios);

        //Obstaculos
        String obstaculos = "";
        for (ConfiguracionDeObstaculos cdo : cdc.getConfiguracionDeObstaculos()) {
            ConfiguracionDeObstaculosAbsoluta cdoa = (ConfiguracionDeObstaculosAbsoluta) cdo;
            obstaculos = obstaculos + (obstaculos.length() == 0 ? "" : "-") + "(" + cdoa.getAvenida() + "," + cdoa.getCalle() + ")";
        }
        propiedades.setProperty(OBSTACULOS, obstaculos);

        //
        FileOutputStream fos = new FileOutputStream(archivo);
        propiedades.store(fos, "");
        fos.close();
    }
}
