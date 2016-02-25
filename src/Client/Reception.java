package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import GUI.StartGui;
import Model.Raquette;
import Model.Terrain;

/**
 * Thread de réception des données du serveur
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez & Nicolas Belleme
 */
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

	/**
	 * Méthode run du Thread qui lit en continue ce qui arrive du serveur
	 */
    public synchronized void run() {

        while(true){
            try {
                message = in.readLine();
                String login;
                Raquette r;
                if(message.equals("balle")){ //Le serveur renvoi les mouvements de la balle
					double x = Double.parseDouble(in.readLine());
					double y = Double.parseDouble(in.readLine());
					terrain.moveBall(x, y);
				}else if(message.equals("newConnexion")) { //Un nouveau joueur a �t� detect�
					login = in.readLine();
					String scoreConnexion = in.readLine();
					String posXConnexion = in.readLine();
					r = new Raquette(terrain);
					r.setScore(Integer.parseInt(scoreConnexion));
					terrain.addRaquette(login, r);
					startGui.updatePlayersList();
					if (posXConnexion.equals("null")) {
						terrain.paint();
					} else {
						terrain.moveRacket(login, posXConnexion);
					}
				}else if(message.equals("moveRaquette")) { //Un joueur a boug� sa raquette
					login = in.readLine();
					String posX = in.readLine();
					terrain.moveRacket(login, posX);
				}else if(message.equals("depart")) {//Un joueur est parti
					login = in.readLine();
					terrain.getRackets().remove(login);
					startGui.updatePlayersList();
				}else if(message.equals("createMatrice")) {//On instancie la matrice des briques
					String xm = in.readLine();
					String ym = in.readLine();
					terrain.createMatrice(Integer.parseInt(xm), Integer.parseInt(ym));
				}else if(message.equals("newCoord")) {//On set une brique de coordonn�e i,j dans la matrice
					String xc = in.readLine();
					String yc = in.readLine();
					String posXc = in.readLine();
					String posYc = in.readLine();
					String nbCoupsc = in.readLine();
					if (posXc.equals("brickRemoved")) {
						terrain.setMatriceNull(Integer.parseInt(xc), Integer.parseInt(yc));
					} else {
						terrain.setMatrice(Integer.parseInt(xc), Integer.parseInt(yc), Integer.parseInt(posXc), Integer.parseInt(posYc), Integer.parseInt(nbCoupsc));
					}
				}else if(message.equals("breackBrick")) {//Une brique a �t� heurt�
					String xb = in.readLine();
					String yb = in.readLine();
					String nbCoups = in.readLine();
					terrain.breakBrick(Integer.parseInt(xb), Integer.parseInt(yb), Integer.parseInt(nbCoups));
				}else if(message.equals("newPoint")){//Un joueur a marqu� un point
                		String buteur = in.readLine();
                		int score = Integer.parseInt(in.readLine());
                		Raquette player = terrain.getRackets().get(buteur);
                		player.setScore(score);
                		startGui.updatePlayersList();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}