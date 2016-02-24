package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import GUI.VueBrique;

public class Brique {
	
	public static final int UPSIDE = 0;
	public static final int SIDE = 1;
	private int x, y, width, height, nbCoups;
	public static Color[] colors = {Color.yellow, Color.orange, Color.red};
	private VueBrique drawBrique;
	
	public Brique(int posX, int posY, int nbc){
		width = 100;
		height = 30;
		x= posX;
		y = posY;
		nbCoups = nbc;
		drawBrique = new VueBrique(x, y, width, height);
	}
	public void draw(Graphics2D g){
		drawBrique.paintComponent(g, colors[nbCoups-1]);	
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
