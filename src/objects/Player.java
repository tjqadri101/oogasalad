package objects;

import jgame.JGColor;
/*
 * @Author: Justin (Zihao) Zhang
 */
public class Player extends GameObject {

	public Player(String name, double xpos, double ypos, int collisionId, JGColor color) {
		super(name, xpos, ypos, collisionId, color);
		
	}
	
	public Player(String name, double xpos, double ypos, int collisionId, String gfxname) {
		super(name, xpos, ypos, collisionId, gfxname);

	}
	
	@Override
	public void move(){
		super.move();
	}

}
