package topo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

public class Topo {

	private Case[][] image;

	public Topo(int x, int y) {
		this.image = new Case[x][y];
		for (int i = 0; i < this.image.length; i++) {
			for (int j = 0; j < this.image.length; j++) {
				this.image[i][j] = new Terre(127);
			}
		}
	}

	public void ajoutPic(float hauteur, int x, int y, float pente) {
		if (hauteur > 0 && x >= 0 && x < this.image.length && y >= 0 && y < this.image.length
				&& this.image[x][y].getHauteur() <= hauteur) {

			int temp = (int) ((hauteur - (hauteur * pente)) / 2 + (hauteur * pente));
			this.image[x][y].setHauteur((int) hauteur);

			ajoutPic(temp, x + 1, y, pente);
			ajoutPic(temp, x - 1, y, pente);
			ajoutPic(temp, x, y - 1, pente);
			ajoutPic(temp, x, y + 1, pente);
		}
	}

	public void ajoutChaineMontagne(int x1, int y1, int x2, int y2, int nb) {
		int x3 = (x2 - x1) / nb;
		int y3 = (y2 - y1) / nb;

		Random rand = new Random();

		for (int i = 0; i < nb; i++) {
			int x = x1 + (x3 * i) + (x3 / 2);
			int y = y1 + (y3 * i) + (x3 / 2);

			this.ajoutPic(rand.nextInt(56) + 200, x, y, (rand.nextInt(12) + 80) / 100f);
		}
	}

	public void ajoutCreux(float hauteur, int x, int y, float pente) {
		if (hauteur > 0 && x >= 0 && x < this.image.length && y >= 0 && y < this.image.length
				&& this.image[x][y].getHauteur() > hauteur) {
			
			int temp = (int) ((hauteur - (hauteur / pente)) / 2 + (hauteur / pente));
			this.image[x][y].setHauteur((int) hauteur);
			
			ajoutCreux(temp, x + 1, y, pente);
			ajoutCreux(temp, x - 1, y, pente);
			ajoutCreux(temp, x, y - 1, pente);
			ajoutCreux(temp, x, y + 1, pente);
		}
	}

	public void ajoutFalaise(int x1, int y1, int x2, int y2, int nb) {
		int x3 = (x2 - x1) / nb;
		int y3 = (y2 - y1) / nb;

		Random rand = new Random();

		for (int i = 0; i < nb; i++) {
			int x = x1 + (x3 * i) + (x3 / 2);
			int y = y1 + (y3 * i) + (x3 / 2);
			this.ajoutCreux(rand.nextInt(56), x, y, (rand.nextInt(20) + 70) / 100f);
		}
	}
	
	public void ajoutPente(int x1, int y1, int x2, int y2, int nb) {
		for (int x = x1; x < x2+1; x++) {
			for (int y = y1; y < y2+1; y++) {
				this.image[x][y].setHauteur(nb);
			}
		}
	}

	public void ajoutRiviere(int x, int y) {
		// pas d'image vide
		this.image[x][y] = new Riviere(this.image[x][y].getHauteur());
		
		int[] caseVoisines = new int[4];
		
		//Si une case d'� c�t� est hors du tableau, la rivi�re s'arr�te la.
		if (y > 0) {
			caseVoisines[0] = this.image[x][y - 1].getHauteur(); // haut
		}
		else {
			return;
		}
		
		if (x < this.image.length - 1) {
			caseVoisines[1] = this.image[x + 1][y].getHauteur(); // droite
		}
		else {
			return;
		}
		
		if (y < this.image[0].length - 1) {
			caseVoisines[2] = this.image[x][y + 1].getHauteur(); // bas
		}
		else {
			return;
		}
		
		if (x > 0) {
			caseVoisines[3] = this.image[x - 1][y].getHauteur(); // gauche
		}
		else {
			return;
		}

		if (!(this.image[x][y - 1] instanceof Riviere) && caseVoisines[0] <= this.image[x][y].getHauteur() && caseVoisines[0] <= caseVoisines[1]
				&& caseVoisines[0] <= caseVoisines[2] && caseVoisines[0] <= caseVoisines[3]) {
			ajoutRiviere(x, y - 1);
		} 
		else if (!(this.image[x + 1][y] instanceof Riviere) && caseVoisines[1] <= this.image[x][y].getHauteur() && caseVoisines[1] <= caseVoisines[0]
				&& caseVoisines[1] <= caseVoisines[2] && caseVoisines[1] <= caseVoisines[3]) {
			ajoutRiviere(x + 1, y);
		} 
		else if (!(this.image[x][y + 1] instanceof Riviere) && caseVoisines[2] <= this.image[x][y].getHauteur()
				&& caseVoisines[2] <= caseVoisines[1] && caseVoisines[2] <= caseVoisines[0]
				&& caseVoisines[2] <= caseVoisines[3]) {
			ajoutRiviere(x, y + 1);
		} 
		else if (!(this.image[x - 1][y] instanceof Riviere) && caseVoisines[3] <= this.image[x][y].getHauteur() && caseVoisines[3] <= caseVoisines[1]
				&& caseVoisines[3] <= caseVoisines[2] && caseVoisines[3] <= caseVoisines[0]) {
			ajoutRiviere(x - 1, y);
		}

	}

	public void toFile(String nom) {
		BufferedImage img = new BufferedImage(this.image.length, this.image[0].length, BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < this.image.length; i++) {
			for (int j = 0; j < this.image.length; j++) {
				img.setRGB(i, j, this.image[i][j].getCouleur());
			}
		}

		try {
			File f = new File(nom + ".png");
			ImageIO.write(img, "PNG", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String r = "";

		for (int i = 0; i < this.image.length; i++) {
			for (int j = 0; j < this.image.length; j++) {
				r += this.image[i][j] + " ";
			}

			r += "\n";
		}
		return r;
	}

	public static void main(String[] args) {
		Topo nouvelle = new Topo(128, 128);
		nouvelle.genererTopologie();
		nouvelle.toFile("test");
		nouvelle.importerTopologie("test.png");
		nouvelle.toFile("test2");
	}
	
	public static Topo topoAleatoire() {
		Topo topo = new Topo(64, 64);
		topo.genererTopologie();
		topo.toFile(FileSystemView.getFileSystemView().getRoots()[0] + "\\topo");
		return topo;
	}

	public void genererTopologie() {
		//1 Chaine de montagne par 64 pixels
		//1 Montagne par 16 pixels
		//1 Falaise par 128 pixels
		//1 Creux par 32 Pixels
		//1 riviere par 32 pixels
		
		Random rand = new Random();
		
		if (this.image.length >= 64) {
			for (int i = 0; i < this.image.length / 64; i++) {
				int x1 = rand.nextInt(this.image.length);
				int y1 = rand.nextInt(this.image.length);
				int x2 = rand.nextInt(this.image.length);
				int y2 = rand.nextInt(this.image.length);
				
				this.ajoutChaineMontagne(x1, y1, x2, y2, ((int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) / 8) + 1);
			}
		}
		
		if (this.image.length >= 16) {
			for (int i = 0; i < this.image.length / 16; i++) {
				System.out.println("test");
				this.ajoutPic(rand.nextInt(64) + 191, rand.nextInt(this.image.length), rand.nextInt(this.image.length), (rand.nextInt(20) + 70) / 100f);
			}
		}
		
		if (this.image.length >= 128) {
			for (int i = 0; i < this.image.length / 128; i++) {
				int x1 = rand.nextInt(this.image.length);
				int y1 = rand.nextInt(this.image.length);
				int x2 = rand.nextInt(this.image.length);
				int y2 = rand.nextInt(this.image.length);
				
				this.ajoutFalaise(x1, y1, x2, y2, ((int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) / 8) + 1);
			}
		}
		
		if (this.image.length >= 32) {
			for (int i = 0; i < this.image.length / 32; i++) {
				this.ajoutCreux(rand.nextInt(255) - 190, rand.nextInt(this.image.length), rand.nextInt(this.image.length), (rand.nextInt(20) + 70) / 100f);
			}
		}
		
		if (this.image.length >= 32) {
			for (int i = 0; i < this.image.length / 32; i++) {
				this.ajoutRiviere(rand.nextInt(this.image.length), rand.nextInt(this.image.length));
			}
		}
		
		
		this.ajoutDepartArrivee();
	}
	
	private void ajoutDepartArrivee() {
		Random rand = new Random();
		int x1 = rand.nextInt(this.image.length);
		int y1 = rand.nextInt(this.image[0].length);
		int x2 = rand.nextInt(this.image.length);
		int y2 = rand.nextInt(this.image[0].length);
		
		this.image[x1][y1] = new DepartArrivee(this.image[x1][y1].getHauteur());
		this.image[x2][y2] = new DepartArrivee(this.image[x2][y2].getHauteur());
	}

	public int isColorDepartArrivee(int rgb) {
		int r = 0;
		
		for (int i = 1; i < 256; i++) {
			if (rgb == new DepartArrivee(i).getCouleur()) {
				r = i;
			}
		}
		
		return r;
	}
	
	public int isColorTerre(int rgb) {
		int r = 0;
		
		for (int i = 1; i < 256; i++) {
			if (rgb == new Terre(i).getCouleur()) {
				r = i;
			}
		}
		
		return r;
	}
	
	public int isColorRoute(int rgb) {
		int r = 0;
		
		for (int i = 1; i < 256; i++) {
			if (rgb == new Route(i).getCouleur()) {
				r = i;
			}
		}
		
		return r;
	}
	
	public int isColorRiviere(int rgb) {
		int r = 0;
		
		for (int i = 1; i < 256; i++) {
			if (rgb == new Riviere(i).getCouleur()) {
				r = i;
			}
		}
		
		return r;
	}

	public void importerTopologie(String text) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(text));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.image = new Case[img.getHeight()][img.getWidth()];
		
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				if (isColorTerre(img.getRGB(i, j)) != 0) {
					this.image[i][j] = new Terre(isColorTerre(img.getRGB(i, j)));
				} else if (isColorRiviere(img.getRGB(i, j)) != 0) {
					this.image[i][j] = new Riviere(isColorRiviere(img.getRGB(i, j)));
				} else if (isColorRoute(img.getRGB(i, j)) != 0) {
					this.image[i][j] = new Route(isColorRoute(img.getRGB(i, j)));
				} else if (isColorDepartArrivee(img.getRGB(i, j)) != 0) {
					this.image[i][j] = new DepartArrivee(isColorDepartArrivee(img.getRGB(i, j)));
				}
			}
		}
	}

	public void executerPathfinding() {
		// TODO Auto-generated method stub
		
	}

}
