package engineManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import objects.GameObject;
import objects.SideDetector;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Intended to manage all collision behaviors between objects and objects, objects and tiles
 * @author Main Justin (Zihao) Zhang
 * @contribution Shenghan Chen
 *
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
	public void addCollisionPair(int hitterColid, String type, int victimColid, Object ... args){
		List<Object> objects = SaladUtil.convertArgsToObjectList(args);
		List<Object> attributeParams = SaladUtil.copyObjectList(objects);
		attributeParams.add(0, victimColid);
		String attribute = AttributeMaker.addAttribute(SaladConstants.MODIFY_COLLISION_BEHAVIOR, 
				SaladConstants.COLLISION_ID, hitterColid, type, true, attributeParams);
		myAttributes.add(attribute);
		objects.add(0, type);
		String pair = hitterColid + SaladConstants.SEPERATER + victimColid;
		myCollisionMap.put(pair, objects);
	}
	
	/**
	 * Add a collision behavior between object and tile
	 * @param victimColid
	 * @param type collision behavior
	 * @param tileColid
	 * @param args parameters
	 */
	public void addTileCollisionPair(int victimColid, String type, int tileColid, Object ... args){
		List<Object> objects = SaladUtil.convertArgsToObjectList(args);
		List<Object> attributeParams = SaladUtil.copyObjectList(objects);
		attributeParams.add(0, tileColid);
		String attribute = AttributeMaker.addAttribute(SaladConstants.MODIFY_TILE_COLLISION_BEHAVIOR, 
				SaladConstants.COLLISION_ID, victimColid, type, true, attributeParams);
		myAttributes.add(attribute);
		objects.add(0, type);
		String pair = tileColid + SaladConstants.SEPERATER + victimColid;
		myTileCollisionMap.put(pair, objects);
	}
	
	/**
	 * Get the collision behavior between two objects
	 * @param victimColid
	 * @param hitterColid
	 * @return Object list
	 */
	public List<Object> getCollisionBehavior(int victimColid, int hitterColid){
		String pair = hitterColid + SaladConstants.SEPERATER + victimColid;
		return myCollisionMap.get(pair);
	}
	
	/**
	 * Get the collision behavior between an object and a tile
	 * @param victimColid
	 * @param tileColid
	 * @return Object list
	 */
	public List<Object> getTileCollisionBehavior(int victimColid, int tileColid){
		String pair = tileColid + SaladConstants.SEPERATER + victimColid;
		return myTileCollisionMap.get(pair);
	}
	
	/**
	 * Get all the current available collision pairs
	 * @return a set of int array (array[0] hitter, array[1] victim)
	 */
	public Set<int[]> getCollisionPair(){
		Set<int[]> answer = new HashSet<int[]>();
		for(String s: myCollisionMap.keySet()){
			String[] pair = s.split(SaladConstants.SEPERATER);
			int pair1 = Integer.parseInt(pair[0]);
			int pair2 = Integer.parseInt(pair[1]);
			int[] adder = new int[]{pair1, pair2};
			answer.add(adder);
		}
		return answer;
	}
	
	/**
	 * Get all the current available tile collision pairs
	 * @return a set of int array (array[0] tile, array[1] victim)
	 */
	public Set<int[]> getTileCollisionPair(){
		Set<int[]> answer = new HashSet<int[]>();
		for(String s: myTileCollisionMap.keySet()){
			String[] pair = s.split(SaladConstants.SEPERATER);
			int pair1 = Integer.parseInt(pair[0]);
			int pair2 = Integer.parseInt(pair[1]);
			int[] adder = new int[]{pair1, pair2};
			answer.add(adder);
		}
		return answer;
	}
	
	/**
	 * Set side collision detector bars for every Game Object
	 * @param object
	 * @param direction
	 * @param cid
	 */
	public void setSideCollisionDetecter(GameObject object, String direction, int cid){
		int dir = Arrays.asList(new String[]{"up","bottom","left","right"}).indexOf(direction);
		if (dir == -1) return;
		SideDetector sd = object.getSideDetector(dir);
		if (sd == null) object.setSideDetector(new SideDetector(object,dir,cid));
		else sd.colid = cid;
	}
	
	/**
	 * Get the attributes of all collision behaviors
	 * @return String List
	 */
	public List<String> getAttributes(){
		return myAttributes;
	}
}
