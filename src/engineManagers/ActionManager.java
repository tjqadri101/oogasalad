package engineManagers;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import objects.GameObject;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Manager the actions for a Game Object
 * For every new action A added to a Game Object, a setABehavior, an A() and a makeAAttribute methods 
 * are needed to faciliate the action
 * @author Main Justin (Zihao) Zhang
 */
public class ActionManager {

	protected ResourceBundle myBehaviors;
	protected List<String> myAttributes;
	protected GameObject myObject;
	protected String myDieBehavior;
	protected String myMoveBehavior;
	protected String myJumpBehavior;
	protected String myShootBehavior;

	protected List<Object> myShootParameters;
	protected List<Object> myDieParameters;
	protected List<Object> myMoveParameters;
	protected List<Object> myJumpParameters;
	
	public ActionManager(GameObject object){
		myObject = object;
		myBehaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
				+ SaladConstants.OBJECT_BEHAVIOR);
		myAttributes = new ArrayList<String>();
	}
	
	/**
	 * Set the Die Behavior
	 * 
	 * @param a String specifying one of the die behaviors
	 */
	public void setDieBehavior(String s, Object... args) {
		myDieBehavior = s;
		myDieParameters = SaladUtil.convertArgsToObjectList(args);
	}
	
	/**
	 * Set the jump behavior
	 * 
	 * @param String
	 *            specifying one of the jump behaviors
	 * @param Magnitude
	 *            of the initial jump speed
	 */
	public void setJumpBehavior(String s, Object... args) {
		myJumpBehavior = s;
		myJumpParameters = SaladUtil.convertArgsToObjectList(args);
	}
	
	/**
	 * Set the shoot behavior
	 * 
	 * @param s
	 *            : shoot type
	 * @param args
	 *            : parameters
	 */
	public void setShootBehavior(String s, Object... args) {
		myShootBehavior = s;
		myShootParameters = SaladUtil.convertArgsToObjectList(args);
	}
	
	/**
	 * Set the move behavior
	 * 
	 * @param s
	 *            : String specifying the move behavior
	 * @param xspeed
	 *            : the x speed
	 * @param yspeed
	 *            : the y speed
	 */
	public void setMoveBehavior(String s, Object... args) {
		myMoveBehavior = s;
		myMoveParameters = SaladUtil.convertArgsToObjectList(args);
	}
	
	public void die() {
		if (myDieBehavior == null) return;
		SaladUtil.behaviorReflection(myBehaviors, myDieBehavior,
				myDieParameters, SaladConstants.REMOVE, myObject);
	}
	
	public void jump() {
		if (myJumpBehavior == null) return;
		SaladUtil.behaviorReflection(myBehaviors, myJumpBehavior,
				myJumpParameters, SaladConstants.JUMP, myObject);
	}
	
	public void autoMove() {
		if (myMoveBehavior == null) return;
		SaladUtil.behaviorReflection(myBehaviors, myMoveBehavior,
				myMoveParameters, SaladConstants.MOVE, myObject);
	}

	public void shoot() {
		if (myShootBehavior == null) return;
		SaladUtil.behaviorReflection(myBehaviors, myShootBehavior,
				myShootParameters, SaladConstants.SHOOT, myObject);
	}
	
	protected void makeDieAttribute(){
		if(myDieBehavior == null) return;
		myAttributes.add(AttributeMaker.addAttribute(myObject.modificationString(),
				SaladConstants.ID, myObject.getID(), myDieBehavior, true,
				myDieParameters));
	}
	
	protected void makeJumpAttribute(){
		if(myJumpBehavior == null) return;
		myAttributes.add(AttributeMaker.addAttribute(myObject.modificationString(),
				SaladConstants.ID, myObject.getID(), myJumpBehavior, true,
				myJumpParameters));
	}
	
	protected void makeShootAttribute(){
		if(myShootBehavior == null) return;
		myAttributes.add(AttributeMaker.addAttribute(myObject.modificationString(),
				SaladConstants.ID, myObject.getID(), myShootBehavior, true,
				myShootParameters));
	}
	
	protected void makeMoveAttribute(){
		if(myMoveBehavior == null) return;
		myAttributes.add(AttributeMaker.addAttribute(myObject.modificationString(),
				SaladConstants.ID, myObject.getID(), myMoveBehavior, true,
				myMoveParameters));
	}
	
	
	public List<String> getAttributes(){
		makeDieAttribute();
		makeJumpAttribute();
		makeShootAttribute();
		makeMoveAttribute();
		return myAttributes;
	}
	
	// added for the sake of testing
	public String getDieBehavior(){
	        return myDieBehavior;
	}
	
	// added for the sake of testing
    public String getMoveBehavior () {
        // TODO Auto-generated method stub
        return myMoveBehavior;
    }
}
