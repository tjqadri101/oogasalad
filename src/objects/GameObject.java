package objects;

import java.util.ResourceBundle;

import jboxGlue.PhysicalObject;
import jgame.JGColor;

public abstract class GameObject extends PhysicalObject{
    public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_BEHAVIOR = "";
    
	protected ResourceBundle myDieWays;
	protected ResourceBundle myMoveWays;

	protected GameObject(String name, int collisionId, JGColor color) {
		super(name, collisionId, color);
	}
	
	protected void initObject(){
		myDieWays = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
	}
	
	protected GameObject(String name, int collisionId, String gfxname){
		super(name, collisionId, gfxname);
	}
	
	public setDieBehavior(String s){
		
	}
	
	public void die(){
		
	}
}
