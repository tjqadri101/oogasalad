package behaviors;

import objects.GameObject;

public class Jump extends Jumpable{
	
	public Jump(GameObject o){
		super(o);
	}
	
	public void jump(double magnitude){
			System.out.println("jump called not in air");
			//myObject.setDir(myObject.xdir, 1);
			myObject.yspeed -= magnitude;
			myObject.setIsAir(true);
	}
}
