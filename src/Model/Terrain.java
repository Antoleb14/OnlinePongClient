package Model;

import java.util.HashMap;
import java.util.Vector;

import GUI.VueTerrain;
import Client.Emission;

public class Terrain{
	
    private Vector<Balle> Balls;
    private VueTerrain drawPanel;
    private Raquette racket;
    private Brique[][] matrix;
    private HashMap<String, Raquette> listRackets;
    public static final int panelWidth = 1000;
	public static final int panelHeight = 800;
	private Balle balle;
	private Emission emission;
    
    public Terrain(){
        Balls = new Vector<Balle>();      
        matrix = new Brique[0][0];
        listRackets = new HashMap<String, Raquette>();
        racket = new Raquette(this);
		listRackets.put("Player", racket);
		balle = new Balle(this);       
		drawPanel = new VueTerrain(this);
        racket.move(500);
        
        
    }
    public Emission getEmission(){
    	return emission;
    }
    
    public void setEmission(Emission e){
    	emission = e;
    }
    
    public HashMap<String, Raquette> getRackets(){
        return listRackets;
    }

    public void removeBall(Balle balle) {
        Balls.remove(balle);
    }

    public void addRaquette (String login, Raquette r){
    	listRackets.put(login, r);
    }
    
    public int countBalls() {
        return Balls.size();
    }

    public Vector<Balle> getBalls() {
        return Balls;
    }
    
    public Balle getBalle(){
    	return balle;
    }
    public Brique[][] getMatrix(){
        return matrix;
    }
    
    public int getWidth(){
        return drawPanel.getWidth();
    }
    
    public int getHeight(){
        return drawPanel.getHeight();
    }
    
    public void paint(){
        drawPanel.repaint();
    }
    
    public VueTerrain getVueTerrain(){
        return drawPanel;
    }
    
    public void moveBall(double x, double y){
    	balle.setPosition(x, y);
    }
    
    public void moveRacket(String login, String posX){
    	Raquette r = listRackets.get(login);
    	r.move(Integer.parseInt(posX)+r.getWidth()/2);
    	
    	paint();
    }
	
	public void createMatrice(int x, int y){
		matrix = new Brique[x][y];
	}
	public void setMatrice(int x, int y, int posX, int posY, int nbCoups){
		matrix[x][y] = new Brique(posX, posY, nbCoups);
	}
	public void setMatriceNull(int x, int y){
		matrix[x][y] = null;
	}
	public void breakBrick(int x, int y, int nbCoups){
		if(nbCoups == 0){
			matrix[x][y] = null;
		} else {
			matrix[x][y].setNbCoups(nbCoups);
		}
		
	}
	
	public void editRaquette(String login) {
		Raquette r = listRackets.get("Player");
		listRackets.remove("Player");
		listRackets.put(login, r);
	}
	public Raquette getPlayerRacket() {
		return racket;
	}
    
}
