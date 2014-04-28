package game_authoring_environment;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * The Panel factory where every panel is created. Using factory pattern.
 * 
 * @author Nick Pengyi Pan
 * 
 * */
public abstract class Panel extends JPanel{
	
	private PanelType type = null;

	public Panel(PanelType type){
		this.type = type;
		arrangePanel();
	}
	
	private void arrangePanel(){
		this.setBackground(Color.WHITE);
	}
	
	

	// Do subclass level processing in this method
	protected abstract void construct();
	
	protected abstract void makeSubPanel();
	
	protected abstract JComponent makeSubPanelItems();
	
	
	public PanelType getType(){
		return type;
	}
	
	public void setType(PanelType newType){
		this.type = newType;
	}

}
