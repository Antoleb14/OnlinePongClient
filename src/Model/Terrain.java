package Model;

import java.util.HashMap;
import java.util.Vector;

import GUI.VueTerrain;
import Client.Emission;

/**
 * Classe Terrain du modèle
 * @author Antoine Lebel, Guillaume Brosse, Clément LeBiez et Nicolas Belleme
 */
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

    /**
     * Constructeur de la classe Terrain
     */
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

    /**
     * Getter de la classe Emission utilisée pour envoyer la position de la raquette
     * @return Emission Instance de Emission avec le joueur
     */
    public Emission getEmission(){
    	return emission;
    }

    /**
     * Setter pour lier l'instance de la classe Emission au Terrain de jeu
     * @param e emission
     */
    public void setEmission(Emission e){
    	emission = e;
    }

    /**
     * Récupère la liste des raquettes (joueurs) en train de jouer actuellement
     * @return HashMap la liste des raquettes
     */
    public HashMap<String, Raquette> getRackets(){
        return listRackets;
    }

    /**
     * Ajoute une raquette à la liste des raquettes
     * @param login Le nom du joueur
     * @param r La raquette du joueur
     */
    public void addRaquette (String login, Raquette r){
    	listRackets.put(login, r);
    }


    /**
     * Récupère la balle en jeu
     * @return Balle balle en jeu
     */
    public Balle getBalle(){
    	return balle;
    }

    /**
     * Récupère la matrice des briques
     * @return Brique[][] Matrice des briques
     */
    public Brique[][] getMatrix(){
        return matrix;
    }

    /**
     * Méthode de peinture des composants de la vue
     */
    public void paint(){
        drawPanel.repaint();
    }

    /**
     * Getter de la vue associée au Terrain
     * @return VueTerrain Vue du terrain
     */
    public VueTerrain getVueTerrain(){
        return drawPanel;
    }

    /**
     * Méthode de déplacement de la balle
     * @param x position en X de la balle
     * @param y position en Y de la balle
     */
    public void moveBall(double x, double y){
    	balle.setPosition(x, y);
    }

    /**
     * Méthode de déplacement d'une raquette
     * @param login nom du joueur
     * @param posX nouvelle position en X de la raquette
     */
    public void moveRacket(String login, String posX){
    	Raquette r = listRackets.get(login);
    	r.move(Integer.parseInt(posX)+r.getSize()/2);
    	paint();
    }

    /**
     * Méthode de création de la matrice des briques
     * @param x position en X dans la matrice
     * @param y position en Y dans la matrice
     */
	public void createMatrice(int x, int y){
		matrix = new Brique[x][y];
	}

    /**
     * Setter d'une brique dans la matrice
     * @param x position en X de la brique dans la matrice
     * @param y position en Y de la brique dans la matrice
     * @param posX position en X de la brique sur la vue
     * @param posY position en Y de la brique sur la vue
     * @param nbCoups nombre de coups restants pour casser la brique
     */
	public void setMatrice(int x, int y, int posX, int posY, int nbCoups){
		matrix[x][y] = new Brique(posX, posY, nbCoups);
	}

    /**
     * Efface une brique de la matrice
     * @param x position en X dans la matrice
     * @param y position en Y dans la matrice
     */
	public void setMatriceNull(int x, int y){
		matrix[x][y] = null;
	}

    /**
     * Méthode qui vérifie que l'on doit casser une brique et la casse si nécessaire
     * @param x position en X dans la matrice
     * @param y position en Y dans la matrice
     * @param nbCoups nombre de coups restants pour casser la brique
     */
	public void breakBrick(int x, int y, int nbCoups){
		if(nbCoups == 0){
			setMatriceNull(x, y);
		} else {
			matrix[x][y].setNbCoups(nbCoups);
		}
		
	}

    /**
     * Editer une raquette
     * @param login le nouveau login du joueur
     */
	public void editRaquette(String login) {
		Raquette r = listRackets.get("Player");
		listRackets.remove("Player");
		listRackets.put(login, r);
	}

    /**
     * Récupère la raquette du joueur
     * @return Raquette la raquette du joueur
     */
	public Raquette getPlayerRacket() {
		return racket;
	}
    
}
