package objects;

import jgame.JGColor;

public class NonPlayer extends GameObject {

	protected NonPlayer(String name, double xpos, double ypos, int collisionId, String gfxname) {
		super(name, xpos, ypos, collisionId, gfxname);

	}
	
	protected NonPlayer(String name, double xpos, double ypos, int collisionId, JGColor color) {
		super(name, xpos, ypos, collisionId, color);

	}

}
