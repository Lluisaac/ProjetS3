package pathfinding;

public class Topo {
	
	private Case[][] matrice;
	int largeur;
	int hauteur;


	public Topo(int largeur, int hauteur) {
		this.matrice = new Case[largeur][hauteur];
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		/*for (int i = 0; i < this.largeur; i++) {
		for (int j = 0; j < this.hauteur; j++) {
			this.matrice[i][j] = new Case();
			this.matrice[i][j].niv = 2+i+j; 
		}
	}*/
		for (int i = 0; i < this.largeur; i++) {
		for (int j = 0; j < this.hauteur; j++) {
			this.matrice[i][j] = new Case();
			if(i==2 && j==2){
				this.matrice[i][j].niv = 15; 
			}else{
				this.matrice[i][j].niv = 0; 
			}
		}
	}
	}
	
	public Case find(int x,int y){
		return this.matrice[x][y];
	}

	
	public void ToString() {
		String x = "";
		for (int i = 0; i < this.largeur; i++) {
			for (int j = 0; j < this.hauteur; j++) {
				x = x + this.matrice[i][j].niv + " ";
			}
			System.out.println(x);
			x = "";
		}
	}
	
	
	
	
}
