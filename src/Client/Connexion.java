package Client;

/**
 * Created by Clement on 21/01/2016.
 */
import java.net.*;
import GUI.StartGui;
import Model.Raquette;
import Model.Terrain;

import java.io.*;

public class Connexion {

    private Socket socket = null;
    public Thread tReception;
    public Thread tEmission;
    public String login = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Terrain terrain=null;
    private StartGui startGui;
    
    public Connexion(Socket s, Terrain t){
        socket = s;
        terrain=t;
    }
    
    public void setStartGUI(StartGui s){
    	startGui = s;
    }

    public void sendLogin(String login) {
        try {

        	//out sert à écrire au serveur
            out = new PrintWriter(socket.getOutputStream());
            
            //in sert à lire ce que le serveur renvoi
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //On envoi le login
            out.println(login);
            out.flush();
            
            //On attend la response du serveur pour savoir si le login est accepté
            String message = in.readLine();
            if(message.equals("loginOk")){
            	//Si le serveur accepte la connexion
            	Emission e = new Emission(out, login);
            	new Raquette(terrain);
        		terrain.editRaquette(login);
        		startGui.updatePlayersList();
            	terrain.setEmission(e);
            	
            	//Le thread Reception sert à récupérer ce que le serveur renvoi
            	tReception = new Thread(new Reception(in, terrain, startGui));
            	tReception.start();
            	
            	//On cache le panel de connexion
            	startGui.hideLoginPanel();
            }else {
            	
            	//Si le serveur répond que le login n'est pas OK, alors on affiche une erreur
            	startGui.setErrorlabel();
            }
        } catch (IOException e) {
            System.err.println("Le serveur ne répond plus ");
        }
    }


}