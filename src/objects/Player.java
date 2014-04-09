package objects;

import java.io.Serializable;
import java.util.List;

import reflection.Reflection;
import jgame.JGColor;
/*
 * @Author: Justin (Zihao) Zhang
 */
public class Player extends GameObject implements Serializable{
	public static final int DEFAULT_KEY = '`';
	protected int myKeyUp;
	protected int myKeyDown;
	protected int myKeyLeft;
	protected int myKeyRight;
	protected int myKeyShoot;
	protected int myKeyJump;
	
	public Player(int uniqueID, String gfxname, double xpos, double ypos, String name, int collisionID) {
		super(uniqueID, gfxname, xpos, ypos, name, collisionID);
		myKeyUp = DEFAULT_KEY;
		myKeyDown = DEFAULT_KEY;
		myKeyLeft = DEFAULT_KEY;
		myKeyRight = DEFAULT_KEY;
		myKeyRight = DEFAULT_KEY;
		myKeyShoot = DEFAULT_KEY;
		myKeyJump = DEFAULT_KEY;
	}
	
	public void setKey(int key, String methodName){
		Reflection.callMethod(this, methodName, key);
	}
	
	public void setShootKey(int keyShoot){
		myKeyShoot = keyShoot;
	}
	
	public void setJumpKey(int keyJump){
		myKeyJump = keyJump;
	}
	
	public void setMoveUpKey(int keyUp){
		myKeyUp = keyUp;
	}
	
	public void setMoveDownKey(int keyDown){
		myKeyDown = keyDown;
	}
	
	public void setMoveLeftKey(int keyLeft){
		myKeyLeft = keyLeft;
	}
	
	public void setMoveRightKey(int keyRight){
		myKeyRight = keyRight;
	}
	
	@Override
	public void move(){
		super.move();
		checkMoveUpDownKeys();
		checkMoveLeftRightKeys();
		checkJumpKeys();
		checkShootKeys();
	}
	
	protected void checkMoveLeftRightKeys(){
		if(myKeyLeft == DEFAULT_KEY|| myKeyRight == DEFAULT_KEY) return;
		if ((eng.getKey(myKeyLeft)  && x > 0))  			xdir = -1;
		if (eng.getKey(myKeyRight) && x < eng.pfWidth()) 	xdir = 1; 
	}
	
	protected void checkMoveUpDownKeys(){
		if(myKeyUp == DEFAULT_KEY|| myKeyDown == DEFAULT_KEY) return;
		if ((eng.getKey(myKeyUp)  && y > 0))                ydir = -1;
		if (eng.getKey(myKeyDown) && y < eng.pfHeight())  	ydir = 1;
	}
	
	protected void checkJumpKeys(){
		if(myKeyJump == DEFAULT_KEY) return;
		if(eng.getKey(myKeyJump)) jump();
	}
	
	protected void checkShootKeys(){
		if(myShootBehavior == null) return;
		behaviorNoParameterReflection(myBehaviors, myShootBehavior, "shoot");
	}
	
	@Override
	public List<String> getAttributes(){
		List<String> answer = super.getAttributes();
		
		return answer;
	}

}
