
abstract class Case {
	
	protected int hauteur;
	protected int couleur; // En réflexion
	
	public Case(int h) {
		this.hauteur = h;
	}

	abstract void setHauteur(int h);
	
	public int getHauteur() {
		return this.hauteur;
	}
	
	abstract void setCouleur(int c);
	
	public int getCouleur() {
		return this.couleur;
	}
	
}
