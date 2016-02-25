package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import GUI.VueBrique;

/**
 * Classe brique du modèle
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez et Nicolas Belleme
 */
public class Brique {
	
	public static final int UPSIDE = 0;
	public static final int SIDE = 1;
	private int x, y, width, height, nbCoups;
	public static Color[] colors = {Color.yellow, Color.orange, Color.red};
	private VueBrique drawBrique;

	/**
	 * Constructeur de la classe brique
	 * @param posX position en X de la brique
	 * @param posY position en Y de la brique
	 * @param nbc nombre de coups nécessaire pour détruire la brique
     */
	public Brique(int posX, int posY, int nbc){
		width = 100;
		height = 30;
		x= posX;
		y = posY;
		nbCoups = nbc;
		drawBrique = new VueBrique(x, y, width, height, colors[nbCoups-1]);
	}

	/**
	 * Appel aux méthode de dessin de la brique
	 * @param g Graphics
     */
	public void draw(Graphics2D g){
		drawBrique.setColor(colors[nbCoups-1]);
		drawBrique.paintComponent(g);
	}
	
	public String toString(){
		return x+" - "+y;
	}

	/**
	 * Setter du nombre de coups nécessaire à la destruction d'une brique
	 * @param nb nombre de coups restants pour casser une brique
     */
	public void setNbCoups(int nb) {
		nbCoups = nb;
	}

}
