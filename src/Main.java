
import game_authoring_environment.GAE;
import game_authoring_environment.GlassPaneDragAndDrop;
import game_authoring_environment.ImageTransferHandler;
import game_authoring_environment.MainPanel;

public class Main
{
    public static void main (String[] args)
    {
    	System.out.println("This is the main s");
    	MainPanel m = new MainPanel();
    	m.setVisible(true);
    	ImageTransferHandler g = new ImageTransferHandler(m);
   	
    	//GAE g = new GAE();
    	//GlassPaneDragAndDrop d = new GlassPaneDragAndDrop();
    	//d.setVisible(true);
    }
}
