package engineManagers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.SaladUtil;

public class CollisionManager {
	
	protected Map<int[], List<Object>> myCollisionMap;
	protected Map<int[], List<Object>> myTileCollisionMap;
	
	public CollisionManager(){
		myCollisionMap = new HashMap<int[], List<Object>>();
		myTileCollisionMap = new HashMap<int[], List<Object>>();
	}
	
	public void addCollisionPair(int victimColid, String type, int hitterColid, Object ... args){
		List<Object> objects = SaladUtil.convertArgsToObjectList(args);
		objects.add(0, type);
		int[] pair = new int[]{hitterColid, victimColid};
		myCollisionMap.put(pair, objects);
	}
	
	public void addTileCollisionPair(int victimColid, String type, int tileColid, Object ... args){
		List<Object> objects = SaladUtil.convertArgsToObjectList(args);
		objects.add(0, type);
		int[] pair = new int[]{tileColid, victimColid};
		myTileCollisionMap.put(pair, objects);
	}
	
	public List<Object> getCollisionBehavior(int victimColid, int hitterColid){
		int[] pair = new int[]{hitterColid, victimColid};
		return myCollisionMap.get(pair);
	}
	
	public List<Object> getTileCollisionBehavior(int victimColid, int tileColid){
		int[] pair = new int[]{tileColid, victimColid};
		return myTileCollisionMap.get(pair);
	}
	
	public Set<int[]> getCollisionPair(){
		return myCollisionMap.keySet();
	}
	
	public Set<int[]> getTileCollisionPair(){
		return myTileCollisionMap.keySet();
	}

}
