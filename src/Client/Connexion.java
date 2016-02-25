package Client;
import java.net.*;
import GUI.StartGui;
import Model.Raquette;
import Model.Terrain;

import java.io.*;

/**
 * Classe pour se connecter au serveur via la socket
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez et Nicolas Belleme
 */
public class Connexion {

    private Socket socket = null;
    public Thread tReception;
    public Thread tEmission;
    public String login = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Terrain terrain=null;
    private StartGui startGui;

    /**
     * Constructeur de classe
     * @param s une socket
     * @param t un terrain de jeu
     */
    public Connexion(Socket s, Terrain t){
        socket = s;
        terrain=t;
    }

    /**
     * Setter pour lier notre fenêtre à la connexion, sert notamment pour les messages d'erreur et l'update de la liste des scores.
     * @param s Fenêtre JFrame de l'application
     */
    public void setStartGUI(StartGui s){
    	startGui = s;
    }

    /**
     * Méthode pour demander au serveur de se connecter
     * @param login Nom d'utilisateur du joueur
     */
    public void sendLogin(String login) {
        try {

        	//out sert � �crire au serveur
            out = new PrintWriter(socket.getOutputStream());
            
            //in sert � lire ce que le serveur renvoi
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //On envoi le login
            out.println(login);
            out.flush();
            
            //On attend la response du serveur pour savoir si le login est accept�
            String message = in.readLine();
            if(message.equals("loginOk")){
            	//Si le serveur accepte la connexion
            	Emission e = new Emission(out, login);
            	new Raquette(terrain);
        		terrain.editRaquette(login);
        		startGui.updatePlayersList();
            	terrain.setEmission(e);
            	
            	//Le thread Reception sert � r�cup�rer ce que le serveur renvoi
            	tReception = new Thread(new Reception(in, terrain, startGui));
            	tReception.start();
            	
            	//On cache le panel de connexion
            	startGui.hideLoginPanel();
            }else {
            	
            	//Si le serveur r�pond que le login n'est pas OK, alors on affiche une erreur
            	startGui.setErrorlabel();
            }
        } catch (IOException e) {
            System.err.println("Le serveur ne r�pond plus ");
        }
    }


}