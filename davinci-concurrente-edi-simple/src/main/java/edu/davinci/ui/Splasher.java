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
package edu.davinci.ui;

/*
 * @(#)Splasher.java  2.0  January 31, 2004
 *
 * Copyright (c) 2003-2010 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 *
 * This software is in the public domain.
 * You are free to use, adapt, copy and license this work
 * without having to attribute to Werner Randelshofer.
 */

/**
 * 
 * @author Werner Randelshofer
 */
public class Splasher {
	/**
	 * Shows the splash screen, launches the application and then disposes the
	 * splash screen.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		SplashWindow.splash(Splasher.class.getResource("images/splash/splash.png"));
		SplashWindow.invokeMain("edu.davinci.ui.EditorSencillo", args);
		SplashWindow.disposeSplash();
	}

}
