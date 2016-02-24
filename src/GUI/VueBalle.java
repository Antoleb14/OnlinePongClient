package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import Model.Balle;

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
	
	public double getX(){
		return balle.getX();
	}
	
	public double getY(){
		return balle.getY();
	}

	public void setFrame(double newx, double newy, int size, int size2) {
		balle.setFrame(newx, newy, size, size);
	}
}
