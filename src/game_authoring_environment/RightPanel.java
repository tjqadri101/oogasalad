/**
 * @author Talal Javed Qadri
 */
package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.LayerUI;

import reflection.MethodAction;
import reflection.ReflectionException;
import java.lang.reflect.Field;

import jgame.platform.JGEngine;
import controller.GAEController;
import engine.GameEngine;
import engineTests.EngineTest;
import objects.NonPlayer;
import objects.Player;

@SuppressWarnings("serial")
public class RightPanel extends JSplitPane {

	public double curActorXPos, curActorYPos, curPlayerXPos, curPlayerYPos;
	public GAEController myGAEController;
	protected JSpinner actorXSpinner, actorYSpinner;
	public static final double POSIT_STEP = 10d;

	
	public RightPanel(GAEController gController){
		myGAEController = gController;
		setOrientation(VERTICAL_SPLIT);
		setTopComponent(createSpinnerPanel(gController));
		setBottomComponent(new JScrollPane(new EnginePanel(myGAEController.getEngine(),myGAEController)));
	}
	
	private JComponent createSpinnerPanel(GAEController gController){
		JPanel spinnerPanel = new JPanel();
		actorXSpinner = addLabeledPositionSpinner(spinnerPanel, "ACTOR_X", "curActorXPos",POSIT_STEP);
		actorYSpinner = addLabeledPositionSpinner(spinnerPanel, "ACTOR_Y", "curActorYPos", POSIT_STEP);
		spinnerPanel.add(makeFreezeButton());
		spinnerPanel.add(makeUnFreezeButton());
		return spinnerPanel;
	}
	
	
	 

	private JButton makeFreezeButton() {
		JButton freezeButton = new JButton("Freeze");
		freezeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				myGAEController.getEngine().setPaused(true);
				}
			});	
		return freezeButton;
	}
	
	private JButton makeUnFreezeButton() {
		JButton freezeButton = new JButton("UnFreeze");
		freezeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				myGAEController.getEngine().setPaused(false);
				}
			});	
		return freezeButton;
	}

	protected JSpinner addLabeledPositionSpinner(Container c, String label, String field,
												double stepSize){
		SpinnerModel posModel = new SpinnerNumberModel(0d, 0d, 1000d, stepSize);
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
						myGAEController.modifyActorPosNoID(curActorXPos, curActorYPos);
					}
					 catch (Exception e2)
				        {
				            throw new ReflectionException(e2.getMessage());
				        }
			}

	        });
		return spinner;
	}
	 
	protected void updateActorPositionSpinners(){
		
		NonPlayer curActor = myGAEController.getNonPlayer();
		curActorXPos = curActor.x;
		curActorYPos = curActor.y;
		actorXSpinner.setValue(curActorXPos);
		actorYSpinner.setValue(curActorYPos);
	}
	
	
	private RightPanel getCurInstance(){
		return this;
	}

	
}
