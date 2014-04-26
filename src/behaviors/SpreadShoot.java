package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
/**
 * Shoot a line of bullets and spread out
 * @author Main Justin (Zihao) Zhang
 */
public class SpreadShoot extends Shootable{

	protected SpreadShoot(GameObject o) {
		super(o);
	}

	/**
	 * @param String bullet's Image Name
	 * @param int x size of the image
	 * @param int y size of the image
	 * @param int collision ID of the bullet
	 * @param double absolute speed of the bullet
	 * @param the number of bullets per shoot
	 */
	@Override
	public void shoot(List<Object> objects) {
		GameEngine engine = (GameEngine) myObject.eng;
		double xface = myObject.xdir * myObject.xspeed;
		double yface = myObject.ydir * myObject.yspeed;
		
		String imageName = (String) objects.get(0);
		int xsize = (Integer) objects.get(1);
		int ysize = (Integer) objects.get(2);
		int colid = (Integer) objects.get(3);
		double shootSpeed = (Double) objects.get(4);
		int times = (Integer) objects.get(5);
		
		double shootXSpeed, shootYSpeed, xpos, ypos;
		if(xface < 0){
			xpos = myObject.x - xsize;
//			shootXSpeed = -shootSpeed;
		}
		else if (xface > 0){
			xpos = myObject.x + myObject.getXSize();
//			shootXSpeed = shootSpeed;
		}
		else{
			xpos = myObject.x + myObject.getXSize()/2;
//			shootXSpeed = myObject.xdir*shootSpeed;
		}
		if(yface < 0){
			ypos = myObject.y - ysize;
//			shootYSpeed = -shootSpeed;
		}
		else if (yface > 0){
			ypos = myObject.y + myObject.getYSize(); 
//			shootYSpeed = shootSpeed;
		}
		else{
			ypos = myObject.y + myObject.getYSize()/2;
//			shootYSpeed = myObject.ydir*shootSpeed;
		}
		shootXSpeed = myObject.xdir*shootSpeed;
//		shootYSpeed = myObject.ydir*shootSpeed;
		shootYSpeed = 0;
		
		for(int i = 0; i < times; i ++){
			NonPlayer object = engine.createActor(SaladConstants.NULL_UNIQUE_ID, imageName, xsize, ysize, xpos+20*i, ypos, SaladConstants.SHOOT_NAME, colid, SaladConstants.SHOOT_LIVES);
			object.setSpeed(shootXSpeed, shootYSpeed);	
			object.setDieBehavior(SaladConstants.REGULAR_REMOVE);
		}
	}

}