package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Model.Balle;
import Model.Raquette;
import Model.Terrain;


public class StartGui extends JFrame{
	
	protected Balle balle;
	protected Raquette racket;
	protected VueTerrain pan;
	
    public StartGui(){
	   
       this.setTitle("PONG GAME POPOPO");
       this.setSize(Terrain.panelWidth, Terrain.panelHeight);
       this.setLocationRelativeTo(null);
       this.setResizable(false);
       Terrain terrain = new Terrain();
       pan = terrain.getVueTerrain();
       add(pan, BorderLayout.NORTH);
       
       //Thread t = new Thread(pan);
       //t.start();
      
       Font font1 = new Font("SansSerif", Font.BOLD, 40);
       
     
       
       
       
       //firstField.setText(firstDevise);
       //secondField.setText(secondDevise);
       this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
       addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent we)
           {
               String ObjButtons[] = {"Oui mais je rejouerai :(","Non je RESTE !"};
               int PromptResult = JOptionPane.showOptionDialog(null,
                       "Quitter l'application ?", "Fermer le jeu? pas consseillé :( :( :(",
                       JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                       ObjButtons,ObjButtons[1]);
               if(PromptResult==0)
               {
                   System.exit(0);
               }
           }
       });

       this.setContentPane(pan);
       this.setVisible(true);
       
       
   }
   
 
   class BoutonListener implements ActionListener{
	   public BoutonListener(){
		   super();	   
	   }

       public void actionPerformed(ActionEvent arg0) {
    	   System.out.println("CLICKED");
    	   
       }
   }

}