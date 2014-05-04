package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import objects.Player;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Manage the overall lives and change of the lives for the Player(s) throughout the whole Game
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
		super();
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
		myCurrentLifeMap.put(myPlayerMap.get(playerID), lives);
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
	public void changeLive(int playerID, int change){
		int currentLive = myCurrentLifeMap.get(myPlayerMap.get(playerID));
		currentLive += change;
		myCurrentLifeMap.put(myPlayerMap.get(playerID), currentLive);
	}
	
	/**
	 * Called to initiate a live for a player be default
	 * @param player
	 */
	public void addPlayer(Player player){
		myPlayerMap.put(player.getID(), player);
		myInitLifeMap.put(player, DEFAULT_INITIAL_LIVES);
		restore();
	}
	
	/**
	 * Set if a player's live is restored for transition of level done
	 * @param ifRestore
	 */
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
			myCurrentLifeMap.put(o, myInitLifeMap.get(o));
		}
	}

	/**
	 * Called to update the live of a player for a condition between two objects
	 */
	@Override
	public void update(String info, GameObject victim, GameObject hitter) {
		int victimColid = checkIfSideDetectorColid(victim);
		int hitterColid = checkIfSideDetectorColid(hitter);
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, victimColid, hitterColid);
		update(condition, victim);
	}
	
	/**
	 * Do not call this method directly
	 * @param condition
	 * @param victim
	 */
	protected void update(String condition, GameObject victim){
		if(!myMap.containsKey(condition)) return;
		if(victim instanceof Player){
			Player p = (Player) victim;
			int changeLive = myMap.get(condition);
			int finalLive = myCurrentLifeMap.get(p) + changeLive;
			myCurrentLifeMap.put(p, finalLive);	
		}
	}
	
	/**
	 * Called to update the live of a player for a condition between an object and a tile
	 */
	@Override
	public void update(String info, GameObject victim, int tileColid){
		int victimColid = checkIfSideDetectorColid(victim);
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, victimColid, tileColid);
		update(condition, victim);
	}
	
	
	/**
	 * Called to get the attribute of LiveManager
	 */
	@Override
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
}
