package stage;

import saladConstants.SaladConstants;

/**
 * @Author: Justin (Zihao) Zhang
 */

public class ResetLevelException extends Exception {
	
	public ResetLevelException(){
		super();
	}
	
	@Override
	public String getMessage(){
		return SaladConstants.RESET_LEVEL_EXCEPTION;
	}
}

