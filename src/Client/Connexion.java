package Client;

/**
 * Created by Clement on 21/01/2016.
 */
import java.net.*;
import java.util.Scanner;

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
    private Scanner sc = null;
    private Terrain terrain=null;
    private StartGui startGui;
    
    public Connexion(Socket s, Terrain t){

        socket = s;
        terrain=t;
        //run();
    }
    
    public void setStartGUI(StartGui s){
    	startGui = s;
    }

    public void sendLogin(String login) {
        try {

            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           // login = "Keulément";
            out.println(login);
            out.flush();
            String message = in.readLine();
            System.out.println(message);
            if(message.equals("loginOk")){
            	Emission e = new Emission(out, login);
            	Raquette racket = new Raquette(terrain);
        		terrain.editRaquette(login);
        		startGui.updatePlayersList();
            	terrain.setEmission(e);
            	tEmission = new Thread(e);
            	tEmission.start();
            	tReception = new Thread(new Reception(in, terrain, startGui));
            	tReception.start();
            	startGui.hideLoginPanel();
            }else {
            	startGui.setErrorlabel();
            }
        } catch (IOException e) {

            System.err.println("Le serveur ne répond plus ");
        }
    }


}