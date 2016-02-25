package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import javax.swing.*;
import Client.Connexion;
import Model.Balle;
import Model.Raquette;
import Model.Terrain;

/**
 * Classe JFrame de l'application
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez et Nicolas Belleme
 */

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
	public static Color[] colors = {Color.red, Color.blue, Color.pink, Color.green, Color.cyan, Color.magenta, Color.orange};

	/**
	 * Constructeur de la fenêtre principale de l'application
	 * @param terrain Terrain de jeu
	 * @param connect Classe Connexion pour pouvoir appeler la méthode de login
     */
    public StartGui(Terrain terrain, Connexion connect){
       this.terrain = terrain;
	   this.connect = connect;
	   this.connect.setStartGUI(this);
       this.setTitle("PONG GAME POPOPO");
       this.setSize(WIDTH, HEIGHT);
       this.setLocationRelativeTo(null);
       this.setResizable(false);
       
       pan = terrain.getVueTerrain();
       add(pan, BorderLayout.EAST);
       
       scorePanel = new JPanel();
	   scorePanel.setSize(new Dimension(250, Terrain.panelHeight));
	   scorePanel.setMinimumSize(new Dimension(250, Terrain.panelHeight));
	   scorePanel.setBounds(0,0,250,Terrain.panelHeight);
	   scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
	   
	   loginPan = new JPanel();
	   loginPan.setLayout(new BoxLayout(loginPan, BoxLayout.PAGE_AXIS));
	   JLabel label = new JLabel("Entrez votre login:");
	   label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));
	   labelerror = new JLabel("Ce pseudo est d�j� utilis�");
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
     
       this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
       addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent we)
           {
               String ObjButtons[] = {"Oui mais je rejouerai :(","Non je RESTE !"};
               int PromptResult = JOptionPane.showOptionDialog(null,
                       "Quitter l'application ?", "Fermer le jeu? pas consseill� :( :( :(",
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

	/**
	 * Méthode pour afficher une erreur de login
	 */
    public void setErrorlabel(){
    	labelerror.setVisible(true);
    }

    /**
	 * Méthode pour masquer le panel de login d'un utilisateur lorsqu'il est connecté
	 */
    public void hideLoginPanel() {
    	loginPan.setVisible(false);
    	
    }

	/**
	 * Méthode de mise à jour des scores des joueurs
	 * return void
	 */
    public void updatePlayersList(){
    	scorePanel.removeAll();
    	int i=0;
    	for(Map.Entry<String, Raquette> entry : terrain.getRackets().entrySet()){
			JLabel lab = new JLabel(entry.getKey()+"  "+entry.getValue().getScore());
			lab.setFont(new Font(lab.getFont().getName(), Font.PLAIN, 20));
			lab.setForeground(colors[i]);
			scorePanel.add(lab);
			i++;
        }
    	scorePanel.validate();
    	scorePanel.repaint();
    }

    /**
	 * Classe interne de gestion des listener sur notre bouton de login
	 */
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