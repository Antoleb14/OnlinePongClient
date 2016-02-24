package Model;

import java.awt.Graphics2D;

import GUI.VueBalle;

public class Balle
{
   private 	VueBalle balle;
   private int size;
    private Terrain pan;
   
   private double x,y;
   
   public Balle(Terrain panel)
   {    
      balle = new VueBalle(this);
      size=20;
      pan = panel;
   }

	public double getSize() {
		return size;
	}
	
	public void setPosition(Double x, Double y){
		this.x=x;
		this.y=y;
		balle.setFrame(this.x, this.y, size, size);
		pan.paint();
	}

	public void paint(Graphics2D g2d) {
		balle.paintComponent(g2d);		
	}
}
