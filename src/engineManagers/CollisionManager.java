package engineManagers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import saladConstants.SaladConstants;
import util.SaladUtil;

public class CollisionManager {
	
	protected Map<String, List<Object>> myCollisionMap;
	protected Map<String, List<Object>> myTileCollisionMap;
	
	public CollisionManager(){
		myCollisionMap = new HashMap<String, List<Object>>();
		myTileCollisionMap = new HashMap<String, List<Object>>();
	}
	
	public void addCollisionPair(int victimColid, String type, int hitterColid, Object ... args){
		List<Object> objects = SaladUtil.convertArgsToObjectList(args);
		objects.add(0, type);
		String pair = hitterColid + SaladConstants.SEPERATER + victimColid;
//		SaladUtil.printObjectList(objects);
		myCollisionMap.put(pair, objects);
	}
	
	public void addTileCollisionPair(int victimColid, String type, int tileColid, Object ... args){
		List<Object> objects = SaladUtil.convertArgsToObjectList(args);
		objects.add(0, type);
		String pair = tileColid + SaladConstants.SEPERATER + victimColid;
//		SaladUtil.printObjectList(objects);
		myTileCollisionMap.put(pair, objects);
	}
	
	public List<Object> getCollisionBehavior(int victimColid, int hitterColid){
//		System.out.println("getCollisionBehavior: " + victimColid + " " + hitterColid);
		String pair = hitterColid + SaladConstants.SEPERATER + victimColid;
		return myCollisionMap.get(pair);
	}
	
	public List<Object> getTileCollisionBehavior(int victimColid, int tileColid){
//		System.out.println("getTileCollisionBehavior: " + victimColid + " " + tileColid);
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

}
