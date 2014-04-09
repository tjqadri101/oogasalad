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

public class ScenePanel extends Panel{
	
	private SubPanel mySubPanel;
	private JList myScenesList;
	private int mySceneCount = 0;
	private int mySeletedIndex = -1;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private GAEController gController;
	
	public ScenePanel(GAEController gController){
		super(PanelType.SCENE);
		this.gController = gController;
		makeSubPanel();
		construct();
		addScene();
	}

	@Override
	protected void construct() {
		makeSceneList();
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(mySubPanel),BorderLayout.NORTH);
		this.add(new JScrollPane(myScenesList),BorderLayout.CENTER);
	}

	private void makeSceneList() {
		myScenesList = new JList();
		myScenesList.setModel(listModel);
		MouseListener mouseListener = new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		    	 if (e.getClickCount() == 1 && listModel.size()!= 0) {
		    		 //single clicked
		    		 mySeletedIndex = myScenesList.locationToIndex(e.getPoint());	
		    		 //need to change attribute display to current scene
		          }
		    	 
		    	 else if (e.getClickCount() == 2 && listModel.size()!= 0) {
		             //double clicked
		          }
		     }
		 };
		 myScenesList.addMouseListener(mouseListener);
		
	}

	@Override
	protected void makeSubPanel() {
		mySubPanel = (SubPanel) ViewFactory.buildPanel(PanelType.SUB,gController);
		mySubPanel.setSuperType(getType());
		mySubPanel.addItems(makeSubPanelItems());
		mySubPanel.construct();
	}

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
	
	private void addScene(){
		if(mySceneCount==0){
			listModel.addElement("Initial Scene");
			//add scene here
			mySceneCount++;
		}
		else{
			listModel.addElement("Scene " + mySceneCount);
			//add scene here
			mySceneCount++;
		}
		
	}
	
	private void deleteScene(){		
		if(mySeletedIndex > 0){
			//delete scene here
			String sceneName = listModel.get(mySeletedIndex);
			int sceneID = Integer.parseInt(sceneName.split(" ")[sceneName.split(" ").length-1]);
			listModel.remove(mySeletedIndex);
			mySeletedIndex = -1;
		}		
	}
			
}
