package objects;

public class NonPlayer extends GameObject {

	public NonPlayer(int uniqueID, String gfxname, double xpos, double ypos, String name, int collisionId, int lives) {
		super(uniqueID, gfxname, xpos, ypos, name, collisionId, lives);
	}

	@Override
	public void move(){
		super.move();
		autoMove();
	}

}
