package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import GUI.VueTerrain;
import Client.Emission;

public class Terrain{
	
    private Vector<Balle> Balls;
    private VueTerrain drawPanel;
    private Raquette racket;
    private Brique[][] matrix;
    private HashMap<String, Raquette> listRackets;
    public static final int panelWidth = 1000;
	public static final int panelHeight = 800;
	private Balle balle;
	private Emission emission;
       
    private static final String SIDES = null;
    
    public Terrain(){
    	matrix=new Brique[0][0];
        Balls = new Vector<Balle>();      
        
        //this.matrix=m;
        listRackets = new HashMap<String, Raquette>();
        racket = new Raquette(this);
		listRackets.put("moi", racket);
		balle = new Balle(this);       
		drawPanel = new VueTerrain(this);
        racket.move(500);
        
        //Balls.addElement(balle);
        
        
    }
    public Emission getEmission(){
    	return emission;
    }
    public void setEmission(Emission e){
    	emission = e;
    }
    public HashMap<String, Raquette> getRackets(){
        return listRackets;
    }

    public void removeBall(Balle balle) {
        Balls.remove(balle);
    }

    public void addRaquette (String login, Raquette r){
    	listRackets.put(login, r);
    }
    public int countBalls() {
        return Balls.size();
    }

    public Vector<Balle> getBalls() {
        return Balls;
    }
    
    public Balle getBalle(){
    	return balle;
    }
    public Brique[][] getMatrix(){
        return matrix;
    }
    
    public int getWidth(){
        return drawPanel.getWidth();
    }
    
    public int getHeight(){
        return drawPanel.getHeight();
    }
    
    public void paint(){
        drawPanel.repaint();
    }
    
    public VueTerrain getVueTerrain(){
        return drawPanel;
    }
    
    public void moveBall(double x, double y){
    	balle.setPosition(x, y);
    }
    
    public void moveRacket(String login, String posX){
    	Raquette r = listRackets.get(login);
    	r.move(Integer.parseInt(posX));
    	
    	paint();
    }
    
}
