package objects;

import java.io.Serializable;
import java.util.List;

import reflection.Reflection;
import jgame.JGColor;
/*
 * @Author: Justin (Zihao) Zhang
 */
public class Player extends GameObject implements Serializable{
	protected int myKeyUp;
	protected int myKeyDown;
	protected int myKeyLeft;
	protected int myKeyRight;
	protected int myKeyShoot;
	protected int myKeyJump;
	
	public Player(String name, double xpos, double ypos, int collisionId, String gfxname) {
		super(name, xpos, ypos, collisionId, gfxname);
	}
	
	public void setKey(int key, String methodName){
		Reflection.callMethod(this, methodName, key);
	}
	
	protected void setShootKey(int keyShoot){
		myKeyShoot = keyShoot;
	}
	
	protected void setJumpKey(int keyJump){
		myKeyJump = keyJump;
	}
	
	protected void setMoveUpKey(int keyUp){
		myKeyUp = keyUp;
	}
	
	protected void setMoveDownKey(int keyDown){
		myKeyDown = keyDown;
	}
	
	protected void setMoveLeftKey(int keyLeft){
		myKeyLeft = keyLeft;
	}
	
	protected void setMoveRightKey(int keyRight){
		myKeyRight = keyRight;
	}
	
	@Override
	public void move(){
		super.move();
		checkMoveKeys();
	}
	
	protected void checkMoveKeys(){
		if ((eng.getKey(myKeyLeft)  && x > 0))  			xdir = -1;
		if (eng.getKey(myKeyRight) && x < eng.pfWidth()) 	xdir = 1; 
		if ((eng.getKey(myKeyUp)  && y > 0))                ydir = -1;
		if (eng.getKey(myKeyDown) && y < eng.pfHeight())  	ydir = 1;
	}
	
	protected void checkJumpKeys(){
		
	}
	
	protected void checkShootKeys(){
		
	}
	
	@Override
	public List<String> getAttributes(){
		List<String> answer = super.getAttributes();
		
		return answer;
	}

}
