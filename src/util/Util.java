package util;

import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public static List<String> convertStringArrayToList(String[] array){
		List<String> answer = new ArrayList<String>();
		for(int i = 0; i < array.length; i ++){
			answer.add(array[i]);
		}
		return answer;
	}

}
