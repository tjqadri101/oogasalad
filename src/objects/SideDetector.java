package objects;

import saladConstants.SaladConstants;
/**
 * 
 * @author Main Isaac (Shenghan) Chen
 *
 */
public class SideDetector extends GameObject{

	public static final double DETECTOR_FACTOR = 0.2;
	public static final int GENERATOR_FACTOR = 10000;

	protected GameObject myParent;
	protected int myDirection;
	
	public SideDetector(GameObject parent, int direction, int cid) {
		super(SaladConstants.NULL_UNIQUE_ID, SaladConstants.NULL, 0, 0, 0, 0, SaladConstants.NULL, cid, 1, 
				parent.getCollisionManager(), parent.getEventManager());
		myParent = parent;
		myDirection = direction;
		move();
		setSDBBox(direction);
	}

	public static int SDcid(int parent_cid, int dir){
		return parent_cid*GENERATOR_FACTOR+dir;
	}
	
	public int getParentColid(){
		return myParent.colid;
	}
	
	public GameObject getParent(){
	    return myParent;
	}
	
	public void changeBlood(int blood){
		myParent.changeBlood(blood);
	}
	
	public void move(){
		if (myParent == null) {return;}
		setPos(myParent.x, myParent.y);
	}
	
	public void stop(){
		myParent.stop();
	}
	
	public void ground(){
		myParent.ground();
	}

	protected void setSDBBox(int direction){
		switch(direction){
		case 0: 
			setBBox((int)(myParent.getXSize()*DETECTOR_FACTOR), 0, (int)(myParent.getXSize()*(1-2*DETECTOR_FACTOR)), 1);
			break;
		case 1: 
			setBBox((int)(myParent.getXSize()*DETECTOR_FACTOR), myParent.getYSize(), (int)(myParent.getXSize()*(1-2*DETECTOR_FACTOR)), 1);
			break;
		case 2: 
			setBBox(0, (int)(myParent.getYSize()*DETECTOR_FACTOR), 1, (int)(myParent.getYSize()*(1-2*DETECTOR_FACTOR)));
			break;
		case 3: 
			setBBox(myParent.getXSize(), (int)(myParent.getYSize()*DETECTOR_FACTOR), 1, (int)(myParent.getYSize()*(1-2*DETECTOR_FACTOR)));
			break;
		default: break;
		}
	}
}
