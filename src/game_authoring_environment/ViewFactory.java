package game_authoring_environment;

import javax.swing.JButton;

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
			
		default:
			//throw exception
			break;						
		}
		return panel;
	} 

}
