import java.io.FileOutputStream;
import java.io.IOException;

public class Topo {

	private int[][] image;

	public Topo(int x, int y) {
		this.image = new int[x][y];
		for (int i = 0; i < this.image.length; i++) {
			for (int j = 0; j < this.image.length; j++) {
				this.image[i][j] = 0;
			}
		}
	}

	public void ajoutPic(float hauteur, int x, int y, float pente) {
		if (hauteur > 0 && x >= 0 && x < this.image.length && y >= 0 && y < this.image.length
				&& this.image[x][y] <= hauteur) {
			int temp = (int) (hauteur * pente);
			this.image[x][y] = (int) hauteur;
			ajoutPic(temp, x + 1, y, pente);
			ajoutPic(temp, x - 1, y, pente);
			ajoutPic(temp, x, y - 1, pente);
			ajoutPic(temp, x, y + 1, pente);
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

	public void toFile(int max) {
		try {
			FileOutputStream fichier = new FileOutputStream("Topo.pgm");
			String r = "P2\n" + this.image.length + " " + this.image[0].length + "\n" + max + "\n" + this.toString();
			fichier.write(r.getBytes());
			fichier.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ajoutChaineMontagne(int x1, int y1, int x2, int y2) {
		
	}
	
	public void ajoutFalaise(int x1, int y1, int x2, int y2) {
		
	}

	public String toString() {
		String r = "";

		for (int i = 0; i < this.image.length; i++) {
			for (int j = 0; j < this.image.length; j++) {
				this.image[i][j] += 127;
				r += this.image[i][j] + " ";
			}

			r += "\n";
		}
		return r;
	}

	public static void main(String[] args) {
		Topo topo = new Topo(64, 64);
		topo.ajoutPic(127f, 2, 4, 0.90f);
		topo.ajoutPic(100f, 10, 8, 0.70f);
		topo.ajoutCreux(-100f, 55, 55, 0.90f);
		topo.toFile(255);
		System.out.println("Done!");
	}

}
