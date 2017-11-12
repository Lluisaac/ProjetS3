import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Topo {

	private int[][] image;

	public Topo(int x, int y) {
		this.image = new int[x][y];
		for (int i = 0; i < this.image.length; i++) {
			for (int j = 0; j < this.image.length; j++) {
				this.image[i][j] = 127;
			}
		}
	}

	public void ajoutPic(float hauteur, int x, int y, float pente) {
		if (hauteur > 0 && x >= 0 && x < this.image.length && y >= 0 && y < this.image.length
				&& this.image[x][y] <= hauteur) {

			int temp = (int) ((hauteur - (hauteur * pente)) / 2 + (hauteur * pente));
			this.image[x][y] = (int) hauteur;

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
				&& this.image[x][y] > hauteur) {
			int temp = (int) ((hauteur - (hauteur / pente)) / 2 + (hauteur / pente));
			this.image[x][y] = (int) hauteur;
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

	public void ajoutRiviere(int x, int y) {
		// pas d'image vide
		this.image[x][y] = 255;
		
		int[] caseVoisines = new int[4];
		
		//Si une case d'à côté est hors du tableau, la rivière s'arrête la.
		if (y > 0) {
			caseVoisines[0] = this.image[x][y - 1]; // haut
		}
		else {
			return;
		}
		
		if (x < this.image.length - 1) {
			caseVoisines[1] = this.image[x + 1][y]; // droite
		}
		else {
			return;
		}
		
		if (y < this.image[0].length - 1) {
			caseVoisines[2] = this.image[x][y + 1]; // bas
		}
		else {
			return;
		}
		
		if (x > 0) {
			caseVoisines[3] = this.image[x - 1][y]; // gauche
		}
		else {
			return;
		}

		if (y > 0 && caseVoisines[0] <= this.image[x][y] && caseVoisines[0] <= caseVoisines[1]
				&& caseVoisines[0] <= caseVoisines[2] && caseVoisines[0] <= caseVoisines[3]) {
			ajoutRiviere(x, y - 1);
		} 
		else if (x < this.image.length - 1 && caseVoisines[1] <= this.image[x][y] && caseVoisines[1] <= caseVoisines[0]
				&& caseVoisines[1] <= caseVoisines[2] && caseVoisines[1] <= caseVoisines[3]) {
			ajoutRiviere(x + 1, y);
		} 
		else if (y < this.image[0].length - 1 && caseVoisines[2] <= this.image[x][y]
				&& caseVoisines[2] <= caseVoisines[1] && caseVoisines[2] <= caseVoisines[0]
				&& caseVoisines[2] <= caseVoisines[3]) {
			ajoutRiviere(x, y + 1);
		} 
		else if (x > 0 && caseVoisines[3] <= this.image[x][y] && caseVoisines[3] <= caseVoisines[1]
				&& caseVoisines[3] <= caseVoisines[2] && caseVoisines[3] <= caseVoisines[0]) {
			ajoutRiviere(x - 1, y);
		}

	}

	public void toFile(String nom) {
		BufferedImage img = new BufferedImage(this.image.length, this.image[0].length, BufferedImage.TYPE_BYTE_GRAY);

		for (int i = 0; i < this.image.length; i++) {
			for (int j = 0; j < this.image.length; j++) {
				img.setRGB(i, j, (this.image[i][j] * 256 * 256) + (this.image[i][j] * 256) + (this.image[i][j]));
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
		Topo topo = new Topo(64, 64);
		//topo.ajoutFalaise(0, 0, 63, 63, 6);
		topo.ajoutChaineMontagne(0, 0, 63, 63, 11);
		topo.ajoutChaineMontagne(63, 0, 0, 63, 1);
		topo.ajoutRiviere(15, 15);
		topo.toFile("Topo");
		System.out.println("Done!");
	}

}
