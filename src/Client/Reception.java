package Client;

import java.io.BufferedReader;
import java.io.IOException;

import Model.Terrain;


public class Reception implements Runnable {

    private BufferedReader in;
    private String message = null;
    private Terrain terrain=null;

    public Reception(BufferedReader in, Terrain t){

        this.in = in;
        terrain=t;
    }

    public void run() {

        while(true){
            try {

                message = in.readLine();
                System.out.println(message);
                switch(message){
                	case "balle":
                		double x=Double.parseDouble(in.readLine());
                		double y=Double.parseDouble(in.readLine());
                		terrain.moveBall(x,y);
                		break;
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

}