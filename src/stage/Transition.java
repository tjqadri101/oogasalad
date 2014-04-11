package stage;

import java.util.HashMap;
import java.util.Map;

public class Transition {
	
	private StateType myType;
	private String myBackground;
	private Map<double[], String> myInstructionMap;
	private int frame_to_stay;
	private char continue_game_key;
	
	Transition(StateType type){
		myType = type;
		myInstructionMap = new HashMap<double[], String>();
	}
	
	public void setBackground(String url){
		myBackground = url;
	}
	
	public void setKey(char key){
		continue_game_key = key;
	}
	
	public void setFrame(int frame){
		frame_to_stay = frame;
	}
	
	public String getBackground(){
		return myBackground;
	}
	
	public char getKey(){
		return continue_game_key;
	}
	
	public int getFrame(){
		return frame_to_stay;
	}
	
	
	
	public enum StateType {
		Title, InGame, StartGame, StartLevel, LevelDone, LifeLost, GameOver;
	}
}
