package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class Brique {
	
	public static final int UPSIDE = 0;
	public static final int SIDE = 1;
	private Rectangle2D.Double brick;
	private int x, y, width, height, nbCoups;
	
	public void draw(Graphics2D g){
		if(nbCoups>1)
			g.setColor(Color.red);
		else
			g.setColor(Color.orange);
		g.fill(brick);
		g.setPaint(Color.black);
		g.draw(brick);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString(){
		return x+" - "+y;
	}

	public int getNbCoups() {
		return nbCoups;
	}
	
	public void setNbCoups(int nb) {
		nbCoups = nb;
	}

}
