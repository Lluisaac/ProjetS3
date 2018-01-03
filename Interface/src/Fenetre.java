import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Fenetre extends JFrame {
	
	public Fenetre(){
		
		// D�finir le titre de la fen�tre
		this.setTitle("LoadRoad");
		// Dimension de la fen�tre
		this.setSize(400, 500);
		// Centrer l'appartition de la fen�tre
		this.setLocationRelativeTo(null);
		// Fermer la fen�tre sur clique sur croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Instanciation d'un objet JPanel
	    JPanel pan = new JPanel();
	    //D�finition de sa couleur de fond
	    pan.setBackground(Color.GREEN);        
	    //On pr�vient notre JFrame que notre JPanel sera son content pane
	    this.setContentPane(pan); 
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Fenetre fen = new Fenetre();
	}
	
}