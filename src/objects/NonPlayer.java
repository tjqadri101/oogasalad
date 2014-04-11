package objects;

public class NonPlayer extends GameObject {

	public NonPlayer(int uniqueID, String gfxname, double xpos, double ypos, String name, int collisionID) {
		super(uniqueID, gfxname, xpos, ypos, name, collisionID);
		setSpeed(10, 10);
	}

	@Override
	public void move(){
		super.move();
		autoMove();
	}

}
