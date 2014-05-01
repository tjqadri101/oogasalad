package game_authoring_environment;

import java.awt.BorderLayout;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.GAEController;

/**
 * The panel where you can add/delete/select actors. Extends Penel.
 * 
 * @author Nick Pengyi Pan
 * 
 * */

public class ActorsPanel extends Panel {

	private static final String ACTOR_DEFAULT_IMAGE = "actor_blank.png";
	private static final Object[] TABLE_COLUMN = {"Media", "Name","FileName"};

	private SubPanel mySubPanel;
	private JTable myActorsTable;
	private int myActorsCount = 1;
	private int mySelectedRow = -1;
	private GAEController gController;
	private DefaultTableModel actorsTableModel;

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
		this.add(new JScrollPane(mySubPanel),BorderLayout.NORTH);
		this.add(new JScrollPane(myActorsTable),BorderLayout.CENTER);
	}
	
	/**
	 * Make the actor list within the panel.
	 * */
	private void makeActorsList() {
		myActorsTable = new JTable();
		actorsTableModel = new DefaultTableModel(TABLE_COLUMN, 0){
			@Override
			public Class<?> getColumnClass(int col) {
				if (col == 0) {
					return ImageIcon.class;				
				} else {
					return super.getColumnClass(col);
				}
			}
			@Override
			public boolean isCellEditable(int row, int column){  
				return false;  
			}
		};
		myActorsTable.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
		myActorsTable.setModel(actorsTableModel);
		ListSelectionModel cellSelectionModel = myActorsTable.getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					if(myActorsTable.isFocusOwner()){
						mySelectedRow = myActorsTable.getSelectedRow();							
						update();
					}
				}
			}
		});

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
				addActors();
			}
			
		});

		JButton deleteButton = ViewFactory.createJButton("Delete");
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				deleteActors();
			}
		});

		outPanel.add(addButton,BorderLayout.WEST);
		outPanel.add(deleteButton,BorderLayout.EAST);

		return outPanel;
	}
	
	/**
	 * Update the selected actor ID in GAEController. Called when the user make selection in the table.
	 * @param null
	 * @return null
	 * */
	
	private void update(){
		gController.updateSelectedActorID(getSelectedActorID());
		gController.switchActiveAttributesTab(2); //actor tab is at index 2
		gController.updateAttributesActorInfo();
	}
	
	/**
	 * Update the selected actor ID in GAEController. Called when the user make selection in the Engine.
	 * @param null
	 * @return null
	 * */
	public void update(int selectedID){
		int actorIndex = actorIDtoRow(selectedID);
		int indexInTable = myActorsTable.convertRowIndexToView(actorIndex);
		myActorsTable.getSelectionModel().setSelectionInterval(indexInTable, indexInTable);
		mySelectedRow = indexInTable;							
		update();
	}
	
	/**
	 * Create new actor. Called when the user click the Add button.
	 * @param null
	 * @return null
	 * */

	private void addActors(){		
		String newActorName = "Actor " + myActorsCount;		
		gController.createActor(myActorsCount, ACTOR_DEFAULT_IMAGE, 100, 100,100.0,200.0, "Actor " + myActorsCount, 2, 1);
		ImageIcon icon = urlToScaledImageIcon("src/game_authoring_environment/resources/"+ACTOR_DEFAULT_IMAGE);
		Object toAdd[] = {icon , newActorName, ""};
		actorsTableModel.addRow(toAdd);
		myActorsTable.setRowHeight(icon.getIconHeight() +2);
		myActorsTable.getColumnModel().getColumn(0).setMaxWidth(icon.getIconWidth() +2);
		myActorsCount++;
	}
	
	/**
	 * Help method to create the Thumbnails.
	 * @param url
	 * @return ImageIcon
	 * */
	
	private ImageIcon urlToScaledImageIcon(String url){
		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance( 50, 50, Image.SCALE_SMOOTH ) ;
		return new ImageIcon( newimg );
	}
	
	/**
	 * Delete the actor. Called when the user clicks Delete button.
	 * @param null
	 * @return null
	 * */
	private void deleteActors(){		
		if(mySelectedRow > -1){
			//delete actor here			
			gController.deleteActorNoID();
			actorsTableModel.removeRow(mySelectedRow);
			mySelectedRow = -1;
		}		
	}

	/**
	 * Get the selected actor ID in the table.
	 * @param null
	 * @return selected actor ID
	 * */
	private int getSelectedActorID(){
		String actorName = (String) actorsTableModel.getValueAt(mySelectedRow, 1);
		int actorID = Integer.parseInt(actorName.split(" ")[actorName.split(" ").length-1]);
		return actorID;
	}
	
	/**
	 * Set the thumbnail image in the table.
	 * @param actorID
	 * @param imageURL
	 * @param name :Imagename
	 * @return null
	 * */
	public void setActorImage(int actorID, String imageURL,String name){
		int actorRow = actorIDtoRow(actorID);
		ImageIcon newIcon = urlToScaledImageIcon(imageURL);
		actorsTableModel.setValueAt(newIcon, actorRow, 0);
		actorsTableModel.setValueAt(name, actorRow, 2);
	}

	/**
	 * Convert the actor ID to the row in the table.
	 * @param actorID : actorID
	 * @return int :row number
	 * */
	private int actorIDtoRow(int actorID) {
		for(int row=0;row<actorsTableModel.getRowCount();row++){
			String actorName = (String) actorsTableModel.getValueAt(row, 1);
			int curID = Integer.parseInt(actorName.split(" ")[actorName.split(" ").length-1]);
			if(curID == actorID){
				return row;
			} 
		}
		return (Integer) null;
	}

	

}
