package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;

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
	private HashMap<Integer,Object> classMap = new HashMap<Integer,Object>();

	public ActoreditorPanel(GAEController gController) {
		super(PanelType.ACTOREDITOR);
		this.gController = gController;
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		makeTable();
		init();
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
		updateTable();
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
		List<String> s = gController.getAttributes();
		String firstrow = s.get(0);
		String[] strings = firstrow.split(",");
		for(String k : strings){
			System.out.println(k);
		}
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
				gController.uploadImage(100, 100, path);
				gController.updateActorImage(path,name);
				gController.setActorImageURL(name);
			}			
		}catch(Exception e){

		}

	}
	
	private void init(){
		final JTextField tf = new JTextField("apple");
		Object[] firstRow = {"Name", tf,"String"}; // each row should be in this format
		tf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("new text:" + tf.getText());	
				// do something here (change name etc)
			}			
		});
		
		myTableModel.addRow(firstRow); // actually adding to the table
		classMap.put(0,firstRow[1]); // classMap is the hashmap that keep track of the thing we created (first number is the row)
		
		
		JComboBox testComboBox = new JComboBox(levelList);
		Object[] secondRow = {"Level",testComboBox,"int"};
		testComboBox.setSelectedIndex(0);
		testComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					System.out.println("new selected item:"+arg0.getItem().toString());
				}				
			}
		});		
		myTableModel.addRow(secondRow);
		classMap.put(1,secondRow[1]);
		
		JCheckBox jb = new JCheckBox();
		Object[] thirdRow = {"TestBoo",jb,"boolen"};
		jb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					System.out.println("now checked:"+true);
					// call the change method in GAEController here
				}
				else{
					System.out.println("now checked:"+false);
					// call the change method in GAEController here
				}
			}
		});	
		
		myTableModel.addRow(thirdRow);
		classMap.put(2,thirdRow[1]);

		
	}

	public void updateInfo(int actorID){
		System.out.println("updating actorID:"+actorID);	
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
			if (classMap.get(row) instanceof JComboBox) {
				editor = new DefaultCellEditor((JComboBox) classMap.get(row));    
			} else if (classMap.get(row) instanceof JTextField) {
				editor = new JTextFieldCellEditor((JTextField) classMap.get(row));
			} else if (classMap.get(row) instanceof JCheckBox) {
				editor = new DefaultCellEditor((JCheckBox)classMap.get(row));
			}

			return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
		}

	}
	
	private class CustomTableCellRenderer implements TableCellRenderer{
    	private Component renderer;
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
        	if (classMap.get(row) instanceof JComboBox) {
        		renderer = (JComboBox)classMap.get(row);    
            } else if (classMap.get(row) instanceof JTextField) {
            	renderer = (JTextField) classMap.get(row);
            } else if (classMap.get(row) instanceof JCheckBox) {
            	renderer = (JCheckBox)classMap.get(row);
            }

            return renderer;
		}
    	
    } 
	
	private class JTextFieldCellEditor extends AbstractCellEditor implements TableCellEditor {

		  JComponent component;
		  
		  public JTextFieldCellEditor(JTextField jt){
			  component = jt;
		  }

		  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
		      int rowIndex, int vColIndex) {

		    return component;
		  }

		  public Object getCellEditorValue() {
		    return ((JTextField) component).getText();
		  }
		}

}
