package parser;

import com.google.gson.Gson;

public class Serializer {
	Gson myGson;
	String json; 
	
	public Serializer(){
		 myGson = new Gson();
		 json = new String(); 
	}
	
	public void serialize(Object object, String url){ 
		json = myGson.toJson(object); 
		System.out.println(json); 
	}
}
