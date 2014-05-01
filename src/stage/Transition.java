package stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import saladConstants.SaladConstants;
/**
 * 
 * @author Main Isaac (Shenghan) Chen
 *
 */
public class Transition {
	
	public static String[] TRANSITION_STATE = new String[]{"StartGame", "LevelDone", "LifeLost", "GameOver",
		"Title", "Paused"};
	
	public static List<String> SEQUENCE =
			Arrays.asList(new String[]{"StartGame", "LevelDone", "LifeLost", "GameOver"});
	
	protected String myType;
	protected String myBackground;
	protected Map<double[], String> myInstructionMap;
	protected Map<double[], String> myImageMap;
	protected int frame_to_stay;
	
	public Transition(String type){
		myType = type;
		frame_to_stay = -1;
		myInstructionMap = new HashMap<double[], String>();
		myImageMap = new HashMap<double[], String>();
	}
	
	public void setBackground(String url){
		if (SEQUENCE.contains(myType)) {return;}
		myBackground = url;
	}
	
	public void setFrame(int frame){
		if (!SEQUENCE.contains(myType)) {return;}
		frame_to_stay = frame;
	}
	
	public void addInstruction(String instrution, int xpos, int ypos){
		myInstructionMap.put(new double[]{xpos, ypos}, instrution);
	}
	
	public void addImage(String image, int xpos, int ypos){
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
		List<String> answer = new ArrayList<String>();
		String prefix = SaladConstants.MODIFY_TRANSITION_STATE + SaladConstants.SEPARATOR + 
				SaladConstants.GAME_STATE + SaladConstants.SEPARATOR + myType;
		if (myBackground != null) {answer.add(prefix + SaladConstants.SEPARATOR + 
				SaladConstants.BACKGROUND + SaladConstants.SEPARATOR + myBackground);}
		if (frame_to_stay != -1) {answer.add(prefix + SaladConstants.SEPARATOR + 
				SaladConstants.FRAME + SaladConstants.SEPARATOR + frame_to_stay);}
		for (Entry<double[], String> entry: getInstructions()) {
			double x = entry.getKey()[0];
			double y = entry.getKey()[1];
			String instruction = entry.getValue();
			answer.add(prefix + SaladConstants.SEPARATOR + SaladConstants.DISPLAY_MESSAGE + 
					SaladConstants.SEPARATOR + x + SaladConstants.SEPARATOR + y + 
					SaladConstants.SEPARATOR + instruction);
		}
		for (Entry<double[], String> entry: getImages()) {
			double x = entry.getKey()[0];
			double y = entry.getKey()[1];
			String imgfile = entry.getValue();
			answer.add(prefix + SaladConstants.SEPARATOR + SaladConstants.DISPLAY_MESSAGE + 
					SaladConstants.SEPARATOR + x + SaladConstants.SEPARATOR + y + 
					SaladConstants.SEPARATOR + imgfile);
		}	
		return answer;
	}
	
}
