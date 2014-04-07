package game_authoring_environment;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RightPanel extends JSplitPane {

	public RightPanel(){
		setOrientation(VERTICAL_SPLIT);
		setTopComponent(createSpinnerPanel());
		setBottomComponent(new JPanel());
	}	
	
	private JComponent createSpinnerPanel(){
		JPanel spinnerPanel = new JPanel();
		addLabeledPositionSpinner(spinnerPanel, "X");
		addLabeledPositionSpinner(spinnerPanel, "Y");
		addLabeledPositionSpinner(spinnerPanel, "W");
		addLabeledPositionSpinner(spinnerPanel, "H");
		return spinnerPanel;
	}
	
	
	static protected JSpinner addLabeledPositionSpinner(Container c, String label) {
		SpinnerModel posModel = new SpinnerNumberModel(0d, 0d, 500d, 0.2d);
		JLabel l = new JLabel(label);
		c.add(l);

		JSpinner spinner = new JSpinner(posModel);
		l.setLabelFor(spinner);
		c.add(spinner);
		spinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JSpinner curSpinner = (JSpinner)(e.getSource());
				 System.out.println(curSpinner.getValue());
			}

	        });
		return spinner;
	}
	//for tesing purposes
	/* private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("SpinnerDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Add content to the window.
	        frame.add(new RightPanel());

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event dispatch thread:
	        //creating and showing this application's GUI.
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                //Turn off metal's use of bold fonts
		        UIManager.put("swing.boldMetal", Boolean.FALSE);
			createAndShowGUI();
	            }
	        });
	    }*/
}
