package engineManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import objects.GameObject;
import objects.SideDetector;
import reflection.Reflection;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Intended to manage all collision behaviors between objects and objects, objects and tiles
 * @author Main Justin (Zihao) Zhang
 * @contribution (set directional collision) Shenghan Chen
 */
public class CollisionManager {
	
	protected Map<String, List<Object>> myCollisionMap;
	protected Map<String, List<Object>> myTileCollisionMap;
	protected List<String> myAttributes;
	
	public CollisionManager(){
		myCollisionMap = new HashMap<String, List<Object>>();
		myTileCollisionMap = new HashMap<String, List<Object>>();
		myAttributes = new ArrayList<String>();
	}
	
	/**
	 * Add a collision behavior between object and object
	 * @param hitterColid
	 * @param type collision behavior
	 * @param victimColid
	 * @param args parameters
	 */
	public void addCollisionPair(int victimColid, String type, int hitterColid, String direction, Object ... args){
		addPairs(SaladConstants.MODIFY_COLLISION_BEHAVIOR, myCollisionMap, 
				victimColid, type, hitterColid, direction, args);
	}
	
	/**
	 * Do not call this method directly
	 * @param key
	 * @param map
	 * @param colid1
	 * @param type
	 * @param colid2
	 * @param args
	 */
	protected void addPairs(String key, Map<String, List<Object>> map, int colid1, String type, int colid2, String direction, Object ... args){
		addPair(map, colid1, type, colid2, direction, args);
		List<Object> attributeParams = SaladUtil.copyObjectList(SaladUtil.convertArgsToObjectList(args));
		attributeParams.add(0, colid2);
		attributeParams.add(1, direction);
		String attribute = AttributeMaker.addAttribute(key, 
				SaladConstants.COLLISION_ID, colid1, type, true, attributeParams);
		myAttributes.add(attribute);
	}
	
	/**
	 * Do not call this method directly
	 * @param map
	 * @param colid1
	 * @param type
	 * @param colid2
	 * @param direction
	 * @param args
	 */
	protected void addPair(Map<String, List<Object>> map, int colid1, String type, int colid2, 
			String direction, Object ... args){
		List<Object> objects = SaladUtil.convertArgsToObjectList(args);
		objects.add(0, type);
		String pair = colid1 + SaladConstants.SEPARATOR + colid2;
		map.put(pair, objects);
	}
	
	/**
	 * Do not call this method directly
	 * @param key
	 * @param map
	 * @param colid1
	 * @param type
	 * @param colid2
	 * @param args
	 */
	protected void addTilePairs(String key, Map<String, List<Object>> map, int colid1, String type, char colid2, String direction, Object ... args){
		addPair(map, colid1, type, colid2, direction, args);
		List<Object> attributeParams = SaladUtil.copyObjectList(SaladUtil.convertArgsToObjectList(args));
		attributeParams.add(0, colid2);
		attributeParams.add(1, direction);
		String attribute = AttributeMaker.addAttribute(key, 
				SaladConstants.COLLISION_ID, colid1, type, true, attributeParams);
		myAttributes.add(attribute);
	}
	
	/**
	 * Add a collision behavior between object and tile
	 * @param victimColid
	 * @param type collision behavior
	 * @param tileColid
	 * @param args parameters
	 */
	public void addTileCollisionPair(int victimColid, String type, char tileColid, String direction, Object ... args){
		addTilePairs(SaladConstants.MODIFY_TILE_COLLISION_BEHAVIOR, myTileCollisionMap, 
				victimColid, type, tileColid, direction, args);
	}
	
	/**
	 * Get the collision behavior between two objects
	 * @param victimColid
	 * @param hitterColid
	 * @return Object list
	 */
	protected List<Object> getCollisionBehavior(int victimColid, int hitterColid){
		String pair = victimColid + SaladConstants.SEPARATOR + hitterColid;
		return myCollisionMap.get(pair);
	}
	
	/**
	 * Called by Game Object to do collision with other Game Object
	 * @param behaviors
	 * @param victim
	 * @param hitter
	 */
	public void hitObject(ResourceBundle behaviors, GameObject victim, GameObject hitter){
		List<Object> parameters = SaladUtil.copyObjectList(
				getCollisionBehavior(victim.colid, hitter.colid));
		if (parameters == null) return; 
		String collisionBehavior = (String) parameters.get(0);
		parameters.remove(0);
		parameters.add(0, hitter);
		SaladUtil.behaviorReflection(behaviors, collisionBehavior,
				parameters, SaladConstants.COLLIDE, victim);
	}
	
	/**
	 * Called by Game Object to do collision with tiles
	 * @param behaviors
	 * @param victim
	 * @param tilecid
	 * @param tx
	 * @param ty
	 * @param txsize
	 * @param tysize
	 */
	public void hitTile(ResourceBundle behaviors, GameObject victim, int tilecid, int tx, int ty, int txsize, int tysize){
		List<Object> parameters = SaladUtil.copyObjectList(
				getTileCollisionBehavior(victim.colid, tilecid));
		if (parameters == null) return; 
		String collisionBehavior = (String) parameters.get(0);
		parameters.remove(0);
		parameters.add(0, tysize);
		parameters.add(0, txsize);
		parameters.add(0, ty);
		parameters.add(0, tx);
		parameters.add(0, tilecid);
		SaladUtil.behaviorReflection(behaviors, collisionBehavior,
				parameters, SaladConstants.COLLIDE, victim);
	}
	
	/**
	 * Get the collision behavior between an object and a tile
	 * @param victimColid
	 * @param tileColid
	 * @return Object list
	 */
	public List<Object> getTileCollisionBehavior(int victimColid, int tileColid){
		String pair = victimColid + SaladConstants.SEPARATOR + tileColid;
		return myTileCollisionMap.get(pair);
	}
	
	/**
	 * Get all the current available collision pairs
	 * @return a set of int array (array[0] hitter, array[1] victim)
	 */
	public Set<int[]> getCollisionPair(){
		return getPairs(myCollisionMap);
	}
	
	/**
	 * Get all the current available tile collision pairs
	 * @return a set of int array (array[0] tile, array[1] victim)
	 */
	public Set<int[]> getTileCollisionPair(){
		return getPairs(myTileCollisionMap);
	}
	
	/**
	 * Do not call this method directly
	 * @param map
	 * @return a set of Colid pairs
	 */
	protected Set<int[]> getPairs(Map<String, List<Object>> map){
		Set<int[]> answer = new HashSet<int[]>();
		for(String s: map.keySet()){
			String[] pair = s.split(SaladConstants.SEPARATOR);
			int pair1 = Integer.parseInt(pair[0]);
			int pair2 = Integer.parseInt(pair[1]);
			int[] adder = new int[]{pair1, pair2};
			answer.add(adder);
		}
		return answer;
	}
	
	/**
	 * If the collision involves direction, reflect on this method
	 * @param victimColid
	 * @param type
	 * @param hitterColid
	 * @param direction
	 * @param args
	 */
	public void setDirectionalCollisionBehavior(int victimColid, String type, int hitterColid, String direction, Object ... args){
		int dir = Arrays.asList(new String[]{SaladConstants.Top,SaladConstants.BOTTOM,SaladConstants.LEFT,SaladConstants.RIGHT, SaladConstants.ALL}).indexOf(direction);
		if (dir == -1) return;
		if(dir == 4) addCollisionPair(victimColid, type, hitterColid, direction, args);
		else addCollisionPair(SideDetector.SDcid(victimColid, dir), type, hitterColid,direction,args);
	}
	
	/**
	 * If the collision involves direction, reflect on this method
	 * @param victimColid
	 * @param type
	 * @param tileColid
	 * @param direction
	 * @param args
	 */
	public void setDirectionalTileCollisionBehavior(int victimColid, String type, char tileColid, String direction, Object ... args){
		int dir = Arrays.asList(new String[]{SaladConstants.Top,SaladConstants.BOTTOM,SaladConstants.LEFT,SaladConstants.RIGHT, SaladConstants.ALL}).indexOf(direction);
		if (dir == -1) return;
		if(dir == 4) addTileCollisionPair(victimColid, type, tileColid, direction, args);
		else addTileCollisionPair(SideDetector.SDcid(victimColid, dir), type, tileColid, direction, args);
	}
	
	/**
	 * Get the attributes of all collision behaviors
	 * @return String List
	 */
	public List<String> getAttributes(){
		return myAttributes;
	}
	
}
