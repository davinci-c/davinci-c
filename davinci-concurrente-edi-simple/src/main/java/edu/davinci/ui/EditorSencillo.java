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

import edu.davinci.ciudad.Ciudad;
import edu.davinci.interprete.Ejecutor;
import edu.davinci.interprete.Interprete;
import edu.davinci.planificador.Planificador;
import edu.davinci.planificador.PlanificadorRepetitivoImpl;
import edu.davinci.planificador.Secuencia;
import edu.davinci.ui.editor.*;
import edu.davinci.ui.editor.autocompletado.AutoCompletar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;

public final class EditorSencillo extends JFrame {

    public static final boolean LOG_TO_FILE = false;
    private static final long serialVersionUID = 1L;
    private final PropertyChangeListener cambiosDepurador;
    private final PropertyChangeListener regitroLogicoDeSecuencia = new OyenteRegistroLogico();
    private final PropertyChangeListener estadoEjecucionEditor;
    private final Action newAction = new AccionNuevo(this);
    private final Action openAction = new AccionAbrir(this);
    private final Action saveAction = new AccionGuardar(this);
    private final Action saveLikeAction = new AccionGuardarComo(this);
    private final Action exitAction = new AccionSalir(this);
    private final Action configurationInterpreteAction = new AccionConfigurarInterprete(this);
    private final Action configurationCiudadAction = new AccionConfigurarCiudad(this);
    private final Action runAction = new AccionEjecutar(this);
    private final Action pauseAction = new AccionPausar(this);
    private final Action stopAction = new AccionParar(this);
    private final Action debugAction = new AccionDepurar(this);
    private final Action nextAction = new AccionDepurarSiguiente(this);
    private final Action runAgainAction = new AccionEjecutarNuevamente(this);
    private final Action runManualAgainAction = new AccionEjecutarNuevamenteManual(this);
    private final JTextComponent visorEntrada;
    private final JTextComponent visorSalida;
    private final JTextComponent visorDepurador;
    private JPanel ciudad;
    private Interprete interprete;
    private Ejecutor ejecutor;
    private final TextLineNumber tln;
    private final JSplitPane splitPane;
    private final JSplitPane splitFuente;
    private File selectedFile;

    /**
     * Constructor Editor - Construye un editor sencillo
     * @throws java.io.IOException
     */
    public EditorSencillo() throws IOException {
        super("EDIS - Entorno Desarrollo Integrado Simple - DaVinci Concurrente");

        cargarConfiguraciones();

        // Creamos el editor del código
        visorEntrada = crearVisorConRealceSintaxis(Color.WHITE, Color.BLACK);

        //agregamos la regla al editor 
        LinePainter linepainter = new LinePainter(visorEntrada);

        //agregamos el autocompletado
        AutoCompletar.habilitar(visorEntrada);

        //creamos el visor de salida del interprete
        visorSalida = crearVisorConRealceSintaxis(Color.BLACK, Color.GREEN);
        
        //creamos el visor de depuración del interprete
        visorDepurador = crearVisorConRealceSintaxis(Color.WHITE, Color.BLUE);
        
        //creamos el espacio que contendrá la ciiudad
        ciudad = new JPanel();
        
        //dejamos editable el depurador (a efectos de poder compiar el texto)
        visorDepurador.setEditable(false);

        /**
         * EDITOR FUENTE *
         */
        JScrollPane paneFuente = new JScrollPane(visorEntrada);
        paneFuente.setPreferredSize(new Dimension(600, 500));
        paneFuente.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        /**
         * EDITOR FUENTE - NUMEROS DE LINEA *
         */
        tln = new TextLineNumber(visorEntrada);
        paneFuente.setRowHeaderView(tln);

        /**
         * DIVISOR EDITOR FUENTE y CIUDAD *
         */
        splitFuente = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, paneFuente, ciudad);
        splitFuente.setOneTouchExpandable(true);
        splitFuente.setDividerLocation(paneFuente.getMaximumSize().width);

        /**
         * EDITOR DEBUG *
         */
        JScrollPane paneDebug = new JScrollPane(visorDepurador);
        paneDebug.setPreferredSize(new Dimension(200, 300));
        paneDebug.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        paneDebug.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        /**
         * EDITOR SALIDA *
         */
        JScrollPane paneSalida = new JScrollPane(visorSalida);
        paneSalida.setPreferredSize(new Dimension(800, 300));
        paneSalida.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        /**
         * DIVISOR SALIDA y DEBUG *
         */
        JSplitPane splitDebug = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, paneSalida, paneDebug);
        splitDebug.setOneTouchExpandable(true);
        splitDebug.setDividerLocation(800);

        /**
         * DIVISOR (DIVISOR ENTRADA - CIUDAD) y (DIVISOR SALIDA - DEBUG) *
         */
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitFuente, splitDebug);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(450);

        add(splitPane, BorderLayout.CENTER);
        add(createToolBar(), BorderLayout.NORTH);
        setJMenuBar(createMenuBar());

        setSize(1150, 750);

        // redirijimos la salida standar y error
        PrintStream printStreamError = new PrintStream(new FilteredOutStream(
                new ByteArrayOutputStream(), (JTextArea) visorSalida));

        System.setOut(printStreamError);
        System.setErr(printStreamError);

        // iniciamos el depurador
        cambiosDepurador = new RegistroCambioDepurador(visorEntrada, visorDepurador);

        // cambios de estado del editor
        estadoEjecucionEditor = new OyenteEstadoEjecucion(this);

    }

    /**
     * Crea el editor con realce de sintaxis
     *
     * @param background
     * @param foreground
     * @return
     */
    private JTextComponent crearVisorConRealceSintaxis(Color background, Color foreground) {

        RSyntaxTextArea ta = new RSyntaxTextArea();
        ta.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DAVINCI_CONCURRENTE);

        RSyntaxTextArea.setTemplatesEnabled(true);

        ta.setCodeFoldingEnabled(true);
        ta.setAntiAliasingEnabled(true);
        ta.setAutoIndentEnabled(true);

        // JTextArea ta = new JTextArea();
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setTabSize(2);
        ta.setForeground(foreground);
        ta.setBackground(background);

        try {

            InputStream xml = getClass().getResourceAsStream("/edu/davinci/ui/temas/davinci-dark.xml");
            Theme tema = Theme.load(xml);
            tema.apply(ta);
        } catch (IOException e) {
            System.out.println("No se pudo cargar el tema. " + e.getMessage());// ioe.printStackTrace();
        }

        return ta;
    }

    /**
     * Retorna la imagen del recurso pedido
     *
     * @param recurso
     * @return
     */
    private ImageIcon getImage(String recurso) {
        return new ImageIcon(getResource(recurso));
    }

    /**
     * Retorna la imagen del recurso pedido
     *
     * @param recurso
     * @return
     */
    private URL getResource(String recurso) {
        return EditorSencillo.class.getResource(recurso);
    }

    /**
     * Crea la barra de herramientas
     *
     * @return
     */
    private JToolBar createToolBar() {
        JToolBar bar = new JToolBar();

        Action a;

        bar.add(newAction).setToolTipText("Nuevo");
        bar.add(openAction).setToolTipText("Abrir");
        bar.add(saveAction).setToolTipText("Guardar");
        bar.add(saveLikeAction).setToolTipText("Guardar como...");
        bar.addSeparator();

        a = visorEntrada.getActionMap().get(DefaultEditorKit.cutAction);
        a.putValue(Action.SMALL_ICON, getImage("images/ide/edit-cut.png"));
        a.putValue(Action.NAME, "Cortar");
        //agregamos la tecla aceleradora
        a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        bar.add(a).setToolTipText("Cortar");

        a = visorEntrada.getActionMap().get(DefaultEditorKit.copyAction);
        a.putValue(Action.SMALL_ICON, getImage("images/ide/edit-copy.png"));
        a.putValue(Action.NAME, "Copiar");
        //agregamos la tecla aceleradora
        a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));

        bar.add(a).setToolTipText("Copiar");

        a = visorEntrada.getActionMap().get(DefaultEditorKit.pasteAction);
        a.putValue(Action.SMALL_ICON, getImage("images/ide/edit-paste.png"));
        a.putValue(Action.NAME, "Pegar");
        //agregamos la tecla aceleradora
        a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        bar.add(a).setToolTipText("Pegar");

        a = visorEntrada.getActionMap().get(DefaultEditorKit.selectAllAction);
        a.putValue(Action.SMALL_ICON, getImage("images/ide/edit-select-all.png"));
        a.putValue(Action.NAME, "Seleccionar todo");
        //agregamos la tecla aceleradora
        a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));

        bar.addSeparator();

        bar.add(runAction).setToolTipText("Ejecutar / Continuar");
        bar.add(pauseAction).setToolTipText("Pausar");
        bar.add(stopAction).setToolTipText("Parar");
        bar.addSeparator();

        bar.add(debugAction).setToolTipText("Depurador");
        bar.add(nextAction).setToolTipText("Siguiente");
        bar.addSeparator();

        //bar.add(runAgainAction).setToolTipText("Re-Ejecutar");
        //bar.add(runManualAgainAction).setToolTipText("Re-Ejecutar-manual");
        return bar;
    }

    /**
     * Crea el menu
     *
     * @return
     */
    private JMenuBar createMenuBar() {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("Archivo");
        JMenu edit = new JMenu("Edición");
        JMenu compiler = new JMenu("Interprete");
        JMenu options = new JMenu("Opciones");

        menubar.add(file);
        menubar.add(edit);
        menubar.add(compiler);
        menubar.add(options);

        file.add(newAction);
        file.add(openAction);
        file.add(saveAction);
        file.add(saveLikeAction);
        file.addSeparator();
        file.add(exitAction);

        edit.add(visorEntrada.getActionMap().get(DefaultEditorKit.cutAction));
        edit.add(visorEntrada.getActionMap().get(DefaultEditorKit.copyAction));
        edit.add(visorEntrada.getActionMap().get(DefaultEditorKit.pasteAction));
        edit.add(visorEntrada.getActionMap().get(DefaultEditorKit.selectAllAction));

        compiler.add(runAction);
        compiler.add(pauseAction);
        compiler.add(stopAction);
        compiler.addSeparator();
        compiler.add(debugAction);
        compiler.add(nextAction);
        compiler.addSeparator();
        compiler.add(runAgainAction);
        compiler.add(runManualAgainAction);

        options.add(configurationInterpreteAction);
        options.add(configurationCiudadAction);

        return menubar;
    }

    public InputStream getTextoEntrada() throws UnsupportedEncodingException {

        return new ByteArrayInputStream(visorEntrada.getText().getBytes("ISO-8859-1"));

    }

    /**
     *
     * @param ciudadInicializada
     */
    public void insertarCiudad(Ciudad ciudadInicializada) {
        try {
            // insertamos la ciudad grafica
            if (ciudadInicializada.tieneRepresentacionGrafica()) {

                splitFuente.remove(ciudad);
                ciudad = ciudadInicializada.getRepresentacionGrafica();
                splitFuente.add(ciudad);
                splitFuente.setDividerLocation(getWidth() - OpcionesInterprete.getAnchoCiudad());

            } else {
                JSplitPane contenedor = (JSplitPane) ciudad.getParent();
                contenedor.remove(ciudad);
                contenedor.setDividerLocation(getWidth());
            }
        } catch (Exception e) {
            System.out.println("error al insertar la representacion gr�fica de la ciudad:"
                    + e.getMessage());
        }

    }

    /**
     * Retorna la ciudad con valores por defecto
     *
     * @return
     */
    public Ciudad getCiudadInicializada() {

        //cargamos las configuraciones de la ciudad y el interprete
        cargarConfiguraciones();

        // obtnemos la ciudad
        Ciudad nuevaCiudad = OpcionesInterprete.getInstanciaCiudad();

        // seteamos la cantidad de avenidas y calles
        nuevaCiudad.setCantidadAvenidasYCalles(10, 10);

        // establece las flores manuales
        estableceFloresEnCiudad(nuevaCiudad, OpcionesInterprete.getFlores());
        establecerPapelesEnCiudad(nuevaCiudad, OpcionesInterprete.getPapeles());

        // establece las flores aleatorio
        establecerFloresAleatoriosEnCiudad(nuevaCiudad, OpcionesInterprete.getFloresAleatorio());
        establecerPapelesAleatoriosEnCiudad(nuevaCiudad, OpcionesInterprete.getPapelesAleatorio());

        // establece los obstaculos
        establecerObstaculosEnCiudad(nuevaCiudad, OpcionesInterprete.getObstaculos());

        // establece la bolsa del robot
        nuevaCiudad.establecerBolsaFlores(OpcionesInterprete.getBolsaFlores());
        nuevaCiudad.establecerBolsaPapeles(OpcionesInterprete.getBolsaPapeles());

        // establece configuracoin del robot
        nuevaCiudad.permitirRobotsSuperpuestos(OpcionesInterprete.getRobotSuperpuesto());

        return nuevaCiudad;
    }

    /**
     *
     * @param ciudad
     * @param obstaculos
     */
    private void establecerObstaculosEnCiudad(Ciudad ciudad, String obstaculos) {
        if (!obstaculos.isEmpty()) {
            try {
                String[] elementos = obstaculos.split("-");
                for (String elemento : elementos) {
                    String elementoTabajado = elemento.replace("(", "").replace(")", "");
                    int avenida = Integer.parseInt(elementoTabajado.split(",")[0]);
                    int calle = Integer.parseInt(elementoTabajado.split(",")[1]);
                    ciudad.setObstaculo(avenida, calle);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error al cargar los obstaculos!!!!");
            }
        }
    }

    /**
     *
     * @param ciudad
     * @param papeles
     */
    private void establecerPapelesEnCiudad(Ciudad ciudad, String papeles) {
        if (!papeles.isEmpty()) {
            try {
                String[] elementos = papeles.split("-");
                for (String elemento : elementos) {
                    
                    String elementoTrabajado = elemento.replace("(", "").replace(")", "");
                    //obtenemos la avenida
                    int avenida = Integer.parseInt(elementoTrabajado.split(",")[0]);
                    //obtenemos la calle
                    int calle = Integer.parseInt(elementoTrabajado.split(",")[1]);
                    //verificamos si la avenida es aleatoria
                    if(avenida == 0){
                        avenida = new Random().nextInt(ciudad.getCantidadAvenidas()) + 1;
                    }
                    //verificamos si la calle es aleatoria
                    if(calle == 0){
                        calle = new Random().nextInt(ciudad.getCantidadCalles()) + 1;            
                    }
                    //incremetamos la cantidad de flores en la intersección
                    ciudad.setPapeles(avenida, calle, 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error al cargar los papeles!!!!");
            }
        }
    }

    /**
     *
     * @param ciudad
     * @param flores
     */
    private void estableceFloresEnCiudad(Ciudad ciudad, String flores) {
        if (!flores.isEmpty()) {
            try {
                String[] elementos = flores.split("-");
                for (String elemento : elementos) {
                    String elementoTrabajado = elemento.replace("(", "").replace(")", "");
                    //obtenemos la avenida
                    int avenida = Integer.parseInt(elementoTrabajado.split(",")[0]);
                    //obtenemos la calle
                    int calle = Integer.parseInt(elementoTrabajado.split(",")[1]);
                    //verificamos si la avenida es aleatoria
                    if(avenida == 0){
                        avenida = new Random().nextInt(ciudad.getCantidadAvenidas()) + 1;
                    }
                    //verificamos si la calle es aleatoria
                    if(calle == 0){
                        calle = new Random().nextInt(ciudad.getCantidadCalles()) + 1;            
                    }
                    //incremetamos la cantidad de flores en la intersección
                    ciudad.setFlores(avenida, calle, 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error al cargar las flores!!!!");
            }
        }

    }

    /**
     *
     * @param ciudad
     * @param papeles
     */
    private void establecerPapelesAleatoriosEnCiudad(Ciudad ciudad, int papeles) {

        Random r = new Random();

        for (int i = 0; i < papeles; i++) {

            int avenida = r.nextInt(ciudad.getCantidadAvenidas()) + 1;
            int calle = r.nextInt(ciudad.getCantidadCalles()) + 1;

            ciudad.incrementarPapeles(avenida, calle);

        }

    }

    /**
     *
     * @param ciudad
     * @param flores
     */
    private void establecerFloresAleatoriosEnCiudad(Ciudad ciudad, int flores) {

        Random r = new Random();

        for (int i = 0; i < flores; i++) {

            int avenida = r.nextInt(ciudad.getCantidadAvenidas()) + 1;
            int calle = r.nextInt(ciudad.getCantidadCalles()) + 1;

            ciudad.incrementarFlores(avenida, calle);

        }

    }

    /**
     * Retorna el planificador inicializado
     *
     * @param secuencia
     * @return
     */
    public Planificador getPlanificadorRepetitivo(List<Secuencia> secuencia) {

        Planificador pl = new PlanificadorRepetitivoImpl(secuencia);

        return pl;
    }

    /**
     *
     * @return
     */
    public JTextComponent getEntrada() {
        return visorEntrada;
    }

    /**
     *
     * @return
     */
    public JTextComponent getSalida() {
        return visorSalida;
    }

    /**
     *
     * @return
     */
    public JTextComponent getDebug() {
        return visorDepurador;
    }

    /**
     *
     * @return
     */
    public Ejecutor getEjecutor() {
        return ejecutor;
    }

    /**
     *
     * @param inter
     */
    public void setInterprete(Interprete inter) {
        interprete = inter;

    }

    /**
     *
     * @return
     */
    public Interprete getInterprete() {
        return interprete;
    }

    /**
     *
     * @param ejec
     */
    public void setEjecutor(Ejecutor ejec) {
        ejecutor = ejec;
    }

    /**
     *
     * @return
     */
    public OyenteRegistroLogico getRegitroLogicoSecuencia() {
        return (OyenteRegistroLogico) regitroLogicoDeSecuencia;
    }

    /**
     *
     * @return
     */
    public File getSelectedFile() {
        return selectedFile;
    }

    /**
     *
     * @param selectedFile
     */
    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    /**
     *
     * @return
     */
    public PropertyChangeListener getDepurador() {
        return cambiosDepurador;
    }

    public PropertyChangeListener getEstadoEjecucionEditor() {
        return estadoEjecucionEditor;
    }

    /**
     * @return the runAction
     */
    public Action getRunAction() {
        return runAction;
    }

    /**
     * @return the pauseAction
     */
    public Action getPauseAction() {
        return pauseAction;
    }

    /**
     * @return the stopAction
     */
    public Action getStopAction() {
        return stopAction;
    }

    /**
     * @return the debugAction
     */
    public Action getDebugAction() {
        return debugAction;
    }

    /**
     * @return the nextAction
     */
    public Action getNextAction() {
        return nextAction;
    }

    /**
     * @return the runAgainAction
     */
    public Action getRunAgainAction() {
        return runAgainAction;
    }

    /**
     * @return the runManualAgainAction
     */
    public Action getRunManualAgainAction() {
        return runManualAgainAction;
    }

    /**
     * Carga el contenido del archivo en el editor de c�digo
     *
     * @param path Ruta absoluta del archivo
     */
    private void cargarArchivo(String path) {

        FileReader reader = null;

        try {
            setSelectedFile(new File(path));

            reader = new FileReader(getSelectedFile());

            getEntrada().read(reader, null);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo " + path, "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        } finally {

            ((JTextArea) getEntrada()).setTabSize(2);

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException x) {
                }
            }
        }

    }

    protected void cargarConfiguraciones() {
        //System.out.println("Utilizando el directorio temporal " +OpcionesInterprete.getDirectorioTrabajo());

        if (!OpcionesInterprete.hayConfiguracion()) {
            System.out.println("No se encontraron configuraciones, estableciendo configuraciones por defecto...");

            if (!OpcionesInterprete.generarConfiguracionPorDefecto()) {
                System.out.println("No se pudo generar la configuración por defecto");
            }
        }

        // cargamos las configuraciones
        if (!OpcionesInterprete.cargarConfiguracion()) {
            System.out.println("No se pudieron cargar las configuraciones...");
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        final EditorSencillo editor;
        try {
            editor = new EditorSencillo();
            editor.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            editor.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent evt) {
                    editor.exitAction.actionPerformed(null);
                }
            });
            editor.setVisible(true);

            if (args.length == 1) {
                editor.cargarArchivo(args[0]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
