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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import edu.davinci.ciudad.Elemento;
import edu.davinci.ciudad.Objeto;
import edu.davinci.ciudad.Robot;

/**
 * The DukeAnim class displays an animated gif with a transparent background.
 */
public class CiudadPanel extends JPanel implements ImageObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6850785960130580738L;

	private Image ciudad;
	private int anchoPredeterminado;
	private int altoPredeterminado;
	private BufferedImage bimg;

	private List<Robot> robots;
	private Objeto flores;
	private Objeto papeles;
	private Objeto obstaculos;

	/**
	 * @param obstaculos
	 * @param papeles
	 * @param flores
	 * 
	 */
	public CiudadPanel(List<Robot> robots, Objeto flores, Objeto papeles, Objeto obstaculos) {
		// inicializamos con el tamano por defecto
		this(400, 400, robots, flores, papeles, obstaculos);
	}

	/**
	 * @param obstaculos
	 * @param papeles
	 * @param flores
	 * 
	 */
	public CiudadPanel(int ancho, int alto, List<Robot> robots, Objeto flores, Objeto papeles,
			Objeto obstaculos) {
		anchoPredeterminado = ancho;
		altoPredeterminado = alto;

		BufferedImage ciudad_original = null;// =
												// getImage(String.format("images%sciudad.png",
												// File.separator));

		this.robots = robots;
		this.flores = flores;
		this.papeles = papeles;
		this.obstaculos = obstaculos;

		try {
			String rutaImagen = "/edu/davinci/ui/images/ciudad.png";

			if (CiudadPanel.class.getResource(rutaImagen) != null) {

				ciudad_original = ImageIO.read(CiudadPanel.class.getResource(rutaImagen));

			} else {

				System.out.println("no existe ruta imagen ciudad:" + rutaImagen);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		ciudad = ciudad_original;
		// getCiudadConRecorrido(anchoPredeterminado, altoPredeterminado,
		// ciudad_original,robots);
	}

	/**
	 * 
	 * @param w
	 * @param h
	 * @param g2
	 */
	private void drawImages(int w, int h, Graphics2D g2) {
		// insertmos el recorrido a la ciudad
		// Image c = getCiudadConRecorrido(w, h, ciudad, robots);
		// dibujamos la ciudad
		synchronized (this) {
			g2.drawImage(ciudad, 0, 0, w, h, this);

			// dibujamos los obstaculos
			for (Elemento obstaculo : obstaculos.lista()) {
				dibujar(obstaculo, obstaculos.getImagen(), w, h, g2);
			}

			// dibujamos los papeles
			for (Elemento papel : papeles.lista()) {
				dibujar(papel, papeles.getImagen(), w, h, g2);
			}

			// dibujamos las flores
			for (Elemento flor : flores.lista()) {
				dibujar(flor, flores.getImagen(), w, h, g2);
			}

			// dibujamos los robots

			for (Robot robot : robots) {
				dibujar(((RobotSimpleGuiImpl) robot),
						((RobotSimpleGuiImpl) robot).getRepresentacion(), w, h, g2);
				// Se cambia la manera como se dibuja el robot
				// ((RobotSimpleGuiImpl) robot).dibujar(w, h, g2);
			}
		}

	}

	/**
	 * Inserta en la imagen de la ciudad el recorrido
	 * 
	 * @param recorrido
	 * @param ciudad
	 */
	public void insertarRecorrido(Recorrido recorrido) {
		int w = getWidth();
		int h = getHeight();

		//
		BufferedImage imagen = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		//
		imagen.getGraphics().drawImage(ciudad, 0, 0, w, h, null);

		//
		recorrido.paint(imagen.getGraphics());

		ciudad = imagen;

	}

	public int getAnchoPredeterminado() {
		return anchoPredeterminado;
	}

	public void setAnchoPredeterminado(int anchoPredeterminado) {
		this.anchoPredeterminado = anchoPredeterminado;
	}

	public int getAltoPredeterminado() {
		return altoPredeterminado;
	}

	public void setAltoPredeterminado(int altoPredeterminado) {
		this.altoPredeterminado = altoPredeterminado;
	}

	private void dibujar(Elemento elemento, Image image, int w, int h, Graphics2D g2) {
		// porcentaje para escalar la imagen
		float aumentoY = (float) h / altoPredeterminado;
		float aumentoX = (float) w / anchoPredeterminado;

		// sizeX *= aumentoX; sizeY *= aumentoY;
		int height = (int) (10 * aumentoY);
		int width = (int) (10 * aumentoX);

		// System.out.println("Y:" + aumentoY + " X:" + aumentoX);

		// re-posiciono
		int posiciony = (int) (getPosicionY(elemento.getCalle()) * aumentoY);
		int posicionx = (int) (getPosicionX(elemento.getAvenida()) * aumentoX);

		g2.drawImage(image, posicionx, posiciony, width, height, this);

	}

	private float getPosicionX(int avenida) {
		// retorna la posicion en la ciudad en funcion de la avenida
		return 5 + ((avenida - 1) * 40) + (avenida * 2);
	}

	private float getPosicionY(int calle) {
		// retorno la posicion en la ciudad en funcion de la calle
		// TODO Obtener el tamano de la ciudad (Panel)
		return 385 - ((calle - 1) * 40) - (calle * 2);
		// return (ciudad.getRepresentacionGrafica().getHeight() - 12) - ((calle
		// - 1) * 40);
	}

	/**
	 * 
	 * @param w
	 * @param h
	 * @return
	 */
	private Graphics2D createGraphics2D(int w, int h) {
		Graphics2D g2 = null;
		if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
			bimg = (BufferedImage) createImage(w, h);
		}
		g2 = bimg.createGraphics();
		g2.setBackground(getBackground());
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.clearRect(0, 0, w, h);
		return g2;
	}

	/**
	 * 
	 */
	@Override
	public void paint(Graphics g) {
		try {
			Dimension d = getSize();
			Graphics2D g2 = createGraphics2D(d.width, d.height);
			drawImages(d.width, d.height, g2);
			g2.dispose();
			g.drawImage(bimg, 0, 0, this);
		} catch (ConcurrentModificationException cme) {
			// System.out.println("########## U P S ###########");
		}
	}

}
