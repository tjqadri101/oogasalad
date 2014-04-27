package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import jgame.JGObject;
import saladConstants.SaladConstants;
import engineManagers.*;
import util.AttributeMaker;
import util.SaladUtil;
import engineManagers.CollisionManager;
import engineManagers.RevivalManager;
import engineManagers.ScoreManager;

/**
 * GameObject is the superclass of Player and NonPlayer GameObject is a game
 * unit that can execute certain actions and interactions
 * 
 * @author: Main Justin (Zihao) Zhang,
 * @contribution (images/animations): David Chou
 * @contribution (side detectors): Shenghan Chen
 */

public abstract class GameObject extends JGObject {

	protected ScoreManager myScoreManager;
	protected CollisionManager myCollisionManager;
	protected BloodManager myBloodManager;
	protected RevivalManager myRevivalManager;
	protected ActionManager myActionManager;

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
	protected boolean myIsActive;
	
	protected int myDirection;

	protected ResourceBundle myBehaviors; //delete later
//	protected String myDieBehavior;
//	protected String myMoveBehavior;
//	protected String myJumpBehavior;
//	protected String myShootBehavior;
//
//	protected List<Object> myShootParameters;
//	protected List<Object> myDieParameters;
//	protected List<Object> myMoveParameters;
//	protected List<Object> myJumpParameters;
	protected SideDetector[] mySideDetectors;

	protected GameObject(int uniqueID, String staticGfxName, int xsize,
			int ysize, double xpos, double ypos, String name, int collisionId,
			int blood, CollisionManager collisionManager,
			ScoreManager scoreManager, BloodManager bloodManager, RevivalManager revivalManager) {
		super(String.valueOf(uniqueID), true, xpos, ypos, collisionId, staticGfxName);
		myBehaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
						+ SaladConstants.OBJECT_BEHAVIOR);
		setInitPos(xpos, ypos);
		setInitBlood(blood); // change later
		myUniqueID = uniqueID;
		setSize(xsize, ysize);
		myAttributes = new ArrayList<String>();
		myCollisionManager = collisionManager;
		myScoreManager = scoreManager;
		myBloodManager = bloodManager;
		myRevivalManager = revivalManager;
		myStaticGfxName = staticGfxName;
		myName = name;
		myActionManager = new ActionManager(this);
		initSideDetectors();
		System.out.println((creationString() + SaladConstants.ID + myUniqueID + 
				SaladConstants.IMAGE + false + myStaticGfxName + myXSize + myYSize + SaladConstants.POSITION + myInitX + 
				myInitY + SaladConstants.NAME + myName + SaladConstants.COLLISION_ID + colid + SaladConstants.LIVES + myInitBlood));
		myAttributes.add(AttributeMaker.addAttribute(creationString(), SaladConstants.ID, myUniqueID, 
				SaladConstants.IMAGE, false, myStaticGfxName, myXSize, myYSize, SaladConstants.POSITION, myInitX, 
				myInitY, SaladConstants.NAME, myName, SaladConstants.COLLISION_ID, colid, SaladConstants.LIVES, myInitBlood));
	}

	public boolean getIsActive() {
		return myIsActive;
	}

	public void setIsActive(boolean active) {
		myIsActive = active;
	}

	/**
	 * 
	 */
	private void initSideDetectors() {
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
	 * 
	 * @return String
	 */
	public String modificationString() {
		if (this instanceof Player) {
			return SaladConstants.MODIFY_PLAYER;
		}
		return SaladConstants.MODIFY_ACTOR;
	}
	
	/**
	 * Used for ActionManager or GameObject itself to getAttributes
	 * 
	 * @return String
	 */
	public String creationString(){
		if(this instanceof Player){
			return SaladConstants.CREATE_PLAYER;
		}
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
	public void restore(boolean resetLife) {
		setPos(myInitX, myInitY);
		setSpeed(myInitXSpeed, myInitYSpeed);
		restoreBlood();
		if (resetLife) {
			//Live Manager
		}
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
	
//	public void suspend(){
//		super.resume();
//		if (mySideDetectors!=null){
//			for (int i = 0; i < 4; i++) {
//				mySideDetectors[i].suspend();
//			}
//		}
//	}

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
//		myDieBehavior = s;
//		myDieParameters = SaladUtil.convertArgsToObjectList(args);
//		myAttributes.add(AttributeMaker.addAttribute(modificationString(),
//				SaladConstants.ID, myUniqueID, myDieBehavior, true,
//				myDieParameters));
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
//		myJumpBehavior = s;
//		myJumpParameters = SaladUtil.convertArgsToObjectList(args);
//		myAttributes.add(AttributeMaker.addAttribute(modificationString(),
//				SaladConstants.ID, myUniqueID, myJumpBehavior, true,
//				myJumpParameters));
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
//		myShootBehavior = s;
//		myShootParameters = SaladUtil.convertArgsToObjectList(args);
//		myAttributes.add(AttributeMaker.addAttribute(modificationString(),
//				SaladConstants.ID, myUniqueID, myShootBehavior, true,
//				myShootParameters));
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
//		myMoveBehavior = s;
//		myMoveParameters = SaladUtil.convertArgsToObjectList(args);
//		myAttributes.add(AttributeMaker.addAttribute(modificationString(),
//				SaladConstants.ID, myUniqueID, myMoveBehavior, true,
//				myMoveParameters));
		myActionManager.setMoveBehavior(s, args);
	}

	public void die() {
//		if (myDieBehavior == null) return;
//		SaladUtil.behaviorReflection(myBehaviors, myDieBehavior,
//				myDieParameters, SaladConstants.REMOVE, this);
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
//		if (myJumpBehavior == null) return;
//		SaladUtil.behaviorReflection(myBehaviors, myJumpBehavior,
//				myJumpParameters, SaladConstants.JUMP, this);
		myActionManager.jump();
		setImage(myJumpingGfxName);
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
		if (xspeed != 0) {
			setImage(myMovingGfxName);
		} else {
			setImage(myStaticGfxName);
		}
	}

	@Override
	public void hit(JGObject other) {
		List<Object> parameters = SaladUtil.copyObjectList(myCollisionManager
				.getCollisionBehavior(colid, other.colid));
		if (parameters == null)
			return; // just to make sure
		String collisionBehavior = (String) parameters.get(0);
		parameters.remove(0);
		parameters.add(0, other);
		SaladUtil.behaviorReflection(myBehaviors, collisionBehavior,
				parameters, SaladConstants.COLLIDE, this);
	}

	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize) {
		// myInAirCounter = 0;
		List<Object> parameters = SaladUtil.copyObjectList(
				myCollisionManager.getTileCollisionBehavior(colid, tilecid));
		if (parameters == null) return; // just to make sure
		String collisionBehavior = (String) parameters.get(0);
		parameters.remove(0);
		parameters.add(tilecid);
		parameters.add(tx);
		parameters.add(ty);
		parameters.add(txsize);
		parameters.add(tysize);
		SaladUtil.behaviorReflection(myBehaviors, collisionBehavior,
				parameters, SaladConstants.COLLIDE, this);
		setImage(myStaticGfxName);
	}
	
	@Override
	public void remove() {
		if (isAlive()) eng.removeObject(this); 
		is_alive=false; 
		myRevivalManager.addRemovedObject(this);
	}

	public void autoMove() {
//		if (myMoveBehavior == null) return;
//		SaladUtil.behaviorReflection(myBehaviors, myMoveBehavior,
//				myMoveParameters, SaladConstants.MOVE, this);
		myActionManager.autoMove();
	}

	public void shoot() {
//		if (myShootBehavior == null) return;
//		SaladUtil.behaviorReflection(myBehaviors, myShootBehavior,
//				myShootParameters, SaladConstants.SHOOT, this);
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
	
/* @Steve:
 * The following getter and setters used for GameFactoryTest
 * Will remove them once finished
 */
    /**
     * @return the myMoveBehavior
     */
//    public String getMyMoveBehavior () {
//        return myMoveBehavior;
//    }
//
//    /**
//     * @return the myDieBehavior
//     */
//    public String getMyDieBehavior () {
//        return myDieBehavior;
//    }
    
    /**
     * @return the myInitX
     */
    public double getMyInitX() {
        return myInitX;
    }

}
