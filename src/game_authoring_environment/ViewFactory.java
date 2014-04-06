package game_authoring_environment;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ViewFactory {
	
	public ViewFactory(){
		
	}
	
	public static JButton createJButton(String str){
		JButton button = new JButton(str);
		return button;
	}
	
	public static Panel buildPanel(PanelType type){
		Panel panel = null;
		switch(type){
		case SCENE:
			panel = new ScenePanel();
			break;
		
		case ACTORS:
			panel = new ActorsPanel();
			break;
		
		case MEDIA:
			panel = new MediaPanel();
			break;
		
		case BEHAVIORS:
			panel = new BehaviorsPanel();
			break;
			
		case SUB:
			panel = new SubPanel();
			break;
			
		case ACTOREDITOR:
			panel = new ActorEditorPanel();
			break;
			
		case SCENEEDITOR:
			panel = new SceneEditorPanel();
			break;
			
		default:
			//throw exception
			break;						
		}
		return panel;
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
