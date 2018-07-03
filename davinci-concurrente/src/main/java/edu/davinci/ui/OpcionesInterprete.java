/**
 * *****************************************************************************
 * Copyright (c) 2011, 2013 - Daniel, Aguil Mallea. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the GNU Public License v3.0 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Daniel, Aguil Mallea - initial API and implementation
 * ****************************************************************************
 */
package edu.davinci.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.ciudad.CiudadBaseImpl;
import edu.davinci.planificador.Planificador;
import edu.davinci.planificador.PlanificadorEsencialImpl;
import edu.davinci.planificador.PlanificadorRoundRobinImpl;

public class OpcionesInterprete {

    /**
     * PROPIEDADES
     */
    private static String directorioTrabajo = System.getProperty("user.dir");
    private static String separador = System.getProperty("file.separator");
    private static String archivoConfiguracion = directorioTrabajo + separador
            + "configuracion.properties";

    private static final String PLANIFICADOR = "planificador";
    private static final String DEFAULT_PLANIFICADOR = "edu.davinci.planificador.PlanificadorAleatorioImpl";
    private static String planificador = "edu.davinci.planificador.PlanificadorAleatorioImpl";

    private static final String CIUDAD = "ciudad";
    private static final String DEFAULT_CIUDAD = "edu.davinci.ui.ciudad.CiudadSimpleGuiImpl";
    private static String ciudad = "edu.davinci.ui.ciudad.CiudadSimpleGuiImpl";

    private static final String FLORES = "flores";
    private static final String DEFAULT_FLORES = "";
    private static String flores = "";

    private static final String PAPELES = "papeles";
    private static final String DEFAULT_PAPELES = "";
    private static String papeles = "";

    private static final String OBSTACULOS = "obstaculos";
    private static final String DEFAULT_OBSTACULOS = "";
    private static String obstaculos = "";

    private static final String BOLSAFLORES = "flores.bolsa";
    private static final int DEFAULT_BOLSAFLORES = 0;
    private static String bolsaFlores = "0";

    private static final String FLORESALEATORIO = "flores.aleatorio";
    private static final int DEFAULT_FLORESALEATORIO = 10;
    private static String floresAleatorio = "10";

    private static final String PAPELESALEATORIO = "papeles.aleatorio";
    private static final int DEFAULT_PAPELESALEATORIO = 10;
    private static String papelesAleatorio = "10";

    private static final String BOLSAPAPELES = "papeles.bolsa";
    private static final int DEFAULT_BOLSAPAPELES = 0;
    private static String bolsaPapeles = "0";

    private static final String ROBOTSUPERPUESTO = "robot.superpuesto";
    private static final boolean DEFAULT_ROBOTSUPERPUESTO = true;
    private static String robotSuperpuesto = "true";

    private static final String QUANTUM = "planificador.quantum";
    private static final int DEFAULT_QUANTUM = 20;
    private static String quantum = "20";

    private static final String RETARDO = "ejecutor.retardo";
    private static final int DEFAULT_RETARDO = 30;
    private static String retardo = "30";

    private static final String DEPURAR_AST = "depurar.ast";
    private static final boolean DEFAULT_DEPURAR_AST = false;
    private static String depurarAst = "false";

    private static final String DEPURAR_LEXER = "depurar.lexer";
    private static final boolean DEFAULT_DEPURAR_LEXER = false;
    private static String depurarLexer = "false";

    private static final String CANTIDADCARACTERES = "editor.cantidad.caracteres";
    private static final int DEFAULT_CANTIDADCARACTERES = 10000;
    private static String cantidadCaracteres = "10000";

    private static final String ANCHOCIUDAD = "ciudad.ancho";
    private static final int DEFAULT_ANCHOCIUDAD = 600;
    private static String anchoCiudad = "600";

    private static final String HABILITAR_ROBOTFISICO = "habilitar.robotfisico";
    private static final boolean DEFAULT_HABILITAR_ROBOTFISICO = false;
    private static String habilitarRobotFisico = "false";

    private static final String IP_ROBOTFISICO = "ip.robotfisico";
    private static final String DEFAULT_IP_ROBOTFISICO = "192.168.0.107";
    private static String ipRobotFisico = "192.168.0.107";

    private static final String PUERTO_ROBOTFISICO = "puerto.robotfisico";
    private static final int DEFAULT_PUERTO_ROBOTFISICO = 2048;
    private static String puertoRobotFisico = "2048";

    /**
     * Retorna si pudo cargar las configuraciones Se debe evaluar si el archivo
     * de configuracion existe
     */
    public static boolean cargarConfiguracion() {
        // marca utilizada para retorna el exito de la carga de configuraciones
        boolean exito = false;

        Properties propiedades = new Properties();
        try {

            propiedades.load(new FileInputStream(archivoConfiguracion));

            papeles = setPropiedad(propiedades, DEFAULT_PAPELES, PAPELES);
            flores = setPropiedad(propiedades, DEFAULT_FLORES, FLORES);
            obstaculos = setPropiedad(propiedades, DEFAULT_OBSTACULOS, OBSTACULOS);
            bolsaFlores = setPropiedad(propiedades, DEFAULT_BOLSAFLORES, BOLSAFLORES);
            bolsaPapeles = setPropiedad(propiedades, DEFAULT_BOLSAPAPELES, BOLSAPAPELES);
            floresAleatorio = setPropiedad(propiedades, DEFAULT_FLORESALEATORIO, FLORESALEATORIO);
            papelesAleatorio = setPropiedad(propiedades, DEFAULT_PAPELESALEATORIO, PAPELESALEATORIO);
            planificador = setPropiedad(propiedades, DEFAULT_PLANIFICADOR, PLANIFICADOR);
            ciudad = setPropiedad(propiedades, DEFAULT_CIUDAD, CIUDAD);
            anchoCiudad = setPropiedad(propiedades, DEFAULT_ANCHOCIUDAD, ANCHOCIUDAD);
            robotSuperpuesto = setPropiedad(propiedades, DEFAULT_ROBOTSUPERPUESTO, ROBOTSUPERPUESTO);
            quantum = setPropiedad(propiedades, DEFAULT_QUANTUM, QUANTUM);
            retardo = setPropiedad(propiedades, DEFAULT_RETARDO, RETARDO);
            depurarAst = setPropiedad(propiedades, DEFAULT_DEPURAR_AST, DEPURAR_AST);
            depurarLexer = setPropiedad(propiedades, DEFAULT_DEPURAR_LEXER, DEPURAR_LEXER);
            cantidadCaracteres = setPropiedad(propiedades, DEFAULT_CANTIDADCARACTERES,
                    CANTIDADCARACTERES);

            habilitarRobotFisico = setPropiedad(propiedades, DEFAULT_HABILITAR_ROBOTFISICO, HABILITAR_ROBOTFISICO);
            ipRobotFisico = setPropiedad(propiedades, DEFAULT_IP_ROBOTFISICO, IP_ROBOTFISICO);
            puertoRobotFisico = setPropiedad(propiedades, DEFAULT_PUERTO_ROBOTFISICO, PUERTO_ROBOTFISICO);

            exito = true;
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo de configuracion.");
        } catch (IOException e) {
            System.out.println("Se produjo un error con el archivo de configuracion");
        }

        return exito;
    }

    /**
     * Genera el archivo de configuracion con los valores por defecto
     */
    public static boolean generarConfiguracionPorDefecto() {
        boolean generadas = false;

        // cargamos el flujo de entrada
        InputStream entrada = OpcionesInterprete.class
                .getResourceAsStream("/configuracion.properties");

        // verificamos si se pudo cargar
        if (entrada == null) {
            System.out.println("No se encontro el archivo de configuracion.");
        } else {
            OutputStream salida = null;
            int readBytes;
            byte[] buffer = new byte[4096];
            // copiamos el contenido en el archivo de salida.
            try {
                salida = new FileOutputStream(archivoConfiguracion);
                while ((readBytes = entrada.read(buffer)) > 0) {
                    salida.write(buffer, 0, readBytes);
                }
                //establecemos que se generaron correctamente
                generadas = true;

            } catch (IOException e1) {
                System.out.println("Se produjo un error con el archivo de configuracion");
            } finally {
                if (salida != null) {
                    try {
                        salida.close();
                    } catch (IOException e) {
                    }
                }
            }

        }

        //retornamos el exito de la generacion
        return generadas;
    }

    /**
     * Retorna el contenido del archivo de configuracion Se debe utilizar en
     * conjunto con el metodo hayConfiguracion()
     *
     * @return String
     */
    public static String getConfiguracion() {

        StringBuilder sb = new StringBuilder();

        try {

            // Cargo el archivo de configuracion
            Scanner scanner = new Scanner(new FileInputStream(archivoConfiguracion));
            try {
                // recorro por linea
                while (scanner.hasNextLine()) {
                    sb.append(scanner.nextLine()).append("\n");
                }
            } finally {

                // cierro el archivo
                scanner.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo de configuración no exite.");
        }

        return sb.toString();

    }

    public static String getArchivoConfiguracion() {
        return archivoConfiguracion;
    }

    /**
     * retorna si existe el archivo de configuracion
     *
     * @return
     */
    public static boolean hayConfiguracion() {
        File configuracion = new File(archivoConfiguracion);
        return configuracion.exists();
    }

    /**
     *
     * @param propiedad
     * @param valorPorDefecto
     * @param clave
     * @return
     */
    private static String setPropiedad(Properties propiedad, String valorPorDefecto, String clave) {

        return propiedad.getProperty(clave, valorPorDefecto);

    }

    /**
     *
     * @param propiedad
     * @param valorPorDefecto
     * @param clave
     * @return
     */
    private static String setPropiedad(Properties propiedad, int valorPorDefecto, String clave) {

        return propiedad.getProperty(clave, String.valueOf(valorPorDefecto));

    }

    /**
     *
     * @param propiedad
     * @param valorPorDefecto
     * @param clave
     * @return
     */
    private static String setPropiedad(Properties propiedad, boolean valorPorDefecto, String clave) {

        return propiedad.getProperty(clave, String.valueOf(valorPorDefecto));

    }

    /**
     *
     * @param text
     */
    public static boolean guardarConfiguracion(String texto) {

        boolean exito = false;

        FileWriter archivo;
        try {
            archivo = new FileWriter(archivoConfiguracion);

            archivo.write(texto);

            archivo.close();

            exito = true;

        } catch (IOException e) {

            System.out.println("No se pudo guardar la configuración. " + e.getMessage());
        }

        return exito;
    }

    private static boolean valor_seguro(String logico, boolean porDefecto, String regla) {
        boolean valor = porDefecto;

        try {
            valor = Boolean.parseBoolean(logico);
        } catch (Exception e) {
            System.out.println(String.format(
                    "el valor de %s no es válido. Usando valor por defecto", regla));
        }

        return valor;
    }

    private static int valor_seguro(String numero, int porDefecto, String regla) {
        int valor = porDefecto;

        try {
            valor = Integer.parseInt(numero);
        } catch (Exception e) {
            System.out.println(String.format(
                    "el valor de %s no es válido. Usando valor por defecto", regla));
        }

        return valor;
    }

    /**
     * retorna el directorio temporal
     *
     * @return
     */
    public static String getDirectorioTrabajo() {
        return directorioTrabajo;
    }

    public static Planificador getInstanciaPlanificador() {

        Planificador planificador_ = null;

        try {
            planificador_ = (Planificador) Class.forName(OpcionesInterprete.getPlanificador())
                    .newInstance();
            if (planificador_ instanceof PlanificadorRoundRobinImpl) {
                ((PlanificadorRoundRobinImpl) planificador_).setQuantum(getQuantum());
            }
        } catch (Exception e) {
            System.out.println("no se encuentra la definición del planificador.");
        } finally {
            if (planificador_ == null) {
                planificador_ = new PlanificadorEsencialImpl();
            }
        }

        return planificador_;
    }

    /**
     *
     */
    public static String getPlanificador() {
        try {
            Object instancia = Class.forName(planificador).newInstance();
            if (instancia instanceof Planificador) {
                return planificador;
            }
        } catch (Exception e) {
            System.out
                    .println(String
                            .format("no se puede utilizar el planificador %s, se utilizara el valor por defecto %s",
                                    planificador, DEFAULT_PLANIFICADOR));
        }
        return DEFAULT_PLANIFICADOR;
    }

    public static String getFlores() {
        return flores;
    }

    public static String getPapeles() {
        return papeles;
    }

    public static String getObstaculos() {
        return obstaculos;
    }

    public static int getBolsaFlores() {
        return valor_seguro(bolsaFlores, DEFAULT_BOLSAFLORES, BOLSAFLORES);
    }

    public static int getBolsaPapeles() {
        return valor_seguro(bolsaPapeles, DEFAULT_BOLSAPAPELES, BOLSAPAPELES);
    }

    public static boolean getRobotSuperpuesto() {
        return valor_seguro(robotSuperpuesto, DEFAULT_ROBOTSUPERPUESTO, ROBOTSUPERPUESTO);
    }

    public static int getQuantum() {
        return valor_seguro(quantum, DEFAULT_QUANTUM, QUANTUM);
    }

    public static int getFloresAleatorio() {
        return valor_seguro(floresAleatorio, DEFAULT_FLORESALEATORIO, FLORESALEATORIO);
    }

    public static int getPapelesAleatorio() {
        return valor_seguro(papelesAleatorio, DEFAULT_PAPELESALEATORIO, PAPELESALEATORIO);
    }

    public static int getRetardo() {
        // return valor_seguro(retardo, DEFAULT_RETARDO, RETARDO);

        int ret = valor_seguro(retardo, DEFAULT_RETARDO, RETARDO);
        return (ret > 0 ? ret : 1);

    }

    public static boolean getDepurarAst() {
        return valor_seguro(depurarAst, DEFAULT_DEPURAR_AST, DEPURAR_AST);
    }

    public static boolean getDepurarLexer() {

        return valor_seguro(depurarLexer, DEFAULT_DEPURAR_LEXER, DEPURAR_LEXER);
    }

    public static int getCantidadCaracteres() {
        return valor_seguro(cantidadCaracteres, DEFAULT_CANTIDADCARACTERES, CANTIDADCARACTERES);
    }

    public static int getAnchoCiudad() {
        return valor_seguro(anchoCiudad, DEFAULT_ANCHOCIUDAD, ANCHOCIUDAD);
    }

    public static boolean getHabilitarRobotFisico() {
        return valor_seguro(habilitarRobotFisico, DEFAULT_HABILITAR_ROBOTFISICO, HABILITAR_ROBOTFISICO);
    }

    public static String getIpRobotFisico() {
        return ipRobotFisico;
    }

    public static int getPuertoRobotFisico() {
        return valor_seguro(puertoRobotFisico, DEFAULT_PUERTO_ROBOTFISICO, PUERTO_ROBOTFISICO);
    }

    // ***************************************************************************//
    public static void setFlores(String flores) {
        OpcionesInterprete.flores = flores;
    }

    public static void setPapeles(String papeles) {
        OpcionesInterprete.papeles = papeles;
    }

    public static void setObstaculos(String obstaculos) {
        OpcionesInterprete.obstaculos = obstaculos;
    }

    public static void setBolsaFlores(String bolsaFlores) {
        OpcionesInterprete.bolsaFlores = bolsaFlores;
    }

    public static void setBolsaPapeles(String bolsaPapeles) {
        OpcionesInterprete.bolsaPapeles = bolsaPapeles;
    }

    public static void setDirectorioTrabajo(String directorioTrabajo) {
        OpcionesInterprete.directorioTrabajo = directorioTrabajo;
    }

    public static void setPlanificador(String planificador) {
        OpcionesInterprete.planificador = planificador;
    }

    public static void setRobotSuperpuesto(String robotSuperpuesto) {
        OpcionesInterprete.robotSuperpuesto = robotSuperpuesto;
    }

    public static void setQuantum(String quantum) {
        OpcionesInterprete.quantum = quantum;
    }

    public static void setFloresAleatorio(String floresAleatorio) {
        OpcionesInterprete.floresAleatorio = floresAleatorio;
    }

    public static void setPapelesAleatorio(String papelesAleatorio) {
        OpcionesInterprete.papelesAleatorio = papelesAleatorio;
    }

    public static void setRetardo(String retardo) {
        OpcionesInterprete.retardo = retardo;
    }

    public static void setDepurarAst(String depurarAst) {
        OpcionesInterprete.depurarAst = depurarAst;
    }

    public static void setDepurarLexer(String depurarLexer) {
        OpcionesInterprete.depurarLexer = depurarLexer;
    }

    public static void setCantidadCaracteres(String cantidadLineas) {
        OpcionesInterprete.cantidadCaracteres = cantidadLineas;
    }

    public static void setHabilitarRobotFisico(String habilitarRobotFisico) {
        OpcionesInterprete.habilitarRobotFisico = habilitarRobotFisico;
    }

    public static void setIpRobotFisico(String ipRobotFisico) {
        OpcionesInterprete.ipRobotFisico = ipRobotFisico;
    }

    public static void setPuertoRobotFisico(String puertoRobotFisico) {
        OpcionesInterprete.puertoRobotFisico = puertoRobotFisico;
    }

    public static Ciudad getInstanciaCiudad() {

        Ciudad ciudad_ = null;

        try {
            ciudad_ = (Ciudad) Class.forName(OpcionesInterprete.getCiudad()).newInstance();
        } catch (Exception e) {
            System.out.println("no se encuentra la definición de la ciudad.");
        } finally {
            if (ciudad_ == null) {
                ciudad_ = new CiudadBaseImpl();
            }
        }

        return ciudad_;
    }

    /**
     *
     */
    public static String getCiudad() {
        try {
            Object instancia = Class.forName(ciudad).newInstance();
            if (instancia instanceof Ciudad) {
                return ciudad;
            }
        } catch (Exception e) {
            System.out.println(String.format(
                    "no se puede utilizar la ciudad %s, se utilizara el valor por defecto %s",
                    ciudad, DEFAULT_CIUDAD));
        }
        return DEFAULT_CIUDAD;
    }

    public static void setAnchoCiudad(String anchoCiudad) {
        OpcionesInterprete.anchoCiudad = anchoCiudad;
    }
}
