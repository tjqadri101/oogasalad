package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import objects.Player;
import saladConstants.SaladConstants;
import statistics.GameStats;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Manage the overall lives and change of the lives for the Players throughout the whole Game
 * @author Main Justin (Zihao) Zhang
 *
 */
public class LiveManager extends StatisticsManager {
	
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
	
	/**
	 * Set the initial number of lives of a player
	 * @param lives
	 * @param playerID
	 */
	public void setInitLives(int lives, int playerID){
		if(!myPlayerMap.containsKey(playerID)) return;
		myInitLifeMap.put(myPlayerMap.get(playerID), lives);
		GameStats.set(myPlayerMap.get(playerID).getObjectName() + " " + SaladConstants.LIVE, lives);
	}
	
	/**
	 * Get the current number of lives of a player
	 * @param playerID
	 * @return
	 */
	public int getCurrentLife(int playerID){
		if(!myPlayerMap.containsKey(playerID) || myPlayerMap == null) return DEFAULT_NULL_LIVES;
		return myCurrentLifeMap.get(myPlayerMap.get(playerID));
	}
	
	/**
	 * Decrement a live for a player matched with the playerID
	 * @param playerID
	 */
	public void decrementLive(int playerID){
		int currentLive = myCurrentLifeMap.get(myPlayerMap.get(playerID));
		currentLive --;
		myCurrentLifeMap.put(myPlayerMap.get(playerID), currentLive);
		GameStats.update(myPlayerMap.get(playerID).getObjectName() + " " + SaladConstants.LIVE, -1);
	}
	
	public void addPlayer(Player player){
		myPlayerMap.put(player.getID(), player);
		myInitLifeMap.put(player, DEFAULT_INITIAL_LIVES);
		restore();
	}
	
	public void setRestore(boolean ifRestore){
		myRestore = ifRestore;
	}

	/**
	 * Called by engine to update live while finishing a level
	 */
	public void updateLevelDoneLives(){
		if(myRestore){ restore(); }
	}
	
	/**
	 * Do not call this method directly
	 * Used to restore every player's current live to its initial live
	 */
	protected void restore(){
		for(Player o: myPlayerMap.values()){
			if(myCurrentLifeMap.containsKey(o)){
				GameStats.update(o.getObjectName() + " " + SaladConstants.LIVE, myInitLifeMap.get(o) - myCurrentLifeMap.get(o));
			}
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
			type = SaladConstants.SET_INIT_LIVES;
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_LIFE_MANAGER, type, false, params));
		}
		for (String condition: myMap.keySet()){
			String type = null;
			StringBuilder param = new StringBuilder();
			param.append(myMap.get(condition) + SaladConstants.SEPARATOR);
			param.append(condition);
			List<Object> params = SaladUtil.convertStringListToObjectList(SaladUtil.convertStringArrayToList(
					param.toString().split(SaladConstants.SEPARATOR)));
			if(condition.startsWith(SaladConstants.COLLISION)) type = SaladConstants.SET_COLLISION_LIVE;
			else if(condition.startsWith(SaladConstants.TILE_COLLISION)) type = SaladConstants.SET_TILE_COLLISION_LIVE;
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_LIFE_MANAGER, type, false, params));
		}
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_LIFE_MANAGER, SaladConstants.RESTORE_LIFE_BY_LEVEL, myRestore));
		return answer;
	}

	@Override
	public void update(String info, GameObject victim, GameObject hitter) {
		int victimColid = checkIfSideDetectorColid(victim);
		int hitterColid = checkIfSideDetectorColid(hitter);
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, victimColid, hitterColid);
		if(!myMap.containsKey(condition)) return;
		if(hitter instanceof Player){
			Player p = (Player) hitter;
			int changeLive = myMap.get(condition);
			GameStats.update(p.getObjectName() + " " + SaladConstants.LIVE, changeLive);
			int finalLive = myCurrentLifeMap.get(p) + changeLive;
			myCurrentLifeMap.put(p, finalLive);	
		}
	}
	
	public void update(String info, GameObject victim, int tileColid){
		int victimColid = checkIfSideDetectorColid(victim);
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, victimColid, tileColid);
		if(!myMap.containsKey(condition)) return;
		if(victim instanceof Player){
			Player p = (Player) victim;
			int changeLive = myMap.get(condition);
			GameStats.update(p.getObjectName() + " " + SaladConstants.LIVE, changeLive);
			int finalLive = myCurrentLifeMap.get(p) + changeLive;
			myCurrentLifeMap.put(p, finalLive);	
		}
	}
}
