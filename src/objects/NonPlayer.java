package objects;

import saladConstants.SaladConstants;

public class NonPlayer extends GameObject {

	public NonPlayer(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, String name, int collisionId, int lives) {
		super(uniqueID, gfxname, xsize, ysize, xpos, ypos, name, collisionId, lives);
		myIsPlayer = false;
		
		myAttributes.add(SaladConstants.CREATE_ACTOR + "," + SaladConstants.ID + "," + myUniqueID + "," + 
				SaladConstants.IMAGE + "," + getGraphic() + "," + myXSize + "," + myYSize + "," +
				SaladConstants.POSITION + "," + myInitX + "," + myInitY + "," + SaladConstants.NAME + "," + getName() + "," + 
				SaladConstants.COLLISION_ID + "," + colid + "," + SaladConstants.LIVES + "," + myInitLives);
	}

	@Override
	public void move(){
		super.move();
		autoMove();
	}
}
