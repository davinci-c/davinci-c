package edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
//JAVA AWT
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
//JAVAX SWING
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

/**
 *
 * @author german.tejero
 */
public final class EditorVisualCiudad extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(EditorVisualCiudad.class.getName());

    /**
     *
     */
    private Persistidor persistidor;

    /**
     *
     */
    private ListaDeConfiguracionDeEsquina configuracionesFlores;

    /**
     *
     */
    private ListaDeConfiguracionDeEsquina configuracionesPapeles;

    /**
     *
     */
    private ListaDeConfiguracionDeEsquina configuracionesObstaculos;

    /**
     *
     */
    public EditorVisualCiudad(Persistidor p) {

        //DEBUG
        auditor.finest("Constructor");

        //
        persistidor = p;
        configuracionesFlores = new ListaDeConfiguracionDeEsquina();
        configuracionesPapeles = new ListaDeConfiguracionDeEsquina();
        configuracionesObstaculos = new ListaDeConfiguracionDeEsquina();

        //
        initComponents();

        initComponentsEx();

        cargarConfiguracion();
    }

    public EditorVisualCiudad(Persistidor persistidor, JFrame padre) {
        this(persistidor);
        setLocationRelativeTo(padre);
    }

    /**
     *
     */
    public void initComponentsEx() {

        //TODO Reemplazar por el uso de javax.swing.Action y generics?
        botonEliminarFlores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                eliminarConfiguracionFlores(evento);
            }
        });

        //
        botonAgregarFlores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                agregarConfiguracionFlores(evento);
            }
        });

        //TODO Reemplazar por el uso de javax.swing.Action y generics?
        botonEliminarPapeles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                eliminarConfiguracionPapeles(evento);
            }
        });

        //
        botonAgregarPapeles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                agregarConfiguracionPapeles(evento);
            }
        });

        //TODO Reemplazar por el uso de javax.swing.Action y generics?
        botonEliminarObstaculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                eliminarConfiguracionObstaculos(evento);
            }
        });

        //
        botonAgregarObstaculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                agregarConfiguracionObstaculos(evento);
            }
        });

    }

    /**
     *
     * @param evento
     */
    public void agregarConfiguracionFlores(ActionEvent evento) {

        auditor.log(Level.FINEST, "agregarConfiguracionFlores {0}", evento);

        try {
            ConfiguracionDeFloresAbsoluta cdfa = new ConfiguracionDeFloresAbsoluta();

            ComboBoxModel<String> modeloAvenidaFlores = (ComboBoxModel<String>) listaAvenidaFlores.getModel();
            ComboBoxModel<String> modeloCalleFlores = (ComboBoxModel<String>) listaCalleFlores.getModel();

            //agremamos la avenida absoluta
            if (modeloAvenidaFlores.getSelectedItem().toString().equals(ConfiguracionDeFloresAbsoluta.SIMBOLO_COMODIN)) {
                cdfa.setAvenida(0);
            } else {
                cdfa.setAvenida(Integer.parseInt(modeloAvenidaFlores.getSelectedItem().toString()));
            }

            //agremamos la calle absoluta
            if (modeloCalleFlores.getSelectedItem().toString().equals(ConfiguracionDeFloresAbsoluta.SIMBOLO_COMODIN)) {
                cdfa.setCalle(0);
            } else {
                cdfa.setCalle(Integer.parseInt(modeloCalleFlores.getSelectedItem().toString()));
            }

            //agregamos la cantidad
            SpinnerNumberModel modeloCantidadFlores = (SpinnerNumberModel) editorCantidadFlores.getModel();
            cdfa.setCantidad(modeloCantidadFlores.getNumber().intValue());

            //agregamos la configuracion
            configuracionesFlores.agregarConfiguracion(cdfa);

        } catch (NumberFormatException e) {
            auditor.log(Level.SEVERE, "Error al guardar configuracion de flores por {0}", e.getLocalizedMessage());
        }
    }

    /**
     *
     * @param evento
     */
    public void eliminarConfiguracionFlores(ActionEvent evento) {

        auditor.log(Level.FINEST, "eliminarConfiguracionFlores {0}", evento);
        eliminarConfiguracion(listaFlores, configuracionesFlores, evento);

        /*int relativo = 0;
         for (Integer indice:listaFlores.getSelectedIndices()) {
         configuracionesFlores.eliminarConfiguracion(indice-relativo);
         relativo++;
         } */
    }

    /**
     *
     * @param evento
     */
    public void agregarConfiguracionPapeles(ActionEvent evento) {

        auditor.log(Level.FINEST, "agregarConfiguracionPapeles {0}", evento);

        try {
            ConfiguracionDePapelesAbsoluta cdpa = new ConfiguracionDePapelesAbsoluta();

            ComboBoxModel<String> modeloAvenidaPapeles = (ComboBoxModel<String>) listaAvenidaPapeles.getModel();
            ComboBoxModel<String> modeloCallePapeles = (ComboBoxModel<String>) listaCallePapeles.getModel();

            //agremamos la avenida absoluta
            if (modeloAvenidaPapeles.getSelectedItem().toString().equals(ConfiguracionDePapelesAbsoluta.SIMBOLO_COMODIN)) {
                cdpa.setAvenida(0);
            } else {
                cdpa.setAvenida(Integer.parseInt(modeloAvenidaPapeles.getSelectedItem().toString()));
            }

            //agremamos la calle absoluta
            if (modeloCallePapeles.getSelectedItem().toString().equals(ConfiguracionDePapelesAbsoluta.SIMBOLO_COMODIN)) {
                cdpa.setCalle(0);
            } else {
                cdpa.setCalle(Integer.parseInt(modeloCallePapeles.getSelectedItem().toString()));
            }

            //agregamos la cantidad
            cdpa.setCantidad(((SpinnerNumberModel) editorCantidadPapeles.getModel()).getNumber().intValue());

            //agregamos la configuracion
            configuracionesPapeles.agregarConfiguracion(cdpa);

        } catch (Exception e) {
            auditor.log(Level.SEVERE, "Error al guardar configuracion de papeles por {0}", e.getLocalizedMessage());
        }
    }

    /**
     *
     * @param evento
     */
    public void eliminarConfiguracionPapeles(ActionEvent evento) {

        auditor.log(Level.FINEST, "eliminarConfiguracionPapeles {0}", evento);
        eliminarConfiguracion(listaPapeles, configuracionesPapeles, evento);

        /*int relativo = 0;
         for (Integer indice:listaPapeles.getSelectedIndices()) {
         configuracionesPapeles.eliminarConfiguracion(indice-relativo);
         relativo++;
         }*/
    }

    /**
     * Elimina las configuraciones seleccionadas según los indices seleccionados
     * de la lista
     *
     * @param lista
     * @param configuracion
     * @param evento
     */
    private void eliminarConfiguracion(JList lista, ListaDeConfiguracionDeEsquina configuracion, ActionEvent evento) {
        //para apuntar al indice real 
        int relativo = 0;

        //recorro los indices seleccionados de la lista
        for (Integer indice : lista.getSelectedIndices()) {

            //elimina la configuracion de la posicion seleccionada
            configuracion.eliminarConfiguracion(indice - relativo);

            //
            relativo++;
        }
    }

    /**
     *
     * @param evento
     */
    public void agregarConfiguracionObstaculos(ActionEvent evento) {

        auditor.log(Level.FINEST, "agregarConfiguracionObstaculos {0}", evento);

        try {
            ConfiguracionDeObstaculosAbsoluta cdoa = new ConfiguracionDeObstaculosAbsoluta();
            ComboBoxModel<String> modeloAvenidaPapeles = (ComboBoxModel<String>) listaAvenidaObstaculos.getModel();
            ComboBoxModel<String> modeloCallePapeles = (ComboBoxModel<String>) listaCalleObstaculos.getModel();

            cdoa.setAvenida(Integer.parseInt(modeloAvenidaPapeles.getSelectedItem().toString()));
            cdoa.setCalle(Integer.parseInt(modeloCallePapeles.getSelectedItem().toString()));

            configuracionesObstaculos.agregarConfiguracion(cdoa);

        } catch (Exception e) {
            auditor.log(Level.SEVERE, "Error al guardar configuracion de obstaculos por {0}", e.getLocalizedMessage());
        }
    }

    /**
     *
     * @param evento
     */
    public void eliminarConfiguracionObstaculos(ActionEvent evento) {

        auditor.log(Level.FINEST, "eliminarConfiguracionObstaculos {0}", evento);
        eliminarConfiguracion(listaObstaculos, configuracionesObstaculos, evento);

        /*Integer indice = listaObstaculos.getSelectedIndex();
         if (indice >= 0) {

         configuracionesObstaculos.eliminarConfiguracion(indice);
         }*/
    }

    /**
     *
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneles = new JTabbedPane();
        panelFlores = new JPanel();
        panelListaFlores = new JPanel();
        desplazadorListaFlores = new JScrollPane();
        listaFlores = new JList();
        botonEliminarFlores = new JButton();
        panelDatosFlores = new JPanel();
        etiquetaAvenidaFlores = new JLabel();
        etiquetaCalleFlores = new JLabel();
        etiquetaCantidadFlores = new JLabel();
        editorCantidadFlores = new JSpinner();
        listaAvenidaFlores = new JComboBox();
        listaCalleFlores = new JComboBox();
        botonAgregarFlores = new JButton();
        panelFloresAleatorias = new JPanel();
        jLabel1 = new JLabel();
        cantidadFloresAleatorias = new JSpinner();
        panelPapeles = new JPanel();
        panelDatosPapeles = new JPanel();
        etiquetaAvenidaPapeles = new JLabel();
        etiquetaCallePapeles = new JLabel();
        etiquetaCantidadPapeles = new JLabel();
        editorCantidadPapeles = new JSpinner();
        listaAvenidaPapeles = new JComboBox();
        listaCallePapeles = new JComboBox();
        botonAgregarPapeles = new JButton();
        panelListaPapeles = new JPanel();
        desplazadorListaPapeles = new JScrollPane();
        listaPapeles = new JList();
        botonEliminarPapeles = new JButton();
        panelPapelesAleatorios = new JPanel();
        jLabel2 = new JLabel();
        cantidadPapelesAleatorios = new JSpinner();
        panelObstaculos = new JPanel();
        panelListaObstaculos = new JPanel();
        desplazadorListaObstaculos = new JScrollPane();
        listaObstaculos = new JList();
        botonEliminarObstaculos = new JButton();
        panelDatosObstaculos = new JPanel();
        etiquetaAvenidaObstaculos = new JLabel();
        etiquetaCalleObstaculos = new JLabel();
        botonAgregarObstaculos = new JButton();
        listaAvenidaObstaculos = new JComboBox();
        listaCalleObstaculos = new JComboBox();
        panelRobots = new JPanel();
        etiquetaFloresRobots = new JLabel();
        etiquetaPapelesRobots = new JLabel();
        permitirSuperposicion = new JCheckBox();
        editorFloresRobots = new JSpinner();
        editorPapelesRobots = new JSpinner();
        panelCiudadFisica = new JPanel();
        etiquetaIPRobot = new JLabel();
        etiquetaPuertoRobot = new JLabel();
        habilitarCiudadFisica = new JCheckBox();
        editorIPRobot = new JTextField();
        editorPuertoRobot = new JTextField();
        botonCancelar = new JButton();
        botonAceptar = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuración de ciudad");
        setMinimumSize(new Dimension(444, 444));
        setResizable(false);

        panelListaFlores.setBorder(BorderFactory.createTitledBorder("Fijas"));
        panelListaFlores.setPreferredSize(new Dimension(210, 190));

        listaFlores.setModel(configuracionesFlores);
        listaFlores.setToolTipText("<html>\nPuede utilizar las teclas <strong> ctrl </strong>, <strong> shift </strong> o <strong> ctrl + a </strong> para seleccionar más de uno\n</html>");
        desplazadorListaFlores.setViewportView(listaFlores);

        botonEliminarFlores.setText("Eliminar");
        botonEliminarFlores.setToolTipText("Elimina las filas seleccionadas");

        GroupLayout panelListaFloresLayout = new GroupLayout(panelListaFlores);
        panelListaFlores.setLayout(panelListaFloresLayout);
        panelListaFloresLayout.setHorizontalGroup(panelListaFloresLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelListaFloresLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelListaFloresLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(desplazadorListaFlores, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .add(botonEliminarFlores, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelListaFloresLayout.setVerticalGroup(panelListaFloresLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelListaFloresLayout.createSequentialGroup()
                .addContainerGap()
                .add(desplazadorListaFlores, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(botonEliminarFlores)
                .addContainerGap())
        );

        panelDatosFlores.setBorder(BorderFactory.createTitledBorder("Datos"));
        panelDatosFlores.setPreferredSize(new Dimension(184, 141));

        etiquetaAvenidaFlores.setText("Avenida:");

        etiquetaCalleFlores.setText("Calle:");

        etiquetaCantidadFlores.setText("Cantidad:");
        etiquetaCantidadFlores.setToolTipText("");

        editorCantidadFlores.setModel(new SpinnerNumberModel(1, 1, 200, 1));
        editorCantidadFlores.setToolTipText("<html>\nMínimo = 1 <br>\nMáximo= 200\n</html>");

        listaAvenidaFlores.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "?" }));
        listaAvenidaFlores.setToolTipText("<html>\nel símbolo <strong> ? </strong> indica que puede ser cualquier avenida \n</html>");

        listaCalleFlores.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "?" }));
        listaCalleFlores.setToolTipText("<html>\nel símbolo <strong> ? </strong> indica que puede ser cualquier calle \n</html>");

        botonAgregarFlores.setText("Agregar");
        botonAgregarFlores.setToolTipText("Incorpora los datos a la lista de flores fijas");

        GroupLayout panelDatosFloresLayout = new GroupLayout(panelDatosFlores);
        panelDatosFlores.setLayout(panelDatosFloresLayout);
        panelDatosFloresLayout.setHorizontalGroup(panelDatosFloresLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelDatosFloresLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelDatosFloresLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(botonAgregarFlores, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(panelDatosFloresLayout.createSequentialGroup()
                        .add(panelDatosFloresLayout.createParallelGroup(GroupLayout.LEADING)
                            .add(etiquetaCantidadFlores)
                            .add(etiquetaCalleFlores)
                            .add(etiquetaAvenidaFlores))
                        .add(18, 18, 18)
                        .add(panelDatosFloresLayout.createParallelGroup(GroupLayout.LEADING, false)
                            .add(editorCantidadFlores, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .add(listaCalleFlores, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(listaAvenidaFlores, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDatosFloresLayout.setVerticalGroup(panelDatosFloresLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelDatosFloresLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelDatosFloresLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaAvenidaFlores)
                    .add(listaAvenidaFlores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(panelDatosFloresLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaCalleFlores)
                    .add(listaCalleFlores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(panelDatosFloresLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaCantidadFlores)
                    .add(editorCantidadFlores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(botonAgregarFlores)
                .addContainerGap())
        );

        panelFloresAleatorias.setBorder(BorderFactory.createTitledBorder("Aleatorias"));
        panelFloresAleatorias.setPreferredSize(new Dimension(150, 70));

        jLabel1.setText("Cantidad:");

        cantidadFloresAleatorias.setModel(new SpinnerNumberModel(0, 0, 200, 1));
        cantidadFloresAleatorias.setToolTipText("<html> Mínimo = 1 <br> Máximo= 200 </html>");

        GroupLayout panelFloresAleatoriasLayout = new GroupLayout(panelFloresAleatorias);
        panelFloresAleatorias.setLayout(panelFloresAleatoriasLayout);
        panelFloresAleatoriasLayout.setHorizontalGroup(panelFloresAleatoriasLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelFloresAleatoriasLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(18, 18, 18)
                .add(cantidadFloresAleatorias, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFloresAleatoriasLayout.setVerticalGroup(panelFloresAleatoriasLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelFloresAleatoriasLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelFloresAleatoriasLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(cantidadFloresAleatorias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        GroupLayout panelFloresLayout = new GroupLayout(panelFlores);
        panelFlores.setLayout(panelFloresLayout);
        panelFloresLayout.setHorizontalGroup(panelFloresLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelFloresLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelFloresLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(panelFloresAleatorias, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                    .add(panelFloresLayout.createSequentialGroup()
                        .add(panelListaFlores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(panelDatosFlores, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelFloresLayout.setVerticalGroup(panelFloresLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelFloresLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelFloresAleatorias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.UNRELATED)
                .add(panelFloresLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(panelListaFlores, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .add(panelDatosFlores, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                .addContainerGap())
        );

        paneles.addTab("Flores", panelFlores);

        panelDatosPapeles.setBorder(BorderFactory.createTitledBorder("Datos"));

        etiquetaAvenidaPapeles.setText("Avenida:");

        etiquetaCallePapeles.setText("Calle:");

        etiquetaCantidadPapeles.setText("Cantidad:");

        editorCantidadPapeles.setModel(new SpinnerNumberModel(1, 1, 200, 1));
        editorCantidadPapeles.setToolTipText("<html> Mínimo = 1 <br> Máximo= 200 </html>");

        listaAvenidaPapeles.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "?" }));
        listaAvenidaPapeles.setToolTipText("<html>\nel símbolo <strong> ? </strong> indica que puede ser cualquier avenida \n</html>");

        listaCallePapeles.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "?" }));
        listaCallePapeles.setToolTipText("<html>\nel símbolo <strong> ? </strong> indica que puede ser cualquier calle \n</html>");

        botonAgregarPapeles.setText("Agregar");
        botonAgregarPapeles.setToolTipText("Incorpora los datos a la lista de papeles fijos");

        GroupLayout panelDatosPapelesLayout = new GroupLayout(panelDatosPapeles);
        panelDatosPapeles.setLayout(panelDatosPapelesLayout);
        panelDatosPapelesLayout.setHorizontalGroup(panelDatosPapelesLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelDatosPapelesLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelDatosPapelesLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(panelDatosPapelesLayout.createSequentialGroup()
                        .add(botonAgregarPapeles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(panelDatosPapelesLayout.createSequentialGroup()
                        .add(panelDatosPapelesLayout.createParallelGroup(GroupLayout.LEADING)
                            .add(etiquetaAvenidaPapeles)
                            .add(etiquetaCallePapeles)
                            .add(etiquetaCantidadPapeles))
                        .add(18, 18, 18)
                        .add(panelDatosPapelesLayout.createParallelGroup(GroupLayout.LEADING, false)
                            .add(listaAvenidaPapeles, 0, 82, Short.MAX_VALUE)
                            .add(listaCallePapeles, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(editorCantidadPapeles))
                        .add(0, 40, Short.MAX_VALUE))))
        );
        panelDatosPapelesLayout.setVerticalGroup(panelDatosPapelesLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelDatosPapelesLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelDatosPapelesLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaAvenidaPapeles)
                    .add(listaAvenidaPapeles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(panelDatosPapelesLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaCallePapeles)
                    .add(listaCallePapeles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(panelDatosPapelesLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaCantidadPapeles)
                    .add(editorCantidadPapeles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(botonAgregarPapeles)
                .addContainerGap())
        );

        panelListaPapeles.setBorder(BorderFactory.createTitledBorder("Fijos"));
        panelListaPapeles.setPreferredSize(new Dimension(210, 190));

        listaPapeles.setModel(configuracionesPapeles);
        listaPapeles.setToolTipText("<html> Puede utilizar las teclas <strong> ctrl </strong>, <strong> shift </strong> o <strong> ctrl + a </strong> para seleccionar más de uno </html>");
        desplazadorListaPapeles.setViewportView(listaPapeles);

        botonEliminarPapeles.setText("Eliminar");
        botonEliminarPapeles.setToolTipText("Elimina las filas seleccionadas");

        GroupLayout panelListaPapelesLayout = new GroupLayout(panelListaPapeles);
        panelListaPapeles.setLayout(panelListaPapelesLayout);
        panelListaPapelesLayout.setHorizontalGroup(panelListaPapelesLayout.createParallelGroup(GroupLayout.LEADING)
            .add(GroupLayout.TRAILING, panelListaPapelesLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelListaPapelesLayout.createParallelGroup(GroupLayout.TRAILING)
                    .add(botonEliminarPapeles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(desplazadorListaPapeles, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelListaPapelesLayout.setVerticalGroup(panelListaPapelesLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelListaPapelesLayout.createSequentialGroup()
                .addContainerGap()
                .add(desplazadorListaPapeles, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(botonEliminarPapeles)
                .addContainerGap())
        );

        panelPapelesAleatorios.setBorder(BorderFactory.createTitledBorder("Aleatorios"));
        panelPapelesAleatorios.setPreferredSize(new Dimension(150, 70));

        jLabel2.setText("Cantidad:");

        cantidadPapelesAleatorios.setModel(new SpinnerNumberModel(0, 0, 200, 1));
        cantidadPapelesAleatorios.setToolTipText("<html> Mínimo = 1 <br> Máximo= 200 </html>");

        GroupLayout panelPapelesAleatoriosLayout = new GroupLayout(panelPapelesAleatorios);
        panelPapelesAleatorios.setLayout(panelPapelesAleatoriosLayout);
        panelPapelesAleatoriosLayout.setHorizontalGroup(panelPapelesAleatoriosLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelPapelesAleatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2)
                .add(18, 18, 18)
                .add(cantidadPapelesAleatorios, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPapelesAleatoriosLayout.setVerticalGroup(panelPapelesAleatoriosLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelPapelesAleatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelPapelesAleatoriosLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(cantidadPapelesAleatorios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        GroupLayout panelPapelesLayout = new GroupLayout(panelPapeles);
        panelPapeles.setLayout(panelPapelesLayout);
        panelPapelesLayout.setHorizontalGroup(panelPapelesLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelPapelesLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelPapelesLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(panelPapelesAleatorios, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                    .add(panelPapelesLayout.createSequentialGroup()
                        .add(panelListaPapeles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(panelDatosPapeles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelPapelesLayout.setVerticalGroup(panelPapelesLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelPapelesLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelPapelesAleatorios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.UNRELATED)
                .add(panelPapelesLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(panelListaPapeles, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .add(panelDatosPapeles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        paneles.addTab("Papeles", panelPapeles);

        panelListaObstaculos.setBorder(BorderFactory.createTitledBorder("Fijos"));

        listaObstaculos.setModel(configuracionesObstaculos);
        listaObstaculos.setToolTipText("<html> Puede utilizar las teclas <strong> ctrl </strong>, <strong> shift </strong> o <strong> ctrl + a </strong> para seleccionar más de uno </html>");
        desplazadorListaObstaculos.setViewportView(listaObstaculos);

        botonEliminarObstaculos.setText("Eliminar");
        botonEliminarObstaculos.setToolTipText("Elimina las filas seleccionadas");

        GroupLayout panelListaObstaculosLayout = new GroupLayout(panelListaObstaculos);
        panelListaObstaculos.setLayout(panelListaObstaculosLayout);
        panelListaObstaculosLayout.setHorizontalGroup(panelListaObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelListaObstaculosLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelListaObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(botonEliminarObstaculos, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .add(desplazadorListaObstaculos, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelListaObstaculosLayout.setVerticalGroup(panelListaObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelListaObstaculosLayout.createSequentialGroup()
                .add(desplazadorListaObstaculos, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(botonEliminarObstaculos)
                .addContainerGap())
        );

        panelDatosObstaculos.setBorder(BorderFactory.createTitledBorder("Datos"));

        etiquetaAvenidaObstaculos.setText("Avenida:");

        etiquetaCalleObstaculos.setText("Calle:");
        etiquetaCalleObstaculos.setToolTipText("");

        botonAgregarObstaculos.setText("Agregar");
        botonAgregarObstaculos.setToolTipText("Incorpora los datos a la lista de obstáculos fijos");

        listaAvenidaObstaculos.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        listaCalleObstaculos.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        GroupLayout panelDatosObstaculosLayout = new GroupLayout(panelDatosObstaculos);
        panelDatosObstaculos.setLayout(panelDatosObstaculosLayout);
        panelDatosObstaculosLayout.setHorizontalGroup(panelDatosObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelDatosObstaculosLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelDatosObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(botonAgregarObstaculos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(panelDatosObstaculosLayout.createSequentialGroup()
                        .add(panelDatosObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
                            .add(etiquetaCalleObstaculos)
                            .add(etiquetaAvenidaObstaculos))
                        .add(26, 26, 26)
                        .add(panelDatosObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
                            .add(listaAvenidaObstaculos, 0, 108, Short.MAX_VALUE)
                            .add(listaCalleObstaculos, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelDatosObstaculosLayout.setVerticalGroup(panelDatosObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelDatosObstaculosLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelDatosObstaculosLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaAvenidaObstaculos)
                    .add(listaAvenidaObstaculos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(panelDatosObstaculosLayout.createParallelGroup(GroupLayout.TRAILING)
                    .add(etiquetaCalleObstaculos)
                    .add(listaCalleObstaculos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(botonAgregarObstaculos)
                .addContainerGap())
        );

        GroupLayout panelObstaculosLayout = new GroupLayout(panelObstaculos);
        panelObstaculos.setLayout(panelObstaculosLayout);
        panelObstaculosLayout.setHorizontalGroup(panelObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelObstaculosLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelListaObstaculos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.RELATED)
                .add(panelDatosObstaculos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelObstaculosLayout.setVerticalGroup(panelObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelObstaculosLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelObstaculosLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(panelListaObstaculos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(panelDatosObstaculos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        paneles.addTab("Obstáculos", panelObstaculos);

        etiquetaFloresRobots.setText("Cantidad de flores inicial:");

        etiquetaPapelesRobots.setText("Cantidad de papeles inicial:");

        permitirSuperposicion.setText("¿Permitir superposición de robots en la ciudad?");
        permitirSuperposicion.setHorizontalTextPosition(SwingConstants.LEADING);

        editorFloresRobots.setModel(new SpinnerNumberModel(0, 0, 200, 1));

        editorPapelesRobots.setModel(new SpinnerNumberModel(0, 0, 200, 1));

        GroupLayout panelRobotsLayout = new GroupLayout(panelRobots);
        panelRobots.setLayout(panelRobotsLayout);
        panelRobotsLayout.setHorizontalGroup(panelRobotsLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelRobotsLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelRobotsLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(permitirSuperposicion)
                    .add(panelRobotsLayout.createSequentialGroup()
                        .add(panelRobotsLayout.createParallelGroup(GroupLayout.LEADING)
                            .add(etiquetaPapelesRobots)
                            .add(etiquetaFloresRobots))
                        .add(18, 18, 18)
                        .add(panelRobotsLayout.createParallelGroup(GroupLayout.LEADING, false)
                            .add(editorPapelesRobots, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .add(editorFloresRobots))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRobotsLayout.setVerticalGroup(panelRobotsLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelRobotsLayout.createSequentialGroup()
                .addContainerGap()
                .add(permitirSuperposicion)
                .add(18, 18, 18)
                .add(panelRobotsLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaFloresRobots)
                    .add(editorFloresRobots, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.UNRELATED)
                .add(panelRobotsLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaPapelesRobots)
                    .add(editorPapelesRobots, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
        );

        paneles.addTab("Robots", panelRobots);

        etiquetaIPRobot.setText("IP del robot");

        etiquetaPuertoRobot.setText("Puerto");

        habilitarCiudadFisica.setText("¿Habilitar la ciudad con un robot fisico?");
        habilitarCiudadFisica.setHorizontalTextPosition(SwingConstants.LEADING);
        habilitarCiudadFisica.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                habilitarCiudadFisicaStateChanged(evt);
            }
        });
        habilitarCiudadFisica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                habilitarCiudadFisicaActionPerformed(evt);
            }
        });

        GroupLayout panelCiudadFisicaLayout = new GroupLayout(panelCiudadFisica);
        panelCiudadFisica.setLayout(panelCiudadFisicaLayout);
        panelCiudadFisicaLayout.setHorizontalGroup(panelCiudadFisicaLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelCiudadFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelCiudadFisicaLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(habilitarCiudadFisica)
                    .add(panelCiudadFisicaLayout.createSequentialGroup()
                        .add(panelCiudadFisicaLayout.createParallelGroup(GroupLayout.LEADING)
                            .add(etiquetaPuertoRobot)
                            .add(etiquetaIPRobot))
                        .add(18, 18, 18)
                        .add(panelCiudadFisicaLayout.createParallelGroup(GroupLayout.LEADING)
                            .add(editorIPRobot, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                            .add(editorPuertoRobot, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(224, Short.MAX_VALUE))
        );
        panelCiudadFisicaLayout.setVerticalGroup(panelCiudadFisicaLayout.createParallelGroup(GroupLayout.LEADING)
            .add(panelCiudadFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .add(habilitarCiudadFisica)
                .add(18, 18, 18)
                .add(panelCiudadFisicaLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaIPRobot)
                    .add(editorIPRobot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.UNRELATED)
                .add(panelCiudadFisicaLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(etiquetaPuertoRobot)
                    .add(editorPuertoRobot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
        );

        paneles.addTab("Ciudad Fisica", panelCiudadFisica);

        botonCancelar.setMnemonic('C');
        botonCancelar.setText("Cancelar");
        botonCancelar.setToolTipText("Descarta los cambios");
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonAceptar.setMnemonic('A');
        botonAceptar.setText("Aceptar");
        botonAceptar.setToolTipText("Guarda la configuración");
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(paneles)
                .add(18, 18, 18))
            .add(GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(botonAceptar)
                .addPreferredGap(LayoutStyle.RELATED)
                .add(botonCancelar)
                .add(39, 39, 39))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(paneles, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(botonAceptar)
                    .add(botonCancelar))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param evt
     */
    private void botonAceptarActionPerformed(ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed

        //
        guardarConfiguracion();
        dispose();
    }//GEN-LAST:event_botonAceptarActionPerformed

    /**
     *
     * @param evt
     */
    private void botonCancelarActionPerformed(ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed

        //
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void habilitarCiudadFisicaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_habilitarCiudadFisicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_habilitarCiudadFisicaActionPerformed

    private void habilitarCiudadFisicaStateChanged(ChangeEvent evt) {//GEN-FIRST:event_habilitarCiudadFisicaStateChanged
        // TODO add your handling code here:
        if (habilitarCiudadFisica.isSelected()) {
            editorIPRobot.setEnabled(true);
            editorPuertoRobot.setEnabled(true);
        } else {
            editorIPRobot.setEnabled(false);
            editorPuertoRobot.setEnabled(false);
        }

    }//GEN-LAST:event_habilitarCiudadFisicaStateChanged

    // <editor-fold defaultstate="collapsed" desc="Autogenerated variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    JButton botonAceptar;
    JButton botonAgregarFlores;
    JButton botonAgregarObstaculos;
    JButton botonAgregarPapeles;
    JButton botonCancelar;
    JButton botonEliminarFlores;
    JButton botonEliminarObstaculos;
    JButton botonEliminarPapeles;
    JSpinner cantidadFloresAleatorias;
    JSpinner cantidadPapelesAleatorios;
    JScrollPane desplazadorListaFlores;
    JScrollPane desplazadorListaObstaculos;
    JScrollPane desplazadorListaPapeles;
    JSpinner editorCantidadFlores;
    JSpinner editorCantidadPapeles;
    JSpinner editorFloresRobots;
    JTextField editorIPRobot;
    JSpinner editorPapelesRobots;
    JTextField editorPuertoRobot;
    JLabel etiquetaAvenidaFlores;
    JLabel etiquetaAvenidaObstaculos;
    JLabel etiquetaAvenidaPapeles;
    JLabel etiquetaCalleFlores;
    JLabel etiquetaCalleObstaculos;
    JLabel etiquetaCallePapeles;
    JLabel etiquetaCantidadFlores;
    JLabel etiquetaCantidadPapeles;
    JLabel etiquetaFloresRobots;
    JLabel etiquetaIPRobot;
    JLabel etiquetaPapelesRobots;
    JLabel etiquetaPuertoRobot;
    JCheckBox habilitarCiudadFisica;
    JLabel jLabel1;
    JLabel jLabel2;
    JComboBox listaAvenidaFlores;
    JComboBox listaAvenidaObstaculos;
    JComboBox listaAvenidaPapeles;
    JComboBox listaCalleFlores;
    JComboBox listaCalleObstaculos;
    JComboBox listaCallePapeles;
    JList listaFlores;
    JList listaObstaculos;
    JList listaPapeles;
    JPanel panelCiudadFisica;
    JPanel panelDatosFlores;
    JPanel panelDatosObstaculos;
    JPanel panelDatosPapeles;
    JPanel panelFlores;
    JPanel panelFloresAleatorias;
    JPanel panelListaFlores;
    JPanel panelListaObstaculos;
    JPanel panelListaPapeles;
    JPanel panelObstaculos;
    JPanel panelPapeles;
    JPanel panelPapelesAleatorios;
    JPanel panelRobots;
    JTabbedPane paneles;
    JCheckBox permitirSuperposicion;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

    /**
     *
     */
    public void cargarConfiguracion() {

        //DEBUG
        auditor.finest("cargarConfiguracion");

        //
        try {
            if (persistidor == null) {
                dispose();
            } else {
                //Obtenemos la condiguracion
                ConfiguracionDeCiudad configuracion = (ConfiguracionDeCiudad) persistidor.cargarConfiguracion();

                habilitarCiudadFisica.setSelected(configuracion.isHabilitarRobotFisico());
                editorIPRobot.setText(configuracion.getIpRobotFisico());
                editorPuertoRobot.setText(String.valueOf(configuracion.getPuertoRobotFisico()));

                //Configuracion de ciudad
                permitirSuperposicion.setSelected(configuracion.isPermitirSuperposicion());

                //Recorremos la confiuracion de flores
                SpinnerNumberModel modeloFloresAleatoria = (SpinnerNumberModel) cantidadFloresAleatorias.getModel();
                modeloFloresAleatoria.setValue(0);

                //cantidadFloresAleatorias.setText("0");
                for (ConfiguracionDeFlores cdf : configuracion.getConfiguracionDeFlores()) {
                    if (cdf instanceof ConfiguracionDeFloresAbsoluta) {
                        configuracionesFlores.agregarConfiguracion(cdf);
                    } else {
                        ConfiguracionDeFloresAleatoria cdfa = (ConfiguracionDeFloresAleatoria) cdf;
                        modeloFloresAleatoria.setValue(cdfa.getCantidad());
                    }
                }

                //Recorremos la confiuracion de papeles
                cantidadPapelesAleatorios.getModel().setValue(0);
                for (ConfiguracionDePapeles cdp : configuracion.getConfiguracionDePapeles()) {
                    if (cdp instanceof ConfiguracionDePapelesAbsoluta) {
                        configuracionesPapeles.agregarConfiguracion(cdp);
                    } else {
                        ConfiguracionDePapelesAleatoria cdpa = (ConfiguracionDePapelesAleatoria) cdp;
                        cantidadPapelesAleatorios.getModel().setValue(cdpa.getCantidad());
                    }
                }

                //Recorremos la confiuracion de obstaculos
                for (ConfiguracionDeObstaculos cdo : configuracion.getConfiguracionDeObstaculos()) {
                    configuracionesObstaculos.agregarConfiguracion(cdo);
                }

                //Configuracion de robots, por ahora solo una para todos
                if (configuracion.getConfiguracionDeRobots().size() == 1) {

                    ConfiguracionDeRobotsUnica cdru = (ConfiguracionDeRobotsUnica) configuracion.getConfiguracionDeRobots().get(0);

                    editorFloresRobots.getModel().setValue(cdru.getFlores());
                    editorPapelesRobots.getModel().setValue(cdru.getPapeles());

                } else {
                    editorFloresRobots.getModel().setValue(0);
                    editorPapelesRobots.getModel().setValue(0);
                }
            }
        } catch (Exception e) {
            auditor.severe(String.format("Error en la lectura de la configuracion de ciudad: %s", e.getLocalizedMessage()));
        }
    }

    /**
     *
     */
    public void guardarConfiguracion() {

        //DEBUG
        auditor.finest("componentClosed");

        //
        try {
            if (persistidor != null) {
                //
                ConfiguracionDeCiudad configuracion = new ConfiguracionDeCiudad();

                configuracion.setHabilitarRobotFisico(habilitarCiudadFisica.isSelected());
                configuracion.setIpRobotFisico(editorIPRobot.getText());
                configuracion.setPuertoRobotFisico(Integer.parseInt(editorPuertoRobot.getText()));

                //Configuracion de ciudad
                configuracion.setPermitirSuperposicion(permitirSuperposicion.isSelected());

                //Recorremos la configuracion de las flores fijas
                for (ConfiguracionDeEsquina cdf : configuracionesFlores) {
                    configuracion.getConfiguracionDeFlores().add((ConfiguracionDeFlores) cdf);
                }

                //configuracion de flores aleatorias
                ConfiguracionDeFloresAleatoria cdfa = new ConfiguracionDeFloresAleatoria();
                SpinnerNumberModel modelo = (SpinnerNumberModel) cantidadFloresAleatorias.getModel();
                cdfa.setCantidad(modelo.getNumber().intValue());
                //TODO no guarda las flores 
                configuracion.getConfiguracionDeFlores().add(cdfa);

                //Recorremos la configuracion de las papeles fijas
                for (ConfiguracionDeEsquina cdp : configuracionesPapeles) {
                    configuracion.getConfiguracionDePapeles().add((ConfiguracionDePapeles) cdp);
                }

                //configuracion de papeles aleatorias
                ConfiguracionDePapelesAleatoria cdpa = new ConfiguracionDePapelesAleatoria();
                cdpa.setCantidad(((SpinnerNumberModel) cantidadPapelesAleatorios.getModel()).getNumber().intValue());
                //TODO no guarda los papeles
                configuracion.getConfiguracionDePapeles().add(cdpa);

                //Recorremos la configuracion de las obstaculos
                for (ConfiguracionDeEsquina cdo : configuracionesObstaculos) {
                    configuracion.getConfiguracionDeObstaculos().add((ConfiguracionDeObstaculos) cdo);
                }

                //Configuracion unica de robots
                ConfiguracionDeRobotsUnica cdru = new ConfiguracionDeRobotsUnica();
                try {
                    cdru.setFlores(((SpinnerNumberModel) editorFloresRobots.getModel()).getNumber().intValue());
                    cdru.setPapeles(((SpinnerNumberModel) editorPapelesRobots.getModel()).getNumber().intValue());
                } catch (Exception e) {
                    auditor.log(Level.SEVERE, "Error en la configuracion de robots {0}", e.getLocalizedMessage());
                }
                configuracion.getConfiguracionDeRobots().add(cdru);

                //Guardamos la configuracion
                persistidor.guardarConfiguracion(configuracion);
            }
        } catch (Exception e) {
            auditor.severe(String.format("Error al guardar la configuracion de la ciudad: ", e.getLocalizedMessage()));
            JOptionPane.showMessageDialog(this, "Error al guardar la configuración, verifique la misma", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
