package fr.mds.javacourse.java1tp14.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.mds.javacourse.java1tp14.models.Case;
import fr.mds.javacourse.java1tp14.models.Joueur;

public class Jeu {
	
	private int map_x;
	private int map_y;
	private int joueurs;
	
	//Lancemenct du jeu avec le nombres de cases (x,y) et le nombre de joueurs
	public static void main(String[] args) {
		new Jeu(10, 10, 2);
	}

	/**
	* Creation d'un objet pour faire de un random
	*/
	private static Random rand = new Random();
	private List<Joueur> arrJoueur = new ArrayList<Joueur>();
	
	/**
	* Indique si un bateau existe place aux coordonnees x, y pour un joueur donne
	* 
	* @param joueur joueur
	* @param k      coordonnee X
	* @param j      coordonnee Y
	* @return
	*/
	public int boatExist(Joueur monjoueur, int k, int j) {
		
		int Exist = 0;
		for (Case joueurCases : monjoueur.getPosNavire()) {
			if (k == joueurCases.getPositionX() && j == joueurCases.getPositionY()) {
				Exist = joueurCases.getidCase();
			}
		}
		return Exist;
	}

	public Jeu(int map_x, int map_y, int joueurs) {
		this.map_x = map_x;
		this.map_y = map_y;
		this.joueurs = joueurs;

		//Creation des joueurs
		for (int i = 0; i < this.joueurs; i++) {
			Joueur monjoueur = new Joueur(map_x, map_y, i);
			this.arrJoueur.add(monjoueur);
		}

		boolean fini = false;

		//La partie continue temps qu'au moins un joueur a un navire encore "en vie"
		while (!fini) {
			for (Joueur joueur : arrJoueur) {
				int cible = trouveCible(joueur.getId());

				//Si une cible est trouve
				if (cible != -1) {
					tire(arrJoueur.get(cible));
					afficheMapJoueur(arrJoueur.get(cible));
					System.out.println();

				}
				else {
					//Le jeu est fini
					fini = true;
				}
			}
		}

		//Affichage du gagnant
		String gagnant = null;
		for (Joueur joueur : arrJoueur) {
			if (estVivant(joueur)) {
				gagnant = "joueur " + (joueur.getId());
			}
			afficheMapJoueur(joueur);
			System.out.println();
		}

		System.out.println("Le gagant est : " + gagnant);
	}

	/**
	* Indique si le joueur est encore vivant
	* 
	* @param joueur indice du joueur dans le tableau de jeu
	* @return
	*/
	public static boolean estVivant(Joueur joueur) {
		
		boolean result = false;

		for (Case caseActuelle : joueur.getPosNavire()) {
			String recherche = caseActuelle.getPositionX() + ";" + caseActuelle.getPositionY();

			if (!joueur.getMapTirs().containsKey(recherche)) {
				result = true;
			}
		}
		
		return result;
	}

	/**
	* Trouve la prochaine cible en vie
	* 
	* @param attaquant l'indice de l'attaquant
	* @return l'adversaire suivant
	*/
	public int trouveCible(int attaquant) {
		
		int adversaire = -1;
		boolean flag = true;
		int i = attaquant;
		System.out.println("attaquant:" + attaquant);
		
		do {
			// Verification de l'indice pour ne pas sortir du tableau
			if (i + 1 == joueurs) {
				i = 0;
			} 
			
			else {
				i++;
			}

			Joueur cible = arrJoueur.get(i);
			if (estVivant(cible)) {
				adversaire = i;
				System.out.println("adversaire: " + adversaire);
				flag = false;
			}
		}
		
		while (flag && i != attaquant);

		if (i == attaquant) {
			adversaire = -1;
		}

		return adversaire;
	}

	/**
	* Tire sur le joueur cible
	* 
	* @param joueurCible indice du joueur cible
	*/
	public void tire(Joueur joueurCible) {
		
		// Tirage aleatoire de x et y;
		int x = -1;
		int y = -1;
		boolean shot = false;
		boolean touched = false;
		
		do {
			x = rand.nextInt(this.map_x) % this.map_x;
			y = rand.nextInt(this.map_y) % this.map_y;

			String posCible = x + ";" + y;

			if (!joueurCible.getMapTirs().containsKey(posCible)) {

				for (Case posNavire : joueurCible.getPosNavire()) {
					String caseBoat = posNavire.getPositionX() + ";" + posNavire.getPositionY();
					
					if (caseBoat == posCible) {

						touched = true;
						shot = true;
						String idCase = posNavire.getidCase() + "";
						joueurCible.setMapTirs(posCible, idCase);
					}
				}
				if (!touched) {
					joueurCible.setMapTirs(posCible, "9");
					shot = true;
				}
			}
		} 
		
		while (!shot);
	}

	public void afficheMap() {
		for (int i = 0; i < this.map_x; i++) {
			for (int j = 0; j < this.map_y; j++) {
				System.out.print(0);
			}

			System.out.println();
		}
	}

	/**
	* Affiche la carte d'un joueur
	* 
	* @param joueur
	*/
	public void afficheMapJoueur(Joueur joueur) {
		
		//Affiche map Joueur
		for (int k = 0; k < this.map_x; k++) {
			for (int j = 0; j < this.map_y; j++) {
				String recherche = k + ";" + j;
				
				if (boatExist(joueur, k, j) == 0) {
					if (joueur.getMapTirs().containsKey(recherche)) {
						System.out.print("9");
					}
					
					else {
						System.out.print("0");
					}
				}
				
				else {
					if (joueur.getMapTirs().containsKey(recherche)) {
						System.out.print("7");
					}
					
					else {
						System.out.print(boatExist(joueur, k, j));
					}
				}
			}
			
			System.out.println();
		}
	}

	//Getters & Setters
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

	public int getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(int joueurs) {
		this.joueurs = joueurs;
	}
}