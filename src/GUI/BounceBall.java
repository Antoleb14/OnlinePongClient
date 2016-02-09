package GUI;
// BounceBall.java
// Application that creates multiple bouncing balls by assigning one thread to each
// Balls are added by clicking on the panel

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;



public class BounceBall extends JFrame
{
   private ShapePanel drawPanel;
   private Vector<NewBall> Balls;
   private JTextField message;

   // set up interface
   public BounceBall()  
   {
      super("MultiThreading");
      drawPanel = new ShapePanel(400, 345);
      message = new JTextField();
      message.setEditable(false);
     
      Balls = new Vector<NewBall>();
      add(drawPanel, BorderLayout.NORTH);
      add(message, BorderLayout.SOUTH);
  
      setSize(400, 400);
      setVisible(true);
   } // end Ex20 constructor



   // Class NewBall is used to create a ball and move its position
   // A thread overrides the run method to define how the ball behaves
   // Each ball is one instance of this class
 
   private class NewBall extends Thread
   {
      private Ellipse2D.Double thisBall;
      private boolean ballStarted;
      private int size, speed;             // characteristics
      private int deltax, deltay;          // of the ball
  
      public NewBall()
      {
         // Create new ball with random size, speed, start point,
         // and direction.  "Speed" is actually the amount of sleep
         // between moves.
         ballStarted = true;   
         size = 10 + (int)(Math.random() * 60);
         speed = 100 + (int)(Math.random() * 100);
         int startx = (int)(Math.random() * 300);
         int starty = (int)(Math.random() * 300);
         deltax = -10 + (int)(Math.random() * 21);
         deltay = -10 + (int)(Math.random() * 21);
         if ((deltax == 0) && (deltay == 0)) { deltax = 1; }
         thisBall = new Ellipse2D.Double(startx, starty, size, size);
      }

      
      public void draw(Graphics2D g2d)
      {
         if (thisBall != null)
         {
            g2d.setColor(Color.BLUE);
            g2d.fill(thisBall);
         }
      }

      public void run()
      {
         while(ballStarted)   // Keeps ball moving
         {
           try {
               // To free up processor time
               Thread.sleep(10);
            }
            catch (InterruptedException e)
            {  System.out.println("Woke up prematurely");}

            // calculate new position and move ball
            int oldx = (int) thisBall.getX();
            int oldy = (int) thisBall.getY();
            int newx = oldx + deltax;
            if (newx + size > drawPanel.getWidth() || newx < 0) 
               deltax = -deltax;
            int newy = oldy + deltay;
            if (newy + size > drawPanel.getHeight() || newy < 0) 
               deltay = -deltay;            
            thisBall.setFrame(newx, newy, size, size);
            // It is not efficient to repaint every time through run 
            // for each of the threads.  We will see a better way of
            // doing this later
            drawPanel.repaint();
         }
      }
   } // end NewBall


   // Define a class to be a panel on which the balls are drawn
   private class ShapePanel extends JPanel  {
      private int prefwid, prefht;
 
      public ShapePanel (int pwid, int pht)
      {
         prefwid = pwid;
         prefht = pht;
      

         // add ball when mouse is clicked
         addMouseListener(

		new MouseAdapter()  {
		   public void mouseClicked(MouseEvent e)
                   {
                       NewBall nextBall = new NewBall();
                       Balls.addElement(nextBall);
                       nextBall.start();
                       message.setText("Number of Balls: " + Balls.size());
                      
                   }
                }
          );
      }

      public Dimension getPreferredSize()
      {
         return new Dimension(prefwid, prefht);
      }

      public void paintComponent (Graphics g)  
      {
          super.paintComponent(g);
          Graphics2D g2d = (Graphics2D) g;
          for (int i = 0; i < Balls.size(); i++) 
          { 
             (Balls.elementAt(i)).draw(g2d);
          }
      }
   } // end ShapePanel inner class
} // end BounceBall
           