package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import engineManagers.CollisionManager;
//import engineManagers.ScoreManager;
import reflection.Reflection;
import saladConstants.SaladConstants;
import util.SaladUtil;
import jgame.JGObject;
/**
 * GameObject is the superclass of Player and NonPlayer
 * GameObject is a game unit that can execute certain actions and interactions 
 * @Author: Justin (Zihao) Zhang
 */
public abstract class GameObject extends JGObject {
    
//	protected ScoreManager myScoreManager;
	protected CollisionManager myCollisionManager;
	
	protected int myXSize;
	protected int myYSize;
	protected double myInitX;
	protected double myInitY;
	protected int myInitLives;
	protected int myLives;
	protected int myUniqueID;
	protected int myJumpTimes;
	protected boolean myIsInAir;
	protected double myInitXSpeed;
	protected double myInitYSpeed;
	protected String myGfx;
	protected List<String> myAttributes;
	
	protected boolean myIsPlayer;//need change
    
	protected ResourceBundle myBehaviors;
	protected String myDieBehavior;
	protected String myMoveBehavior;
	protected String myJumpBehavior;
	protected String myShootBehavior;

	protected List<Object> myShootParameters;
	protected List<Object> myDieParameters;
	protected List<Object> myMoveParameters;
	protected List<Object> myJumpParameters;
	protected SideDetecter[] mySideDetecters;//plz review
	
	protected GameObject(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, String name, int collisionId, int lives, CollisionManager collisionManager){
		super(name, true, xpos, ypos, collisionId, gfxname);
		myBehaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.DEFAULT_BEHAVIOR);
		setInitPos(xpos, ypos);
		setLives(lives); // change later
		myUniqueID = uniqueID;
		setSize(xsize, ysize);
		myAttributes = new ArrayList<String>();
		mySideDetecters = new SideDetecter[4];//plz review
		myCollisionManager = collisionManager;
	}
	
	/**
	 * Get the collision manager associated with this object
	 * @return myCollisionManager
	 */
	public CollisionManager getCollisionManager(){
		return myCollisionManager;
	}
	
	/**
	 * Get the side collision detecters associated with this object
	 * @return mySideDetecters
	 */
	public SideDetecter[] getSideDetecters(){
		return mySideDetecters;
	}
	
	/**
	 * Set the dimension of the object image
	 * @param xsize
	 * @param ysize
	 */
	public void setSize(int xsize, int ysize){
		myXSize = xsize;
		myYSize = ysize;
	}
	
	/**
	 * Get the x size of the object image
	 * @return int
	 */
	public int getXSize(){
		return myXSize;
	}
	
	/**
	 * Get the y size of the object image
	 * @return int
	 */
	public int getYSize(){
		return myYSize;
	}
	
	/**
	 * Set the initial position of the object in a scene
	 * @param x
	 * @param y
	 */
	public void setInitPos(double x, double y){
		super.setPos(x, y);
		myInitX = x;
		myInitY = y;
	}
	
	/**
	 * Do not call this method directly
	 * Used for getAttributes() method
	 * @return String
	 */
	protected String ModificationString(){
		if(myIsPlayer){
			return SaladConstants.MODIFY_PLAYER;
		}
		return SaladConstants.MODIFY_ACTOR;
	}
	
	@Override
	public void setSpeed(double xspeed, double yspeed){
		super.setSpeed(xspeed, yspeed);
		myInitXSpeed = xspeed;
		myInitYSpeed = yspeed;
		myAttributes.add(ModificationString() + "," + SaladConstants.ID + "," + myUniqueID + "," + 
				SaladConstants.SPEED + "," + myInitXSpeed + "," + myInitYSpeed);
	}
	
	/**
	 * Restore to original state within a scene
	 * Used for live-editing
	 */
	public void restore(){
		setInitPos(myInitX, myInitY);
		setLives(myInitLives);
	}
	
	/**
	 * Reset the unique ID
	 * @param the new uniqueID
	 */
	public void resetID(int uniqueID){
		myUniqueID = uniqueID;
	}
	
	/**
	 * Get the unique ID
	 * @return int
	 */
	public int getID(){
		return myUniqueID;
	}
	
	/**
	 * Set the Die Behavior
	 * @param a String specifying one of the die behaviors
	 */
	public void setDieBehavior(String s, Object ... args){
		myDieBehavior = s;
		myDieParameters = new ArrayList<Object>();
		for(int i = 0; i < args.length; i ++){
			myDieParameters.add(args[i]);
		}
		addAttributes(SaladConstants.ID, myUniqueID, myDieBehavior, myDieBehavior, myDieParameters);
	}
	
	/**
	 * Add attribute for behaviors already set
	 * @param s
	 * @param params
	 */
	protected void addAttributes(String firstType, Object param, String secondType, String typeToken, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(ModificationString() + "," + firstType + "," + param + "," + secondType + "," + typeToken);
		for(Object o: params){
			String att = o.toString();
			attribute.append("," + att);
		}
		myAttributes.add(attribute.toString());
	}
	
	protected void addAttributes(String firstType, Object param, String secondType, String typeToken, int integerToken, List<Object> params){
		StringBuilder attribute = new StringBuilder();
		attribute.append(ModificationString() + "," + firstType + "," + param + "," + secondType + "," + typeToken + "," + integerToken);
		for(Object o: params){
			String att = o.toString();
			attribute.append("," + att);
		}
		myAttributes.add(attribute.toString());
	}
	
	/**
	 * Decrement the number of lives
	 */
	public void loseLife(){
		myLives --;
	}
	
	/**
	 * Initialize the number of lives
	 * @param lives
	 */
	public void setLives(int lives){
		myInitLives = lives;
		myLives = lives;
	}
	
	/**
	 * Get current number of lives
	 * @return
	 */
	public int getLives(){
		return myLives;
	}
	
	/**
	 * Get number of initial lives
	 * @return
	 */
	public int getInitLives(){
		return myInitLives;
	}
	
	/**
	 * Set the jump behavior
	 * @param String specifying one of the jump behaviors
	 * @param Magnitude of the initial jump speed
	 */
	public void setJumpBehavior(String s, Object ... args){
		myJumpBehavior = s;
		myJumpParameters = new ArrayList<Object>();
		for(int i = 0; i < args.length; i ++){
			myJumpParameters.add(args[i]);
		}
		addAttributes(SaladConstants.ID, myUniqueID, myJumpBehavior, myJumpBehavior, myJumpParameters);
	}
	
    /**
     * Set the shoot behavior
     * @param s: shoot type
     * @param args: parameters
     */
	public void setShootBehavior(String s, Object ... args){
		myShootBehavior = s;
		myShootParameters = new ArrayList<Object>();
		for(int i = 0; i < args.length; i ++){
			myShootParameters.add(args[i]);
		}
		addAttributes(SaladConstants.ID, myUniqueID, myShootBehavior, myShootBehavior, myShootParameters);
	}
	
	/**
	 * Set the move behavior
	 * @param s: String specifying the move behavior
	 * @param xspeed: the x speed 
	 * @param yspeed: the y speed
	 */
	public void setMoveBehavior(String s, Object ... args){
		myMoveBehavior = s;
		myMoveParameters = new ArrayList<Object>();
		for(int i = 0; i < args.length; i ++){
			myMoveParameters.add(args[i]);
		}
		addAttributes(SaladConstants.ID, myUniqueID, myMoveBehavior, myMoveBehavior, myMoveParameters);
	}
	
	
	public void die(){
		if(myDieBehavior == null) return;
		behaviorReflection(myBehaviors, myDieBehavior, myDieParameters, "remove");	
	}
	
//	public void bounce(){
//		xspeed *= -1;
//		yspeed *= -1;
//	}

	public void stop(){
		setSpeed(0);
		setPos(getLastX(), getLastY());
	}

	public void ground(){
		myIsInAir = false;
		myJumpTimes = 0;
		stop();
	}
	
	/**
	 * Reset the collision ID
	 * @param new collisionID
	 */
	public void resetCollisionID(int collisionID){
		colid = collisionID;
	}
	
	public void jump(){
		myJumpTimes ++;
		myIsInAir = true;
		if(myJumpBehavior == null) return;
		behaviorReflection(myBehaviors, myJumpBehavior, myJumpParameters, "jump");
	}
	
	/**
	 * 
	 * @return int jump times
	 */
	public int getJumpTimes(){
		return myJumpTimes;
	}
	
	/**
	 * 
	 * @return boolean if is in air
	 */
	public boolean getIsInAir(){
		return myIsInAir;
	}
	
	/**
	 * Do not call this method directly
	 * @param ResourceBundle
	 * @param myString
	 * @param methodName
	 */
	protected void behaviorReflection(ResourceBundle myBundle, String myString, List<Object> objects, String methodName){
		if(myString == null) return;
		try{
			Object behavior = Reflection.createInstance(myBundle.getString(myString), this);
			Reflection.callMethod(behavior, methodName, objects);	
		} catch (Exception e){
			e.printStackTrace(); // should never reach here
		}
	}
	
	@Override
	public void move(){
		if(myLives <= 0) die();
	}
	
	@Override
	public void hit(JGObject other)
    {
		List<Object> parameters = SaladUtil.copyObjectList(myCollisionManager.getCollisionBehavior(colid, other.colid));
		if(parameters == null) return; // just to make sure
		String collisionBehavior = (String) parameters.get(0);
		parameters.remove(0);
		parameters.add(0, other);
		behaviorReflection(myBehaviors, collisionBehavior, parameters, "collide");
    }
	
	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize){
//		myInAirCounter = 0;
		List<Object> parameters = SaladUtil.copyObjectList(myCollisionManager.getTileCollisionBehavior(colid, tilecid));
		if(parameters == null) return; // just to make sure
		String collisionBehavior = (String) parameters.get(0);
		parameters.remove(0);
		parameters.add(tilecid);
		parameters.add(tx);
		parameters.add(ty);
		parameters.add(txsize);
		parameters.add(tysize);
		behaviorReflection(myBehaviors, collisionBehavior, parameters, "collide");
	}
	
	public void autoMove(){
		if(myMoveBehavior == null) return;
		behaviorReflection(myBehaviors, myMoveBehavior, myMoveParameters, "move");
	}
	
	public void shoot(){
		if(myShootBehavior == null) return;
		behaviorReflection(myBehaviors, myShootBehavior, myShootParameters, "shoot");
	}
	
	/**
	 * Should be called by the Parser class to get all attributes of the GameObject
	 * Return a list of Strings that match with the Data Format but without Key 
	 * @return a list of Strings
	 */
	public List<String> getAttributes(){
		return myAttributes;
	}
	
	/**When ModifyActor/PlayerImage is called, the gfx info is passed along
         */
	public void updateImageURL(String gfx){
	    this.myGfx = gfx;
	}
	
	
	
/* @NOTE:
 * The following getter and setters used for GameFactoryTest
 * Will remove them once finished
 */
    /**
     * @return the myMoveBehavior
     */
    public String getMyMoveBehavior () {
        return myMoveBehavior;
    }

    /**
     * @return the myDieBehavior
     */
    public String getMyDieBehavior () {
        return myDieBehavior;
    }
    
    /**
     * @return the myInitX
     */
    public double getMyInitX() {
        return myInitX;
    }

    /**
     * @return the Gfx info
     */
    public String getMyGfx(){
        return myGfx;
    }

    /**
     * @return set the initXSpeed and initYSpeed
     */
    public void updateInitSpeed (double xspeed, double yspeed) {
        myInitX = xspeed;
        myInitY = yspeed;
    }
    
//    //plz review
//	public void addSDCollisionBehavior(String direction, String type, int otherColid, Object ... args){
//		int dir = Arrays.asList(new String[]{"up","bottom","left","right"}).indexOf(direction);
//		if (dir == -1) return;
//		SideDetecter sd = mySideDetecters[dir];
//		if (sd == null){
//			sd = new SideDetecter(this,dir);
//			mySideDetecters[dir] = sd;
//		}
//		sd.setCollisionBehavior(type, otherColid, args);
//	}
//	public void addSDTileCollisionBehavior(String direction, String type, int tileColid, Object ... args){
//		int dir = Arrays.asList(new String[]{"up","bottom","left","right"}).indexOf(direction);
//		if (dir == -1) return;
//		SideDetecter sd = mySideDetecters[dir];
//		if (sd == null){
//			sd = new SideDetecter(this,dir);
//			mySideDetecters[dir] = sd;
//		}
//		sd.setTileCollisionBehavior(type, tileColid, args);
//	}
	

}
