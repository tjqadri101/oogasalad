package game_authoring_environment;

import javax.swing.JPanel;

public abstract class Panel extends JPanel{
	
	private PanelType type = null;

	public Panel(PanelType type){
		this.type = type;
	}
	
	
	// Do subclass level processing in this method
	protected abstract void construct();
	
	
	public PanelType getType(){
		return type;
	}
	
	public void setType(PanelType newType){
		this.type = newType;
	}

}
