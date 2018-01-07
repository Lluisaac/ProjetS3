package topo;

public class Route extends Case {

	public Route(int h) {
		super(h);
	}

	void setHauteur(int h) {
		this.hauteur = h;
	}

	public int getCouleur() {
		int red = (this.hauteur << 16) & 0x00FF0000;
		int green = (this.hauteur << 8) & 0x0000FF00;
		int blue = this.hauteur & 0x000000FF;
		return 0xFF000000 | red | green | blue;
	}

}
