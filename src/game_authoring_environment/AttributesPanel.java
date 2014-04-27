package game_authoring_environment;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import saladConstants.SaladConstants;
import controller.GAEController;

public class AttributesPanel extends JTabbedPane {
	
	private static final int FULL_VIEW_HEIGHT = 768;
	private static final int FULL_VIEW_WIDTH = 1024;
	private static final int LEFT_PANEL_HEIGHT = FULL_VIEW_HEIGHT;
	private static final int LEFT_PANEL_WIDTH = FULL_VIEW_WIDTH * 1/5+70;
	
	
	private JPanel myActorEditorPanel;
	private JPanel mySceneEditorPanel;
	private JPanel myGameEditorPanel;
	private JPanel myPlayerEditorPanel;
	private JPanel myCollisionEditorPanel;
	
	public AttributesPanel(GAEController gController){

		makePanels(gController);
		addTabs();
		this.setPreferredSize(new Dimension(LEFT_PANEL_HEIGHT/2,LEFT_PANEL_WIDTH));
		
	}
	
	private void addTabs() {
		ImageIcon actorEditorTabIcon = new ImageIcon(this.getClass().getResource("resources/actoreditorIcon.png"));
		ImageIcon sceneEditorTabIcon = new ImageIcon(this.getClass().getResource("resources/sceneeditorTabIcon.png"));
		ImageIcon gameEditorTabIcon = new ImageIcon(this.getClass().getResource("resources/GameeditorTabIcon.png"));
		this.addTab("Game", gameEditorTabIcon, myGameEditorPanel);
		this.addTab("Scene", sceneEditorTabIcon, mySceneEditorPanel);		
		this.addTab("Actor", actorEditorTabIcon, myActorEditorPanel);	
		this.addTab("Player", null, myPlayerEditorPanel);
		this.addTab("Collisions", null, myCollisionEditorPanel);
	}

	private void makePanels(GAEController gController) {
		myActorEditorPanel = ViewFactory.buildPanel(PanelType.ACTOREDITOR,gController);
		mySceneEditorPanel = ViewFactory.buildPanel(PanelType.SCENEEDITOR,gController);
		myGameEditorPanel = ViewFactory.buildPanel(PanelType.GAMEEDITOR,gController);
		myPlayerEditorPanel = ViewFactory.buildPanel(PanelType.PLAYEREDITOR, gController);
		myCollisionEditorPanel = ViewFactory.buildPanel(PanelType.COLLISIONEDITOR, gController);
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

	public void updateActorInfo(int actorID) {
		((ActoreditorPanel) myActorEditorPanel).update(actorID);
		
	}
	
}
