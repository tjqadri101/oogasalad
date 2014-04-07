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

import controller.GAEController;

public class ActorsPanel extends Panel {
	
	private static final String ACTOR_DEFAULT_IMAGE = "resources/actor_default.png";

	private SubPanel mySubPanel;
	private JList myActorsList;
	private int myActorsCount = 1;
	private int mySeletedIndex = -1;
	private GAEController gController;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	public ActorsPanel(GAEController gController){
		super(PanelType.ACTORS);
		this.gController = gController;
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		makeActorsList();
		this.setLayout(new BorderLayout());
		this.add(mySubPanel,BorderLayout.NORTH);
		this.add(myActorsList,BorderLayout.CENTER);
	}

	private void makeActorsList() {
		myActorsList = new JList();
		myActorsList.setModel(listModel);
		MouseListener mouseListener = new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		    	 if (e.getClickCount() == 1 && listModel.size()!= 0) {
		    		 //single clicked
		    		 mySeletedIndex = myActorsList.locationToIndex(e.getPoint());	
		    		 //need to change attribute display to current actor
		          }
		    	 
		    	 else if (e.getClickCount() == 2 && listModel.size()!= 0) {
		             //double clicked
		          }
		     }
		 };
		 myActorsList.addMouseListener(mouseListener);
		
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
				addActors();
				manageActorsNum(true);
			}
		});
		
		JButton deleteButton = ViewFactory.createJButton("Delete");
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				deleteActors();
				manageActorsNum(false);
			}
		});
		
		outPanel.add(addButton,BorderLayout.WEST);
		outPanel.add(deleteButton,BorderLayout.EAST);
		
		return outPanel;
	}
	
	private void addActors(){		
		listModel.addElement("Actor " + myActorsCount);
		//add scene here
		gController.createActor(myActorsCount, ACTOR_DEFAULT_IMAGE, listModel.get(myActorsCount-1));
			
	}
	
	private void deleteActors(){		
		if(mySeletedIndex > -1){
			//delete scene here
			listModel.remove(mySeletedIndex);
			gController.deleteActor(mySeletedIndex+1);
		}		
	}

	private void manageActorsNum(boolean ifAdd){
		if(ifAdd){//adding scene
			myActorsCount = listModel.getSize()+1;
		}else{//deleting scene			
			mySeletedIndex = -1;
			//update the list display and scene number at engine
			for(int j=0;j<listModel.getSize();j++){
				listModel.set(j, "Actor "+(j+1));
				//need to set scene ID to be j at engine
			}
			myActorsCount = listModel.getSize()+1;
		}
		
	}
}
