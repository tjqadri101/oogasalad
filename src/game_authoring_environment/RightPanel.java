/**
 * @author Talal Javed Qadri
 */
package game_authoring_environment;


import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import reflection.ReflectionException;
import java.lang.reflect.Field;
import controller.GAEController;


@SuppressWarnings("serial")
public class RightPanel extends JSplitPane {

	public double curXPos, curYPos;
	public GAEController myGAEController;
	//public JSpinner xSpinner, ySpinner;
	public static final double POSIT_STEP = 10d;
	
	public RightPanel(GAEController gController){
		myGAEController = gController;
		setOrientation(VERTICAL_SPLIT);
		setTopComponent(createSpinnerPanel(gController));
		setBottomComponent(new JScrollPane(new EnginePanel(myGAEController.getEngine(),myGAEController)));
	}
	
	private JComponent createSpinnerPanel(GAEController gController){
		JPanel spinnerPanel = new JPanel();
		addLabeledPositionSpinner(spinnerPanel, "X", "curXPos",POSIT_STEP);
		addLabeledPositionSpinner(spinnerPanel, "Y", "curYPos", POSIT_STEP);
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
				JSpinner curSpinner = (JSpinner)(e.getSource());
				 try{
						Class<?> c1 = getCurInstance().getClass();
						Field field1 = c1.getField(curSpinner.getName());
						field1.set(getCurInstance(),curSpinner.getValue());
						myGAEController.modifyActorPosNoID(curXPos, curYPos);
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

	
}
