package engineManagers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import objects.Player;
import saladConstants.SaladConstants;
import util.SaladUtil;
/**
 * Manage the overall lives of the Player throughout the whole Game
 * @author Main Justin (Zihao) Zhang
 *
 */
public class LifeManager extends StatisticsManager{
	
	public static final int DEFAULT_INITIAL_LIVES = 5;
	public static final int DEFAULT_NULL_LIVES = 0;
	
	protected Map<Integer, Player> myPlayerMap;
	protected Map<Player, Integer> myInitLifeMap;
	protected Map<Player, Integer> myCurrentLifeMap;
	
	public LifeManager(){
		super();
		myInitLifeMap = new HashMap<Player, Integer>();
		myCurrentLifeMap = new HashMap<Player, Integer>();
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
	}

	public void updateLevelDoneLives(){
		if(myRestoreLevel){
			myLives = myInitLives;
		}
	}

	@Override
	public void update(String info, GameObject victim, GameObject hitter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String info, String oldLevelOrSceneID,
			String newLevelOrSceneID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String condition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
}
