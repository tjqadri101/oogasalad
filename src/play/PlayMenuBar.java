package play;

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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import controller.DataController;
import controller.GAEController;
import reflection.ReflectionException;
import reflection.Reflection;

/**
 * 
 * @author David Chou
 *
 */




/*
 * This class allows for the user to pick which game he or she would like to play.
 * Current functionality only allows for opening a file and quitting the program.
 * 
 * Intended future functionality includes:
 * 		-Not having to load an XML file but being able to choose a list of buttons.
 * 			A separate box would have to open for this to occur.
 * 		-Being able to open up a help page in order to see how to work the GUI.
 * 		-Being able to save the game (This is an optional feature)
 *
 */
@SuppressWarnings("serial")
public class PlayMenuBar extends JMenuBar{
	
	private static DataController myController;

	public PlayMenuBar(DataController myController){
		super();
		this.myController = myController;
		this.add(createFileMenu());
	}
	
	private JMenu createFileMenu(){
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(makeMenuItem("Open", "openGameFile"));
		fileMenu.add(makeMenuItem("Quit", "closeProgram"));
		return fileMenu;
	}
	
	private void closeProgram(){
	
		int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit", "Quitting", JOptionPane.YES_NO_OPTION);
		if(n==JOptionPane.YES_OPTION){
			System.exit(0);
		}	
	}
	
	private void openGameFile() throws Exception{
		File loadedFile =  chooseGameFile("Load");
		if(loadedFile == null) {
			return;
		}
			try {
				myController.readXML(loadedFile.getAbsolutePath());
			}
			catch (IOException ioe) {
				//ioe.printStackTrace();
				System.exit(1);
			}
		
	}
	
	private File chooseGameFile(String command){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML file", "xml");
		final JFileChooser chooser = new JFileChooser();
		chooser.setApproveButtonText(command);
		chooser.setFileFilter(filter);
		int actionDialog = chooser.showOpenDialog(this);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		File curFile = chooser.getSelectedFile();
		return curFile;

	}
	
	private JComponent makeMenuItem(String label, String method) {
		JMenuItem m = new JMenuItem(label);

		try {
			final Method onClickMethod = PlayMenuBar.class
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
		}
		return m;
	}
	
	private PlayMenuBar getCurrentInstance(){
		return this;
	}

	// for testing purposes
	 private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("MenuBarDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Create and set up the content pane.
	        PlayMenuBar demo = new PlayMenuBar(myController);
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
	    }

}
