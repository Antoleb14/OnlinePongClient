package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;

/**
 * Classe d'émission pour envoyer des données au serveur
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez et Nicolas Belleme
 */
public class Emission {

    private PrintWriter out;
    private String login = null;


    /**
     * Constructeur de classe
     * @param out PrintWriter
     * @param login le login du joueur qui envoie une requête
     */
    public Emission(PrintWriter out, String login) {
        this.out = out;
        this.login = login;

    }


    /**
     * Méthode d'envoi de la position de la raquette du joueur au serveur
     * @param x position de la raquette sur l'axe X
     */
    public void sendRacketPosition(int x){
    	out.println("sendracketposition");
    	out.println(x);
    	out.flush();
    	
    }
}