package fr.mds.javacourse.java1tp14.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Joueur {

	private List<Navire> arrNavires = new ArrayList<Navire>();
	private List<Case> posNavire = new ArrayList<Case>();
	private Map<String, String> mapTirs = new HashMap<>();

	private int id;
	private int map_x;
	private int map_y;
	private final int NBR_CORVETTE = 1;
	private final int NBR_CROISEUR = 2;
	private final int NBR_DESTROYER = 2;
	private final int NBR_PORTE_AVION = 1;

	public Joueur(int map_x, int map_y, int id) {

		this.setId(id);
		this.setMap_x(map_x);
		this.setMap_y(map_y);

		//Creation du corvette
		for (int i = 0; i < NBR_CORVETTE; i++) {
			Corvette corvette = new Corvette();
			placeBateau(corvette);
			this.arrNavires.add(corvette);
		}
		
		//Creation du croiseur
		for (int i = 0; i < NBR_CROISEUR; i++) {
			Croiseur croiseur = new Croiseur();
			placeBateau(croiseur);
			this.arrNavires.add(croiseur);
		}
		
		//Creation du destroyeur
		for (int i = 0; i < NBR_DESTROYER; i++) {
			Destroyer destroyeur = new Destroyer();
			placeBateau(destroyeur);
			this.arrNavires.add(destroyeur);
		}
		
		//Creation du porte avion 
		for (int i = 0; i < NBR_PORTE_AVION; i++) {
			PorteAvion PorteAvion = new PorteAvion();
			placeBateau(PorteAvion);
			this.arrNavires.add(PorteAvion);
		}
	}

	/**
	* Place un bateau pour un joueur
	* 
	* @param joueur l'indice du joueur dans le tableau
	* @param navire le tableau representant le type de bateau a placer
	*/
	public void placeBateau(Navire navire) {
		
		//Creation d'un objet pour faire de l'aleatoire
		map_x = this.getMap_x();
		map_y = this.getMap_y();
		Random rand = new Random();

		//Tirage aleatoire de x et y
		int x = rand.nextInt(map_x) % map_x;
		int y = rand.nextInt(map_y) % map_y;
		int direction = rand.nextInt(2) % 2;

		//Bateau placable aux coordonnees
		if (estPlacable(navire, x, y, direction)) {
			placeBateauDansCarte(navire, x, y, direction);
		}
		
		else {
			placeBateau(navire);
		}
	}

	public boolean estPlacable(Navire navire, int x, int y, int direction) {
		
		boolean notExist = true;
		map_x = this.getMap_x();
		map_y = this.getMap_y();

		for (int i = 0; i < navire.getTaille(); i++) {
			
			for (Case posNavire : this.getPosNavire()) {
				
				switch (direction) {
				
					case 0:
						
						//Verticale
						//Si hors de la carte
						if (x + i >= map_x) {
							notExist = false;
						} 
						
						else
							// Si la case contient d�j� un bateau.
							if (x + i == posNavire.getPositionX() && y == posNavire.getPositionY()) {
								notExist = false;
							}
						
						break;
						
					case 1:
						//Horizontale
						//Si hors de la carte
						
						if (y + i >= map_y) {
							notExist = false;
						}
						
						else
							//Si la case contient d�j� un bateau.
							if (y + i == posNavire.getPositionY() && x == posNavire.getPositionX()) {
								notExist = false;
							}
						
						break;
				}
			}
		}

		return notExist;
	}

	/**
	* Place r�ellement le bateau sur la carte du joueur.
	* 
	* @param navire    le tableau repr�sentant le type de navire
	* @param x         coordonn�e X
	* @param y         coordonn�e Y
	* @param direction verticale ou horizontale
	*/
	public void placeBateauDansCarte(Navire navire, int x, int y, int direction) {
		
		switch (direction) {
		
			case 0:
				
				//Verticale
				for (int i = 0; i < navire.getTaille(); i++) {
					Case addPosNavire = new Case(navire.getIdentifiant(), (x + i), y);
					this.posNavire.add(addPosNavire);
	
				}
				
				break;
	
			case 1:
				
				//Horizontale
				for (int i = 0; i < navire.getTaille(); i++) {
					Case addPosNavire = new Case(navire.getIdentifiant(), x, (y + i));
					this.posNavire.add(addPosNavire);
				}
				
				break;
		}
	}

	//Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMap_x() {
		return map_x;
	}

	public void setMap_x(int map_x) {
		this.map_x = map_x;
	}

	public int getMap_y() {
		return map_y;
	}

	public void setMap_y(int map_y) {
		this.map_y = map_y;
	}

	public List<Navire> getArrNavires() {
		return arrNavires;
	}

	public void setArrNavires(List<Navire> arrNavires) {
		this.arrNavires = arrNavires;
	}

	public List<Case> getPosNavire() {
		return posNavire;
	}

	public void setPosNavire(List<Case> posNavire) {
		this.posNavire = posNavire;
	}

	public Map<String, String> getMapTirs() {
		return mapTirs;
	}

	public void setMapTirs(String key, String value) {
		this.mapTirs.put(key, value);
	}
}