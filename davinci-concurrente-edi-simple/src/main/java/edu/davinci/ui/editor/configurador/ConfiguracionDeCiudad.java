package edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.util.List;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author german
 */
public class ConfiguracionDeCiudad extends Configuracion {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(ConfiguracionDeCiudad.class.getName());

    /**
     * ***********
     * ATRIBUTOS * ***********
     */
    /**
     *
     */
    private Integer avenidas;

    /**
     *
     */
    private Integer calles;

    /**
     *
     */
    private boolean permitirSuperposicion;

    /**
     *
     */
    private List<String> visores;

    /**
     *
     */
    private List<ConfiguracionDeFlores> configuracionDeFlores;

    /**
     *
     */
    private List<ConfiguracionDePapeles> configuracionDePapeles;

    /**
     *
     */
    private List<ConfiguracionDeObstaculos> configuracionDeObstaculos;

    /**
     *
     */
    private List<ConfiguracionDeRobots> configuracionDeRobots;

    private boolean habilitarRobotFisico;

    private String ipRobotFisico;

    private Integer puertoRobotFisico;

    /**
     * ***************
     * CONSTRUCTORES * ***************
     */
    /**
     *
     */
    public ConfiguracionDeCiudad() {
        //
        super();

        //DEBUG
        auditor.finest("Constructor1");

        //
        avenidas = 10;
        calles = 10;
        permitirSuperposicion = true;

        //
        visores = new LinkedList<String>();
        configuracionDeFlores = new LinkedList<ConfiguracionDeFlores>();
        configuracionDePapeles = new LinkedList<ConfiguracionDePapeles>();
        configuracionDeObstaculos = new LinkedList<ConfiguracionDeObstaculos>();
        configuracionDeRobots = new LinkedList<ConfiguracionDeRobots>();

        //
        habilitarRobotFisico = false;
        ipRobotFisico = "";
        puertoRobotFisico = 0;

    }

    /**
     *
     * @param a
     * @param c
     */
    public ConfiguracionDeCiudad(Integer a, Integer c) {
        //
        this();

        //DEBUG
        auditor.finest(String.format("Constructor2: avenidas %d calles %d", a, c));

        //
        avenidas = a;
        calles = c;
    }

    /**
     *
     * @param a
     * @param c
     * @param v
     * @param cdf
     * @param cdp
     * @param cdo
     * @param cdr
     */
    public ConfiguracionDeCiudad(Integer a, Integer c, Boolean p, List<String> v, List<ConfiguracionDeFlores> cdf, List<ConfiguracionDePapeles> cdp, List<ConfiguracionDeObstaculos> cdo, List<ConfiguracionDeRobots> cdr, Boolean h, String ip, Integer pu) {

        //DEBUG
        auditor.finest(String.format("Constructor3: avenidas %d calles %d", a, c));

        //
        avenidas = a;
        calles = c;
        permitirSuperposicion = p;

        //
        visores = v;
        configuracionDeFlores = cdf;
        configuracionDePapeles = cdp;
        configuracionDeObstaculos = cdo;
        configuracionDeRobots = cdr;

        habilitarRobotFisico = h;
        ipRobotFisico = ip;
        puertoRobotFisico = pu;
    }

    /**
     * *************
     * PROPIEDADES * *************
     */
    /**
     * @return the avenidas
     */
    public Integer getAvenidas() {
        //DEBUG
        auditor.finest("getAvenidas");

        //
        return avenidas;
    }

    /**
     * @param a the avenidas to set
     */
    public void setAvenidas(Integer a) {
        //DEBUG
        auditor.finest("setAvenidas");

        //
        Integer olda = avenidas;
        avenidas = a;
        getCambios().firePropertyChange("avenidas", olda, a);
    }

    /**
     * @return the calles
     */
    public Integer getCalles() {
        //DEBUG
        auditor.finest("getCalles");

        //
        return calles;
    }

    /**
     * @param c the calles to set
     */
    public void setCalles(Integer c) {
        //DEBUG
        auditor.finest("setCalles");

        //
        Integer oldc = calles;
        calles = c;
        getCambios().firePropertyChange("calles", oldc, c);
    }

    /**
     *
     * @return
     */
    public boolean isPermitirSuperposicion() {

        auditor.finest("getPermitirSuperposicion");

        return permitirSuperposicion;
    }

    /**
     *
     * @param permitir
     */
    public void setPermitirSuperposicion(boolean permitir) {

        auditor.log(Level.FINEST, "setPermitirSuperposicion {0}", permitir);

        boolean oldp = permitirSuperposicion;
        permitirSuperposicion = permitir;
        getCambios().firePropertyChange("permitirSuperposicion", oldp, permitir);

    }

    /**
     *
     * @return
     */
    public List<String> getVisores() {
        //DEBUG
        auditor.finest("getVisores");

        //
        return visores;
    }

    /**
     *
     * @param v
     */
    public void setVisores(List<String> v) {
        //DEBUG
        auditor.finest("setVisores");

        //
        List<String> oldv = visores;
        visores = v;
        getCambios().firePropertyChange("visores", oldv, v);
    }

    /**
     * @return the configuracionDeFlores
     */
    public List<ConfiguracionDeFlores> getConfiguracionDeFlores() {
        //DEBUG
        auditor.finest("getConfiguracionDeFlores");

        //
        return configuracionDeFlores;
    }

    /**
     *
     * @param cdf
     */
    public void setConfiguracionDeFlores(List<ConfiguracionDeFlores> cdf) {
        //DEBUG
        auditor.finest("setConfiguracionDeFlores");

        //
        List<ConfiguracionDeFlores> oldcdf = configuracionDeFlores;
        configuracionDeFlores = cdf;
        getCambios().firePropertyChange("configuracionDeFlores", oldcdf, cdf);
    }

    /**
     * @return the configuracionDePapeles
     */
    public List<ConfiguracionDePapeles> getConfiguracionDePapeles() {
        //DEBUG
        auditor.finest("getConfiguracionDePapeles");

        //
        return configuracionDePapeles;
    }

    /**
     *
     * @param cdp
     */
    public void setConfiguracionDePapeles(List<ConfiguracionDePapeles> cdp) {
        //DEBUG
        auditor.finest("setConfiguracionDePapeles");

        //
        List<ConfiguracionDePapeles> oldcdp = configuracionDePapeles;
        configuracionDePapeles = cdp;
        getCambios().firePropertyChange("configuracionDePapeles", oldcdp, cdp);
    }

    /**
     * @return the configuracionDeObstaculos
     */
    public List<ConfiguracionDeObstaculos> getConfiguracionDeObstaculos() {
        //DEBUG
        auditor.finest("getConfiguracionDeObstaculos");

        //
        return configuracionDeObstaculos;
    }

    /**
     *
     * @param cdo
     */
    public void setConfiguracionDeObstaculos(List<ConfiguracionDeObstaculos> cdo) {
        //DEBUG
        auditor.finest("setConfiguracionDeObstaculos");

        //
        List<ConfiguracionDeObstaculos> oldcdo = configuracionDeObstaculos;
        configuracionDeObstaculos = cdo;
        getCambios().firePropertyChange("configuracionDeObstaculos", oldcdo, cdo);
    }

    /**
     * @return the configuracionDeRobots
     */
    public List<ConfiguracionDeRobots> getConfiguracionDeRobots() {
        //DEBUG
        auditor.finest("getConfiguracionDeRobots");

        //
        return configuracionDeRobots;
    }

    /**
     *
     * @param cdr
     */
    public void setConfiguracionDeRobots(List<ConfiguracionDeRobots> cdr) {
        //DEBUG
        auditor.finest("setConfiguracionDeRobots");

        //
        List<ConfiguracionDeRobots> oldcdr = configuracionDeRobots;
        configuracionDeRobots = cdr;
        getCambios().firePropertyChange("configuracionDeRobots", oldcdr, cdr);
    }

    public boolean isHabilitarRobotFisico() {
        return habilitarRobotFisico;
    }

    public void setHabilitarRobotFisico(boolean habilitar) {
        auditor.log(Level.FINEST, "setHabilitarRobotFisico {0}", habilitar);

        boolean oldp = habilitarRobotFisico;
        habilitarRobotFisico = habilitar;
        getCambios().firePropertyChange("habilitarRobotFisico", oldp, habilitar);
    }

    public String getIpRobotFisico() {
        return ipRobotFisico;
    }

    public void setIpRobotFisico(String ip) {

        String oldcdo = ipRobotFisico;
        ipRobotFisico = ip;
        getCambios().firePropertyChange("ipRobotFisico", oldcdo, ip);

    }

    public Integer getPuertoRobotFisico() {
        return puertoRobotFisico;
    }

    public void setPuertoRobotFisico(Integer puerto) {

        //
        Integer olda = puertoRobotFisico;
        puertoRobotFisico = puerto;
        getCambios().firePropertyChange("puertoRobotFisico", olda, puertoRobotFisico);
    }

}
