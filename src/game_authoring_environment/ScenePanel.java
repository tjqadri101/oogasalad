package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;

public class ScenePanel extends Panel{
	
	private SubPanel mySubPanel;
	private JList myScenesList;
	private int mySceneCount = 0;
	private int mySeletedIndex = -1;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	public ScenePanel(){
		super(PanelType.SCENE);
		makeSubPanel();
		construct();
		addScene();
	}

	@Override
	protected void construct() {
		makeSceneList();
		this.setLayout(new BorderLayout());
		this.add(mySubPanel,BorderLayout.NORTH);
		this.add(myScenesList,BorderLayout.CENTER);
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
		mySubPanel = (SubPanel) ViewFactory.buildPanel(PanelType.SUB);
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
				manageSceneNum(true);
			}
		});
		
		JButton deleteButton = ViewFactory.createJButton("Delete");
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				deleteScene();
				manageSceneNum(false);
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
			manageSceneNum(true);
		}
		else{
			listModel.addElement("Scene " + mySceneCount);
			//add scene here
		}
		
	}
	
	private void deleteScene(){		
		if(mySeletedIndex > 0){
			//delete scene here
			listModel.remove(mySeletedIndex);			
		}		
	}

	private void manageSceneNum(boolean ifAdd){
		if(ifAdd){//adding scene
			mySceneCount = listModel.getSize();
		}else{//deleting scene			
			mySeletedIndex = -1;
			//update the list display and scene number at engine
			for(int j=1;j<listModel.getSize();j++){
				listModel.set(j, "Scene "+j);
				//need to set scene ID to be j at engine
			}
			mySceneCount = listModel.getSize();
		}
		
	}
}
