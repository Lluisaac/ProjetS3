package topo;

abstract class Case {
	
	protected int hauteur;
	
	public Case(int h) {
		this.hauteur = h;
	}

	abstract void setHauteur(int h);
	
	public int getHauteur() {
		return this.hauteur;
	}
	
	public abstract int getCouleur();
	
}
