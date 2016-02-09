package GUI;

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

class Balle extends Thread
{
   //private Ellipse2D.Double balle; 
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
	  pan = panel;
      threadStarted = true;   
      size = 20;
      speed = 3;
      int startx = (int)(Math.random() * pan.getWidth()-(size*2));
      int starty = (int)(Math.random() * pan.getHeight()/2-(size*2));
  
      balle = new VueBalle(this);
      deltax = speed * ((int) (Math.random() * 10) >= 5 ? -1 : 1);
      deltay = speed * ((int) (Math.random() * 10) >= 5 ? -1 : 1);
      
      //deltax = speed*(Math.sin(Math.toRadians((int)(Math.floor(Math.random()*360)))));
	  //deltay = speed*(Math.cos(Math.toRadians((int)(Math.floor(Math.random()*360)))));
      
      System.out.println("deltax: "+ deltax +" deltay: "+ deltay);
      if ((deltax == 0) && (deltay == 0)) { deltax = 1; }
      if(startx <= size || starty <= size){
    	  startx = size*2;
    	  starty = size*2;
      }
   }

   public void run()
   {
      while(threadStarted)
      {
        try {
            // To free up processor time
            Thread.sleep(10);
         }
         catch (InterruptedException e)
         {  System.out.println("crash");}

         oldx =  balle.getX();
         oldy =  balle.getY();
         newx = oldx + deltax;
         if (newx < 0){
            deltax = -deltax;
            newx = -newx;
         }
         if(newx + size > pan.getWidth()){
        	 deltax=-deltax;
        	 newx = pan.getWidth()-((newx+size)-pan.getWidth())-size;
         }
         
         ArrayList<Raquette> list = pan.getRackets();
         for(int i=0;i<list.size();i++){
        	 //System.out.println(list.get(i));
        	 if(checkCollision(list.get(i))){
        		 break;
        	 }
             //list.get(i).move((int) newx+(list.get(i).getSize()-(size)));
         }
         
         
         
         newy = oldy + deltay;
         //test loose
         if(newy < 0){
        	deltay = -deltay;
        	newy = -newy;
         }
         
         if(newy > pan.getHeight()){
        	pan.removeBall(this);
        	threadStarted = false;
         }
         
         
         breakBrick();
         //if (newy + size > pan.getHeight() || newy < 0)
            //deltay = -deltay;            
         
         //Envoie  à la vue la nouvelle position de la balle puis repaint le panel
         balle.setFrame(newx, newy, size, size);
         //a revoir
         pan.paint();
      }
   }


	private void breakBrick() {
		
		double posx =  newx/100;
		double posy =  (newy-80)/30;
		double posx2 =  (newx+size)/100;
		double posy2 =  (newy+size-80)/30;
		Brique[][] matrix = pan.getMatrix();
		int dir=-1;
		boolean changed=false;
		if((posy>=0 && posy < matrix.length)){
			dir = pan.setMatrixValue(posy, posx, newx, newy, size);
			if(!changed && dir != -1){ invertdelta(dir); changed=true; }
			dir = pan.setMatrixValue(posy, posx2, newx, newy, size);
			if(!changed && dir != -1){ invertdelta(dir);changed=true;}
		}
		
		if(posy2 < matrix.length){
			dir = pan.setMatrixValue(posy2, posx2, newx, newy, size);
			if(!changed && dir != -1){ invertdelta(dir);changed=true;}
			dir = pan.setMatrixValue(posy2, posx, newx, newy, size);
			if(!changed && dir != -1){ invertdelta(dir);changed=true;}
		}
		
	}


	private void invertdelta(int dir) {
		switch(dir){
			case Brique.UPSIDE :
				deltay = -deltay;
				break;
			case Brique.SIDE :
				deltax = -deltax;
				break;
		}
	}


	public boolean checkCollision(Raquette racket) {
		
		if((newx+size > (racket.getX()) && newx+size < (racket.getX()+racket.getSize())
			|| newx > (racket.getX()) && newx < (racket.getX()+racket.getSize())) 
				&& newy+size >= pan.getHeight()-racket.getHeight() && newy+size < pan.getHeight()+size){	
			
			double relativeY = (racket.getX()+(racket.getSize()/2)) - newx - (size/2);
			double normalRelativeY = (relativeY/(racket.getSize()/2));
			double angle = (normalRelativeY * 70);
			deltax = speed*(-Math.sin(Math.toRadians(angle)));
			deltay = speed*(-Math.cos(Math.toRadians(angle)));
			newy = pan.getWidth()-racket.getHeight()-((newy+size)-pan.getHeight()-racket.getHeight()); 
			speed = speed*acceleration;
			return true;
		}
		return false;	
	}

	public double getSize() {
		return size;
	}

	public void paint(Graphics2D g2d) {
		balle.paintComponent(g2d);		
	}
}
