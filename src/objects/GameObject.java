package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import reflection.Reflection;
import saladConstants.SaladConstants;
import jboxGlue.PhysicalObject;
import jgame.JGColor;
import jgame.JGObject;
/*
 * @Author: Justin (Zihao) Zhang
 */
public abstract class GameObject extends PhysicalObject implements Serializable{
	public static final int DEFAULT_LIVES = 1;
    public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_BEHAVIOR = "ObjectBehaviors";
    
	protected ResourceBundle myBehaviors;
	public String myDieBehavior;
	public String myMoveBehavior;
	protected double mySetXSpeed;
	protected double mySetYSpeed;
	public HashMap<Integer, String> myCollisionMap;
	protected int myLives;
	protected int myUniqueID;
	public String myJumpBehavior;
	protected double myJumpForceMagnitude;
	public String myShootBehavior;
	
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
	
	public void setCollisionBehavior(int id, String s){
		myCollisionMap.put(id, s);
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
		answer.add(SaladConstants.CREATE_ACTOR + ",ID," + colid + ",Image," + getGraphic() + ",Position," + x + "," + y + ",Name," + getName());
		answer.add(SaladConstants.MODIFY_ACTOR + ",ID," + colid + ",Move," + myMoveBehavior + "," + mySetXSpeed + "," + mySetYSpeed);
		answer.add(SaladConstants.MODIFY_ACTOR + ",ID," + colid + ",Die," + myDieBehavior);
		for(int otherID: myCollisionMap.keySet()){
			answer.add(SaladConstants.MODIFY_ACTOR + ",ID," + colid + ",Collision," + myCollisionMap.get(otherID) + "," + otherID);
		}
		return answer;
	}
}
