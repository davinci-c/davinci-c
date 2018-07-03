package  edu.davinci.ui.editor.configurador;

//JAVA UTIL
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
//SWING
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
//
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

/**
 * 
 * @author german.tejero
 */
public final class EditorVisualInterprete extends JFrame{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Auditor
     */
    private static final Logger auditor = Logger.getLogger(EditorVisualInterprete.class.getName());

    /**
     *
     */
    private JToolBar toolbar = new JToolBar();
    
    /**
     * 
     */
    private Persistidor persistidor;
    
    /**
     *
     */
    public EditorVisualInterprete(Persistidor p) {

        //DEBUG
        auditor.finest("Constructor");
        
        //
        persistidor   = p;
        
        //
        initComponents();
        
        //
        cargarConfiguracion();
    }
    
    public EditorVisualInterprete(Persistidor persistidor, JFrame padre) {
        this(persistidor);
        setLocationRelativeTo(padre);
    }
    
    /**
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        planificadores = new JComboBox();
        retardo = new JSlider();
        etiquetaPlanificadores = new JLabel();
        etiquetaRetardo = new JLabel();
        botonCancelar = new JButton();
        botonAceptar = new JButton();
        quantum = new JTextField();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuración de interprete");
        setMaximumSize(new Dimension(500, 250));
        setMinimumSize(new Dimension(450, 240));
        setResizable(false);

        planificadores.setModel(new ListaDePlanificadores());
        planificadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                planificadoresActionPerformed(evt);
            }
        });

        retardo.setMaximum(500);
        retardo.setPaintLabels(true);
        retardo.setPaintTicks(true);
        retardo.setSnapToTicks(true);

        etiquetaPlanificadores.setText("Planificador:");

        etiquetaRetardo.setText("Retardo:");

        botonCancelar.setMnemonic('C');
        botonCancelar.setText("Cancelar");
        botonCancelar.setDefaultCapable(false);
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonAceptar.setMnemonic('A');
        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        jLabel1.setText("Quantum:");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(GroupLayout.LEADING)
                    .add(GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(botonAceptar)
                        .addPreferredGap(LayoutStyle.UNRELATED)
                        .add(botonCancelar))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(GroupLayout.LEADING)
                            .add(etiquetaRetardo)
                            .add(etiquetaPlanificadores)
                            .add(jLabel1))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(GroupLayout.LEADING)
                            .add(retardo, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                            .add(planificadores, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(quantum, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                                .add(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(41, 41, 41)
                .add(layout.createParallelGroup(GroupLayout.LEADING)
                    .add(planificadores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(etiquetaPlanificadores))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(quantum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(GroupLayout.LEADING)
                    .add(retardo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(etiquetaRetardo))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(botonCancelar)
                    .add(botonAceptar))
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt 
     */
    private void botonCancelarActionPerformed(ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        
        auditor.finest("Cerramos sin guardar");
        
        //
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    /**
     * 
     * @param evt 
     */
    private void botonAceptarActionPerformed(ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        
        auditor.finest("Guardamos la configuracion");        
        guardarConfiguracion();
        
        auditor.finest("Cerramos");
        dispose();
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void planificadoresActionPerformed(ActionEvent evt) {//GEN-FIRST:event_planificadoresActionPerformed
        actualizarQuantum();
    }//GEN-LAST:event_planificadoresActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton botonAceptar;
    private JButton botonCancelar;
    private JLabel etiquetaPlanificadores;
    private JLabel etiquetaRetardo;
    private JLabel jLabel1;
    private JComboBox planificadores;
    private JTextField quantum;
    private JSlider retardo;
    // End of variables declaration//GEN-END:variables

    /**
     * 
     */
    public void cargarConfiguracion() {
        
        //DEBUG
        auditor.finest("cargarConfiguracion");
        
        //
        try{
            if(persistidor == null){
                dispose();
            }else{
                //
                ConfiguracionDeInterprete configuracion = (ConfiguracionDeInterprete) persistidor.cargarConfiguracion();
                
                //
                quantum.setText(configuracion.getQuantum().toString());
                retardo.setValue(configuracion.getRetardo());
                
                //
                ListaDePlanificadores lista = (ListaDePlanificadores) planificadores.getModel();
                lista.seleccionarPlanificador(configuracion.getPlanificador());
                
                actualizarQuantum();
            }
        }catch(Exception e){
            auditor.severe(String.format("Error en la lectura de la configuracion del interprete: %s", e.getLocalizedMessage()));
        }
    }

    /**
     *
     */
    public void guardarConfiguracion() {
        
        //DEBUG
        auditor.finest("guardarConfiguracion");
        
        //
        try{
            if(persistidor != null){
                
                //
                ConfiguracionDeInterprete configuracion = new ConfiguracionDeInterprete();
                
                //
                configuracion.setQuantum(Integer.valueOf(quantum.getText()));
                configuracion.setRetardo(retardo.getValue());
                
                //
                ListaDePlanificadores lista = (ListaDePlanificadores) planificadores.getModel();
                String planificador = lista.getSelectedItem().getClass().getCanonicalName();
                configuracion.setPlanificador(planificador);
                
                //
                persistidor.guardarConfiguracion(configuracion);
            }
        }catch(Exception e){
            auditor.severe(String.format("Error al guardar la configuracion del interprete: ", e.getLocalizedMessage()));
            JOptionPane.showMessageDialog(this, "Error al guardar la configuración, verifique la misma", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void actualizarQuantum() {
        String planificadorSeleccionado = planificadores.getModel().getSelectedItem().toString().toLowerCase();
        quantum.setEnabled(planificadorSeleccionado.contains("circular")||planificadorSeleccionado.contains("roundrobin"));
    }
}
