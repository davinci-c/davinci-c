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
package edu.davinci.interprete;

import java.io.FileInputStream;
import java.io.InputStream;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.ciudad.CiudadBaseImpl;
import edu.davinci.lenguaje.Debug;
import edu.davinci.lenguaje.ParseException;
import edu.davinci.lenguaje.Parser;
import edu.davinci.planificador.Planificador;
import edu.davinci.ui.OpcionesInterprete;

/**
 * Clase que proporciona la funcionalidad necesaria para interpretar codigo del
 * lenguaje Davinci Concurrente
 * 
 * <p>
 * <strong>Fecha:</strong> 08/03/2011
 * </p>
 * 
 * @author Daniel E., Aguil Mallea
 */
public class Interprete {

	/** The codigo fuente. */
	private InputStream codigoFuente;

	/** The parser. */
	private Parser parser;

	/** The AST debug. */
	private boolean ASTDebug;

	/** The lexer debug. */
	private boolean lexerDebug;

	/** The planificador. */
	private Planificador planificador;

	/** The ciudad. */
	private static Ciudad ciudad;

	/**
	 * CONSTRUCTOR.
	 */
	public Interprete() {
		ASTDebug = false;
		lexerDebug = false;
	}

	/**
	 * Sets the aST modo debug.
	 * 
	 * @param debug
	 *            the new aST modo debug
	 */
	public void setASTModoDebug(boolean debug) {
		ASTDebug = debug;
	}

	/**
	 * Sets the lexer modo debug.
	 * 
	 * @param debug
	 *            the new lexer modo debug
	 */
	public void setLexerModoDebug(boolean debug) {
		lexerDebug = debug;
	}

	/**
	 * Inicializar parser.
	 * 
	 * @param codigoFuente
	 */
	private void inicializarParser(InputStream codigoFuente) {

		parser = new Parser(codigoFuente);

		// parser = new Parser();
	}

	/**
	 * Gets the ejecucion.
	 * 
	 * @return the ejecucion
	 * @throws ParseException
	 */
	public Ejecutor getEjecutor() throws ParseException {
		// creamos el parser con el flujo que tenemos
		inicializarParser(codigoFuente);

		// seteamos el modo debug del parser
		Debug.setDebug(ASTDebug);

		// seteamos el modo debug del lexer
		if (lexerDebug)
			parser.enable_tracing();
		else
			parser.disable_tracing();

		// retornamos la instancia que contiene el codigo interpretable
		return new Ejecutor(parser.Compilar(), planificador, getCiudad());
	}

	/**
	 * Sets the codigo fuente.
	 * 
	 * @param codigoFuente
	 *            the new codigo fuente
	 */
	public void setCodigoFuente(InputStream codigoFuente) {
		this.codigoFuente = codigoFuente;
	}

	/**
	 * Sets the planificador.
	 * 
	 * @param planificador
	 *            the new planificador
	 */
	public void setPlanificador(Planificador planificador) {
		this.planificador = planificador;
	}

	/**
	 * Metodo estico para retornar la ciudad que ha sido seteada.
	 * 
	 * @return Ciudad
	 */
	public static Ciudad getCiudad() {
		if (null == ciudad)
			throw new RuntimeException("La ciudad no ha sido inicializada");
		return ciudad;
	}

	/**
	 * Setea la ciudad a ser utilizada por el interprete.
	 * 
	 * @param c
	 *            the new ciudad
	 */
	public void setCiudad(Ciudad c) {
		ciudad = c;
	}

	public static void main(String[] args) {
		try {

			System.out.println("Utilizando el directorio temporal "
					+ OpcionesInterprete.getDirectorioTrabajo());

			if (!OpcionesInterprete.hayConfiguracion()) {
				System.out.println("generando configuracion por defecto...");
				OpcionesInterprete.generarConfiguracionPorDefecto();
			}

			// cargamos las configuraciones
			if (OpcionesInterprete.cargarConfiguracion()) {
				System.out.println("Configuraciones cargadas con exito...\n");
			}

			//InputStream codigoFuente = new FileInputStream(args[0]);
                        InputStream codigoFuente = new FileInputStream("C:\\Users\\jonatan\\Dropbox\\funcion.txt");
//                        InputStream codigoFuente = new FileInputStream("/home/jonatan.mamani/Dropbox/funcion.txt");


			//
			Interprete interprete = new Interprete();

			//
			Ciudad ciudad = new CiudadBaseImpl();

			//
			ciudad.permitirRobotsSuperpuestos(OpcionesInterprete.getRobotSuperpuesto());

			//
			interprete.setASTModoDebug(OpcionesInterprete.getDepurarAst());

			//
			interprete.setLexerModoDebug(OpcionesInterprete.getDepurarLexer());

			//
			interprete.setCiudad(ciudad);

			//
			interprete.setPlanificador(OpcionesInterprete.getInstanciaPlanificador());

			//
			interprete.setCodigoFuente(codigoFuente);

			//
			Ejecutor ejecucion = interprete.getEjecutor();
                        
                        
			//
			ejecucion.setVelocidadRetardo(OpcionesInterprete.getRetardo());

			//
			ejecucion.start();

		} catch (Exception excepcion) {
			System.out.println("excepcion: " + excepcion.getMessage());
		} catch (Error error) {
			System.out.println("error: " + error.getMessage());
		}
	}
}
