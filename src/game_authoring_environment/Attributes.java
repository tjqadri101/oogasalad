package game_authoring_environment;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Attributes extends JTabbedPane {
	
	private JPanel myActorEditorPanel;
	private JPanel mySceneEditorPanel;
	private JPanel myGameEditorPanel;
	
	public Attributes(){

		makePanels();
		addTabs();
	}
	
	private void addTabs() {
		ImageIcon actorEditorTabIcon = new ImageIcon(this.getClass().getResource("resources/actoreditorIcon.png"));
		ImageIcon sceneEditorTabIcon = new ImageIcon(this.getClass().getResource("resources/sceneeditorTabIcon.png"));
		ImageIcon gameEditorTabIcon = new ImageIcon(this.getClass().getResource("resources/GameeditorTabIcon.png"));
		this.addTab("Game", gameEditorTabIcon, myGameEditorPanel);
		this.addTab("Scene", sceneEditorTabIcon, mySceneEditorPanel);		
		this.addTab("Actor", actorEditorTabIcon, myActorEditorPanel);
		
	}

	private void makePanels() {
		myActorEditorPanel = ViewFactory.buildPanel(PanelType.ACTOREDITOR);
		mySceneEditorPanel = ViewFactory.buildPanel(PanelType.SCENEEDITOR);
		myGameEditorPanel = ViewFactory.buildPanel(PanelType.GAMEEDITOR);
	}

}
