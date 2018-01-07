package topo;

public class DepartArrivee extends Case {

	public DepartArrivee(int h) {
			super(h);
		}

	void setHauteur(int h) {
		this.hauteur = h;
	}

	public int getCouleur() {
		int red = (this.hauteur << 16) & 0x00FF0000;
		int green = (0 << 8) & 0x0000FF00;
		int blue = 0 & 0x000000FF;
		return 0xFF000000 | red | green | blue;
	}

}
