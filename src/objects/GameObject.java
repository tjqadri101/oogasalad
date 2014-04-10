package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import engineManagers.ScoreManager;
import reflection.Reflection;
import saladConstants.SaladConstants;
import jboxGlue.PhysicalObject;
import jgame.JGObject;
/*
 * @Author: Justin (Zihao) Zhang
 */
public abstract class GameObject extends PhysicalObject {
	public static final int DEFAULT_LIVES = 1;
    public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_BEHAVIOR = "ObjectBehaviors";
    
	protected ResourceBundle myBehaviors;
//	protected ScoreManager myScoreManager;
	protected String myDieBehavior;
	protected String myMoveBehavior;
	protected double mySetXSpeed;
	protected double mySetYSpeed;
	protected Map<Integer, String> myCollisionMap;
//	protected Map<Integer, String>
	protected int myLives;
	protected int myUniqueID;
	protected String myJumpBehavior;
	protected double myJumpForceMagnitude;
	protected String myShootBehavior;
	
	protected void initObject(int uniqueID, double xpos, double ypos){
		myBehaviors = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
		myCollisionMap = new HashMap<Integer, String>();
		setPos(xpos, ypos);
		myLives = DEFAULT_LIVES; // change later
		myUniqueID = uniqueID;
	}
	
	protected GameObject(int uniqueID, String gfxname, double xpos, double ypos, String name, int collisionId){
		super(name, collisionId, gfxname);
		initObject(uniqueID, xpos, ypos);
	}
	
	public int getID(){
		return myUniqueID;
	}

	public void setDieBehavior(String s){
		myDieBehavior = s;
	}
	
	public void loseLife(){
		myLives --;
	}
	
	public void setLives(int lives){
		myLives = lives;
	}
	
	public void setJumpBehavior(String s, double forceMagnitude){
		myJumpBehavior = s;
		myJumpForceMagnitude = forceMagnitude;
	}
	
	public void setShootBehavior(String s){
		myShootBehavior = s;
	}
	
	public void setMoveBehavior(String s, double xspeed, double yspeed){
		myMoveBehavior = s;
		mySetXSpeed= xspeed;
		mySetYSpeed = yspeed;
	}
	
	public void setCollisionBehavior(String type, int id){
		myCollisionMap.put(id, type);
	}
	
	public void die(){
		behaviorNoParameterReflection(myBehaviors, myDieBehavior, "remove");	
	}
	
	public void jump(){
		if(myJumpBehavior == null) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myJumpBehavior), this);
			Reflection.callMethod(behavior, "jump", myJumpForceMagnitude);	
		} catch (Exception e){
			e.printStackTrace(); //should never reach here
		}
	}
	
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
		super.move();
		if(myLives <= 0) die();
	}
	
	@Override
	public void hit (JGObject other)
    {
		if(!myCollisionMap.containsKey(other.colid)) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myCollisionMap.get(other.colid)), this);
			Reflection.callMethod(behavior, "collide", other);	
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
	
	//need modification
	public void shoot(){
		if(myShootBehavior == null) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myShootBehavior), this);
			Reflection.callMethod(behavior, "shoot"); // need modification
		} catch (Exception e){
			e.printStackTrace(); //should never reach here
		}
	}
	
	@Override
	protected void paintShape() {
		// do nothing; image already set
	}
	
	/*
	 * Should be called by the Parser class to get all attributes of the GameObject
	 * Return a list of Strings that match with the Data Format but without Key 
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
