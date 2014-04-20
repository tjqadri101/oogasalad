package objects;

import jgame.JGObject;
import saladConstants.SaladConstants;

public class SideDetecter extends GameObject{

	public static final double DETECTER_FACTOR = 0.1;

	protected GameObject myParent;
	protected int myDirection;

	protected SideDetecter(GameObject parent, int direction) {
		super(SaladConstants.NULL_UNIQUE_ID, null, 0, 0, 0, 0, null, parent.colid, 1);
		myParent = parent;
		myDirection = direction;
		move();
		setSDBBox(direction);
	}

	public void move(){
//		setPos(myParent.x, myParent.y);
		x = myParent.x;
		y = myParent.y;
		System.out.println(getBBox());
	}
	
	//for testing only
	public void hit(JGObject other){
		super.hit(other);
		System.out.println("lol");
	}
	public void paint(){
		switch(myDirection){
		case 0: 
			eng.drawRect(x+myParent.getXSize()*DETECTER_FACTOR, y, (int)(myParent.getXSize()*(1-2*DETECTER_FACTOR)), 5, true, false);
			break;
		case 1: 
			eng.drawRect(x+myParent.getXSize()*DETECTER_FACTOR, y+myParent.getYSize(), (int)(myParent.getXSize()*(1-2*DETECTER_FACTOR)), 5, true, false);
			break;
		case 2: 
			eng.drawRect(x, y+myParent.getYSize()*DETECTER_FACTOR, 5, (int)(myParent.getYSize()*(1-2*DETECTER_FACTOR)), true, false);
			break;
		case 3: 
			eng.drawRect(x+myParent.getXSize(), y+myParent.getYSize()*DETECTER_FACTOR, 5, (int)(myParent.getYSize()*(1-2*DETECTER_FACTOR)), true, false);
			break;
		default: break;
		}
	}

	protected void setSDBBox(int direction){
		switch(direction){
		case 0: 
			setBBox((int)(myParent.getXSize()*DETECTER_FACTOR), 0, (int)(myParent.getXSize()*(1-2*DETECTER_FACTOR)), 1);
			break;
		case 1: 
			setBBox((int)(myParent.getXSize()*DETECTER_FACTOR), myParent.getYSize(), (int)(myParent.getXSize()*(1-2*DETECTER_FACTOR)), 1);
			break;
		case 2: 
			setBBox(0, (int)(myParent.getYSize()*DETECTER_FACTOR), 1, (int)(myParent.getYSize()*(1-2*DETECTER_FACTOR)));
			break;
		case 3: 
			setBBox(myParent.getXSize(), (int)(myParent.getYSize()*DETECTER_FACTOR), 1, (int)(myParent.getYSize()*(1-2*DETECTER_FACTOR)));
			break;
		default: break;
		}
	}
}
