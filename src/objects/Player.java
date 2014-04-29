package objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engineManagers.BloodManager;
import engineManagers.CollisionManager;
import engineManagers.LiveManager;
import engineManagers.RevivalManager;
import engineManagers.ScoreManager;
import engineManagers.TriggerEventManager;
import reflection.Reflection;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * @author Main Justin (Zihao) Zhang
 */
public class Player extends GameObject {
	
	protected Map<Integer, String> myKeyMap;
	protected List<String> myNonClearKeys;
	protected double myMovingXSpeed;
	protected double myMovingYSpeed;
	protected TriggerEventManager myTEManager;
	
	public Player(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, 
			String name, int collisionId, int lives, 
			CollisionManager collisionManager, ScoreManager scoreManager, 
			BloodManager bloodManager, RevivalManager revivalManager, LiveManager liveManager,
			TriggerEventManager triggerEventManager) {
		super(uniqueID, gfxname, xsize, ysize, xpos, ypos, name, collisionId, lives, collisionManager, 
				scoreManager, bloodManager, revivalManager, liveManager, triggerEventManager);
		myKeyMap = new HashMap<Integer, String>();
		myMovingXSpeed = SaladConstants.DEFAULT_ACTOR_SPEED;
		myMovingYSpeed = SaladConstants.DEFAULT_ACTOR_SPEED;

		myNonClearKeys = SaladUtil.getListFromPropertiesFile(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.NONCLEAR_KEYS_FILE, 
		                                                     SaladConstants.NON_CLEAR_KEYS, SaladConstants.SEPARATOR);
		myTEManager = new TriggerEventManager();
	}
	
	/**
	 * Called by Factory to set the key
	 * @param key
	 * @param type
	 */
	public void setKey(int key, String type){
		myKeyMap.put(key, type);
	}
	
	@Override
	public void move(){
		checkKeys();
		super.move();
		
	}
	
	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize) {
		super.hit_bg(tilecid, tx, ty, txsize, tysize);
		setImage(myDefaultImage);
	}
	
	@Override
	public void jump() {
		super.jump();
		myAnimationManager.updateImage(SaladConstants.UPDATE_JUMP);
	}

	
	protected void checkKeys(){
		for(int key: myKeyMap.keySet()){
			if(eng.getKey(key)){
				String methodName = myKeyMap.get(key);
				Reflection.callMethod(this, methodName);
				if(!myNonClearKeys.contains(methodName)) eng.clearKey(key);
			}
		}
	}
	
	/**
	 * Called by the Game Authorizing Environment to display the current set keys
	 * @return Map maps from int key to String function (i.e. jump)
	 */
	public Map<Integer, String> getKeyMap(){
		return myKeyMap;
	}
	
	public void moveUp(){
		if (y > 0) {
			y -= myMovingYSpeed*eng.getGameSpeed();
		}
		myYHead = SaladConstants.NEGATIVE_DIRECTION;
		myXHead = SaladConstants.NEUTRAL_DIRECTION;
	}
	
	public void moveDown(){
		if (y + getYSize() < eng.pfHeight()) {
			y += myMovingYSpeed*eng.getGameSpeed();
		}
		myYHead = SaladConstants.POSITIVE_DIRECTION;
		myXHead = SaladConstants.NEUTRAL_DIRECTION;
	}
	
	public void moveLeft(){
		if (x > 0) {
			x -= myMovingXSpeed*eng.getGameSpeed();
		}
		myXHead = SaladConstants.NEGATIVE_DIRECTION;
		myYHead = SaladConstants.NEUTRAL_DIRECTION;
	}
	
	public void moveRight(){
		if (x + getXSize() < eng.pfWidth()) {
			x += myMovingXSpeed*eng.getGameSpeed();
		}
		myXHead = SaladConstants.POSITIVE_DIRECTION;
		myYHead = SaladConstants.NEUTRAL_DIRECTION;
	}
	
	@Override
	public void die(){
		super.die();
		myLiveManager.decrementLive(getID());
	}
	
	@Override
	public List<String> getAttributes(){
		List<String> answer = super.getAttributes();
		for(int key: myKeyMap.keySet()){
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_PLAYER, SaladConstants.ID, myUniqueID, 
					SaladConstants.SET_KEY, false, key, myKeyMap.get(key)));
		}
		return answer;
	}

}
