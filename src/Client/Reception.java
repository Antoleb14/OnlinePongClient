package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import GUI.StartGui;
import Model.Raquette;
import Model.Terrain;


public class Reception implements Runnable {

    private BufferedReader in;
    private String message = null;
    private Terrain terrain=null;
    private StartGui startGui;
    public Reception(BufferedReader in, Terrain t, StartGui s){
        this.in = in;
        terrain=t;
        startGui = s;
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
                		String scoreConnexion = in.readLine();
                		String posXConnexion = in.readLine();
                		r = new Raquette(terrain);
                		r.setScore(Integer.parseInt(scoreConnexion));
                		terrain.addRaquette(login, r);
                		startGui.updatePlayersList();
                		if(posXConnexion.equals("null")){
                			terrain.paint();
                		}else {
                			terrain.moveRacket(login, posXConnexion);
                		}
                		break;
                	case "moveRaquette":
                		login = in.readLine();
                		String posX = in.readLine();
                		terrain.moveRacket(login, posX);
                		break;
                	case "depart":
                		
                		login = in.readLine();
                		terrain.getRackets().remove(login);
                		startGui.updatePlayersList();
                		System.out.println(login+ " s'est barré");
                		break;
                	case "createMatrice":
                		String xm = in.readLine();
                		String ym = in.readLine();
                		terrain.createMatrice(Integer.parseInt(xm), Integer.parseInt(ym));
                		break;
                	case "newCoord":
                		String xc = in.readLine();
                		String yc = in.readLine();
                		String posXc = in.readLine();
                		String posYc = in.readLine();
                		String nbCoupsc = in.readLine();
                		if(posXc.equals("brickRemoved")){
                			terrain.setMatriceNull(Integer.parseInt(xc), Integer.parseInt(yc));
                		} else {
                    		terrain.setMatrice(Integer.parseInt(xc), Integer.parseInt(yc), Integer.parseInt(posXc), Integer.parseInt(posYc), Integer.parseInt(nbCoupsc));
                		}
                		break;
                	case "breackBrick":
                		String xb = in.readLine();
                		String yb = in.readLine();
                		String nbCoups = in.readLine();
                		terrain.breakBrick(Integer.parseInt(xb), Integer.parseInt(yb),  Integer.parseInt(nbCoups));
                		break;
                	case "newPoint":
                		String buteur = in.readLine();
                		int score = Integer.parseInt(in.readLine());
                		System.out.println(buteur+" il a marqué!");
                		Raquette player = terrain.getRackets().get(buteur);
                		player.setScore(score);
                		startGui.updatePlayersList();
                		break;
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

}