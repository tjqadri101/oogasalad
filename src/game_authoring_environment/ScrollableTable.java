package game_authoring_environment;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ScrollableTable extends JTable {


     protected String[] columnNames;
     protected Object[][] data;
   	 private DefaultTableModel myTableModel;

     public ScrollableTable(String[] columnNames, Object[][] data) {
         this.columnNames = columnNames;
         this.data = data;
         myTableModel = (DefaultTableModel) this.getModel();
         init();
     }

     public String getColumnName(int column) {
         return columnNames[column];
     }

     public boolean isCellEditable(int row, int column) {
         if (column == 1) return true;
         else return false;
     }

     public void init(){
    	 for(String col : columnNames){
    		 myTableModel.addColumn(col);
    	 }
    	/* for(int i=0; i<data.length; i++){
    		 myTableModel.addRow(data[i]);
    	 }*/
    	 
    	 TableColumn sportColumn = this.getColumnModel().getColumn(2);

    	 String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
    	 
         //Create the combo box, select the item at index 4.
         //Indices start at 0, so 4 specifies the pig.
         JComboBox petList = new JComboBox(petStrings);
    	 
    	
         Object[] j = {"string", "String", petList};
         myTableModel.addRow(j);
     }
     
     public int getColumnCount() {
         return columnNames.length;
     }
     
     
     

}
