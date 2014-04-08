package objects;

import java.io.Serializable;

import jgame.JGColor;

public class NonPlayer extends GameObject implements Serializable {

	public NonPlayer(String name, double xpos, double ypos, int collisionId, String gfxname) {
		super(name, xpos, ypos, collisionId, gfxname);

	}

}
