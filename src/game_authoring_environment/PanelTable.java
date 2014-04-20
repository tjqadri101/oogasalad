package game_authoring_environment;

import game_authoring_environment.PanelTable.CustomTableCellEditor;
import game_authoring_environment.PanelTable.CustomTableCellRenderer;
import game_authoring_environment.PanelTable.JTextFieldCellEditor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

	


public abstract class PanelTable extends JTable{

	
	protected DefaultTableModel myTableModel;
	protected JTable myTable;
	protected HashMap<Integer,Object> classMap = new HashMap<Integer,Object>();
	
	public PanelTable(){
		makeTable();
		init();
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

	abstract void updateTable();

	
	abstract void init();
		/*final JTextField tf = new JTextField("apple");
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
					// call the change method in GAEController here (change level;change scene etc)
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
		*/
	

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
