package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public abstract class Shootable {
	
	protected GameObject myObject;
	
	protected Shootable(GameObject o){
		myObject = o;
	}
	
	protected double[] locateShootLocation(int xsize, int ysize, double shootSpeed) {
		double shootXSpeed, shootYSpeed, xpos, ypos;
		if(myObject.getXHead() < 0){ xpos = myObject.x - xsize; }
		else if (myObject.getXHead() > 0){ xpos = myObject.x + myObject.getXSize(); }
		else{ xpos = myObject.x + myObject.getXSize()/2; }
		if(myObject.getYHead() < 0){ ypos = myObject.y - ysize; }
		else if (myObject.getYHead() > 0){ ypos = myObject.y + myObject.getYSize(); }
		else{ ypos = myObject.y + myObject.getYSize()/2; }
		shootXSpeed = myObject.getXHead()*shootSpeed;
		shootYSpeed = myObject.getYHead()*shootSpeed;
		double[] answer = {xpos, ypos, shootXSpeed, shootYSpeed};
		return answer;
	}
	
	public abstract void shoot(List<Object> objects);
}
