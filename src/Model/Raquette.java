package Model;


import java.awt.Graphics2D;

import GUI.VueRaquette;

public class Raquette
{
   private VueRaquette thisRacket;
   private int width, height;       
   private Terrain pan;
   private int score = 0;
   

private int posx;
   
   private int startx;
   private int starty;
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
   
   public int getStartX(){
	   return startx;
   }
   
   public int getStartY(){
	   return starty;
   }

   public void move(int x)
   {
         posx = x-width/2;           
         thisRacket.setFrame(posx, pan.getVueTerrain().getHeight()-height, width, height);
         //a revoir
         pan.getVueTerrain().paint();
   }
   
   public int getX(){
	   return this.posx;
   }
   
   public void setX(int x){
	   this.posx = x;

   }
   
   
   public int getSize(){
	   return this.width;
   }


	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}

	public void paint(Graphics2D g2d, int i) {
		thisRacket.paintComponentRaquette(g2d, i);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public Boolean isItPlayerRaquette(){
		return this.equals(pan.getPlayerRacket());
	}
}
