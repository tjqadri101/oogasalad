package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

//import engineManagers.ScoreManager;
import reflection.Reflection;
import saladConstants.SaladConstants;
import jgame.JGObject;
/**
 * GameObject is the superclass of Player and NonPlayer
 * GameObject is a game unit that can execute certain actions and interactions 
 * @Author: Justin (Zihao) Zhang
 */
public abstract class GameObject extends JGObject {
    
//	protected ScoreManager myScoreManager;
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
	protected List<String> myAttributes;
	
	protected boolean myIsPlayer;//need change
    
	protected ResourceBundle myBehaviors;
	protected String myDieBehavior;
	protected String myMoveBehavior;
	protected String myJumpBehavior;
	protected String myShootBehavior;
	protected Map<Integer, String> myCollisionBehavior;
	protected Map<Integer, String> myTileCollisionBehavior;

	protected List<Object> myShootParameters;
	protected List<Object> myDieParameters;
	protected List<Object> myMoveParameters;
	protected List<Object> myJumpParameters;
	protected Map<Integer, List<Object>> myCollisionParameters;
	protected Map<Integer, List<Object>> myTileCollisionParameters;
	protected SideDetecter[] mySideDetecters;//plz review
	
	protected GameObject(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, String name, int collisionId, int lives){
		super(name, true, xpos, ypos, collisionId, gfxname);
		myBehaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.DEFAULT_BEHAVIOR);
		myCollisionBehavior = new HashMap<Integer, String>();
		myCollisionParameters = new HashMap<Integer, List<Object>>();
		myTileCollisionBehavior = new HashMap<Integer, String>();
		myTileCollisionParameters = new HashMap<Integer, List<Object>>();
		setInitPos(xpos, ypos);
		setLives(lives); // change later
		myUniqueID = uniqueID;
		myXSize = xsize;
		myYSize = ysize;
		myAttributes = new ArrayList<String>();
		mySideDetecters = new SideDetecter[4];//plz review
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
		System.out.println("GameObject: setInitPos called" );
		myInitX = x;
		myInitY = y;
	}
	
	@Override
	public void setPos(double x, double y){
		super.setPos(x, y);
		System.out.println("GameObject: setPos called" );
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
	
	/**
	 * Set the collision behavior
	 * @param type: String specifying the collision behavior
	 * @param id: the collision ID of the hitter
	 */
	public void setCollisionBehavior(String type, int otherColid, Object ... args){
		myCollisionBehavior.put(otherColid, type);
		List<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < args.length; i ++){
			objects.add(args[i]);
		}
		myCollisionParameters.put(otherColid, objects);
		addAttributes(SaladConstants.COLLISION_ID, colid, type, type, otherColid, objects);
	}
	
	/**
	 * Set the tile collision behavior
	 * @param type: String specifying the tile collision behavior
	 * @param tileID: the tile collision ID
	 */
	public void setTileCollisionBehavior(String type, int tileColid, Object ... args){
		myTileCollisionBehavior.put(tileColid, type);
		List<Object> objects = new ArrayList<Object>();
		for(int i = 0; i < args.length; i ++){
			objects.add(args[i]);
		}
		myTileCollisionParameters.put(tileColid, objects);
		addAttributes(SaladConstants.COLLISION_ID, colid, type, type, tileColid, objects);
	}
	
	public void die(){
		if(myDieBehavior == null) return;
		behaviorReflection(myBehaviors, myDieBehavior, myDieParameters, "remove");	
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
	 * @return
	 */
	public int getJumpTimes(){
		return myJumpTimes;
	}
	
	/**
	 * 
	 * @return
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
		if(!myCollisionBehavior.containsKey(other.colid)) return;
		List<Object> params = myCollisionParameters.get(other.colid);
		params.add(other); //add the hitter to the end of the parameter list
		behaviorReflection(myBehaviors, myCollisionBehavior.get(other.colid), params, "collide");
    }
	
	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize){
//		myInAirCounter = 0;
		if(!myTileCollisionBehavior.containsKey(tilecid)) return;
		List<Object> params = myTileCollisionParameters.get(tilecid);
		params.add(tilecid);
		params.add(tx);
		params.add(ty);
		params.add(txsize);
		params.add(tysize);
		behaviorReflection(myBehaviors, myTileCollisionBehavior.get(tilecid), params, "collide");
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
    
    //plz review
	public void addSDCollisionBehavior(String direction, String type, int otherColid, Object ... args){
		int dir = Arrays.asList(new String[]{"up","bottom","left","right"}).indexOf(direction);
		if (dir == -1) return;
		SideDetecter sd = mySideDetecters[dir];
		if (sd == null){
			sd = new SideDetecter(this,dir);
			mySideDetecters[dir] = sd;
		}
		sd.setCollisionBehavior(type, otherColid, args);
	}
	public void addSDTileCollisionBehavior(String direction, String type, int tileColid, Object ... args){
		int dir = Arrays.asList(new String[]{"up","bottom","left","right"}).indexOf(direction);
		if (dir == -1) return;
		SideDetecter sd = mySideDetecters[dir];
		if (sd == null){
			sd = new SideDetecter(this,dir);
			mySideDetecters[dir] = sd;
		}
		sd.setTileCollisionBehavior(type, tileColid, args);
	}
}
