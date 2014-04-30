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
//		if (myDirection == 1) System.out.println("move() "+((GameEngine)eng).getSaladTimer()+" "+colid);
		if (myParent == null) {return;}
		setPos(myParent.x, myParent.y);
	}
	
	public void stop(){
		myParent.stop();
	}
	
	public void ground(){
//		if (myDirection == 1) System.out.println("ground() "+((GameEngine)eng).timer+" "+colid);
		myParent.ground();
	}
	
//	public void paint(){
//		switch(myDirection){
//		case 0: 
//			eng.drawRect(x+myParent.getXSize()*DETECTOR_FACTOR, y, (int)(myParent.getXSize()*(1-2*DETECTOR_FACTOR)), 2, true, false);
//			break;
//		case 1: 
//			eng.drawRect(x+myParent.getXSize()*DETECTOR_FACTOR, y+myParent.getYSize(), (int)(myParent.getXSize()*(1-2*DETECTOR_FACTOR)), 2, true, false);
//			break;
//		case 2: 
//			eng.drawRect(x, y+myParent.getYSize()*DETECTOR_FACTOR, 2, (int)(myParent.getYSize()*(1-2*DETECTOR_FACTOR)), true, false);
//			break;
//		case 3: 
//			eng.drawRect(x+myParent.getXSize(), y+myParent.getYSize()*DETECTOR_FACTOR, 2, (int)(myParent.getYSize()*(1-2*DETECTOR_FACTOR)), true, false);
//			break;
//		}
//	}

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
