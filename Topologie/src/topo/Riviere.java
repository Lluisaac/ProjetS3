package topo;

public class Riviere extends Case {
	
	public Riviere(int h) {
		super(h);
	}
	
	public void setHauteur(int h) {
		this.hauteur = h;
	}
	
	public int getCouleur() {
		int red = (0 << 16) & 0x00FF0000;
	    int green = (0 << 8) & 0x0000FF00;
	    int blue = this.hauteur & 0x000000FF;
		return 0xFF000000 | red | green | blue;
	}
	
}
