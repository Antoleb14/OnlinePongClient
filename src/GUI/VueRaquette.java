package GUI;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import Model.Raquette;

public class VueRaquette implements Vue{

	private RoundRectangle2D.Double thisRacket;
	private Raquette racket;
	
	public VueRaquette(Raquette racket){
		this.racket = racket;
		thisRacket = new RoundRectangle2D.Double(racket.getStartX(), racket.getStartY(), racket.getWidth(), racket.getHeight(), 5, 5);

	}
	
	@Override
	public void paintComponent(Graphics g){}
	
	public void paintComponentRaquette(Graphics g, int i) {
		Graphics2D g2d = (Graphics2D) g;
	  if (thisRacket != null)
      {
		 Composite c = g2d.getComposite();
		 if(!racket.isItPlayerRaquette()){
			 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
		 }
		 

		 
         g2d.setColor(StartGui.colors[i]);
        
         g2d.fill(thisRacket);
         g2d.setComposite(c);
      }
		
	}
	
	public double getX(){
		return thisRacket.getX();
	}
	
	public double getY(){
		return thisRacket.getY();
	}

	public void setFrame(double newx, double newy, int size, int size2) {
		thisRacket.setFrame(newx, newy, size, size);
	}

}
