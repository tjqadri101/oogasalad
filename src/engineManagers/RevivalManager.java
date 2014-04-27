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

	public void undo() {
		if (removedObjects.peek() != null) {
			removedObjects.pop().restore(true);
		} else {
			return;
		}
	}
	
	public void addRemovedObject(GameObject go) {
		removedObjects.push(go);
	}
	
	public Stack<GameObject> getRemovedObjects() {
		return removedObjects;
	}
}
