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
			
			int temp = (int) ((hauteur - (hauteur * pente))/2 + (hauteur * pente));			
			this.image[x][y] = (int) hauteur;
			
			ajoutPic(temp, x + 1, y, pente);
			ajoutPic(temp, x - 1, y, pente);
			ajoutPic(temp, x, y - 1, pente);
			ajoutPic(temp, x, y + 1, pente);
		}
	}
	
	public void ajoutChaineMontagne(int x1, int y1, int x2, int y2, int nb) {
		 int x3 = (x2 - x1)/nb;
		 int y3 = (y2 - y1)/nb;
		 
		 Random rand = new Random();
		 
		 for (int i = 0; i < nb; i++) {
			 int x = x1+(x3*i)+(x3/2);
			 int y = y1+(y3*i)+(x3/2);
			 
			 this.ajoutPic(rand.nextInt(56) + 200, x, y, (rand.nextInt(20)+70)/100f);
		 }
	}
	
	public void ajoutCreux(float hauteur, int x, int y, float pente) {
		if (hauteur < 0 && x >= 0 && x < this.image.length && y >= 0 && y < this.image.length
				&& this.image[x][y] > hauteur) {
			int temp = (int) (hauteur * pente);
			this.image[x][y] = (int) hauteur;
			ajoutCreux(temp, x + 1, y, pente);
			ajoutCreux(temp, x - 1, y, pente);
			ajoutCreux(temp, x, y - 1, pente);
			ajoutCreux(temp, x, y + 1, pente);
		}
	}
	
	public void ajoutFalaise(int x1, int y1, int x2, int y2, int nb) {
		 int x3 = (x2 - x1)/nb;
		 int y3 = (y2 - y1)/nb;
		 
		 Random rand = new Random();
		 
		 for (int i = 0; i < nb; i++) {
			 int x = x1+(x3*i)+(x3/2);
			 int y = y1+(y3*i)+(x3/2);
			 
			 this.ajoutCreux(-(rand.nextInt(40) + 80), x, y, (rand.nextInt(20)+70)/100f);
		 }
	}
	

	public void toFile(int max) {
		BufferedImage img = new BufferedImage(this.image.length, this.image[0].length, BufferedImage.TYPE_BYTE_GRAY);
		
		for (int i = 0; i < this.image.length; i++) {
			for (int j = 0; j < this.image.length; j++) {
				img.setRGB(i, j, (this.image[i][j] * 256 * 256) + (this.image[i][j] * 256) + (this.image[i][j]));
			}
		}
		
		try {
			File f = new File("topo.png");
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
		topo.ajoutPic(255, 15, 47, 0.90f);
		//topo.ajoutFalaise(0, 0, 33, 63, 6);
		topo.ajoutChaineMontagne(0, 0, 63, 63, 8);
		topo.toFile(255);
		System.out.println("Done!");
	}

}
