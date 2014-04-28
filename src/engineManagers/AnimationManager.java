package engineManagers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import objects.GameObject;
import saladConstants.SaladConstants;

/**
 * 
 * @author David Chou
 *
 */

public class AnimationManager {

	protected HashMap<String, String> myImageMappings;
	protected ResourceBundle myAnimationResources;
	protected List<String> myAttributes;
	
	public AnimationManager() {
		myImageMappings = new HashMap<String, String>();
		constructImageMappings();
	}
	
	private void constructImageMappings() {
		myAnimationResources = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
				+ SaladConstants.OBJECT_IMAGES);
		Enumeration<String> keys = myAnimationResources.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			myImageMappings.put(key, myAnimationResources.getString(key));
		}
	}
	/**
	 * Will cause the appearing image of the object to change to the appropriate behavior
	 * @param obj
	 * @param behavior
	 */
	public void updateImage(GameObject obj, String behavior) {
		String newImg = myImageMappings.get(behavior);
		if (newImg != null) obj.setImage(newImg);
	}
	/**
	 * Changes the corresponding behavioral image to the new image specified
	 * @param behavior
	 * @param newImage
	 */
	public void modifyImage(String behavior, String newImage) {
		myImageMappings.put(behavior, newImage);
	}
	
}
