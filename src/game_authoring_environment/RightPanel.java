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

import reflection.ReflectionException;

import java.lang.reflect.Field;

import jgame.platform.JGEngine;

import controller.GAEController;
import engine.GameEngine;
import engineTests.EngineTest;

public class RightPanel extends JSplitPane {

	public double curXPos, curYPos;
	public String fieldName;
	public GAEController myGAEController;
	public static final double ID_STEP = 1d;
	public static final double POSIT_STEP = 10d;
	private static final String[] keyBehavior = {"EnemyShower", "SceneDone", "BloodFull", "LifeIncrease", "GameOver"}; 
	private String selectedBehavior;
	private JTextField keyField;
	
	public RightPanel(GAEController gController){
		myGAEController = gController;
		setOrientation(VERTICAL_SPLIT);
		setTopComponent(createSpinnerPanel(gController));
		setBottomComponent(new JScrollPane(new EnginePanel(myGAEController.getEngine(),myGAEController)));
		fieldName = "";
	}
	
	private JComponent createSpinnerPanel(GAEController gController){
		JPanel spinnerPanel = new JPanel();
		addLabeledPositionSpinner(spinnerPanel, "X", "curXPos",POSIT_STEP);
		addLabeledPositionSpinner(spinnerPanel, "Y", "curYPos", POSIT_STEP);
		spinnerPanel.add(makeSetKeyButton());
		return spinnerPanel;
	}
	
	
	 private JComponent makeSetKeyButton() {
		JButton outButton = new JButton("Cheat Key");		
		outButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				popUpAndSetKey();
			}			
		});
		return outButton;
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
	
	private void popUpAndSetKey() {
		selectedBehavior = "EnemyShower";
		Integer inputKey = null;		
		keyField = new JTextField(10);
		JButton setButton = new JButton("Set");
		setButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				setCheatKey();
			}			
		});
		JComboBox goalTypesBox = new JComboBox(keyBehavior);
		goalTypesBox.setSelectedIndex(0);
		goalTypesBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					System.out.println("new selected item:"+arg0.getItem().toString());
					selectedBehavior = arg0.getItem().toString();					
				}				
			}
						
		});	
		JComponent[] texts = {goalTypesBox, keyField, setButton};
		String[] strings = {"Behavior", "Key:",""};
		JPanel myPanel = ViewFactory.createOptionInputPanel(texts, strings);
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				"Please Set the Behavior and Key", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			setCheatKey(); 
		}
	}
	
	private void setCheatKey() {
		int key = (int)keyField.getText().charAt(0);
		myGAEController.modifyInputManager(key, selectedBehavior);
		
	}
	
}
