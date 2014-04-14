package stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transition {
	
	protected StateType myType;
	protected String myBackground;
	protected Map<double[], String> myInstructionMap;
	protected int frame_to_stay;
	protected char continue_game_key;
	
	public Transition(StateType type){
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
	
	public void addInstruction(double xpos, double ypos, String instrution){
		myInstructionMap.put(new double[]{xpos, ypos}, instrution);
	}
	
	public StateType getType(){
		return myType;
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
	
	public Map<double[], String> getInstructions(){
		return myInstructionMap;
	}
	
	public List<String> getAttributes() {
		//what's the convention ?
		return null;
	}
	
	public enum StateType {
		Title, InGame, StartGame, StartLevel, LevelDone, LifeLost, GameOver;
	}
	
}
