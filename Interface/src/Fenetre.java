import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Fenetre extends JFrame {
	
	public Fenetre(){
		
		this.setTitle("LoadRoad");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
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