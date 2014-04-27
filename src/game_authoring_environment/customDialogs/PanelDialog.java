package game_authoring_environment.customDialogs;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class PanelDialog extends JDialog{

	public PanelDialog(JFrame frame){
		super(frame);
        setContentPane(initializePanel());
        this.setVisible(true);
        this.pack();
	}
	
	protected abstract JComponent initializePanel();
	
	
	
	
}
