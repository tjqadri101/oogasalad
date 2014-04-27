package engineManagers;

import java.util.Stack;

import objects.GameObject;

/**
 * 
 * @author David Chou
 * 		   
 * The purpose of this class is to recreate objects that have been
 *         destroyed inside of the GAE. The function mainly serves as an undo.
 */
public class RevivalManager {
	
	protected Stack<GameObject> removedObjects;
	
	public RevivalManager() {
		removedObjects = new Stack<GameObject>();
	}

	public GameObject undo() {
		if (removedObjects.peek() != null) {
			return removedObjects.pop();
		} else {
			return null;
		}
	}
}
