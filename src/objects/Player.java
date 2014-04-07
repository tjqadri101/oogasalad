package objects;

import jgame.JGColor;
/*
 * @Author: Justin (Zihao) Zhang
 */
public class Player extends GameObject {
	
	protected int myKeyUp;
	protected int myKeyDown;
	protected int myKeyLeft;
	protected int myKeyRight;

	public Player(String name, double xpos, double ypos, int collisionId, JGColor color) {
		super(name, xpos, ypos, collisionId, color);
	}
	
	public Player(String name, double xpos, double ypos, int collisionId, String gfxname) {
		super(name, xpos, ypos, collisionId, gfxname);
	}
	
	public void setMoveUpKeys(int keyUp){

	}
	
	@Override
	public void move(){
		super.move();
		checkKeys();
	}
	
	protected void checkKeys(){
		if ((eng.getKey(myKeyLeft)  && x > 0))  			xdir = -1;
		if (eng.getKey(myKeyRight) && x < eng.pfWidth()) 	xdir = 1; 
		if ((eng.getKey(myKeyUp)  && y > 0))                ydir = -1;
		if (eng.getKey(myKeyDown) && y < eng.pfHeight())  	ydir = 1;
	}

}
