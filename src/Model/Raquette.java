package Model;


import java.awt.Graphics2D;

import GUI.VueRaquette;

/**
 * Classe Raquette du modèle
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez et Nicolas Belleme
 */
public class Raquette
{
    private VueRaquette thisRacket;
    private int width, height;
    private Terrain pan;
    private int score = 0;
    private int posx;
   
    private int startx;
    private int starty;

    /**
     * Constructeur de la classe
     * @param terrain le terrain sur lequel se déroule le jeu
     */
    public Raquette(Terrain terrain)
    {
	  pan = terrain;  
      width = 150;
      height = 10;
      
      startx = (int)(Math.random() * Terrain.panelWidth-width);
      starty = Terrain.panelHeight-height;
      thisRacket = new VueRaquette(this);
      
      thisRacket.setFrame(startx, (Terrain.panelHeight)-height, width, height);
      
    }

    /**
     * Getter de la position en X au départ de la raquette
     * @return int position en X
     */
   public int getStartX(){
	   return startx;
   }

    /**
     * Getter de la position en Y au départ de la raquette
     * @return int position en Y de la raquette
     */
   public int getStartY(){
	   return starty;
   }


    /**
     * Permet de bouger la raquette sur l'axe des X
     * @param x nouvelle position en X de la raquette
     */
    public void move(int x)
   {
         posx = x-width/2;           
         thisRacket.setFrame(posx, pan.getVueTerrain().getHeight()-height, width, height);
         //a revoir
         pan.getVueTerrain().paint();
   }

    /**
     * Renvoie la taille de la raquette
     * @return int taille de la raquette
     */
   public int getSize(){
	   return this.width;
   }

    /**
     * Renvoie la hauteur de la raquette
     * @return int hauteur de la raquette
     */
	public int getHeight() {
		return this.height;
	}

    /**
     * Peindre le composant sur la vue
     * @param g2d Graphics
     * @param i raquette en cours
     */
	public void paint(Graphics2D g2d, int i) {
		thisRacket.paintComponentRaquette(g2d, i);
	}

    /**
     * Récupère le score du joueur en cours
     * @return int le score
     */
	public int getScore() {
		return score;
	}

    /**
     * Setter du score d'un joueur
     * @param score score du joueur
     */
	public void setScore(int score) {
		this.score = score;
	}

    /**
     * Méthode pour savoir si la raquette en cours est celle du joueur
     * @return boolean vrai ou faux
     */
	public Boolean isItPlayerRaquette(){
		return this.equals(pan.getPlayerRacket());
	}
}
