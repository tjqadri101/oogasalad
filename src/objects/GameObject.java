package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
	private String myDieBehavior;
	private String myMoveBehavior;
	private double mySetXSpeed;
	protected double mySetYSpeed;
	protected Map<Integer, String> myCollisionMap;
//	protected Map<Integer, String>
	protected int myLives;
	protected int myUniqueID;
	protected String myJumpBehavior;
	protected double myJumpForceMagnitude;
	protected String myShootBehavior;
	protected double myInitX;
	protected double myInitY;
	protected int myInitLives;
	
	protected GameObject(int uniqueID, String gfxname, double xpos, double ypos, String name, int collisionId, int lives){
		super(name, true, xpos, ypos, collisionId, gfxname);
		myBehaviors = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
		myCollisionMap = new HashMap<Integer, String>();
		setPos(xpos, ypos);
		setLives(lives); // change later
		myUniqueID = uniqueID;
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

	public void setDieBehavior(String s){
		setMyDieBehavior(s);
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
	
	public void setShootBehavior(String s){
		myShootBehavior = s;
	}
	
	public void setMoveBehavior(String s, double xspeed, double yspeed){
		setMyMoveBehavior(s);
		setMySetXSpeed(xspeed);
		mySetYSpeed = yspeed;
	}
	
	public void setCollisionBehavior(String type, int id){
		myCollisionMap.put(id, type);
	}
	
	public void die(){
		behaviorNoParameterReflection(myBehaviors, getMyDieBehavior(), "remove");	
	}
	
	public void resetCollisionID(int collisionID){
		colid = collisionID;
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
		if(getMyMoveBehavior() == null) return;
		System.out.println("autoMove called");
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(getMyMoveBehavior()), this);
			Reflection.callMethod(behavior, "move", getMySetXSpeed(), mySetYSpeed);	
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
     * @param myMoveBehavior the myMoveBehavior to set
     */
    public void setMyMoveBehavior (String myMoveBehavior) {
        this.myMoveBehavior = myMoveBehavior;
    }

    /**
     * @return the mySetXSpeed
     */
    public double getMySetXSpeed () {
        return mySetXSpeed;
    }

    /**
     * @param mySetXSpeed the mySetXSpeed to set
     */
    public void setMySetXSpeed (double mySetXSpeed) {
        this.mySetXSpeed = mySetXSpeed;
    }

    /**
     * @return the myDieBehavior
     */
    public String getMyDieBehavior () {
        return myDieBehavior;
    }

    /**
     * @param myDieBehavior the myDieBehavior to set
     */
    public void setMyDieBehavior (String myDieBehavior) {
        this.myDieBehavior = myDieBehavior;
    }
	
//	public void init( double width, double height, double mass )
//	{
//		PolygonDef shape = new PolygonDef();
//		shape.density = (float)mass;
//		shape.setAsBox( (float)width, (float)height );
//		createBody( shape );
//		setBBox( -(int)width/2, -(int)height/2, (int)width, (int)height );
//	}
	
//	protected void initObject(int uniqueID, double xpos, double ypos){
//	myBehaviors = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
//	myCollisionMap = new HashMap<Integer, String>();
//	init(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_MASS);
//	setPos(xpos, ypos);
//	myInitX = xpos;
//	myInitY = ypos;
//	setLives(DEFAULT_LIVES); // change later
//	myUniqueID = uniqueID;
//	 //copy the position and rotation from the JBox world to the JGame world
//	updatePositionInJGame();
//}

//protected void updatePositionInJGame() {
//	Vec2 position = myBody.getPosition();
//	x = position.x;
//	y = position.y;
//	myRotation = -myBody.getAngle();
//}
	
}
