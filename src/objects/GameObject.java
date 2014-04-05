package objects;

import java.util.HashMap;
import java.util.ResourceBundle;

import util.reflection.Reflection;
import jboxGlue.PhysicalObject;
import jgame.JGColor;
import jgame.JGObject;

public abstract class GameObject extends PhysicalObject{
    public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_BEHAVIOR = "ObjectBehaviors";
    public static final String DEFAULT_NULL = "null";
    
	protected ResourceBundle myBehaviors;
	protected String myDieMethod;
	protected String myMoveMethod;
	protected double mySetXSpeed;
	protected double mySetYSpeed;
	protected HashMap<Integer, String> myCollisionMap;

	protected GameObject(String name, double xpos, double ypos, int collisionId, JGColor color) {
		super(name, collisionId, color);
		initObject(xpos, ypos);
	}
	
	protected void initObject(double xpos, double ypos){
		myBehaviors = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
		myDieMethod = DEFAULT_NULL;
		myMoveMethod = DEFAULT_NULL;
		myCollisionMap = new HashMap<Integer, String>();
		setPos(xpos, ypos);
	}
	
	protected GameObject(String name, double xpos, double ypos, int collisionId, String gfxname){
		super(name, collisionId, gfxname);
		initObject(xpos, ypos);
	}
	
	public void setDieBehavior(String s){
		myDieMethod = s;
	}
	
	public void setMoveBehavior(String s, double xspeed, double yspeed){
		myMoveMethod = s;
		mySetXSpeed= xspeed;
		mySetYSpeed = yspeed;
	}
	
	public void setCollisionBehavior(int id, String s){
		myCollisionMap.put(id, s);
	}
	
	public void die(){
		behaviorNoParameterReflection(myBehaviors, myDieMethod, "remove");	
	}
	
	public void behaviorNoParameterReflection(ResourceBundle myBundle, String myFile, String methodName){
		if(myFile.equals(DEFAULT_NULL)) return;
		try{
			Object behavior = Reflection.createInstance(myBundle.getString(myFile), this);
			Reflection.callMethod(behavior, methodName);	
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void move(){
		super.move();
		autoMove();
	}
	
	@Override
	public void hit (JGObject other)
    {
		if(!myCollisionMap.containsKey(other.colid)) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myCollisionMap.get(other.colid)), this);
			Reflection.callMethod(behavior, "collide", other, this);	
		} catch (Exception e){
			e.printStackTrace();
		}
    }
	
	public void autoMove(){
		if(myMoveMethod.equals(DEFAULT_NULL)) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myMoveMethod), this);
			Reflection.callMethod(behavior, "move", mySetXSpeed, mySetYSpeed);	
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintShape() {
		
	}
	
	//public double getXPos(){ return this.x; }
	
	//public double getYPos(){ return this.y; }
}
