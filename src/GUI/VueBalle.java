package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import Model.Balle;

/**
 * Vue d'une Balle
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez & Nicolas Belleme
 */
public class VueBalle implements Vue{

	private Ellipse2D.Double balle;
	
	public VueBalle(Balle ball){
		balle = new Ellipse2D.Double(500, 500, ball.getSize(), ball.getSize());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		 Graphics2D g2d = (Graphics2D) g;
		 if (balle != null)
	      {
	         g2d.setColor(Color.RED);
	         g2d.fill(balle);
	      }
		
	}

	/**
	 * Définit les bordures de la balle dans la vue
	 * @param newx position en X
	 * @param newy position en Y
	 * @param size largeur de la balle
     */
	public void setFrame(double newx, double newy, int size) {
		balle.setFrame(newx, newy, size, size);
	}
}
