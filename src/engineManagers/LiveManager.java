package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.Player;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Manage the overall lives of the Players throughout the whole Game
 * @author Main Justin (Zihao) Zhang
 *
 */
public class LiveManager {
	
	public static final int DEFAULT_INITIAL_LIVES = 5;
	public static final int DEFAULT_NULL_LIVES = 0;
	
	protected boolean myRestore;
	protected Map<Integer, Player> myPlayerMap;
	protected Map<Player, Integer> myInitLifeMap;
	protected Map<Player, Integer> myCurrentLifeMap;
	
	public LiveManager(){
		myPlayerMap = new HashMap<Integer, Player>();
		myInitLifeMap = new HashMap<Player, Integer>();
		myCurrentLifeMap = new HashMap<Player, Integer>();
		myRestore = true;
	}
	
	public void setInitLives(int lives, int playerID){
		if(!myPlayerMap.containsKey(playerID)) return;
		myInitLifeMap.put(myPlayerMap.get(playerID), lives);
	}
	
	public int getCurrentLife(int playerID){
		if(!myPlayerMap.containsKey(playerID)) return DEFAULT_NULL_LIVES;
		return myCurrentLifeMap.get(myPlayerMap.get(playerID));
	}
	
	public void addPlayer(Player player){
		myPlayerMap.put(player.getID(), player);
		myInitLifeMap.put(player, DEFAULT_INITIAL_LIVES);
		restore();
	}
	
	public void setRestore(boolean ifRestore){
		myRestore = ifRestore;
	}

	public void updateLevelDoneLives(){
		if(myRestore){ restore(); }
	}
	
	/**
	 * Do not call this method directly
	 * Used to restore every player's current live to its initial live
	 */
	protected void restore(){
		for(Player o: myPlayerMap.values()){
			myCurrentLifeMap.put(o, myInitLifeMap.get(o));
		}
	}

	public List<String> getAttributes() {
		List<String> answer = new ArrayList<String>();
		for(Player player: myInitLifeMap.keySet()){
			String type = null;
			StringBuilder param = new StringBuilder();
			param.append(myInitLifeMap.get(player) + SaladConstants.SEPARATOR);
			param.append(player.getID());
			List<Object> params = SaladUtil.convertStringListToObjectList(SaladUtil.convertStringArrayToList(
					param.toString().split(SaladConstants.SEPARATOR)));
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_BLOOD_MANAGER, type, false, params));
		}
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_BLOOD_MANAGER, SaladConstants.RESTORE_LIFE_BY_LEVEL, myRestore));
		return answer;
	}
}
