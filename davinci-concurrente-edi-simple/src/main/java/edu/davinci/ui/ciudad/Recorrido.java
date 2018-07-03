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
package edu.davinci.ui.ciudad;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

public class Recorrido extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -101782604501645712L;
	private static Color COLOR = null;
	private static BasicStroke estilo = new BasicStroke(2f, BasicStroke.CAP_ROUND,
			BasicStroke.JOIN_ROUND);
	// 2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, new float[] { 4f
	// }, 0f);
	private GeneralPath path = new GeneralPath();
	private Color color;

	// private int puntos;

	// private boolean seMovio;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Recorrido(int av, int ca, int color) {
		if (color == 1)
			COLOR = Color.red;
		else if (color == 2)
			COLOR = Color.green;
		else if (color == 3)
			COLOR = Color.cyan;
		else if (color == 4)
			COLOR = Color.yellow;
		else {
			COLOR = new Color((new Random()).nextInt(255 * 255 * 255));

		}
		this.color = COLOR;
		// convertir avenidas y calles por posiciones
		path.moveTo(av, ca);

		// guardo la cantidad de puntos
		// puntos = 1;
	}

	public void mover(int av, int ca) {

		path.lineTo(av, ca);

		// guardo la cantidad de puntos
		// puntos++;
		// seMovio = true;
	}

	public void saltar(int av, int ca) {

		path.moveTo(av, ca);

		// guardo la cantidad de puntos
		// puntos++;

		// seMovio = false;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(color);
		// g2d.setComposite(AlphaComposite.Xor);
		g2d.setStroke(estilo);
		g2d.draw(path);

		// System.out.println("armando nuevo path:" + puntos);
		// puntos = 0;
		Point2D p = path.getCurrentPoint();
		path = new GeneralPath();
		path.moveTo(p.getX(), p.getY());
		// if (seMovio) {
		// Point2D p = path.getCurrentPoint();
		//
		// path.reset();
		//
		// path.moveTo(p.getX(), p.getY());
		// }
	}
}
