/**
 * @author Talal Javed Qadri
 */
package game_authoring_environment;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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

import jgame.platform.JGEngine;

import controller.GAEController;
import engineTests.EngineTest;

public class RightPanel extends JSplitPane {

	public double curXPos, curYPos;
	public String fieldName;
	public GAEController myGAEController;
	public static final double ID_STEP = 1d;
	public static final double POSIT_STEP = .2d;
	
	public RightPanel(GAEController gController){
		myGAEController = gController;
		setOrientation(VERTICAL_SPLIT);
		setTopComponent(createSpinnerPanel(gController));
		setBottomComponent(myGAEController.getEngine());
		fieldName = "";
	}
	
	private JLayeredPane createEnginePanel(){
		JLayeredPane lp  = new JLayeredPane();
		JGEngine engine = myGAEController.getEngine();
		
		JPanel glass = new JPanel();
		glass.add(engine);
	    glass.setOpaque(false);
		MouseListener mouseListener = new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		         if (e.getClickCount() == 1) {
		        	 System.out.println("mouse clicked");
		        	 int selectedID = myGAEController.getSelectedIDFromDataController();
		        	 myGAEController.updateSelectedActorID(selectedID);
		          }
		     }
		 };		 
		 
		 glass.addMouseListener(mouseListener);
		
		
		//lp.add(engine, Integer.valueOf(1));
		lp.add(glass);
		
		return lp;
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
				// TODO Auto-generated method stub
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
