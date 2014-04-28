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
		constructImageMappings();
		myObject = object;
	}
	
	private void constructImageMappings() {
		myAnimationResources = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
				+ SaladConstants.OBJECT_IMAGES);
		Enumeration<String> keys = myAnimationResources.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			myImageMappings.put(key, SaladConstants.DEFAULT_ENGINE_PACKAGE + myAnimationResources.getString(key));
		}
	}
	/**
	 * Will cause the appearing image of the object to change to the appropriate behavior
	 * @param obj
	 * @param behavior
	 */
	public void updateImage(String behavior) {
		String newImg = myImageMappings.get(behavior);
		if (newImg != null) myObject.setImage(newImg);
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
		for (String s : myImageMappings.keySet()) {
			List<Object> temp = new ArrayList<Object>();
			temp.add(s);
			temp.add(myImageMappings.get(s));
			myAttributes.add(AttributeMaker.addAttribute(myObject.modificationString(), SaladConstants.ID, myObject.getID() ));
		}
	}
	
	public List<String> getAttributes(){
		
		return myAttributes;
	}
}
