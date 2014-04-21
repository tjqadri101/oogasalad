package engineManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import objects.GameObject;
import objects.SideDetecter;
import saladConstants.SaladConstants;
import util.SaladUtil;

public class CollisionManager {
	
	protected Map<String, List<Object>> myCollisionMap;
	protected Map<String, List<Object>> myTileCollisionMap;
	protected List<String> myAttributes;
	
	public CollisionManager(){
		myCollisionMap = new HashMap<String, List<Object>>();
		myTileCollisionMap = new HashMap<String, List<Object>>();
		myAttributes = new ArrayList<String>();
	}
	

	public void addCollisionPair(int hitterColid, String type, int victimColid, Object ... args){
		List<Object> objects = SaladUtil.convertArgsToObjectList(args);
		StringBuilder attribute = new StringBuilder();
		attribute.append(SaladConstants.MODIFY_COLLISION_BEHAVIOR + "," + SaladConstants.COLLISION_ID + hitterColid + "," + type + "," + type + "," + victimColid);
		for(Object o: objects){
			String att = o.toString();
			attribute.append("," + att);
		}
		myAttributes.add(attribute.toString());
		objects.add(0, type);
		String pair = hitterColid + SaladConstants.SEPERATER + victimColid;
		myCollisionMap.put(pair, objects);
	}
	
	public void addTileCollisionPair(int victimColid, String type, int tileColid, Object ... args){
		List<Object> objects = SaladUtil.convertArgsToObjectList(args);
		StringBuilder attribute = new StringBuilder();
		attribute.append(SaladConstants.MODIFY_TILE_COLLISION_BEHAVIOR + "," + SaladConstants.COLLISION_ID + victimColid + "," + type + "," + type + "," + tileColid);
		for(Object o: objects){
			String att = o.toString();
			attribute.append("," + att);
		}
		myAttributes.add(attribute.toString());
		objects.add(0, type);
		String pair = tileColid + SaladConstants.SEPERATER + victimColid;
		myTileCollisionMap.put(pair, objects);
	}
	
	public List<Object> getCollisionBehavior(int victimColid, int hitterColid){
		String pair = hitterColid + SaladConstants.SEPERATER + victimColid;
		return myCollisionMap.get(pair);
	}
	
	public List<Object> getTileCollisionBehavior(int victimColid, int tileColid){
		String pair = tileColid + SaladConstants.SEPERATER + victimColid;
		return myTileCollisionMap.get(pair);
	}
	
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
	
	public void setSideCollisionDetecter(GameObject object, String direction, int cid){
		int dir = Arrays.asList(new String[]{"up","bottom","left","right"}).indexOf(direction);
		if (dir == -1) return;
		SideDetecter sd = object.getSideDetecters()[dir];
		if (sd == null) object.getSideDetecters()[dir] = new SideDetecter(object,dir,cid);
		else sd.colid = cid;
	}
	
	public List<String> getAttributes(){
		return myAttributes;
	}
}
