package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import engine.GameEngine;
import engineManagers.ScoreManager;
import reflection.Reflection;
import saladConstants.SaladConstants;
import jgame.JGObject;
/**
 * GameObject is the superclass of Player and NonPlayer
 * GameObject is a game unit that can execute certain actions and interactions 
 * @Author: Justin (Zihao) Zhang
 */
public abstract class GameObject extends JGObject {
    public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_BEHAVIOR = "ObjectBehaviors";
    
	protected ResourceBundle myBehaviors;
//	protected ScoreManager myScoreManager;
	private String myDieBehavior;
	private String myMoveBehavior;
	private double mySetXSpeed;
	protected double mySetYSpeed;
	protected Map<Integer, String> myCollisionMap;
	protected Map<Integer, String> myTileCollisionMap;
//	protected Map<Integer, String>
	protected int myLives;
	protected int myUniqueID;
	protected String myJumpBehavior;
	protected double myJumpForceMagnitude;
	protected String myShootBehavior;
	protected double myInitX;
	protected double myInitY;
	protected int myInitLives;
	protected int myShootFrequency;
	protected String myShootImage;
	protected int myShootColid;
	protected int myShootXSize; 
	protected int myShootYSize;
	protected double myShootSpeed;
	protected int myXSize;
	protected int myYSize;
	
	protected GameObject(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, String name, int collisionId, int lives){
		super(name, true, xpos, ypos, collisionId, gfxname);
		myBehaviors = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
		myCollisionMap = new HashMap<Integer, String>();
		myTileCollisionMap = new HashMap<Integer, String>();
		setPos(xpos, ypos);
		setLives(lives); // change later
		myUniqueID = uniqueID;
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
	
	@Override
	public void setPos(double x, double y){
		super.setPos(x, y);
		myInitX = x;
		myInitY = y;
	}
	
	/**
	 * Restore to original state within a scene
	 * Used for live-editing
	 */
	public void restore(){
		setPos(myInitX, myInitY);
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
	public void setDieBehavior(String s){
		myDieBehavior = s;
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
	public void setJumpBehavior(String s, double magnitude){
		myJumpBehavior = s;
		myJumpForceMagnitude = magnitude;
	}
	
	/**
	 * Set the shoot behavior
	 * @param frequency: number of bullets for one shoot
	 * @param imageName: the file name of the image of the bullet
	 * @param xsize: the x size of the image of the bullet
	 * @param ysize: the y size of the image of the bullet
	 * @param colid: the collision ID of the bullet
	 * @param speed: the speed of the bullet
	 */
	public void setShootBehavior(int frequency, String imageName, int xsize, int ysize, int colid, double speed){
		myShootFrequency = frequency;
		myShootImage = imageName;
		myShootColid = colid;
		myShootXSize = xsize;
		myShootYSize = ysize;
		myShootSpeed = speed;
	}
	
	/**
	 * Set the move behavior
	 * @param s: String specifying the move behavior
	 * @param xspeed: the x speed 
	 * @param yspeed: the y speed
	 */
	public void setMoveBehavior(String s, double xspeed, double yspeed){
		myMoveBehavior = s;
		mySetXSpeed = xspeed;
		mySetYSpeed = yspeed;
	}
	
	/**
	 * Set the collision behavior
	 * @param type: String specifying the collision behavior
	 * @param id: the collision ID of the hitter
	 */
	public void setCollisionBehavior(String type, int otherColid){
		myCollisionMap.put(otherColid, type);
	}
	
	/**
	 * Set the tile collision behavior
	 * @param type: String specifying the tile collision behavior
	 * @param tileID: the tile collision ID
	 */
	public void setTileCollisionBehavior(String type, int tileID){
		myTileCollisionMap.put(tileID, type);
	}
	
	public void die(){
		behaviorNoParameterReflection(myBehaviors, getMyDieBehavior(), "remove");	
	}
	
	/**
	 * Reset the collision ID
	 * @param new collisionID
	 */
	public void resetCollisionID(int collisionID){
		colid = collisionID;
	}
	
	public void jump(){
		System.out.println("jump called");
		if(myJumpBehavior == null) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myJumpBehavior), this);
			Reflection.callMethod(behavior, "jump", myJumpForceMagnitude);	
		} catch (Exception e){
			e.printStackTrace(); //should never reach here
		}
	}
	
	/**
	 * Do not call this method directly
	 * @param ResourceBundle
	 * @param myString
	 * @param methodName
	 */
	protected void behaviorNoParameterReflection(ResourceBundle myBundle, String myString, String methodName){
		if(myString == null) return;
		try{
			Object behavior = Reflection.createInstance(myBundle.getString(myString), this);
			Reflection.callMethod(behavior, methodName);	
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
		if(!myCollisionMap.containsKey(other.colid)) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myCollisionMap.get(other.colid)), this);
			Reflection.callMethod(behavior, "collide", other);	
		} catch (Exception e){
			e.printStackTrace(); // should never reach here
		}
    }
	
	@Override
	public void hit_bg(int tilecid, int tx, int ty, int txsize, int tysize){
		if(!myTileCollisionMap.containsKey(tilecid)) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myTileCollisionMap.get(tilecid)), this);
			Reflection.callMethod(behavior, "collide", tilecid, tx, ty, txsize, tysize);	
		} catch (Exception e){
			e.printStackTrace(); // should never reach here
		} 
	}
	
	public void autoMove(){
		if(myMoveBehavior == null) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(getMyMoveBehavior()), this);
			Reflection.callMethod(behavior, "move", getMySetXSpeed(), mySetYSpeed);	
		} catch (Exception e){
			e.printStackTrace(); //should never reach here
		}
	}
	
	public void shoot(){
		if(myShootImage == null) return;
		GameEngine engine = (GameEngine) eng;
		double xface = xdir * xspeed;
		double yface = ydir * yspeed;
		double shootXSpeed, shootYSpeed, xpos, ypos;
		if(xface < 0){
			xpos = x - myShootXSize;
			shootXSpeed = -myShootSpeed;
		}
		else if (xface > 0){
			xpos = x + myXSize;
			shootXSpeed = myShootSpeed;
		}
		else{
			xpos = x + myXSize/2;
			shootXSpeed = 0;
		}
		if(yface < 0){
			ypos = y - myShootYSize;
			shootYSpeed = -myShootSpeed;
		}
		else if (yface > 0){
			ypos = y + myYSize; 
			shootYSpeed = myShootSpeed;
		}
		else{
			ypos = y + myYSize/2;
			shootYSpeed = 0;
		}
		NonPlayer object = engine.createActor(SaladConstants.SHOOT_UNIQUE_ID, myShootImage, myShootXSize, myShootYSize, xpos, ypos, SaladConstants.SHOOT_NAME, myShootColid, SaladConstants.SHOOT_LIVES);
		object.setSpeed(shootXSpeed, shootYSpeed);
	}
	
	/**
	 * Should be called by the Parser class to get all attributes of the GameObject
	 * Return a list of Strings that match with the Data Format but without Key 
	 * @return a list of Strings
	 */
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.CREATE_ACTOR + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.IMAGE + "," + getGraphic() + "," + SaladConstants.POSITION + "," + x + "," + y + "," + SaladConstants.NAME + "," + getName() + "," + SaladConstants.COLLISION_ID + "," + colid);
		answer.add(SaladConstants.MODIFY_ACTOR + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.MOVE + "," + getMyMoveBehavior() + "," + getMySetXSpeed() + "," + mySetYSpeed);
		answer.add(SaladConstants.MODIFY_ACTOR + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.DIE + "," + getMyDieBehavior());
		for(int otherID: myCollisionMap.keySet()){
			answer.add(SaladConstants.MODIFY_ACTOR + "," + SaladConstants.COLLISION_ID + "," + colid + "," + SaladConstants.COLLISION + "," + myCollisionMap.get(otherID) + "," + otherID);
		}
		return answer;
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
     * @return the mySetXSpeed
     */
    public double getMySetXSpeed () {
        return mySetXSpeed;
    }


    /**
     * @return the myDieBehavior
     */
    public String getMyDieBehavior () {
        return myDieBehavior;
    }
}
