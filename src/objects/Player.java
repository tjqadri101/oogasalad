package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbox2d.common.Vec2;

import reflection.Reflection;
import saladConstants.SaladConstants;
/**
 * @Author: Justin (Zihao) Zhang
 */
public class Player extends GameObject {
	public static final float XVEL = 5;
	public static final float YVEL = 5;
	
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
//				eng.clearKey(key);
			}
		}
	}
	
	public void moveUp(){
		System.out.println("Player moveUp");
		if (y <= 0) return;
		Vec2 velocity = myBody.getLinearVelocity();
   		velocity.y = -YVEL;
        myBody.setLinearVelocity(velocity);
	}
	
	public void moveDown(){
		if (y >= eng.pfHeight()) return;
		Vec2 velocity = myBody.getLinearVelocity();
   		velocity.y = YVEL;
        myBody.setLinearVelocity(velocity);
	}
	
	public void moveLeft(){
		if (x <= 0) return;
		Vec2 velocity = myBody.getLinearVelocity();
   		velocity.x = -XVEL;
        myBody.setLinearVelocity(velocity);
	}
	
	public void moveRight(){
		if (x >= eng.pfWidth())	return;
		Vec2 velocity = myBody.getLinearVelocity();
   		velocity.x = XVEL;
        myBody.setLinearVelocity(velocity);
	}
	
	@Override
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.CREATE_PLAYER + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.IMAGE + "," + getGraphic() + "," + SaladConstants.POSITION + "," + x + "," + y + "," + SaladConstants.NAME + "," + getName() + "," + SaladConstants.COLLISION_ID + "," + colid);
		answer.add(SaladConstants.MODIFY_PLAYER + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.MOVE + "," + myMoveBehavior + "," + mySetXSpeed + "," + mySetYSpeed);
		answer.add(SaladConstants.MODIFY_PLAYER + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.DIE + "," + myDieBehavior);
		for(int otherID: myCollisionMap.keySet()){
			answer.add(SaladConstants.MODIFY_PLAYER + "," + SaladConstants.COLLISION_ID + "," + colid + "," + SaladConstants.COLLISION + "," + myCollisionMap.get(otherID) + "," + otherID);
		}
		for(int key: myKeyMap.keySet()){
			answer.add(SaladConstants.MODIFY_PLAYER + "," + SaladConstants.SET_KEY + "," + key + "," + myKeyMap.get(key));
		}
		return answer;
	}

}
