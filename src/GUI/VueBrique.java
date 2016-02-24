package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class VueBrique {

	private Rectangle2D.Double brick;
	public VueBrique(int x, int y, int width, int height){
		brick = new Rectangle2D.Double(x, y, width, height);
	}

	
	public void paintComponent(Graphics2D g, Color c){
		g.setColor(c);
		g.fill(brick);
		g.setPaint(Color.black);
		g.draw(brick);
	}
	
	
}
