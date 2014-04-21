/**
 * @author Talal Javed Qadri
 */
package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import controller.GAEController;
import reflection.ReflectionException;
import reflection.Reflection;
import reflection.MethodAction;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar{
	
	public GAEController gController;

	public MenuBar(GAEController gController){
		super();
		this.gController = gController;
		this.add(createFileMenu());
		this.add(createEditMenu());
		this.add(createHelpMenu());
	}
	
	private JMenu createFileMenu(){
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(makeMenuItem("Save As...", "saveGameFile"));
		fileMenu.add(makeMenuItem("Open", "openGameFile"));
		fileMenu.add(makeMenuItem("Quit", "closeProgram"));
		return fileMenu;
	}
	
	private JMenu createEditMenu(){
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(makeMenuItem("Undo", "doNothing"));
		editMenu.add(makeMenuItem("Redo", "doNothing"));
		editMenu.add(makeMenuItem("Cut", "doNothing"));
		editMenu.add(makeMenuItem("Copy", "doNothing"));
		editMenu.add(makeMenuItem("Paste", "doNothing"));
		return editMenu;
	}
	
	private JMenu createHelpMenu(){
		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(makeMenuItem("About", "createAboutPanel"));
		helpMenu.add(makeMenuItem("Help page", "createHelpPanel"));
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
			gController.getDataController().exportXML(saveFile.getAbsolutePath());
		} catch (ParserConfigurationException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void openGameFile(){
		File loadedFile =  chooseGameFile("Load");
		if(loadedFile == null) {
			return;
		}
			try {
				gController.getDataController().readXML(loadedFile.getAbsolutePath());
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
	
	public JComponent makeMenuItem(String label, String method) {
		JMenuItem m = new JMenuItem(label);
		
		/*try {
		final Method onClickMethod = getCurrentInstance().getClass()
				.getDeclaredMethod(method);
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					onClickMethod.setAccessible(true);
					onClickMethod.invoke(getCurrentInstance());
					onClickMethod.setAccessible(false);
				}
				catch (Exception e1) {
					throw new ReflectionException(e1.getMessage());
				}
			}
		});

	} catch (Exception e) {
		throw new ReflectionException(e.getMessage());
	}*/
		
		MethodAction action = new MethodAction(getCurrentInstance() ,method);
		m.addActionListener(action);
		
		return m;
	}
	
	

	private MenuBar getCurrentInstance(){
		return this;
	}

	/*// for testing purposes
	 private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("MenuBarDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Create and set up the content pane.
	        MenuBar demo = new MenuBar();
	        frame.setJMenuBar(demo);
	        //Display the window.
	        frame.setSize(450, 260);
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }*/

}
