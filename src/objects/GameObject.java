package objects;

import java.util.ResourceBundle;

import util.reflection.Reflection;
import jboxGlue.PhysicalObject;
import jgame.JGColor;

public abstract class GameObject extends PhysicalObject{
    public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_BEHAVIOR = "";
    
	protected ResourceBundle myBehaviors;
	protected String myDieString;
	protected String myMoveString;

	protected GameObject(String name, int collisionId, JGColor color) {
		super(name, collisionId, color);
	}
	
	protected void initObject(){
		myBehaviors = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
	}
	
	protected GameObject(String name, int collisionId, String gfxname){
		super(name, collisionId, gfxname);
	}
	
	public void setDieBehavior(String s){
		myDieString = s;
	}
	
	public void setMoveBehavior(String s){
		myMoveString = s;
	}
	
	public void die(){
		behaviorReflection(myBehaviors, myDieString, "remove");
	}
	
	public void behaviorReflection(ResourceBundle myBundle, String myFile, String methodName){
		try{
			Object behavior = Reflection.createInstance(myBundle.getString(myFile), this);
			Reflection.callMethod(behavior, methodName);	
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void move(){
		behaviorReflection(myBehaviors, myMoveString, "move");
	}
}
