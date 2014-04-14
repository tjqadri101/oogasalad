package exception;

import saladConstants.SaladConstants;

public class ObjectCreatedWithoutSceneException extends Exception{

	public ObjectCreatedWithoutSceneException(){
		super();
	}
	
	@Override
	public String getMessage(){
		return SaladConstants.RESET_LEVEL_EXCEPTION;
	}
}
