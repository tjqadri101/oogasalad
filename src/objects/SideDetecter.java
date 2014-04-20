package objects;

import engine.GameEngine;
import jgame.JGObject;
import saladConstants.SaladConstants;

public class SideDetecter extends GameObject{

	public static final double DETECTER_FACTOR = 0.1;

	protected GameObject myParent;

	protected SideDetecter(GameObject parent, int direction) {
		super(SaladConstants.NULL_UNIQUE_ID, "ball20-red.gif", 0, 0, 0, 0, null, parent.colid, 1);
		GameEngine engine = (GameEngine) eng;
		engine.loadImage("ball20-red.gif");
		myParent = parent;
		move();
		setBBox(0, 0, 200, 200);
		//setSDBBox(direction);
	}

	public void move(){
		setPos(myParent.x, myParent.y);
	}
	
	public void hit(JGObject other){
		super.hit(other);
		System.out.println("lol");
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
