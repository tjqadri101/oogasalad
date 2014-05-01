package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import controller.GAEController;

public class TilePanel extends Panel{

	/**
	 * The panel where you can create/delete tile. Extends Penel.
	 * 
	 * @author Nick Pengyi Pan
	 * 
	 * */


	    private static final String BRICK_DEFAULT_IMAGE = "brick.png";
		private static final Object[] TABLE_COLUMN = {"Image", "ColID","FileName"};

		private SubPanel mySubPanel;
		private JTable myTileTable;
		private int mySelectedRow = -1;
		private GAEController gController;
		private DefaultTableModel tileTableModel;

		public TilePanel(GAEController gController){
			super(PanelType.TILE);
			this.gController = gController;
			makeSubPanel();
			construct();
		}

		@Override
		protected void construct() {
			makeTileList();
			this.setLayout(new BorderLayout());
			this.add(new JScrollPane(mySubPanel),BorderLayout.NORTH);
			this.add(new JScrollPane(myTileTable),BorderLayout.CENTER);
		}
		
		/**
		 * Make the actor list within the panel.
		 * */
		private void makeTileList() {
			myTileTable = new JTable();
			tileTableModel = new DefaultTableModel(TABLE_COLUMN, 0){
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
			myTileTable.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
			myTileTable.setModel(tileTableModel);
			ListSelectionModel cellSelectionModel = myTileTable.getSelectionModel();
			cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(!e.getValueIsAdjusting()){
						if(myTileTable.isFocusOwner()){
							mySelectedRow = myTileTable.getSelectedRow();
							updateTileCreationMode();
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

			JButton enterTileMode = ViewFactory.createJButton("Enter Tile Mode");
			enterTileMode.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed (ActionEvent e){

					enterTileEditingMode();
				}
			});

			JButton exitTileMode = ViewFactory.createJButton("Exit Tile Mode");
			exitTileMode.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed (ActionEvent e){
					exitTileEditingMode();
				}
			});

			outPanel.add(enterTileMode,BorderLayout.NORTH);
			outPanel.add(exitTileMode,BorderLayout.SOUTH);

			return outPanel;
		}
		
		/**
		 * Create new actor. Called when the user click the Add button.
		 * @param null
		 * @return null
		 * */

		private void addToTable(String imagePath,char colID,String imageName){	
			ImageIcon icon = urlToScaledImageIcon("src/game_authoring_environment/resources/"+imageName);
			Object toAdd[] = {icon , colID, imageName};
			tileTableModel.addRow(toAdd);
			myTileTable.setRowHeight(icon.getIconHeight() +2);
			myTileTable.getColumnModel().getColumn(0).setMaxWidth(icon.getIconWidth() +2);
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
		
		
		private char getSelectedCOLID(){
			return (char) tileTableModel.getValueAt(mySelectedRow, 1);
		}
		
		private String getSelectedImageName(){
			return (String) tileTableModel.getValueAt(mySelectedRow, 2);
		}
		

		/**
		 * Convert the actor ID to the row in the table.
		 * @param actorID : actorID
		 * @return int :row number
		 * */
		private int actorIDtoRow(int actorID) {
			for(int row=0;row<tileTableModel.getRowCount();row++){
				String actorName = (String) tileTableModel.getValueAt(row, 1);
				int curID = Integer.parseInt(actorName.split(" ")[actorName.split(" ").length-1]);
				if(curID == actorID){
					return row;
				} 
			}
			return (Integer) null;
		}

		private void enterTileEditingMode(){
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JTextField tf = new JTextField();
			panel.add(tf);
			String imageName = BRICK_DEFAULT_IMAGE;
			String path = "";
			int result = JOptionPane.showConfirmDialog(null, panel, "Enter tile collision ID",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				JPanel panel2 = new JPanel(new GridLayout(0, 1));
				JLabel lb = new JLabel("Do you want to set the brick image? (Not mandatory)");
				panel2.add(lb);
				int result2 = JOptionPane.showConfirmDialog(null, panel2, "Confirm",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result2 == JOptionPane.OK_OPTION) {
					try{
						JFileChooser chooser = new JFileChooser("src/engine/ImageBuffer");
						UIManager.put("FileChooser.openDialogTitleText", "Choose Tile Image");
						SwingUtilities.updateComponentTreeUI(chooser);
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"jpg", "gif","png","jpeg");
						chooser.setFileFilter(filter);
						int returnVal = chooser.showOpenDialog(getParent());
						if(returnVal == JFileChooser.APPROVE_OPTION) {
							imageName = chooser.getSelectedFile().getName();
							path = chooser.getSelectedFile().getPath();							
							gController.uploadImage(20, 20, path);
						}			
					}catch(Exception e){
					}
				}
				char colID = tf.getText().charAt(0);
				addToTable(path, colID, imageName);
				gController.setDragTile(colID, imageName);
				gController.getEngine().setTileEditing(true);
			}
		}

		private void exitTileEditingMode(){
			gController.getEngine().setTileEditing(false);
			System.out.println("TileEditingMode Exited");
		}
		
		private void updateTileCreationMode() {
			gController.setDragTile(getSelectedCOLID(), this.getSelectedImageName());
			gController.getEngine().setTileEditing(true);
			
		}

}
