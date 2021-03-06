package game_authoring_environment;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
class ImageImplement extends JPanel {
 
      private Image img;
 
 
      public ImageImplement(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
      }
 
      public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
      }
 
    }
 
public class ImageInJframe extends JFrame
{
public static void main(String args[])
{
    new ImageInJframe().start();
}
 
public void start()
{
    ImageImplement panel = new ImageImplement(new ImageIcon(this.getClass().getResource("resources/actorsIcon.png")).getImage());
      add(panel);
      setVisible(true);
      setSize(400,400);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}


