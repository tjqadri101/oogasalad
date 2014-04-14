package parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import saladConstants.SaladConstants;

public class TestGameSaverAndLoader {
	private GameSaverAndLoader myGameSaverAndLoader = new GameSaverAndLoader(); 
	private List<String> inputStrings = new ArrayList<String>(); 
	private List<String> outputStrings = new ArrayList<String>(); 

	public void init(String url){ 
		populateInputStrings(); 
		saveDataToFile(url); 
		loadDataFromFile(url); 
	}

	public void populateInputStrings(){ 
		for(int i=0; i<7; i++){
			inputStrings.add("test_"+i); 
		}
	}
	
	public void saveDataToFile(String url){
		try {
			myGameSaverAndLoader.save(inputStrings, url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} 
	}

	public void loadDataFromFile(String url){ 
		try {
			outputStrings = myGameSaverAndLoader.load(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		System.out.println(outputStrings.size()); 
	}

	@Test
	public void testListElementsOrder() throws Exception{
		init(SaladConstants.TEST_XMLFILE_URL);
		for(String attribute: inputStrings){
			assertEquals(inputStrings, outputStrings); 
		}
	}

	@Test
	public void testListSizes(){
		init(SaladConstants.TEST_XMLFILE_URL);
		assertEquals(inputStrings.size(), outputStrings.size()); 
	}
}
