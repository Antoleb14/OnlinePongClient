package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import Model.Raquette;

public class VueRaquette implements Vue{

	private RoundRectangle2D.Double thisRacket;
	private Raquette racket;
	private Color[] colors;
	
	public VueRaquette(Raquette racket){
		this.racket = racket;
		thisRacket = new RoundRectangle2D.Double(racket.getStartX(), racket.getStartY(), racket.getWidth(), racket.getHeight(), 5, 5);
		colors = new Color[4];
		colors[0] = Color.red;
		colors[1] = Color.blue;
		colors[2] = Color.yellow;
		colors[3] = Color.green;
	}
	
	@Override
	public void paintComponent(Graphics g){}
	
	public void paintComponentRaquette(Graphics g, int i) {
		Graphics2D g2d = (Graphics2D) g;
	  if (thisRacket != null)
      {
         g2d.setColor(colors[i]);
         g2d.fill(thisRacket);
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
