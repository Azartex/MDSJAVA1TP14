package fr.mds.javacourse.java1tp14.models;

public abstract class Navire {
	
	protected int identifiant;
	protected int taille;
	protected boolean direction;

	public Navire(int identifiant, int taille) {
		this.identifiant = identifiant;
		this.taille = taille;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}
}