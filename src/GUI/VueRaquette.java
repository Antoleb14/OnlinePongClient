package GUI;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import Model.Raquette;

/**
 * Vue d'une raquette
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez et Nicolas Belleme
 */
public class VueRaquette implements Vue{

	private RoundRectangle2D.Double thisRacket;
	private Raquette racket;

	/**
	 * Constructeur de la vue
	 * @param racket une Raquette du modèle
     */
	public VueRaquette(Raquette racket){
		this.racket = racket;
		thisRacket = new RoundRectangle2D.Double(racket.getStartX(), racket.getStartY(), racket.getSize(), racket.getHeight(), 5, 5);

	}
	
	@Override
	public void paintComponent(Graphics g){}

	/**
	 * Méthode pour peindre la raquette avec sa couleur correspondante sur la vue
	 * @param g Graphics
	 * @param i la raquette en cours afin de lui attribuer une couleur
     */
	public void paintComponentRaquette(Graphics g, int i) {
		Graphics2D g2d = (Graphics2D) g;
		if (thisRacket != null){
			Composite c = g2d.getComposite();
			if(!racket.isItPlayerRaquette()){
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
			}

			g2d.setColor(StartGui.colors[i]);
			g2d.fill(thisRacket);
			g2d.setComposite(c);
		}
	}

	public void setFrame(double newx, double newy, int size, int size2) {
		thisRacket.setFrame(newx, newy, size, size2);
	}

}
