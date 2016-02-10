package Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import GUI.VueBalle;

public class Balle
{
   private 	VueBalle balle;
   private boolean threadStarted;
   private int size;
   private double speed;       
   private double deltax, deltay;
   private Terrain pan;
   
   private double newx, newy, oldx, oldy;
   private double acceleration = 1.05;
   
   private static Image img = new ImageIcon("bgball.png").getImage();
   
   public Balle(Terrain panel)
   {    
      balle = new VueBalle(this);
      newx=200;
      newy=200;
      pan = panel;
   }

	public double getSize() {
		return size;
	}
	
	public void setPosition(Double x, Double y){
		newx=x;
		newy=y;
		balle.setFrame(newx, newy, size, size);
		pan.paint();
	}

	public void paint(Graphics2D g2d) {
		balle.paintComponent(g2d);		
	}
}
