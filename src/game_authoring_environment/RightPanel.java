package game_authoring_environment;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import reflection.ReflectionException;

import java.lang.reflect.Field;

import controller.GAEController;

public class RightPanel extends JSplitPane {

	public double curID, curXPos, curYPos;
	public String fieldName;
	public GAEController myGAEController;
	public static final double ID_STEP = 1d;
	public static final double POSIT_STEP = .2d;
	
	public RightPanel(GAEController gController){
		myGAEController = gController;
		setOrientation(VERTICAL_SPLIT);
		setTopComponent(createSpinnerPanel(gController));
		setBottomComponent(new JScrollPane(myGAEController.getEngine()));
		fieldName = "";
	}	
	
	private JComponent createSpinnerPanel(GAEController gController){
		JPanel spinnerPanel = new JPanel();
		addLabeledPositionSpinner(spinnerPanel, "ID", "curID", ID_STEP);
		addLabeledPositionSpinner(spinnerPanel, "X", "curXPos",POSIT_STEP);
		addLabeledPositionSpinner(spinnerPanel, "Y", "curYPos", POSIT_STEP);
		addLabeledPositionSpinner(spinnerPanel, "W", "", POSIT_STEP);
		addLabeledPositionSpinner(spinnerPanel, "H", "", POSIT_STEP);
		return spinnerPanel;
	}
	
	
	 protected JSpinner addLabeledPositionSpinner(Container c, String label, String field, double stepSize){
		SpinnerModel posModel = new SpinnerNumberModel(0d, 0d, 500d, stepSize);
		JLabel l = new JLabel(label);
		c.add(l);
		JSpinner spinner = new JSpinner(posModel);
		l.setLabelFor(spinner);
		c.add(spinner);
		spinner.setName(field);
		spinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JSpinner curSpinner = (JSpinner)(e.getSource());
				 try{
						Class<?> c1 = getCurInstance().getClass();
						Field field1 = c1.getField(curSpinner.getName());
						field1.set(getCurInstance(),curSpinner.getValue());
						myGAEController.modifyActorPosition((int) curID, curXPos, curYPos);
					}
					 catch (Exception e2)
				        {
				            throw new ReflectionException(e2.getMessage());
				        }
			}

	        });
		return spinner;
	}
	 
	
	private RightPanel getCurInstance(){
		return this;
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
