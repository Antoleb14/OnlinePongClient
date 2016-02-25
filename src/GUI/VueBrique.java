package GUI;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Classe de la vue d'une brique
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez et Nicolas Belleme
 */
public class VueBrique implements Vue {

	private Color c;
	private Rectangle2D.Double brick;

	/**
	 * Constructeur de classe
	 * @param x position en X de la brique
	 * @param y position en Y de la brique
	 * @param width largeur de la brique
	 * @param height hauteur de la brique
	 * @param c Couleur de la brique
	 */
	public VueBrique(int x, int y, int width, int height, Color c){
		this.c = c;
		brick = new Rectangle2D.Double(x, y, width, height);
	}

	@Override
	public void paintComponent(Graphics g2d){
		Graphics2D g = (Graphics2D) g2d;
		g.setColor(c);
		g.fill(brick);
		g.setPaint(Color.black);
		g.draw(brick);
	}

	/**
	 * Modifie la couleur d'une brique lors d'un impact
	 * @param color nouvelle couleur à attribuer
     */
	public void setColor(Color color) {
		this.c = color;
	}
}
