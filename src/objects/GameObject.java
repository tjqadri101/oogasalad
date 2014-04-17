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
 * @Author: Justin (Zihao) Zhang
 */
public abstract class GameObject extends JGObject {
    public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_BEHAVIOR = "ObjectBehaviors";
    
	protected ResourceBundle myBehaviors;
//	protected ScoreManager myScoreManager;
	protected String myDieBehavior;
	protected String myMoveBehavior;
	protected double mySetXSpeed;
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
	
	protected boolean myIsAir; 
	
	protected GameObject(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, String name, int collisionId, int lives){
		super(name, true, xpos, ypos, collisionId, gfxname);
		myBehaviors = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
		myCollisionMap = new HashMap<Integer, String>();
		myTileCollisionMap = new HashMap<Integer, String>();
		setPos(xpos, ypos);
		setLives(lives); // change later
		myUniqueID = uniqueID;
		myIsAir = false;
		myXSize = xsize;
		myYSize = ysize;
	}
	
	public int getXSize(){
		return myXSize;
	}
	
	public int getYSize(){
		return myYSize;
	}
	
	@Override
	public void setPos(double x, double y){
		super.setPos(x, y);
		myInitX = x;
		myInitY = y;
	}
	
	public void restore(){
		setPos(myInitX, myInitY);
		setLives(myInitLives);
	}
	
	public void resetID(int uniqueID){
		myUniqueID = uniqueID;
	}
	
	public int getID(){
		return myUniqueID;
	}
	
	public void setIsAir(boolean isAir){
		myIsAir = isAir;
	}
	
	public boolean getIsAir(){
		return myIsAir;
	}

	public void setDieBehavior(String s){
		myDieBehavior = s;
	}
	
	public void loseLife(){
		myLives --;
	}
	
	public void setLives(int lives){
		myInitLives = lives;
		myLives = lives;
	}
	
	public int getLives(){
		return myLives;
	}
	
	public void setJumpBehavior(String s, double forceMagnitude){
		myJumpBehavior = s;
		myJumpForceMagnitude = forceMagnitude;
	}
	
	public void setShootBehavior(int frequency, String imageName, int xsize, int ysize, int colid, double speed){
		myShootFrequency = frequency;
		myShootImage = imageName;
		myShootColid = colid;
		myShootXSize = xsize;
		myShootYSize = ysize;
		myShootSpeed = speed;
	}
	
	public void setMoveBehavior(String s, double xspeed, double yspeed){
		myMoveBehavior = s;
		mySetXSpeed= xspeed;
		mySetYSpeed = yspeed;
	}
	
	public void setCollisionBehavior(String type, int id){
		myCollisionMap.put(id, type);
	}
	
	public void setTileCollisionBehavior(String type, int tileID){
		myTileCollisionMap.put(tileID, type);
	}
	
	public void die(){
		behaviorNoParameterReflection(myBehaviors, myDieBehavior, "remove");	
	}
	
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
			Object behavior = Reflection.createInstance(myBehaviors.getString(myMoveBehavior), this);
			Reflection.callMethod(behavior, "move", mySetXSpeed, mySetYSpeed);	
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
		NonPlayer object = engine.createActor(SaladConstants.SHOOT_UNIQUE_ID, myShootImage, xpos, ypos, SaladConstants.SHOOT_NAME, myShootColid, SaladConstants.SHOOT_LIVES);
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
		answer.add(SaladConstants.MODIFY_ACTOR + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.MOVE + "," + myMoveBehavior + "," + mySetXSpeed + "," + mySetYSpeed);
		answer.add(SaladConstants.MODIFY_ACTOR + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.DIE + "," + myDieBehavior);
		for(int otherID: myCollisionMap.keySet()){
			answer.add(SaladConstants.MODIFY_ACTOR + "," + SaladConstants.COLLISION_ID + "," + colid + "," + SaladConstants.COLLISION + "," + myCollisionMap.get(otherID) + "," + otherID);
		}
		return answer;
	}
	
}
