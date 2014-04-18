package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.EventObject;

import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import controller.GAEController;

public class ActoreditorPanel extends Panel {
	
	private static final Integer[] levelList = {1,2,3,4,5,6,7,8,9,10};

	private SubPanel mySubPanel;
	private PanelType superType;
	private JComponent myComponent;
	private GAEController gController;
	private DefaultTableModel myTableModel;
	private JTable myTable;

	public ActoreditorPanel(GAEController gController) {
		super(PanelType.ACTOREDITOR);
		this.gController = gController;
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		makeTable();
		this.setLayout(new BorderLayout());		
		this.add(new JScrollPane(mySubPanel), BorderLayout.NORTH);
		this.add(new JScrollPane(myTable), BorderLayout.CENTER);
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
		JButton jb  = makeChooseImageButton();
		return jb;
	}
	public void update(){

	}

	public void makeTable(){
		myTable = new JTable();
		myTableModel = new DefaultTableModel(new Object[]{"Property","","Type"}, 0){
			@Override
			public Class<?> getColumnClass(int col) {					
				return super.getColumnClass(col);
			}
			@Override
			public boolean isCellEditable(int row, int column){  
				switch (column) {
				case 0:
					return false;
				case 1:
					return true;
				default:
					return false;
				}  
			}
		};


		myTable.setModel(myTableModel);
		myTable.getColumnModel().getColumn(1).setCellEditor(new CustomTableCellEditor());
		myTable.getColumnModel().getColumn(1).setCellRenderer(new CustomTableCellRenderer());
	}

	public void updateTable(){

	}

	private JButton makeChooseImageButton(){
		JButton b = ViewFactory.createJButton("Select Actor Image");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				chooseActorImage("Select Actor Image");
			}			
		});
		return b;
	}

	private void chooseActorImage(String displayText) {
		try{
			JFileChooser chooser = new JFileChooser("src/game_authoring_environment/resources");
			UIManager.put("FileChooser.openDialogTitleText", displayText);
			SwingUtilities.updateComponentTreeUI(chooser);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"jpg", "gif","png","jpeg");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getPath();
				String name = chooser.getSelectedFile().getName();
				gController.updateActorImage(path,name);
			}			
		}catch(Exception e){

		}

	}

	public void updateInfo(int actorID){
		System.out.println("updating actorID:"+actorID);
		Object[] data = {"Name", "apple","String"};
		myTableModel.addRow(data);
		JComboBox comboBox = new JComboBox();
		Object[] data2 = {"Level",comboBox,"int"};
		//		comboBox.setSelectedIndex(0);


		myTableModel.addRow(data2);
	}

	private class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor {
		private TableCellEditor editor;

		@Override
		public Object getCellEditorValue() {
			if (editor != null) {
				return editor.getCellEditorValue();
			}

			return null;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			Object curValue = myTable.getValueAt(row, column);
			if (curValue instanceof JComboBox) {
				JComboBox newBox = new JComboBox(levelList);
				editor = new DefaultCellEditor(newBox);    
			} else if (curValue instanceof String) {
				editor = new DefaultCellEditor(new JTextField());
			} else if (curValue instanceof Boolean) {
				editor = new DefaultCellEditor(new JCheckBox());
			}

			return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
		}

		@Override
		public boolean shouldSelectCell(EventObject anEvent){
			return false;
		}

	}
	
	private class CustomTableCellRenderer implements TableCellRenderer{
    	private Component renderer;
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Object curValue = myTabl+e.getValueAt(row, column);
        	if (curValue instanceof JComboBox) {
        		renderer = (JComboBox)curValue;    
            } else if (curValue instanceof String) {
            	renderer = new JLabel((String)value);
            } else if (curValue instanceof Boolean) {
            	renderer = new DefaultTableCellRenderer();
            }

            return renderer;
		}
    	
    } 




}
