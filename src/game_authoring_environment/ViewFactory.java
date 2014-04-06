package game_authoring_environment;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import reflection.Reflection;

public class ViewFactory {

	public ViewFactory(){
		
	}
	
	public static JButton createJButton(String str){
		JButton button = new JButton(str);
		return button;
	}

	public static Panel buildPanel(PanelType type){

		try{
			return (Panel) Reflection.createInstance("game_authoring_environment."+type.toString()+"Panel");
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public JPanel createFileChooser(){
		JFileChooser fc = new JFileChooser();

        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        JButton openButton = new JButton("Open a File...");
        
        JButton saveButton = new JButton("Save a File...");

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        return buttonPanel;
	}
	
	
	
}
