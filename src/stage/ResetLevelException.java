package stage;
/**
 * @Author: Justin (Zihao) Zhang
 */

public class ResetLevelException extends Exception {
	private String myExceptionString = "Level can not be reset!";
	
	public ResetLevelException(){
		super();
	}
	
	@Override
	public String getMessage(){
		return myExceptionString;
	}
}

