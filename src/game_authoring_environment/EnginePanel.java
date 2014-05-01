package game_authoring_environment;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import controller.GAEController;

import engine.GameEngine;

public class EnginePanel extends JPanel{
	
	private GAEController gController;

	public EnginePanel(GameEngine engine,GAEController g) {
		super();
		this.add(engine);
		gController = g;
		this.addPropertyChangeListener("clickedID",new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				int selectedID = (int)(long)arg0.getNewValue();
				if(selectedID != -1){
					gController.setActorPanelSelection(selectedID);
				}								
			}
			
		});		
		this.addPropertyChangeListener("updatePos",new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				int selectedID = (int)(long)arg0.getNewValue();		
				if(selectedID != -1){
					gController.getRightPanel().updateActorPositionSpinners();
					
				}								
			}
			
		});
	}	

}
