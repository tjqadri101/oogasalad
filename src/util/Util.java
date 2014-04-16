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
	
	public static void printStringList(List<String> list){
		System.out.println("StringList starts: ");
		for(String s: list){
			System.out.print(s + " ");
		}
		System.out.print("//StringList ends.");
		System.out.println();
	}

}
