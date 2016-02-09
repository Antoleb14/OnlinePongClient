package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VueTerrain extends JPanel implements Vue, MouseMotionListener {

	private Terrain terrain;
	private static Image img = new ImageIcon("bg.jpg").getImage();
	
	private Vector<Balle> Balls;
	private Raquette racket;
	private Brique[][] matrix;
	private ArrayList<Raquette> listRackets = new ArrayList<Raquette>();
	
	public VueTerrain(Terrain terrain){
		setBackground(Color.BLUE);
		setSize(getPreferredSize());
	    setLayout(null);
	    System.out.println("HEIGHT: "+this.getHeight());
	    
	    
	    Balls = terrain.getBalls();
		matrix = terrain.getMatrix();
		racket = terrain.getRackets().get(0);
		
		addMouseListener(
			new MouseAdapter()  {
				public void mouseClicked(MouseEvent e){
                   Balle nextBall = new Balle(terrain);
                   if(Balls.size()<10)
                	   Balls.addElement(nextBall);
                   nextBall.start();	                  
	            }	
	        }
        );
		
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
        racket.paint(g2d);
        for (int i = 0; i < Balls.size(); i++) 
        { 
        	Balle ball = Balls.elementAt(i);
            ball.paint(g2d);
        }

        for(int i=0;i<matrix.length;i++){
        	for(int j=0;j<matrix[i].length;j++){
        		if(matrix[i][j] != null)
        			matrix[i][j].draw(g2d);
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
		
	}

	public ArrayList<Raquette> getRackets() {
		return listRackets;
	}
	
	public void paint(){
		repaint();
	}
	
	
}
