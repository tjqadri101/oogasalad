package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import jgame.JGObject;
import reflection.Reflection;
import saladConstants.SaladConstants;
import statistics.GameStats;
import engineManagers.*;
import util.AttributeMaker;
import engineManagers.CollisionManager;
import engineManagers.RevivalManager;
import engineManagers.ScoreManager;

/**
 * GameObject is the superclass of Player and NonPlayer GameObject is a game
 * unit that can execute certain actions and interactions
 * 
 * @author: Main Justin (Zihao) Zhang,
 * @contribution: (side detectors/jump handling): Shenghan Chen
 * @contribution: David Chou
 */

public abstract class GameObject extends JGObject {
	
	protected ResourceBundle myBehaviors; 
	protected ScoreManager myScoreManager;
	protected CollisionManager myCollisionManager;
	protected BloodManager myBloodManager;
	protected RevivalManager myRevivalManager;
	protected LiveManager myLiveManager;
	protected ActionManager myActionManager;
	protected TriggerEventManager myEventManager;
	protected AnimationManager myAnimationManager;

	protected int myXSize;
	protected int myYSize;
	protected double myInitX; 
	protected double myInitY;
	protected int myInitBlood;
	protected int myBlood;
	protected int myUniqueID;
	protected int myJumpTimes;
	protected int myIsInAir;
	protected double myInitXSpeed;
	protected double myInitYSpeed;
	protected String myDefaultImage;
	protected List<String> myAttributes;
	protected String myName;
	protected int myXHead;
	protected int myYHead;
	protected SideDetector[] mySideDetectors;

	public GameObject(int uniqueID, String staticGfxName, int xsize,
			int ysize, double xpos, double ypos, String name, int collisionId,
			int blood, CollisionManager collisionManager, ScoreManager scoreManager, 
			BloodManager bloodManager, RevivalManager revivalManager, LiveManager liveManager,
			TriggerEventManager eventManager) {
		super(String.valueOf(uniqueID), true, xpos, ypos, collisionId, staticGfxName);
		suspend();
		resume_in_view = false;
		myBehaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
						+ SaladConstants.OBJECT_BEHAVIOR);
		setInitPos(xpos, ypos);
		setInitBlood(blood);
		myUniqueID = uniqueID;
		setSize(xsize, ysize);
		myAttributes = new ArrayList<String>();
		myCollisionManager = collisionManager;
		myScoreManager = scoreManager;
		myBloodManager = bloodManager;
		myRevivalManager = revivalManager;
		myLiveManager = liveManager;
		myDefaultImage = staticGfxName;
		myName = name;
		myActionManager = new ActionManager(this);
		myAnimationManager = new AnimationManager(this);
		myEventManager = eventManager;
		initSideDetectors();
		myXHead = xdir;
		myYHead = ydir;
		myAttributes.add(AttributeMaker.addAttribute(creationString(), SaladConstants.ID, myUniqueID, 
				SaladConstants.IMAGE, false, myDefaultImage, myXSize, myYSize, SaladConstants.POSITION, myInitX, 
				myInitY, SaladConstants.NAME, myName, SaladConstants.COLLISION_ID, colid, SaladConstants.LIVES, myInitBlood));
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
	
	public int getXHead(){
		return myXHead;
	}
	
	public int getYHead(){
		return myYHead;
	}
	
	public void setXHead(int head){
		myXHead = head;
	}
	
	public void setYHead(int head){
		myYHead = head;
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
		GameStats.set(myName + SaladConstants.SPACE + SaladConstants.BLOOD, blood);
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

	public void bounce(){
		myActionManager.bounce();
	}

	public void stop() {
		setSpeed(0);
		setPos(getLastX(), getLastY());
	}

	public void ground() {
		myIsInAir = 1;
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
	
	public int getIsInAir(){
		return myIsInAir;
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
		myIsInAir = 2 * (myIsInAir % 2);
		if (myXHead < 0) {
			myAnimationManager.updateImage(SaladConstants.BK_MOVE);
		} else if (myXHead > 0) {
			myAnimationManager.updateImage(SaladConstants.FD_MOVE);
		}
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
			if(myScoreManager != null) Reflection.callMethod(myScoreManager, "update", args);
			if(myBloodManager != null) Reflection.callMethod(myBloodManager, "update", args);
			if(myLiveManager != null) Reflection.callMethod(myLiveManager, "update", args);
			if(myEventManager != null) Reflection.callMethod(myEventManager, "update", args);	
		} catch (Exception e){ e.printStackTrace(); }
	}

	@Override
	public void hit(JGObject other) {
		myCollisionManager.hitObject(myBehaviors, this, (GameObject) other);
	}

	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize) {
		myCollisionManager.hitTile(myBehaviors, this, tilecid, tx, ty, txsize, tysize);
//		if (myXHead == 0) setImage(myDefaultImage);
	}
	
	@Override
	public void remove() {
		super.remove();
		if (mySideDetectors!=null){
			for (int i = 0; i < SaladConstants.NUM_SIDE_DETECTORS; i++) { mySideDetectors[i].remove();}
		}
		if (myUniqueID != SaladConstants.NULL_UNIQUE_ID) myRevivalManager.addRemovedObject(this);
		if (this instanceof Player) myLiveManager.decrementLive(getID());
	}

	/**
	 * Should be called by the Parser class to get all attributes of the
	 * GameObject Return a list of Strings that match with the Data Format but
	 * without Key
	 * 
	 * @return a list of Strings
	 */
	public List<String> getAttributes() {
		myAttributes.addAll(myActionManager.getAttributes());
		return myAttributes;
	}

	/**
	 * When ModifyActor/PlayerImage is called, the gfx info is passed along
	 * 
	 * @param gfxname
	 */
	public void updateImageURL(String gfxname) {
		myDefaultImage = gfxname;
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
		return myEventManager;
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
	public void modifyDynamicImage(String action, String imgfile, int xsize,
			int ysize) {
		myAnimationManager.modifyImage(action, imgfile);
	}

}
