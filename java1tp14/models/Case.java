package fr.mds.javacourse.java1tp14.models;

public class Case {
	
	private int idCase;
	private int positionX;
	private int positionY;

	public Case(int idCase, int x, int y) {
		this.positionX = x;
		this.positionY = y;
		this.idCase = idCase;
	}

	public int getidCase() {
		return idCase;
	}

	public void setidCase(int idCase) {
		this.idCase = idCase;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
}