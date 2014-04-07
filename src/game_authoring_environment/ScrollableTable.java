package game_authoring_environment;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

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
    	 for(int i=0; i<data.length; i++)
    	 myTableModel.addRow(data[i]);
    	 
     }
     public int getColumnCount() {
         return columnNames.length;
     }
     
     

}
