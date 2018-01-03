import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Fenetre extends JFrame {
	
	public Fenetre(){
		
		// Définir le titre de la fenêtre
		this.setTitle("LoadRoad");
		// Dimension de la fenêtre
		this.setSize(400, 500);
		// Centrer l'appartition de la fenêtre
		this.setLocationRelativeTo(null);
		// Fermer la fenêtre sur clique sur croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Instanciation d'un objet JPanel
	    JPanel pan = new JPanel();
	    //Définition de sa couleur de fond
	    pan.setBackground(Color.GREEN);        
	    //On prévient notre JFrame que notre JPanel sera son content pane
	    this.setContentPane(pan); 
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Fenetre fen = new Fenetre();
	}
	
}