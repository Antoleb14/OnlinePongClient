package Client;

/**
 * Created by Clement on 21/01/2016.
 */
import java.io.*;
import java.net.*;

import GUI.StartGui;
import Model.Terrain;

public class Client {

    public static Socket socket = null;

    public static void main(String[] args) {
    	
        try {
        	
        	//On se connecte au serveur
            socket = new Socket("127.0.0.1",2009);
           
            //On instancie le terrain
            Terrain terrain = new Terrain();
            
            Connexion connect = new Connexion(socket, terrain);
            
            //On instancie la vue
            StartGui f = new StartGui(terrain, connect);
    		//f.getContentPane().add(new JButton("Bouton 3"));
    		f.setVisible(true);
    		

        } catch (UnknownHostException e) {
            System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress());
        } catch (IOException e) {
            System.err.println("Aucun serveur à l'écoute du port "+socket.getLocalPort());
        }



    }

}