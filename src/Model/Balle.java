package Model;

import java.awt.Graphics2D;

import GUI.VueBalle;

/**
 * Classe Balle du modèle
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez & Nicolas Belleme
 */
public class Balle
{
   private 	VueBalle balle;
   private int size;
    private Terrain pan;
   
   private double x,y;

	/**
	 * Constructeur de Balle
	 * @param panel le Terrain sur lequel la balle se déplace
     */
   public Balle(Terrain panel)
   {    
      balle = new VueBalle(this);
      size=20;
      pan = panel;
   }

	/**
	 * Récupère la taille de la balle
	 * @return double taille de la balle
     */
	public double getSize() {
		return size;
	}

	/**
	 * Définit la position de la Balle
	 * @param x position de la balle en X
	 * @param y position de la balle en Y
     */
	public void setPosition(Double x, Double y){
		this.x=x;
		this.y=y;
		balle.setFrame(this.x, this.y, size);
		pan.paint();
	}

	/**
	 * Méthode pour repeindre la balle dans notre vue
	 * @param g2d Graphics2D
     */
	public void paint(Graphics2D g2d) {
		balle.paintComponent(g2d);		
	}
}
