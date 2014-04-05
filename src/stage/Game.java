package stage;

import java.util.List;

import objects.Player;

public class Game {
	
	protected List<Level> myLevels;
	protected List<Scene> nonLevelStates;
	protected Player myPlayer;
	
	public Game(){
		
	}
	
	public void setLevels(List<Level> levels){
		myLevels = levels;
	}

}
