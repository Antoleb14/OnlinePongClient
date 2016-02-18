package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Client.Connexion;
import Model.Balle;
import Model.Raquette;
import Model.Terrain;


public class StartGui extends JFrame{
	
	protected Balle balle;
	protected Raquette racket;
	protected VueTerrain pan;
	protected JTextField login;
	private JLabel labelerror;
	private JPanel loginPan;
	private Connexion connect;
	private JPanel scorePanel;
	private Terrain terrain;
	public static final int WIDTH = Terrain.panelWidth + 250;
	public static final int HEIGHT = Terrain.panelHeight;
	
    public StartGui(Terrain terrain, Connexion connect){
       this.terrain = terrain;
	   this.connect = connect;
	   this.connect.setStartGUI(this);
       this.setTitle("PONG GAME POPOPO");
       this.setSize(WIDTH, HEIGHT);
       this.setLocationRelativeTo(null);
       this.setResizable(false);
       //this.setLayout(null);
       
       pan = terrain.getVueTerrain();
       add(pan, BorderLayout.EAST);
       
       //Thread t = new Thread(pan);
       //t.start();
      
       Font font1 = new Font("SansSerif", Font.BOLD, 40);
       
     
       
       
       
       scorePanel = new JPanel();
       //scorePanel.setBackground(Color.LIGHT_GRAY);
	   scorePanel.setSize(new Dimension(250, Terrain.panelHeight));
	   scorePanel.setMinimumSize(new Dimension(250, Terrain.panelHeight));
	   scorePanel.setBounds(0,0,250,Terrain.panelHeight);
	   scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
	   
	   loginPan = new JPanel();
	   loginPan.setLayout(new BoxLayout(loginPan, BoxLayout.PAGE_AXIS));
	   JLabel label = new JLabel("Entrez votre login:");
	   label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));
	   labelerror = new JLabel("Ce pseudo est déjà utilisé");
	   labelerror.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));
	   labelerror.setForeground(Color.red);
	   labelerror.setVisible(false);
	   loginPan.add(labelerror);
	   
	   login = new JTextField();
	   login.setMaximumSize(new Dimension(500, 30));
	   JButton validbtn = new JButton("Connexion");
	   validbtn.addActionListener(new BoutonListener());
	   loginPan.add(label);
	   loginPan.add(login);
	   loginPan.add(validbtn);
	   
	   scorePanel.add(loginPan);
	   
	   JPanel scoreList = new JPanel();
	   
	   scorePanel.add(scoreList);
	   
       add(scorePanel);
     
       
       //firstField.setText(firstDevise);
       //secondField.setText(secondDevise);
       this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
       addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent we)
           {
               String ObjButtons[] = {"Oui mais je rejouerai :(","Non je RESTE !"};
               int PromptResult = JOptionPane.showOptionDialog(null,
                       "Quitter l'application ?", "Fermer le jeu? pas consseillï¿½ :( :( :(",
                       JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                       ObjButtons,ObjButtons[1]);
               if(PromptResult==0)
               {
                   System.exit(0);
               }
           }
       });

      // this.setContentPane(pan);
       this.setVisible(true);
       
       
   }
   
    public void setErrorlabel(){
    	labelerror.setVisible(true);
    }
    
    public void hideLoginPanel() {
    	loginPan.setVisible(false);
    	
    }
    
    public void updatePlayersList(){
    	scorePanel.removeAll();
    	
    	for(Map.Entry<String, Raquette> entry : terrain.getRackets().entrySet()){
			JLabel lab = new JLabel(entry.getKey()+"  "+entry.getValue().getScore());
			lab.setFont(new Font(lab.getFont().getName(), Font.PLAIN, 20));
			scorePanel.add(lab);
        }
    	scorePanel.validate();
    	scorePanel.repaint();
    }
    
   class BoutonListener implements ActionListener{
	   public BoutonListener(){
		   super();	   
	   }

       public void actionPerformed(ActionEvent arg0) {
    	   
    	   System.out.println(login.getText());
    	   connect.sendLogin(login.getText());
       }
   }

}