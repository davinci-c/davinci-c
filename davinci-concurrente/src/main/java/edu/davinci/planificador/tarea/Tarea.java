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
package edu.davinci.planificador.tarea;

import edu.davinci.lenguaje.ASTFuncionUsuario;
import java.util.Hashtable;

import edu.davinci.lenguaje.ASTIdentificador;
import edu.davinci.lenguaje.ASTIteracionCondicional;
import edu.davinci.lenguaje.ASTIteracionIncondicional;
import edu.davinci.lenguaje.ASTPri_PE;
import edu.davinci.lenguaje.ASTPri_PS;
import edu.davinci.lenguaje.ASTProcedimientoUsuario;
import edu.davinci.lenguaje.ASTProcedimientoUsuarioHilo;
import edu.davinci.lenguaje.ASTSeleccion;
import edu.davinci.lenguaje.ComplexNode;
import edu.davinci.lenguaje.DatoSemaforo;
import edu.davinci.lenguaje.Debug;
import edu.davinci.lenguaje.Node;
import edu.davinci.lenguaje.SimpleNode;
import edu.davinci.planificador.PlanificadorUtils;

/**
 * Clase encargada de representar el conjunto de tareas que tiene un hilo.
 *
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 *
 * @author Daniel E., Aguil Mallea
 */
public class Tarea {

    /**
     * Identificador de la tarea *
     */
    private int id;

    /**
     * nombre *
     */
    private String nombre;

    /**
     * Estado de la tarea *
     */
    private boolean suspendida;

    /**
     * Contexto actual de la tarea *
     */
    private Hashtable<String, Object> contexto;

    /**
     * nodos a interpretar *
     */
    private TareaSimple nodos;

    /**
     * TareaCompuesta *
     */
    private TareaCompuesta subtarea;

    /**
     * CONSTRUCTOR .
     */
    public Tarea() {
        setId(0);
        setNombre("");
        setSuspendida(false);
        setContexto(null);
        setSubTarea(null);
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre the new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the suspendida.
     *
     * @param suspendida the new suspendida
     */
    public void setSuspendida(boolean suspendida) {
        this.suspendida = suspendida;
    }

    /**
     * Checks if is suspendida.
     *
     * @return true, if is suspendida
     */
    public boolean isSuspendida() {
        return suspendida;
    }

    /**
     * Setear contexto.
     *
     * @param contexto
     */
    public void setContexto(Hashtable<String, Object> contexto) {
        this.contexto = contexto;
    }

    /**
     * Gets the contexto.
     *
     * @return the contexto
     */
    public Hashtable<String, Object> getContexto() {
        return contexto;
    }

    /**
     * Gets the nodos.
     *
     * @return the nodos
     */
    public TareaSimple getNodos() {
        return nodos;
    }

    /**
     * Seteamos los nodos para su interpretacion.
     *
     * @param rootNode the new nodos
     */
    public void setNodos(Node rootNode) {
        setNodos(new TareaSimple(((SimpleNode) rootNode).getChildren()));
    }

    /**
     * Sets the nodos.
     *
     * @param tareaSimple the new nodos
     */
    public void setNodos(TareaSimple tareaSimple) {
        nodos = tareaSimple;
    }

    /**
     * Sets the sub tarea.
     *
     * @param subtarea the new sub tarea
     */
    private void setSubTarea(TareaCompuesta subtarea) {
        this.subtarea = subtarea;
    }

    /**
     * Gets the sub tarea.
     *
     * @return the sub tarea
     */
    protected TareaCompuesta getSubTarea() {
        return subtarea;
    }

    /**
     * Hay sub tarea.
     *
     * @return true, if successful
     */
    protected boolean haySubTarea() {
        return getSubTarea() != null;
    }

    /**
     * Gets the linea.
     *
     * @return the linea
     */
    public int getLinea() {
        return ((SimpleNode) getNodos().getNodo()).jjtGetFirstToken().beginLine;
    }

    /**
     * Gets the variables.
     *
     * @return the variables
     */
    public Hashtable<String, Object> getVariables() {
        //
        Tarea t = getTareaMasprofunda();
        //
        return t.getContexto();
    }

    /**
     * Gets the linea interpretacion.
     *
     * @return the linea interpretacion
     */
    public int getLineaInterpretacion() {
        //
        Tarea t = getTareaMasprofunda();
        //
        return t.getLinea();

    }

    /**
     * Gets the tarea masprofunda.
     *
     * @return the tarea masprofunda
     */
    protected Tarea getTareaMasprofunda() {
        Tarea t = this;
        while (t.getSubTarea() != null) {
            t = t.getSubTarea();
        }
        return t;
    }

    /**
     * Interpretamos las tareas simples o descomponemos en subtareas si son
     * compuestas
     */
    public void ejecutar() {
        // verificamos si hay subtareas
        if (haySubTarea()) {

            Debug.println(String.format("<<Tarea.java - Existe una subtarea - nombre: %s >>", getSubTarea().getNombre()));
            // ejecutamos la subtarea
            getSubTarea().ejecutar();

            // verificamos si se terminaron de interpretar todas las subtareas
            if (getSubTarea().finalizo()) // marcamos que no hay mas subtareas
            {
                setSubTarea(null);
            }

        } else {
            // para verificar si se puede avanza con el siguiente nodo
            boolean avanzarConElSiguienteNodo = true;

            // no hay subtareas, continuamos la interpretacion
            SimpleNode nodo = (SimpleNode) getNodos().getNodo();

            // seteamos el contexto
            // la tabla de simbolos depende del contexto:
            SimpleNode.setTablaDeSimbolos(getContexto());

            // verificamos si el nodo a intepretar hay que descomponerlo o se
            // puede interpretar
            if (esInterpretable(nodo)) {
                // seteamos el contexto
                // deprecado porque la tabla de simbolos depende del contexto:
                // SimpleNode.setTablaDeSimbolos(getContexto());
                // interpretamos
                interpretar(nodo);

            } else if (nodo instanceof ASTPri_PS) {

                // Obtenemos el nombre del semaforo
                String nombre = ((ASTIdentificador) nodo.jjtGetChild(0)).getNombre();

                // Obtenemos de la pila el semaforo
                DatoSemaforo semaforo = (DatoSemaforo) getContexto().get(nombre);

                // Aplicamos la operacion pS (Signal)
                semaforo.senal();

            } else if (nodo instanceof ASTPri_PE) {

                // Obtenemos el nombre del semaforo
                String nombre = ((ASTIdentificador) nodo.jjtGetChild(0)).getNombre();

                // Obtenemos de la pila el semaforo
                DatoSemaforo semaforo = (DatoSemaforo) getContexto().get(nombre);

                // Aplicamos la operacion pE (wait)
                if (semaforo.esperar()) // como la operacion dio Esperar=true no avanzamos con el
                // siguiente
                {
                    avanzarConElSiguienteNodo = true;
                }

            } else if (nodo instanceof ASTProcedimientoUsuarioHilo) {

                // generamos la subtarea y la agregamos al planificador
                PlanificadorUtils.getPlanificador().agregarTarea(
                        new TareaCompuestaProcUsuarioHilo(nodo, getContexto()));

            } else {

                // creamos la subtarea
                TareaCompuesta tc = null;

                Debug.println("<<Tarea.java - Es una Tarea compuesta>>");

                if (nodo instanceof ASTIteracionIncondicional) {

                    Debug.println("<<generamos la subtarea del REPETIR>>");
                    // generamos la subtarea
                    tc = new TareaCompuestaIteracionIncond(nodo, getContexto());
                } else if (nodo instanceof ASTIteracionCondicional) {

                    Debug.println("<<generamos la subtarea del MIENTRAS>>");
                    // generamos la subtarea
                    tc = new TareaCompuestaIteracionCond(nodo, getContexto());
                } else if (nodo instanceof ASTSeleccion) {

                    Debug.println("<<generamos la subtarea del SELECCION>>");
                    // generamos la subtarea
                    tc = new TareaCompuestaSeleccion(nodo, getContexto());
                } else if (nodo instanceof ASTProcedimientoUsuario) {

                    Debug.println("<<generamos la subtarea del PROCEDIMIENTO USUARIO>>");
                    // generamos la subtarea
                    tc = new TareaCompuestaProcUsuario(nodo, getContexto());

                } else if (nodo instanceof ASTFuncionUsuario) {

                    Debug.println("<<generamos la subtarea del FUNCION USUARIO>>");
                    // generamos la subtarea
                    tc = new TareaCompuestaFuncUsuario(nodo, getContexto());
                } else {
                    Debug.println("<<es una TAREA COMPUESTA pero no fue instanciada>>");
                }

                /*
                 * else if (nodo instanceof ASTAsignacion) {
                 * 
                 * // generamos la subtarea tc = new
                 * TareaCompuestaAsignacion(nodo, getContexto());
                 * 
                 * } else if (nodo instanceof ASTExpresion) {
                 * 
                 * // generamos la subtarea tc = new
                 * TareaCompuestaExpresion(nodo, getContexto()); }
                 */
                // asignamos la subtarea
                if (!tc.finalizo()) {
                    setSubTarea(tc);
                }

            }
            if (avanzarConElSiguienteNodo) // dejamos apuntando al siguiente nodo para su interpretacion
            {
                getNodos().incrementarIndice();
            }
            // Incrementar nodos si no se ha suspendido la tarea(suspendida)

        }

    }

    /**
     * Retornamos si el nodo se puede interpretar directamente.
     *
     * @param nodo SimpleNode
     * @return boolean true si es interpretable
     */
    private boolean esInterpretable(SimpleNode nodo) {
        return !(nodo instanceof ComplexNode);
    }

    /**
     * Tiene nodos para interpretar.
     *
     * @return true, if successful
     */
    public boolean tieneNodosParaInterpretar() {

        return haySubTarea() || getNodos().hayOtroNodo();
    }

    /**
     * Interpreta el nodo
     *
     * @param nodo
     */
    protected void interpretar(SimpleNode nodo) {
        nodo.interpret();

    }
}
