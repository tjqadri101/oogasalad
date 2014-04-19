package objects;

public class NonPlayer extends GameObject {

	public NonPlayer(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, String name, int collisionId, int lives) {
		super(uniqueID, gfxname, xsize, ysize, xpos, ypos, name, collisionId, lives);
	}

	@Override
	public void move(){
		super.move();
		autoMove();
	}
}
