package controller;

import java.util.ResourceBundle;

import stage.Game;

public class DataController {
	public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
	public static final String DEFAULT_CREATEORMODIFY = "CreationOrModify";
	public static final String IS_CREATION = "Creation";
	
	//Exporter myExporter;
	//GameFactory myFactory;
	//Importer myImporter;
	//GameEngine myGameEngine;
	Game myGame;
	ResourceBundle myCreateModifyTeller;
	
	public DataController(){
		//myExporter = new Exporter();
		//myFactory = new GameFactory();
		//myImporter = new Importer();
		myGame = new Game();
		myCreateModifyTeller = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_CREATEORMODIFY);
	}
	
	public void receiveOrder(String order){
		String[] orders = order.split(",");
		if(myCreateModifyTeller.getString(orders[0]) == IS_CREATION){
			
		}
		//Object o = myFactory.processOrder(order);
		//myGame.add(o);
		//myGameEngine.receiveObject(o);
	}
	
	public void receiveXML(String url){
		//Game game = myImporter.parser(url);
	}

}
