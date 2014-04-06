package objects;

import jgame.JGColor;

public class Player extends GameObject {

	protected Player(String name, double xpos, double ypos, int collisionId, JGColor color) {
		super(name, xpos, ypos, collisionId, color);
		
	}
	
	protected Player(String name, double xpos, double ypos, int collisionId, String gfxname) {
		super(name, xpos, ypos, collisionId, gfxname);

	}
	
	@Override
	public void move(){
		super.move();
	}

}
