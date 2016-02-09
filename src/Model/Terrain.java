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
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import GUI.VueTerrain;

public class Terrain{
	
    private Vector<Balle> Balls;
    private VueTerrain drawPanel;
    private Raquette racket;
    private Brique[][] matrix;
    private ArrayList<Raquette> listRackets = new ArrayList<Raquette>();
    public static final int panelWidth = 1000;
	public static final int panelHeight = 800;
       
    private static final String SIDES = null;
    
    public Terrain(){
        Balls = new Vector<Balle>();      
        matrix = new Brique[5][10];
        
        int defX=0;
        int defY=80;
        int defW=0;
        int defH=0;
        int row=0;
        int col=0;
        for(int i = 0;i<50;i++){
            if(defW + defX > panelWidth){
                defX=0;
                defY+=defH;
                row++;
                col=0;
            }
            Brique brick = new Brique(drawPanel, defX, defY);
            defW=brick.getWidth();
            defH=brick.getHeight();
            defX+=defW;
            matrix[row][col] = brick;
            col++;
        }
        
        racket = new Raquette(this);
		listRackets.add(racket);
        drawPanel = new VueTerrain(this);
        racket.move(500);
        
        
    }
    
    public ArrayList<Raquette> getRackets(){
        return listRackets;
    }

    public void removeBall(Balle balle) {
        Balls.remove(balle);
    }

    
    public int countBalls() {
        return Balls.size();
    }
    

    public int setMatrixValue(double posy, double posx, double newx, double newy, double ballsize) {
        if(posy >= 0 && posx >= 0){
            if(matrix[(int)posy][(int)posx] != null){
                System.out.println("DELETED: ["+(int)posy+"]["+(int)posx+"]");
                double minx = ((int)posx * 100);
                double maxx = ((int)posx * 100)+100;
                double miny = ((int)posy * 30) + 80;
                double maxy = ((int)posy * 30) + 80 + 30;
                newx=(int)newx;
                newy=(int)newy;
                ballsize=(int)ballsize;
                //System.out.println(minx+" "+maxx+" / "+ miny+" "+ maxy);
                //System.out.println(newx+" / "+newy);
                
                //AFFINER LA DETECTION DES COLLISIONS AVEC LES BRIQUES
                if((newx+ballsize/2 <= minx || maxx <= newx+ballsize/2) 
                        && (miny <= (newy+ballsize) && maxy >= (newy+ballsize) || (miny <= newy) && maxy >= (newy))){
                    System.out.println("SIDE");
                    hitBrick(posx, posy);
                    return Brique.SIDE;
                }
                if(((newx >= minx && newx <= maxx) || (newx+ballsize/2 >= minx && newx+ballsize/2 <= maxx)) && miny <= newy+ballsize){
                    System.out.println("TOP");
                    hitBrick(posx, posy);
                    return Brique.UPSIDE;
                }                
            }
        }
        return -1;
    }
    
    private void hitBrick(double posx, double posy) {
    	Brique b = matrix[(int)posy][(int)posx];
    	if(b.getNbCoups() <= 1){
    		matrix[(int)posy][(int)posx] = null;
    	}else{
    		b.setNbCoups(b.getNbCoups()-1);
    	}
	}

    public Vector<Balle> getBalls() {
        return Balls;
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
    
}
