package game_authoring_environment;


import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import javax.swing.AbstractCellEditor;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	protected HashMap<Integer,Object> classMap = new HashMap<Integer,Object>();
	protected Object[] headerObject;
	
	public PanelTable(){
		new JTable();
		resize();
		headerObject = new Object[]{"Property",""};
		makeTable();
		init();
		
	}
	
	public PanelTable(boolean b){
		new JTable();
		resize();
		headerObject = new Object[]{"Property",""};
		
	}
	public PanelTable(Object[] o){
		new JTable();
		resize();
		headerObject = o;
		makeTable();
		init();
	}

	private void resize() {
		this.setRowHeight(20);
		
	}
	public void makeTable(){
		myTableModel = new DefaultTableModel(headerObject, 0){
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


		this.setModel(myTableModel);
		this.getColumnModel().getColumn(1).setCellEditor(new CustomTableCellEditor());
		this.getColumnModel().getColumn(1).setCellRenderer(new CustomTableCellRenderer()); 
	}

	abstract void updateTable();

	
	abstract void init();


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
