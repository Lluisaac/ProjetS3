package window;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{

  public Fenetre(){
		
    this.setTitle("LoadRoad");
    this.setSize(300, 120);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    JPanel b2 = new JPanel();
    b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
    b2.add(new JButton("Lancer Programme"));
    b2.add(new JButton("Charger Topo"));

    JPanel b4 = new JPanel();
    //On positionne maintenant ces trois lignes en colonne
    b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
    b4.add(b2);
		
    this.getContentPane().add(b4);
    this.setVisible(true);
  }
}