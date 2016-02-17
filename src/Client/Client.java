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
    public static Thread tConnexion;

    public static void main(String[] args) {
    	
        try {

            System.out.println("Demande de connexion");
            socket = new Socket("192.168.0.12",2009);
            System.out.println("Connexion établie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connecté

            Terrain terrain = new Terrain();
            
            tConnexion = new Thread(new Connexion(socket, terrain));
            tConnexion.start();
            
            StartGui f = new StartGui(terrain);
    		//f.getContentPane().add(new JButton("Bouton 3"));
    		f.setVisible(true);
    		

        } catch (UnknownHostException e) {
            System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress());
        } catch (IOException e) {
            System.err.println("Aucun serveur à l'écoute du port "+socket.getLocalPort());
        }



    }

}