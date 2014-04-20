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

public class ActorEditorTable extends PanelTable{

	private static final Integer[] levelList = {1,2,3,4,5,6,7,8,9,10};
	private JTable myTable;
	public ActorEditorTable() {
		myTable = makeTable();
		init();
	}
	
	@Override
	void init() {

		final JTextField tf = new JTextField("test");
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

	@Override
	void updateTable() {
		// TODO Auto-generated method stub
		
	}

}
