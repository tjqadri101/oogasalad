package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import reflection.Reflection;
import saladConstants.SaladConstants;
/*
 * @Author: Justin (Zihao) Zhang
 */
public class Player extends GameObject {
	public static final int DEFAULT_KEY = '`';
	
	protected Map<Integer, String> myKeyMap;
	
	public Player(int uniqueID, String gfxname, double xpos, double ypos, String name, int collisionID) {
		super(uniqueID, gfxname, xpos, ypos, name, collisionID);
		myKeyMap = new HashMap<Integer, String>();
	}
	
	public void setKey(int key, String type){
		myKeyMap.put(key, type);
	}
	
	@Override
	public void move(){
		super.move();
		checkKeys();
	}
	
	public void checkKeys(){
		for(int key: myKeyMap.keySet()){
			if(eng.getKey(key)){
				Reflection.callMethod(this, myKeyMap.get(key));
			}
		}
	}
	
	public void moveUp(){
		if (y > 0) ydir = -1;
	}
	
	public void moveDown(){
		if (y < eng.pfHeight())  	ydir = 1;
	}
	
	public void moveLeft(){
		if (x > 0)  			xdir = -1;
	}
	
	public void moveRight(){
		if (x < eng.pfWidth()) 	xdir = 1; 
	}
	
	@Override
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.CREATE_PLAYER + ",ID," + myUniqueID + ",Image," + getGraphic() + ",Position," + x + "," + y + ",Name," + getName() + ",CollisionID," + colid);
		answer.add(SaladConstants.MODIFY_PLAYER + ",ID," + myUniqueID + ",Move," + myMoveBehavior + "," + mySetXSpeed + "," + mySetYSpeed);
		answer.add(SaladConstants.MODIFY_PLAYER + ",ID," + myUniqueID + ",Die," + myDieBehavior);
		for(int otherID: myCollisionMap.keySet()){
			answer.add(SaladConstants.MODIFY_PLAYER + ",Colid," + colid + ",Collision," + myCollisionMap.get(otherID) + "," + otherID);
		}
		for(int key: myKeyMap.keySet()){
			answer.add(SaladConstants.MODIFY_PLAYER + ",SetKey," + key + "," + myKeyMap.get(key));
		}
		return answer;
	}

}
