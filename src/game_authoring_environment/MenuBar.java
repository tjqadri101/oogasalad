/**
 * @author Talal Javed Qadri and Nick Pan
 */
package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import controller.GAEController;
import reflection.ReflectionException;
import reflection.Reflection;
import reflection.MethodAction;
import saladConstants.SaladConstants;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar{
	
	public static final String[] keyBehavior = {SaladConstants.LEVEL_DONE, SaladConstants.LIFE_INCREASE, SaladConstants.GAME_OVER}; 
	public String selectedBehavior;
	public JTextField keyField;
	public GAEController myGAEController;

	public MenuBar(GAEController gController){
		super();
		this.myGAEController = gController;
		this.add(createFileMenu());
		this.add(createEditMenu());
		this.add(createHelpMenu());
	}
	
	private JMenu createFileMenu(){
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(makeMenuItem(getCurrentInstance(), "Save As...", "saveGameFile"));
		fileMenu.add(makeMenuItem(getCurrentInstance(), "Open", "openGameFile"));
		fileMenu.add(makeMenuItem(getCurrentInstance(), "Quit", "closeProgram"));
		return fileMenu;
	}
	
	private JMenu createEditMenu(){
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(makeMenuItem(myGAEController.getDataController(), "Undo", "reviveObject"));
		editMenu.add(makeMenuItem(getCurrentInstance(), "Cheat Keys", "popUpAndSetKey"));
		return editMenu;
	}
	
	private JMenu createHelpMenu(){
		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(makeMenuItem(getCurrentInstance(), "About", "createAboutPanel"));
		helpMenu.add(makeMenuItem(getCurrentInstance(), "Help page", "createHelpPanel"));
		return helpMenu;
	}
	
	//temporarily added for testing purposes
	public void doNothing(){
		
	}
	
	public void createHelpPanel(){
		HelpPanel helpPanel = new HelpPanel();
		helpPanel.createAndShowHelpPanel();
	}
	
	public void createAboutPanel(){
		AboutPanel aboutPanel = new AboutPanel();
		aboutPanel.createAndShowAboutPanel();
	}
	
	public void closeProgram(){
	
		int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit", "Quitting", JOptionPane.YES_NO_OPTION);
		if(n==JOptionPane.YES_OPTION){
			System.exit(0);
		}	
	}

	public void saveGameFile(){
		File saveFile = chooseGameFile("Save");
		if(saveFile == null){
			return;
		}
		if (!saveFile.getName().endsWith(".xml")) {
			saveFile = new File(saveFile.getAbsolutePath() + ".xml");		
		}
		try {
			myGAEController.getDataController().exportXML(saveFile.getAbsolutePath());
		} catch (ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void openGameFile(){
		File loadedFile =  chooseGameFile("Load");
		if(loadedFile == null) {
			return;
		}
			try {
				myGAEController.getDataController().readXML(loadedFile.getAbsolutePath());
			}
			catch (Exception ioe) {
				//ioe.printStackTrace();
				System.exit(1);
			}
		
	}
	
	public File chooseGameFile(String command){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML file", "xml");
		final JFileChooser chooser =  ViewFactory.createJFileChooser();
		chooser.setApproveButtonText(command);
		chooser.setFileFilter(filter);
		int actionDialog = chooser.showOpenDialog(this);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		File curFile = chooser.getSelectedFile();
		return curFile;

	}

	
	public void popUpAndSetKey() {
		selectedBehavior = SaladConstants.LEVEL_DONE;
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
	
	public void setCheatKey() {
		int key = (int)keyField.getText().charAt(0);
		myGAEController.modifyInputManager(key, selectedBehavior);
		
	}
	
	public JComponent makeMenuItem(Object target, String label, String method) {
		JMenuItem m = new JMenuItem(label);
		
		MethodAction action = new MethodAction(target, method);
		m.addActionListener(action);
		
		return m;
	}
	
	

	private MenuBar getCurrentInstance(){
		return this;
	}

}
