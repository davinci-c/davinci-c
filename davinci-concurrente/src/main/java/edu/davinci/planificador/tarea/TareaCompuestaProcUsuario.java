/*******************************************************************************
 * Copyright (c) 2011, 2013  - Daniel, Aguil Mallea.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Daniel, Aguil Mallea - initial API and implementation
 ******************************************************************************/
package edu.davinci.planificador.tarea;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import edu.davinci.lenguaje.ASTParametroFormalEntrada;
import edu.davinci.lenguaje.ASTParametroFormalEntradaSalida;
import edu.davinci.lenguaje.ASTParametroFormalSalida;
import edu.davinci.lenguaje.ASTParametrosFormales;
import edu.davinci.lenguaje.ASTProcedimientoUsuario;
import edu.davinci.lenguaje.Dato;
import edu.davinci.lenguaje.DatoEntero;
import edu.davinci.lenguaje.DatoLogico;
import edu.davinci.lenguaje.DatoTexto;
import edu.davinci.lenguaje.DefinicionProcedimientos;
import edu.davinci.lenguaje.Node;
import edu.davinci.lenguaje.SimpleNode;
import edu.davinci.utils.Resource;

/**
 * Clase encargada de representar al "procedimiento"
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class TareaCompuestaProcUsuario extends TareaCompuesta {

	/** The valor parametros reales. */
	private List<Dato> valorParametrosReales;

	/**
	 * Constructor de la tarea compuesta Proceso de Usuario.
	 * 
	 * @param nodo
	 *            Nodo que invoca al proceso
	 * @param contextoPadre
	 *            pila con la que se invoca al proceso
	 */
	public TareaCompuestaProcUsuario(SimpleNode nodo, Hashtable<String, Object> contextoPadre) {
		super(obtenerNodoProceso(nodo), crearPilaProceso(contextoPadre));
		setNombre(((ASTProcedimientoUsuario) nodo).getNombre());
		// verificamos si hay parametros formales
		if (hayParametrosReales(nodo)) {

			// interpretamos los parametros reales
			interpretarParametrosReales(nodo.getChildren());

			// establecemos la correspondencia con los parametros formales

			// Traemos los parametros formales
			Node[] parametrosFormales = ((ASTParametrosFormales) getNodos().getNodo())
					.getChildren();

			// traemos los nombres de los parametros reales
			List<String> nombreParametrosReales = obtenerNombreParametrosReales(nodo);

			establecerCorrespondenciaEntreParametros(parametrosFormales, nombreParametrosReales,
					getContexto(), contextoPadre, valorParametrosReales);

			// apuntamos al proximo nodo
			getNodos().incrementarIndice();
		}

		if (!tieneNodosParaInterpretar())
			finalizar();
	}

	/**
	 * Estable la correspondencia entre los parametros reales y los parametros
	 * formales.
	 * 
	 * @param parametrosFormales
	 *            parametros de la definicion del proceso
	 * @param nombreParametrosReales
	 *            nombre de los parametros reales
	 * @param contexto
	 *            pila del proceso
	 * @param contextoPadre
	 *            pila del que llama al proceso
	 * @param valores
	 *            valores de los parametros reales
	 */
	private void establecerCorrespondenciaEntreParametros(Node[] parametrosFormales,
			List<String> nombreParametrosReales, Hashtable<String, Object> contexto,
			Hashtable<String, Object> contextoPadre, List<Dato> valores) {

		String nombre;
		Dato valor;
		for (int i = 0; i < parametrosFormales.length; i++) {

			// verificamos el tipo de parametro
			if (esDeEntradaSalida(parametrosFormales[i])) {

				// obtenemos el nombre del parametro formal
				nombre = ((ASTParametroFormalEntradaSalida) parametrosFormales[i]).getNombre();

				// obtenemos la referencia de la pila
				valor = (Dato) contextoPadre.get(nombreParametrosReales.get(i));

			} else if (esDeSalida(parametrosFormales[i])) {
				// obtenemos el nombre del parametro formal
				nombre = ((ASTParametroFormalSalida) parametrosFormales[i]).getNombre();

				// obtenemos la referencia de la pila
				valor = (Dato) contextoPadre.get(nombreParametrosReales.get(i));

				// inicializamos el valor
				valor.setValorPorDefecto();

			} else if (esDeEntrada(parametrosFormales[i])) {
				// obtenemos el nombre del parametro formal
				nombre = ((ASTParametroFormalEntrada) parametrosFormales[i]).getNombre();
				// Creamos el nuevo tipo de dato
				if (valores.get(i) instanceof DatoEntero)
					valor = new DatoEntero();
				else if (valores.get(i) instanceof DatoTexto)
					valor = new DatoTexto();
				else
					valor = new DatoLogico();
				// seteamos el valor en la nueva referencia
				valor.setValor(valores.get(i).getValor());
			} else {
				throw new RuntimeException(Resource.getString("tarea.error.parametros"));
			}
			// ingresamos en la pila la referencia
			if (nombre == null || valor == null)
				nombre = "null";
			contexto.put(nombre, valor);
		}
	}

	/**
	 * Retorna el nombre de los parametros reales.
	 * 
	 * @param nodo
	 *            Nodo que invoca al proceso
	 * @return Lista con los nombres de los parametros reales
	 */
	private List<String> obtenerNombreParametrosReales(SimpleNode nodo) {
		return ((ASTProcedimientoUsuario) nodo).getNombresParametrosReales();
	}

	/**
	 * Retorna si hay parametros reales en el nodo que invoca al proceso.
	 * 
	 * @param nodo
	 *            Nodo que invoca al proceso
	 * @return retorna verdadero si hay parametros, caso contrario falso
	 */
	private boolean hayParametrosReales(SimpleNode nodo) {
		return nodo.getChildren() != null;
	}

	/**
	 * Interpreta los parametros reales.
	 * 
	 * @param children
	 *            Nodos que representan los parametros a interpretar
	 */
	private void interpretarParametrosReales(Node[] children) {
		// inicilizamos el contenedor de los valores de los parametros reales
		valorParametrosReales = new ArrayList<Dato>();

		// recorremos los parametros reales
		for (int i = 0; i < children.length; i++) {
			// interpretamos el parametro
			interpretar((SimpleNode) children[i]);
			// guardamos el resultado de la expresion
			valorParametrosReales.add((Dato) SimpleNode.getUltimaOperacion());
		}
	}

	/**
	 * Retorna la definicion del proceso que se invoca.
	 * 
	 * @param nodo
	 *            Nodo que invoca al proceso
	 * @return retorna el nodo que contiene la definicion
	 */
	private static SimpleNode obtenerNodoProceso(SimpleNode nodo) {
		// traemos el nodo de la definicion del proceso
		return DefinicionProcedimientos.get(((ASTProcedimientoUsuario) nodo).getNombre());
	}

	/**
	 * Crea la pila para el proceso.
	 * 
	 * @param contexto
	 *            pila con la que se invoca al proceso
	 * @return retorna la pila que se usara en el proceso
	 */
	private static Hashtable<String, Object> crearPilaProceso(Hashtable<String, Object> contexto) {
		// Armamos la pila del proceso
		return new Hashtable<String, Object>(contexto);
	}

	/**
	 * Retorna si el nodo que me pasan como parametro es de Entrada.
	 * 
	 * @param nodo
	 * @return true, if successful
	 * @return
	 */
	private boolean esDeEntrada(Node nodo) {
		return (nodo instanceof ASTParametroFormalEntrada);
	}

	/**
	 * Retorna si el nodo que me pasan como parametro es de Salida.
	 * 
	 * @param nodo
	 * @return true, if successful
	 * @return
	 */
	private boolean esDeSalida(Node nodo) {
		return (nodo instanceof ASTParametroFormalSalida);
	}

	/**
	 * Retorna si el nodo que me pasan como parametro es de Entrada y Salida.
	 * 
	 * @param nodo
	 * @return true, if successful
	 * @return
	 */
	private boolean esDeEntradaSalida(Node nodo) {
		return (nodo instanceof ASTParametroFormalEntradaSalida);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.tarea.Tarea#ejecutar()
	 */
	@Override
	public void ejecutar() {
		// verificamos si hay nodos que interpretar
		if (tieneNodosParaInterpretar()) {
			// interpretamos
			super.ejecutar();
		} else {
			// marcamos para finalizar la tarea
			finalizar();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.davinci.planificador.tarea.TareaCompuesta#getLinea()
	 */
	@Override
	public int getLinea() {

		if (tieneNodosParaInterpretar())
			return ((SimpleNode) getNodos().getNodo()).jjtGetFirstToken().beginLine;
		else {
			int ultimo = getNodos().getNodos().size() - 1;
			return ((SimpleNode) getNodos().getNodos().get(ultimo)).jjtGetFirstToken().beginLine + 1;
		}
	}
}
