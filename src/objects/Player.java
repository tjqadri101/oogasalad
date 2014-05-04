package objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.GameEngine;
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
	protected int myXofs;
	protected int myYofs;
	protected double myMovingXSpeed;
	protected double myMovingYSpeed;
	protected boolean myCanMoveInAir;
	
	public Player(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, 
			String name, int collisionId, int lives, 
			CollisionManager collisionManager, ScoreManager scoreManager, 
			BloodManager bloodManager, RevivalManager revivalManager, LiveManager liveManager,
			TriggerEventManager triggerEventManager) {
		super(uniqueID, gfxname, xsize, ysize, xpos, ypos, name, collisionId, lives, collisionManager, 
				scoreManager, bloodManager, revivalManager, liveManager, triggerEventManager);
		
		initViewOffset();
		myKeyMap = new HashMap<Integer, String>();
		myMovingXSpeed = SaladConstants.DEFAULT_ACTOR_SPEED;
		myMovingYSpeed = SaladConstants.DEFAULT_ACTOR_SPEED;
		myCanMoveInAir = true;
		myNonClearKeys = SaladUtil.getListFromPropertiesFile(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.NONCLEAR_KEYS_FILE, 
		                                                     SaladConstants.NON_CLEAR_KEYS, SaladConstants.SEPARATOR);
	}
	
	/**
	 * Set a key for an action type
	 * @param key
	 * @param action type
	 */
	public void setKey(int key, String type){
		myKeyMap.put(key, type);
	}
	
	/**
	 * Executed for each frame
	 * Called from the engine
	 */
	@Override
	public void move(){
		checkKeys();
		super.move();
	}
	
	/**
	 * Do not call this method directly
	 * Check valid keys that are already set and execute the corresponding actions
	 */
	protected void checkKeys(){
		for(int key: myKeyMap.keySet()){
			if(eng.getKey(key)){
				String methodName = myKeyMap.get(key);
				try{
					Reflection.getDeclaredMethod(this, methodName);	
					Reflection.callMethod(this, methodName);
				} catch (Exception e){
					Reflection.callMethod(myActionManager, SaladConstants.OBJECT_DO_ACTION, methodName);	
				}
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
		setYHead(SaladConstants.NEGATIVE_DIRECTION);
		setXHead(myXHead = SaladConstants.NEUTRAL_DIRECTION);
		if(myAirCounter == 0 && !myCanMoveInAir) return;
		if (y > 0) y -= myMovingYSpeed*eng.getGameSpeed();
	}
	
	public void moveDown(){
		setYHead(SaladConstants.POSITIVE_DIRECTION);
		setXHead(SaladConstants.NEUTRAL_DIRECTION);
		if(myAirCounter == 0 && !myCanMoveInAir) return;
		if (y + getYSize() < eng.pfHeight()) y += myMovingYSpeed*eng.getGameSpeed();
	}
	
	public void moveLeft(){
		setXHead(SaladConstants.NEGATIVE_DIRECTION);
		setYHead(SaladConstants.NEUTRAL_DIRECTION);
		if(myAirCounter == 0 && !myCanMoveInAir) return;
		if (x > 0) x -= myMovingXSpeed*eng.getGameSpeed();
	}
	
	public void moveRight(){
		setXHead(SaladConstants.POSITIVE_DIRECTION);
		setYHead(SaladConstants.NEUTRAL_DIRECTION);
		if(myAirCounter == 0 && !myCanMoveInAir) return;
		if (x + getXSize() < eng.pfWidth()) x += myMovingXSpeed*eng.getGameSpeed();
	}
	
	public void setCanMoveInAir(boolean canMoveInAir){
		myCanMoveInAir = canMoveInAir;
	}
	
	public void setPlayerSpeed(double xspeed, double yspeed){
		myMovingXSpeed = xspeed;
		myMovingYSpeed = yspeed;
	}
	
	@Override
	public List<String> getAttributes(){
		List<String> answer = super.getAttributes();
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_PLAYER, SaladConstants.ID, myUniqueID, 
				SaladConstants.PLAYER_SPEED, false, myMovingXSpeed, myMovingYSpeed));
		for(int key: myKeyMap.keySet()){
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_PLAYER, SaladConstants.ID, myUniqueID, 
					SaladConstants.SET_KEY, false, key, myKeyMap.get(key)));
		}
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_PLAYER, SaladConstants.ID, myUniqueID, 
				SaladConstants.CAN_MOVE_IN_AIR, false, myCanMoveInAir));
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_PLAYER, SaladConstants.ID, myUniqueID, 
				SaladConstants.VIEW_OFFSET, false, myXofs, myYofs));
		return answer;
	}
	
	/**
	 * Initiate the view off set
	 */
	public void initViewOffset(){
		myXofs = GameEngine.JGPOINT_X/2;
		myYofs = GameEngine.JGPOINT_Y/2;
	}
	
	/**
	 * Set the view off set
	 * @param xofs
	 * @param yofs
	 */
	public void setViewOffset(int xofs, int yofs){
		myXofs = xofs;
		myYofs = yofs;
	}
	
	/**
	 * Called by Game Engine to get the x off set
	 * @return
	 */
	public int getXofs(){
		return myXofs;
	}
	
    /**
     * Called by Game Engine to get the y off set
     * @return
     */
	public int getYofs(){
		return myYofs;
	}

}
