package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import Model.Raquette;
import Model.Terrain;


public class Reception implements Runnable {

    private BufferedReader in;
    private String message = null;
    private Terrain terrain=null;
    public Reception(BufferedReader in, Terrain t){
        this.in = in;
        terrain=t;
    }

    public synchronized void run() {

        while(true){
            try {

                message = in.readLine();
                //System.out.println(message);
                String login;
                Raquette r;
                switch(message){
                	case "balle":
                		double x=Double.parseDouble(in.readLine());
                		double y=Double.parseDouble(in.readLine());
                		terrain.moveBall(x,y);
                		break;
                	case "newConnexion":
                		login = in.readLine();
                		r = new Raquette(terrain);
                		terrain.addRaquette(login, r);
                		System.out.println("Nouveau joueur ! :"+ login);
                		terrain.paint();
                		break;
                	case "moveRaquette":
                		login = in.readLine();
                		String posX = in.readLine();
                		System.out.println("Move de "+login+ " en "+posX);
                		terrain.moveRacket(login, posX);
                		break;
                	case "depart":
                		login = in.readLine();
                		terrain.getRackets().remove(login);
                		System.out.println(login+ " s'est barr�");
                		break;
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

}