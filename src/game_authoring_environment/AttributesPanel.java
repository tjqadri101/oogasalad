package game_authoring_environment;

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import saladConstants.SaladConstants;
import controller.GAEController;

public class AttributesPanel extends JTabbedPane {
	
	private JPanel myActorEditorPanel;
	private JPanel mySceneEditorPanel;
	private JPanel myGameEditorPanel;
	
	public AttributesPanel(GAEController gController){

		makePanels(gController);
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

	private void makePanels(GAEController gController) {
		myActorEditorPanel = ViewFactory.buildPanel(PanelType.ACTOREDITOR,gController);
		mySceneEditorPanel = ViewFactory.buildPanel(PanelType.SCENEEDITOR,gController);
		myGameEditorPanel = ViewFactory.buildPanel(PanelType.GAMEEDITOR,gController);
	}
	
	public void setTab(int index){
		this.setSelectedIndex(index);
	}
	
	
	///best way to do this?
	public HashMap<String, JPanel> setUpMap(HashMap<String, JPanel> map){
		map.put(SaladConstants.GAME_EDITOR_PANEL, myGameEditorPanel);
		map.put(SaladConstants.SCENE_EDITOR_PANEL, mySceneEditorPanel);
		map.put(SaladConstants.ACTOR_EDITOR_PANEL, myActorEditorPanel);
		
		return map;
	}
	
}
