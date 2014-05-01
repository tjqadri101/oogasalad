package engineManagers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import objects.GameObject;
import saladConstants.SaladConstants;
import util.AttributeMaker;

/**
 * 
 * @author David Chou
 *
 */

public class AnimationManager {

	protected HashMap<String, String> myImageMappings;
	protected ResourceBundle myAnimationResources;
	protected List<String> myAttributes;
	protected GameObject myObject;
	
	public AnimationManager(GameObject object) {
		myImageMappings = new HashMap<String, String>();
		myObject = object;
		myAttributes = new ArrayList<String>();
	}
	
	/**
	 * Will cause the appearing image of the object to change to the appropriate behavior
	 * @param obj
	 * @param behavior
	 */
	public void updateImage(String behavior) {
		String newImg = myImageMappings.get(behavior);
//		System.out.println("updateImage: " + newImg);
		if (newImg != null) {
			myObject.setImage(newImg);
		} else {
//			System.out.println("Behavior is null");
		}
	}
	/**
	 * Changes the corresponding behavioral image to the new image specified
	 * @param behavior
	 * @param newImage
	 */
	public void modifyImage(String behavior, String newImage) {
		myImageMappings.put(behavior, newImage);
	}
	
	public void makeImageAttributes() {
		for (String key : myImageMappings.keySet()) {
			List<Object> temp = new ArrayList<Object>();
			temp.add(myImageMappings.get(key));
			temp.add(myObject.getXSize());
			temp.add(myObject.getYSize());
			myAttributes.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_ACTOR_ANIMATION,
					SaladConstants.ID, myObject.getID(), key, true, temp ));
		}
	}
	
	public List<String> getAttributes(){
		makeImageAttributes();
		return myAttributes;
	}
}
