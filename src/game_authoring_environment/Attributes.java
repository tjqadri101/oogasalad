package game_authoring_environment;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Attributes extends JTabbedPane {
	
	private JPanel myActorEditorPanel;
	private JPanel mySceneEditorPanel;
	
	public Attributes(){
		
		addTabs();
		makePanels();
	}
	
	private void addTabs() {
		ImageIcon actorEditorTabIcon = new ImageIcon(this.getClass().getResource("resources/actoreditorIcon.png"));
		ImageIcon sceneEditorTabIcon = new ImageIcon(this.getClass().getResource("resources/sceneeditorTabIcon.png"));
		this.addTab("Scene", sceneEditorTabIcon, mySceneEditorPanel);
		
		this.addTab("Actor", actorEditorTabIcon, myActorEditorPanel);

		
	}

	private void makePanels() {
		myActorEditorPanel = ViewFactory.buildPanel(PanelType.ACTOREDITOR);
		mySceneEditorPanel = ViewFactory.buildPanel(PanelType.SCENEEDITOR);
	}

}
