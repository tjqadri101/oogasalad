package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.GAEController;

/**
 * The panel where you can add/delete/select scenes. Extends Penel.
 * 
 * @author Nick Pengyi Pan
 * 
 * */
public class ScenePanel extends Panel{
	
	private SubPanel mySubPanel;
	private JList myScenesList;
	private int mySceneCount = 0;
	private int mySelectedIndex = -1;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private GAEController gController;
	
	public ScenePanel(GAEController gController){
		super(PanelType.SCENE);
		this.gController = gController;
		makeSubPanel();
		construct();
		gController.createLevel(1);
		addScene();
		
	}

	@Override
	protected void construct() {
		makeSceneList();
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(mySubPanel),BorderLayout.NORTH);
		this.add(new JScrollPane(myScenesList),BorderLayout.CENTER);
	}

	/**
	 * Make the actor list within the panel.
	 * */
	private void makeSceneList() {
		myScenesList = new JList();
		myScenesList.setModel(listModel);
		MouseListener mouseListener = new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		    	 if (e.getClickCount() == 1 && listModel.size()!= 0) {
		    		 //single clicked
		    		 mySelectedIndex = myScenesList.locationToIndex(e.getPoint());	
		    		 //need to change attribute display to current scene
		    		 switchToScene();
		    		 gController.updateSelectedSceneID(getSelectedSceneID());
		    		 gController.switchActiveAttributesTab(1);// scene tab is index 1
		    		 
		          }
		    	 
		    	 else if (e.getClickCount() == 2 && listModel.size()!= 0) {
		             //double clicked
		          }
		     }
			
		 };
		 myScenesList.addMouseListener(mouseListener);
		
	}
	
	/**
	 * Switch to the scene that the user selected
	 * @param null
	 * @return null
	 * */
	private void switchToScene() {
		gController.switchScene(gController.getCurrentLevelID(), getSelectedSceneID());	
	}

	/**
	 * Make the sub panel at the top where the buttons exist
	 * @param null
	 * @return null
	 * */
	@Override
	protected void makeSubPanel() {
		mySubPanel = (SubPanel) ViewFactory.buildPanel(PanelType.SUB,gController);
		mySubPanel.setSuperType(getType());
		mySubPanel.addItems(makeSubPanelItems());
		mySubPanel.construct();
	}

	/**
	 * Make the sub panel items. Two buttons: Add/Delete and set the listener for each of them
	 * @param null
	 * @return JComponent
	 * */
	@Override
	protected JComponent makeSubPanelItems() {
		JPanel outPanel = new JPanel();
		outPanel.setLayout(new BorderLayout());
		
		JButton addButton = ViewFactory.createJButton("Add");
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				addScene();
			}
		});
		
		JButton deleteButton = ViewFactory.createJButton("Delete");
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				deleteScene();
			}
		});
		
		outPanel.add(addButton,BorderLayout.WEST);
		outPanel.add(deleteButton,BorderLayout.EAST);
		
		return outPanel;
	}
	
	/**
	 * Create new scene. Called when the user click the Add button.
	 * @param null
	 * @return null
	 * */
	private void addScene(){		
			listModel.addElement("Scene " + mySceneCount);
			//add scene here
			gController.createScene(1,mySceneCount);
			gController.switchScene(1,mySceneCount);
			mySceneCount++;
		
	}
	
	/**
	 * Delete the current selected scene. Called when the user clicks Delete button.
	 * @param null
	 * @return null
	 * */
	private void deleteScene(){		
		if(mySelectedIndex > 0){
			//delete scene here			
			gController.deleteScene(gController.getCurrentLevelID(),getSelectedSceneID());
			listModel.remove(mySelectedIndex);
			mySelectedIndex = -1;
		}		
	}
	
	/**
	 * Get the selected scene ID in the table.
	 * @param null
	 * @return selected actor ID
	 * */
	private int getSelectedSceneID(){
		String sceneName = listModel.get(mySelectedIndex);
		int sceneID = Integer.parseInt(sceneName.split(" ")[sceneName.split(" ").length-1]);
		return sceneID;
	}
			
}
