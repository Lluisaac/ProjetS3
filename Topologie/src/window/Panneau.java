package window;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
public class Panneau extends JPanel { 
 
	public void paintComponent(Graphics g){                
	    int x1 = this.getWidth()/4;
	    int y1 = this.getHeight()/4;                      
	    g.fillRoundRect(x1, y1, this.getWidth()/2, this.getHeight()/2, 5, 5);       
	}             
}
