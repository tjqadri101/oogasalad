package objects;

import java.io.Serializable;

import jgame.JGColor;

public class NonPlayer extends GameObject implements Serializable {

	public NonPlayer(int uniqueID, String gfxname, double xpos, double ypos, String name, int collisionID) {
		super(uniqueID, gfxname, xpos, ypos, name, collisionID);
	}

	@Override
	public void move(){
		super.move();
		autoMove();
	}

}
