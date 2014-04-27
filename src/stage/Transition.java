package stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Transition {
	
	public static String[] TRANSITION_STATE = new String[]{"Title", "InGame", "StartGame",
		"StartLevel", "LevelDone", "LifeLost", "GameOver"};
	
	protected String myType;
	protected String myBackground;
	protected Map<double[], String> myInstructionMap;
	protected Map<double[], String> myImageMap;
	protected int frame_to_stay;
	
	public Transition(String type){
		myType = type;
		myInstructionMap = new HashMap<double[], String>();
		myImageMap = new HashMap<double[], String>();
	}
	
	public void setBackground(String url){
		myBackground = url;
	}
	
	public void setFrame(int frame){
		frame_to_stay = frame;
	}
	
	public void addInstruction(double xpos, double ypos, String instrution){
		myInstructionMap.put(new double[]{xpos, ypos}, instrution);
	}
	
	public void addImage(double xpos, double ypos, String image){
		myImageMap.put(new double[]{xpos, ypos}, image);
	}

	public String getBackground(){
		return myBackground;
	}
	
	public int getFrame(){
		return frame_to_stay;
	}
	
	public Set<Entry<double[], String>> getInstructions(){
		return myInstructionMap.entrySet();
	}
	
	public Set<Entry<double[], String>> getImages(){
		return myImageMap.entrySet();
	}
	
	public List<String> getAttributes() {
		//what's the convention ?
		return null;
	}
	
}
