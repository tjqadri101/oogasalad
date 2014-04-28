package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import jgame.JGObject;
import saladConstants.SaladConstants;
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
 * @contribution (images/animations): David Chou
 * @contribution (side detectors/jump handling): Shenghan Chen
 */

public abstract class GameObject extends JGObject {

	protected ScoreManager myScoreManager;
	protected CollisionManager myCollisionManager;
	protected BloodManager myBloodManager;
	protected RevivalManager myRevivalManager;
	protected LiveManager myLiveManager;
	protected ActionManager myActionManager;
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
	protected String myStaticGfxName;
	protected String myJumpingGfxName;
	protected String myMovingGfxName;
	protected List<String> myAttributes;
	protected String myName;
	
	protected int myDirection; // change later

	protected ResourceBundle myBehaviors; //delete later
	protected SideDetector[] mySideDetectors;

	protected GameObject(int uniqueID, String staticGfxName, int xsize,
			int ysize, double xpos, double ypos, String name, int collisionId,
			int blood, CollisionManager collisionManager, ScoreManager scoreManager, 
			BloodManager bloodManager, RevivalManager revivalManager, LiveManager liveManager) {
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
		myStaticGfxName = staticGfxName;
		myName = name;
		myActionManager = new ActionManager(this);
		initSideDetectors();
		myAttributes.add(AttributeMaker.addAttribute(creationString(), SaladConstants.ID, myUniqueID, 
				SaladConstants.IMAGE, false, myStaticGfxName, myXSize, myYSize, SaladConstants.POSITION, myInitX, 
				myInitY, SaladConstants.NAME, myName, SaladConstants.COLLISION_ID, colid, SaladConstants.LIVES, myInitBlood));
	}

	/**
	 * 
	 */
	protected void initSideDetectors() {
		if (myUniqueID == SaladConstants.NULL_UNIQUE_ID) return;
		mySideDetectors = new SideDetector[SaladConstants.NUM_SIDE_DETECTORS];
		for (int i = 0; i < SaladConstants.NUM_SIDE_DETECTORS; i++) {
			setSideDetector(new SideDetector(this, i, SideDetector.SDcid(colid, i)));
		}
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
	
	public void resume(){
		super.resume();
		if (mySideDetectors!=null){
			for (int i = 0; i < SaladConstants.NUM_SIDE_DETECTORS; i++) {
				mySideDetectors[i].resume();
			}
		}
	}
	
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
	 * Set the Die Behavior
	 * 
	 * @param a String specifying one of the die behaviors
	 */
	public void setDieBehavior(String s, Object... args) {
		myActionManager.setDieBehavior(s, args);
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

	/**
	 * Set the jump behavior
	 * 
	 * @param String
	 *            specifying one of the jump behaviors
	 * @param Magnitude
	 *            of the initial jump speed
	 */
	public void setJumpBehavior(String s, Object... args) {
		myActionManager.setJumpBehavior(s, args);
	}

	/**
	 * Set the shoot behavior
	 * 
	 * @param s
	 *            : shoot type
	 * @param args
	 *            : parameters
	 */
	public void setShootBehavior(String s, Object... args) {
		myActionManager.setShootBehavior(s, args);
	}

	/**
	 * Set the move behavior
	 * 
	 * @param s
	 *            : String specifying the move behavior
	 * @param xspeed
	 *            : the x speed
	 * @param yspeed
	 *            : the y speed
	 */
	public void setMoveBehavior(String s, Object... args) {
		myActionManager.setMoveBehavior(s, args);
	}

	public void die() {
		myActionManager.die();
	}

	// public void bounce(){
	// xspeed *= -1;
	// yspeed *= -1;
	// }

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

	public void setJumpingImage(String jumpGfx) {
		myJumpingGfxName = jumpGfx;
	}

	public void setMovingImage(String moveGfx) {
		myMovingGfxName = moveGfx;
	}

	public void jump() {
		if (myIsInAir == 0) { myJumpTimes++; }
		myActionManager.jump();
		myAnimationManager.updateImage(this, "Jump") ; //hardcode to be modified later
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
		if (myBlood <= 0) die();
		myIsInAir = 2 * (myIsInAir % 2);
		if (xspeed > 0) {
			myAnimationManager.updateImage(this, "FDMove");
		} else if (xspeed < 0) {
			myAnimationManager.updateImage(this, "BKMove");
		} else {
			setImage(myStaticGfxName);
		}
	}

	@Override
	public void hit(JGObject other) {
		myCollisionManager.hitObject(myBehaviors, this, (GameObject) other);
	}

	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize) {
		// myInAirCounter = 0;
		myCollisionManager.hitTile(myBehaviors, this, tilecid, tx, ty, txsize, tysize);
		setImage(myStaticGfxName);
	}
	
	@Override
	public void remove() {
		super.remove();
		if (mySideDetectors!=null){
			for (int i = 0; i < SaladConstants.NUM_SIDE_DETECTORS; i++) {
				mySideDetectors[i].remove();
			}
		}
		if (myUniqueID != SaladConstants.NULL_UNIQUE_ID) myRevivalManager.addRemovedObject(this);
	}

	public void autoMove() {
		myActionManager.autoMove();
	}

	public void shoot() {
		myActionManager.shoot();
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
		myStaticGfxName = gfxname;
	}

	/**
	 * Used for behaviors to get the ScoreManager to update scores
	 * 
	 * @return ScoreManager
	 */
	public ScoreManager getScoreManager() {
		return myScoreManager;
	}

	/**
	 * Used for behaviors to get the BloodManager to update blood
	 * 
	 * @return BloodManager
	 */
	public BloodManager getBloodManager() {
		return myBloodManager;
	}
	
	/**
	 * Used for behaviors to get the LiveManager to update blood
	 * 
	 * @return LiveManager
	 */
	public LiveManager getLiveManager(){
		return myLiveManager;
	}
	
	/**
	 * @return the Gfx info
	 */
	public String getMyGfx() {
		return myStaticGfxName;
	}
	
	public void setStaticGfx(String image) {
		myStaticGfxName = image;
	}

	public RevivalManager getRevivalManager() {
		return myRevivalManager;
	}
    
    /**
     * @return the myInitX
     */
    public double getMyInitX() {
        return myInitX;
    }

}
