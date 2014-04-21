package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import controller.GAEController;

public class GameeditorPanel extends Panel {
	
	private SubPanel mySubPanel;
	private PanelType superType;
	private GAEController gController;
	private String GameName;
	private Integer DisplayWidth;
	private Integer DisplayHeight;
	private DefaultTableModel myTableModel;
	private JTable myTable;
	
	public GameeditorPanel(GAEController gController) {
		super(PanelType.GAMEEDITOR);
		this.gController = gController;
		setDefaultValues();
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());		
		this.add(new JScrollPane(mySubPanel), BorderLayout.NORTH);
		//this.add(makeTable(), BorderLayout.SOUTH);
		this.add(new JScrollPane(createTable()), BorderLayout.CENTER);
	

	}
	
	private void setDefaultValues(){
		GameName = "";
		DisplayWidth = 480;
		DisplayHeight = 360;
		
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
		JButton jb = ViewFactory.createJButton("Validate");
		return jb;
	}
	
	
	public JTable createTable(){
		GameEditorTable table = new GameEditorTable(gController);
		
		return table;
	}
	
	public void update(){
		construct();
	}
	
			

}

