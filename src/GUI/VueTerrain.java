package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Model.Balle;
import Model.Brique;
import Model.Raquette;
import Model.Terrain;

public class VueTerrain extends JPanel implements Vue, MouseMotionListener {

	private Terrain terrain;
	private static Image img = new ImageIcon("bg.jpg").getImage();
	
	private Balle balle;
	private Raquette racket;
	private Brique[][] matrix;
	public VueTerrain(Terrain terrain){
		setBackground(Color.BLUE);
		setSize(getPreferredSize());
	    setLayout(null);	    
	    
	    balle = terrain.getBalle();
		racket = terrain.getRackets().get("Player");
		
		this.terrain = terrain;
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		this.setCursor(blankCursor);
		this.addMouseMotionListener(this);
	}
	
	public Dimension getPreferredSize()
    {
       return new Dimension(Terrain.panelWidth, Terrain.panelHeight);
    }
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
        
        Graphics2D g2d = (Graphics2D) g;
        balle.paint(g2d);
        HashMap<String, Raquette> listRackets = terrain.getRackets();
        int i=0;
        for(Map.Entry<String, Raquette> entry : listRackets.entrySet()){
        	Raquette r = entry.getValue();
        	r.paint(g2d,i);
        	i++;
        }
        matrix = terrain.getMatrix();
        for(int j=0;j<matrix.length;j++){
        	for(int k=0;k<matrix[j].length;k++){
        		if(matrix[j][k] != null)
        			matrix[j][k].draw(g2d);
        	}
        }
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		racket.move(arg0.getX());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		racket.move(arg0.getX());
		int posX = arg0.getX() - (racket.getSize()/2);
		terrain.getEmission().sendRacketPosition(posX);
	}

	
	public void paint(){
		repaint();
	}

	
	
}
