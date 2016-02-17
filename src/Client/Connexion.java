package Client;

/**
 * Created by Clement on 21/01/2016.
 */
import java.net.*;
import java.util.Scanner;

import Model.Terrain;

import java.io.*;

public class Connexion implements Runnable {

    private Socket socket = null;
    public Thread tReception;
    public Thread tEmission;
    public String login = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc = null;
    private Terrain terrain=null;
    
    public Connexion(Socket s, Terrain t){

        socket = s;
        terrain=t;
    }

    public void run() {

        try {

            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in);
            System.out.println(in.readLine());
            login = sc.nextLine();
           // login = "Keulément";
            out.println(login);
            out.flush();

            Emission e = new Emission(out, login);
            terrain.setEmission(e);
            tEmission = new Thread(e);
            tEmission.start();
            tReception = new Thread(new Reception(in, terrain));
            tReception.start();
        } catch (IOException e) {

            System.err.println("Le serveur ne rÃ©pond plus ");
        }
    }

}