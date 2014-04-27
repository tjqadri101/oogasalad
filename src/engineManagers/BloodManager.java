package engineManagers;

import java.util.List;

import objects.GameObject;
import saladConstants.SaladConstants;
import util.SaladUtil;
/**
 * Manage the blood of a Game Object
 * For Player, manage the blood for its each life
 * @author Main Justin (Zihao) Zhang
 *
 */
public class BloodManager extends StatisticsManager{
	
	public static final int NOT_USED_BLOOD = 0;
	
	public BloodManager(){
		super();
	}

	@Override
	public void update(String info, GameObject victim, GameObject hitter) {
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, victim.colid, hitter.colid);
		if(!myMap.containsKey(condition)) return;
		hitter.changeBlood(myMap.get(condition));
	}
	
	public void update(String info, GameObject victim, int tileColid){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, victim.colid, tileColid);
		if(!myMap.containsKey(condition)) return;
		victim.changeBlood(myMap.get(condition));
	}

	public void update(String info, String oldLevelOrSceneID, String newLevelOrSceneID, GameObject object) {
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, oldLevelOrSceneID, newLevelOrSceneID);
		if(!myMap.containsKey(condition)) return;
		object.changeBlood(myMap.get(condition));
	}

	public void update(String condition, GameObject object) {
		if(!myMap.containsKey(condition)) return;
		object.changeBlood(myMap.get(condition));
	}

	@Override
	public List<String> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Do not call this method
	 */
	@Override
	public void update(String info, String oldLevelOrSceneID, String newLevelOrSceneID) {}

	/**
	 * Do not call this method
	 */
	@Override
	public void update(String condition) {}

}
