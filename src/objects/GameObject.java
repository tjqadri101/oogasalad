package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import jgame.JGObject;
import reflection.Reflection;
import saladConstants.SaladConstants;
import engineManagers.*;
import util.AttributeMaker;
import util.SaladUtil;
import engineManagers.CollisionManager;
import engineManagers.RevivalManager;
import engineManagers.ScoreManager;

/**
 * GameObject is the superclass of Player and NonPlayer 
 * GameObject is a game unit that can execute certain actions and interactions
 * 
 * @author: Main Justin (Zihao) Zhang
 * 
 * @contribution (side detectors): Shenghan Chen
 * @contribution (Animation Manager & Revival Manager): David Chou
 */

public abstract class GameObject extends JGObject {
	
	protected ResourceBundle myBehaviors; 
	protected CollisionManager myCollisionManager;
	protected RevivalManager myRevivalManager;
	protected ActionManager myActionManager;
	protected AnimationManager myAnimationManager;
	protected List<StatisticsManager> myGameManagers;

	protected int myXSize;
	protected int myYSize;
	protected double myInitX; 
	protected double myInitY;
	protected int myInitBlood;
	protected int myBlood;
	protected int myUniqueID;
	protected int myJumpTimes;
	protected boolean myIsInAir;
	protected int myAirCounter;
	protected double myInitXSpeed;
	protected double myInitYSpeed;
	protected String myDefaultImage;
	protected List<String> myAttributes;
	protected String myName;
	protected int myXHead;
	protected int myYHead;
	protected int myPrevXHead;
	protected int myPrevYHead;
	protected List<GameObject> myShotThings;
	protected SideDetector[] mySideDetectors;

	public GameObject(int uniqueID, String staticGfxName, int xsize,
			int ysize, double xpos, double ypos, String name, int collisionId,
			int blood, CollisionManager collisionManager, ScoreManager scoreManager, 
			BloodManager bloodManager, RevivalManager revivalManager, LiveManager liveManager,
			TriggerEventManager eventManager) {
		super(String.valueOf(uniqueID), true, xpos, ypos, collisionId, staticGfxName);
		
		resume_in_view = false;
		suspend();
		myBehaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
						+ SaladConstants.OBJECT_BEHAVIOR);
		setInitPos(xpos, ypos);
		setInitBlood(blood);
		myUniqueID = uniqueID;
		setSize(xsize, ysize);
		myShotThings = new ArrayList<GameObject>();
		myAttributes = new ArrayList<String>();
		myCollisionManager = collisionManager;
		myGameManagers = new ArrayList<StatisticsManager>();
		if(bloodManager != null) myGameManagers.add(bloodManager);
		if(scoreManager != null) myGameManagers.add(scoreManager);
		if(liveManager != null) myGameManagers.add(liveManager);
		if(eventManager != null) myGameManagers.add(eventManager);
		myRevivalManager = revivalManager;
		myDefaultImage = staticGfxName;
		myName = name;
		myActionManager = new ActionManager(this);
		myAnimationManager = new AnimationManager(this);
		initSideDetectors();
		myXHead = xdir;
		myYHead = ydir;
	}
	
	public GameObject(int uniqueID, String staticGfxName, int xsize, 
			int ysize, double xpos, double ypos, String name, int collisionId, int blood, 
			CollisionManager collisionManager, TriggerEventManager eventManager){
		this(uniqueID, staticGfxName, xsize, ysize, xpos, ypos, name, collisionId, blood, 
				collisionManager, null, null, null, null, eventManager); 
	}

	/**
	 * Initial the side detectors
	 */
	protected void initSideDetectors() {
		if (myUniqueID == SaladConstants.NULL_UNIQUE_ID) return;
		mySideDetectors = new SideDetector[SaladConstants.NUM_SIDE_DETECTORS];
		for (int i = 0; i < SaladConstants.NUM_SIDE_DETECTORS; i++) {
			setSideDetector(new SideDetector(this, i, SideDetector.SDcid(colid, i)));
		}
	}
	
	/**
	 * Set a behavior
	 * @param s
	 * @param args
	 */
	public void setBehavior(String behavior, Object ...args){
		myActionManager.setBehavior(behavior, args);
	}
	
	/**
	 * Perform an action
	 * @param action
	 */
	public void doAction(String action){
		myActionManager.doAction(action);
	}
	
	/**
	 * Get the x head direction
	 * @return
	 */
	public int getXHead(){
		return myXHead;
	}
	
	/**
	 * Get the y head direction
	 */
	public int getYHead(){
		return myYHead;
	}
	
	/**
	 * set the x head direction
	 */
	public void setXHead(int head){
		myXHead = head;
		if (myXHead < 0 && myPrevXHead != myXHead) {
			myAnimationManager.updateImage(SaladConstants.BK_MOVE);
		} else if (myXHead >= 0 && myPrevXHead != myXHead) {
			myAnimationManager.updateImage(SaladConstants.FD_MOVE);
		}
		myPrevXHead = myXHead;
	}
	
	/**
	 * set the y head direction
	 * @param head
	 */
	public void setYHead(int head){
		myYHead = head;
		if (myYHead < 0 && myPrevYHead != myYHead) {
			myAnimationManager.updateImage(SaladConstants.UP_MOVE);
		} else if (myYHead >= 0 && myPrevYHead != myYHead) {
			myAnimationManager.updateImage(SaladConstants.DW_MOVE);
		}
		myPrevYHead = myYHead;
	}

	/**
	 * Reset the name
	 * 
	 * @param name
	 */
	public void resetName(String name) {
		myName = name;
	}
	
	/**
	 * Get Name
	 * @return
	 */
	public String getObjectName(){
		return myName;
	}

	/**
	 * Get the collision manager associated with this object
	 * 
	 * @return myCollisionManager
	 */
	public CollisionManager getCollisionManager() {
		return myCollisionManager;
	}

	/**
	 * Get the side collision detector associated with this object in direction
	 * of dir
	 * 
	 * @param dir
	 * @return mySideDetectors
	 */
	public SideDetector getSideDetector(int dir) {
		return mySideDetectors[dir];
	}

	/**
	 * Set the side collision detector associated with this object in direction
	 * of dir
	 * 
	 * @param detector
	 * @return mySideDetectors
	 */
	public void setSideDetector(SideDetector detector) {
		mySideDetectors[detector.myDirection] = detector;
	}

	/**
	 * Set the dimension of the object image
	 * 
	 * @param xsize
	 * @param ysize
	 */
	public void setSize(int xsize, int ysize) {
		myXSize = xsize;
		myYSize = ysize;
	}

	/**
	 * Get the x size of the object image
	 * 
	 * @return int
	 */
	public int getXSize() {
		return myXSize;
	}

	/**
	 * Get the y size of the object image
	 * 
	 * @return int
	 */
	public int getYSize() {
		return myYSize;
	}

	/**
	 * Set the initial position of the object in a scene
	 * 
	 * @param x
	 * @param y
	 */
	public void setInitPos(double x, double y) {
		super.setPos(x, y);
		myInitX = x;
		myInitY = y;
	}

	/**
	 * Used for ActionManager or GameObject itself to getAttributes
	 * @return String
	 */
	public String modificationString() {
		if (this instanceof Player) { return SaladConstants.MODIFY_PLAYER; }
		return SaladConstants.MODIFY_ACTOR;
	}
	
	/**
	 * Used for ActionManager or GameObject itself to getAttributes
	 * @return String
	 */
	public String creationString(){
		if(this instanceof Player){ return SaladConstants.CREATE_PLAYER; }
		return SaladConstants.CREATE_ACTOR;
	}

	/**
	 * Set the intial speed 
	 * @param xspeed
	 * @param yspeed
	 */
	public void setInitSpeed(double xspeed, double yspeed) {
		super.setSpeed(xspeed, yspeed);
		myInitXSpeed = xspeed;
		myInitYSpeed = yspeed;
		myAttributes.add(AttributeMaker.addAttribute(modificationString(),
				SaladConstants.ID, myUniqueID, SaladConstants.SPEED, false,
				myInitXSpeed, myInitYSpeed));
	}

	/**
	 * Restore to original state within a scene Used for live-editing
	 */
	public void restore(boolean resetPos) {
		if (resetPos) setPos(myInitX, myInitY); 
		setSpeed(myInitXSpeed, myInitYSpeed);
		restoreBlood();
		if (!is_alive) {
			eng.markAddObject(this);
			is_alive = true;
		}
		if (mySideDetectors!=null){
			for (int i = 0; i < SaladConstants.NUM_SIDE_DETECTORS; i++) {
				mySideDetectors[i].restore(false);
			}
		}
	}
	
	/**
	 * Resume in view
	 */
	public void resume(){
		super.resume();
		if (mySideDetectors!=null){
			for (int i = 0; i < SaladConstants.NUM_SIDE_DETECTORS; i++) {
				mySideDetectors[i].resume();
			}
		}
	}
	
	/**
	 * suspend from view
	 */
	public void suspend(){
		super.suspend();
		if (mySideDetectors!=null){
			for (int i = 0; i < SaladConstants.NUM_SIDE_DETECTORS; i++) {
				mySideDetectors[i].suspend();
			}
		}
	}

	/**
	 * Reset the unique ID
	 * 
	 * @param new uniqueID
	 */
	public void resetID(int uniqueID) {
		myUniqueID = uniqueID;
	}

	/**
	 * Get the unique ID
	 * 
	 * @return int
	 */
	public int getID() {
		return myUniqueID;
	}

	/**
	 * Change the number of lives
	 */
	public void changeBlood(int blood) {
		myBlood += blood;
	}

	/**
	 * Initialize the number of lives
	 * 
	 * @param lives
	 */
	public void setInitBlood(int blood) {
		myInitBlood = blood;
		restoreBlood();
	}
	
	protected void restoreBlood(){
		myBlood = myInitBlood;
	}

	/**
	 * Get current number of lives
	 * 
	 * @return
	 */
	public int getBlood() {
		return myBlood;
	}

	/**
	 * Get number of initial lives
	 * 
	 * @return
	 */
	public int getInitBlood() {
		return myInitBlood;
	}

	public void stop() {
		setSpeed(0);
		setPos(getLastX(), getLastY());
	}

	/**
	 * Called when hit the ground
	 */
	public void ground() {
		if(myIsInAir) setImage(myDefaultImage);
		myIsInAir = false;
		myAirCounter = 1;
		myJumpTimes = 0;
		stop();
	}

	/**
	 * Reset the collision ID
	 * 
	 * @param new collisionID
	 */
	public void resetCollisionID(int collisionID) {
		colid = collisionID;
	}
	
	public int getAirCounter(){
		return myAirCounter;
	}
	
	public void incrementJumpTimes(int change){
		myJumpTimes += change;
	}

	/**
	 * 
	 * @return int jump times
	 */
	public int getJumpTimes() {
		return myJumpTimes;
	}

	@Override
	public void move() {
		if (myBlood <= 0) doAction(SaladConstants.DIE);

		myAirCounter = 2 * (myAirCounter % 2);
	}
	
	/**
	 * Update the image
	 * @param behavior
	 */
	public void updateImage(String behavior){
		myAnimationManager.updateImage(behavior);
	}
	
	/**
	 * Called by behaviors to update the managers
	 * @param args
	 */
	public void updateManagers(Object ... args){
		try{
			for(StatisticsManager manager: myGameManagers){
				if(manager != null){
					Reflection.callMethod(manager, SaladConstants.MANAGER_UPDATE, args);
				}
			}
		} catch (Exception e){ e.printStackTrace(); } 
	}

	@Override
	public void hit(JGObject other) {
		myCollisionManager.hitObject(myBehaviors, this, (GameObject) other);
	}

	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize) {
		myCollisionManager.hitTile(myBehaviors, this, tilecid, tx, ty, txsize, tysize);
	}
	
	@Override
	public void remove() {
		super.remove();
		if (mySideDetectors!=null){
			for (int i = 0; i < SaladConstants.NUM_SIDE_DETECTORS; i++) { mySideDetectors[i].remove();}
		}
		if (myUniqueID != SaladConstants.NULL_UNIQUE_ID) myRevivalManager.addRemovedObject(this);
		if (this instanceof Player) {
			LiveManager liveManager = (LiveManager) getSpecificManager(SaladConstants.LIVE_MANAGER_PATH);
			if(liveManager != null) liveManager.changeLive(myUniqueID, -1);
		}
	}
	
	protected StatisticsManager getSpecificManager(String name){
		for(StatisticsManager manager: myGameManagers){
			if(manager.getClass().getName().equals(name)){
				return manager;
			}
		}
		return null;
	}

	/**
	 * Should be called by the Parser class to get all attributes of the
	 * GameObject Return a list of Strings that match with the Data Format but
	 * without Key
	 * 
	 * @return a list of Strings
	 */
	public List<String> getAttributes() {
		myAttributes.add(0, AttributeMaker.addAttribute(creationString(), SaladConstants.ID, myUniqueID, 
				SaladConstants.IMAGE, false, myDefaultImage, myXSize, myYSize, SaladConstants.POSITION, myInitX, 
				myInitY, SaladConstants.NAME, myName, SaladConstants.COLLISION_ID, colid, SaladConstants.LIVES, myInitBlood));
		myAttributes.addAll(myActionManager.getAttributes());
		myAttributes.addAll(myAnimationManager.getAttributes());
		List<String> deleteList = new ArrayList<String>();
		for(int i = 0; i < myAttributes.size(); i ++){
			if(myAttributes.get(i).startsWith(creationString()) && i != 0){ deleteList.add(myAttributes.get(i)); }
		}
		for(String s: deleteList){ myAttributes.remove(s); }
		return myAttributes;
	}
	
	/**
	 * @return the Gfx info
	 */
	public String getMyGfx() {
		return myDefaultImage;
	}
	
	public void setStaticGfx(String image) {
		myDefaultImage = image;
	}
	
	public TriggerEventManager getEventManager(){
		return (TriggerEventManager) getSpecificManager(SaladConstants.EVENT_MANAGER_PATH);
	}
	
	/**
	 * Get the number of the shots that are alive on the screen
	 * @return
	 */
	public int getNumAliveShots(){
		int count = 0;
		for(GameObject object: myShotThings){
			if(object.isAlive()) count ++;
		}
		List<GameObject> temp = new ArrayList<GameObject>();
		for(GameObject object: myShotThings){
			if(object.isAlive()) temp.add(object);
		}
		myShotThings = temp;
		return count;
	}
	
	public void setInAir(boolean inAir){
		myIsInAir = inAir;
	}
	
	public boolean getInAir(){
		return myIsInAir;
	}
	
	/**
	 * Add the shot bullet
	 * @param object
	 */
	public void addShotThing(GameObject object){
		myShotThings.add(object);
	}
    
    /**
     * @return the myInitX
     */
    public double getMyInitX() {
        return myInitX;
    }

    /**
     * Allows the user to modify the image for different actions
     * @param action
     * @param imgfile
     * @param xsize
     * @param ysize
     */
	public void modifyDynamicImage(String action, String imgfile, int xsize, int ysize) {
		myAnimationManager.modifyImage(action, imgfile);
	}

}
