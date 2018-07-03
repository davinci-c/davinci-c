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
import java.awt.Image;
import java.awt.Toolkit;

import edu.davinci.ciudad.Ciudad;
import edu.davinci.ciudad.Direccion;
import edu.davinci.ciudad.RobotBaseImpl;

public class RobotSimpleGuiImpl extends RobotBaseImpl {

	private Image representacion;
	private Dimension tamano;
	private Recorrido recorrido;

	private int anchoPredeterminado;
	private int altoPredeterminado;

	public RobotSimpleGuiImpl(String nombre, final Ciudad ciudad) {
		super(nombre, ciudad);

		tamano = new Dimension(10, 10);
		if (ciudad.getCantidadRobots() == 0) {
			representacion = getImage("/edu/davinci/ui/images/robot1.png");

		} else if (ciudad.getCantidadRobots() == 1) {
			representacion = getImage("/edu/davinci/ui/images/robot2.png");

		} else if (ciudad.getCantidadRobots() == 2) {
			representacion = getImage("/edu/davinci/ui/images/robot3.png");

		} else if (ciudad.getCantidadRobots() == 3) {
			representacion = getImage("/edu/davinci/ui/images/robot4.png");

		} else if (ciudad.getCantidadRobots() == 4) {
			representacion = getImage("/edu/davinci/ui/images/robot5.png");

		} else
			representacion = getImage("/edu/davinci/ui/images/robot.png");
		// TODO poner diferentes colores

		// representacion = FilterColor.processImage(representacion);
		// BufferedImageFilter bif = new BufferedImageFilter(null);

		anchoPredeterminado = ((CiudadPanel) ciudad.getRepresentacionGrafica())
				.getAnchoPredeterminado();
		altoPredeterminado = ((CiudadPanel) ciudad.getRepresentacionGrafica())
				.getAltoPredeterminado();

		// esperamos que el panel se encuentre creado en el contenedor y
		// tenga valores validos
		while (ciudad.getRepresentacionGrafica().getHeight() == 0) {
			try {

				Thread.sleep(1);
			} catch (InterruptedException e) {

			}
		}

		float alto = ciudad.getRepresentacionGrafica().getHeight();
		float ancho = ciudad.getRepresentacionGrafica().getWidth();

		// porcentaje para escalar la imagen
		float aumentoY = alto / altoPredeterminado;
		float aumentoX = ancho / anchoPredeterminado;

		recorrido = new Recorrido((int) (getPosicionX(avenida) * aumentoX) + 5,
				(int) (getPosicionY(calle) * aumentoY) + 5, ciudad.getCantidadRobots() + 1);

	}

	private Image getImage(String name) {

		return Toolkit.getDefaultToolkit().getImage(RobotSimpleGuiImpl.class.getResource(name));

	}

	public void setRepresentacion(Image representacion) {
		this.representacion = representacion;
	}

	public Image getRepresentacion() {
		return representacion;
	}

	public void setTamano(Dimension tamano) {
		this.tamano = tamano;
	}

	public Dimension getTamano() {
		return tamano;
	}

	/**
	 * Dibuja el robot
	 * 
	 * @param h
	 * @param w
	 * 
	 * @param grafico
	 * @param altoPredeterminado
	 * @param anchoPredeterminado
	 * @param panel
	 */
	/*
	 * public void dibujar(int w, int h, Graphics2D grafico) {
	 * 
	 * // porcentaje para escalar la imagen float aumentoY = (float) h /
	 * altoPredeterminado; float aumentoX = (float) w / anchoPredeterminado;
	 * 
	 * // sizeX *= aumentoX; sizeY *= aumentoY; int alto = (int) (tamano.height
	 * * aumentoY); int ancho = (int) (tamano.width * aumentoX);
	 * 
	 * // System.out.println("Y:" + aumentoY + " X:" + aumentoX);
	 * 
	 * // re-posiciono int posiciony = (int) (getPosicionY(calle) * aumentoY);
	 * int posicionx = (int) (getPosicionX(avenida) * aumentoX);
	 * 
	 * grafico.drawImage(representacion, posicionx, posiciony, ancho, alto,
	 * null);
	 * 
	 * }
	 */
	private int getPosicionX(int avenida) {
		// retorna la posicion en la ciudad en funcion de la avenida
		return 5 + ((avenida - 1) * 40) + (avenida * 2);
	}

	private int getPosicionY(int calle) {
		// retorno la posicion en la ciudad en funcion de la calle
		// TODO Obtener el tamano de la ciudad (Panel)
		return 385 - ((calle - 1) * 40) - (calle * 2);
		// return (ciudad.getRepresentacionGrafica().getHeight() - 12) - ((calle
		// - 1) * 40);
	}

	/**
	 * Setea las posiciones donde se encuentra el robot
	 * 
	 * @param av
	 * @param ca
	 */
	@Override
	public void posicionarse(int av, int ca) {
		avenida = av;
		calle = ca;

		// porcentaje para escalar la imagen
		float aumentoY = (float) ciudad.getRepresentacionGrafica().getHeight() / altoPredeterminado;
		float aumentoX = (float) ciudad.getRepresentacionGrafica().getWidth() / anchoPredeterminado;

		recorrido.saltar((int) (getPosicionX(avenida) * aumentoX) + 5,
				(int) (getPosicionY(calle) * aumentoY) + 5);

		// redibujo
		ciudad.getRepresentacionGrafica().repaint();

		verificarEstadoDelRobot();
	}

	/**
	 * Avanza al robot en la direccion que se encuentra.
	 */
	@Override
	public void mover() {

		if (direccion.getSentido() == Direccion.NORTE)
			calle++;
		else if (direccion.getSentido() == Direccion.SUR)
			calle--;
		else if (direccion.getSentido() == Direccion.ESTE)
			avenida++;
		else if (direccion.getSentido() == Direccion.OESTE)
			avenida--;

		// porcentaje para escalar la imagen
		float aumentoY = (float) ciudad.getRepresentacionGrafica().getHeight() / altoPredeterminado;
		float aumentoX = (float) ciudad.getRepresentacionGrafica().getWidth() / anchoPredeterminado;

		recorrido.mover((int) (getPosicionX(avenida) * aumentoX) + 5,
				(int) (getPosicionY(calle) * aumentoY) + 5);

		((CiudadPanel) ciudad.getRepresentacionGrafica()).insertarRecorrido(recorrido);
		// redibujo
		ciudad.getRepresentacionGrafica().repaint();

		verificarEstadoDelRobot();
                
	}

	@Override
	public void tomarFlor() {
		// hereda el comportamiento
		super.tomarFlor();

		// redibujamos la ciudad
		ciudad.getRepresentacionGrafica().repaint();
	}

	@Override
	public void tomarPapel() {
		// hereda el comportamiento
		super.tomarPapel();

		// redibujamos la ciudad
		ciudad.getRepresentacionGrafica().repaint();

	}

	@Override
	public void depositarFlor() {
		// hereda el comportamiento
		super.depositarFlor();

		// redibujamos la ciudad
		ciudad.getRepresentacionGrafica().repaint();
	}

	@Override
	public void depositarPapel() {
		// hereda el comportamiento
		super.depositarPapel();

		// redibujamos la ciudad
		ciudad.getRepresentacionGrafica().repaint();
	}

}
